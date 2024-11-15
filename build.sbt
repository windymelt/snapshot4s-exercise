import snapshot4s.BuildInfo.snapshot4sVersion

val scala3Version = "3.5.2"
val circeVersion = "0.14.1"

lazy val root = project
  .in(file("."))
  .enablePlugins(Snapshot4sPlugin)
  .settings(
    name := "Snapshot4s exercise",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
    libraryDependencies += "org.scodec" %% "scodec-bits" % "1.2.1",
    libraryDependencies += "org.scodec" %% "scodec-core" % "2.3.2",
    libraryDependencies += "com.siriusxm" %% "snapshot4s-scalatest" % snapshot4sVersion % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
