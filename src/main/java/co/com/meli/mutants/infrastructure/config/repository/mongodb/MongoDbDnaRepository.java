package co.com.meli.mutants.infrastructure.config.repository.mongodb;

import co.com.meli.mutants.domain.models.DnaEntity;
import co.com.meli.mutants.domain.repositories.IDnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Primary
@RequiredArgsConstructor
public class MongoDbDnaRepository implements IDnaRepository {

    private final SpringDataMongoDnaRepository repository;

    @Override
    public Mono<DnaEntity> saveDna(DnaEntity dnaEntity) {
        return repository.save(dnaEntity);
    }

    @Override
    public Flux<DnaEntity> findAllDnaSequences() {
        return repository.findAll();
    }

}
