package com.percytataje.movieadbapp.data.remote.request;

import android.support.compat.BuildConfig;

import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.holder.TrackEntityHolder;
import com.percytataje.movieadbapp.data.remote.ApiConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by junior on 27/11/16.
 */

public interface MoviesRequest {


    @GET(ApiConstants.POPULAR_MOVIES)
    Call<TrackEntityHolder<MovieModel>> getMoviesPopular(@Query("api_key") String apiKey,
                                                         @Query("language") String languaje,
                                                         @Query("page") int page );

    @GET(ApiConstants.SEARCH_POPULAR_MOVIES)
    Call<TrackEntityHolder<MovieModel>> getSearchMovies(@Query("api_key") String apiKey,
                                                         @Query("language") String languaje,
                                                        @Query("query") String query,
                                                         @Query("page") int page );


}
