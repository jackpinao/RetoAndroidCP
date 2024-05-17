package com.pinao.retoandroidcp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pinao.retoandroidcp.data.database.dao.CandyDao;
import com.pinao.retoandroidcp.data.database.dao.PremierDao;
import com.pinao.retoandroidcp.data.database.dao.LoginDao;
import com.pinao.retoandroidcp.data.database.entity.CandyEntity;
import com.pinao.retoandroidcp.data.database.entity.PremierEntity;
import com.pinao.retoandroidcp.data.database.entity.LoginEntity;

@Database(entities = {LoginEntity.class, PremierEntity.class, CandyEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoginDao userDao();
    public abstract PremierDao premierDao();
    public abstract CandyDao candyDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "retoandroidcp-db").build();
        }
        return INSTANCE;
    }
}

