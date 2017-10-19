package com.percytataje.movieadbapp.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseActivity;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.presentation.fragments.MovieDetailFragment;
import com.percytataje.movieadbapp.presentation.fragments.SerieDetailFragment;
import com.percytataje.movieadbapp.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSeriesActivity extends BaseActivity {


    public static final String MOVIE_EXTRA= "movie";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        SerieModel eventModel = (SerieModel) extras.getSerializable(MOVIE_EXTRA);

        toolbar.setTitle(eventModel.getName());
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        SerieDetailFragment fragment = (SerieDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = SerieDetailFragment.newInstance(eventModel);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }

        // Create the presenter

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
