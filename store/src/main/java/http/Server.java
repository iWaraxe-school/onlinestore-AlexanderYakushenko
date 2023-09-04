package http;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import com.sun.net.httpserver.*;

public class Server {


        private static Map<String, Integer> cart = new HashMap<>();
        private static Map<String, Integer> products = new HashMap<>();

        public static void main(String[] args) throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            HttpContext context = server.createContext("/");
            context.setAuthenticator(new BasicAuthenticator("my-realm") {
                @Override
                public boolean checkCredentials(String user, String pwd) {
                    return user.equals("admin") && pwd.equals("password");
                }
            });

            context.setHandler(Server::handleRequest);

            server.start();
        }

        private static void handleRequest(HttpExchange exchange) throws IOException {
            String response = "";
            int statusCode = 200;

            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            try{
                if (method.equals("GET")) {
                    response = handleGetRequest(path);
                }else if (method.equals("POST")){
                    response = handlePostRequest(path);
                } else {
                    statusCode = 405; // Method not allowed
                }
            } catch (Exception e) {
                statusCode = 500; // Internal server error
                response = "An error occurred: " + e.getMessage();
            }

            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            }

    private static String handleGetRequest(String path) {
        if (path.startsWith("/products")) {
            return products.toString();
        } else if (path.startsWith("/cart")) {
            return cart.toString();
        }
        return "Resource not found";
    }

    private static String handlePostRequest(String path) {
        String[] parts = path.split("/");
        if (parts.length != 3) {
            return "Invalid request";
        }

        String action = parts[1];
        String productName = parts[2];

        if ("products".equals(action)) {
            products.put(productName, products.getOrDefault(productName, 0) + 1);
            return "Product added: " + productName;
        } else if ("cart".equals(action)) {
            cart.put(productName, cart.getOrDefault(productName, 0) + 1);
            return "Product added to cart: " + productName;
        } else {
            return "Invalid action";
        }
    }

}
