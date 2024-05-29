package com.pinao.retoandroidcp.data.network;

import com.pinao.retoandroidcp.data.Response.CandyResponse;
import com.pinao.retoandroidcp.data.model.CandyModel;
import com.pinao.retoandroidcp.data.network.ApiService.ApiService;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import android.util.Log;

public class CandyService {

    private final ApiService apiService;
    @Inject
    public  CandyService(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<CandyModel> getCandies() {
        try {
            Response<CandyResponse> response = apiService.getCandies().execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().getItems();
            }
        } catch (Exception e) {
            Log.e("CandyService", "Error getting candies", e);
        }
        return Collections.emptyList();
    }
}
