package com.pinao.retoandroidcp.data.network;

import com.pinao.retoandroidcp.data.network.ApiService.ApiService;
import com.pinao.retoandroidcp.data.model.PremierModel;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import android.util.Log;

public class PremierService   {

    private final ApiService apiService;
    @Inject
    public PremierService(ApiService apiService) {
        this.apiService = apiService;
    }
    public List<PremierModel> getPremiers() {
        try {
            Response<List<PremierModel>> response = apiService.getPremiers().execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            //return apiService.getPremiers().execute().body();
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("PremierService", "Error getting premiers", e);
        }
        return Collections.emptyList();
        //apiService.getPremiers();
    }
}
