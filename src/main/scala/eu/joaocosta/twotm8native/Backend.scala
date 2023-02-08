package eu.joaocosta.twotm8native

import scala.scalanative.unsafe.*
import scalanative.unsigned.*

import microuilib.constants.*
import microuilib.functions.*
import microuilib.types.*

trait Backend {

  /** Container type of the internal backend context (e.g. to keep mutable stuff).
    *  We could have a mutable backend instead, but this is a bit more versatile.
    */
  protected type BackendContext

  /** Main loop function. This is just a glorified while loop with a thread sleep.
    *  Must call in order:
    *    - appCode
    *    - handleInput
    *    - renderCommands
    */
  def run(ctx: Ptr[mu_Context])(appCode: Zone ?=> Unit): Unit

  /** Sends the input to microui.
    */
  protected def handleInput(backCtx: BackendContext, ctx: Ptr[mu_Context])(using z: Zone): Unit

  /** Processes a `MU_COMMAND_TEXT` command */
  protected def renderText(backCtx: BackendContext, command: mu_Command): Unit

  /** Processes a `MU_COMMAND_RECT` command */
  protected def renderRect(backCtx: BackendContext, command: mu_Command): Unit

  /** Processes a `MU_COMMAND_ICON` command */
  protected def renderIcon(backCtx: BackendContext, command: mu_Command): Unit

  /** Sets the clip rectangle. Used for the `MU_COMMAND_CLIP` command */
  protected def setClipRect(backCtx: BackendContext, x: Int, y: Int, w: Int, h: Int): Unit

  /** Just go through all the commands and run the respective functions */
  protected def renderCommands(backCtx: BackendContext, ctx: Ptr[mu_Context])(implicit z: Zone): Unit = {
    // Don't forget to create a new command buffer and reset the clip
    val cmd = alloc[Ptr[mu_Command]](1)
    setClipRect(backCtx, 0, 0, Constants.screenWidth, Constants.screenHeight)
    while (mu_next_command(ctx, cmd) != 0) {
      val command = !cmd
      (!command).`type`.toUInt match {
        case MU_COMMAND_TEXT =>
          renderText(backCtx, !command)
        case MU_COMMAND_RECT =>
          renderRect(backCtx, !command)
        case MU_COMMAND_ICON =>
          renderIcon(backCtx, !command)
        case MU_COMMAND_CLIP =>
          setClipRect(
            backCtx,
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
