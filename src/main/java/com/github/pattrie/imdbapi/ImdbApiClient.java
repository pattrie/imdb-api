package com.github.pattrie.imdbapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {

  public ImdbApiClient(String apiKey) {
    this.apiKey = apiKey;
  }

  private String apiKey;

  public String getBody() throws Exception {
    final URI uri = new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey));

    final HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
    final HttpClient client = HttpClient.newHttpClient();

    final HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }
}
