from pyspark.sql import SparkSession
from pyspark.sql.types import StructType, StructField, StringType
from pyspark.sql.functions import (
    from_json, col, to_timestamp, date_format
)

TOPIC = "spotify_songs"
BOOTSTRAP_SERVERS = "localhost:29092"
CSV_PATH = "/vagrant/spotify-songs.csv"
CHECKPOINT_DIR = "/tmp/spotify_checkpoint"
TRIGGER_SECONDS = 30

schema = StructType([
    StructField("person_name", StringType(), False),
    StructField("song_name", StringType(), False),
    StructField("listened_at", StringType(), False),
])

spark = (
    SparkSession.builder
    .appName("SpotifySongStreamingToCassandra")
    .config(
        "spark.jars.packages",
        ",".join([
            "org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.0",
            "com.datastax.spark:spark-cassandra-connector_2.12:3.5.0"
        ])
    )
    .config("spark.cassandra.connection.host", "localhost")
    .getOrCreate()
)

spark.sparkContext.setLogLevel("WARN")

songs_df = (
    spark.read
    .option("header", True)
    .option("inferSchema", True)
    .csv(CSV_PATH)
    .select(
        col("name").alias("csv_song_name"),
        col("artists"),
        col("duration_ms"),
        col("album_name"),
        col("album_release_date"),
        col("danceability"),
        col("energy"),
        col("key"),
        col("loudness"),
        col("mode"),
        col("speechiness"),
        col("acousticness"),
        col("instrumentalness"),
        col("liveness"),
        col("valence"),
        col("tempo")
    )
    .dropDuplicates(["csv_song_name"])
)

songs_df.cache()
songs_df.count()

kafka_df = (
    spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", BOOTSTRAP_SERVERS)
    .option("subscribe", TOPIC)
    .option("startingOffsets", "latest")
    .load()
)

parsed_df = (
    kafka_df
    .selectExpr("CAST(value AS STRING) AS json_value")
    .select(from_json(col("json_value"), schema).alias("data"))
    .select("data.*")
    .withColumn("listened_at_ts", to_timestamp(col("listened_at")))
    .withColumn("listen_hour", date_format(col("listened_at_ts"), "yyyy-MM-dd HH:00"))
)

enriched_df = (
    parsed_df.join(
        songs_df,
        parsed_df.song_name == songs_df.csv_song_name,
        "inner"
    )
    .select(
        col("person_name"),
        col("listen_hour"),
        col("listened_at_ts").alias("listened_at"),
        col("song_name"),
        col("artists"),
        col("duration_ms"),
        col("album_name"),
        col("album_release_date"),
        col("danceability"),
        col("energy"),
        col("key"),
        col("loudness"),
        col("mode"),
        col("speechiness"),
        col("acousticness"),
        col("instrumentalness"),
        col("liveness"),
        col("valence"),
        col("tempo")
    )
)

def write_to_cassandra(batch_df, batch_id):
    if batch_df.rdd.isEmpty():
        return

    (
        batch_df.write
        .format("org.apache.spark.sql.cassandra")
        .mode("append")
        .options(table="person_hour_song_history", keyspace="spotify")
        .save()
    )

query = (
    enriched_df.writeStream
    .foreachBatch(write_to_cassandra)
    .outputMode("append")
    .option("checkpointLocation", CHECKPOINT_DIR)
    .trigger(processingTime=f"{TRIGGER_SECONDS} seconds")
    .start()
)

query.awaitTermination()
