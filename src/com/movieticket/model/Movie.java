package com.movieticket.model;

import com.movieticket.enums.Genre;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// # Class: Movie
// # Purpose: Handles movie logic in this module.
public class Movie {
    private final String id;
    private final String title;
    private final String language;
    private final Genre genre;
    private final int durationInMinutes;
    private final double rating;
    private final LocalDateTime releaseDate;
    private final String director;

// # Method: Movie
// # Purpose: Executes the Movie workflow for this class.
    private Movie(MovieBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.language = builder.language;
        this.genre = builder.genre;
        this.durationInMinutes = builder.durationInMinutes;
        this.rating = builder.rating;
        this.releaseDate = builder.releaseDate;
        this.director = builder.director;
    }

// # Method: getId
// # Purpose: Executes the getId workflow for this class.
    public String getId() { return id; }
// # Method: getTitle
// # Purpose: Executes the getTitle workflow for this class.
    public String getTitle() { return title; }
// # Method: getLanguage
// # Purpose: Executes the getLanguage workflow for this class.
    public String getLanguage() { return language; }
// # Method: getGenre
// # Purpose: Executes the getGenre workflow for this class.
    public Genre getGenre() { return genre; }
// # Method: getRating
// # Purpose: Executes the getRating workflow for this class.
    public double getRating() { return rating; }

    public static class MovieBuilder {
        private final String id;
        private final String title;
        private final String language;
        private final Genre genre;
        private int durationInMinutes = 120;
        private double rating = 0.0;
        private LocalDateTime releaseDate = LocalDateTime.now();
        private String director = "Unknown";

// # Method: MovieBuilder
// # Purpose: Executes the MovieBuilder workflow for this class.
        public MovieBuilder(String id, String title, String language, Genre genre) {
            this.id = id;
            this.title = title;
            this.language = language;
            this.genre = genre;
        }

// # Method: duration
// # Purpose: Executes the duration workflow for this class.
        public MovieBuilder duration(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
            return this;
        }

// # Method: rating
// # Purpose: Executes the rating workflow for this class.
        public MovieBuilder rating(double rating) {
            this.rating = rating;
            return this;
        }

// # Method: releaseDate
// # Purpose: Executes the releaseDate workflow for this class.
        public MovieBuilder releaseDate(LocalDateTime releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

// # Method: director
// # Purpose: Executes the director workflow for this class.
        public MovieBuilder director(String director) {
            this.director = director;
            return this;
        }

// # Method: build
// # Purpose: Executes the build workflow for this class.
        public Movie build() {
            return new Movie(this);
        }
    }
}