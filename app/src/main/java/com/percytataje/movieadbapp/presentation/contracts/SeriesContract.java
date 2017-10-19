package com.percytataje.movieadbapp.presentation.contracts;

import com.percytataje.movieadbapp.core.BasePresenter;
import com.percytataje.movieadbapp.core.BaseView;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.SerieModel;

import java.util.ArrayList;

/**
 * Especifica el contrato entre la vista y el presentador
 */
public interface SeriesContract {

    interface View extends BaseView<Presenter> {

        void showPopularSeries(ArrayList<SerieModel> serieModels);

        void showMorePopularSeriews(ArrayList<SerieModel> serieModels);
        void showSearchSeries(ArrayList<SerieModel> serieModels);

        void showMoreSearchSeries(ArrayList<SerieModel> serieModels);

        boolean isActive();

        void showDetail(SerieModel entity);
    }

    interface Presenter extends BasePresenter {


        void loadPopularSeries(int page);
        void loadSearchSeries(int page, String query);
    }
}
