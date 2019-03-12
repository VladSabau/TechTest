package babylon.test.ui.posts.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import babylon.test.R;
import babylon.test.data.posts.model.Post;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private final List<Post> posts = new ArrayList<>();
    private OnPostClickListener listener;

    @SuppressWarnings("squid:S1186")
    @Inject
    public PostsAdapter() {
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.name.setText(post.getTitle());
        holder.layout.setOnClickListener(view -> listener.onPostClick(post));
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        PostsViewHolder holder = new PostsViewHolder(view);
        holder.layout.setOnClickListener(v -> listener.onPostClick(posts.get(holder.getAdapterPosition())));
        return holder;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setListener(OnPostClickListener listener) {
        this.listener = listener;
    }

    public void update(List<Post> newPosts) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PostDiffCallback(newPosts, this.posts), true);

        this.posts.clear();
        this.posts.addAll(newPosts);

        diffResult.dispatchUpdatesTo(this);
    }

    static class PostsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_layout)
        ViewGroup layout;

        @BindView(R.id.post_name)
        TextView name;

        PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
