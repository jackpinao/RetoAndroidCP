package com.pinao.retoandroidcp.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinao.retoandroidcp.domain.usecase.GetLoginUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final GetLoginUseCase getLoginUseCase;
    private final MutableLiveData<String> mText = new MutableLiveData<>();
    @Inject
    public LoginViewModel(GetLoginUseCase getLoginUseCase) {
        this.getLoginUseCase = getLoginUseCase;
    }

    public LiveData<String> getText() {
        return mText;
    }
}