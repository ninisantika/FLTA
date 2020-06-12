package com.example.employeeleave.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.employeeleave.R;

public class DetailCutiUserActivity extends AppCompatActivity {

    private String kode, nik, tanggal_awal, tanggal_akhir, jumlah, jenis_cuti, ket, status;
    private TextView tvKode, tvNiK, tvTglAwal, tvTglAkhir, tvJumlah, tvJenis, tvKet, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuti_user);

        tvKode = findViewById(R.id.kode_cuti);
        tvNiK = findViewById(R.id.detail_id);
        tvTglAwal = findViewById(R.id.detail_tglAwal);
        tvTglAkhir = findViewById(R.id.detail_tglAkhir);
        tvJenis = findViewById(R.id.detail_jenis);
        tvJumlah = findViewById(R.id.detail_jumlah);
        tvKet = findViewById(R.id.detail_keterangan);
        tvStatus = findViewById(R.id.detail_status);

        Intent i = getIntent();

        kode = i.getStringExtra("kode");
        nik = i.getStringExtra("nik");
        tanggal_awal = i.getStringExtra("tanggal_awal");
        tanggal_akhir = i.getStringExtra("tanggal_akhir");
        jenis_cuti = i.getStringExtra("jenis_cuti");
        jumlah = i.getStringExtra("jumlah");
        ket = i.getStringExtra("ket");
        status = i.getStringExtra("status");

        tvNiK.setText(nik);
        tvTglAwal.setText(tanggal_awal);
        tvTglAkhir.setText(tanggal_awal);
        tvJenis.setText(jenis_cuti);
        tvJumlah.setText(jumlah);
        tvKet.setText(ket);
        tvStatus.setText(status);

    }
}
