FROM java:8-alpine
COPY ./*.jar /tmp/app.jar
ENTRYPOINT java -jar /tmp/app.jar --spring.profiles.active=pro
