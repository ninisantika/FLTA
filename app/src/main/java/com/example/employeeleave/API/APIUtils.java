package com.example.employeeleave.API;

public class APIUtils {

    public static final String baseURL = "http://192.168.43.130/cuti-karyawan/";

    public static APIRequestData getAPIRequest(){
        return RetrofitClient.getClient(baseURL).create(APIRequestData.class);
    }

}
