package co.com.meli.mutants.application.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public final class DnaStatsResponse {
    private Long count_mutant_dna;
    private Long count_human_dna;
    private Double ratio;


}
