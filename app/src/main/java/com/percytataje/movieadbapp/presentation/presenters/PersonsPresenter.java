package com.percytataje.movieadbapp.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.percytataje.movieadbapp.BuildConfig;
import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.data.model.PersonModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.data.model.holder.TrackEntityHolder;
import com.percytataje.movieadbapp.data.remote.ServiceFactory;
import com.percytataje.movieadbapp.data.remote.request.PersonRequest;
import com.percytataje.movieadbapp.presentation.contracts.PersonContract;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonsPresenter implements PersonContract.Presenter,CommunicatorEntityItem<PersonModel> {

    private final PersonContract.View mView;
    private boolean mFirstLoad = false;
    private Context mContext;
    private int mTotalPages;

    public PersonsPresenter(@NonNull PersonContract.View view,
                            Context context) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mContext = context;

    }

    @Override
    public void start() {
        if (!mFirstLoad) {
            loadPopularPerson(1);
            mFirstLoad = true;
        }
    }

    @Override
    public void onClick(PersonModel entity) {

    }

    @Override
    public void loadPopularPerson(final int page) {
        mView.setLoadingIndicator(true);
        final PersonRequest personRequest =
                ServiceFactory.createService(PersonRequest.class);
        String languaje = mContext.getResources().getString(R.string.languaje_api);
        Call<TrackEntityHolder<PersonModel>> call = personRequest.getPersonsPopular(BuildConfig.API_KEY, languaje,page);

        call.enqueue(new Callback<TrackEntityHolder<PersonModel>>() {
            @Override
            public void onResponse(Call<TrackEntityHolder<PersonModel>> call, Response<TrackEntityHolder<PersonModel>> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }

                    mTotalPages = response.body().getTotal_pages();

                    if (page==1){
                        mView.showPopularPerson(response.body().getResults());

                    }else{
                        mView.showMorePopularPerson(response.body().getResults());
                    }

                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.showErrorMessage("No se pudieron cargar las peliculas");
                }
            }

            @Override
            public void onFailure(Call<TrackEntityHolder<PersonModel>> call, Throwable t) {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.setLoadingIndicator(false);
                }

        });
    }
}
