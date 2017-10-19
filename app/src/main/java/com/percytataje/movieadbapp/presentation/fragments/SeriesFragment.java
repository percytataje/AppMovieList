package com.percytataje.movieadbapp.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseActivity;
import com.percytataje.movieadbapp.core.BaseFragment;
import com.percytataje.movieadbapp.core.EndlessRecyclerOnScrollListener;
import com.percytataje.movieadbapp.core.ScrollChildSwipeRefreshLayout;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.presentation.activities.DetailMovieActivity;
import com.percytataje.movieadbapp.presentation.activities.DetailSeriesActivity;
import com.percytataje.movieadbapp.presentation.activities.ExampleActivity;
import com.percytataje.movieadbapp.presentation.adapters.MoviesAdapter;
import com.percytataje.movieadbapp.presentation.adapters.SeriesAdapter;
import com.percytataje.movieadbapp.presentation.contracts.MoviesContract;
import com.percytataje.movieadbapp.presentation.contracts.SeriesContract;
import com.percytataje.movieadbapp.presentation.presenters.MoviesPresenter;
import com.percytataje.movieadbapp.presentation.presenters.SeriesPresenter;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;
import com.percytataje.movieadbapp.utils.ProgressDialogCustom;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SeriesFragment extends BaseFragment implements SeriesContract.View {


    @BindView(R.id.complatins_list_series)
    RecyclerView complatinsList;
    @BindView(R.id.noItems_series)
    LinearLayout noItems;

    @BindView(R.id.et_serie)
    EditText et_serie;


    private SeriesContract.Presenter mPresenter;
    private GridLayoutManager gridLayoutManager;
    private static final int NUMBER_COLUM =3 ;
    private SeriesAdapter seriesAdapter;

    public SeriesFragment() {
        // Requires empty public constructor
    }

    public static SeriesFragment newInstance() {
        return new SeriesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter =  new SeriesPresenter(this,getContext());
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_series, container, false);
        ButterKnife.bind(this, root);

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(complatinsList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadPopularSeries(1);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seriesAdapter =  new SeriesAdapter(new ArrayList<SerieModel>(),getContext(), (CommunicatorEntityItem<SerieModel>) mPresenter);
        gridLayoutManager =  new GridLayoutManager(getContext(),NUMBER_COLUM);
        complatinsList.setAdapter(seriesAdapter);
        complatinsList.setLayoutManager(gridLayoutManager);

    }




    @Override
    public void setPresenter(SeriesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        ((BaseActivity) getActivity()).showMessage(msg);
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity) getActivity()).showMessageError(message);
    }

    @OnClick(R.id.btn_serie)
    public void search()
    {
        final String serie = String.valueOf(et_serie.getText());
        mPresenter.loadSearchSeries(1,serie);

    }

    @Override
    public void showPopularSeries(ArrayList<SerieModel> serieModels) {

        if (serieModels != null){
            seriesAdapter.setItems(serieModels);
            if (serieModels.size()>0){
                noItems.setVisibility(View.GONE);


            }else{
                noItems.setVisibility(View.VISIBLE);

            }
        }else{
            showErrorMessage("Ocurrió un error desconocido");
        }

        complatinsList.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                mPresenter.loadPopularSeries(current_page);
            }
        });
    }

    @Override
    public void showMorePopularSeriews(ArrayList<SerieModel> serieModels) {
        seriesAdapter.addMoreItems(serieModels);
    }

    @Override
    public void showSearchSeries(ArrayList<SerieModel> serieModels) {
        if (serieModels != null){
            seriesAdapter.setItems(serieModels);
            if (serieModels.size()>0){
                noItems.setVisibility(View.GONE);
            }else{
                noItems.setVisibility(View.VISIBLE);
            }
        }else{
            showErrorMessage("Ocurrió un error desconocido");
        }

        complatinsList.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                mPresenter.loadPopularSeries(current_page);
            }
        });
    }

    @Override
    public void showMoreSearchSeries(ArrayList<SerieModel> serieModels) {
        seriesAdapter.addMoreItems(serieModels);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showDetail(SerieModel entity) {
        Intent intent = new Intent(getContext(), DetailSeriesActivity.class);
        intent.putExtra(DetailMovieActivity.MOVIE_EXTRA,entity);
        startActivity(intent);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
