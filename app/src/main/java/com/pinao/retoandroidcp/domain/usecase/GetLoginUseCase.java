package com.pinao.retoandroidcp.domain.usecase;

import com.pinao.retoandroidcp.data.repository.LoginRepository;
import com.pinao.retoandroidcp.domain.model.LoginItems;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class GetLoginUseCase {
    private final LoginRepository loginRepository;
    @Inject
    public GetLoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public List<LoginItems> invoke() throws Exception {
        List<LoginItems> items = loginRepository.getLoginsFromApi();
        if (!items.isEmpty()) {
            loginRepository.clearLogins();
            loginRepository.insertLogins(items.stream().map(LoginItems::toDatabase).collect(Collectors.toList()));
            return items;
        }
        else {
            return loginRepository.getAllLoginFromDatabase();
        }
    }

}
