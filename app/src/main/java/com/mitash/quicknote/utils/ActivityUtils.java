package com.mitash.quicknote.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mitash.quicknote.viewmodel.ViewModelFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public final class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addNonUIFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                  @NonNull Fragment nonUIFragment, String fragmentTag) {
        checkNotNull(fragmentManager);
        checkNotNull(nonUIFragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(nonUIFragment, fragmentTag);
        transaction.commit();
    }

    /**
     * @param activity instance of parent activity.
     * @param type ViewModel type.
     * @param <T> ViewModel extension.
     * @return ViewModel.
     */
    public static <T extends ViewModel> T obtainViewModel(FragmentActivity activity, Class<T> type) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(type);
    }


}
