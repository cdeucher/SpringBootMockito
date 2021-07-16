#docker build -t springboot:01 .
#docker tag  springboot:01 ryanface/springboot:01
#docker push ryanface/springboot:01
FROM openjdk:11.0.11-jdk-oracle

COPY target/* /opt/

WORKDIR /opt

CMD ["java", "-Dspring.profiles.active=production", "-jar", "SlackBot-0.0.1-SNAPSHOT.jar"]
