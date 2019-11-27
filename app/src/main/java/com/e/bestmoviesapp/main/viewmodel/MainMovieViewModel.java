package com.e.bestmoviesapp.main.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.e.bestmoviesapp.R;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.model.MoviesFilterType;
import com.e.bestmoviesapp.main.model.PagingMovieResult;
import com.e.bestmoviesapp.main.model.Resource;
import com.e.bestmoviesapp.main.repository.MovieRepository;

public class MainMovieViewModel extends ViewModel {

    private LiveData<PagingMovieResult> pagingMovieResultLiveData;
    private LiveData<PagedList<Movie>> pagedList;
    private MutableLiveData<Integer> currentTitle = new MutableLiveData<>();
    private MutableLiveData<MoviesFilterType> sortBy = new MutableLiveData<>();


        public MainMovieViewModel(final MovieRepository movieRepository){

            sortBy.setValue(MoviesFilterType.POPULAR);
            currentTitle.setValue(R.string.popular);

            pagingMovieResultLiveData = Transformations.map(sortBy,
                    sort -> movieRepository.loadFilteredMovieResult(sort));

            pagedList = Transformations.switchMap(pagingMovieResultLiveData,
                    input -> input.data);


        }

    public LiveData<PagedList<Movie>> getPagedList() {
        return pagedList;
    }

    public MoviesFilterType getCurrentSorting() {
        return sortBy.getValue();
    }

    public LiveData<Integer> getCurrentTitle() {
        return currentTitle;
    }

    public void setSortMoviesBy(int id) {
        MoviesFilterType filterType = null;
        Integer title = null;
        switch (id) {
            case R.id.action_popular_movies: {
                if (sortBy.getValue() == MoviesFilterType.POPULAR)
                    return;

                filterType = MoviesFilterType.POPULAR;
                title = R.string.popular;
                break;
            }
            case R.id.action_now_playing: {
                if (sortBy.getValue() == MoviesFilterType.NOW_PLAYING)
                    return;

                filterType = MoviesFilterType.NOW_PLAYING;
                title = R.string.action_now_playing;
                break;
            }

            default:
                throw new IllegalArgumentException("unknown sorting id");
        }
        sortBy.setValue(filterType);
        currentTitle.setValue(title);
    }

    public void retry() {
        pagingMovieResultLiveData.getValue().moviePagedDataSourceMutableLiveData.getValue().retryCallback.invoke();
    }
}
