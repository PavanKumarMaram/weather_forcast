organization := "com.weather"

scalaVersion := "2.11.11"

version := "1.0-SNAPSHOT"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.11
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql_2.11
// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.11
libraryDependencies ++= Seq (
	"org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided",
	"org.apache.spark" % "spark-sql_2.11" % "2.2.0" % "provided",
	"org.scalatest" % "scalatest_2.11" % "3.2.0-SNAP7" % "test"
	)
