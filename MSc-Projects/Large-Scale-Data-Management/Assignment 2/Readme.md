# Assignment 2: Kafka, Spark Streaming and Cassandra Pipeline

## Overview
This assignment was completed for the Large Scale Data Management subject during my MSc studies.

The assignment focused on building a streaming data pipeline using Apache Kafka, Apache Spark Structured Streaming, and Apache Cassandra.

The goal of the project was to simulate a stream of Spotify listening events, process and enrich the stream using Spark, and persist the final records into Cassandra.

## Project Purpose
The purpose of this assignment was to practise large-scale streaming data management and understand how modern distributed systems work together in a data pipeline.

## Technologies Used
- Python
- Apache Kafka
- Apache Spark Structured Streaming
- Apache Cassandra
- PySpark
- CQL
- Docker / local distributed environment
- CSV data processing
- Streaming data processing

## Project Files
- `Report.ipynb` — notebook version of the assignment report
- `Report.pdf` — exported assignment report
- `producer.py` — Kafka producer that simulates Spotify listening events
- `spark_stream_to_cassandra.py` — Spark Structured Streaming consumer that enriches and writes data to Cassandra
- `spotify-songs.csv` — Spotify songs dataset used for enrichment
- `Cassandra persisted lines sample.txt` — sample persisted rows from Cassandra
- `CQL query results.txt` — Cassandra query results
- `README.md` — project documentation

## Part 1: Kafka Producer
The first part created a Python Kafka producer that simulates song listening events.

The producer:
- Reads song names from `spotify-songs.csv`
- Generates fake users using Faker
- Adds my name to the generated users
- Creates listening events with `person_name`, `song_name`, and `listened_at`
- Sends the events to the Kafka topic `spotify_songs`

## Part 2: Spark Streaming Consumer
The second part created a PySpark Structured Streaming script.

The Spark script:
- Reads streaming messages from Kafka
- Parses JSON event data
- Converts timestamps
- Creates an hourly listening key
- Reads the static Spotify CSV dataset
- Joins streaming events with song metadata
- Enriches events with audio features such as danceability, energy, loudness, valence, and tempo
- Writes the enriched records to Cassandra

## Cassandra Data Model
The Cassandra table was designed to store listening history by person and hour.

The table supports queries such as:
- Songs listened to by a person during a specific hour
- Average danceability for a person during a specific hour

The partition key is based on `person_name` and `listen_hour`, while `listened_at` and `song_name` are used for ordering and uniqueness.

## Querying Cassandra
The assignment included CQL queries to check the stored data.

Example queries included:
- Calculating average danceability for a specific person and hour
- Listing songs listened to by a person during a specific hour

## What I Learned
- How to simulate streaming data with Kafka
- How to build a Kafka producer in Python
- How Spark Structured Streaming consumes Kafka messages
- How to enrich streaming data with a static dataset
- How to write streaming data into Cassandra
- How to design a Cassandra table for query-based access patterns
- How to query Cassandra using CQL
- How Kafka, Spark, and Cassandra work together in a large-scale data pipeline

## Academic Context
This project was completed as part of my MSc studies in the Large Scale Data Management subject.
