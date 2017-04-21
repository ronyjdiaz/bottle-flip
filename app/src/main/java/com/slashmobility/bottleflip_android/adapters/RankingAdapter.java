package com.slashmobility.bottleflip_android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    private Context mContext;
    private ArrayList<String> mProductList;
    public RankingAdapter(Context mContext, ArrayList<String> mProductList){
        this.mProductList = mProductList;
        this.mContext = mContext;

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
        return mProductList.size();
    }

    public static class RankingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textviewPosition) TextView mtvNumber;
        @BindView(R.id.textviewName) TextView mtvName;
        @BindView(R.id.textviewPoints) TextView mtvPoint;
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

