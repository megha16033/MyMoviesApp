package com.example.megha.moviesdata2;

public class Movie {
    String movieCategory;
    String movieName;
    String movieStatus;

    public Movie(String movieName, String movieStatus, String movieCategory) {
         //Variables of Movie table
        this.movieName = movieName;
        this.movieStatus = movieStatus;
        this.movieCategory = movieCategory;
    }


    /**
     * Getter and setters for all the variables of table Movies
     * @return
     */
    public String getMovieName() {
        return this.movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieStatus() {
        return this.movieStatus;
    }

    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    public String getMovieCategory() {
        return this.movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }
}
