package com.github.pattrie.imdbapi;

import com.github.pattrie.imdbapi.model.Movie;
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
    // SpringApplication.run(ImdbApiApplication.class, args);

    Scanner scanner = new Scanner(System.in);

    System.out.print("Insira a apiKey: ");
    String apiKey = scanner.nextLine();

    final URI uri = new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey));
    final HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

    final HttpClient client = HttpClient.newHttpClient();

    final HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString());

    final String[] movies = getMovies(response.body());

    final List<String> titles = getList(movies, "title");
    final List<String> images = getList(movies, "image");
    final List<String> ratings = getList(movies, "imDbRatingCount");
    final List<String> years = getList(movies, "year");

    final List<Movie> movieList = new ArrayList<>();
    final int topMovies = 250;
    for (int i = 0; i < topMovies; i++) {
      final String title = titles.get(i);
      final String image = images.get(i);
      final String rating = ratings.get(i);
      final String year = years.get(i);
      movieList.add(new Movie(title, image, Long.parseLong(rating), Long.parseLong(year)));
      System.out.println(i + 1 + " :: " + movieList.get(i));
      // Decidi utilizar o constructor com os atributos obrigatórios porque o setter permitiria
      // alterar o objeto Movie, e somente penso que deve ser alterado conforme novas
      // requisições ao endpoint
      // Não acho que precisa ser interfaceado e deve ser imutável
    }
  }

  private static List<String> getList(String[] movies, String information) {
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

  private static String[] getMovies(String moviesJson) {
    moviesJson.substring(moviesJson.indexOf("[") + 1, moviesJson.indexOf("]"));
    return moviesJson.split(",");
  }
}
