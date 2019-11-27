package com.e.bestmoviesapp.main.repository;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.e.bestmoviesapp.common.utils.AppExecutors;
import com.e.bestmoviesapp.common.api.WebService;
import com.e.bestmoviesapp.main.model.DetailedResponse;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.model.MoviesFilterType;
import com.e.bestmoviesapp.main.model.PagingMovieResult;
import com.e.bestmoviesapp.main.model.Resource;
import com.e.bestmoviesapp.main.repository.paging.MovieDataSource;
import com.e.bestmoviesapp.main.repository.paging.MoviePagedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.e.bestmoviesapp.common.utils.Configuration.API_KEY;
import static com.e.bestmoviesapp.common.utils.Configuration.CREDITS;

public class MovieRepository {

    WebService webService;
    private final AppExecutors appExecutors;
    private static final int PAGE_SIZE = 20;
    private static volatile MovieRepository sInstance;


    public MovieRepository(WebService movieService,
                           AppExecutors executors) {
        webService = movieService;
        appExecutors = executors;
    }


    public LiveData<DetailedResponse> getMovie(long movieId) {
        final MutableLiveData<DetailedResponse> movieData = new MutableLiveData<>();

        webService.getMovieDetails(movieId, API_KEY, CREDITS)
                .enqueue(new Callback<DetailedResponse>() {
                    @Override
                    public void onResponse(Call<DetailedResponse> call, Response<DetailedResponse> response) {
                        if (response.isSuccessful()) {
                            DetailedResponse movie = response.body();
                            Log.i("moviedetailsucc", movie.toString());
                            movieData.setValue(movie);
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailedResponse> call, Throwable t) {
                        movieData.setValue(null);
                        Log.e(TAG, "moviedetailfail: " + t.getMessage());
                    }
                });
        return movieData;
    }


    public PagingMovieResult loadFilteredMovieResult(MoviesFilterType filterType){

        MovieDataSource sourceFactory =
                new MovieDataSource(webService, appExecutors.networkIO(), filterType);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();

        LiveData<PagedList<Movie>> moviesPagedList = new LivePagedListBuilder<>(sourceFactory, config)
                .setFetchExecutor(appExecutors.networkIO())
                .build();

        LiveData<Resource> networkState = Transformations.switchMap(sourceFactory.sourceLiveData, new Function<MoviePagedDataSource, LiveData<Resource>>() {
            @Override
            public LiveData<Resource> apply(MoviePagedDataSource input) {
                return input.networkState;
            }
        });

        return new PagingMovieResult(
                moviesPagedList,
                networkState,
                sourceFactory.sourceLiveData
        );

    }

    public static MovieRepository getInstance(WebService movieService,
                                                     AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppExecutors.class) {
                if (sInstance == null) {
                    sInstance = new MovieRepository(movieService, executors);
                }
            }
        }
        return sInstance;
    }



}
