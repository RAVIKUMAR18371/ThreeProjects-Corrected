package com.API.Gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/employee")
    public Mono<ResponseEntity<String>> employeeFallback() {

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Employee Service is currently unavailable. Please try again later.")
        );

    }

    @GetMapping("/address")
    public Mono<ResponseEntity<String>> addressFallback() {

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Address Service is currently unavailable. Please try again later.")
        );

    }

}