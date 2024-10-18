package com.exadel.gateway.config.routing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.addRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Slf4j
@Configuration
public class CoreRoutingConfig {

    @Value("${app.gateway.core-service.target-url}")
    private String targetUrl;

    @Bean
    public RouterFunction<ServerResponse> handleCoreRoutes() {
        log.debug("Routing request to core-service: " + targetUrl);

        return route("core-service")
                .route(
                    path("/core-service/**"),
                    http(targetUrl)
                )
                .before(addRequestHeader("X-Authenticated-User", "validated"))
                .before(stripPrefix(1))
            .build();
    }
}
