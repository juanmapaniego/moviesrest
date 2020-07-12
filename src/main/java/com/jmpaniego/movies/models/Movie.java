package com.jmpaniego.movies.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Movie implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private Integer year;
  private Double rate;
  private Integer runtime;
  private String director;
  private String actors;
  @Column(columnDefinition = "TEXT")
  private String plot;
  private String posterUrl;

  @ManyToMany
  @JoinTable(
      name = "movies_genere",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "genere_id")
  )
  Set<Genere> genres;

  public Movie() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Integer getRuntime() {
    return runtime;
  }

  public void setRuntime(Integer runtime) {
    this.runtime = runtime;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(String actors) {
    this.actors = actors;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }

  public String getPosterUrl() {
    return posterUrl;
  }

  public void setPosterUrl(String posteUrl) {
    this.posterUrl = posteUrl;
  }

  public Set<Genere> getGenres() {
    return genres;
  }

  public void setGenres(Set<Genere> genres) {
    this.genres = genres;
  }

  private static final long serialVersionUID = 7526472295622776147L;
}
