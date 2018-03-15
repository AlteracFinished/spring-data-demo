FROM java:8-jre

ADD ./target/spring-demo.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/spring-demo.jar"]

EXPOSE 6000