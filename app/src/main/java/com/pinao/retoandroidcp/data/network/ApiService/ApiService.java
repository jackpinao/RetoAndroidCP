package com.pinao.retoandroidcp.data.network.ApiService;

import com.pinao.retoandroidcp.data.model.CandyModel;
import com.pinao.retoandroidcp.data.model.LoginModel;
import com.pinao.retoandroidcp.data.model.PremierModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("https://cp-staging.onrender.com/v1/candystore")
    Call<List<CandyModel>> getCandies();
    @GET("https://cp-staging.onrender.com/v1/premieres")
    Call<List<PremierModel>> getPremiers();
    @GET("FIREBASE")
    Call<List<LoginModel>> getLogin();
}
