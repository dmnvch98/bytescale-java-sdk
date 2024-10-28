package org.dmnvch.bytescale.client;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import org.dmnvch.bytescale.model.UploadFileResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

public interface ByteScaleApiClient {

    @Multipart
    @POST("v2/accounts/{appId}/uploads/form_data")
    Single<UploadFileResponseDto> uploadFile(
            @Header("Authorization") final String authorization,
            @Part final MultipartBody.Part filePart,
            @Path("appId") final String appId
    );

    @DELETE("v2/accounts/{appId}/files")
    Call<Void> deleteFileUpload(
            @Header("Authorization") final String authorization,
            @Path("appId") final String appId,
            @Query("filePath") String filePath
    );
}

