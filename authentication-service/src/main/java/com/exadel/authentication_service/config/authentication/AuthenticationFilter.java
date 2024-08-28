package com.exadel.authentication_service.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return Mono.error(new RuntimeException("Missing authorization header"));
            }

            String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

            return webClientBuilder.build()
                    .post()
                    .uri(config.getAuthServiceUri())
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new RuntimeException("Unauthorized")))
                    .bodyToMono(Void.class)
                    .then(chain.filter(exchange));
        };
    }

    public static class Config {
        private String authServiceUri;

        public String getAuthServiceUri() {
            return authServiceUri;
        }

        public void setAuthServiceUri(String authServiceUri) {
            this.authServiceUri = authServiceUri;
        }
    }
}
