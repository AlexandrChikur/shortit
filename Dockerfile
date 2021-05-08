FROM openjdk:12-jdk-alpine

COPY build/libs/shortit-*.jar shortit.jar
COPY ./scripts/entrypoint.sh /scripts/entrypoint.sh
ENTRYPOINT ["/scripts/entrypoint.sh"]