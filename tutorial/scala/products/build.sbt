name := """products"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

EclipseKeys.withSource := true

libraryDependencies ++= Seq(
  "net.sf.barcode4j" % "barcode4j" % "2.0" ,
  jdbc,
  "org.squeryl" %% "squeryl" % "0.9.5-7",
  anorm,
  cache,
  ws,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)


