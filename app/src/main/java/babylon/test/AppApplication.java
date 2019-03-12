package babylon.test;

import com.facebook.stetho.Stetho;

import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class AppApplication extends BaseApplication {

    private static AppApplication instance;

    public static AppApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void initializeInfrastructure() {
        super.initializeInfrastructure();

        stethoActivation();
    }

    @Override
    protected void initializeApp() {
        super.initializeApp();
        AppInitializer initializer = new AppInitializer(this);
        initializer.initialize();
    }

    @Override
    protected void dependencyInjectionActivation() {
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        MemberInjectorRegistryLocator.setRootRegistry(new MemberInjectorRegistry());
        FactoryRegistryLocator.setRootRegistry(new FactoryRegistry());
    }

    private void stethoActivation() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}

