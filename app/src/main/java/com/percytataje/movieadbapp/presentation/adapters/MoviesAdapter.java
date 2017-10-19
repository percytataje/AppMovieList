package com.percytataje.movieadbapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.data.model.MovieModel;
import com.percytataje.movieadbapp.presentation.adapters.listeners.OnClickListListener;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;
import com.percytataje.movieadbapp.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by percysoft on 29/06/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements OnClickListListener{


    private ArrayList<MovieModel> mListMovies;
    private Context mContext;
    private CommunicatorEntityItem<MovieModel> mMovieModelCommunicatorEntityItem;

    public MoviesAdapter(ArrayList<MovieModel> listMovies, Context context,CommunicatorEntityItem<MovieModel> movieModelCommunicatorEntityItem) {
        this.mListMovies = listMovies;
        this.mContext = context;
        this.mMovieModelCommunicatorEntityItem=movieModelCommunicatorEntityItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieModel model = mListMovies.get(position);

        holder.tvTitle.setText(model.getTitle());
        GlideUtils.loadImage(holder.ivImage,model.getPoster_path185(),mContext);
        holder.tvDescription.setText(model.getOverview());
    }

    @Override
    public int getItemCount() {
        return mListMovies.size();
    }

    public void setItems(ArrayList<MovieModel> movieModels) {
        mListMovies = movieModels;
        notifyDataSetChanged();
    }

    public void addMoreItems(ArrayList<MovieModel> movieModels) {
        mListMovies.addAll(movieModels);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {
        mMovieModelCommunicatorEntityItem.onClick(mListMovies.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        OnClickListListener onClickListListener;
        public ViewHolder(View itemView, OnClickListListener onClickListListener) {
            super(itemView);
            this.onClickListListener = onClickListListener;
            ButterKnife.bind(this,itemView);
            this.onClickListListener = onClickListListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListListener.onClick(getAdapterPosition());
        }
    }
}
