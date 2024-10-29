package org.dmnvch.bytescale.builders;

import okhttp3.OkHttpClient;
import org.dmnvch.bytescale.client.ByteScaleApiClient;
import org.dmnvch.bytescale.service.ByteScaleService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ByteScaleServiceBuilder {

    private String publicKey;
    private String secretKey;
    private String appId;
    private static final String BYTESCALE_URL = "https://api.bytescale.com/";

    public ByteScaleServiceBuilder publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public ByteScaleServiceBuilder secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public ByteScaleServiceBuilder appId(String appId) {
        this.appId = appId;
        return this;
    }

    public ByteScaleService build() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BYTESCALE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ByteScaleApiClient byteScaleApiClient = retrofit.create(ByteScaleApiClient.class);
        return new ByteScaleService(publicKey, secretKey, appId, byteScaleApiClient);
    }
}
