package com.pinao.retoandroidcp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pinao.retoandroidcp.data.database.entity.LoginEntity;

import java.util.List;
@Dao
public interface LoginDao {
    @Query("SELECT * FROM LoginEntity_table WHERE email = :email AND password = :password")
   LiveData< List<LoginEntity>> getAll(String email, String password);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LoginEntity> userEntity);
    @Query("DELETE FROM LoginEntity_table")
    void deleteAll();
}
