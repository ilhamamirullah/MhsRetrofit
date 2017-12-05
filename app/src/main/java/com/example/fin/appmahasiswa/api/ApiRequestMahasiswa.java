package com.example.fin.appmahasiswa.api;

import com.example.fin.appmahasiswa.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Fin on 28/11/2017.
 */

public interface ApiRequestMahasiswa {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendMahasiswa(@Field("npm") String npm,
                                     @Field("nama") String nama,
                                     @Field("prodi") String prodi,
                                     @Field("fakultas") String fakultas);

    @GET("read.php")
    Call<ResponsModel> getMahasiswa();
    

}
