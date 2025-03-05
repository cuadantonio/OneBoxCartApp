FROM amazoncorretto:17-alpine-jdk
MAINTAINER com.cuadantonio
EXPOSE 8080
COPY target/OneBoxCartApp-1.0.0.jar OneBoxCartApp-1.0.0-jar

ENTRYPOINT ["java","-jar","/OneBoxCartApp-1.0.0-jar"]