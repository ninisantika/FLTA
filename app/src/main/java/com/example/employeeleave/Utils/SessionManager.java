package com.example.employeeleave.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.employeeleave.Activity.DashboardStaffActivity;

import java.util.HashMap;

import javax.microedition.khronos.egl.EGLDisplay;

public class SessionManager {
    private SharedPreferences.Editor editor;
    private Context context;
    private SharedPreferences sharedPreferences ;
    public static final String KEY_NIK = "nik";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_TGLMASUK = "tanggal_masuk";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_JUMLAH = "jumlah_cuti";
    public static final String KEY_LEVEL = "level";
    public static final String VALIDASI_LOGIN = "loginstatus";
    public final String SHARE_NAME= "loginsession";
    public final int MODE_PRIVATE = 0;

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // fungsi untuk menyetor username ke sharemanager
    public void storeLogin(String username, String nik, String nama, String tgl_masuk, String jml_cuti, String level){
        editor.putBoolean(VALIDASI_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_NIK, nik);
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_TGLMASUK, tgl_masuk);
        editor.putString(KEY_JUMLAH, jml_cuti);
        editor.putString(KEY_LEVEL, level);
        editor.commit();
    }

    public HashMap detailLogin(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        hashMap.put(KEY_NIK, sharedPreferences.getString(KEY_NIK, null));
        hashMap.put(KEY_NAMA, sharedPreferences.getString(KEY_NAMA, null));
        hashMap.put(KEY_TGLMASUK, sharedPreferences.getString(KEY_TGLMASUK, null));
        hashMap.put(KEY_JUMLAH, sharedPreferences.getString(KEY_JUMLAH, null));
        hashMap.put(KEY_LEVEL, sharedPreferences.getString(KEY_LEVEL, null));
        return hashMap;
    }


    private boolean validasiLogin(){
        return sharedPreferences.getBoolean(VALIDASI_LOGIN, false);

    }

    public void checkLogin(){
        if (!this.validasiLogin()){
            Intent i = new Intent(context, DashboardStaffActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}