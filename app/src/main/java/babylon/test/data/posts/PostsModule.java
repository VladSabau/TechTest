package babylon.test.data.posts;

import toothpick.config.Module;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public final class PostsModule extends Module {

    public PostsModule() {
        bind(PostsService.class).toProvider(PostsServiceProvider.class);
    }
}
