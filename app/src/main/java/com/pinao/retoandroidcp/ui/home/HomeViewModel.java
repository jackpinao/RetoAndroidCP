package com.pinao.retoandroidcp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.domain.usecase.GetPremierUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final GetPremierUseCase getPremierUseCase;
    private final MutableLiveData<List<String>> description = new MutableLiveData<>();
    private final MutableLiveData<List<String>> imageUrls = new MutableLiveData<>();

    @Inject
    public HomeViewModel(GetPremierUseCase getPremierUseCase) {
        this.getPremierUseCase = getPremierUseCase;
    }

    public void onCreate() {
        Single.fromCallable(getPremierUseCase::invoke)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateLiveData, this::logError);
    }

    private void updateLiveData(List<PremierItems> premierItems) {
        if (!premierItems.isEmpty()) {
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            for (PremierItems item : premierItems) {
                list.add(item.getImage());
                list2.add(item.getDescription());
            }
            imageUrls.setValue(list);
            description.setValue(list2);
        }
    }

    private void logError(Throwable error) {
        Log.e("HomeViewModel", "Error in OnCreate", error);
    }

    public LiveData<List<String>> getImageUrls() {
        return imageUrls;
    }

    public LiveData<List<String>> getDescriptionUrls() {
        return description;
    }

}