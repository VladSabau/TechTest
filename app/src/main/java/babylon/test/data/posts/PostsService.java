package babylon.test.data.posts;

import java.util.List;

import babylon.test.data.posts.model.Post;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Vlad Sabau on 09.03.19.
 */

public interface PostsService {

    @GET("/posts")
    Observable<List<Post>> getPosts();
}
