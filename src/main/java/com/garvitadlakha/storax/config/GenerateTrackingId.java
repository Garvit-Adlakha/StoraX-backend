package com.garvitadlakha.storax.config;

public class GenerateTrackingId {
    public static String generateTrackingId() {
        return java.util.UUID.randomUUID().toString();
    }
}
