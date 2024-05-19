package com.pinao.retoandroidcp.data.Response;

import com.google.gson.annotations.SerializedName;
import com.pinao.retoandroidcp.data.model.PremierModel;

import java.util.List;

public class PremierResponse {
    @SerializedName("premieres")
    private List<PremierModel> premieres;

    public List<PremierModel> getPremieres() {
        return premieres;
    }

    public void setPremieres(List<PremierModel> premieres) {
        this.premieres = premieres;
    }
}
