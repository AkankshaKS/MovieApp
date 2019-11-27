package com.e.bestmoviesapp.main.repository.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.e.bestmoviesapp.common.api.WebService;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.model.MoviesFilterType;

import java.util.concurrent.Executor;

public class MovieDataSource extends DataSource.Factory<Integer, Movie> {

    public MutableLiveData<MoviePagedDataSource> sourceLiveData = new MutableLiveData<>();
    private final WebService movieService;
    private final Executor networkExecutor;
    private final MoviesFilterType sortBy;

    public MovieDataSource(WebService movieService,
                                  Executor networkExecutor, MoviesFilterType sortBy) {
        this.movieService = movieService;
        this.sortBy = sortBy;
        this.networkExecutor = networkExecutor;
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        MoviePagedDataSource movieDataSource =
                new MoviePagedDataSource(movieService, networkExecutor, sortBy);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
