package org.dmnvch.bytescale.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.dmnvch.bytescale.client.ByteScaleApiClient;
import org.dmnvch.bytescale.model.ApiResponse;
import org.dmnvch.bytescale.model.JobResponseDto;
import org.dmnvch.bytescale.model.UploadFileResponseDto;
import retrofit2.Response;

import java.io.File;

@RequiredArgsConstructor
public class ByteScaleService {

    private static final String AUTH_HEADER_NAME = "Bearer ";

    private final String publicKey;
    private final String secretKey;
    private final String appId;
    private final ByteScaleApiClient byteScaleApiClient;

    public ApiResponse<UploadFileResponseDto> uploadFile(final File file) {
        final String authHeader = AUTH_HEADER_NAME + publicKey;
        final RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
        final MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        return uploadFile(authHeader, filePart);
    }

    public ApiResponse<UploadFileResponseDto> uploadFile(final MultipartBody.Part filePart) {
        final String authHeader = AUTH_HEADER_NAME + publicKey;
        return uploadFile(authHeader, filePart);
    }

    private ApiResponse<UploadFileResponseDto> uploadFile(String authHeader, MultipartBody.Part filePart) {
        try {
            retrofit2.Call<UploadFileResponseDto> call = byteScaleApiClient.uploadFile(authHeader, filePart, appId);
            Response<UploadFileResponseDto> response = call.execute();

            if (response.isSuccessful()) {
                return ApiResponse.success(response.body());
            } else {
                if (response.errorBody() != null) {
                    String errorBodyString = response.errorBody().string();
                    UploadFileResponseDto errorResponse = new ObjectMapper().readValue(errorBodyString, UploadFileResponseDto.class);
                    return ApiResponse.failure(errorResponse);
                } else {
                    return ApiResponse.failure("Upload failed with unknown error. Response code: " + response.code());
                }
            }
        } catch (Exception e) {
            return ApiResponse.failure("Upload failed: " + e.getMessage());
        }
    }


    public boolean deleteFileUpload(final String filePath) {
        final String authHeader = AUTH_HEADER_NAME + secretKey;
        byteScaleApiClient.deleteFileUpload(authHeader, appId, filePath);
        return true;
    }

    public JobResponseDto getAntivirusCheck(final String filePath) {
        final String authHeader = AUTH_HEADER_NAME + publicKey;
        final String url = String.format("https://upcdn.io/%s/antivirus/%s", appId, filePath);
        return byteScaleApiClient.getAntivirusCheck(authHeader, url).blockingGet();
    }
}
