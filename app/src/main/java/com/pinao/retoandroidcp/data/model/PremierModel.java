package com.pinao.retoandroidcp.data.model;

import com.google.gson.annotations.SerializedName;
import com.pinao.retoandroidcp.domain.model.PremierItems;

public class PremierModel {
    @SerializedName("name")
    private final String name;
    @SerializedName("description")
    private final String description;
    @SerializedName("image")
    private final String image;

    public PremierModel(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public PremierItems toDomain() {
        return new PremierItems(name, description, image);
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
