package com.example.employeeleave.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponModelCuti {

    @SerializedName("kode_id")
    private String kode_id;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private List<DataCuti> data;

    public ResponModelCuti(List<DataCuti> data) {
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

    public List<DataCuti> getData() {
        return data;
    }

    public void setData(List<DataCuti> data) {
        this.data = data;
    }
}
