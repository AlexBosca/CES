FROM java:8
EXPOSE 8080
ADD target/gradle-component-definer.jar /gradle-component-definer.jar
RUN sh -c 'touch /gradle-component-definer.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gradle-component-definer.jar C:\Users\borozani\Desktop\CES\src\main\java\result.json C:\Users\borozani\Desktop\CES\src\main\java\input.txt"]
