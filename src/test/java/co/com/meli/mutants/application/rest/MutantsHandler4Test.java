package co.com.meli.mutants.application.rest;

import co.com.meli.mutants.application.request.DnaDataRequest;
import co.com.meli.mutants.domain.models.DnaEntity;
import co.com.meli.mutants.domain.services.MutantService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MutantRouterConfig.class, MutantsHandler.class})
@WebFluxTest
public class MutantsHandler4Test {
    @Autowired
    private ApplicationContext context;

    @MockBean
    private MutantService mutantService;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void handlerMutantTest() {
        String[] dna = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAAG", "ACCCTA", "TCACTG"};
        DnaDataRequest dnaDataRequest = new DnaDataRequest();
        dnaDataRequest.setDna(dna);
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setMutant(true);
        dnaEntity.setDna(dna);
        dnaEntity.setId(String.valueOf(Arrays.hashCode(dnaDataRequest.getDna())));

        Mockito.when(mutantService.validateRequest(dnaDataRequest)).thenReturn(Mono.just(true));
        Mockito.when(mutantService.validateIsMutant(dnaDataRequest)).thenReturn(Mono.just(true));

        webTestClient.post()
                .uri("/mutant")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> {
                            Assertions.assertThat(response).isEqualTo("Magneto te la bienvenida a los XMEN!");

                        }
                );


    }
}