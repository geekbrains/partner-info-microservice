# Use official Java 17 base image with Debian
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Spring Boot JAR file
COPY target/partner-info-microservice.jar app.jar

VOLUME ["/data", "/config"]

# Expose port 8989 (configured in app config)
EXPOSE 8989

# Run the app, output logs to console (stdout)
CMD ["java", "-jar", "app.jar"]