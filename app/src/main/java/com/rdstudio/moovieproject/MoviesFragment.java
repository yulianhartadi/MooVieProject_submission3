package com.rdstudio.moovieproject;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rdstudio.moovieproject.adapter.MovieAdapter;
import com.rdstudio.moovieproject.items.MovieItems;
import com.rdstudio.moovieproject.model.MovieModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;

    private MovieModel movieModel;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle saveInstanceState) {

        assert saveInstanceState != null;

        return super.onGetLayoutInflater(saveInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        movieModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(MovieModel.class);
        movieModel.getMovies().observe(this, getMovie);

        // adapter
        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();

        // Recyclerview
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragment_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);

        return rootView;

    }

    private Observer<ArrayList<MovieItems>> getMovie = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                movieAdapter.setData(movieItems);
            }
        }
    };


}
