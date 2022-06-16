package co.com.meli.mutants.domain.services;

import reactor.core.publisher.Mono;

public interface IDnaLaboratory {

    Mono<char[][]> dnaVectorToMatriz(String[] dna, int dnaLength, char[][] dnaMatriz, int index);

    Mono<Boolean> isMutant(String[] dna);

    Mono<Boolean> validateSequences(int dnaLength, char[][] dnaSequenceMatriz);


}
