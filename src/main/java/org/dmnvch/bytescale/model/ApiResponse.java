package org.dmnvch.bytescale.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private T data;
    private String errorMessage;
    private final boolean isSuccess;

    public ApiResponse(T data, String errorMessage, boolean isSuccess) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.isSuccess = isSuccess;
    }

    public ApiResponse(String errorMessage, boolean isSuccess) {
        this.errorMessage = errorMessage;
        this.isSuccess = isSuccess;
    }

    public ApiResponse(T data, boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, true);
    }

    public static <T> ApiResponse<T> failure(String errorMessage) {
        return new ApiResponse<>(errorMessage, false);
    }

    public static <T> ApiResponse<T> failure(T data) {
        return new ApiResponse<>(data, false);
    }
}
