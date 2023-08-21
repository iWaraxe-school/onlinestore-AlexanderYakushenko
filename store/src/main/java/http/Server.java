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

            if (method.equals("GET")) {
                if (path.startsWith("/products")) {
                    response = products.toString();
                } else if (path.startsWith("/cart")) {
                    response = cart.toString();
                }
            } else if (method.equals("POST")) {
                if (path.startsWith("/products")) {
                    String[] parts = path.split("/");
                    if (parts.length == 3) {
                        String productName = parts[2];
                        products.put(productName, products.getOrDefault(productName, 0) + 1);
                        response = "Product added: " + productName;
                    }
                } else if (path.startsWith("/cart")) {
                    String[] parts = path.split("/");
                    if (parts.length == 3) {
                        String productName = parts[2];
                        cart.put(productName, cart.getOrDefault(productName, 0) + 1);
                        response = "Product added to cart: " + productName;
                    }
                }
            } else {
                statusCode = 405; // Method not allowed
            }

            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }


}
