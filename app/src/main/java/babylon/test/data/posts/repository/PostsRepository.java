package babylon.test.data.posts.repository;

import java.util.List;

import javax.inject.Inject;

import babylon.test.data.posts.PostsService;
import babylon.test.data.posts.model.Post;
import io.reactivex.Observable;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class PostsRepository {

    private final PostsService postsService;

    @Inject
    public PostsRepository(PostsService postsService) {
        this.postsService = postsService;
    }

    public Observable<List<Post>> fetchPosts() {
        return postsService.getPosts();
    }
}