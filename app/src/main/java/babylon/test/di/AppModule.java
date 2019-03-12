package babylon.test.di;

import okhttp3.OkHttpClient;
import toothpick.config.Module;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public final class AppModule extends Module {

    public AppModule() {
        bindRetrofitStuff();
    }

    private void bindRetrofitStuff() {
        bind(OkHttpClient.Builder.class).toProvider(OkHttpClientBuilderAppProvider.class);
    }
}

