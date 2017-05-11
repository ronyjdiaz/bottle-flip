package com.slashmobility.bottleflip_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.activities.ChallengeDetailActivity;
import com.slashmobility.bottleflip_android.activities.ChallengeReviewActivity;
import com.slashmobility.bottleflip_android.activities.ChallengesActivity;
import com.slashmobility.bottleflip_android.model.Challenge;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    private Context mContext;
    private ArrayList<Challenge> mChallenges;

    public ChallengeAdapter(Context mContext, ArrayList<Challenge> mChallenges) {
        this.mChallenges = mChallenges;
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

        Challenge challengeItem = mChallenges.get(position);
        holder.textviewChallengeName.setText(challengeItem.getName());
        holder.textviewChallengeNumber.setText(mContext.getString(R.string.challenge) + " " + String.valueOf(challengeItem.getLevel()));
        holder.imageIconLocked.setVisibility(View.GONE);

        if (!SingletonSession.getInstance().getBottleCode().equals("")) {

            if (SingletonSession.getInstance().getScore() >= challengeItem.getScore()) {
                holder.textviewChallengePoints.setText(String.valueOf(challengeItem.getScore()) + " " + mContext.getString(R.string.pts));
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle bundle = new Bundle();
                        bundle.putInt("positionChallenge", position);
                        ((ChallengesActivity) mContext).openActivity(ChallengeDetailActivity.class, bundle);
                        //((ChallengesActivity)mContext).openActivity(ChallengeReviewActivity.class, bundle);

                    }
                });
            } else {
                holder.imageIconChallenge.setImageResource(R.drawable.ic_reto_default);
                holder.imageIconLocked.setVisibility(View.VISIBLE);
                holder.textviewChallengePoints.setVisibility(View.GONE);

            }
        } else {
            holder.imageIconChallenge.setImageResource(R.drawable.ic_reto_default);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("positionChallenge", position);
                    ((ChallengesActivity) mContext).openActivity(ChallengeDetailActivity.class, bundle);

                }
            });
        }


    }


    @Override
    public int getItemCount() {

        return mChallenges.size();
    }

    public static class ChallengeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textviewChallengeName)
        TextView textviewChallengeName;
        @BindView(R.id.textviewChallengePoints)
        TextView textviewChallengePoints;
        @BindView(R.id.textviewChallengeNumber)
        TextView textviewChallengeNumber;
        @BindView(R.id.layoutMain)
        LinearLayout linearLayout;
        @BindView(R.id.imageIconChallenge)
        ImageView imageIconChallenge;
        @BindView(R.id.imageIconLocked)
        ImageView imageIconLocked;

        View itemView;

        ChallengeViewHolder(View itemView) {
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