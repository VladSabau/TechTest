package babylon.test.ui.utils;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import babylon.test.BaseApplication;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.smoothie.module.SmoothieSupportActivityModule;

/**
 * Created by Vlad Sabau on 07.03.19.
 */
public class ToothpickUtils {

    private ToothpickUtils() {
        // hide implicit public constructor
    }

    public static void openActivityScopeAndInject(final BaseApplication application, final AppCompatActivity activity) {
        final Scope scope = Toothpick.openScopes(application, activity);
        scope.installModules(new SmoothieSupportActivityModule(activity));
        Toothpick.inject(activity, scope);
    }

    public static void openFragmentScopeAndInject(final Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        BaseApplication application = (BaseApplication) activity.getApplication();
        final Scope scope = Toothpick.openScopes(application, activity, fragment);
        scope.installModules(new SmoothieSupportActivityModule(activity));
        Toothpick.inject(fragment, scope);
    }
}
