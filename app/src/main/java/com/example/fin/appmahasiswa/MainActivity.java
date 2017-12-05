package com.example.fin.appmahasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fin.appmahasiswa.api.ApiRequestMahasiswa;
import com.example.fin.appmahasiswa.api.Retroserver;
import com.example.fin.appmahasiswa.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText npm, nama, prodi, fakultas;
//    TextView text;
    Button save, show;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        npm = (EditText)findViewById(R.id.edt_npm);
        nama = (EditText)findViewById(R.id.edt_nama);
        prodi = (EditText)findViewById(R.id.edt_prodi);
        fakultas = (EditText)findViewById(R.id.edt_fakultas);

        save = (Button) findViewById(R.id.btn_save);
        show = (Button) findViewById(R.id.btn_show);
        pd = new ProgressDialog(this);

//        final ApiRequestMahasiswa api = Retroserver.getClient().create(ApiRequestMahasiswa.class);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godata = new Intent(MainActivity.this, TampilData.class);
                startActivity(godata);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Data...");
                pd.setCancelable(false);
                pd.show();

                String snpm = npm.getText().toString();
                String snama = nama.getText().toString();
                String sprodi = prodi.getText().toString();
                String sfakultas = fakultas.getText().toString();
                ApiRequestMahasiswa api = Retroserver.getClient().create(ApiRequestMahasiswa.class);

                Call<ResponsModel> sendmhs = api.sendMahasiswa(snpm, snama, sprodi,sfakultas);
                sendmhs.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response: " + response.body().toString());
                        String kode = response.body().getKode();

                        if (kode.equals("1")){
                            Toast.makeText(MainActivity.this, "data berhasil disimpan", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this, "data gagal disimpan", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Failure: " + "gagal mengirim string");
                    }
                });
            }
        });

    }
}
