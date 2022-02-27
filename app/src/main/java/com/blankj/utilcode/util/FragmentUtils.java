package com.blankj.utilcode.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.y */
/* loaded from: classes.dex */
public final class FragmentUtils {

    /* renamed from: a */
    private static final int f6915a = 1;

    /* renamed from: b */
    private static final int f6916b = 2;

    /* renamed from: c */
    private static final int f6917c = 4;

    /* renamed from: d */
    private static final int f6918d = 8;

    /* renamed from: e */
    private static final int f6919e = 16;

    /* renamed from: f */
    private static final int f6920f = 32;

    /* renamed from: g */
    private static final int f6921g = 64;

    /* renamed from: h */
    private static final String f6922h = "args_id";

    /* renamed from: i */
    private static final String f6923i = "args_is_hide";

    /* renamed from: j */
    private static final String f6924j = "args_is_add_stack";

    /* renamed from: k */
    private static final String f6925k = "args_tag";

    /* compiled from: FragmentUtils.java */
    /* renamed from: com.blankj.utilcode.util.y$c */
    /* loaded from: classes.dex */
    public interface AbstractC1037c {
        /* renamed from: a */
        boolean m22062a();
    }

    private FragmentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m22135a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22126a(fragmentManager, fragment, i, (String) null, false, false);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22123a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22126a(fragmentManager, fragment, i, (String) null, z, false);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22120a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22126a(fragmentManager, fragment, i, (String) null, z, z2);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22134a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, null, false, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22122a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, null, z, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22133a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, null, false, i2, i3, i4, i5);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22121a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, null, z, i2, i3, i4, i5);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22118a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr != null) {
            m22125a(fragmentManager, fragment, i, (String) null, false, viewArr);
        } else {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22119a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr != null) {
            m22125a(fragmentManager, fragment, i, (String) null, z, viewArr);
        } else {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22112a(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> list, @IdRes int i, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (list != null) {
            m22108a(fragmentManager, (Fragment[]) list.toArray(new Fragment[0]), i, (String[]) null, i2);
        } else {
            throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22109a(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentArr, @IdRes int i, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentArr != null) {
            m22108a(fragmentManager, fragmentArr, i, (String[]) null, i2);
        } else {
            throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22132a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22126a(fragmentManager, fragment, i, str, false, false);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22129a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22126a(fragmentManager, fragment, i, str, z, false);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22126a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22141a(fragment, new C1035a(i, str, z, z2));
            m22136a(fragmentManager, 1, (Fragment) null, fragment);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22131a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, str, false, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22128a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, str, z, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22130a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22127a(fragmentManager, fragment, i, str, false, i2, i3, i4, i5);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22127a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            m22141a(fragment, new C1035a(i, str, false, z));
            m22107a(beginTransaction, i2, i3, i4, i5);
            m22163a(1, fragmentManager, beginTransaction, (Fragment) null, fragment);
        } else {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22124a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr != null) {
            m22125a(fragmentManager, fragment, i, str, false, viewArr);
        } else {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22125a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (viewArr != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            m22141a(fragment, new C1035a(i, str, false, z));
            m22106a(beginTransaction, viewArr);
            m22163a(1, fragmentManager, beginTransaction, (Fragment) null, fragment);
        } else {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#5 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22111a(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> list, @IdRes int i, String[] strArr, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (list != null) {
            m22108a(fragmentManager, (Fragment[]) list.toArray(new Fragment[0]), i, strArr, i2);
        } else {
            throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22108a(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentArr, @IdRes int i, String[] strArr, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentArr != null) {
            if (strArr == null) {
                int length = fragmentArr.length;
                int i3 = 0;
                while (i3 < length) {
                    m22141a(fragmentArr[i3], new C1035a(i, null, i2 != i3, false));
                    i3++;
                }
            } else {
                int length2 = fragmentArr.length;
                int i4 = 0;
                while (i4 < length2) {
                    m22141a(fragmentArr[i4], new C1035a(i, strArr[i4], i2 != i4, false));
                    i4++;
                }
            }
            m22136a(fragmentManager, 1, (Fragment) null, fragmentArr);
        } else {
            throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22160a(@NonNull Fragment fragment) {
        if (fragment != null) {
            m22102b(fragment, false);
            m22136a(fragment.getFragmentManager(), 2, (Fragment) null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22137a(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            for (Fragment fragment : j) {
                m22102b(fragment, false);
            }
            m22136a(fragmentManager, 2, (Fragment) null, (Fragment[]) j.toArray(new Fragment[0]));
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22105b(@NonNull Fragment fragment) {
        if (fragment != null) {
            m22102b(fragment, true);
            m22136a(fragment.getFragmentManager(), 4, (Fragment) null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'hide' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22101b(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            for (Fragment fragment : j) {
                m22102b(fragment, true);
            }
            m22136a(fragmentManager, 4, (Fragment) null, (Fragment[]) j.toArray(new Fragment[0]));
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22162a(int i, @NonNull List<Fragment> list) {
        if (list != null) {
            m22140a(list.get(i), list);
            return;
        }
        throw new NullPointerException("Argument 'fragments' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22140a(@NonNull Fragment fragment, @NonNull List<Fragment> list) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (list != null) {
            Iterator<Fragment> it = list.iterator();
            while (true) {
                boolean z = false;
                if (it.hasNext()) {
                    Fragment next = it.next();
                    if (next != fragment) {
                        z = true;
                    }
                    m22102b(next, z);
                } else {
                    m22136a(fragment.getFragmentManager(), 8, fragment, (Fragment[]) list.toArray(new Fragment[0]));
                    return;
                }
            }
        } else {
            throw new NullPointerException("Argument 'hide' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22161a(int i, @NonNull Fragment... fragmentArr) {
        if (fragmentArr != null) {
            m22138a(fragmentArr[i], fragmentArr);
            return;
        }
        throw new NullPointerException("Argument 'fragments' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22138a(@NonNull Fragment fragment, @NonNull Fragment... fragmentArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentArr != null) {
            int length = fragmentArr.length;
            for (int i = 0; i < length; i++) {
                Fragment fragment2 = fragmentArr[i];
                m22102b(fragment2, fragment2 != fragment);
            }
            m22136a(fragment.getFragmentManager(), 8, fragment, fragmentArr);
        } else {
            throw new NullPointerException("Argument 'hide' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22157a(@NonNull Fragment fragment, @NonNull Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22102b(fragment, false);
            m22102b(fragment2, true);
            m22136a(fragment.getFragmentManager(), 8, fragment, fragment2);
        } else {
            throw new NullPointerException("Argument 'hide' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22103b(@NonNull Fragment fragment, @NonNull Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22151a(fragment, fragment2, (String) null, false);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22146a(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22151a(fragment, fragment2, (String) null, z);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22156a(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, (String) null, false, i, i2, 0, 0);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22145a(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, (String) null, z, i, i2, 0, 0);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22155a(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, (String) null, false, i, i2, i3, i4);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22144a(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, (String) null, z, i, i2, i3, i4);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22142a(@NonNull Fragment fragment, @NonNull Fragment fragment2, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22148a(fragment, fragment2, (String) null, false, viewArr);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22143a(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22148a(fragment, fragment2, (String) null, z, viewArr);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22100b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22094b(fragmentManager, fragment, i, (String) null, false);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22089b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22094b(fragmentManager, fragment, i, (String) null, z);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22099b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, null, false, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22088b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, null, z, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22098b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, null, false, i2, i3, i4, i5);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22087b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, null, z, i2, i3, i4, i5);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22085b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22091b(fragmentManager, fragment, i, (String) null, false, viewArr);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22086b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22091b(fragmentManager, fragment, i, (String) null, z, viewArr);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22154a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22151a(fragment, fragment2, str, false);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22151a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null) {
                m22094b(fragmentManager, fragment2, m22074f(fragment).f6926a, str, z);
            }
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22153a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, str, false, i, i2, 0, 0);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22150a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, str, z, i, i2, 0, 0);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22152a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22149a(fragment, fragment2, str, false, i, i2, i3, i4);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22149a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null) {
                m22092b(fragmentManager, fragment2, m22074f(fragment).f6926a, str, z, i, i2, i3, i4);
            }
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22147a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            m22148a(fragment, fragment2, str, false, viewArr);
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22148a(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment2 != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null) {
                m22091b(fragmentManager, fragment2, m22074f(fragment).f6926a, str, z, viewArr);
            }
        } else {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22097b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22094b(fragmentManager, fragment, i, str, false);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22094b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            m22141a(fragment, new C1035a(i, str, false, z));
            m22163a(16, fragmentManager, beginTransaction, (Fragment) null, fragment);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22096b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, str, false, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22093b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, str, z, i2, i3, 0, 0);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22095b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22092b(fragmentManager, fragment, i, str, false, i2, i3, i4, i5);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22092b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            m22141a(fragment, new C1035a(i, str, false, z));
            m22107a(beginTransaction, i2, i3, i4, i5);
            m22163a(16, fragmentManager, beginTransaction, (Fragment) null, fragment);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22090b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            m22091b(fragmentManager, fragment, i, str, false, viewArr);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static void m22091b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            m22141a(fragment, new C1035a(i, str, false, z));
            m22106a(beginTransaction, viewArr);
            m22163a(16, fragmentManager, beginTransaction, (Fragment) null, fragment);
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static void m22081c(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            m22110a(fragmentManager, true);
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22110a(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            fragmentManager.popBackStackImmediate();
        } else {
            fragmentManager.popBackStack();
        }
    }

    /* renamed from: a */
    public static void m22116a(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z) {
        if (fragmentManager != null) {
            m22115a(fragmentManager, cls, z, true);
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static void m22115a(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z2) {
            fragmentManager.popBackStackImmediate(cls.getName(), (int) z);
        } else {
            fragmentManager.popBackStack(cls.getName(), z ? 1 : 0);
        }
    }

    /* renamed from: d */
    public static void m22078d(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            m22083b(fragmentManager, true);
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22083b(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(0);
            if (z) {
                fragmentManager.popBackStackImmediate(backStackEntryAt.getId(), 1);
            } else {
                fragmentManager.popBackStack(backStackEntryAt.getId(), 1);
            }
        }
    }

    /* renamed from: c */
    public static void m22082c(@NonNull Fragment fragment) {
        if (fragment != null) {
            m22136a(fragment.getFragmentManager(), 32, (Fragment) null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'remove' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22139a(@NonNull Fragment fragment, boolean z) {
        if (fragment != null) {
            m22136a(fragment.getFragmentManager(), 64, z ? fragment : null, fragment);
            return;
        }
        throw new NullPointerException("Argument 'removeTo' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static void m22075e(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            m22136a(fragmentManager, 32, (Fragment) null, (Fragment[]) m22069j(fragmentManager).toArray(new Fragment[0]));
            return;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    private static void m22141a(Fragment fragment, C1035a aVar) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putInt(f6922h, aVar.f6926a);
        arguments.putBoolean(f6923i, aVar.f6927b);
        arguments.putBoolean(f6924j, aVar.f6928c);
        arguments.putString(f6925k, aVar.f6929d);
    }

    /* renamed from: b */
    private static void m22102b(Fragment fragment, boolean z) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putBoolean(f6923i, z);
    }

    /* renamed from: f */
    private static C1035a m22074f(Fragment fragment) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = Bundle.EMPTY;
        }
        return new C1035a(arguments.getInt(f6922h, fragment.getId()), arguments.getBoolean(f6923i), arguments.getBoolean(f6924j));
    }

    /* renamed from: a */
    private static void m22136a(@Nullable FragmentManager fragmentManager, int i, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager != null) {
            m22163a(i, fragmentManager, fragmentManager.beginTransaction(), fragment, fragmentArr);
        }
    }

    /* renamed from: a */
    private static void m22163a(int i, @NonNull FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (fragment == null || !fragment.isRemoving()) {
            int i2 = 0;
            if (i == 4) {
                int length = fragmentArr.length;
                while (i2 < length) {
                    fragmentTransaction.hide(fragmentArr[i2]);
                    i2++;
                }
            } else if (i == 8) {
                fragmentTransaction.show(fragment);
                int length2 = fragmentArr.length;
                while (i2 < length2) {
                    Fragment fragment2 = fragmentArr[i2];
                    if (fragment2 != fragment) {
                        fragmentTransaction.hide(fragment2);
                    }
                    i2++;
                }
            } else if (i == 16) {
                Bundle arguments = fragmentArr[0].getArguments();
                if (arguments != null) {
                    String string = arguments.getString(f6925k, fragmentArr[0].getClass().getName());
                    fragmentTransaction.replace(arguments.getInt(f6922h), fragmentArr[0], string);
                    if (arguments.getBoolean(f6924j)) {
                        fragmentTransaction.addToBackStack(string);
                    }
                } else {
                    return;
                }
            } else if (i == 32) {
                int length3 = fragmentArr.length;
                while (i2 < length3) {
                    Fragment fragment3 = fragmentArr[i2];
                    if (fragment3 != fragment) {
                        fragmentTransaction.remove(fragment3);
                    }
                    i2++;
                }
            } else if (i != 64) {
                switch (i) {
                    case 1:
                        int length4 = fragmentArr.length;
                        while (i2 < length4) {
                            Fragment fragment4 = fragmentArr[i2];
                            Bundle arguments2 = fragment4.getArguments();
                            if (arguments2 != null) {
                                String string2 = arguments2.getString(f6925k, fragment4.getClass().getName());
                                Fragment findFragmentByTag = fragmentManager.findFragmentByTag(string2);
                                if (findFragmentByTag != null && findFragmentByTag.isAdded()) {
                                    fragmentTransaction.remove(findFragmentByTag);
                                }
                                fragmentTransaction.add(arguments2.getInt(f6922h), fragment4, string2);
                                if (arguments2.getBoolean(f6923i)) {
                                    fragmentTransaction.hide(fragment4);
                                }
                                if (arguments2.getBoolean(f6924j)) {
                                    fragmentTransaction.addToBackStack(string2);
                                }
                                i2++;
                            } else {
                                return;
                            }
                        }
                        break;
                    case 2:
                        int length5 = fragmentArr.length;
                        while (i2 < length5) {
                            fragmentTransaction.show(fragmentArr[i2]);
                            i2++;
                        }
                        break;
                }
            } else {
                int length6 = fragmentArr.length - 1;
                while (true) {
                    if (length6 < 0) {
                        break;
                    }
                    Fragment fragment5 = fragmentArr[length6];
                    if (fragment5 != fragmentArr[0]) {
                        fragmentTransaction.remove(fragment5);
                        length6--;
                    } else if (fragment != null) {
                        fragmentTransaction.remove(fragment5);
                    }
                }
            }
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            Log.e("FragmentUtils", fragment.getClass().getName() + " is isRemoving");
        }
    }

    /* renamed from: a */
    private static void m22107a(FragmentTransaction fragmentTransaction, int i, int i2, int i3, int i4) {
        fragmentTransaction.setCustomAnimations(i, i2, i3, i4);
    }

    /* renamed from: a */
    private static void m22106a(FragmentTransaction fragmentTransaction, View... viewArr) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (View view : viewArr) {
                fragmentTransaction.addSharedElement(view, view.getTransitionName());
            }
        }
    }

    /* renamed from: f */
    public static Fragment m22073f(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return m22080c(fragmentManager, false);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public static Fragment m22072g(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return m22080c(fragmentManager, true);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    private static Fragment m22080c(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            for (int size = j.size() - 1; size >= 0; size--) {
                Fragment fragment = j.get(size);
                if (fragment != null) {
                    if (!z) {
                        return fragment;
                    }
                    Bundle arguments = fragment.getArguments();
                    if (arguments != null && arguments.getBoolean(f6924j)) {
                        return fragment;
                    }
                }
            }
            return null;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public static Fragment m22071h(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return m22077d(fragmentManager, false);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: i */
    public static Fragment m22070i(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return m22077d(fragmentManager, true);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    private static Fragment m22077d(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            for (int size = j.size() - 1; size >= 0; size--) {
                Fragment fragment = j.get(size);
                if (fragment != null && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint()) {
                    if (!z) {
                        return fragment;
                    }
                    Bundle arguments = fragment.getArguments();
                    if (arguments != null && arguments.getBoolean(f6924j)) {
                        return fragment;
                    }
                }
            }
            return null;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: j */
    public static List<Fragment> m22069j(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            List<Fragment> fragments = fragmentManager.getFragments();
            return (fragments == null || fragments.isEmpty()) ? Collections.emptyList() : fragments;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: k */
    public static List<Fragment> m22068k(@NonNull FragmentManager fragmentManager) {
        Bundle arguments;
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            ArrayList arrayList = new ArrayList();
            for (Fragment fragment : j) {
                if (!(fragment == null || (arguments = fragment.getArguments()) == null || !arguments.getBoolean(f6924j))) {
                    arrayList.add(fragment);
                }
            }
            return arrayList;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: l */
    public static List<C1036b> m22067l(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return m22113a(fragmentManager, new ArrayList());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    private static List<C1036b> m22113a(@NonNull FragmentManager fragmentManager, List<C1036b> list) {
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            for (int size = j.size() - 1; size >= 0; size--) {
                Fragment fragment = j.get(size);
                if (fragment != null) {
                    list.add(new C1036b(fragment, m22113a(fragment.getChildFragmentManager(), new ArrayList())));
                }
            }
            return list;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: m */
    public static List<C1036b> m22066m(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            return m22084b(fragmentManager, new ArrayList());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    private static List<C1036b> m22084b(@NonNull FragmentManager fragmentManager, List<C1036b> list) {
        Bundle arguments;
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            for (int size = j.size() - 1; size >= 0; size--) {
                Fragment fragment = j.get(size);
                if (!(fragment == null || (arguments = fragment.getArguments()) == null || !arguments.getBoolean(f6924j))) {
                    list.add(new C1036b(fragment, m22084b(fragment.getChildFragmentManager(), new ArrayList())));
                }
            }
            return list;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Fragment m22117a(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls) {
        if (fragmentManager != null) {
            return fragmentManager.findFragmentByTag(cls.getName());
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Fragment m22114a(@NonNull FragmentManager fragmentManager, @NonNull String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str != null) {
            return fragmentManager.findFragmentByTag(str);
        } else {
            throw new NullPointerException("Argument 'tag' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: d */
    public static boolean m22079d(@NonNull Fragment fragment) {
        if (fragment != null) {
            return fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof AbstractC1037c) && ((AbstractC1037c) fragment).m22062a();
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: n */
    public static boolean m22065n(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            List<Fragment> j = m22069j(fragmentManager);
            if (j == null || j.isEmpty()) {
                return false;
            }
            for (int size = j.size() - 1; size >= 0; size--) {
                Fragment fragment = j.get(size);
                if (fragment != null && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof AbstractC1037c) && ((AbstractC1037c) fragment).m22062a()) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22159a(@NonNull Fragment fragment, @ColorInt int i) {
        if (fragment != null) {
            View view = fragment.getView();
            if (view != null) {
                view.setBackgroundColor(i);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22104b(@NonNull Fragment fragment, @DrawableRes int i) {
        if (fragment != null) {
            View view = fragment.getView();
            if (view != null) {
                view.setBackgroundResource(i);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22158a(@NonNull Fragment fragment, Drawable drawable) {
        if (fragment != null) {
            View view = fragment.getView();
            if (view != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    view.setBackground(drawable);
                } else {
                    view.setBackgroundDrawable(drawable);
                }
            }
        } else {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public static String m22076e(Fragment fragment) {
        return fragment == null ? "null" : fragment.getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FragmentUtils.java */
    /* renamed from: com.blankj.utilcode.util.y$a */
    /* loaded from: classes.dex */
    public static class C1035a {

        /* renamed from: a */
        final int f6926a;

        /* renamed from: b */
        final boolean f6927b;

        /* renamed from: c */
        final boolean f6928c;

        /* renamed from: d */
        final String f6929d;

        C1035a(int i, boolean z, boolean z2) {
            this(i, null, z, z2);
        }

        C1035a(int i, String str, boolean z, boolean z2) {
            this.f6926a = i;
            this.f6929d = str;
            this.f6927b = z;
            this.f6928c = z2;
        }
    }

    /* compiled from: FragmentUtils.java */
    /* renamed from: com.blankj.utilcode.util.y$b */
    /* loaded from: classes.dex */
    public static class C1036b {

        /* renamed from: a */
        final Fragment f6930a;

        /* renamed from: b */
        final List<C1036b> f6931b;

        public C1036b(Fragment fragment, List<C1036b> list) {
            this.f6930a = fragment;
            this.f6931b = list;
        }

        /* renamed from: a */
        public Fragment m22064a() {
            return this.f6930a;
        }

        /* renamed from: b */
        public List<C1036b> m22063b() {
            return this.f6931b;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f6930a.getClass().getSimpleName());
            sb.append("->");
            List<C1036b> list = this.f6931b;
            sb.append((list == null || list.isEmpty()) ? "no child" : this.f6931b.toString());
            return sb.toString();
        }
    }
}
