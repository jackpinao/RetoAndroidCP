package com.pinao.retoandroidcp.data.repository;

import android.util.Log;

import com.pinao.retoandroidcp.data.database.entity.CandyEntity;
import com.pinao.retoandroidcp.data.database.entity.PremierEntity;
import com.pinao.retoandroidcp.data.model.CandyModel;
import com.pinao.retoandroidcp.data.model.PremierModel;
import com.pinao.retoandroidcp.data.network.ApiService.ApiService;
import com.pinao.retoandroidcp.data.database.dao.CandyDao;
import com.pinao.retoandroidcp.data.network.CandyService;
import com.pinao.retoandroidcp.domain.model.CandyItems;
import com.pinao.retoandroidcp.domain.model.PremierItems;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class CandyRepository {
    private final CandyDao candyDao;
    private final CandyService candyService;

    @Inject
    public CandyRepository(CandyDao candyDao, CandyService candyService) {
        this.candyDao = candyDao;
        this.candyService = candyService;
    }

    public List<CandyItems> getPremiersFromApi() {
        List<CandyItems> items = Collections.emptyList();
        try {
            List<CandyModel> response = candyService.getCandies();
            if(response != null) {
                items = response.stream().map(CandyModel::toDomain).collect(Collectors.toList());
            }
        } catch (Exception e) {
            Log.e("PremierRepository", "Error fetching premier entities", e);
        }
        return items;
    }
    public List<CandyItems> getAllCandyFromDatabase() {
        List<CandyItems> items = Collections.emptyList();
        try {
            List<CandyEntity> response = candyDao.getAll().getValue();
            if(response != null) {
                items = response.stream().map(CandyEntity::toDomain).collect(Collectors.toList());
            }
        }
        catch (Exception e) {
            Log.e("PremierRepository", "Error fetching premier entities", e);
        }
        return items;
    }
    public List<CandyItems> getCandysFromApi() {
        List<CandyItems> items = Collections.emptyList();
        try {
            List<CandyModel> response = candyService.getCandies();
            if(response != null) {
                items = response.stream().map(CandyModel::toDomain).collect(Collectors.toList());
            }
        } catch (Exception e) {
            Log.e("CandyRepository", "Error fetching candy entities", e);
        }
        return items;
    }

    public void insertCandies(List<CandyEntity> items) {
        candyDao.insertAll(items);
    }
    public void clearCandies() {
        candyDao.deleteAll();
    }

    ///RECYCLEVIEW
}
