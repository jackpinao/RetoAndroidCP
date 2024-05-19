package com.pinao.retoandroidcp.ui.home;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.domain.usecase.GetPremierUseCase;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final GetPremierUseCase getPremierUseCase;
    private final MutableLiveData<String> image = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();

    @Inject
    public HomeViewModel(GetPremierUseCase getPremierUseCase) {
        this.getPremierUseCase = getPremierUseCase;
    }

    public void onCreate() {
        Single.fromCallable(() -> getPremierUseCase.invoke())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateLiveData, this::logError);
    }
    private void updateLiveData(List<PremierItems> premierItems) {
        if (!premierItems.isEmpty()) {
            PremierItems firstItem = premierItems.get(0);
            image.setValue(firstItem.getImage());
            description.setValue(firstItem.getDescription());
        }
    }
    private void logError(Throwable error) {
        Log.e("HomeViewModel", "Error in OnCreate", error);
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