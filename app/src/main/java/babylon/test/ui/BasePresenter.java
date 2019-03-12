package babylon.test.ui;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    protected V view;
    private CompositeDisposable disposables;

    public BasePresenter() {
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        unSubscribeAll();
        this.view = null;
    }

    protected void addSubscription(final Disposable disposable) {
        if (disposables == null || disposables.isDisposed()) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }

    protected void unSubscribeAll() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
            disposables = null;
        }
    }
}
