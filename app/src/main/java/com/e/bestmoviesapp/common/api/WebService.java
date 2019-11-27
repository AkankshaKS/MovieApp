package com.e.bestmoviesapp.common.api;

import androidx.lifecycle.LiveData;

import com.e.bestmoviesapp.main.model.Credits;
import com.e.bestmoviesapp.main.model.DetailedResponse;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.model.MovieDetails;
import com.e.bestmoviesapp.main.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("page") int page);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/{id}")
    Call<DetailedResponse> getMovieDetails(@Path("id") long id,
    @Query("api_key") String apiKey,@Query("append_to_response") String credits);

    /*@GET("movie/{id}/credits")
    Call<Credits> getMovieCredits(@Path("id") long id);
*/
}
