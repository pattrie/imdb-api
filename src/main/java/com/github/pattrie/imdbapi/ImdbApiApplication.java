package com.github.pattrie.imdbapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImdbApiApplication {

  public static void main(String[] args) throws Exception {
    //SpringApplication.run(ImdbApiApplication.class, args);

    Scanner scanner = new Scanner(System.in);

    System.out.print("Insira a apiKey: ");
    String apiKey = scanner.nextLine();

    final URI uri = new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey));
    HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

    HttpClient client = HttpClient.newHttpClient();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    final String[] movies = getMovies(response.body());

    System.out.println(getList(movies, "title"));
    System.out.println(getList(movies, "image"));
    System.out.println(getList(movies, "imDbRatingCount"));
    System.out.println(getList(movies, "year"));
  }

  private static List getList(String[] movies, String information) {
    List<String> list = new ArrayList<>();

    for (String movie : movies) {
      if (movie.contains(information)) {
        final int beginIndex = movie.indexOf("\":\"") + 3;
        final int endIndex = movie.lastIndexOf("\"");
        final int finalIndex = endIndex < beginIndex ? movie.length() : endIndex;
        list.add(movie.substring(beginIndex, finalIndex));
      }
    }
    return list;
  }

  private static String[] getMovies(String response) {
    final String body = response;
    body.substring(body.indexOf("[") + 1, body.indexOf("]"));
    return body.split(",");
  }
}