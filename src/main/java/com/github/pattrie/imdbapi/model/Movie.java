package com.github.pattrie.imdbapi.model;

import com.github.pattrie.imdbapi.Content;

public class Movie implements Content {
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

  @Override
  public String toString() {
    return "Movie{"
        + "title='" + title + '\''
        + ", urlImage='" + urlImage + '\''
        + ", rating=" + rating
        + ", year=" + year
        + '}';
  }

  @Override
  public String title() {
    return title;
  }

  @Override
  public String urlImage() {
    return urlImage;
  }

  @Override
  public Double rating() {
    return rating;
  }

  @Override
  public Long year() {
    return year;
  }
}