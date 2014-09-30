name := """akka-first-tutorial"""

version := "1.0"

scalaVersion := "2.10.3"

EclipseKeys.withSource := true

libraryDependencies ++= {
    val akkaV = "2.2.4"
    Seq(
            "com.typesafe.akka"   %%   "akka-actor"                % akkaV,
            "com.typesafe.akka"   %%   "akka-remote"                % akkaV,
            "com.typesafe.akka"   %%   "akka-testkit"              % akkaV   % "test",
            "org.specs2"          %%   "specs2"                    % "2.2.3" % "test"
        )
}
