# Use the official OpenJDK image as the base image
FROM eclipse-temurin:17 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle configuration files into the container
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

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
