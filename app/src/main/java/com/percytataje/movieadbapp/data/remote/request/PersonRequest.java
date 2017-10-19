package com.percytataje.movieadbapp.data.remote.request;

import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.PersonModel;
import com.percytataje.movieadbapp.data.model.PersonModelKnown;
import com.percytataje.movieadbapp.data.model.holder.TrackEntityHolder;
import com.percytataje.movieadbapp.data.remote.ApiConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by junior on 27/11/16.
 */

public interface PersonRequest {

    @GET(ApiConstants.POPULAR_PERSON)
    Call<TrackEntityHolder<PersonModel>> getPersonsPopular(@Query("api_key") String apiKey,
                                                                            @Query("language") String languaje,
                                                                            @Query("page") int page);

}
