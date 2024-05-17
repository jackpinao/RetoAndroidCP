package com.pinao.retoandroidcp.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.pinao.retoandroidcp.domain.model.LoginItems;

@Entity(tableName = "LoginEntity_table")
public class LoginEntity {
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
    private int id=0;
   @ColumnInfo(name = "name")
    private String name;
   @ColumnInfo(name = "email")
    private String email;
   @ColumnInfo(name = "password")
    private String password;

    public LoginEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public LoginItems toDomain() {
        return new LoginItems(name, email, password);
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
