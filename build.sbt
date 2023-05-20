name         := "twotm8-native"
organization := "eu.joaocosta"

scalaVersion := "3.2.2"

enablePlugins(ScalaNativePlugin)
libraryDependencies ++= List(
  "eu.joaocosta" %%% "minart"        % "0.5.2",
  "io.circe"     %%% "circe-core"    % "0.14.5",
  "io.circe"     %%% "circe-generic" % "0.14.5",
  "io.circe"     %%% "circe-parser"  % "0.14.5"
)

nativeLinkStubs := true
nativeMode      := "release"
nativeLTO       := "thin"
nativeGC        := "commix"
nativeConfig ~= {
  _.withEmbedResources(true)
}
