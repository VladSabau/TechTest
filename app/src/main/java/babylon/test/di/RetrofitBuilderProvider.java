package babylon.test.di;

import javax.inject.Inject;
import javax.inject.Provider;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class RetrofitBuilderProvider implements Provider<Retrofit.Builder> {
    private String serverUrl;

    @Inject
    public RetrofitBuilderProvider(@ServerUrl String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public Retrofit.Builder get() {
        return (new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()));
    }
}
