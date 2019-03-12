package babylon.test.ui.posts.list.adapter;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.DiffUtil;
import babylon.test.data.posts.model.Post;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class PostDiffCallback extends DiffUtil.Callback {

    private List<Post> oldPosts;
    private List<Post> newPosts;

    @Inject
    public PostDiffCallback(List<Post> oldPosts, List<Post> newPosts) {
        this.oldPosts = oldPosts;
        this.newPosts = newPosts;
    }

    @Override
    public int getOldListSize() {
        return oldPosts.size();
    }

    @Override
    public int getNewListSize() {
        return newPosts.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Post oldPost = oldPosts.get(oldItemPosition);
        Post newPost = newPosts.get(newItemPosition);

        return sameId(oldPost, newPost);
    }

    @Override
    public boolean areContentsTheSame(int newItemPosition, int oldItemPosition) {
        return newPosts.get(newItemPosition).equals(oldPosts.get(oldItemPosition));
    }

    private boolean sameId(Post oldPost, Post newPost) {
        return oldPost.getId() == newPost.getId();
    }
}
