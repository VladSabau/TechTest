package babylon.test.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Provider;

import okhttp3.OkHttpClient;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class OkHttpClientBuilderProvider implements Provider<OkHttpClient.Builder> {

    public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20000;
    public static final long DEFAULT_READ_TIMEOUT_MILLIS = 20000;
    public static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 10000;

    @Inject
    public OkHttpClientBuilderProvider() {
    }

    @Override
    public OkHttpClient.Builder get() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        httpClientBuilder.readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

        return httpClientBuilder;
    }
}