package co.com.meli.mutants.application.rest;


import co.com.meli.mutants.application.request.DnaDataRequest;
import co.com.meli.mutants.domain.services.IMutantService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class MutantsHandler {
    private final Validator validator;
    private final IMutantService mutantService;

    @SneakyThrows
    public Mono<ServerResponse> validateDna(ServerRequest request) {
        var dnaDataRequest = request.bodyToMono(DnaDataRequest.class);
        return dnaDataRequest.flatMap(dnaReq -> {
                    var serviceRequest = new DnaDataRequest();
                    serviceRequest.setDna(dnaReq.getDna());
                    return mutantService.validateRequest(serviceRequest)
                            .flatMap(clr -> {
                                if (Boolean.FALSE.equals(clr)) {
                                    return mutantService.validateIsMutant(serviceRequest).flatMap(aBoolean -> {
                                        if (Boolean.FALSE.equals(aBoolean)) {
                                            return ServerResponse.status(403).body(BodyInserters
                                                    .fromValue("Eres Humano, no puedes ser parte de los XMEN!"));
                                        } else {
                                            return ServerResponse.ok().body(BodyInserters
                                                    .fromValue("Magneto te la bienvenida a los XMEN!"));
                                        }
                                    });
                                } else {
                                    return ServerResponse.status(400).body(BodyInserters
                                            .fromValue("DNA SUMINISTRADO ES INCORRECTO"));
                                }
                            });
                }
        );

    }

    @SneakyThrows
    public Mono<ServerResponse> getStats(ServerRequest request) {
        return mutantService.getDnaStats()
                .flatMap(clr -> ServerResponse.ok().body(BodyInserters.fromValue(clr))
                );
    }


}