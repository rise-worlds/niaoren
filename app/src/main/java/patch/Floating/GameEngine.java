package patch.Floating;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.tencent.smtt.sdk.TbsConfig;

/* renamed from: patch.Floating.b */
/* loaded from: classes2.dex */
public class GameEngine {

    /* renamed from: b */
    private static Activity f14989b;

    /* renamed from: c */
    private static GameEngine f14990c;

    /* renamed from: a */
    private TouchCatchLayout f14991a;

    /* renamed from: d */
    private final Object f14992d = new Object();

    /* renamed from: e */
    private int f14993e = 0;

    /* compiled from: GameEngine.java */
    /* renamed from: patch.Floating.b$a */
    /* loaded from: classes2.dex */
    class RunnableC3279a implements Runnable {
        RunnableC3279a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Activity a = GameEngine.m14580b().m14584a();
            if (a != null) {
                ViewGroup i = GameEngine.this.m14569i(a);
                if (i == null) {
                    GameEngine.this.f14991a = null;
                } else if (i == null || !(i instanceof TouchCatchLayout)) {
                    GameEngine.this.f14991a = null;
                } else {
                    GameEngine.this.f14991a = (TouchCatchLayout) i;
                }
            }
        }
    }

    /* renamed from: a */
    public Activity m14584a() {
        Activity activity = f14989b;
        if (activity == null || activity.isDestroyed() || f14989b.isFinishing()) {
            return null;
        }
        return f14989b;
    }

    /* renamed from: a */
    public void m14583a(Activity activity) {
        synchronized (this) {
            m14570h(activity);
        }
    }

    /* renamed from: b */
    public void m14579b(Activity activity) {
        synchronized (this) {
            synchronized (this.f14992d) {
                this.f14993e++;
                f14989b = activity;
            }
        }
    }

    /* renamed from: c */
    public void m14577c(Activity activity) {
        synchronized (this) {
            f14989b = activity;
            if (activity != null && activity.getPackageName().contains(TbsConfig.APP_WX)) {
                FloatingViewManager.m14586e().m14590a(true);
            }
            FloatingViewManager.m14586e().m14587d();
        }
    }

    /* renamed from: d */
    public void m14575d(Activity activity) {
        synchronized (this) {
            FloatingViewManager.m14586e().m14589b();
        }
    }

    /* renamed from: e */
    public void m14573e(Activity activity) {
        synchronized (this) {
            synchronized (this.f14992d) {
                this.f14993e--;
                if (!(activity == null || f14989b == null || !activity.equals(f14989b))) {
                    f14989b = null;
                }
            }
        }
    }

    /* renamed from: f */
    public void m14572f(Activity activity) {
        synchronized (this) {
            if (activity != null) {
                if (f14989b != null && activity.equals(f14989b)) {
                    f14989b = null;
                }
            }
        }
    }

    /* renamed from: b */
    public static synchronized GameEngine m14580b() {
        GameEngine bVar;
        synchronized (GameEngine.class) {
            if (f14990c == null) {
                f14990c = new GameEngine();
            }
            bVar = f14990c;
        }
        return bVar;
    }

    /* renamed from: c */
    public TouchCatchLayout m14578c() {
        if (Looper.getMainLooper().getThread().equals(Thread.currentThread())) {
            new RunnableC3279a().run();
        } else {
            new Handler(Looper.getMainLooper()).post(new RunnableC3279a());
        }
        return this.f14991a;
    }

    /* renamed from: d */
    public Context m14576d() {
        return FloatingViewManager.m14586e().m14592a();
    }

    /* renamed from: g */
    private ViewGroup m14571g(Activity activity) {
        ViewGroup viewGroup;
        View findViewById = activity.findViewById(16908290);
        while (true) {
            viewGroup = (ViewGroup) findViewById;
            ViewParent parent = viewGroup.getParent();
            if (parent == null || !(parent instanceof ViewGroup)) {
                break;
            }
            findViewById = parent;
        }
        return viewGroup;
    }

    /* renamed from: h */
    private void m14570h(Activity activity) {
        ViewGroup g = m14571g(activity);
        if (g != null && (g instanceof ViewGroup)) {
            View[] viewArr = new View[g.getChildCount()];
            for (int i = 0; i < g.getChildCount(); i++) {
                viewArr[i] = g.getChildAt(i);
            }
            if (viewArr.length != 0) {
                TouchCatchLayout touchCatchLayout = new TouchCatchLayout(activity, 0);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                g.removeAllViews();
                for (View view : viewArr) {
                    touchCatchLayout.addView(view);
                }
                g.addView(touchCatchLayout, layoutParams);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public ViewGroup m14569i(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        while (true) {
            ViewParent parent = findViewById.getParent();
            if (parent != null && (parent instanceof TouchCatchLayout)) {
                return (ViewGroup) parent;
            }
            if (parent == null || !(parent instanceof ViewGroup)) {
                return null;
            }
            findViewById = (View) parent;
        }
    }

    /* renamed from: e */
    public boolean m14574e() {
        boolean z;
        synchronized (this.f14992d) {
            z = this.f14993e > 0;
        }
        return z;
    }
}
