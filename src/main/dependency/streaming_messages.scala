import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger
import java.util.Properties

object OLTPToKafka {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("OLTPToKafka")
      .master("local[*]")
      .getOrCreate()

    // JDBC connection properties
    val jdbcHostname = "localhost"
    val jdbcPort = 3306
    val jdbcDatabase = "your_oltp_db"
    val jdbcUrl = s"jdbc:mysql://$jdbcHostname:$jdbcPort/$jdbcDatabase"

    val connectionProperties = new Properties()
    connectionProperties.put("user", "your_username")
    connectionProperties.put("password", "your_password")

    val tableName = "your_table_name"

    // Load data from OLTP table
    val oltpDF = spark.read
      .jdbc(jdbcUrl, tableName, connectionProperties)
      .select("column1", "column2") // Select the relevant columns

    // Writing to Kafka
    oltpDF
      .selectExpr("CAST(column1 AS STRING) AS key", "CAST(column2 AS STRING) AS value")
      .write
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "oltp-topic")
      .save()

    spark.stop()
  }
}
