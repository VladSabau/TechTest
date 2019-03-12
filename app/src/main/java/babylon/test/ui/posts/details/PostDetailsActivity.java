package babylon.test.ui.posts.details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import javax.inject.Inject;

import babylon.test.R;
import babylon.test.data.posts.model.Post;
import babylon.test.ui.BaseActivity;
import butterknife.BindView;

import static babylon.test.ui.posts.details.PostDetailsPresenter.POST_NOT_SET;
import static babylon.test.ui.posts.list.PostsActivity.POST;

public class PostDetailsActivity extends BaseActivity implements PostDetailsView {

    @BindView(R.id.post_title)
    protected TextView postName;

    @BindView(R.id.post_body)
    protected TextView postBody;

    @BindView(R.id.username)
    protected TextView username;

    @BindView(R.id.number_comments)
    protected TextView numberComments;

    @Inject
    protected PostDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        bindView();

        setListeners();

        presenter.attachView(this);

        closeBtn.setVisibility(View.VISIBLE);
        toolBarTitle.setText(R.string.post_details_title);
    }

    private void setListeners() {
        closeBtn.setOnClickListener(v -> finish());
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
    public Post getPost() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(POST)) {
            return Parcels.unwrap(extras.getParcelable(POST));
        }
        return POST_NOT_SET;
    }

    @Override
    public void showUnknownPostDialog() {
        //TODO
    }

    @Override
    public void showErrorLoadingPost() {
        //TODO
    }

    @Override
    public void loadPost(PostDetailsModel model) {
        postName.setText(model.getPost().getTitle());
        postBody.setText(model.getPost().getBody());
        username.setText(model.getUser().getUsername());
        numberComments.setText(String.valueOf(model.getComments().size()));
    }
}
