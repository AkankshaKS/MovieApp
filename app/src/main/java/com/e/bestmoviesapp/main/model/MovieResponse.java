package com.e.bestmoviesapp.main.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    private int mPage;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    @SerializedName("results")
    private List<Movie> mMovieResults = null;

    public MovieResponse() {
    }

    public List<Movie> getMovieResults() {
        return mMovieResults;
    }
}
