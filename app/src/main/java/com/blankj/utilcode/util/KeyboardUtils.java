package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import java.lang.reflect.Field;

/* renamed from: com.blankj.utilcode.util.ad */
/* loaded from: classes.dex */
public final class KeyboardUtils {

    /* renamed from: a */
    private static int f6629a;

    /* compiled from: KeyboardUtils.java */
    /* renamed from: com.blankj.utilcode.util.ad$a */
    /* loaded from: classes.dex */
    public interface AbstractC0960a {
        /* renamed from: a */
        void m23773a(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m23793a() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.m24103a().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 1);
        }
    }

    /* renamed from: a */
    public static void m23790a(View view) {
        m23789a(view, 2);
    }

    /* renamed from: a */
    public static void m23789a(View view, int i) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.m24103a().getSystemService("input_method");
        if (inputMethodManager != null) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            final Handler handler = new Handler();
            inputMethodManager.showSoftInput(view, i, new ResultReceiver(handler) { // from class: com.blankj.utilcode.util.KeyboardUtils$1
                @Override // android.os.ResultReceiver
                protected void onReceiveResult(int i2, Bundle bundle) {
                    if (i2 == 1 || i2 == 3) {
                        KeyboardUtils.m23786b();
                    }
                }
            });
            inputMethodManager.toggleSoftInput(2, 1);
        }
    }

    /* renamed from: a */
    public static void m23792a(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        m23784b(currentFocus);
    }

    /* renamed from: b */
    public static void m23784b(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.m24103a().getSystemService("input_method");
        if (inputMethodManager != null) {
            IBinder windowToken = view.getWindowToken();
            final Handler handler = new Handler();
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0, new ResultReceiver(handler) { // from class: com.blankj.utilcode.util.KeyboardUtils$2
                @Override // android.os.ResultReceiver
                protected void onReceiveResult(int i, Bundle bundle) {
                    if (i == 0 || i == 2) {
                        KeyboardUtils.m23786b();
                    }
                }
            });
        }
    }

    /* renamed from: b */
    public static void m23786b() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.m24103a().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 0);
        }
    }

    /* renamed from: b */
    public static boolean m23785b(@NonNull Activity activity) {
        if (activity != null) {
            return m23775e(activity.getWindow()) > 0;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static int m23775e(@NonNull Window window) {
        if (window != null) {
            View decorView = window.getDecorView();
            if (decorView == null) {
                return 0;
            }
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
            int abs = Math.abs(decorView.getBottom() - rect.bottom);
            if (abs > m23776e()) {
                return abs - f6629a;
            }
            f6629a = abs;
            return 0;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23791a(@NonNull Activity activity, @NonNull AbstractC0960a aVar) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (aVar != null) {
            m23787a(activity.getWindow(), aVar);
        } else {
            throw new NullPointerException("Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23787a(@NonNull final Window window, @NonNull final AbstractC0960a aVar) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (aVar != null) {
            if ((window.getAttributes().flags & 512) != 0) {
                window.clearFlags(512);
            }
            final int[] iArr = {m23775e(window)};
            ((FrameLayout) window.findViewById(16908290)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.blankj.utilcode.util.ad.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    int e = KeyboardUtils.m23775e(window);
                    if (iArr[0] != e) {
                        aVar.m23773a(e);
                        iArr[0] = e;
                    }
                }
            });
        } else {
            throw new NullPointerException("Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static void m23781c(@NonNull Activity activity) {
        if (activity != null) {
            m23788a(activity.getWindow());
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23788a(@NonNull final Window window) {
        if (window != null) {
            FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
            final View childAt = frameLayout.getChildAt(0);
            final int paddingBottom = childAt.getPaddingBottom();
            final int[] iArr = {m23774f(window)};
            frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.blankj.utilcode.util.ad.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    int f = KeyboardUtils.m23774f(window);
                    if (iArr[0] != f) {
                        View view = childAt;
                        view.setPadding(view.getPaddingLeft(), childAt.getPaddingTop(), childAt.getPaddingRight(), paddingBottom + KeyboardUtils.m23775e(window));
                        iArr[0] = f;
                    }
                }
            });
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static int m23774f(Window window) {
        View findViewById = window.findViewById(16908290);
        if (findViewById == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= m23779d() + m23776e()) {
            return 0;
        }
        return abs;
    }

    /* renamed from: d */
    public static void m23778d(@NonNull Activity activity) {
        if (activity != null) {
            m23783b(activity.getWindow());
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m23783b(@NonNull Window window) {
        if (window != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) Utils.m24103a().getSystemService("input_method");
            if (inputMethodManager != null) {
                for (String str : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
                    try {
                        Field declaredField = InputMethodManager.class.getDeclaredField(str);
                        if (declaredField != null) {
                            if (!declaredField.isAccessible()) {
                                declaredField.setAccessible(true);
                            }
                            Object obj = declaredField.get(inputMethodManager);
                            if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                                declaredField.set(inputMethodManager, null);
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23782c() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }

    /* renamed from: d */
    private static int m23779d() {
        Resources system = Resources.getSystem();
        return system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* renamed from: e */
    private static int m23776e() {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return system.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
