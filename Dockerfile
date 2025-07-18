# Étape 1 : Build du projet Java avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Créer l'image exécutable
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/productmanager-be-0.0.1-SNAPSHOT.jar app.jar

# Railway utilise souvent la variable d’environnement PORT (par défaut 8080)
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
