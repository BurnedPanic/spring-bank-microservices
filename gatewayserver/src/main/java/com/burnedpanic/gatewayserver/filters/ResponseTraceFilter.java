package com.burnedpanic.gatewayserver.filters;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;


@Configuration
@RequiredArgsConstructor
public class ResponseTraceFilter {

    private final FilterUtility filterUtility;

    Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

            String correlationId = filterUtility.getCorrelationId(requestHeaders);

            logger.debug("Updated the correlation id to the outbound headers: {}", correlationId);

            exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
        }));
    }

}
