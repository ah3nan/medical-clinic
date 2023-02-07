FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8081
ADD target/medicalclinic-app.jar medicalclinic-app.jar
ENTRYPOINT ["java","-jar", "/medicalclinic-app.jar"]