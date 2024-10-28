package org.dmnvch.bytescale.model;

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
public class UploadFileResponseDto {
    @JsonProperty("files")
    private final List<FileInfo> files;

    @Builder
    @Jacksonized
    @Getter
    @ToString
    @EqualsAndHashCode
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
}
