package babylon.test.ui.posts.list;

import java.util.List;

import babylon.test.data.posts.model.Post;
import babylon.test.ui.MvpView;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
interface PostsView extends MvpView {

    void updatePosts(List<Post> posts);

    void showNoPostsAvailable();

    void openPostDetail(Post post);
}
