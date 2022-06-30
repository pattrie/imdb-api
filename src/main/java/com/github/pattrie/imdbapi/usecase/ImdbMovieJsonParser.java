package com.github.pattrie.imdbapi.usecase;

import com.github.pattrie.imdbapi.Content;
import com.github.pattrie.imdbapi.JsonParser;
import com.github.pattrie.imdbapi.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class ImdbMovieJsonParser implements JsonParser {

  private final String moviesJson;

  public ImdbMovieJsonParser(String moviesJson) {
    this.moviesJson = moviesJson;
  }

  @Override
  public List<? extends Content> parse() {
    final String[] movies = formatMovies();

    final List<String> titles = getList(movies, "title");
    final List<String> images = getList(movies, "image");
    final List<String> ratings = getList(movies, "imDbRating\"");
    final List<String> years = getList(movies, "year");

    final List<Movie> movieList = new ArrayList<>();
    final int topMovies = 250;
    for (int i = 0; i < topMovies; i++) {
      final String title = titles.get(i);
      final String image = images.get(i);
      final String rating = ratings.get(i);
      final String year = years.get(i);
      movieList.add(new Movie(title, image, Double.parseDouble(rating), Long.parseLong(year)));
    }

    return movieList;
  }

  private String[] formatMovies() {
    moviesJson.substring(moviesJson.indexOf("[") + 1, moviesJson.indexOf("]"));
    return moviesJson.split(",");
  }

  private List<String> getList(final String[] movies, final String information) {
    final List<String> list = new ArrayList<>();

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
}
