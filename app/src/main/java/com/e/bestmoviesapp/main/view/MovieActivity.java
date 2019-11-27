package com.e.bestmoviesapp.main.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import com.e.bestmoviesapp.R;
import com.e.bestmoviesapp.common.utils.Injection;
import com.e.bestmoviesapp.common.utils.ViewModelFactory;
import com.e.bestmoviesapp.databinding.ActivityMovieBinding;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.model.MoviesFilterType;
import com.e.bestmoviesapp.main.view.adapter.MovieScreenAdapter;
import com.e.bestmoviesapp.main.viewmodel.MainMovieViewModel;

public class MovieActivity extends AppCompatActivity {

    ActivityMovieBinding activityMovieBinding;
    private MainMovieViewModel mainMovieViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        mainMovieViewModel = obtainViewModel(getApplicationContext());

        initAdapter();

        if (savedInstanceState == null) {
            showNetworkText(isOnline());
        }
    }

    //view model instantiation
    public MainMovieViewModel obtainViewModel(Context context) {
        ViewModelFactory factory = Injection.provideViewModelFactory(context);
        return ViewModelProviders.of(this, factory).get(MainMovieViewModel.class);
    }

    private void initAdapter() {
        final MovieScreenAdapter movieScreenAdapter = new MovieScreenAdapter(mainMovieViewModel);
        final GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);

        activityMovieBinding.rvMovie.setAdapter(movieScreenAdapter);
        activityMovieBinding.rvMovie.setLayoutManager(layoutManager);
        mainMovieViewModel.getPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movieScreenAdapter.submitList(movies);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        if (mainMovieViewModel.getCurrentSorting() == MoviesFilterType.POPULAR) {
            menu.findItem(R.id.action_popular_movies).setChecked(true);
        } else {
            menu.findItem(R.id.action_now_playing).setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId() == R.id.menu_sort_group) {
            mainMovieViewModel.setSortMoviesBy(item.getItemId());
            item.setChecked(true);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showNetworkText(boolean online) {
            if(!isOnline()){
                activityMovieBinding.tvEmpty.setVisibility(View.VISIBLE);
                activityMovieBinding.tvEmpty.setText(getString(R.string.internet_disconnected));
            }
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
