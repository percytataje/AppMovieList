package com.percytataje.movieadbapp.presentation.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseFragment;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.presentation.activities.DetailMovieActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MovieDetailFragment extends BaseFragment {

    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.video)
    TextView video;
    @BindView(R.id.vote_average)
    TextView voteAverage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.poster_path)
    ImageView posterPath;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.original_title)
    TextView originalTitle;
    @BindView(R.id.backdrop_path)
    ImageView backdropPath;
    @BindView(R.id.adult)
    TextView adult;
    @BindView(R.id.overview)
    TextView overview;

    @BindView(R.id.ScrollView01)
    ScrollView ScrollView01;

    @BindView(R.id.share_sms)
    Button shareSms;
    private MovieModel mMovieModel;
    public Context context;


    public MovieDetailFragment() {
        // Requires empty public constructor
    }

    public static MovieDetailFragment newInstance(MovieModel movieModel) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailMovieActivity.MOVIE_EXTRA, movieModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMovieModel = (MovieModel) bundle.getSerializable(DetailMovieActivity.MOVIE_EXTRA);
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        id.setText(String.valueOf(mMovieModel.getId()));
        video.setText(Boolean.toString(mMovieModel.isVideo()));
        voteAverage.setText(String.valueOf(mMovieModel.getVote_average()));
        title.setText(mMovieModel.getTitle());
        popularity.setText(String.valueOf(mMovieModel.getPopularity()));
        Glide.with(getContext())
                .load(mMovieModel.getPoster_path780())
                .into(posterPath);
        originalLanguage.setText(mMovieModel.getOriginal_language());
        originalTitle.setText(mMovieModel.getOriginal_title());
        Glide.with(getContext())
                .load(mMovieModel.getBackdrop_path780())
                .into(backdropPath);

        adult.setText(Boolean.toString(mMovieModel.isAdult()));
        overview.setText(mMovieModel.getOverview());
    }


        @OnClick(R.id.share_sms)
        public void shareSms()
        {
            Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
            intent2.setType("text/plain");
            intent2.putExtra(Intent.EXTRA_TEXT, "Compartir Home page" );
            startActivity(Intent.createChooser(intent2, "Share via"));
        }




    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}
