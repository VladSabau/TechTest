package babylon.test.di;

import javax.inject.Inject;
import javax.inject.Provider;

import babylon.test.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class HttpLoggingInterceptorProvider implements Provider<HttpLoggingInterceptor> {

    @Inject
    public HttpLoggingInterceptorProvider() {
    }

    @Override
    public HttpLoggingInterceptor get() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(getHttpLoggingLevel());

        return loggingInterceptor;
    }

    private HttpLoggingInterceptor.Level getHttpLoggingLevel() {
        return (BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS
                : HttpLoggingInterceptor.Level.NONE);
    }
}
