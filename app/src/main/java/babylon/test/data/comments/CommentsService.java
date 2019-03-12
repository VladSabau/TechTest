package babylon.test.data.comments;

import java.util.List;

import babylon.test.data.comments.model.Comment;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Vlad Sabau on 10.03.19.
 */

public interface CommentsService {

    @GET("/comments")
    Observable<List<Comment>> getComments();
}
