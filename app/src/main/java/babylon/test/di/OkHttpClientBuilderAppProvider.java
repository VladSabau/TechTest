package babylon.test.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Inject;

import babylon.test.BuildConfig;
import okhttp3.OkHttpClient;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class OkHttpClientBuilderAppProvider extends OkHttpClientBuilderProvider {

    @Inject
    public OkHttpClientBuilderAppProvider() {
    }

    @Override
    public OkHttpClient.Builder get() {
        final OkHttpClient.Builder httpClientBuilder = super.get();

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }

        return httpClientBuilder;
    }
}
