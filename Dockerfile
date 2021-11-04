FROM openjdk:11
EXPOSE 9090
ADD target/bu_dev.jar bu_dev.jar
ENTRYPOINT ["java" , "-jar", "/bu_dev.jar"]