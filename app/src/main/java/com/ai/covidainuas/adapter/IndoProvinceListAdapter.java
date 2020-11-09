package com.ai.covidainuas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ai.covidainuas.model.IndonesiaProvinsiModel;
import com.ai.covidainuluas.R;

import java.util.ArrayList;

public class IndoProvinceListAdapter extends RecyclerView.Adapter<IndoProvinceListAdapter.ViewHolder> {
    private final ArrayList<IndonesiaProvinsiModel> indoList = new ArrayList<>();
    private final Context context;

    public IndoProvinceListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<IndonesiaProvinsiModel> getIndoList() {
        return indoList;
    }

    public void setIndoList(ArrayList<IndonesiaProvinsiModel> indoItem) {
        if (indoList != null) {
            if (indoList.size() > 0) {
                indoList.clear();
            }
            indoList.addAll(indoItem);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.indonesia_province_item_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvConfirmed.setText(String.valueOf(indoList.get(position).getAttributesProv().getConfirmed()));
        holder.tvRecovered.setText(String.valueOf(indoList.get(position).getAttributesProv().getRecovered()));
        holder.tvDeath.setText(String.valueOf(indoList.get(position).getAttributesProv().getDead()));
        holder.tvProvince.setText(indoList.get(position).getAttributesProv().getProvinsi());
    }

    @Override
    public int getItemCount() {
        return indoList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvConfirmed;
        final TextView tvRecovered;
        final TextView tvDeath;
        final TextView tvProvince;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvConfirmed = itemView.findViewById(R.id.tvProvConfirmed);
            tvRecovered = itemView.findViewById(R.id.tvProvRecovered);
            tvDeath = itemView.findViewById(R.id.tvProvDeath);
            tvProvince = itemView.findViewById(R.id.tvProvProvince);
        }
    }

}
