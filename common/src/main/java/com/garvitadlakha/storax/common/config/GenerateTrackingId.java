package com.garvitadlakha.storax.common.config;

import org.springframework.stereotype.Component;

@Component
public class GenerateTrackingId {

    public static String generateTrackingId() {
        return java.util.UUID.randomUUID().toString();
    }
}
