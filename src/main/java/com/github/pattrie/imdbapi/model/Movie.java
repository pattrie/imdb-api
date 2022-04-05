package com.github.pattrie.imdbapi.model;

public class Movie {
  private String title;
  private String urlImage;
  private Double rating;
  private Long year;

  public Movie(String title, String urlImage, Double rating, Long year) {
    this.title = title;
    this.urlImage = urlImage;
    this.rating = rating;
    this.year = year;
  }

  public String getTitle() {
    return title;
  }

  public String getUrlImage() {
    return urlImage;
  }

  public Double getRating() {
    return rating;
  }

  public Long getYear() {
    return year;
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