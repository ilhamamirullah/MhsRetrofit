package com.example.fin.appmahasiswa;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.fin.appmahasiswa.adapter.AdapterData;
import com.example.fin.appmahasiswa.api.ApiRequestMahasiswa;
import com.example.fin.appmahasiswa.api.Retroserver;
import com.example.fin.appmahasiswa.model.DataModel;
import com.example.fin.appmahasiswa.model.ResponsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class TampilData extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView)findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();


        ApiRequestMahasiswa api = Retroserver.getClient().create(ApiRequestMahasiswa.class);
        retrofit2.Call<ResponsModel> getdata = api.getMahasiswa();
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(retrofit2.Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "response: " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new AdapterData(TampilData.this, mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "Failed: " + "Response Gagal");
            }
        });

    }
}
