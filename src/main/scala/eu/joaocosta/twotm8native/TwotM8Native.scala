package eu.joaocosta.twotm8native

import microuilib.types.*
import microuilib.functions.*
import microuilib.constants.*
import scala.scalanative.unsafe.*
import scala.scalanative.libc.stdlib
import scalanative.unsigned.*
import scala.io.Source
import io.circe.*
import io.circe.syntax.*

object TwotM8Native {
  def getUser(user: String): User =
    val res = Http.get(s"${Constants.host}/api/thought_leaders/$user")
    parser.parse(res).flatMap(_.as[User]).toTry.get

  case class State(
      users: List[User],
      selectedUser: Option[User]
  )

  var state =
    State(Constants.defaultUsers.map(getUser), None)

  def appDefinition(ctx: Ptr[mu_Context])(using z: Zone): Unit = {
    mu_begin(ctx)
    if (mu_begin_window_ex(ctx, c"Users List", mu_rect(10, 10, 180, 300), 0) != 0) {
      if (mu_button_ex(ctx, c"Sync Users", 0, 0) != 0) {
        println("Synching...")
        state = State(Constants.defaultUsers.map(getUser), None)
      }

      mu_label(ctx, c"Users:")
      mu_layout_row(ctx, 1, Array(-1).at(0), 0)
      state.users.foreach { user =>
        if (mu_button_ex(ctx, toCString(user.nickname), 0, 0) != 0) {
          println(s"Opening ${user.nickname}...")
          state = state.copy(selectedUser = Some(user))
        }
      }
      mu_end_window(ctx)
    }
    if (mu_begin_window_ex(ctx, c"User View", mu_rect(300, 10, 330, 200), 0) != 0) {
      state.selectedUser match {
        case None =>
          mu_layout_row(ctx, 1, Array(-1).at(0), 0)
          mu_label(ctx, c"No user selected")
        case Some(user) =>
          mu_layout_row(ctx, 2, Array(120, -1).at(0), 0)
          mu_label(ctx, toCString(user.nickname))
          mu_label(ctx, toCString(s"${user.twots.size} twots"))
          if (mu_header_ex(ctx, c"Twots", 0) != 0) {
            mu_layout_row(ctx, 1, Array(-1).at(0), 0)
            user.twots.foreach { twot =>
              mu_text(ctx, toCString(twot.content))
            }
          }
      }
      mu_end_window(ctx)
    }
    mu_end(ctx)
  }

  @main def main = {
    val ctx = stdlib.malloc(sizeof[mu_Context]).asInstanceOf[Ptr[mu_Context]]
    mu_init(ctx)
    (!ctx).text_width = CFuncPtr3.fromScalaFunction { (_, str, len) =>
      val strSize = fromCString(str).size
      Constants.fontWidth * (if (len == -1) strSize else math.min(len, strSize))
    }
    (!ctx).text_height = CFuncPtr1.fromScalaFunction { _ => Constants.fontHeight }

    val frameCounter = {
      var frameNumber: Int = 0
      var timer            = System.currentTimeMillis
      () => {
        frameNumber += 1
        if (frameNumber % 10 == 0) {
          val currTime = System.currentTimeMillis()
          val fps      = 10.0 / ((currTime - timer) / 1000.0)
          println("FPS:" + fps)
          timer = System.currentTimeMillis()
        }
      }
    }

    MinartBackend.run(ctx) {
      frameCounter()
      appDefinition(ctx)
    }
  }
}
