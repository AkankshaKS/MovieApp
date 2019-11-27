package com.e.bestmoviesapp.main.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.bestmoviesapp.R;
import com.e.bestmoviesapp.common.utils.Injection;
import com.e.bestmoviesapp.databinding.ActivityMovieDetailsBinding;
import com.e.bestmoviesapp.main.model.Cast;
import com.e.bestmoviesapp.main.model.Credits;
import com.e.bestmoviesapp.main.model.DetailedResponse;
import com.e.bestmoviesapp.main.model.Genre;
import com.e.bestmoviesapp.main.view.adapter.CastAdapter;
import com.e.bestmoviesapp.main.viewmodel.InfoViewModel;
import com.e.bestmoviesapp.common.utils.InfoViewModelFactory;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.e.bestmoviesapp.common.utils.Configuration.IMAGE_BASE_URL;
import static com.e.bestmoviesapp.common.utils.Configuration.IMAGE_FILE_SIZE;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
    private static final int DEFAULT_ID = -1 ;
    private ActivityMovieDetailsBinding activityMovieDetailsBinding;
    private InfoViewModel mViewModel;
    long movieId;
    CastAdapter castAdapter;
    private List<Cast> mCastList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        activityMovieDetailsBinding.setLifecycleOwner(this);
        movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, DEFAULT_ID);
        setupToolbar();
        setupViewModel(this, movieId);

        mCastList = new ArrayList<>();
        castAdapter = new CastAdapter(mCastList);

       LinearLayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        activityMovieDetailsBinding.movieDetailsInfo.listCast.setAdapter(castAdapter);
        activityMovieDetailsBinding.movieDetailsInfo.listCast.setLayoutManager(layoutManager);

        //castAdapter.addAll(mCastList);

    }

    private void setupViewModel(Context context, long movieId) {
        InfoViewModelFactory factory = Injection.provideViewModelFactoryDetail(context, movieId);
        mViewModel = ViewModelProviders.of(this, factory).get(InfoViewModel.class);

        mViewModel.getMovieDetails().observe(this, new Observer<DetailedResponse>() {
            @Override
            public void onChanged(@Nullable DetailedResponse movieDetails) {
                if (movieDetails != null) {
                    loadMovieDetailInfo(movieDetails);
                }
            }
        });
    }


    private void loadMovieDetailInfo(DetailedResponse movieDetails) {

        if(movieDetails.getBackdropPath() != null){
            String thumbnail = IMAGE_BASE_URL + IMAGE_FILE_SIZE + movieDetails.getBackdropPath();
            Picasso.with(getApplicationContext())
                    .load(thumbnail)
                    .into(activityMovieDetailsBinding.imageMovieBackdrop);
        }


        if(movieDetails.getPosterPath() != null){
            String small_thumbnail = IMAGE_BASE_URL + IMAGE_FILE_SIZE + movieDetails.getPosterPath();
            Picasso.with(getApplicationContext())
                    .load(small_thumbnail)
                    .into(activityMovieDetailsBinding.movieDetailsInfo.imagePoster);
        }


        if(movieDetails.getGenres() != null){
            List<Genre> genres = movieDetails.getGenres();
            List<String> genresStrList = new ArrayList<>();
            for (int i = 0; i < genres.size(); i++) {
                Genre genre = genres.get(i);
                String genreName = genre.getGenreName();
                genresStrList.add(genreName);
            }
            String genreStr = TextUtils.join(", ", genresStrList);
            activityMovieDetailsBinding.movieDetailsInfo.genre.setText(genreStr);
        }

        if(movieDetails.getVoteCount() != null){
            long voteCount = movieDetails.getVoteCount();
            activityMovieDetailsBinding.movieDetailsInfo.textVote.setText(formatNumber(voteCount));

        }

        if(movieDetails.getReleaseDate() != null){
            String releaseDate = movieDetails.getReleaseDate();
            String releaseYear = releaseDate.substring(0, 4);
            activityMovieDetailsBinding.movieDetailsInfo.textReleaseDate.setText(releaseYear);
        }

        if(movieDetails.getRuntime() != null){
            long runTime = movieDetails.getRuntime();
            activityMovieDetailsBinding.movieDetailsInfo.textMovieTiming.setText(formatNumber(runTime));

        }


        if(movieDetails.getOverview() != null){
            String synopsis = movieDetails.getOverview();
            activityMovieDetailsBinding.movieDetailsInfo.textSynopsis.setText(synopsis);
        }


        if(movieDetails.getCredits() != null){
            setupCastAdapter(movieDetails);
        }


    }

    public String formatNumber(long number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    private void setupCastAdapter(DetailedResponse cast) {

        Credits credits = cast.getCredits();
        mCastList = credits.getCast();
        credits.setCast(mCastList);

        castAdapter.addAll(mCastList);


    }

    private void setupToolbar() {
        Toolbar toolbar = activityMovieDetailsBinding.toolbar;
        //setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            handleCollapsedToolbarTitle();
        }
    }

    private void handleCollapsedToolbarTitle() {
        activityMovieDetailsBinding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    activityMovieDetailsBinding.collapsingToolbar.setTitle(
                            mViewModel.getMovieDetails().getValue().getTitle());
                    isShow = true;
                } else if (isShow) {
                    activityMovieDetailsBinding.collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }
}
