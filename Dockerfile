FROM amazoncorretto:17-alpine-jdk
MAINTAINER com.cuadantonio
EXPOSE 8080
COPY target/OneBoxCartApp-0.0.1-SNAPSHOT.jar OneBoxCartApp-0.0.1-jar

ENTRYPOINT ["java","-jar","/OneBoxCartApp-0.0.1-jar"]