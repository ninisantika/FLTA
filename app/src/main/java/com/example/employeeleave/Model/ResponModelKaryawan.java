package com.example.employeeleave.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponModelKaryawan {

    @SerializedName("kode_id")
    private String kode_id;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private List<KaryawanCuti> data;

    public ResponModelKaryawan(List<KaryawanCuti> data) {
        this.data = data;
    }

    public String getKode_id() {
        return kode_id;
    }

    public void setKode_id(String kode_id) {
        this.kode_id = kode_id;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<KaryawanCuti> getData() {
        return data;
    }

    public void setData(List<KaryawanCuti> data) {
        this.data = data;
    }
}
