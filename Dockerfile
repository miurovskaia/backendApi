FROM amazoncorretto:17
COPY target/*.jar demo.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "demo.jar"]

