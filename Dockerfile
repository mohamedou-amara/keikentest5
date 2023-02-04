# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /keikentest5

# Copy the jar file and the entrypoint script
COPY target/spring-mongodb-app.jar spring-mongodb-app.jar
RUN chmod -R 777 spring-mongodb-app.jar

# Expose the port the application runs on
EXPOSE 9002

# Set environment variable for mongodb
ENV MONGO_HOST=keikentestdb
ENV MONGO_PORT=27017
ENV MONGO_DB=ChatHistory

# Run the entrypoint script
ENTRYPOINT ["java", "-jar", "/keikentest5/spring-mongodb-app.jar"]