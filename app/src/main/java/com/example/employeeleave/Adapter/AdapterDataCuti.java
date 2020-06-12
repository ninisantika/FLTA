package com.example.employeeleave.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeleave.API.APIRequestData;
import com.example.employeeleave.API.APIUtils;

import com.example.employeeleave.Activity.DataCutiActivity;
import com.example.employeeleave.Activity.DetailCutiUserActivity;
import com.example.employeeleave.Model.DataCuti;
import com.example.employeeleave.Model.ResponModelCuti;
import com.example.employeeleave.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataCuti extends RecyclerView.Adapter<AdapterDataCuti.MyViewHolder> {
    private Context context;
    private List<DataCuti> listDataCuti;
    private int kode_cuti;
    private APIRequestData apiRequestData;

    public AdapterDataCuti(Context cont, List<DataCuti> data) {
        this.context = cont;
        this.listDataCuti = data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recylce_list_history, parent, false);
        return new MyViewHolder(view);
    }

    /* data terhubung ke db */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        DataCuti dataCuti = listDataCuti.get(position);

        holder.valIdCuti.setText(String.valueOf(dataCuti.getKode()));
        holder.valStatusAproval.setText(dataCuti.getStatus());
        holder.valKet.setText(dataCuti.getKet());
        holder.btnDetail.setOnClickListener(v -> {
            Intent i = new Intent(context.getApplicationContext(), DetailCutiUserActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("kode", listDataCuti.get(position).getKode());
            i.putExtra("nik", listDataCuti.get(position).getNik());
            i.putExtra("tanggal_awal", listDataCuti.get(position).getTanggal_awal());
            i.putExtra("tanggal_akhir", listDataCuti.get(position).getTanggal_akhir());
            i.putExtra("jenis_cuti", listDataCuti.get(position).getJenis_cuti());
            i.putExtra("jumlah", listDataCuti.get(position).getJumlah());
            i.putExtra("ket", listDataCuti.get(position).getKet());
            i.putExtra("status", listDataCuti.get(position).getStatus());

            context.startActivity(i);

        });

    }

    /* data sesuai dari db */
    @Override
    public int getItemCount() {
        return listDataCuti.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView valStatusAproval, valIdCuti, valKet;
        Button btnDetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            valIdCuti = itemView.findViewById(R.id.id_listcuti);
            valStatusAproval = itemView.findViewById(R.id.id_statuscuti);
            valKet = itemView.findViewById(R.id.id_ket);
            btnDetail = itemView.findViewById(R.id.detail_cuti);

            apiRequestData = APIUtils.getAPIRequest();

            itemView.setOnLongClickListener(v -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
                dialogPesan.setMessage("Pilih Yang Akan dilakukan ?");
                dialogPesan.setCancelable(true);

                kode_cuti = Integer.parseInt(valIdCuti.getText().toString());

                dialogPesan.setNegativeButton("UBAH", (dialog, which) -> {

                });
                dialogPesan.setPositiveButton("HAPUS", (dialog, which) -> {
                    deleteDataCuti();
                    dialog.dismiss();
                    ((DataCutiActivity) context).retrofitData();
                });

                dialogPesan.show();
                return false;
            });

        }

        private void deleteDataCuti(){

            Call<ResponModelCuti> deleteCuti = apiRequestData.ARDDeleteCuti(kode_cuti);
            deleteCuti.enqueue(new Callback<ResponModelCuti>() {
                @Override
                public void onResponse(Call<ResponModelCuti> call, Response<ResponModelCuti> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(context, " Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponModelCuti> call, Throwable t) {
                    Toast.makeText(context, "Data Gagal Di Hapus " +t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });



        }
    }
}
