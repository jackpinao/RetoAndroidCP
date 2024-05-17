package com.pinao.retoandroidcp.domain.usecase;

import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.data.repository.PremierRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class GetPremierUseCase {
    private final PremierRepository premierRepository;
    @Inject
    public GetPremierUseCase(PremierRepository premierRepository) {
        this.premierRepository = premierRepository;
    }
    public List<PremierItems> invoke() throws Exception {
        List<PremierItems> items = premierRepository.getPremiersFromApi();
        if (!items.isEmpty()) {
            premierRepository.clearPremiers();
            premierRepository.insertPremiers(items.stream().map(PremierItems::toDatabase).collect(Collectors.toList()));
            return items;
        }
        else {
            return premierRepository.getAllPremierFromDatabase();
        }
    }
}
