package babylon.test.ui.posts.details;

import java.util.List;

import babylon.test.data.comments.model.Comment;
import babylon.test.data.posts.model.Post;
import babylon.test.data.users.model.User;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
public class PostDetailsModel {

    private Post post;

    private User user;

    private List<Comment> comments;

    public PostDetailsModel(Post post,
                            User user,
                            List<Comment> comments) {
        this.post = post;
        this.user = user;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
