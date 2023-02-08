package eu.joaocosta.twotm8native

import scala.scalanative.unsafe.*
import scalanative.unsigned.*

import microuilib.constants.*
import microuilib.functions.*
import microuilib.types.*

import eu.joaocosta.minart.backend.defaults.*
import eu.joaocosta.minart.graphics.*
import eu.joaocosta.minart.graphics.image.*
import eu.joaocosta.minart.runtime.*

object Backend {

  /** Resources */

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
    canvas.blit(background)(0, 0)
  }

  /** Main loop function. This is just a glorified while loop with a thread sleep.
    */
  def run(ctx: Ptr[mu_Context])(appCode: Zone ?=> Unit): Unit = {
    AppLoop
      .statelessRenderLoop { (canvas: Canvas) =>
        Zone { implicit z =>
          canvas.redraw()             // Swap double buffer
          appCode                     // Run the app code to set the window
          handleInput(canvas, ctx)    // Send the inputs to microui
          canvas.clear()              // Clear canvas and input buffers
          renderBackground(canvas)    // Render the background
          renderCommands(canvas, ctx) // Process the microui commans
        }
      }
      .configure(
        Canvas.Settings(Constants.screenWidth, Constants.screenHeight, title = "Twotm8 Native"),
        LoopFrequency.hz60
      )
      .run()
  }

  /** Sends the input to microui.
    * Here I'm just sending the mouse position and left mouse click.
    */
  private def handleInput(canvas: Canvas, ctx: Ptr[mu_Context])(using z: Zone): Unit = {
    val pointerInput = canvas.getPointerInput()

    pointerInput.position.foreach(pos => mu_input_mousemove(ctx, pos.x, pos.y))
    pointerInput.events.foreach {
      case (pos, true) =>
        mu_input_mousedown(ctx, pos.x, pos.y, MU_MOUSE_LEFT.toInt)
      case (pos, false) =>
        mu_input_mouseup(ctx, pos.x, pos.y, MU_MOUSE_LEFT.toInt)
    }
  }

  /** Helper function to load microui colors */
  private def loadColor(color: mu_Color): Color =
    Color(
      color.r.toInt,
      color.g.toInt,
      color.b.toInt
    )

  /** Clip rect to know when the characters/icons need to be cropped.
    *  Some graphics API already have support for this, but I don't.
    */
  private var clipRect = (0, 0, Constants.screenWidth, Constants.screenHeight)

  /** For simplicity, I'm assuming I don't need to draw partial characters/icons */
  private def visibleChar(x: Int, y: Int) =
    y > clipRect._2 && y + Constants.fontHeight < clipRect._2 + clipRect._4 &&
      x > clipRect._1 && x + Constants.fontWidth < clipRect._1 + clipRect._3

  /** Processes a `MU_COMMAND_TEXT` command */
  def renderText(canvas: Canvas, command: mu_Command): Unit = {
    val color = loadColor(command.text.color)
    val x     = command.text.pos.x
    val y     = command.text.pos.y
    val str   = fromCString(command.text.str.at(0))
    (0 until str.length).foreach { case idx =>
      val xx = x + idx * Constants.fontWidth
      if (visibleChar(xx, y)) {
        canvas.blit(coloredChar(str(idx), color), Some(Color(0, 0, 0)))(xx, y)
      }
    }
  }

  /** Processes a `MU_COMMAND_RECT` command */
  def renderRect(canvas: Canvas, command: mu_Command): Unit = {
    val color   = loadColor(command.rect.color)
    val surface = Plane.fromConstant(color).clip(0, 0, command.rect.rect.w, command.rect.rect.h)
    canvas.blit(surface)(command.rect.rect.x, command.rect.rect.y)
  }

  /** Processes a `MU_COMMAND_ICON` command */
  def renderIcon(canvas: Canvas, command: mu_Command): Unit = {
    val color  = loadColor(command.icon.color)
    val sprite = coloredIcon(command.icon.id, color)
    val x      = command.icon.rect.x
    val y      = command.icon.rect.y
    // Note: The icon has a width and a height. Here I opted to center the icon
    val w = command.icon.rect.w
    val h = command.icon.rect.h
    if (visibleChar(x, y)) {
      canvas.blit(sprite, Some(Color(0, 0, 0)))(
        x + (w - Constants.fontWidth) / 2,
        y + (h - Constants.fontHeight) / 2
      )
    }
  }

  /** Sets the clip rectangle. Used for the `MU_COMMAND_CLIP` command */
  def setClipRect(x: Int, y: Int, w: Int, h: Int): Unit = {
    clipRect = (x, y, w, h)
  }

  /** Just go through all the commands and run the respective functions */
  def renderCommands(canvas: Canvas, ctx: Ptr[mu_Context])(implicit z: Zone): Unit = {
    // Don't forget to create a new command buffer and reset the clip
    val cmd = alloc[Ptr[mu_Command]](1)
    setClipRect(0, 0, Constants.screenWidth, Constants.screenHeight)
    while (mu_next_command(ctx, cmd) != 0) {
      val command = !cmd
      (!command).`type`.toUInt match {
        case MU_COMMAND_TEXT =>
          renderText(canvas, !command)
        case MU_COMMAND_RECT =>
          renderRect(canvas, !command)
        case MU_COMMAND_ICON =>
          renderIcon(canvas, !command)
        case MU_COMMAND_CLIP =>
          setClipRect(
            (!command).clip.rect.x,
            (!command).clip.rect.y,
            (!command).clip.rect.w,
            (!command).clip.rect.h
          )
        case cmd =>
          println("UNIMPLEMENTED COMMAND: " + cmd)
      }
    }
  }
}
