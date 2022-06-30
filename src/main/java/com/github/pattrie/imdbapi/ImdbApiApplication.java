package com.github.pattrie.imdbapi;

import com.github.pattrie.imdbapi.client.ImdbApiClient;
import com.github.pattrie.imdbapi.html.HTMLGenerator;
import com.github.pattrie.imdbapi.usecase.ImdbMovieJsonParser;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImdbApiApplication {

  public static void main(String[] args) throws Exception {
    // SpringApplication.run(ImdbApiApplication.class, args);

    Scanner scanner = new Scanner(System.in);

    System.out.print("Insira a apiKey: ");
    final String apiKey = scanner.nextLine();

    final String moviesJson = new ImdbApiClient(apiKey).getBody();

    final List<? extends Content> movies = new ImdbMovieJsonParser(moviesJson).parse();

    new HTMLGenerator(new PrintWriter("src/main/resources/movies.html")).generate(movies);
  }
}
