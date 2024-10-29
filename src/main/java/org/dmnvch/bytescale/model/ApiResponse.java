package org.dmnvch.bytescale.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String errorMessage;
    private boolean isSuccess;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, null, true);
    }

    public static <T> ApiResponse<T> failure(String errorMessage) {
        return new ApiResponse<>(null, errorMessage, false);
    }
}
