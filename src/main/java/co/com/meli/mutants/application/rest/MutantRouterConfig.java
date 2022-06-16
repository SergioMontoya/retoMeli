package co.com.meli.mutants.application.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class MutantRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> mutantsRoutes(MutantsHandler handler) {
        return RouterFunctions.route(POST("/mutant"), handler::validateDna);
    }

    @Bean
    public RouterFunction<ServerResponse> statsRoutes(MutantsHandler handler) {
        return RouterFunctions.route(GET("/stats"), handler::getStats);
    }


}
