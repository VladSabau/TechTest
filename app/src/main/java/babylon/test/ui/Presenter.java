package babylon.test.ui;

/**
 * Created by Vlad Sabau on 09.03.19.
 */
public interface Presenter<V> {

    /**
     * Set or attach the view to this presenter. It should be called in Activity.onCreate or Fragment.onViewCreated
     */
    void attachView(V view);

    /**
     * Notify the presenter that the screen is being shown to the user.
     * It should be called in Activity.onResume or Fragment.onResume (at least not before onViewCreated)
     */
    void onScreenLoad();

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    void detachView(boolean retainInstance);
}

