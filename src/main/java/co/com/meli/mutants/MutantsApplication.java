package co.com.meli.mutants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class MutantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutantsApplication.class, args);
    }

}
