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

        for (int row = 0; row < dnaLength - 1; row++) {
            if (Boolean.TRUE.equals(isMutantInThisSequence)) {
                break;
            }
            int n = 1 - row;
            for (int c = 0; c < dnaLength - 1; c++) {
                char actualChar = dnaMatriz[row][c];
                char nextCharVert = dnaMatriz[row][c + 1];
                char nextCharHor = dnaMatriz[row + n][c];

                if (validateDiagonal(dnaLength, dnaMatriz, row, c, actualChar)) {
                    isMutantInThisSequence = true;
                    break;
                }
                if (actualChar == nextCharVert || actualChar == nextCharHor) {
                    sequenceCountL++;
                    if (sequenceCountL >= 3) {
                        isMutantInThisSequence = true;
                        break;
                    }
                } else {
                    sequenceCountL = 0;
                }
                n++;
            }
        }
        return Mono.just(isMutantInThisSequence);
    }

    private boolean validateDiagonal(int dnaLength, char[][] dnaSequenceMatriz, int row, int c, char gen) {
        return (row + 3 <= dnaLength - 1 && c + 3 <= dnaLength - 1 && gen == dnaSequenceMatriz[row + 1][c + 1]
                && gen == dnaSequenceMatriz[row + 2][c + 2] && gen == dnaSequenceMatriz[row + 3][c + 3]);
    }


}
