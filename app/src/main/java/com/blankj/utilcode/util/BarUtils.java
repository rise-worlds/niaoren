package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/* renamed from: com.blankj.utilcode.util.d */
/* loaded from: classes.dex */
public final class BarUtils {

    /* renamed from: a */
    private static final String f6843a = "TAG_STATUS_BAR";

    /* renamed from: b */
    private static final String f6844b = "TAG_OFFSET";

    /* renamed from: c */
    private static final int f6845c = -123;

    private BarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static int m22874a() {
        Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* renamed from: a */
    public static void m22870a(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            m22863a(activity.getWindow(), z);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22863a(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            window.clearFlags(1024);
            m22837g(window);
            m22842d(window);
        } else {
            window.addFlags(1024);
            m22838f(window);
            m22840e(window);
        }
    }

    /* renamed from: a */
    public static boolean m22873a(@NonNull Activity activity) {
        if (activity != null) {
            return (activity.getWindow().getAttributes().flags & 1024) == 0;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22856b(@NonNull Activity activity, boolean z) {
        if (activity != null) {
            m22853b(activity.getWindow(), z);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m22853b(@NonNull Window window, boolean z) {
        View decorView;
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 23 && (decorView = window.getDecorView()) != null) {
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        }
    }

    /* renamed from: b */
    public static boolean m22859b(@NonNull Activity activity) {
        if (activity != null) {
            return m22865a(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static boolean m22865a(@NonNull Window window) {
        View decorView;
        if (window != null) {
            return (Build.VERSION.SDK_INT < 23 || (decorView = window.getDecorView()) == null || (decorView.getSystemUiVisibility() & 8192) == 0) ? false : true;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22867a(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19) {
            view.setTag(f6844b);
            Object tag = view.getTag(f6845c);
            if (tag == null || !((Boolean) tag).booleanValue()) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + m22874a(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(f6845c, true);
            }
        }
    }

    /* renamed from: b */
    public static void m22855b(@NonNull View view) {
        Object tag;
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (tag = view.getTag(f6845c)) != null && ((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - m22874a(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(f6845c, false);
        }
    }

    /* renamed from: d */
    private static void m22842d(Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(f6844b)) != null) {
            m22867a(findViewWithTag);
        }
    }

    /* renamed from: e */
    private static void m22840e(Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(f6844b)) != null) {
            m22855b(findViewWithTag);
        }
    }

    /* renamed from: a */
    public static View m22872a(@NonNull Activity activity, @ColorInt int i) {
        if (activity != null) {
            return m22871a(activity, i, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static View m22871a(@NonNull Activity activity, @ColorInt int i, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT < 19) {
            return null;
        } else {
            m22839f(activity);
            return m22857b(activity, i, z);
        }
    }

    /* renamed from: a */
    public static void m22866a(@NonNull View view, @ColorInt int i) {
        Activity d;
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (d = m22843d(view)) != null) {
            m22839f(d);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = m22874a();
            view.setBackgroundColor(i);
        }
    }

    /* renamed from: c */
    public static void m22848c(@NonNull View view) {
        Activity d;
        if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (d = m22843d(view)) != null) {
            m22839f(d);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-1, m22874a()));
                return;
            }
            layoutParams.width = -1;
            layoutParams.height = m22874a();
        }
    }

    /* renamed from: a */
    public static void m22869a(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i) {
        if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (view != null) {
            m22868a(drawerLayout, view, i, false);
        } else {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22868a(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i, boolean z) {
        Activity d;
        if (drawerLayout == null) {
            throw new NullPointerException("Argument 'drawer' of type DrawerLayout (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (view == null) {
            throw new NullPointerException("Argument 'fakeStatusBar' of type View (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19 && (d = m22843d(view)) != null) {
            m22839f(d);
            drawerLayout.setFitsSystemWindows(false);
            m22866a(view, i);
            int childCount = drawerLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                drawerLayout.getChildAt(i2).setFitsSystemWindows(false);
            }
            if (z) {
                m22841e(d);
            } else {
                m22871a(d, i, false);
            }
        }
    }

    /* renamed from: b */
    private static View m22857b(Activity activity, int i, boolean z) {
        ViewGroup viewGroup;
        if (z) {
            viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        } else {
            viewGroup = (ViewGroup) activity.findViewById(16908290);
        }
        View findViewWithTag = viewGroup.findViewWithTag(f6843a);
        if (findViewWithTag != null) {
            if (findViewWithTag.getVisibility() == 8) {
                findViewWithTag.setVisibility(0);
            }
            findViewWithTag.setBackgroundColor(i);
            return findViewWithTag;
        }
        View c = m22850c(activity, i);
        viewGroup.addView(c);
        return c;
    }

    /* renamed from: e */
    private static void m22841e(Activity activity) {
        m22838f(activity.getWindow());
    }

    /* renamed from: f */
    private static void m22838f(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(f6843a);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(8);
        }
    }

    /* renamed from: g */
    private static void m22837g(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(f6843a);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(0);
        }
    }

    /* renamed from: c */
    private static View m22850c(Activity activity, int i) {
        View view = new View(activity);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, m22874a()));
        view.setBackgroundColor(i);
        view.setTag(f6843a);
        return view;
    }

    /* renamed from: f */
    private static void m22839f(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= 21) {
                window.addFlags(Integer.MIN_VALUE);
                if (Build.VERSION.SDK_INT >= 23) {
                    window.getDecorView().setSystemUiVisibility(1280 | (window.getDecorView().getSystemUiVisibility() & 8192));
                } else {
                    window.getDecorView().setSystemUiVisibility(1280);
                }
                window.setStatusBarColor(0);
                return;
            }
            window.addFlags(67108864);
        }
    }

