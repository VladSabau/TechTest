package babylon.test.ui.posts.list;

import java.util.List;

import javax.inject.Inject;

import babylon.test.data.posts.model.Post;
import babylon.test.data.posts.usecase.PostsUseCase;
import babylon.test.rx.RxUtils;
import babylon.test.ui.BasePresenter;
import timber.log.Timber;


/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class PostsPresenter extends BasePresenter<PostsView> {

    private PostsUseCase postsUseCase;

    @Inject
    public PostsPresenter(PostsUseCase postsUseCase) {
        this.postsUseCase = postsUseCase;
    }

    @Override
    public void onScreenLoad() {
        loadPosts();
    }

    private void loadPosts() {
        view.startProgressIndicator();
        addSubscription(postsUseCase.getPosts()
                .compose(RxUtils.applyDefaultSchedulers())
                .doOnTerminate(() -> view.stopProgressIndicator())
                .subscribe(this::successLoadingPosts,
                        this::errorLoadingPosts));
    }

    private void successLoadingPosts(List<Post> posts) {
        if (posts.isEmpty()) {
            view.showNoPostsAvailable();
        } else {
            view.updatePosts(posts);
        }
    }

    private void errorLoadingPosts(Throwable error) {
        Timber.e(error, "Failed receive posts in PostsPresenter");
        view.showNoPostsAvailable();
    }

    public void onPostClick(Post post) {
        view.openPostDetail(post);
    }
}
