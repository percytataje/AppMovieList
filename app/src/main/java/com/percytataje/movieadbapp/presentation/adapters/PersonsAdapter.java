package com.percytataje.movieadbapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.data.model.PersonModel;
import com.percytataje.movieadbapp.data.model.SerieModel;
import com.percytataje.movieadbapp.presentation.adapters.listeners.OnClickListListener;
import com.percytataje.movieadbapp.presentation.presenters.communicators.CommunicatorEntityItem;
import com.percytataje.movieadbapp.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by percysoft on 29/06/17.
 */

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> implements OnClickListListener{


    private ArrayList<PersonModel> mListPersons;
    private Context mContext;
    private CommunicatorEntityItem<PersonModel> mPersonModelCommunicatorEntityItem;

    public PersonsAdapter(ArrayList<PersonModel> listPersons, Context context, CommunicatorEntityItem<PersonModel> personModelCommunicatorEntityItem) {
        this.mListPersons = listPersons;
        this.mContext = context;
        this.mPersonModelCommunicatorEntityItem=personModelCommunicatorEntityItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_person, parent, false);
        return new ViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PersonModel model = mListPersons.get(position);

        holder.tvTitle.setText(model.getName());
        GlideUtils.loadImage(holder.ivImage,model.getProfile_path185(),mContext);
    }

    @Override
    public int getItemCount() {
        return mListPersons.size();
    }

    public void setItems(ArrayList<PersonModel> personModels) {
        mListPersons = personModels;
        notifyDataSetChanged();
    }

    public void addMoreItems(ArrayList<PersonModel> personModels) {
        mListPersons.addAll(personModels);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {

        mPersonModelCommunicatorEntityItem.onClick(mListPersons.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;


        OnClickListListener onClickListListener;
        public ViewHolder(View itemView, OnClickListListener onClickListListener) {
            super(itemView);
            this.onClickListListener = onClickListListener;
            ButterKnife.bind(this,itemView);this.onClickListListener = onClickListListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListListener.onClick(getAdapterPosition());
        }
    }
}
