FROM openjdk:11.0.6-slim
LABEL maintainer="normandesjr@gmail.com"

ENV LANG C.UTF-8

ADD marketplace-application/target/marketplace-application*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
