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
  // Gloop Font by Polyducks from https://www.gridsagegames.com/rexpaint/resources.html
  val font       = SpriteSheet(Image.loadBmpImage(Resource("gloop.bmp")).get, Constants.fontWidth, Constants.fontHeight)
  val background = Image.loadBmpImage(Resource("bg.bmp")).get

  def handleInput(canvas: Canvas, ctx: Ptr[mu_Context])(implicit z: Zone): Unit = {
    val pointerInput = canvas.getPointerInput()

    pointerInput.position.foreach(pos => mu_input_mousemove(ctx, pos.x, pos.y))
    pointerInput.events.foreach {
      case (pos, true) =>
        mu_input_mousedown(ctx, pos.x, pos.y, MU_MOUSE_LEFT.toInt)
      case (pos, false) =>
        mu_input_mouseup(ctx, pos.x, pos.y, MU_MOUSE_LEFT.toInt)
    }
  }

  def renderCommands(canvas: Canvas, ctx: Ptr[mu_Context])(implicit z: Zone): Unit = {
    canvas.clear()
    canvas.blit(background)(0, 0)

    val cmd      = alloc[Ptr[mu_Command]](1)
    var clipRect = (0, 0, Constants.screenWidth, Constants.screenHeight)
    while (mu_next_command(ctx, cmd) != 0) {
      val command = !cmd
      (!command).`type`.toUInt match {
        case MU_COMMAND_TEXT =>
          val color = Color(
            (!command).text.color.r.toInt,
            (!command).text.color.g.toInt,
            (!command).text.color.b.toInt
          )
          val x   = (!command).text.pos.x
          val y   = (!command).text.pos.y
          val str = fromCString((!command).text.str.at(0))
          if (y > clipRect._2 && y + Constants.fontHeight < clipRect._2 + clipRect._4) {
            (0 until str.length).foreach { case idx =>
              val xx = x + idx * Constants.fontWidth
              if (xx > clipRect._1 && xx + Constants.fontWidth < clipRect._1 + clipRect._3) {
                val coloredChar = font.getSprite(str(idx).toInt).map {
                  case Color(255, 255, 255) => color
                  case c                    => c
                }
                canvas.blit(coloredChar, Some(Color(0, 0, 0)))(xx, y)
              }
            }
          }
        case MU_COMMAND_RECT =>
          val color = Color(
            (!command).rect.color.r.toInt,
            (!command).rect.color.g.toInt,
            (!command).rect.color.b.toInt
          )
          val x       = (!command).rect.rect.x
          val y       = (!command).rect.rect.y
          val w       = (!command).rect.rect.w
          val h       = (!command).rect.rect.h
          val surface = Plane.fromConstant(color).clip(0, 0, w, h)
          canvas.blit(surface)(x, y)
        case MU_COMMAND_ICON =>
          val color = Color(
            (!command).icon.color.r.toInt,
            (!command).icon.color.g.toInt,
            (!command).icon.color.b.toInt
          )
          val sprite = ((!command).icon.id.toUInt match {
            case MU_ICON_CLOSE     => font.getSprite(0, 0)
            case MU_ICON_CHECK     => font.getSprite(0, 1)
            case MU_ICON_COLLAPSED => font.getSprite(14, 13)
            case MU_ICON_EXPANDED  => font.getSprite(15, 13)
            case MU_ICON_MAX       => font.getSprite(15, 9)
          }).map {
            case Color(255, 255, 255) => color
            case c                    => c
          }
          val x = (!command).icon.rect.x
          val y = (!command).icon.rect.y
          val w = (!command).icon.rect.w
          val h = (!command).icon.rect.h
          if (
            y > clipRect._2 && y + Constants.fontHeight < clipRect._2 + clipRect._4 && x > clipRect._1 && x + Constants.fontWidth < clipRect._1 + clipRect._3
          ) {
            canvas.blit(sprite, Some(Color(0, 0, 0)))(
              x + (w - Constants.fontWidth) / 2,
              y + (h - Constants.fontHeight) / 2
            )
          }
        case MU_COMMAND_CLIP =>
          clipRect = (
            (!command).clip.rect.x,
            (!command).clip.rect.y,
            (!command).clip.rect.w,
            (!command).clip.rect.h
          )
        case cmd =>
          println("UNIMPLEMENTED COMMAND: " + cmd)
      }
    }
    canvas.redraw()
  }
}
