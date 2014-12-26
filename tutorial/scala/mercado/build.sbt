name := """mercado"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

EclipseKeys.withSource := true

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.squeryl" %% "squeryl" % "0.9.5-7",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)
