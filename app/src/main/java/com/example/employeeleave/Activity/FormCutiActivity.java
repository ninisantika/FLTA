package com.example.employeeleave.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeleave.API.APIRequestData;
import com.example.employeeleave.API.APIUtils;
import com.example.employeeleave.API.RetrofitClient;
import com.example.employeeleave.Model.ResponModelCuti;
import com.example.employeeleave.R;
import com.example.employeeleave.Utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCutiActivity extends AppCompatActivity {

//    private TextView edNIK;
    private EditText edNIK, edJumlahCuti, edTglAwal, edTglAkhir, edKeterangan, statusCuti;
    private Spinner spinnerJC;
    private Button btnSubmit;
    private String kode, nik, tanggal_awal, tanggal_akhir, jumlah, jenis_cuti, ket, status;
    private APIRequestData apiRequestData;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cuti);

        sessionManager = new SessionManager(this);
        apiRequestData = APIUtils.getAPIRequest();

        edNIK = (EditText) findViewById(R.id.nik);
        edTglAwal = (EditText) findViewById(R.id.tgl_awal);
        edTglAkhir = (EditText) findViewById(R.id.tgl_akhir);
        edJumlahCuti = (EditText) findViewById(R.id.jumlah_cuti);
        spinnerJC = (Spinner) findViewById(R.id.spiner_jenis);
        edKeterangan = (EditText) findViewById(R.id.keterangan_cuti);
        statusCuti = (EditText) findViewById(R.id.status);
        btnSubmit = (Button) findViewById(R.id.submit_cuti);

        HashMap hashMap = sessionManager.detailLogin();
        edNIK.setText((String) hashMap.get(SessionManager.KEY_NIK));

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.jenis_cuti,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJC.setAdapter(adapter);

        edTglAwal.setOnClickListener(v -> showCalender(edTglAwal));
        edTglAkhir.setOnClickListener(v -> showCalender(edTglAkhir));
        btnSubmit.setOnClickListener(v -> {

            nik = edNIK.getText().toString().trim();
            tanggal_awal = edTglAwal.getText().toString().trim();
            tanggal_akhir = edTglAkhir.getText().toString().trim();
            jumlah = edJumlahCuti.getText().toString().trim();
            jenis_cuti = spinnerJC.getSelectedItem().toString().trim();
            ket = edKeterangan.getText().toString().trim();
            status = statusCuti.getText().toString().trim();

           if (tanggal_awal.isEmpty()) {
                edTglAwal.setError("Masukan Tanggal Awal");
                return;
            } if (tanggal_akhir.isEmpty()) {
                edTglAkhir.setError("Masukan Tanggal Akhir");
                return;
            } if (jumlah.isEmpty()) {
                edJumlahCuti.setError("Masukan Jumlah Cuti");
                return;
            } if (ket.isEmpty()){
                edKeterangan.setError("Masukan Keterangan");
                return;
            } else{
                createDataCuti();
            }
            hapusText();
        });

    }

    private void createDataCuti(){

        Call<ResponModelCuti> createDataCuti = apiRequestData.ARDCreateCuti(nik, tanggal_awal, tanggal_akhir, jumlah, jenis_cuti, ket, status);
        createDataCuti.enqueue(new Callback<ResponModelCuti>() {
            @Override
            public void onResponse(Call<ResponModelCuti> call, Response<ResponModelCuti> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormCutiActivity.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();

            }
            @Override
            public void onFailure(Call<ResponModelCuti> call, Throwable t) {
                Toast.makeText(FormCutiActivity.this, "Data Gagal Di Tambah " +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void hapusText() {
        edTglAwal.getText().clear();
        edTglAkhir.getText().clear();
        edJumlahCuti.getText().clear();
        edKeterangan.getText().clear();
    }

    private void showCalender(final TextView StartEndDate) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            StartEndDate.setText(simpleDateFormat.format(calendar.getTime()));
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}