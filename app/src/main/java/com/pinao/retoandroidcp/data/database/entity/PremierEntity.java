package com.pinao.retoandroidcp.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.pinao.retoandroidcp.domain.model.PremierItems;

@Entity(tableName = "PremierEntity_table")
public class PremierEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id=0;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "image")
    private String image;

    public PremierEntity(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
    public PremierItems toDomain() {
        return new PremierItems(name, description, image);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

