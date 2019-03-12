package babylon.test.data.comments;

import toothpick.config.Module;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
public final class CommentsModule extends Module {

    public CommentsModule() {
        bind(CommentsService.class).toProvider(CommentsServiceProvider.class);
    }
}
