package com.percytataje.movieadbapp.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseActivity;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.presentation.fragments.MovieDetailFragment;
import com.percytataje.movieadbapp.presentation.fragments.MoviesFragment;
import com.percytataje.movieadbapp.presentation.presenters.MoviesPresenter;
import com.percytataje.movieadbapp.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends BaseActivity {


    public static final String MOVIE_EXTRA= "movie";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        MovieModel eventModel = (MovieModel) extras.getSerializable(MOVIE_EXTRA);

        toolbar.setTitle(eventModel.getTitle());
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);





        MovieDetailFragment fragment = (MovieDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = MovieDetailFragment.newInstance(eventModel);

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
