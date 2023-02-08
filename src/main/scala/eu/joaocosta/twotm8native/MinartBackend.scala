package eu.joaocosta.twotm8native

import scala.scalanative.unsafe.*
import scalanative.unsigned.*

import microuilib.constants.*
import microuilib.functions.*
import microuilib.types.*

import eu.joaocosta.minart.backend.defaults.*
import eu.joaocosta.minart.input.*
import eu.joaocosta.minart.input.KeyboardInput.Key
import eu.joaocosta.minart.graphics.*
import eu.joaocosta.minart.graphics.image.*
import eu.joaocosta.minart.runtime.*

object MinartBackend extends Backend {

  ////////// Resources and Helper functions //////////

  // Gloop Font by Polyducks from https://www.gridsagegames.com/rexpaint/resources.html
  private val font =
    SpriteSheet(Image.loadBmpImage(Resource("gloop.bmp")).get, Constants.fontWidth, Constants.fontHeight)
  private def coloredChar(char: Char, color: Color): SurfaceView = font.getSprite(char.toInt).map {
    case Color(255, 255, 255) => color
    case c                    => c
  }
  private def coloredIcon(iconId: Int, color: Color): SurfaceView = {
    (iconId.toUInt match {
      case MU_ICON_CLOSE     => font.getSprite(0, 0)
      case MU_ICON_CHECK     => font.getSprite(0, 1)
      case MU_ICON_COLLAPSED => font.getSprite(14, 13)
      case MU_ICON_EXPANDED  => font.getSprite(15, 13)
      case MU_ICON_MAX       => font.getSprite(15, 9)
    }).map {
      case Color(255, 255, 255) => color
      case c                    => c
    }
  }

  private val background = Image.loadBmpImage(Resource("bg.bmp")).get
  private def renderBackground(canvas: Canvas): Unit = {
    canvas.blit(background)(
      (Constants.screenWidth - background.width) / 2,
      (Constants.screenHeight - background.height) / 2
    )
  }

  /** Helper function to load microui colors */
  private def loadColor(color: mu_Color): Color =
    Color(
      color.r.toInt,
      color.g.toInt,
      color.b.toInt
    )

  /** Mutable Clip rect to know when the characters/icons need to be cropped.
    *  Some graphics API already have support for this, but I don't.
    */
  protected class ClipRect(var x: Int, var y: Int, var w: Int, var h: Int) {

    /** For simplicity, I'm assuming I don't need to draw partial characters/icons */
    def visibleChar(xx: Int, yy: Int) =
      yy > y && yy + Constants.fontHeight < y + h &&
        xx > x && xx + Constants.fontWidth < x + w
  }

  ////////// Backend implementation //////////

  /** Internal context for this backend: A canvas and a mutable clip rect */
  protected case class BackendContext(canvas: Canvas, clipRect: ClipRect)

  /** Main loop function. This is just a glorified while loop with a thread sleep.
    */
  def run(ctx: Ptr[mu_Context])(appCode: Zone ?=> Unit): Unit = {
    var clipRect = new ClipRect(0, 0, Constants.screenWidth, Constants.screenHeight)
    AppLoop
      .statelessRenderLoop { (canvas: Canvas) =>
        val backendContext = BackendContext(canvas, clipRect)
        Zone { implicit z =>
          canvas.redraw()                     // Swap double buffer
          appCode                             // Run the app code to set the window
          handleInput(backendContext, ctx)    // Send the inputs to microui
          canvas.clear()                      // Clear canvas and input buffers
          renderBackground(canvas)            // Render the background
          renderCommands(backendContext, ctx) // Process the microui commans
        }
      }
      .configure(
        Canvas.Settings(
          Constants.screenWidth,
          Constants.screenHeight,
          title = "Twotm8 Native",
          clearColor = Color(155, 160, 220)
        ),
        LoopFrequency.hz60
      )
      .run()
  }

  /** Sends the input to microui.
    * Here I'm just sending the mouse position and left mouse click.
    */
  protected def handleInput(backCtx: BackendContext, ctx: Ptr[mu_Context])(using z: Zone): Unit = {
    val pointerInput = backCtx.canvas.getPointerInput()

    pointerInput.position.foreach(pos => mu_input_mousemove(ctx, pos.x, pos.y))
    pointerInput.events.foreach {
      case (pos, true) =>
        mu_input_mousedown(ctx, pos.x, pos.y, MU_MOUSE_LEFT.toInt)
      case (pos, false) =>
        mu_input_mouseup(ctx, pos.x, pos.y, MU_MOUSE_LEFT.toInt)
    }

    // Switch fullscreen, just because I think it's fun
    if (backCtx.canvas.getKeyboardInput().keysPressed(Key.F)) {
      val oldSettings = backCtx.canvas.canvasSettings
      if (oldSettings.fullScreen)
        backCtx.canvas.changeSettings(oldSettings.copy(fullScreen = false, scale = 1))
      else
        backCtx.canvas.changeSettings(oldSettings.copy(fullScreen = true, scale = 2))
    }
  }

  /** Processes a `MU_COMMAND_TEXT` command */
  protected def renderText(backCtx: BackendContext, command: mu_Command): Unit = {
    val color = loadColor(command.text.color)
    val x     = command.text.pos.x
    val y     = command.text.pos.y
    val str   = fromCString(command.text.str.at(0))
    (0 until str.length).foreach { case idx =>
      val xx = x + idx * Constants.fontWidth
      if (backCtx.clipRect.visibleChar(xx, y)) {
        backCtx.canvas.blit(coloredChar(str(idx), color), Some(Color(0, 0, 0)))(xx, y)
      }
    }
  }

  /** Processes a `MU_COMMAND_RECT` command */
  protected def renderRect(backCtx: BackendContext, command: mu_Command): Unit = {
    val color   = loadColor(command.rect.color)
    val surface = Plane.fromConstant(color).clip(0, 0, command.rect.rect.w, command.rect.rect.h)
    backCtx.canvas.blit(surface)(command.rect.rect.x, command.rect.rect.y)
  }

  /** Processes a `MU_COMMAND_ICON` command */
  protected def renderIcon(backCtx: BackendContext, command: mu_Command): Unit = {
    val color  = loadColor(command.icon.color)
    val sprite = coloredIcon(command.icon.id, color)
    val x      = command.icon.rect.x
    val y      = command.icon.rect.y
    // Note: The icon has a width and a height. Here I opted to center the icon
    val w = command.icon.rect.w
    val h = command.icon.rect.h
    if (backCtx.clipRect.visibleChar(x, y)) {
      backCtx.canvas.blit(sprite, Some(Color(0, 0, 0)))(
        x + (w - Constants.fontWidth) / 2,
        y + (h - Constants.fontHeight) / 2
      )
    }
  }

  /** Sets the clip rectangle. Used for the `MU_COMMAND_CLIP` command */
  protected def setClipRect(backCtx: BackendContext, x: Int, y: Int, w: Int, h: Int): Unit = {
    backCtx.clipRect.x = x
    backCtx.clipRect.y = y
    backCtx.clipRect.w = w
    backCtx.clipRect.h = h
  }
}
