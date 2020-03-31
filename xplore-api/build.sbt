import Dependencies.Akka._
import Dependencies.Cats._
import Dependencies.Mongo._
import Dependencies.PureConfig._
import Dependencies.Slf4j._
import Dependencies.Typesafe._
import Dependencies.Webjars._

ThisBuild / name := "xplore"
ThisBuild / scalaVersion := "2.12.8"

lazy val xplore = module(".")
  .aggregate(`xplore-domain`, `xplore-database`, `xplore-web`, `xplore-server`, `xplore-application`)
  .settings(
    stage / aggregate := false
  )

lazy val `xplore-domain` = module("domain")
  .settings(
    libraryDependencies ++= Seq(
      `cats-core`
    )
  )

lazy val `xplore-database` = module("database")
  .dependsOn(`xplore-domain`)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      `mongo-scala-driver`,
      `cats-core`
    )
  )

lazy val `xplore-web` = module("web")
  .settings(
    libraryDependencies ++= Seq(
      jquery,
      bootstrap,
      angularjs
    )
  )

lazy val `xplore-server` = module("server")
  .dependsOn(`xplore-domain`, `xplore-web`, `xplore-database`)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      `akka-http`,
      `akka-http-spray-json`,
      `akka-stream`,
      `webjars-locator`
    )
  )

lazy val `xplore-application` = module("application")
  .dependsOn(`xplore-server`, `xplore-database`)
  .enablePlugins(JavaAppPackaging)
  .settings(commonSettings)
  .settings(
    outputStrategy := Some(StdoutOutput),
    fork in run := true,
    javaOptions in run := Seq("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"),
    connectInput in run := true,
    mappings in (Compile / packageDoc) := Nil,
    libraryDependencies ++= Seq(
      typesafeConfig,
      pureconfig
    )
  )

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    `slf4j-api`,
    `slf4j-simple`
  )
)

def module(name: String) = Project(if (name == ".") "xplore" else name, file(name))
