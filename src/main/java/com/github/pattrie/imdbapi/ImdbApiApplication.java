package com.github.pattrie.imdbapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImdbApiApplication {

	public static void main(String[] args) throws URISyntaxException {
//		SpringApplication.run(ImdbApiApplication.class, args);

		Scanner scanner = new Scanner(System.in);

    System.out.print("Insira a apiKey: " );
		String apiKey = scanner.nextLine();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey)))
				.GET()
				.build();

		HttpClient client = HttpClient.newHttpClient();

		client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept(System.out::println)
				.join();
	}
}
