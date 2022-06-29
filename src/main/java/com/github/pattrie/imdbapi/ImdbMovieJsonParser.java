package com.github.pattrie.imdbapi;

public class ImdbMovieJsonParser {

  public ImdbMovieJsonParser(String moviesJson) {
    this.moviesJson = moviesJson;
  }

  private String moviesJson;

  public String[] getMovies() {
    moviesJson.substring(moviesJson.indexOf("[") + 1, moviesJson.indexOf("]"));
    return moviesJson.split(",");
  }

}
