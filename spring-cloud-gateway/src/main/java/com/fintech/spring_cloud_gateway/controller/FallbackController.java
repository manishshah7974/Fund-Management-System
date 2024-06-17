package com.fintech.spring_cloud_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/fallback/investors")
    public Mono<String> investorServiceFallback() {
        return Mono.just("Investor Service is taking too long to respond or is down. Please try again later.");
    }

    @GetMapping("/fallback/funds")
    public Mono<String> fundServiceFallback() {
        return Mono.just("Fund Service is taking too long to respond or is down. Please try again later.");
    }
}
