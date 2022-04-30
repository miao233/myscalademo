ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.6"

lazy val root = (project in file("."))
  .settings(
    name := "my scala demo"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.4.8",
  "org.apache.spark" % "spark-sql_2.11" % "2.4.8",
  "org.apache.spark" % "spark-mllib_2.11" % "2.4.8",
  "org.locationtech.jts" % "jts-core" % "1.18.2"
)