package co.com.meli.mutants.domain.services;

import co.com.meli.mutants.application.request.DnaDataRequest;
import co.com.meli.mutants.domain.models.DnaEntity;
import co.com.meli.mutants.domain.repositories.IDnaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @InjectMocks
    private MutantService mutantService;

    @Mock
    private IDnaRepository repository;

    @Spy
    private DnaLaboratory dnaLaboratory;

    @Test
    public void validateIsMutant() throws IOException {

        String[] dna = {"ACGAGT",
                "CCCCGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"};
        DnaDataRequest dnaDataRequest = new DnaDataRequest();
        dnaDataRequest.setDna(dna);
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setMutant(true);
        dnaEntity.setDna(dna);
        dnaEntity.setId(String.valueOf(Arrays.hashCode(dnaDataRequest.getDna())));

        Mockito.when(repository.saveDna(dnaEntity)).thenReturn(Mono.just(dnaEntity));
        Mockito.when(dnaLaboratory.isMutant(dna)).thenReturn(Mono.just(dnaEntity.isMutant()));
        Mockito.when(dnaLaboratory.isMutant(dna)).thenReturn(Mono.just(dnaEntity.isMutant()));

        var response = mutantService.validateIsMutant(dnaDataRequest);
        assertNotNull(response.block());
    }


    @Test
    public void validateRequest() throws IOException {

        String[] dna = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAAG", "ACCCTA", "TCACTG"};
        DnaDataRequest dnaDataRequest = new DnaDataRequest();
        dnaDataRequest.setDna(dna);
        DnaEntity dnaEntity = new DnaEntity();
        var response = mutantService.validateRequest(dnaDataRequest);
        assertEquals(Boolean.FALSE, response.block());
    }

    @Test
    public void validateRequestWithErrors() throws IOException {

        String[] dna = {"ATGCGX", "CGGTGC", "TTATGT", "AGAAAG", "ACCCTA", "TCACTG"};
        DnaDataRequest dnaDataRequest = new DnaDataRequest();
        dnaDataRequest.setDna(dna);
        DnaEntity dnaEntity = new DnaEntity();
        var response = mutantService.validateRequest(dnaDataRequest);
        assertEquals(Boolean.TRUE, response.block());
    }

    @Test
    public void getDnaStats() {
        String[] dna = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAAG", "ACCCTA", "TCACTG"};
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setMutant(true);
        dnaEntity.setDna(dna);
        dnaEntity.setId(String.valueOf(Arrays.hashCode(dna)));
        DnaEntity dnaEntity2 = new DnaEntity();
        dnaEntity2.setMutant(true);
        dnaEntity2.setDna(dna);
        dnaEntity2.setId(String.valueOf(Arrays.hashCode(dna)));
        DnaEntity dnaEntity3 = new DnaEntity();
        dnaEntity3.setMutant(false);
        dnaEntity3.setDna(dna);
        dnaEntity3.setId(String.valueOf(Arrays.hashCode(dna)));

        Mockito.when(repository.findAllDnaSequences()).thenReturn(Flux.just(dnaEntity, dnaEntity2, dnaEntity3));
        var responsex = mutantService.getDnaStats();
        assertNotNull(responsex.block());
    }


}