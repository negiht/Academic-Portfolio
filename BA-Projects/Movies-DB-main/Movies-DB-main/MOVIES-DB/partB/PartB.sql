SELECT date_part('year', to_date(release_date, 'YYYY-MM-DD')) AS "YEAR", COUNT (id) AS "MOVIES"
FROM "Movies_Metadata"
WHERE length(release_date) >= 10
GROUP BY "YEAR"
ORDER BY "YEAR"

SELECT genres AS "GENRE", COUNT (id) AS "MOVIES"
FROM "Movies_Metadata"
GROUP BY genres
ORDER BY genres

SELECT genres AS "GENRE", date_part('year', to_date(release_date, 'YYYY-MM-DD')) AS "YEAR", COUNT (id) AS "MOVIES"
FROM "Movies_Metadata"
WHERE length(release_date) >= 10
GROUP BY "GENRE", "YEAR"
ORDER BY "GENRE", "YEAR"

SELECT genres AS "GENRE", AVG(rating) AS "AVERAGE RATING"
FROM "Movies_Metadata" INNER JOIN "Ratings" ON "Movies_Metadata".id = "Ratings".movieid
GROUP BY "GENRE"
ORDER BY "GENRE"

SELECT userid AS "USER", COUNT(rating) AS "TOTAL RATINGS"
FROM "Ratings"
GROUP BY userid
ORDER BY "TOTAL RATINGS" DESC

SELECT userid AS "USER", AVG(rating) AS "AVERAGE RATING"
FROM "Ratings"
GROUP BY userid
ORDER BY "AVERAGE RATING" DESC

CREATE VIEW USER_RATINGS_AND_AVG_RATINGS AS
SELECT userid AS "USER", count(rating) AS "NO OF RATINGS", AVG(rating) AS "AVERAGE RATING"
FROM "Ratings"
GROUP BY userid
ORDER BY "NO OF RATINGS" DESC