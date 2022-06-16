package co.com.meli.mutants.domain.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class DnaLaboratoryTest {


    @InjectMocks
    private DnaLaboratory dnaLaboratory;


    @Test
    public void isMutantTrue() {
        String[] dna = {"AAGAGT", "CAGTGC", "TTATGT", "AGAAGG", "CACCTA", "TCACTG"};
        Mono<Boolean> response = dnaLaboratory.isMutant(dna);
        assertEquals(Boolean.TRUE, response.block());

    }

    @Test
    public void isMutantFalse() {
        String[] dna = {"ACGAGT",
                "CAGTGC",
                "ACGAGT",
                "AGAAGG",
                "ACGAGT",
                "TCACTG"};
        Mono<Boolean> response = dnaLaboratory.isMutant(dna);
        assertEquals(Boolean.FALSE, response.block());

    }

    @Test
    public void dnaVectorToMatriz() {
        String[] dna = {"ACGAGT",
                "CAGTGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"};

        int dnaLength = dna.length;
        char[][] dnaMatriz = new char[dnaLength][dnaLength];
        Mono<char[][]> response = dnaLaboratory.dnaVectorToMatriz(dna, dnaLength, dnaMatriz, 0);

        assertNotNull(response.block());
//        assertEquals(Boolean.TRUE, response.block());

    }

    @Test
    public void ValidateSequenceLineal() {
        String[] dna = {"ACGAGT",
                "CCCCGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"};

        int dnaLength = dna.length;
        char[][] dnaMatriz = new char[dnaLength][dnaLength];
        Mono<char[][]> matriz = dnaLaboratory.dnaVectorToMatriz(dna, dnaLength, dnaMatriz, 0);
        var response = dnaLaboratory.validateSequences(dnaLength, matriz.block());
        assertNotNull(response.block());

    }

    @Test
    public void ValidateSequenceDiagonal() {
        String[] dna = {"ACGAGT",
                "ACCCGC",
                "TACTGT",
                "AGAAGG",
                "CACATA",
                "TCACTG"};

        int dnaLength = dna.length;
        char[][] dnaMatriz = new char[dnaLength][dnaLength];
        Mono<char[][]> matriz = dnaLaboratory.dnaVectorToMatriz(dna, dnaLength, dnaMatriz, 0);
        var response = dnaLaboratory.validateSequences(dnaLength, matriz.block());
        assertNotNull(response.block());

    }
//

}