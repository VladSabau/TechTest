package babylon.test.ui.posts.details;

import javax.inject.Inject;

import babylon.test.data.comments.usecase.CommentsUseCase;
import babylon.test.data.posts.model.Post;
import babylon.test.rx.RxUtils;
import babylon.test.ui.BasePresenter;
import babylon.test.data.users.usecase.UsersUseCase;
import io.reactivex.Observable;
import timber.log.Timber;

import static io.reactivex.Observable.zip;


/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class PostDetailsPresenter extends BasePresenter<PostDetailsView> {

    public static final Post POST_NOT_SET = new Post();

    private UsersUseCase usersUseCase;

    private CommentsUseCase commentsUseCase;

    private Post post;

    @Inject
    public PostDetailsPresenter(UsersUseCase usersUseCase,
                                CommentsUseCase commentsUseCase) {
        this.usersUseCase = usersUseCase;
        this.commentsUseCase = commentsUseCase;
    }

    @Override
    public void attachView(PostDetailsView view) {
        super.attachView(view);
        this.post = view.getPost();
    }

    @Override
    public void onScreenLoad() {
        if (post != POST_NOT_SET) {
            loadPost();
        } else {
            view.showUnknownPostDialog();
        }
    }

    private void loadPost() {
        view.startProgressIndicator();

        addSubscription(zip(Observable.just(post),
                usersUseCase.getUserById(post.getUserId()),
                commentsUseCase.getCommentsByPostId(post.getId()), PostDetailsModel::new)
                .compose(RxUtils.applyDefaultSchedulers())
                .doOnTerminate(() -> view.stopProgressIndicator())
                .subscribe(this::successLoadingPost,
                        this::errorLoadingPosts));
    }

    private void successLoadingPost(PostDetailsModel model) {
        view.loadPostDetail(model);
    }

    private void errorLoadingPosts(Throwable error) {
        Timber.e(error, "Failed receive posts in PostsPresenter");
        view.showErrorLoadingPost();
    }
}
