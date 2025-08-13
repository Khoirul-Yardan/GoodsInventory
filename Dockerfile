# Start with a computer that has Java installed
FROM eclipse-temurin:21-jdk-alpine

# Create a folder called /app
WORKDIR /app

VOLUME /tmp
# Copy your JAR file into the container
COPY target/*.jar app.jar

# Tell Docker your app uses port 8002 (your current port)
EXPOSE 8002

# Add these for production
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8002

# When the container starts, run your app
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]
