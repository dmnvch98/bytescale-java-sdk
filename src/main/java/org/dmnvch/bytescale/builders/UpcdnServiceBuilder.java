package org.dmnvch.bytescale.builders;

import okhttp3.OkHttpClient;
import org.dmnvch.bytescale.client.UcdnApiClient;
import org.dmnvch.bytescale.service.UpcdnService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class UpcdnServiceBuilder {

    private String publicKey;
    private String appId;
    private static final String UPCDN_URL = "https://upcdn.io/";

    public UpcdnServiceBuilder publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public UpcdnServiceBuilder appId(String appId) {
        this.appId = appId;
        return this;
    }

    public UpcdnService build() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UPCDN_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        UcdnApiClient upcdnApiClient = retrofit.create(UcdnApiClient.class);
        return new UpcdnService(publicKey, appId, upcdnApiClient);
    }
}
