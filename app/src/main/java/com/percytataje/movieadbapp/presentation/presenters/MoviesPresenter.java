package com.percytataje.movieadbapp.presentation.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.percytataje.movieadbapp.BuildConfig;
import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.holder.TrackEntityHolder;
import com.percytataje.movieadbapp.data.remote.ServiceFactory;
import com.percytataje.movieadbapp.data.remote.request.MoviesRequest;
import com.percytataje.movieadbapp.presentation.contracts.MoviesContract;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesPresenter implements MoviesContract.Presenter,CommunicatorEntityItem<MovieModel> {

    private final MoviesContract.View mView;
    private boolean mFirstLoad = false;
    private Context mContext;
    private int mTotalPages;


    public MoviesPresenter(@NonNull MoviesContract.View view,
                           Context context) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mContext = context;

    }


    @Override
    public void start() {
        if (!mFirstLoad) {
            loadPopularMovies(1);
            mFirstLoad = true;
        }

    }



    @Override
    public void loadPopularMovies(final int page) {
        mView.setLoadingIndicator(true);
        final MoviesRequest moviesRequest =
                ServiceFactory.createService(MoviesRequest.class);
        String languaje = mContext.getResources().getString(R.string.languaje_api);
        Call<TrackEntityHolder<MovieModel>> call = moviesRequest.
                getMoviesPopular(BuildConfig.API_KEY, languaje,page);

        call.enqueue(new Callback<TrackEntityHolder<MovieModel>>() {
            @Override
            public void onResponse(Call<TrackEntityHolder<MovieModel>> call, Response<TrackEntityHolder<MovieModel>> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }

                    mTotalPages = response.body().getTotal_pages();

                    if (page==1){
                        mView.showPopularMovies(response.body().getResults());

                    }else{
                        mView.showMorePopularMoviews(response.body().getResults());
                    }

                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.showErrorMessage("No se pudieron cargar las peliculas");
                }
            }

            @Override
            public void onFailure(Call<TrackEntityHolder<MovieModel>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void loadSearchMovies(final int page, String query) {
        mView.setLoadingIndicator(true);
        final MoviesRequest moviesRequest =
                ServiceFactory.createService(MoviesRequest.class);
        String languaje = mContext.getResources().getString(R.string.languaje_api);
        Call<TrackEntityHolder<MovieModel>> call = moviesRequest.
                getSearchMovies(BuildConfig.API_KEY, languaje,query,page);

        call.enqueue(new Callback<TrackEntityHolder<MovieModel>>() {
            @Override
            public void onResponse(Call<TrackEntityHolder<MovieModel>> call, Response<TrackEntityHolder<MovieModel>> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    if (!mView.isActive()) {
                        return;
                    }

                    mTotalPages = response.body().getTotal_pages();

                    if (page==1){
                        mView.showSearchMovies(response.body().getResults());

                    }else{
                        mView.showMoreSearchMovies(response.body().getResults());
                    }

                } else {
                    if (!mView.isActive()) {
                        return;
                    }
                    mView.showErrorMessage("No se pudieron cargar las peliculas");
                }
            }

            @Override
            public void onFailure(Call<TrackEntityHolder<MovieModel>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void onClick(MovieModel entity) {
        mView.showDetail(entity);
    }
}
