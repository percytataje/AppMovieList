package com.percytataje.movieadbapp.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.percytataje.movieadbapp.BuildConfig;
import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.data.model.holder.TrackEntityHolder;
import com.percytataje.movieadbapp.data.remote.ServiceFactory;
import com.percytataje.movieadbapp.data.remote.request.MoviesRequest;
import com.percytataje.movieadbapp.data.remote.request.SeriesRequest;
import com.percytataje.movieadbapp.presentation.contracts.SeriesContract;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeriesPresenter implements SeriesContract.Presenter,CommunicatorEntityItem<SerieModel> {

    private final SeriesContract.View mView;
    private boolean mFirstLoad = false;
    private Context mContext;
    private int mTotalPages;


    public SeriesPresenter(@NonNull SeriesContract.View view,
                           Context context) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mContext = context;

    }

    @Override
    public void start() {
        if (!mFirstLoad) {
            loadPopularSeries(1);
            mFirstLoad = true;
        }
    }


    @Override
    public void loadPopularSeries(final int page) {
        mView.setLoadingIndicator(true);
        final SeriesRequest seriesRequest =
                ServiceFactory.createService(SeriesRequest.class);
        String languaje = mContext.getResources().getString(R.string.languaje_api);
        Call<TrackEntityHolder<SerieModel>> call = seriesRequest.
                getSeriesPopular(BuildConfig.API_KEY, languaje,page);

        call.enqueue(new Callback<TrackEntityHolder<SerieModel>>() {
            @Override
            public void onResponse(Call<TrackEntityHolder<SerieModel>> call, Response<TrackEntityHolder<SerieModel>> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }

                    mTotalPages = response.body().getTotal_pages();

                    if (page==1){
                        mView.showPopularSeries(response.body().getResults());

                    }else{
                        mView.showMorePopularSeriews(response.body().getResults());
                    }

                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.showErrorMessage("No se pudieron cargar las peliculas");
                }
            }

            @Override
            public void onFailure(Call<TrackEntityHolder<SerieModel>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void loadSearchSeries(final int page, String query) {
        mView.setLoadingIndicator(true);
        final SeriesRequest seriesRequest =
                ServiceFactory.createService(SeriesRequest.class);
        String languaje = mContext.getResources().getString(R.string.languaje_api);
        Call<TrackEntityHolder<SerieModel>> call = seriesRequest.
                getSearchSeries(BuildConfig.API_KEY, languaje,query,page);

        call.enqueue(new Callback<TrackEntityHolder<SerieModel>>() {
            @Override
            public void onResponse(Call<TrackEntityHolder<SerieModel>> call, Response<TrackEntityHolder<SerieModel>> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }

                    mTotalPages = response.body().getTotal_pages();

                    if (page==1){
                        mView.showSearchSeries(response.body().getResults());

                    }else{
                        mView.showMoreSearchSeries(response.body().getResults());
                    }

                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.showErrorMessage("No se pudieron cargar las peliculas");
                }
            }



            @Override
            public void onFailure(Call<TrackEntityHolder<SerieModel>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void onClick(SerieModel entity) {
        mView.showDetail(entity);
    }


}
