package co.com.meli.mutants.infrastructure.config.repository.mongodb;

import co.com.meli.mutants.domain.models.DnaEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SpringDataMongoDnaRepository extends ReactiveMongoRepository<DnaEntity, String> {

    Flux<DnaEntity> findAll();

}
