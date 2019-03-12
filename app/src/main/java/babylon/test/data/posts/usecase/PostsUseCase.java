package babylon.test.data.posts.usecase;

import java.util.List;

import javax.inject.Inject;

import babylon.test.data.posts.model.Post;
import babylon.test.data.posts.repository.PostsRepository;
import io.reactivex.Observable;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class PostsUseCase {

    private final PostsRepository repository;

    @Inject
    public PostsUseCase(final PostsRepository repository) {
        this.repository = repository;
    }

    public Observable<List<Post>> getPosts() {
        return repository.fetchPosts();
    }
}
