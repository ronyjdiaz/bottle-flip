package com.slashmobility.bottleflip_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    public RankingAdapter(){

    }

    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_adapter, parent, false);
        RankingAdapter.RankingViewHolder rvh = new RankingAdapter.RankingViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RankingViewHolder holder, int position) {
        holder.mtvNumber.setText(position + "º");
        Log.e("item", "" + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class RankingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ranking_text_number) TextView mtvNumber;
        @BindView(R.id.ranking_text_name) TextView mtvName;
        @BindView(R.id.ranking_text_point) TextView mtvPoint;
        View itemView;
        RankingViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



}

