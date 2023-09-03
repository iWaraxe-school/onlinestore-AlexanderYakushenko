package http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
public class Client {

        private static final String BASE_URL = "http://localhost:8080";

        public static void main(String[] args) {
            String username = "admin";
            String password = "password";

            String auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            // Add a product to the cart
            try {
            String productToAdd = "example-product";
            addToCart(productToAdd, auth);

            // Retrieve products and cart contents
            String productsResponse = sendRequest(BASE_URL + "/products", auth);
            String cartResponse = sendRequest(BASE_URL + "/cart", auth);
            System.out.println("Products: " + productsResponse);
            System.out.println("Cart: " + cartResponse);
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

        private static void addToCart(String product, String auth) throws IOException {
            String url = BASE_URL + "/cart/" + product;
            sendPostRequest(url, auth);
        }

    private static HttpURLConnection establishConnection(String urlString, String auth, String method) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Authorization", "Basic " + auth);
        return connection;
    }



        private static void sendPostRequest(String urlString, String auth) throws IOException {
            try {
            HttpURLConnection connection = establishConnection(urlString, auth, "POST");
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            connection.disconnect();
            } catch (IOException e) {
                System.err.println("An error occurred while sending a POST request: " + e.getMessage());
            }
        }

        private static String sendRequest(String urlString, String auth) throws IOException {
            try{
            HttpURLConnection connection = establishConnection(urlString, auth, "GET");
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
        System.err.println("An error occurred while sending a GET request: " + e.getMessage());
        return ""; // Return an empty response in case of error
    }
    }


}
