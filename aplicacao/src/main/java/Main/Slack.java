package Main;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Slack {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString())
        ).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: "+response.statusCode());
        System.out.println("Response: "+ response.body());
    }

}
