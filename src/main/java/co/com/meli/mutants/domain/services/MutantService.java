package co.com.meli.mutants.domain.services;

import co.com.meli.mutants.application.request.DnaDataRequest;
import co.com.meli.mutants.application.response.DnaStatsResponse;
import co.com.meli.mutants.domain.models.DnaEntity;
import co.com.meli.mutants.domain.repositories.IDnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class MutantService implements IMutantService {

    private final IDnaLaboratory dnaLaboratory;
    private final IDnaRepository repository;

    @Override
    public Mono<Object> validateIsMutant(DnaDataRequest dnaDataRequest) {
        var dnaEntity = new DnaEntity();
        return dnaLaboratory.isMutant(dnaDataRequest.getDna())
                .flatMap(c -> {
                    dnaEntity.setDna(dnaDataRequest.getDna());
                    dnaEntity.setId(String.valueOf(Arrays.hashCode(dnaDataRequest.getDna())));
                    dnaEntity.setMutant(c);
                    return repository
                            .saveDna(dnaEntity);
                }).map(DnaEntity::isMutant);
    }

    @Override
    public Mono<DnaStatsResponse> getDnaStats() {
        return repository.findAllDnaSequences()
                .groupBy(DnaEntity::isMutant)
                .flatMap(group -> group
                        .count()
                        .map(count -> {
                            var map = new HashMap<>();
                            map.put(group.key().toString(), count);
                            return map;
                        })
                ).collectList().map(o -> {
                    var dnaStats = new DnaStatsResponse();
                    o.forEach(d -> {
                        if (d.containsKey("true")) {
                            dnaStats.setCount_mutant_dna((Long) new ArrayList<>(d.values()).get(0));
                        } else {
                            dnaStats.setCount_human_dna((Long) new ArrayList<>(d.values()).get(0));
                        }
                    });
                    dnaStats.setRatio((dnaStats.getCount_mutant_dna()
                            / (double) (dnaStats.getCount_human_dna() + dnaStats.getCount_mutant_dna())));
                    return dnaStats;
                });
    }

    @Override
    public Mono<Boolean> validateRequest(DnaDataRequest dnaDataRequest) {
        Pattern pattern = Pattern.compile("[acgt]+", Pattern.CASE_INSENSITIVE);
        var dnaReq = Mono.just(dnaDataRequest);
        return dnaReq.map(d -> {
            boolean errors = Boolean.FALSE;
            int length = d.getDna().length;
            for (String s : d.getDna()) {
                if (length != s.length() || !pattern.matcher(s).matches()) {
                    errors = Boolean.TRUE;
                    break;
                }
            }
            return errors;
        }).map(Boolean::valueOf);

    }


}



