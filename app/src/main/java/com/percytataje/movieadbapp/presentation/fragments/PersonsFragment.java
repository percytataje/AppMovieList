package com.percytataje.movieadbapp.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseActivity;
import com.percytataje.movieadbapp.core.BaseFragment;
import com.percytataje.movieadbapp.core.EndlessRecyclerOnScrollListener;
import com.percytataje.movieadbapp.core.ScrollChildSwipeRefreshLayout;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.PersonModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.presentation.activities.ExampleActivity;
import com.percytataje.movieadbapp.presentation.adapters.PersonsAdapter;
import com.percytataje.movieadbapp.presentation.adapters.SeriesAdapter;
import com.percytataje.movieadbapp.presentation.contracts.MoviesContract;
import com.percytataje.movieadbapp.presentation.contracts.PersonContract;
import com.percytataje.movieadbapp.presentation.contracts.SeriesContract;
import com.percytataje.movieadbapp.presentation.presenters.PersonsPresenter;
import com.percytataje.movieadbapp.presentation.presenters.SeriesPresenter;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;
import com.percytataje.movieadbapp.utils.ProgressDialogCustom;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonsFragment extends BaseFragment  implements  PersonContract.View{
    @BindView(R.id.complatins_list_persons)
    RecyclerView complatinsList;
    @BindView(R.id.noItems_persons)
    LinearLayout noItems;

    private PersonContract.Presenter mPresenter;
    private GridLayoutManager gridLayoutManager;
    private static final int NUMBER_COLUM =3 ;
    private PersonsAdapter personsAdapter;

    public PersonsFragment() {
        // Requires empty public constructor
    }

    public static PersonsFragment newInstance() {
        return new PersonsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter =  new PersonsPresenter(this,getContext());
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
        View root = inflater.inflate(R.layout.fragment_list_actores, container, false);
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
                mPresenter.loadPopularPerson(1);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personsAdapter =  new PersonsAdapter(new ArrayList<PersonModel>(),getContext(), (CommunicatorEntityItem<PersonModel>) mPresenter);
        gridLayoutManager =  new GridLayoutManager(getContext(),NUMBER_COLUM);
        complatinsList.setAdapter(personsAdapter);
        complatinsList.setLayoutManager(gridLayoutManager);

    }


    @Override
    public void setPresenter(PersonContract.Presenter presenter) {
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

    @Override
    public void showPopularPerson(ArrayList<PersonModel> personModels) {
        if (personModels != null){
            personsAdapter.setItems(personModels);
            if (personModels.size()>0){
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
                mPresenter.loadPopularPerson(current_page);
            }
        });
    }

    @Override
    public void showMorePopularPerson(ArrayList<PersonModel> personModels) {
        personsAdapter.addMoreItems(personModels);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showDetail(SerieModel entity) {

    }


}
