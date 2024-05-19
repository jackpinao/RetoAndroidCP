package com.pinao.retoandroidcp.data.repository;

import android.util.Log;

import com.pinao.retoandroidcp.data.database.dao.LoginDao;
import com.pinao.retoandroidcp.data.database.entity.LoginEntity;
import com.pinao.retoandroidcp.data.model.LoginModel;
import com.pinao.retoandroidcp.data.network.LoginService;
import com.pinao.retoandroidcp.domain.model.LoginItems;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class LoginRepository {
    private final LoginDao loginDao;
    private final LoginService loginService;

    @Inject
    public LoginRepository(LoginDao loginDao, LoginService loginService) {
        this.loginDao = loginDao;
        this.loginService = loginService;
    }

    public List<LoginItems> getLoginsFromApi() {
        List<LoginItems> items = Collections.emptyList();
        try {
            List<LoginModel> response = loginService.getLogins();
            if(response != null) {
                items = response.stream().map(LoginModel::toDomain).collect(Collectors.toList());
            }
        } catch (Exception e) {
            Log.e("LoginRepository", "Error fetching login entities", e);
        }
        return items;
    }

    public List<LoginItems> getAllLoginFromDatabase() {
        List<LoginItems> items = Collections.emptyList();
        try {
            List<LoginEntity> response = loginDao.getAll( "test","test2").getValue();
            if(response != null) {
                items = response.stream().map(LoginEntity::toDomain).collect(Collectors.toList());
            }
        }
        catch (Exception e) {
            Log.e("LoginRepository", "Error fetching login entities", e);
        }
        return items;
    }

    public void insertLogins(List<LoginEntity> items) {
        loginDao.insertAll(items);
    }

    public void clearLogins() {
        loginDao.deleteAll();
    }


}
