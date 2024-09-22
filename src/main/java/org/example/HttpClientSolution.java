package org.example;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientSolution {
    public void run(){
        System.out.println("№4");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/headers"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                String responseBody = response.body();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> headers = objectMapper.readValue(responseBody, Map.class);

                // Вывести заголовки в виде списка значений через запятую
                String headersList = headers.values().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                System.out.println(headersList);
            } else {
                System.out.println("Не удалось получить заголовки. Код состояния: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
