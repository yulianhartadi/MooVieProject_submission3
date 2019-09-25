package com.rdstudio.moovieproject;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.rdstudio.moovieproject.adapter.MovieAdapter;
import com.rdstudio.moovieproject.items.MovieItems;
import com.rdstudio.moovieproject.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private TextView tvErrorMessage;
    private MovieModel movieModel;
    RecyclerView recyclerView;

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

        tvErrorMessage = rootView.findViewById(R.id.tv_error_message);
        progressBar = rootView.findViewById(R.id.pb_progress);
        recyclerView = rootView.findViewById(R.id.rv_fragment_movie);

        // adapter
        movieAdapter = new MovieAdapter(listMovieItems());
        movieAdapter.notifyDataSetChanged();

        // Recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        /*recyclerView.setAdapter(movieAdapter);*/


        // Error connection;
        return rootView;

    }

    // Check for network connection

    /*private Observer<ArrayList<MovieItems>> getMovie = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                movieAdapter.setData(movieItems);
            }
        }
    };*/

    private ArrayList<MovieItems> listMovieItems() {

        AsyncHttpClient client = new AsyncHttpClient();
        String API_KEY = "5441b84a29b147aebf6253a9a95f82ab";

        final ArrayList<MovieItems> listMovieItems = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY + "&language=en-US&page=1";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        listMovieItems.add(movieItems);
                        Log.e("onSuccess: ", "Title" + movieItems.getMovieTitle());
                        if (i == list.length() - 1) {
                            recyclerView.setAdapter(movieAdapter);
                            tvErrorMessage.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }


                } catch (Exception e) {
                    Log.d("onFailure", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
        return listMovieItems;
    }


}
