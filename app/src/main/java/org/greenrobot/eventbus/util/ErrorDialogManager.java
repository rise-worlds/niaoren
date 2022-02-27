package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import p110z1.EventBus;

/* loaded from: classes2.dex */
public class ErrorDialogManager {

    /* renamed from: a */
    public static ErrorDialogFragmentFactory<?> f14791a = null;

    /* renamed from: b */
    protected static final String f14792b = "de.greenrobot.eventbus.error_dialog";

    /* renamed from: c */
    protected static final String f14793c = "de.greenrobot.eventbus.error_dialog_manager";

    /* renamed from: d */
    public static final String f14794d = "de.greenrobot.eventbus.errordialog.title";

    /* renamed from: e */
    public static final String f14795e = "de.greenrobot.eventbus.errordialog.message";

    /* renamed from: f */
    public static final String f14796f = "de.greenrobot.eventbus.errordialog.finish_after_dialog";

    /* renamed from: g */
    public static final String f14797g = "de.greenrobot.eventbus.errordialog.icon_id";

    /* renamed from: h */
    public static final String f14798h = "de.greenrobot.eventbus.errordialog.event_type_on_close";

    /* loaded from: classes2.dex */
    public static class SupportManagerFragment extends Fragment {

        /* renamed from: a */
        protected boolean f14803a;

        /* renamed from: b */
        protected Bundle f14804b;

        /* renamed from: c */
        private EventBus f14805c;

        /* renamed from: d */
        private boolean f14806d;

        /* renamed from: e */
        private Object f14807e;

        @Override // android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.f14805c = ErrorDialogManager.f14791a.f14826a.m14787b();
            this.f14805c.m3446a(this);
            this.f14806d = true;
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            if (this.f14806d) {
                this.f14806d = false;
                return;
            }
            this.f14805c = ErrorDialogManager.f14791a.f14826a.m14787b();
            this.f14805c.m3446a(this);
        }

        @Override // android.support.v4.app.Fragment
        public void onPause() {
            this.f14805c.m3430c(this);
            super.onPause();
        }

        /* renamed from: a */
        public void m14807a(ThrowableFailureEvent fVar) {
            if (ErrorDialogManager.m14811b(this.f14807e, fVar)) {
                ErrorDialogManager.m14813a(fVar);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.f14792b);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                DialogFragment dialogFragment2 = (DialogFragment) ErrorDialogManager.f14791a.m14786a(fVar, this.f14803a, this.f14804b);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, ErrorDialogManager.f14792b);
                }
            }
        }

        /* renamed from: a */
        public static void m14808a(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            SupportManagerFragment supportManagerFragment = (SupportManagerFragment) supportFragmentManager.findFragmentByTag(ErrorDialogManager.f14793c);
            if (supportManagerFragment == null) {
                supportManagerFragment = new SupportManagerFragment();
                supportFragmentManager.beginTransaction().add(supportManagerFragment, ErrorDialogManager.f14793c).commit();
                supportFragmentManager.executePendingTransactions();
            }
            supportManagerFragment.f14803a = z;
            supportManagerFragment.f14804b = bundle;
            supportManagerFragment.f14807e = obj;
        }
    }

    @TargetApi(11)
    /* loaded from: classes2.dex */
    public static class HoneycombManagerFragment extends android.app.Fragment {

        /* renamed from: a */
        protected boolean f14799a;

        /* renamed from: b */
        protected Bundle f14800b;

        /* renamed from: c */
        private EventBus f14801c;

        /* renamed from: d */
        private Object f14802d;

        @Override // android.app.Fragment
        public void onResume() {
            super.onResume();
            this.f14801c = ErrorDialogManager.f14791a.f14826a.m14787b();
            this.f14801c.m3446a(this);
        }

        @Override // android.app.Fragment
        public void onPause() {
            this.f14801c.m3430c(this);
            super.onPause();
        }

        /* renamed from: a */
        public void m14809a(ThrowableFailureEvent fVar) {
            if (ErrorDialogManager.m14811b(this.f14802d, fVar)) {
                ErrorDialogManager.m14813a(fVar);
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                android.app.DialogFragment dialogFragment = (android.app.DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.f14792b);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                android.app.DialogFragment dialogFragment2 = (android.app.DialogFragment) ErrorDialogManager.f14791a.m14786a(fVar, this.f14799a, this.f14800b);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, ErrorDialogManager.f14792b);
                }
            }
        }

        /* renamed from: a */
        public static void m14810a(Activity activity, Object obj, boolean z, Bundle bundle) {
            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
            HoneycombManagerFragment honeycombManagerFragment = (HoneycombManagerFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.f14793c);
            if (honeycombManagerFragment == null) {
                honeycombManagerFragment = new HoneycombManagerFragment();
                fragmentManager.beginTransaction().add(honeycombManagerFragment, ErrorDialogManager.f14793c).commit();
                fragmentManager.executePendingTransactions();
            }
            honeycombManagerFragment.f14799a = z;
            honeycombManagerFragment.f14800b = bundle;
            honeycombManagerFragment.f14802d = obj;
        }
    }

    /* renamed from: a */
    public static void m14818a(Activity activity) {
        m14815a(activity, false, null);
    }

    /* renamed from: a */
    public static void m14816a(Activity activity, boolean z) {
        m14815a(activity, z, null);
    }

    /* renamed from: a */
    public static void m14815a(Activity activity, boolean z, Bundle bundle) {
        m14817a(activity, activity.getClass(), z, bundle);
    }

    /* renamed from: a */
    public static void m14817a(Activity activity, Object obj, boolean z, Bundle bundle) {
        if (f14791a == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        } else if (m14812b(activity)) {
            SupportManagerFragment.m14808a(activity, obj, z, bundle);
        } else {
            HoneycombManagerFragment.m14810a(activity, obj, z, bundle);
        }
    }

    /* renamed from: b */
    private static boolean m14812b(Activity activity) {
        String name;
        Class<?> cls = activity.getClass();
        do {
            cls = cls.getSuperclass();
            if (cls != null) {
                name = cls.getName();
                if (name.equals("android.support.v4.app.FragmentActivity")) {
                    return true;
                }
                if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                    throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
                }
            } else {
                throw new RuntimeException("Illegal activity type: " + activity.getClass());
            }
        } while (!name.equals("android.app.Activity"));
        if (Build.VERSION.SDK_INT >= 11) {
            return false;
        }
        throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
    }

    /* renamed from: a */
    protected static void m14813a(ThrowableFailureEvent fVar) {
        if (f14791a.f14826a.f14822f) {
            String str = f14791a.f14826a.f14823g;
            if (str == null) {
                str = EventBus.f21113a;
            }
            Log.i(str, "Error dialog manager received exception", fVar.f14828a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m14811b(Object obj, ThrowableFailureEvent fVar) {
        Object a;
        return fVar == null || (a = fVar.mo14777a()) == null || a.equals(obj);
    }
}
