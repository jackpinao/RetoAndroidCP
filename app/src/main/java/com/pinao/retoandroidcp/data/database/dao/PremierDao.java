package com.pinao.retoandroidcp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pinao.retoandroidcp.data.database.entity.PremierEntity;

import java.util.List;

@Dao
public interface PremierDao {

    @Query("SELECT * FROM PremierEntity_table ORDER BY description ASC")
    LiveData<List<PremierEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PremierEntity> premierEntity);

    @Query("DELETE FROM PremierEntity_table")
    void deleteAll();
}
