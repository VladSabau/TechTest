package babylon.test.data.comments.usecase;

import java.util.List;

import javax.inject.Inject;

import babylon.test.data.comments.model.Comment;
import babylon.test.data.comments.repository.CommentsRepository;
import io.reactivex.Observable;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
public class CommentsUseCase {

    private final CommentsRepository repository;

    @Inject
    public CommentsUseCase(final CommentsRepository repository) {
        this.repository = repository;
    }

    public Observable<List<Comment>> getComments() {
        return repository.fetchComments();
    }

    public Observable<List<Comment>> getCommentsByPostId(int postId) {
        return repository.getCommentsByPostId(postId);
    }
}
