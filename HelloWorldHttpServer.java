import java.io.*;
import java.net.*;

public class HelloWorldHttpServer {

    public static void main(String[] args) {
        try {
            // Create a ServerSocket that listens on port 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started at http://localhost:8080");

            // Infinite loop to handle client requests
            while (true) {
                // Accept an incoming connection (blocking call)
                Socket clientSocket = serverSocket.accept();

                // Handle the request in a separate thread
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle the HTTP request
    private static void handleRequest(Socket clientSocket) {
        try {
            // Get input and output streams from the socket
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            // Read the HTTP request (just the first line for simplicity)
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String requestLine = reader.readLine();
            System.out.println("Received request: " + requestLine);

            // Write the HTTP response (Hello, World!)
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Type: text/html; charset=UTF-8\r\n" +
                                  "\r\n" +
                                  "<html><body><h1>Hello, World!</h1></body></html>";
            output.write(httpResponse.getBytes("UTF-8"));
            output.flush();

            // Close the socket connection after responding
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
