FROM openjdk:17-oracle
COPY target/*.jar demo.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "demo.jar"]

