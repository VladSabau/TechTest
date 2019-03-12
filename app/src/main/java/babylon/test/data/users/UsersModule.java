package babylon.test.data.users;

import toothpick.config.Module;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
public final class UsersModule extends Module {

    public UsersModule() {
        bind(UsersService.class).toProvider(UsersServiceProvider.class);
    }
}
