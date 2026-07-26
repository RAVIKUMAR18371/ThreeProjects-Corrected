package com.API.Gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    // Public APIs (No JWT Required)
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/generate-token",
            "/auth/validate-token",
            "/eureka"
    );

    // Returns true if request is secured
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));
}