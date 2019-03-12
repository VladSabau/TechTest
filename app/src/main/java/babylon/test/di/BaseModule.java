package babylon.test.di;

import com.google.gson.Gson;

import babylon.test.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import toothpick.config.Module;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public final class BaseModule extends Module {

    public BaseModule() {

        bind(String.class).withName(ServerUrl.class).toInstance(BuildConfig.ENDPOINT_BASE);

        bind(Gson.class).toInstance(new Gson());

        bindRetrofitStuff();
    }

    private void bindRetrofitStuff() {
        bind(OkHttpClient.Builder.class).toProvider(OkHttpClientBuilderProvider.class);
        bind(Retrofit.Builder.class).toProvider(RetrofitBuilderProvider.class);
        bind(HttpLoggingInterceptor.class).toProvider(HttpLoggingInterceptorProvider.class);
    }
}
