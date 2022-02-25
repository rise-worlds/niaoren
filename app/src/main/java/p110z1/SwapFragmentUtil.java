package p110z1;

import android.support.p003v4.app.Fragment;
import android.support.p003v4.app.FragmentManager;
import android.support.p003v4.app.FragmentTransaction;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* renamed from: z1.aqf */
/* loaded from: classes3.dex */
public class SwapFragmentUtil {
    /* renamed from: a */
    public static void m11590a(String str, String str2, FragmentManager fragmentManager, int i) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
        if (findFragmentByTag == null) {
            findFragmentByTag = (Fragment) Class.forName(str).newInstance();
        } else if (findFragmentByTag.isVisible()) {
            return;
        }
        Fragment fragment = null;
        if (str2 != null && !str2.equals("")) {
            fragment = fragmentManager.findFragmentByTag(str2);
        }
        if (fragment != null) {
            beginTransaction.hide(fragment);
            if (findFragmentByTag.isAdded()) {
                beginTransaction.show(findFragmentByTag);
            } else {
                if (str2 != null) {
                    beginTransaction.addToBackStack(str2);
                }
                beginTransaction.add(i, findFragmentByTag, str);
            }
        } else {
            beginTransaction.add(i, findFragmentByTag, str);
        }
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: a */
    public static void m11589a(String str, String str2, FragmentManager fragmentManager, int i, boolean z) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
        if (findFragmentByTag == null) {
            findFragmentByTag = (Fragment) Class.forName(str).newInstance();
        } else if (findFragmentByTag.isVisible()) {
            return;
        }
        Fragment fragment = null;
        if (str2 != null && !str2.equals("")) {
            if (!str2.equals(str)) {
                fragment = fragmentManager.findFragmentByTag(str2);
            } else {
                return;
            }
        }
        if (fragment != null) {
            beginTransaction.hide(fragment);
            if (findFragmentByTag.isAdded()) {
                beginTransaction.show(findFragmentByTag);
            } else {
                if (str2 != null && z) {
                    beginTransaction.addToBackStack(str2);
                }
                beginTransaction.add(i, findFragmentByTag, str);
            }
        } else {
            beginTransaction.add(i, findFragmentByTag, str);
        }
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: a */
    public static void m11588a(String str, String str2, FragmentManager fragmentManager, int i, boolean z, Object obj) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
        Fragment fragment = null;
        if (findFragmentByTag == null) {
            findFragmentByTag = (Fragment) Class.forName(str).getDeclaredMethod("newInstance", Object.class).invoke(null, obj);
        } else if (findFragmentByTag.isVisible()) {
            return;
        }
        if (str2 != null && !str2.equals("")) {
            if (!str2.equals(str)) {
                fragment = fragmentManager.findFragmentByTag(str2);
            } else {
                return;
            }
        }
        if (fragment != null) {
            beginTransaction.hide(fragment);
            if (findFragmentByTag.isAdded()) {
                beginTransaction.show(findFragmentByTag);
            } else {
                if (str2 != null && z) {
                    beginTransaction.addToBackStack(str2);
                }
                beginTransaction.add(i, findFragmentByTag, str);
            }
        } else {
            beginTransaction.add(i, findFragmentByTag, str);
        }
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: a */
    public static void m11591a(FragmentManager fragmentManager, String str) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (!(fragments == null || fragments.isEmpty())) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            for (Fragment fragment : fragments) {
                if (fragment.getClass().getName().equals(str)) {
                    beginTransaction.show(fragment);
                } else {
                    beginTransaction.hide(fragment);
                }
            }
            beginTransaction.commit();
        }
    }
}
