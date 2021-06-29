package com.dh.sp.reactive.routing.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.dh.sp.reactive.routing.handler.RestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingConfig {

  private static final String URL = "/routing";

  @Bean
  public RouterFunction<ServerResponse> route(final RestHandler restHandler) {
    return RouterFunctions.route(
        GET(URL + "/flux").and(accept(MediaType.APPLICATION_JSON)),
        restHandler::flux)
        .andRoute(
            GET(URL + "/mono").and(accept(MediaType.APPLICATION_JSON)),
            restHandler::mono);
  }
}
