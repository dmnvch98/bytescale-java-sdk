package org.dmnvch.bytescale.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.dmnvch.bytescale.model.enumeration.MalwareCheckStatus;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobResponseDto {
    @JsonProperty("jobUrl")
    private String jobUrl;

    @JsonProperty("jobDocs")
    private String jobDocs;

    @JsonProperty("jobId")
    private String jobId;

    @JsonProperty("jobType")
    private String jobType;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("created")
    private long created;

    @JsonProperty("lastUpdated")
    private long lastUpdated;

    @JsonProperty("status")
    private String status;

    @JsonProperty("summary")
    private Summary summary;

    @Getter
    @ToString
    @EqualsAndHashCode
    @Jacksonized
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        @JsonProperty("result")
        private Result result;

        @Getter
        @ToString
        @EqualsAndHashCode
        @Jacksonized
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Result {
            @JsonProperty("files")
            private List<FileInfo> files;

            @JsonProperty("database")
            private Database database;

            @JsonProperty("scanStartTime")
            private long scanStartTime;

            @JsonProperty("scanEndTime")
            private long scanEndTime;

            @Getter
            @ToString
            @EqualsAndHashCode
            @Jacksonized
            @Builder
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class FileInfo {
                @JsonProperty("filePath")
                private String filePath;

                @JsonProperty("fileHashSha1")
                private String fileHashSha1;

                @JsonProperty("viruses")
                private List<String> viruses;

                @JsonProperty("status")
                private MalwareCheckStatus status;

                @JsonProperty("skippedReason")
                private String skippedReason;

            }

            @Getter
            @ToString
            @EqualsAndHashCode
            @Jacksonized
            @Builder
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Database {
                @JsonProperty("databaseAge")
                private String databaseAge;

                @JsonProperty("databaseLastChecked")
                private long databaseLastChecked;

                @JsonProperty("databaseLastUpdated")
                private long databaseLastUpdated;
            }
        }
    }
}
