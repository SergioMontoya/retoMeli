package co.com.meli.mutants.domain.repositories;

import co.com.meli.mutants.domain.models.DnaEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDnaRepository {

    Mono<DnaEntity> saveDna(DnaEntity dnaEntity);

    Flux<DnaEntity> findAllDnaSequences();

}
