package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.p003v4.app.Fragment;
import android.support.p003v4.app.FragmentActivity;

/* loaded from: classes.dex */
public class ViewModelStores {
    private ViewModelStores() {
    }

    @NonNull
    @MainThread
    /* renamed from: of */
    public static ViewModelStore m25701of(@NonNull FragmentActivity fragmentActivity) {
        if (fragmentActivity instanceof ViewModelStoreOwner) {
            return fragmentActivity.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragmentActivity).getViewModelStore();
    }

    @NonNull
    @MainThread
    /* renamed from: of */
    public static ViewModelStore m25702of(@NonNull Fragment fragment) {
        if (fragment instanceof ViewModelStoreOwner) {
            return fragment.getViewModelStore();
        }
        return HolderFragment.holderFragmentFor(fragment).getViewModelStore();
    }
}
