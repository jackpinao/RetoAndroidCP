package com.pinao.retoandroidcp.data.network;

import com.pinao.retoandroidcp.data.model.CandyModel;
import com.pinao.retoandroidcp.data.network.ApiService.ApiService;

import org.openjdk.tools.sjavac.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class CandyService {

    private final ApiService apiService;
    @Inject
    public  CandyService(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<CandyModel> getCandies() {
        try {
            Response<List<CandyModel>> response = apiService.getCandies().execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            Log.error("Error fetching candy entities");
        }
        return Collections.emptyList();
    }
}
