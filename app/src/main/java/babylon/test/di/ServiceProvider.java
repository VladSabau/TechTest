package babylon.test.di;

import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public abstract class ServiceProvider {
    protected static final String FORM_URLENCODED = "application/x-www-form-urlencoded";
    protected static final String APPLICATION_JSON = "application/json";

    protected final Retrofit.Builder retrofitBuilder;

    protected final OkHttpClient.Builder httpClientBuilder;

    private final HttpLoggingInterceptor loggingInterceptor;

    private OkHttpClient httpClient;

    private boolean configured = false;

    public ServiceProvider(Retrofit.Builder retrofitBuilder,
                           OkHttpClient.Builder httpClientBuilder,
                           @Nullable HttpLoggingInterceptor loggingInterceptor) {
        this.retrofitBuilder = retrofitBuilder;
        this.httpClientBuilder = httpClientBuilder;
        this.loggingInterceptor = loggingInterceptor;
    }

    private void buildClient() {
        configure();
        if (loggingInterceptor != null) {
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        httpClient = httpClientBuilder.build();
    }

    public abstract void configure();

    protected <T> T buildRetrofitService(Class<T> service) {
        if (!configured) {
            buildClient();
            configured = true;
        }
        Retrofit retrofit = retrofitBuilder.client(httpClient).build();

        return retrofit.create(service);
    }

    protected void addCustomHeaderInterceptor(String contentType,
                                              String methodOverride) {
        if (httpClient != null) {
            throw new IllegalStateException("The OkHttpClient instance is already initialized");
        }

        httpClientBuilder.interceptors().add(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder();

            if (contentType != null) {
                requestBuilder.header("Content-Type", APPLICATION_JSON);
            }

            requestBuilder.header("Accept", APPLICATION_JSON);

            if (methodOverride != null) {
                requestBuilder.header("X-HTTP-Method-Override", methodOverride);
            }

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
    }
}
