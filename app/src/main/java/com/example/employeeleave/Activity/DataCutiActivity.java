package com.example.employeeleave.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.employeeleave.API.APIRequestData;
import com.example.employeeleave.API.APIUtils;
import com.example.employeeleave.API.RetrofitClient;
import com.example.employeeleave.Adapter.AdapterDataCuti;
import com.example.employeeleave.Model.DataCuti;
import com.example.employeeleave.Model.KaryawanCuti;
import com.example.employeeleave.Model.ResponModelCuti;
import com.example.employeeleave.Model.ResponModelKaryawan;
import com.example.employeeleave.R;
import com.example.employeeleave.Utils.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCutiActivity extends AppCompatActivity {

    private List<DataCuti> list = new ArrayList<>();
    private AdapterDataCuti adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private APIRequestData apiRequestData;
    private FloatingActionButton btnAddCuti;
    private String nik;
    private static final String TAG = "DataCutiActivity";
    private SessionManager sessionManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_cuti);
        progressBar = findViewById(R.id.progress_view);

        recyclerView = findViewById(R.id.list_historycuti);
        recyclerView.setHasFixedSize(true);
        btnAddCuti = (FloatingActionButton) findViewById(R.id.btnAdd);

        apiRequestData = APIUtils.getAPIRequest();
        sessionManager = new SessionManager(this);

        linearLayout = new LinearLayoutManager(this);
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayout);

        btnAddCuti.setOnClickListener(v -> {
            Intent profile = new Intent(DataCutiActivity.this, FormCutiActivity.class);
            startActivity(profile);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrofitData();
    }
    public void retrofitData(){

        progressBar.setVisibility(View.VISIBLE);
        Call<ResponModelCuti> tampilData = apiRequestData.getData(nik); // panggil responAPI
        tampilData.enqueue(new Callback<ResponModelCuti>() {

            @Override
            public void onResponse(Call<ResponModelCuti> call, Response<ResponModelCuti> response) {

                Log.v(TAG, "Info Response = " +response);

                ResponModelCuti respon = response.body();
                String kode = respon.getKode_id();
                String pesan = respon.getPesan();
                List<DataCuti> dataCuti = respon.getData();
                if (kode.equals("1")){
                    dataCuti.get(0).getNik();
                    Toast.makeText(DataCutiActivity.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                    list = response.body().getData();
                    adapter = new AdapterDataCuti(DataCutiActivity.this, list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponModelCuti> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(DataCutiActivity.this, "Gagal Menghubungkan ke Server " +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
