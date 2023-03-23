# Twotm8 Native

![Screenshot](./screenshot.png)

This repo contains an example graphical application in [Scala Native](https://scala-native.org), using [microui](https://github.com/rxi/microui).

The backend is abstracted away in a `Backend` trait (with an example `MinartBackend` powered by [minart](https://github.com/jd557/minart)), so it should be pretty simple to bring your own implementation.

The application connects to [Twotm8](https://github.com/keynmol/twotm8) and shows the posts from a few predefined profiles.

## Notes:

The HTTP client is a pretty naive implementation that assumes that you have `curl` installed and in your path (see the `Http.scala` file).
A real application would use a serious HTTP client, but since this is just a demo...

The default `MinartBackend` expects `libSDL` to be installed.

The microui bindings come from https://github.com/keynmol/microuilib-scala-native-demo
