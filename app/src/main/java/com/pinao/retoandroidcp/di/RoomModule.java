package com.pinao.retoandroidcp.di;

import android.content.Context;

import androidx.room.Room;

import com.pinao.retoandroidcp.data.database.dao.CandyDao;
import com.pinao.retoandroidcp.data.database.dao.LoginDao;
import com.pinao.retoandroidcp.data.database.dao.PremierDao;
import com.pinao.retoandroidcp.data.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RoomModule {

    private static final String DATABASE_NAME = "database";

    @Singleton
    @Provides
    public static AppDatabase provideRoom(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
    //provide the dao
    @Singleton
    @Provides
    public static PremierDao providePremierDao(AppDatabase appDatabase) {
        return appDatabase.premierDao();
    }
    @Singleton
    @Provides
    public static CandyDao provideCandyDao(AppDatabase appDatabase) {
        return appDatabase.candyDao();
    }
    @Singleton
    @Provides
    public static LoginDao provideLoginDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }
}
