FROM openjdk:17
COPY ./build/libs/food-log-0.0.1-SNAPSHOT.jar application.jar
ENV TZ=Asia/Seoul
EXPOSE 8080


ENTRYPOINT ["java","-jar","/application.jar"]
