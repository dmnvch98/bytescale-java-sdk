package org.dmnvch.bytescale.client;

import io.reactivex.Single;
import org.dmnvch.bytescale.model.JobResponseDto;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UcdnApiClient {

    @GET("{appId}/antivirus/{filePath}")
    Single<JobResponseDto> getAntivirusCheck(
            @Header("Authorization") final String authorization,
            @Path("filePath") String filePath,
            @Path("appId") final String appId
    );
}
