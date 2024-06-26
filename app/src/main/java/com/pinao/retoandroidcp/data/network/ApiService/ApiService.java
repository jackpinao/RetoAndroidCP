package com.pinao.retoandroidcp.data.network.ApiService;

import com.pinao.retoandroidcp.data.Response.CandyResponse;
import com.pinao.retoandroidcp.data.Response.PremierResponse;
import com.pinao.retoandroidcp.data.model.CandyModel;
import com.pinao.retoandroidcp.data.model.LoginModel;
import com.pinao.retoandroidcp.data.model.PremierModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("https://cp-staging.onrender.com/v1/candystore")
    Call<CandyResponse> getCandies();
    @GET("https://cp-staging.onrender.com/v1/premieres")
    Call<PremierResponse> getPremiers();
    @GET("FIREBASE")
    Call<List<LoginModel>> getLogin();
}
