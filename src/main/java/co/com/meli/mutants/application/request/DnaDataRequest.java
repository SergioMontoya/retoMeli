package co.com.meli.mutants.application.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public final class DnaDataRequest {

    private @NotBlank String[] dna;

}
