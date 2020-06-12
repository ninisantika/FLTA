package com.example.employeeleave.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.employeeleave.API.APIRequestData;
import com.example.employeeleave.API.APIUtils;
import com.example.employeeleave.API.RetrofitClient;
import com.example.employeeleave.Model.KaryawanCuti;
import com.example.employeeleave.Model.ResponModelKaryawan;
import com.example.employeeleave.R;
import com.example.employeeleave.Utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ProgressBar progressBar;
    private EditText edLogin, edPass;
    private Button btnLogin;
    private APIRequestData apiRequestData;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String nik, nama, jumlah_cuti, tanggal_masuk, level, loginUname, loginPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiRequestData = APIUtils.getAPIRequest();
        sessionManager = new SessionManager(this);

        edLogin = (EditText) findViewById(R.id.edUsername);
        edPass = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.sign_in);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_sign);

        btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               loginUname = edLogin.getText().toString();
               loginPass = edPass.getText().toString();

               if (loginUname.isEmpty()){
                   edLogin.setError("Username Tidak Boleh Kosong!");
               } if (loginPass.isEmpty()){
                   edPass.setError("Password Tidak Boleh Kosong!");
               } else {
                   CreateLogin();
               }
           }
       });
    }

    private void CreateLogin(){

        progressBar.setVisibility(View.VISIBLE);
        Call<ResponModelKaryawan> sesionLogin = apiRequestData.ARDSesionLogin(
                loginUname,
                loginPass,
                nik,
                nama,
                tanggal_masuk,
                jumlah_cuti,
                level);
        sesionLogin.enqueue(new Callback<ResponModelKaryawan>() {
            @Override
            public void onResponse(Call<ResponModelKaryawan> call, Response<ResponModelKaryawan> response) {

                Log.v(TAG, "Info Response = " +response.toString());

                ResponModelKaryawan respon = response.body();
                String kode = respon.getKode_id();
                String pesan = respon.getPesan();
                List<KaryawanCuti> LoginUser = response.body().getData();
                if (kode.equals("1")){
                    sessionManager.storeLogin(LoginUser.get(0).getUsername(),
                            LoginUser.get(0).getNik(),
                            LoginUser.get(0).getNama(),
                            LoginUser.get(0).getTanggal_masuk(),
                            LoginUser.get(0).getJumlah_cuti(),
                            LoginUser.get(0).getLevel()
                    );
                    Toast.makeText(MainActivity.this, "Pesan : " +pesan, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), DashboardStaffActivity.class);
                    i.putExtra("nama", LoginUser.get(0).getNama());
                    i.putExtra("level", LoginUser.get(0).getLevel());
                    startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<ResponModelKaryawan> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Data Gagal Di Temukan " +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
