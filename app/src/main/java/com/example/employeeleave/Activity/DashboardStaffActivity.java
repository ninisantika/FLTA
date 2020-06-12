package com.example.employeeleave.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeleave.R;
import com.example.employeeleave.Utils.SessionManager;

import java.util.HashMap;

public class DashboardStaffActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgData, imgCuti, imgLogout;
    private TextView fullname, level;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_staff);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sessionManager = new SessionManager(this);
        fullname = findViewById(R.id.namahallo);
        level = findViewById(R.id.fullnameDashboard);
        imgData = findViewById(R.id.imgData);
        imgCuti = findViewById(R.id.imgCuti);
        imgLogout = findViewById(R.id.imgLogout);

        imgData.setOnClickListener(this);
        imgCuti.setOnClickListener(this);
        imgLogout.setOnClickListener(this);

        HashMap hashMap = sessionManager.detailLogin();
        fullname.setText((String) hashMap.get(SessionManager.KEY_NAMA));
        level.setText((String) hashMap.get(SessionManager.KEY_LEVEL));

        sessionManager.checkLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgCuti:
                Intent daftar = new Intent(this, DataCutiActivity.class);
                daftar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(daftar);
                break;
            case R.id.imgData:
                Intent profile = new Intent(this, DataProfileActivity.class);
                profile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profile);
                break;
            case R.id.imgLogout:
                Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                Intent signOut = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(signOut);
                finish();
                break;
        }
    }

}
