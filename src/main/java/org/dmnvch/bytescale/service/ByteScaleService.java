package org.dmnvch.bytescale.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.dmnvch.bytescale.client.ByteScaleApiClient;
import org.dmnvch.bytescale.model.JobResponseDto;
import org.dmnvch.bytescale.model.UploadFileResponseDto;

import java.io.File;

@RequiredArgsConstructor
public class ByteScaleService {

    private static final String AUTH_HEADER_NAME = "Bearer ";

    private final String publicKey;
    private final String secretKey;
    private final String appId;
    private final ByteScaleApiClient byteScaleApiClient;


    public UploadFileResponseDto uploadFile(final File file) {
        try {
            final String authHeader = AUTH_HEADER_NAME + publicKey;
            final RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
            final MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

            return byteScaleApiClient.uploadFile(authHeader, filePart, appId).blockingGet();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UploadFileResponseDto uploadFile(final MultipartBody.Part filePart) {
        try {
            final String authHeader = AUTH_HEADER_NAME + publicKey;
            return byteScaleApiClient.uploadFile(authHeader, filePart, appId).blockingGet();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFileUpload(final String filePath) {
        final String authHeader = AUTH_HEADER_NAME + secretKey;
        byteScaleApiClient.deleteFileUpload(authHeader, filePath, appId);
        return true;
    }

    public JobResponseDto getAntivirusCheck(final String filePath) {
        final String authHeader = AUTH_HEADER_NAME + publicKey;
        final String url = String.format("https://upcdn.io/%s/antivirus/%s", appId, filePath);
        return byteScaleApiClient.getAntivirusCheck(authHeader, url).blockingGet();
    }
}
