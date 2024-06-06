package com.pinao.retoandroidcp.ui.candy;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinao.retoandroidcp.domain.model.CandyItems;
import com.pinao.retoandroidcp.domain.usecase.GetCandyUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class CandyViewModel extends ViewModel {

    private final GetCandyUseCase getCandyUseCase;
    private final MutableLiveData<List<CandyItems>> items = new MutableLiveData<>();
    private final MutableLiveData<List<String>> name = new MutableLiveData<>();
    private final MutableLiveData<List<String>> description = new MutableLiveData<>();
    private final MutableLiveData<List<String>> price = new MutableLiveData<>();

    @Inject
    public CandyViewModel(GetCandyUseCase getCandyUseCase) {
        this.getCandyUseCase = getCandyUseCase;
    }

    public void onCreate() {
//        try {
//            //getCandyUseCase.invoke();
//            updateLiveData(getCandyUseCase.invoke());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Single.fromCallable(getCandyUseCase::invoke)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateLiveData, this::logError);
    }

    private void updateLiveData(List<CandyItems> invoke) {
        if (!invoke.isEmpty()){
            items.setValue(invoke);
        }
//        if (!invoke.isEmpty()) {
//            List<String> nameList = new ArrayList<>();
//            List<String> descriptionList = new ArrayList<>();
//            List<String> priceList = new ArrayList<>();
//            for (CandyItems item : invoke) {
//                nameList.add(item.getName());
//                descriptionList.add(item.getDescription());
//                priceList.add(item.getPrice());
//            }
//            name.setValue(nameList);
//            description.setValue(descriptionList);
//            price.setValue(priceList);
//        }
    }
    private void logError(Throwable error) {
        Log.e("CandyViewModel", "Error in OnCreate", error);
    }

//    private void uddateLiveData(List<CandyItems> candyItems){
//        if (!candyItems.isEmpty()){
//            items.setValue(candyItems);
//        }
//    }

    public LiveData<List<CandyItems>> getItems() {
        return items;
    }

    public LiveData<List<String>> getName(){
        return name;
    }
    public LiveData<List<String>> getText() {
        return description;
    }
    public LiveData<List<String>> getPrice() {
        return price;
    }
}