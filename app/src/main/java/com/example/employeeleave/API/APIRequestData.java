package com.example.employeeleave.API;

import com.example.employeeleave.Model.DataCuti;
import com.example.employeeleave.Model.ResponModelCuti;
import com.example.employeeleave.Model.ResponModelKaryawan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequestData {

    @GET("retrive-cuti.php")
    Call<ResponModelCuti> ARDRetrotifCuti();

    @GET("show-data.php")
    Call<ResponModelCuti> getData(
            @Query("nik") String nik
    );

    // form encode
    @FormUrlEncoded
    @POST("create-cuti.php")
    Call<ResponModelCuti> ARDCreateCuti(
            @Field("nik") String nik,
            @Field("tgl_awal") String tanggal_awal,
            @Field("tgl_akhir") String tanggal_akhir,
            @Field("jumlah") String jumlah,
            @Field("jenis_cuti") String jenis_cuti,
            @Field("ket") String ket,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("delete-cuti.php")
    Call<ResponModelCuti> ARDDeleteCuti(
            @Field("kode") int kode
    );

    @FormUrlEncoded
    @POST("login-android.php")
    Call<ResponModelKaryawan> ARDSesionLogin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("tanggal_masuk") String tanggal_masuk,
            @Field("jumlah_cuti") String jumlah_cuti,
            @Field("level") String level
//            @Field("gambar") String gambar

    );

    @FormUrlEncoded
    @POST("upload-image.php")
    Call<ResponModelKaryawan> ARDUploadImg();
}
