FROM openjdk:17
EXPOSE 9000
ARG JAR_FILE=target/parking-control-1.0.0.jar
ADD ${JAR_FILE} parking-control.jar
ENTRYPOINT ["java","-jar","/parking-control.jar"]