package org.dmnvch.bytescale.service;

import lombok.RequiredArgsConstructor;
import org.dmnvch.bytescale.client.UcdnApiClient;
import org.dmnvch.bytescale.model.JobResponseDto;

@RequiredArgsConstructor
public class UpcdnService {

    private static final String AUTH_HEADER_NAME = "Bearer ";

    private final String publicKey;
    private final String appId;
    private final UcdnApiClient upcdnApiClient;

    public JobResponseDto getAntivirusCheck(final String filePath) {
        final String authHeader = AUTH_HEADER_NAME + publicKey;
        return upcdnApiClient.getAntivirusCheck(authHeader, filePath, appId).blockingGet();
    }
}
