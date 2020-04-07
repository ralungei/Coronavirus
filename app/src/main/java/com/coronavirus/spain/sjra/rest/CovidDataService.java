package com.coronavirus.spain.sjra.rest;

import com.coronavirus.spain.sjra.model.HelpData;
import com.coronavirus.spain.sjra.model.RetrofitData;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidDataService {

    @GET("/actual")
    Call<RetrofitData> getActual();

    @GET("/info")
    Call<ArrayList<HelpData>> getHelp();

    @GET("/iframe")
    Call<ResponseBody> getIframe();

}
