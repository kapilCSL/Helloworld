# Step 1: Use the official OpenJDK base image
FROM openjdk:17-slim

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the Java source code into the container
COPY HelloWorldHttpServer.java .

# Step 4: Compile the Java program
RUN javac HelloWorldHttpServer.java

# Step 5: Expose port 8045 to access the server
EXPOSE 8045

# Step 6: Run the server when the container starts
CMD ["java", "HelloWorldHttpServer"]
