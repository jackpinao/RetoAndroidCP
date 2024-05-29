package com.pinao.retoandroidcp.data.repository;

import android.util.Log;

import com.pinao.retoandroidcp.data.database.dao.PremierDao;
import com.pinao.retoandroidcp.data.model.PremierModel;
import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.data.database.entity.PremierEntity;
import com.pinao.retoandroidcp.data.network.PremierService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class PremierRepository {
    private final PremierDao premierDao;
    private final PremierService premierService;

    @Inject
    public PremierRepository(PremierDao premierDao, PremierService premierService) {
        this.premierDao = premierDao;
        this.premierService = premierService;
    }

    public List<PremierItems> getPremiersFromApi() {
        List<PremierItems> items = Collections.emptyList();
        try {
            List<PremierModel> response = premierService.getPremiers();
            if (response != null) {
                items = response.stream().map(PremierModel::toDomain).collect(Collectors.toList());
            }
        } catch (Exception e) {
            Log.e("PremierRepository", "Error fetching premier entities", e);
        }
        return items;
    }

    public List<PremierItems> getAllPremierFromDatabase() {
        List<PremierItems> items = Collections.emptyList();
        try {
            List<PremierEntity> response = premierDao.getAll().getValue();
            if (response != null) {
                items = response.stream().map(PremierEntity::toDomain).collect(Collectors.toList());
            }
        } catch (Exception e) {
            Log.e("PremierRepository", "Error fetching premier entities", e);
        }
        return items;
    }

    public void insertPremiers(List<PremierEntity> items) {
        premierDao.insertAll(items);
    }

    public void clearPremiers() {
        premierDao.deleteAll();
    }


}
