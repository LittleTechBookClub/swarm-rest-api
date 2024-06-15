# Use the official OpenJDK image as the base image
FROM eclipse-temurin:17 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle configuration files into the container
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Ensure the gradlew script uses Unix line endings and is executable
RUN apt-get update && apt-get install -y dos2unix && dos2unix gradlew
RUN chmod +x gradlew

# Copy the source code into the container
COPY src src

# Build the application
RUN ./gradlew build -x test

# Start a new build stage
FROM eclipse-temurin:17

# Set the working directory in this new stage
WORKDIR /app

# Copy the built JAR from the previous stage into this new stage
COPY --from=build /app/build/libs/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
