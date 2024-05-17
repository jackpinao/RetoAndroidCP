package com.pinao.retoandroidcp.domain.model;

import com.pinao.retoandroidcp.data.database.entity.CandyEntity;

public class CandyItems {
    private final String name;
    private final String description;
    private final String image;
    private final String price;

    public CandyItems(String name, String description, String image, String price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public CandyEntity toDatabase() {
        return new CandyEntity(name, description, image, price);
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
