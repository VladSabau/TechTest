package babylon.test.ui.posts.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import babylon.test.R;
import babylon.test.data.posts.model.Post;
import babylon.test.ui.BaseActivity;
import babylon.test.ui.posts.list.adapter.OnPostClickListener;
import babylon.test.ui.posts.list.adapter.PostsAdapter;
import babylon.test.ui.posts.details.PostDetailsActivity;
import butterknife.BindView;

public class PostsActivity extends BaseActivity implements PostsView, OnPostClickListener {

    public static final String POST = "POST";
    public static final int POST_DETAILS = 123;

    @BindView(R.id.posts_not_available)
    protected TextView noPostLabel;

    @BindView(R.id.posts_recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected PostsPresenter presenter;

    @Inject
    protected PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        bindView();

        createAdapter();
        setListeners();
        presenter.attachView(this);

        closeBtn.setVisibility(View.INVISIBLE);
        toolBarTitle.setText(R.string.posts_title);
    }

    private void setListeners() {
        postsAdapter.setListener(this);
    }

    private void createAdapter() {
        LinearLayoutManager layout = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(postsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onScreenLoad();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView(false);
        super.onDestroy();
    }

    @Override
    public void updatePosts(List<Post> posts) {
        noPostLabel.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        postsAdapter.update(posts);
    }

    @Override
    public void showNoPostsAvailable() {
        noPostLabel.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void openPostDetail(Post post) {
        Intent intentMileageCredit = new Intent(this, PostDetailsActivity.class);
        Bundle data = new Bundle();
        data.putParcelable(POST, Parcels.wrap(post));
        intentMileageCredit.putExtras(data);
        startActivityForResult(intentMileageCredit, POST_DETAILS);
    }

    @Override
    public void onPostClick(Post post) {
        presenter.onPostClick(post);
    }
}
