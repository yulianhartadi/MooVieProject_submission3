package com.rdstudio.moovieproject.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.rdstudio.moovieproject.items.MovieItems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MovieModel extends ViewModel {

    private static final String API_KEY = "5441b84a29b147aebf6253a9a95f82ab";
    private MutableLiveData<ArrayList<MovieItems>> listMovies = new MutableLiveData<>();

    void setMovies() {

        //Request API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieItems> listMovieItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY + "&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listJsonArray = responseObject.getJSONArray("results");

                    for (int i = 0; i < listJsonArray.length(); i++) {
                        JSONObject movie = listJsonArray.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        listMovieItems.add(movieItems);
                    }

                    listMovies.postValue(listMovieItems);


                } catch (Exception e) {
                    Log.d("onFailure", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });

    }

    public LiveData<ArrayList<MovieItems>> getMovies() {
        return listMovies;
    }


}
