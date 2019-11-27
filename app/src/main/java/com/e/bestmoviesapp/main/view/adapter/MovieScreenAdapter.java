package com.e.bestmoviesapp.main.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.e.bestmoviesapp.R;
import com.e.bestmoviesapp.databinding.ItemMovielistBinding;
import com.e.bestmoviesapp.main.model.Movie;
import com.e.bestmoviesapp.main.view.MovieDetailsActivity;
import com.e.bestmoviesapp.main.viewmodel.MainMovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.e.bestmoviesapp.common.utils.Configuration.IMAGE_BASE_URL;
import static com.e.bestmoviesapp.common.utils.Configuration.IMAGE_FILE_SIZE;

public class MovieScreenAdapter extends PagedListAdapter<Movie, MovieScreenAdapter.MovieScreenViewHolder> {

    private MainMovieViewModel mViewModel;
    private Context context;
    private ItemMovielistBinding movielistBinding;

    public MovieScreenAdapter(MainMovieViewModel viewModel){
        super(MOVIE_COMPARATOR);
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public MovieScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        movielistBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_movielist, parent, false);
        return new MovieScreenViewHolder(movielistBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieScreenViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class MovieScreenViewHolder extends RecyclerView.ViewHolder {

        private ItemMovielistBinding mMovieItemBinding;

        public MovieScreenViewHolder(@NonNull ItemMovielistBinding mMovieItemBinding) {
            super(mMovieItemBinding.getRoot());
            this.mMovieItemBinding = mMovieItemBinding;
        }

        void bind(final Movie movie) {
            mMovieItemBinding.setMovie(movie);
            String thumbnail = IMAGE_BASE_URL + IMAGE_FILE_SIZE + movie.getPosterPath();
            Picasso.with(itemView.getContext())
                    .load(thumbnail)
                    .into(mMovieItemBinding.imageMoviePoster);
            mMovieItemBinding.textTitle.setText(movie.getTitle());

            mMovieItemBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
                    intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movie.getId());
                    intent.putExtra("movie", movie);
                    view.getContext().startActivity(intent);
                }

            });
            mMovieItemBinding.executePendingBindings();


        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    private static DiffUtil.ItemCallback<Movie> MOVIE_COMPARATOR = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };


}
