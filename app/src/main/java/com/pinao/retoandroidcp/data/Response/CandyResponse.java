package com.pinao.retoandroidcp.data.Response;

import com.google.gson.annotations.SerializedName;
import com.pinao.retoandroidcp.data.model.CandyModel;

import java.util.List;

public class CandyResponse {
    @SerializedName("items")
    private List<CandyModel> items;

    public List<CandyModel> getItems() {
        return items;
    }

    public void setItems(List<CandyModel> items) {
        this.items = items;
    }
}
