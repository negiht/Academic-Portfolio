# Movies Database Project

## Overview
This project was completed during my Bachelor’s degree studies for the Database course.

The project focused on working with movie datasets, cleaning the data, creating database tables, defining relationships between tables, and writing SQL queries to analyze the data.

The project was divided into two main parts:
- Part A: database creation, data cleaning, and table relationships
- Part B: SQL queries for analysis and reporting

## Project Purpose
The purpose of this project was to practise database design, SQL, data cleaning, and relational database concepts using real movie-related datasets.

## Technologies Used
- SQL
- PostgreSQL
- Python
- draw.io
- Database relationship diagrams
- Data cleaning

## Project Files
- `dataplotter.py` — Python script used to clean and prepare dataset files
- `movies.drawio` — editable database relationship diagram
- `movies.png` — exported image of the database relationship diagram
- SQL scripts — used for creating tables, cleaning data, adding keys, and running analysis queries

## Database Tables
The database included the following main tables:

- `Movies_Metadata`
- `Credits`
- `Keywords`
- `Links`
- `Ratings`

## Part A: Database Creation and Cleaning
In Part A, I created the database tables and cleaned the data by removing duplicate and invalid records.

This part also included:
- Creating all required tables
- Removing duplicate rows
- Removing records that did not match the main movie metadata table
- Adding a primary key to `Movies_Metadata`
- Converting rating values to numeric format
- Creating foreign key relationships between tables

## Part B: SQL Analysis Queries
In Part B, I wrote SQL queries to analyze the movie dataset.

The queries included:
- Counting movies by year
- Counting movies by genre
- Counting movies by genre and year
- Calculating average rating by genre
- Finding users with the highest number of ratings
- Finding users with the highest average rating
- Creating a view for user ratings and average ratings

## Relationship Diagram
The project includes a database relationship diagram showing how the main tables connect to the `Movies_Metadata` table.

## What I Learned
- How to design relational database tables
- How to clean datasets before inserting or analyzing data
- How to use primary keys and foreign keys
- How to write SQL queries for analysis
- How to create database relationship diagrams
- How to use Python for dataset preparation

## Academic Context
This project was completed as part of my BA studies in the Database course.
