package com.percytataje.movieadbapp.data.model;

import com.percytataje.movieadbapp.data.remote.ApiConstants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by percysoft on 02/07/17.
 */

public class PersonModel implements Serializable{

    private  double popularity;
    private long id;
    private  String profile_path;
    private String name;


    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfile_path780() {
        return ApiConstants.URL_BASE_IMAGE_780+ profile_path;

    }
    public String getProfile_path185() {
        return ApiConstants.URL_BASE_IMAGE_185+ profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
