package babylon.test.ui.posts.details;

import babylon.test.data.posts.model.Post;
import babylon.test.ui.MvpView;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
interface PostDetailsView extends MvpView {

    Post getPost();

    void showUnknownPostDialog();

    void showErrorLoadingPost();

    void loadPost(PostDetailsModel model);
}
