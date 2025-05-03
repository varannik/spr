package com.voiceassistant.gateway.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.reactive.server.SecurityWebTestClientConfigurer;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenAccessPublicEndpoint_thenOk() {
        webTestClient
                .get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void whenAccessProtectedEndpoint_thenUnauthorized() {
        webTestClient
                .get()
                .uri("/api/voice/status")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    public void whenAccessAuthEndpoint_thenOk() {
        webTestClient
                .get()
                .uri("/api/auth/login")
                .exchange()
                .expectStatus()
                .isOk();
    }
} 