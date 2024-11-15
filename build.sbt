import snapshot4s.BuildInfo.snapshot4sVersion

val scala3Version = "3.5.2"

lazy val root = project
  .in(file("."))
  .enablePlugins(Snapshot4sPlugin)
  .settings(
    name := "Snapshot4s exercise",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "com.siriusxm" %% "snapshot4s-scalatest" % snapshot4sVersion % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
