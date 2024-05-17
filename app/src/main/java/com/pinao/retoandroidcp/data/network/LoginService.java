package com.pinao.retoandroidcp.data.network;

import com.pinao.retoandroidcp.data.model.LoginModel;
import com.pinao.retoandroidcp.data.network.ApiService.ApiService;

import org.openjdk.tools.sjavac.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class LoginService {
    private final ApiService apiService;
    @Inject
    public LoginService(ApiService apiService) {
        this.apiService = apiService;
    }
    public List<LoginModel> getLogins() {
        try {
            Response<List<LoginModel>> response = apiService.getLogin().execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            //return apiService.getLogins().execute().body();
        } catch (Exception e) {
            //e.printStackTrace();
            Log.error("Error fetching login entities");
        }
        return Collections.emptyList();
        //apiService.getLogins();
    }
}
