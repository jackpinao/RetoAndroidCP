package com.pinao.retoandroidcp.di;

import com.pinao.retoandroidcp.data.network.ApiService.ApiService;
import com.pinao.retoandroidcp.data.database.dao.PremierDao;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {
    @Binds
    public abstract ApiService bindApiService(ApiService apiService);
    @Binds
    public abstract PremierDao bindPremierDao(PremierDao premierDao);

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
            .baseUrl("https://cp-staging.onrender.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
