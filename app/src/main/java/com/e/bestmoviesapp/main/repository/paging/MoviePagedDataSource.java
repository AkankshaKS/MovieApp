package com.e.bestmoviesapp.main.repository.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import com.e.bestmoviesapp.common.api.WebService;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.model.MovieResponse;
import com.e.bestmoviesapp.main.model.MoviesFilterType;
import com.e.bestmoviesapp.main.model.Resource;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviePagedDataSource extends PageKeyedDataSource<Integer, Movie> {

    private static final int FIRST_PAGE = 1;

    public MutableLiveData<Resource> networkState = new MutableLiveData<>();

    private final WebService movieService;

    private final Executor networkExecutor;

    private final MoviesFilterType sortBy;

    public RetryCallback retryCallback = null;


    public MoviePagedDataSource(WebService movieService,
                                    Executor networkExecutor, MoviesFilterType sortBy) {
        this.movieService = movieService;
        this.networkExecutor = networkExecutor;
        this.sortBy = sortBy;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        networkState.postValue(Resource.loading(null));

        Call<MovieResponse> request;
        if (sortBy == MoviesFilterType.POPULAR) {
            request = movieService.getPopularMovies(FIRST_PAGE);
        }  else {
            request = movieService.getNowPlayingMovies(FIRST_PAGE);
        }

        try{

            Response<MovieResponse> response = request.execute();
            MovieResponse data = response.body();
            List<Movie> movieList =
                    data != null ? data.getMovieResults() : Collections.<Movie>emptyList();

            retryCallback = null;
            networkState.postValue(Resource.success(null));
            callback.onResult(movieList, null, FIRST_PAGE + 1);


        } catch (Exception e) {

            retryCallback = new RetryCallback() {
                @Override
                public void invoke() {
                        networkExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                loadInitial(params, callback);
                            }
                        });
                }
            };
            networkState.postValue(Resource.error(e.getMessage(), null));

        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {

        networkState.postValue(Resource.loading(null));
        Call<MovieResponse> request;
        if (sortBy == MoviesFilterType.POPULAR) {
            request = movieService.getPopularMovies(params.key);
        } else {
            request = movieService.getNowPlayingMovies(params.key);
        }

        request.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse data = response.body();
                    List<Movie> movieList =
                            data != null ? data.getMovieResults() : Collections.<Movie>emptyList();

                    retryCallback = null;
                    callback.onResult(movieList, params.key + 1);
                    networkState.postValue(Resource.success(null));
                } else {
                    retryCallback = new RetryCallback() {
                        @Override
                        public void invoke() {
                            loadAfter(params, callback);
                        }
                    };
                    networkState.postValue(Resource.error("error code: " + response.code(), null));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                retryCallback = new RetryCallback() {
                    @Override
                    public void invoke() {
                        networkExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                loadAfter(params, callback);
                            }
                        });
                    }
                };
                networkState.postValue(Resource.error(t != null ? t.getMessage() : "unknown error", null));
            }
        });

    }

    public interface RetryCallback {
        void invoke();
    }
}
