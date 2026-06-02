# Assignment 1: Hadoop MapReduce

## Overview
This assignment was completed for the Large Scale Data Management subject during my MSc studies.

The assignment focused on using Hadoop, HDFS, Docker, and MapReduce to process large-scale text and CSV data.

The project was divided into two parts:
- Part 1: WordCount MapReduce job using the novel Dracula
- Part 2: Spotify danceability analysis using a custom MapReduce program

## Project Purpose
The purpose of this assignment was to practise distributed data processing using Hadoop MapReduce and understand how large datasets can be stored and processed using HDFS.

## Technologies Used
- Java
- Hadoop
- HDFS
- MapReduce
- Docker
- Maven
- CSV data processing
- Distributed systems concepts

## Project Files
- `Large_Scale_Data_Management_Assignment_1_Report.pdf` — final assignment report
- `DriverSpotify.java` — Hadoop driver class for the Spotify MapReduce job
- `SpotifyDanceability.java` — Mapper, Combiner, Reducer, and custom Writable logic for Spotify analysis
- `Dracula.txt` — input text file used for WordCount
- `universal_top_spotify_songs.csv` — Spotify dataset used for Part 2
- `DraculaWordCountOutput.txt` — output from the Dracula WordCount job
- `SpotifyPart2Output.txt` — output from the Spotify danceability job
- `DraculaWordCountTerminalLog.txt` — terminal execution log for Part 1
- `SpotifyPart2TerminalLog.txt` — terminal execution log for Part 2
- `README.md` — project documentation

## Part 1: Dracula WordCount
The first part used the text of the Gothic horror novel *Dracula* as input data.

The process included:
- Downloading the text file from Project Gutenberg
- Copying the file into the Hadoop namenode Docker container
- Uploading the file into HDFS
- Modifying the Java driver to use the Dracula text file
- Rebuilding the project using Maven
- Running the WordCount MapReduce job
- Saving the execution log and output results

## Part 2: Spotify Danceability Analysis
The second part used a Spotify dataset and a custom MapReduce job.

For each `country:YYYY-MM` key, the program calculated:
- The most danceable song
- The maximum danceability value
- The average danceability value

The Java implementation included:
- A Mapper to parse CSV rows and emit country-month keys
- A CSV-safe split to handle commas inside quoted values
- A custom Writable for partial aggregation
- A Combiner to reduce intermediate data
- A Reducer to calculate the final maximum and average danceability

## What I Learned
- How to work with HDFS
- How to run Hadoop inside Docker containers
- How MapReduce jobs are structured
- How to write Mapper and Reducer logic in Java
- How to process text data with WordCount
- How to process CSV data using MapReduce
- How to use Maven to build Hadoop Java projects
- How distributed processing handles large-scale data

## Academic Context
This project was completed as part of my MSc studies in the Large Scale Data Management subject.
