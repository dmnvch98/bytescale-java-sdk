package org.dmnvch.bytescale.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
@Getter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadFileResponseDto {

    @JsonProperty("files")
    private final List<FileInfo> files;

    @JsonProperty("errors")
    private final List<ErrorInfo> errors;

    @Builder
    @Jacksonized
    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FileInfo {
        @JsonProperty("formDataFieldName")
        private final String formDataFieldName;

        @JsonProperty("accountId")
        private final String accountId;

        @JsonProperty("etag")
        private final String etag;

        @JsonProperty("filePath")
        private final String filePath;

        @JsonProperty("fileUrl")
        private final String fileUrl;
    }

    @Builder
    @Jacksonized
    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ErrorInfo {
        @JsonProperty("formDataFieldName")
        private final String formDataFieldName;

        @JsonProperty("error")
        private final ErrorDetails error;
    }

    @Builder
    @Jacksonized
    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ErrorDetails {
        @JsonProperty("message")
        private final String message;

        @JsonProperty("code")
        private final String code;

        @JsonProperty("details")
        private final ErrorDetailsInfo details;
    }

    @Builder
    @Jacksonized
    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ErrorDetailsInfo {
        @JsonProperty("triggeredBy")
        private final String triggeredBy;

        @JsonProperty("submittedValue")
        private final String submittedValue;

        @JsonProperty("tip")
        private final String tip;
    }
}
