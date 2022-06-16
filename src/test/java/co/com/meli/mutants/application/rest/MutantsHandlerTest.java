package co.com.meli.mutants.application.rest;

import co.com.meli.mutants.application.request.DnaDataRequest;
import co.com.meli.mutants.domain.models.DnaEntity;
import co.com.meli.mutants.domain.services.MutantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.SERVER;
import static org.springframework.http.HttpHeaders.readOnlyHttpHeaders;


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MutantsHandler.class)
@AutoConfigureMockMvc
public class MutantsHandlerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    //    private static final RESPONSE_JSON ="{ \"count_mutant_dna\":\"21,\" "count_human_dna\:\"8,\   "ratio":0.7241379310344828\}" " ;
    @InjectMocks
    private MutantService mutantService;


    @Mock
    private WebClient webClientMock;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;
    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;
    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateDnaTrue() throws Exception {
        String[] dna = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAAG", "ACCCTA", "TCACTG"};
        DnaDataRequest dnaDataRequest = new DnaDataRequest();
        dnaDataRequest.setDna(dna);
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setMutant(true);
        dnaEntity.setDna(dna);
        dnaEntity.setId(String.valueOf(Arrays.hashCode(dnaDataRequest.getDna())));

        Mockito.when(mutantService.validateRequest(dnaDataRequest)).thenReturn(Mono.just(true));



    }
}