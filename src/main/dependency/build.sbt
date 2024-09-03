name := "SparkKafkaStreaming"

version := "0.1"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.4.1",
  "org.apache.spark" %% "spark-sql" % "3.4.1",
  "org.apache.spark" %% "spark-streaming" % "3.4.1",
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % "3.4.1",
  "org.apache.kafka" %% "kafka" % "2.8.0",
  "org.apache.kafka" % "kafka-clients" % "2.8.0",
  "mysql" % "mysql-connector-java" % "8.0.31"
)
