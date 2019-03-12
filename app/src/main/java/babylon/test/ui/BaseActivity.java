package babylon.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import babylon.test.BaseApplication;
import babylon.test.R;
import babylon.test.ui.utils.ToothpickUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import toothpick.Toothpick;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public class BaseActivity extends AppCompatActivity implements MvpView {

    @Nullable
    @BindView(R.id.progress_bar)
    protected ProgressBar loadingProgress;

    @BindView(R.id.close_btn)
    protected Button closeBtn;

    @BindView(R.id.toolbar_title)
    protected TextView toolBarTitle;

    protected Unbinder unbinder;

    protected void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    private void unbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToothpickUtils.openActivityScopeAndInject((BaseApplication) getApplication(), this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }

    @Override
    public void startProgressIndicator() {
        if (loadingProgress != null) {
            loadingProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void stopProgressIndicator() {
        if (loadingProgress != null) {
            loadingProgress.setVisibility(View.GONE);
        }
    }
}
