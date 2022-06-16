package co.com.meli.mutants.domain.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class DnaLaboratory implements IDnaLaboratory {

    @Override
    public Mono<char[][]> dnaVectorToMatriz(String[] dna, int dnaLength, char[][] dnaMatriz, int index) {
        if (index >= dnaLength) {
            return Mono.just(dnaMatriz);
        } else {
            dnaMatriz[index] = dna[index].toUpperCase().toCharArray();
            return dnaVectorToMatriz(dna, dnaLength, dnaMatriz, index + 1);
        }
    }

    @Override
    public Mono<Boolean> isMutant(String[] dna) {
        int dnaLength = dna.length;
        char[][] dnaMatriz = new char[dnaLength][dnaLength];
        return dnaVectorToMatriz(dna, dnaLength, dnaMatriz, 0)
                .flatMap(chars -> validateSequences(dnaLength, dnaMatriz))
                .map(Boolean::valueOf);
    }


    public Mono<Boolean> validateSequences(int dnaLength, char[][] dnaMatriz) {
        boolean isMutantInThisSequence = false;
        int sequenceCountL = 0;
        int row = 0;
        int col = 0;
        int maxIteration = dnaLength - 1;

        while ((row < (maxIteration)) && (!isMutantInThisSequence)) {

            while ((col < (maxIteration)) && (!isMutantInThisSequence)) {

                char actualChar = dnaMatriz[row][col];
                char nextCharVert = dnaMatriz[row][col + 1];
                char nextCharHor = dnaMatriz[row + 1][col];

                if (validateDiagonal(dnaLength, dnaMatriz, row, col, actualChar)) {
                    isMutantInThisSequence = true;
                }
                if (actualChar == nextCharVert || actualChar == nextCharHor) {
                    sequenceCountL++;
                    if (sequenceCountL >= 3) {
                        isMutantInThisSequence = true;
                    }
                } else {
                    sequenceCountL = 0;
                }
                col++;
            }
            row++;
        }
        return Mono.just(isMutantInThisSequence);
    }

    private boolean validateDiagonal(int dnaLength, char[][] dnaSequenceMatriz, int row, int c, char gen) {
        return (row + 3 <= dnaLength - 1 && c + 3 <= dnaLength - 1 && gen == dnaSequenceMatriz[row + 1][c + 1]
                && gen == dnaSequenceMatriz[row + 2][c + 2] && gen == dnaSequenceMatriz[row + 3][c + 3]);
    }


}
