package com.pinao.retoandroidcp.di;

import com.pinao.retoandroidcp.data.network.ApiService.ApiService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
            .baseUrl("https://cp-staging.onrender.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
    @Singleton
    @Provides
    public static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
