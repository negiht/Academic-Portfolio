-------------------------------CREATING ALL TABLES---------------------------------

create table "Credits"(
   cast text,
   crew text,
   id int
);

create table "Keywords"(
   id int,
   keywords text
);

create table "Links"(
   movieId int,
   imdbId int,
   tmdbId int
);

create table "Movies_Metadata"(
   adult varchar(100),
   belongs_to_collection varchar(190),
   budget int,
   genres varchar(270),
   homepage varchar(250),
   id int,
   imdb_id varchar(100),
   original_language varchar(100),
   original_title varchar(110),
   overview varchar(2000),
   popularity varchar(100),
   poster_path varchar(40),
   production_companies varchar(1260),
   production_countries varchar(1040),
   release_date varchar(100),
   revenue varchar(100),
   runtime varchar(100),
   spoken_languages varchar(770),
   status varchar(20),
   tagline varchar(300),
   title varchar(110),
   video varchar(100),
   vote_average varchar(100),
   vote_count int
);

create table "Ratings"(
   userId int,
   movieId int,
   rating varchar(10),
   timestamp int
);

-----------------------------------UPDATES--------------------------------------

DELETE 
FROM "Credits"
WHERE id NOT IN
(
	SELECT MIN(id)
	FROM "Credits"
	GROUP BY "cast", crew
)

DELETE 
FROM "Links"
WHERE movieid NOT IN
(
	SELECT MIN(movieid)
	FROM "Links"
	GROUP BY imdbid, tmdbid
)

DELETE 
FROM "Keywords"
WHERE id NOT IN
(
	SELECT MIN(id)
	FROM "Keywords"
	GROUP BY keywords
)


DELETE 
FROM "Movies_Metadata"
WHERE id IN
(
	SELECT min(id)
	FROM "Movies_Metadata"
	GROUP BY id
	HAVING COUNT(id) > 1
)

DELETE 
FROM "Credits"
WHERE id NOT IN
(
	SELECT id
	FROM "Movies_Metadata"
)

DELETE 
FROM "Keywords"
WHERE id NOT IN
(
	SELECT id
	FROM "Movies_Metadata"
)

DELETE 
FROM "Links"
WHERE movieid NOT IN
(
	SELECT id
	FROM "Movies_Metadata"
)

DELETE 
FROM "Ratings"
WHERE movieid NOT IN
(
	SELECT id
	FROM "Movies_Metadata"
)

ALTER TABLE "Movies_Metadata"
ADD PRIMARY KEY (id);

ALTER TABLE "Ratings"
ALTER COLUMN rating TYPE numeric(5,2) USING rating::numeric(5,2) 

ALTER TABLE "Credits" 
ADD CONSTRAINT FK_Movies_Metadata FOREIGN KEY (id) REFERENCES "Movies_Metadata" (id);

ALTER TABLE "Keywords" 
ADD CONSTRAINT FKK_Movies_Metadata FOREIGN KEY(id) REFERENCES "Movies_Metadata" (id);

ALTER TABLE "Links" 
ADD CONSTRAINT FK_Links_Movies_Metadata FOREIGN KEY (movieid) REFERENCES "Movies_Metadata" (id);

ALTER TABLE "Ratings" 
ADD CONSTRAINT FK_Ratings_Movies_Metadata FOREIGN KEY (movieid) REFERENCES "Movies_Metadata" (id);

