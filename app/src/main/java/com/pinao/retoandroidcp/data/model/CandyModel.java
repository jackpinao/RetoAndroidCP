package com.pinao.retoandroidcp.data.model;

import com.google.gson.annotations.SerializedName;
import com.pinao.retoandroidcp.domain.model.CandyItems;

public class CandyModel {
    @SerializedName("name")
    private final String name;
    @SerializedName("description")
    private final String description;
    @SerializedName("image")
    private final String image;
    @SerializedName("price")
    private final String price;

    public CandyModel(String name, String description, String image, String price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public CandyItems toDomain() {
        return new CandyItems(name, description, image,price);
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getImage() {
        return image;
    }
}
