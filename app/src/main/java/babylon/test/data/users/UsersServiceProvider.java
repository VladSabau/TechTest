package babylon.test.data.users;

import javax.inject.Inject;
import javax.inject.Provider;

import babylon.test.di.ServiceProvider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vlad Sabau on 09.03.19.
 */

public class UsersServiceProvider extends ServiceProvider implements Provider<UsersService> {

    @Inject
    public UsersServiceProvider(Retrofit.Builder builder,
                                OkHttpClient.Builder httpClientBuilder,
                                HttpLoggingInterceptor logging) {
        super(builder, httpClientBuilder, logging);
    }

    @Override
    public void configure() {
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        addCustomHeaderInterceptor(FORM_URLENCODED, null);
    }

    @Override
    public UsersService get() {
        return buildRetrofitService(UsersService.class);
    }
}
