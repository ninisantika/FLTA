package com.example.employeeleave.Model;

public class DataCuti {

    private String kode,
                nik,
                tanggal_awal,
                tanggal_akhir,
                jumlah,
                jenis_cuti,
                ket,
                status;

    public DataCuti(String kode, String nik, String tanggal_awal, String tanggal_akhir, String jumlah, String jenis_cuti, String ket, String status) {
        this.kode = kode;
        this.nik = nik;
        this.tanggal_awal = tanggal_awal;
        this.tanggal_akhir = tanggal_akhir;
        this.jumlah = jumlah;
        this.jenis_cuti = jenis_cuti;
        this.ket = ket;
        this.status = status;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTanggal_awal() {
        return tanggal_awal;
    }

    public void setTanggal_awal(String tanggal_awal) {
        this.tanggal_awal = tanggal_awal;
    }

    public String getTanggal_akhir() {
        return tanggal_akhir;
    }

    public void setTanggal_akhir(String tanggal_akhir) {
        this.tanggal_akhir = tanggal_akhir;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getJenis_cuti() {
        return jenis_cuti;
    }

    public void setJenis_cuti(String jenis_cuti) {
        this.jenis_cuti = jenis_cuti;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
