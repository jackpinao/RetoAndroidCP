package com.pinao.retoandroidcp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.domain.usecase.GetPremierUseCase;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final GetPremierUseCase getPremierUseCase;

    private final MutableLiveData<PremierItems> premierItems = new MutableLiveData<>();
    private final MutableLiveData<String> image = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();

    @Inject
    public HomeViewModel(GetPremierUseCase getPremierUseCase) {
        this.getPremierUseCase = getPremierUseCase;
    }

    public void onCreate() {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        return getPremierUseCase.invoke();
                    } catch (Exception e) {
                        Log.e("HomeViewModel", "onCreate: ", e);
                        return Collections.emptyList();
                    }
                }, Executors.newSingleThreadExecutor())
                .thenAccept(premiers -> {
                    if (!premiers.isEmpty()) {
  //                      premierItems.postValue((PremierItems) premiers.get(0));
                        image.postValue(premiers.get(0).toString());
                        description.postValue(premiers.get(0).toString());
                    }
                });
    }

    public LiveData<String> getImage() {
        return image;
    }
    public LiveData<String> getDescription() {
        return description;
    }

//    public LiveData<String> getText() {
//        return mText;
//    }
//
//    public LiveData<List<PremierEntity>> getPremiers() {
//        return premierEntities;
//    }

}