package com.example.fin.appmahasiswa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fin.appmahasiswa.R;
import com.example.fin.appmahasiswa.model.DataModel;

import java.util.List;

/**
 * Created by Fin on 29/11/2017.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public AdapterData (Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.npm.setText(dm.getNpm());
        holder.nama.setText(dm.getNama());
        holder.prodi.setText(dm.getProdi());
        holder.fakultas.setText(dm.getFakultas());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
    TextView npm, nama, prodi, fakultas;

    public HolderData (View v){
        super(v);
        npm = (TextView)v.findViewById(R.id.tvNPM);
        nama = (TextView)v.findViewById(R.id.tvNama);
        prodi = (TextView)v.findViewById(R.id.tvProdi);
        fakultas = (TextView)v.findViewById(R.id.tvFakultas);


    }
    }
}
