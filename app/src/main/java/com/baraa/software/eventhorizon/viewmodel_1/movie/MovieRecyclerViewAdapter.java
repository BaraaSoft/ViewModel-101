package com.baraa.software.eventhorizon.viewmodel_1.movie;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baraa.software.eventhorizon.viewmodel_1.R;
import com.baraa.software.eventhorizon.viewmodel_1.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {
    private static final String TAG = "MovieRecyclerViewAdapte";
    public static final String baseImageUrl = "https://image.tmdb.org/t/p/w500";
    List<MovieItem> movies = new ArrayList<>();
    MovieListListener movieListListener;

    public interface MovieListListener{
        void onMovieSelected(MovieItem movieItem);
    }

    public void setMovieListListener(MovieListListener movieListListener) {
        this.movieListListener = movieListListener;
    }

    public MovieRecyclerViewAdapter(List<MovieItem> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieItem movieItem = movies.get(position);
        String imgUrl = formatImageUrlPath(movieItem.getPosterPath());
        Picasso.get().load(formatImageUrlPath(movieItem.getPosterPath())).into(holder.getImageView());
        holder.getTvStatus().setText("Available on "+movieItem.getReleaseDate());

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movieListListener != null){
                    movieItem.setPosterPath(imgUrl);
                    movieListListener.onMovieSelected(movieItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private String formatImageUrlPath(String path){
        return baseImageUrl+path;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.tvLabel) TextView tvStatus;
        View view;


        public MovieViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this,itemView);
        }

        public View getView() {
            return view;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

    }
}
