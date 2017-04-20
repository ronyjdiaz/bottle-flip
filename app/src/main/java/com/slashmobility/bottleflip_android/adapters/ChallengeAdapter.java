package com.slashmobility.bottleflip_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengeDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    private Context mContext;
    private ArrayList<String> mProductList;

    public ChallengeAdapter(Context mContext, ArrayList<String> mProductList){
        this.mProductList = mProductList;
        this.mContext = mContext;
    }


    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reto_available_adapter, parent, false);
        ChallengeViewHolder retoAvailableViewHolder = new ChallengeViewHolder(v);
        return retoAvailableViewHolder;
    }

    @Override
    public void onBindViewHolder(ChallengeAdapter.ChallengeViewHolder holder, int position) {
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ChallengeDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        return mProductList.size();
    }

    public static class ChallengeViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView textviewChallengeName;
        ChallengeViewHolder(View itemView){
            super(itemView);
            textviewChallengeName = (TextView)itemView.findViewById(R.id.textviewChallengeName);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.layoutMain);

        }
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}