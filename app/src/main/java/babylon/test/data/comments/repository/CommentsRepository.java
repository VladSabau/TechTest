package babylon.test.data.comments.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import babylon.test.data.comments.CommentsService;
import babylon.test.data.comments.model.Comment;
import io.reactivex.Observable;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
public class CommentsRepository {

    private final CommentsService service;

    @Inject
    public CommentsRepository(CommentsService service) {
        this.service = service;
    }

    public Observable<List<Comment>> fetchComments() {
        return service.getComments();
    }

    public Observable<List<Comment>> getCommentsByPostId(int postId) {
        return fetchComments()
                .map(comments -> getCommentsByPostId(comments, postId));
    }

    private List<Comment> getCommentsByPostId(List<Comment> comments, int postId) {
        List<Comment> resultComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getPostId() == postId) {
                resultComments.add(comment);
            }
        }
        return resultComments;
    }
}