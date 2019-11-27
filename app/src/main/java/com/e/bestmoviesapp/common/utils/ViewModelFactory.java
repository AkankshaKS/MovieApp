package com.e.bestmoviesapp.common.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.e.bestmoviesapp.main.repository.MovieRepository;
import com.e.bestmoviesapp.main.viewmodel.MainMovieViewModel;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;
    private long movieId;

    public static ViewModelFactory getInstance(MovieRepository repository) {
        return new ViewModelFactory(repository);
    }

    private ViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainMovieViewModel.class))
            return (T) new MainMovieViewModel(repository);
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
