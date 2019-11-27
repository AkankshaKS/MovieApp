package com.e.bestmoviesapp.common.utils;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.e.bestmoviesapp.main.repository.MovieRepository;
import com.e.bestmoviesapp.main.viewmodel.InfoViewModel;

public class InfoViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieRepository mRepository;
    private final long mMovieId;

    public InfoViewModelFactory(MovieRepository repository, long movieId) {
        this.mRepository = repository;
        this.mMovieId = movieId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new InfoViewModel(mRepository, mMovieId);
    }
}
