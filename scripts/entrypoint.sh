#!/bin/sh

echo "The application waiting for PostgreSQL..."
while ! nc -z $DB_HOST $DB_PORT; do
  sleep 0.1
done

java -jar shortit.jar