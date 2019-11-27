package com.e.bestmoviesapp.main.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.e.bestmoviesapp.main.repository.paging.MoviePagedDataSource;

public class PagingMovieResult {

    public LiveData<PagedList<Movie>> data;
    public LiveData<Resource> resourceLiveData;
    public MutableLiveData<MoviePagedDataSource> moviePagedDataSourceMutableLiveData;

    public PagingMovieResult(LiveData<PagedList<Movie>> data,
                             LiveData<Resource> resourceLiveData,
                             MutableLiveData<MoviePagedDataSource> moviePagedDataSourceMutableLiveData) {
        this.data = data;
        this.resourceLiveData = resourceLiveData;
        this.moviePagedDataSourceMutableLiveData = moviePagedDataSourceMutableLiveData;
    }
}
