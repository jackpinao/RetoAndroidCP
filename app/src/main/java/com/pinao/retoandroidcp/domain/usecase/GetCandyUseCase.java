package com.pinao.retoandroidcp.domain.usecase;

import com.pinao.retoandroidcp.data.repository.CandyRepository;
import com.pinao.retoandroidcp.domain.model.CandyItems;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class GetCandyUseCase {
    private final CandyRepository candyRepository;
    @Inject
    public GetCandyUseCase(CandyRepository candyRepository) {
        this.candyRepository = candyRepository;
    }
    public List<CandyItems> invoke() throws Exception {
        List<CandyItems> items = candyRepository.getCandysFromApi();
        if (!items.isEmpty()) {
            candyRepository.clearCandies();
            candyRepository.insertCandies(items.stream().map(CandyItems::toDatabase).collect(Collectors.toList()));
            return items;
        }
        else {
            return candyRepository.getAllCandyFromDatabase();
        }
    }
}
