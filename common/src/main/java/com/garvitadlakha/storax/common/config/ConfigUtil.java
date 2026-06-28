package com.garvitadlakha.storax.common.config;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConfigUtil {

    public String getTrackingId(
            final Map<String, String> headers) {
        String trackingId = headers.get("trackingId");
        if (ObjectUtils.isEmpty(trackingId)) {
            trackingId = GenerateTrackingId.generateTrackingId();
        }
        log.atInfo().addKeyValue("logFormat", "v2")
                .log("trackingId {}", trackingId);
        return trackingId;
    }

}
