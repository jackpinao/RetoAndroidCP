package com.pinao.retoandroidcp.domain.model;

import com.pinao.retoandroidcp.data.database.entity.PremierEntity;

public class PremierItems {
    private final String name;
    private final String description;
    private final String image;

    public PremierItems(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public PremierEntity toDatabase() {
        return new PremierEntity(name, description, image);
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
