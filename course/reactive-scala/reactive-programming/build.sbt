name := "ReactiveProgramming"

version := "1.0"

scalaVersion := "2.10.4"

EclipseKeys.withSource := true
    
libraryDependencies ++= Seq(
  "junit"             %  "junit"           % "4.11"   % "test",
  "org.scalatest"     %% "scalatest"       % "2.2.1"  % "test"
)