FROM openjdk:11.0.6-slim
LABEL maintainer="normandesjr@gmail.com"

ENV LANG C.UTF-8

ADD target/marketplace*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
