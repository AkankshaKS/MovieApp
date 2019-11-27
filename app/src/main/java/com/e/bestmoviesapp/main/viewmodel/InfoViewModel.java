
package com.e.bestmoviesapp.main.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.bestmoviesapp.main.model.DetailedResponse;
import com.e.bestmoviesapp.main.model.MovieDetails;
import com.e.bestmoviesapp.main.repository.MovieRepository;

public class InfoViewModel extends ViewModel {

    private final MovieRepository mRepository;
    private final LiveData<DetailedResponse> mMovieDetails;

    public InfoViewModel(MovieRepository repository, long movieId) {
        mRepository = repository;
        mMovieDetails = mRepository.getMovie(movieId);
    }

    public LiveData<DetailedResponse> getMovieDetails() {
        return mMovieDetails;
    }
}
