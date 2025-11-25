package org.example.mutantes.service;

import lombok.RequiredArgsConstructor;
import org.example.mutantes.DTO.StatsResponse;
import org.example.mutantes.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsResponse getStats() {
        long mutants = repository.countByIsMutant(true);
        long humans = repository.countByIsMutant(false);
        double ratio = humans == 0 ? 0 : (double) mutants / humans;

        return StatsResponse.builder()
                .countMutantDna(mutants)
                .countHumanDna(humans)
                .ratio(ratio)
                .build();
    }
}
