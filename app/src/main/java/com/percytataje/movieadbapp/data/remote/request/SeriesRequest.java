package com.percytataje.movieadbapp.data.remote.request;

import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.data.model.holder.TrackEntityHolder;
import com.percytataje.movieadbapp.data.remote.ApiConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by junior on 27/11/16.
 */

public interface SeriesRequest {


    @GET(ApiConstants.POPULAR_SERIES)
    Call<TrackEntityHolder<SerieModel>> getSeriesPopular(@Query("api_key") String apiKey,
                                                         @Query("language") String languaje,
                                                         @Query("page") int page);
    @GET(ApiConstants.SEARCH_POPULAR_SERIES)
    Call<TrackEntityHolder<SerieModel>> getSearchSeries(@Query("api_key") String apiKey,
                                                        @Query("language") String languaje,
                                                        @Query("query") String query,
                                                        @Query("page") int page );


}