    /* renamed from: b */
    public static int m22860b() {
        TypedValue typedValue = new TypedValue();
        if (Utils.m24103a().getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, Utils.m24103a().getResources().getDisplayMetrics());
        }
        return 0;
    }

    @RequiresPermission("android.permission.EXPAND_STATUS_BAR")
    /* renamed from: a */
    public static void m22861a(boolean z) {
        m22862a(z ? Build.VERSION.SDK_INT <= 16 ? "expand" : "expandNotificationsPanel" : Build.VERSION.SDK_INT <= 16 ? "collapse" : "collapsePanels");
    }

    /* renamed from: a */
    private static void m22862a(String str) {
        try {
            Class.forName("android.app.StatusBarManager").getMethod(str, new Class[0]).invoke(Utils.m24103a().getSystemService("statusbar"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public static int m22852c() {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return system.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: c */
    public static void m22849c(@NonNull Activity activity, boolean z) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19) {
            m22846c(activity.getWindow(), z);
        }
    }

    /* renamed from: c */
    public static void m22846c(@NonNull Window window, boolean z) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (Build.VERSION.SDK_INT >= 19) {
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                int id = childAt.getId();
                if (id != -1 && "navigationBarBackground".equals(Utils.m24103a().getResources().getResourceEntryName(id))) {
                    childAt.setVisibility(z ? 0 : 4);
                }
            }
            if (z) {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() & (-4611));
            } else {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() | 4610);
            }
        }
    }

    /* renamed from: c */
    public static boolean m22851c(@NonNull Activity activity) {
        if (activity != null) {
            return m22854b(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static boolean m22854b(@NonNull Window window) {
        boolean z;
        if (window != null) {
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    z = false;
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                int id = childAt.getId();
                if (id != -1 && "navigationBarBackground".equals(Utils.m24103a().getResources().getResourceEntryName(id)) && childAt.getVisibility() == 0) {
                    z = true;
                    break;
                }
                i++;
            }
            return z ? (viewGroup.getSystemUiVisibility() & 2) == 0 : z;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    /* renamed from: b */
    public static void m22858b(@NonNull Activity activity, @ColorInt int i) {
        if (activity != null) {
            m22864a(activity.getWindow(), i);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    /* renamed from: a */
    public static void m22864a(@NonNull Window window, @ColorInt int i) {
        if (window != null) {
            window.setNavigationBarColor(i);
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    /* renamed from: d */
    public static int m22844d(@NonNull Activity activity) {
        if (activity != null) {
            return m22847c(activity.getWindow());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresApi(21)
    /* renamed from: c */
    public static int m22847c(@NonNull Window window) {
        if (window != null) {
            return window.getNavigationBarColor();
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static boolean m22845d() {
        if (Build.VERSION.SDK_INT >= 17) {
            WindowManager windowManager = (WindowManager) Utils.m24103a().getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            return (point2.y == point.y && point2.x == point.x) ? false : true;
        }
        return !ViewConfiguration.get(Utils.m24103a()).hasPermanentMenuKey() && !KeyCharacterMap.deviceHasKey(4);
    }

    /* renamed from: d */
    private static Activity m22843d(@NonNull View view) {
        if (view != null) {
            for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
            }
            Log.e("BarUtils", "the view's Context is not an Activity.");
            return null;
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
