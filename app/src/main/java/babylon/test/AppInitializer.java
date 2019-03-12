package babylon.test;

import android.app.Application;

import babylon.test.data.comments.CommentsModule;
import babylon.test.di.AppModule;
import babylon.test.data.posts.PostsModule;
import babylon.test.data.users.UsersModule;
import toothpick.Scope;
import toothpick.Toothpick;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class AppInitializer {
    private final Application application;

    public AppInitializer(Application application) {
        this.application = application;
    }

    public void initialize() {
        Scope appScope = openScopeToothpick();
        installToothpickModules(appScope);
    }

    private Scope openScopeToothpick() {
        return Toothpick.openScope(application);
    }

    private void installToothpickModules(Scope appScope) {
        appScope.installModules(
                new AppModule(),
                new PostsModule(),
                new UsersModule(),
                new CommentsModule());
    }
}
