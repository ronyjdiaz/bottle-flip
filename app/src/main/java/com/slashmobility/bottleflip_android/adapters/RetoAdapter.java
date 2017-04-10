package com.slashmobility.bottleflip_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slashmobility.bottleflip_android.R;

import butterknife.ButterKnife;

/**
 * Created by Edgar-W10 on 8/4/2017.
 */

public class RetoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public RetoAdapter(){}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.reto_available_adapter, parent, false);
                viewHolder = new RetoAvailableViewHolder(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.reto_locked_adapter, parent, false);
                viewHolder = new RetoLockedViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Modificar layout adapter para cada tipo
    }

    @Override
    public int getItemViewType(int position) {
        //Modificar Preguntar si esta bloqueado o no: Para cambiar de layout
        if(position < 10) return 0; // muestra los primeros 10 con un layout diferente
        else
            return 1;
    }

    @Override
    public int getItemCount() {
        //modificar y agregar: object.size();
        return 100;
    }

    public static class RetoAvailableViewHolder extends RecyclerView.ViewHolder{
        //@BindView(R.id.textview) TextView mtvTitle;
        RetoAvailableViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class RetoLockedViewHolder extends RecyclerView.ViewHolder{
        //@BindView(R.id.textview2) TextView mtvTitle;
        RetoLockedViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}