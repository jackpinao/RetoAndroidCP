package com.pinao.retoandroidcp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pinao.retoandroidcp.data.database.entity.CandyEntity;

import java.util.List;

@Dao
public interface CandyDao {

    @Query("SELECT * FROM CandyEntity_table ORDER BY name ASC")
    LiveData<List<CandyEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CandyEntity> candyEntity);

    @Query("DELETE FROM CandyEntity_table")
    void deleteAll();
}
