lazy val versions = new {
  val mockito = "2.23.0"
  val scalamock = "4.1.0"
  val scalatest = "3.0.4"
}

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "org.refeed",
      scalaVersion := "2.12.4",
      version := "0.1.0-SNAPSHOT",
      scalacOptions ++= Seq(
        "-deprecation",
        "-explaintypes",
        "-feature",
        "-unchecked",
        "-Xfatal-warnings",
        "-Xlint:unsound-match",
        "-Ypartial-unification",
        "-Ywarn-dead-code",
        "-Ywarn-unused:imports",
        "-Ywarn-unused:locals"
      ),
      scalacOptions in (Compile, console) --= Seq(
        "-Ywarn-unused:imports",
        "-Xfatal-warnings"
      ),
      scalastyleConfig := file("project/scalastyle.xml")
    )
  ),
  name := "mockpost",
  libraryDependencies ++= Seq(
    "org.mockito" % "mockito-core" % versions.mockito % Test,
    "org.scalamock" %% "scalamock" % versions.scalamock % Test,
    "org.scalatest" %% "scalatest" % versions.scalatest % Test
  )
)

scalafmtOnCompile in ThisBuild := true
