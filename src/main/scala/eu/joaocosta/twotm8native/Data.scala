package eu.joaocosta.twotm8native

import io.circe.*
import io.circe.generic.semiauto.*

case class User(id: String, nickname: String, following: List[String], followers: List[String], twots: List[Twot])

object User {
  implicit lazy val userDecoder: Decoder[User] = deriveDecoder[User]
}

case class Twot(id: String, author: String, authorNickname: String, content: String, uwotm8Count: Int, uwotm8: Boolean)

object Twot {
  implicit lazy val twotDecoder: Decoder[Twot] = deriveDecoder[Twot]
}
