FROM azul/zulu-openjdk:11.0.12

ADD build/libs/jvm-0.0.1-SNAPSHOT.jar /app.jar

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

EXPOSE 8080

ENTRYPOINT java -jar /app.jar
