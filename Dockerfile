# ===== STAGE 1: Build JAR =====
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# ===== STAGE 2: Run JAR =====
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/leads-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
