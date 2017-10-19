package com.percytataje.movieadbapp.presentation.contracts;

import com.percytataje.movieadbapp.core.BasePresenter;
import com.percytataje.movieadbapp.core.BaseView;
import com.percytataje.movieadbapp.data.model.PersonModel;
import com.percytataje.movieadbapp.data.model.SerieModel;

import java.util.ArrayList;

/**
 * Especifica el contrato entre la vista y el presentador
 */
public interface PersonContract {

    interface View extends BaseView<Presenter> {

        void showPopularPerson(ArrayList<PersonModel> personModels);

        void showMorePopularPerson(ArrayList<PersonModel> personModels);

        boolean isActive();

        void showDetail(SerieModel entity);
    }

    interface Presenter extends BasePresenter {

        void loadPopularPerson(int page);
    }
}
