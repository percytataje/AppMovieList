package com.percytataje.movieadbapp.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseFragment;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.presentation.activities.DetailMovieActivity;
import com.percytataje.movieadbapp.presentation.activities.DetailSeriesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SerieDetailFragment extends BaseFragment {


    @BindView(R.id.original_name)
    TextView originalName;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.vote_count)
    TextView voteCount;
    @BindView(R.id.poster_path)
    ImageView posterPath;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.backdrop_path)
    ImageView backdropPath;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.ScrollView01)
    ScrollView ScrollView01;
    private SerieModel mSeriemodel;

    public SerieDetailFragment() {
        // Requires empty public constructor
    }

    public static SerieDetailFragment newInstance(SerieModel serieModel) {
        SerieDetailFragment fragment = new SerieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailMovieActivity.MOVIE_EXTRA, serieModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mSeriemodel = (SerieModel) bundle.getSerializable(DetailSeriesActivity.MOVIE_EXTRA);
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_serie, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        originalName.setText(mSeriemodel.getOriginal_name());
        id.setText(String.valueOf(mSeriemodel.getId()));
        voteCount.setText(String.valueOf(mSeriemodel.getVote_count()));
        Glide.with(getContext())
                .load(mSeriemodel.getPoster_path780())
                .into(posterPath);
        popularity.setText(String.valueOf(mSeriemodel.getPopularity()));
        originalLanguage.setText(mSeriemodel.getOriginal_language());
        Glide.with(getContext())
                .load(mSeriemodel.getBackdrop_path780())
                .into(backdropPath);
        overview.setText(mSeriemodel.getOverview());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
