package eu.joaocosta.twotm8native

import scala.io.Source
import scala.scalanative.libc.stdlib
import scala.scalanative.unsafe.*

object Http {
  def get(url: String): String = Zone { implicit z =>
    stdlib.system(toCString(s"curl $url > tmp.json"))
    Source.fromFile("tmp.json").getLines().mkString("\n")
  }
}
