package com.rdstudio.moovieproject.items;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class MovieItems {

    private String movieTitle;
    private String moviePoster;
    private String movieReleaseDate;
    private String movieOverview;
    private String movieVoteAverage;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieVoteAverage() {
        return movieVoteAverage;
    }

    public void setMovieVoteAverage(String movieVoteAverage) {
        this.movieVoteAverage = movieVoteAverage;
    }

    public MovieItems(JSONObject object) {

        try {

            String movieTitle = object.getString("title");
            String moviePoster = object.getString("poster_path");
            String movieReleaseDate = object.getString("release_date");
            String movieOverview = object.getString("overview");
            double voteAverage = object.getDouble("vote_average");
            String movieVoteAverage = new DecimalFormat("##.##").format(voteAverage);

            this.movieTitle = movieTitle;
            this.moviePoster = "https://image.tmdb.org/t/p/w154/"+moviePoster;
            this.movieReleaseDate = movieReleaseDate;
            this.movieOverview = movieOverview;
            this.movieVoteAverage = movieVoteAverage;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
