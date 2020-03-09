package com.rusev.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * Custom filter that adds timestamps to the request header for downstream systems.
 */
@Component
public class PreGatewayFilter extends AbstractGatewayFilterFactory<PreGatewayFilter.Config> {

    public PreGatewayFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            builder.headers(httpHeaders -> httpHeaders.add("timestamp", String.valueOf(System.nanoTime())));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    public static class Config {
        //Put the configuration properties for the filter here
    }
}
