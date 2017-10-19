package com.percytataje.movieadbapp.data.model;

import com.percytataje.movieadbapp.data.remote.ApiConstants;

import java.io.Serializable;

/**
 * Created by percysoft on 02/07/17.
 */

public class SerieModel implements Serializable {

    private String original_name;
    private long id;
    private String name;
    private int vote_count;
    private double vote_average;
    private String poster_path;
    private double popularity;
    private String original_language;
    private String backdrop_path;
    private String overview;

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path780() {
        return ApiConstants.URL_BASE_IMAGE_780+ poster_path;
    }
    public String getPoster_path185() {
        return ApiConstants.URL_BASE_IMAGE_185+ poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path780() {
        return ApiConstants.URL_BASE_IMAGE_780 + backdrop_path;
    }
    public String getBackdrop_path185() {
        return ApiConstants.URL_BASE_IMAGE_185 + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
