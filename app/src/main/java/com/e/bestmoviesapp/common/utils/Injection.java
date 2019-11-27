package com.e.bestmoviesapp.common.utils;

import android.content.Context;

import com.e.bestmoviesapp.common.api.ApiClient;
import com.e.bestmoviesapp.common.api.WebService;
import com.e.bestmoviesapp.main.repository.MovieRepository;

public class Injection {

    public static MovieRepository provideMoviesRepo(Context context) {
        WebService apiService = ApiClient.getInstance();
        AppExecutors executors = AppExecutors.getInstance();
        return MovieRepository.getInstance(apiService, executors);
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        MovieRepository repository = provideMoviesRepo(context);
        return ViewModelFactory.getInstance(repository);
    }

    public static InfoViewModelFactory provideViewModelFactoryDetail(Context context, long movieid) {
        MovieRepository repository = provideMoviesRepo(context);
        return new InfoViewModelFactory(repository, movieid);
    }
}
