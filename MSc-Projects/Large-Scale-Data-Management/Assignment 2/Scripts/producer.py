import csv
import json
import asyncio
import random
from datetime import datetime, timezone

from aiokafka import AIOKafkaProducer
from faker import Faker

TOPIC = "spotify_songs"
BOOTSTRAP_SERVERS = "localhost:29092"
MY_NAME = "NEGI HOXHA"
MIN_DELAY_SECONDS = 5
MAX_DELAY_SECONDS = 15

fake = Faker()


def serializer(value):
    return json.dumps(value).encode("utf-8")


def load_song_names(csv_path="spotify-songs.csv"):
    songs = []
    with open(csv_path, "r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            song_name = row["name"].strip()
            if song_name:
                songs.append(song_name)
    return songs


async def produce():
    producer = AIOKafkaProducer(
        bootstrap_servers=BOOTSTRAP_SERVERS,
        value_serializer=serializer,
        compression_type="gzip"
    )

    songs = load_song_names()

    people = [fake.name() for _ in range(10)]
    people.append(MY_NAME)

    await producer.start()
    try:
        while True:
            for person in people:
                song = random.choice(songs)
                data = {
                    "person_name": person,
                    "song_name": song,
                    "listened_at": datetime.now(timezone.utc).isoformat()
                }
                await producer.send_and_wait(TOPIC, data)
                print(data)
            await asyncio.sleep(random.randint(MIN_DELAY_SECONDS, MAX_DELAY_SECONDS))
    finally:
        await producer.stop()


if __name__ == "__main__":
    asyncio.run(produce())
