package com.sera.rest;

import com.sera.model.RetrofitData;
import com.sera.model.StateData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidDataService {

    @GET("/actual")
    Call<RetrofitData> getActual();

}
