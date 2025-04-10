# Use OpenJDK base image
FROM maven:3.8.4-openjdk-17 AS build


# Set working directory
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
# Copy JAR file
COPY --from=build /app/target/leads-backend-0.0.1-SNAPSHOT.jar .

# Expose port (Render uses 10000+)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app/leads-backend-0.0.1-SNAPSHOT.jar"]
