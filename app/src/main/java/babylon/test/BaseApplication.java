package babylon.test;

import android.app.Application;

import com.facebook.stetho.Stetho;

import babylon.test.di.BaseModule;
import babylon.test.rx.RxUtils;
import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class BaseApplication extends Application {

    private static final String PREFERENCES = "BabylonPreferences";

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("Initializing App...");

        initializeInfrastructure();
        initializeApp();

        Timber.i("App initialized");
    }

    protected void initializeInfrastructure() {
        rxJavaInit();
        stethoActivation();
        dependencyInjectionActivation();
    }

    private void stethoActivation() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    private void rxJavaInit() {
        RxUtils.initInterruptedExceptionsHandler();
    }

    protected void initializeApp() {
        Scope appScope = openScopeToothpick();
        appScope.installModules(
                new SmoothieApplicationModule(this, PREFERENCES),
                new BaseModule());
    }

    protected Scope openScopeToothpick() {
        return Toothpick.openScope(this);
    }

    protected void dependencyInjectionActivation() {
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        MemberInjectorRegistryLocator.setRootRegistry(new babylon.test.MemberInjectorRegistry());
        FactoryRegistryLocator.setRootRegistry(new babylon.test.FactoryRegistry());
    }
}
