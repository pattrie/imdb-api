package com.github.pattrie.imdbapi.model;

public class Movie {
  private String title;
  private String urlImage;
  private Long rating;
  private Long year;

  public Movie(String title, String urlImage, Long rating, Long year) {
    this.title = title;
    this.urlImage = urlImage;
    this.rating = rating;
    this.year = year;
  }

  @Override
  public String toString() {
    return "Movie{"
        + "title='" + title + '\''
        + ", urlImage='" + urlImage + '\''
        + ", rating=" + rating
        + ", year=" + year
        + '}';
  }
}
