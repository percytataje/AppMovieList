package com.percytataje.movieadbapp.data.remote;

import com.percytataje.movieadbapp.BuildConfig;

/**
 * URL BASE
 */
public class ApiConstants {

    public static final String URL_BASE_IMAGE_780 = "http://image.tmdb.org/t/p/w780";
    public static final String URL_BASE_IMAGE_185 = "http://image.tmdb.org/t/p/w185";

    public static final String POPULAR_MOVIES = "movie/popular";
    public static final String SEARCH_POPULAR_MOVIES = "search/movie";
    public static final String POPULAR_SERIES = "tv/popular";
    public static final String SEARCH_POPULAR_SERIES = "search/tv";
    public static final String POPULAR_PERSON = "person/popular";
    public static final String API_KEY = BuildConfig.API_KEY;
}
