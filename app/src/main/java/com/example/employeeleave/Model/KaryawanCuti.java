package com.example.employeeleave.Model;

public class KaryawanCuti {
    private String nik,
                nama,
                tanggal_masuk,
                jumlah_cuti,
                username,
                password,
                level,
                gambar;

    public KaryawanCuti(String nik, String nama, String tanggal_masuk, String jumlah_cuti, String username, String password, String level, String gambar) {
        this.nik = nik;
        this.nama = nama;
        this.tanggal_masuk = tanggal_masuk;
        this.jumlah_cuti = jumlah_cuti;
        this.username = username;
        this.password = password;
        this.level = level;
        this.gambar = gambar;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }

    public String getJumlah_cuti() {
        return jumlah_cuti;
    }

    public void setJumlah_cuti(String jumlah_cuti) {
        this.jumlah_cuti = jumlah_cuti;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
