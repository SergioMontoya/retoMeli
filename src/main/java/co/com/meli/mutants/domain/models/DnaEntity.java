package co.com.meli.mutants.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "dnacollection")
@EnableMongoAuditing
public class DnaEntity {

    @Id
    private String id;
    @Field
    private String[] dna;
    @Field
    private boolean isMutant;


}
