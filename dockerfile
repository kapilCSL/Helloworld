# Step 1: Build the Java application using OpenJDK
FROM openjdk:17-slim AS builder

WORKDIR /app
COPY ./app /app
RUN javac HelloWorld.java

# Step 2: Set up the web server with Ubuntu and Apache2
FROM ubuntu:20.04

# Install Apache2 and OpenJDK (Java)
RUN apt-get update && \
    apt-get install -y apache2 openjdk-17-jre && \
    apt-get clean

# Copy the compiled Java class from the builder image
COPY --from=builder /app/HelloWorld.class /var/www/html/

# Copy the HTML page to serve via Apache
COPY ./web/index.html /var/www/html/

# Expose port 80 for the Apache server
EXPOSE 80

# Command to run the Java program and Apache2 server
CMD java -cp /var/www/html HelloWorld && apachectl -D FOREGROUND
