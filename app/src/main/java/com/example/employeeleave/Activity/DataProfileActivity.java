package com.example.employeeleave.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.employeeleave.R;
import com.example.employeeleave.Utils.SessionManager;

import java.util.HashMap;

public class DataProfileActivity extends AppCompatActivity {

    private TextView tvFullebel, tvNik, tvUsername, tvLevel, tvJml, tvTgl_Masuk;
    private Button btnUbah;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_profile);

        sessionManager = new SessionManager(this);
        tvFullebel = (TextView) findViewById(R.id.fullnamaLabel);
        tvNik = (TextView) findViewById(R.id.nikData);
        tvUsername = (TextView) findViewById(R.id.userData);
        tvTgl_Masuk = (TextView) findViewById(R.id.tgl_masukData);
        tvJml = (TextView) findViewById(R.id.jumlahDataCuti);
        tvLevel = (TextView) findViewById(R.id.levelData);
        btnUbah = (Button) findViewById(R.id.ubah_profile);

        HashMap hashMap = sessionManager.detailLogin();
        tvFullebel.setText((String) hashMap.get(SessionManager.KEY_NAMA));
        tvNik.setText((String) hashMap.get(SessionManager.KEY_NIK));
        tvUsername.setText((String) hashMap.get(SessionManager.KEY_USERNAME));
        tvTgl_Masuk.setText((String) hashMap.get(SessionManager.KEY_TGLMASUK));
        tvJml.setText((String) hashMap.get(SessionManager.KEY_JUMLAH));
        tvLevel.setText((String) hashMap.get(SessionManager.KEY_LEVEL));

        sessionManager.checkLogin();

    }
}
