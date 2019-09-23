package com.rdstudio.moovieproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rdstudio.moovieproject.R;
import com.rdstudio.moovieproject.items.MovieItems;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItems> mData = new ArrayList<>();

    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        holder.bind(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPosterItems;
        TextView tvTitleItems, tvReleaseItems, tvOverviewItems, tvVoteAverage, tvMoreInfo;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPosterItems = itemView.findViewById(R.id.iv_poster_items);
            tvTitleItems = itemView.findViewById(R.id.tv_title_items);
            tvReleaseItems = itemView.findViewById(R.id.tv_release_date_items);
            tvOverviewItems = itemView.findViewById(R.id.tv_overview_items);
            tvVoteAverage = itemView.findViewById(R.id.tv_vote_average);
            tvMoreInfo = itemView.findViewById(R.id.tv_more_info);
        }

        void bind(final MovieItems movieItems) {
            tvTitleItems.setText(movieItems.getMovieTitle());
            tvReleaseItems.setText(movieItems.getMovieReleaseDate());
            tvOverviewItems.setText(movieItems.getMovieOverview());
            tvVoteAverage.setText(movieItems.getMovieVoteAverage());

            Glide.with(itemView.getContext())
                    .load(movieItems.getMoviePoster())
                    .apply(new RequestOptions().override(110, 160))
                    .into(ivPosterItems);


        }
    }

}
