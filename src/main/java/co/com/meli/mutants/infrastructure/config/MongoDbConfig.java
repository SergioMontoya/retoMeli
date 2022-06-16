package co.com.meli.mutants.infrastructure.config;

import co.com.meli.mutants.infrastructure.config.repository.mongodb.SpringDataMongoDnaRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@EnableReactiveMongoRepositories(basePackageClasses = SpringDataMongoDnaRepository.class)
public class MongoDbConfig {

}
