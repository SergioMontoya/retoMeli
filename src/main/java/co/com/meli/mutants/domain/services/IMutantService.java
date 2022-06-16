package co.com.meli.mutants.domain.services;

import co.com.meli.mutants.application.request.DnaDataRequest;
import co.com.meli.mutants.application.response.DnaStatsResponse;
import reactor.core.publisher.Mono;


public interface IMutantService {

    Mono<Object> validateIsMutant(DnaDataRequest dnaDataRequest);

    Mono<Boolean> validateRequest(DnaDataRequest dnaDataRequest);

    Mono<DnaStatsResponse> getDnaStats();


}
