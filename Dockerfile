FROM openjdk:8
COPY target/gradle-component-definer.jar /gradle-component-definer.jar
CMD ["java", "-jar", "gradle-component-definer.jar"]
