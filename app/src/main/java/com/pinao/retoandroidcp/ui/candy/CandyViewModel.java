package com.pinao.retoandroidcp.ui.candy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinao.retoandroidcp.domain.usecase.GetCandyUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CandyViewModel extends ViewModel {

    private final GetCandyUseCase getCandyUseCase;
    private final MutableLiveData<String> mText = new MutableLiveData<>();


    @Inject
    public CandyViewModel(GetCandyUseCase getCandyUseCase) {
        this.getCandyUseCase = getCandyUseCase;
    }

    public LiveData<String> getText() {
        return mText;
    }
}