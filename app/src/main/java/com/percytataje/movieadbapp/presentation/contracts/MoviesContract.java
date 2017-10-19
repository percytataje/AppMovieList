package com.percytataje.movieadbapp.presentation.contracts;

import com.percytataje.movieadbapp.core.BasePresenter;
import com.percytataje.movieadbapp.core.BaseView;
import com.percytataje.movieadbapp.data.model.MovieModel;

import java.util.ArrayList;

/**
 * Especifica el contrato entre la vista y el presentador
 */
public interface MoviesContract {

    interface View extends BaseView<Presenter> {

        void showPopularMovies(ArrayList<MovieModel> movieModels);

        void showMorePopularMoviews(ArrayList<MovieModel> movieModels);

        void showSearchMovies(ArrayList<MovieModel> movieModels);

        void showMoreSearchMovies(ArrayList<MovieModel> movieModels);

        boolean isActive();

        void showDetail(MovieModel entity);
    }

    interface Presenter extends BasePresenter {


        void loadPopularMovies(int page);
        void loadSearchMovies(int page, String query);
    }
}
