package com.tendcloud.tenddata;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bb */
/* loaded from: classes2.dex */
public class C2696bb {

    /* renamed from: a */
    private WeakHashMap f13559a = new WeakHashMap();

    /* renamed from: b */
    private final Handler f13560b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    private final Map f13561c = new HashMap();

    /* renamed from: d */
    private final Set f13562d = new HashSet();

    public void add(Activity activity) {
        try {
            Thread.currentThread();
            Looper.getMainLooper().getThread();
            this.f13559a.put(activity, activity.getLocalClassName());
            m16275c();
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void remove(Activity activity) {
        try {
            Thread.currentThread();
            Looper.getMainLooper().getThread();
            this.f13559a.remove(activity);
            synchronized (this.f13562d) {
                for (ViewTreeObserver$OnGlobalLayoutListenerC2697a aVar : this.f13562d) {
                    aVar.kill();
                }
                this.f13562d.clear();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16277a(Map map) {
        try {
            synchronized (this.f13562d) {
                for (ViewTreeObserver$OnGlobalLayoutListenerC2697a aVar : this.f13562d) {
                    aVar.kill();
                }
                this.f13562d.clear();
            }
            synchronized (this.f13561c) {
                this.f13561c.clear();
                this.f13561c.putAll(map);
            }
            m16275c();
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: c */
    private void m16275c() {
        try {
            if (Thread.currentThread() == this.f13560b.getLooper().getThread()) {
                m16274d();
            } else {
                this.f13560b.post(new RunnableC2698bc(this));
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m16274d() {
        List list;
        List list2;
        try {
            for (Activity activity : m16280a()) {
                String canonicalName = activity.getClass().getCanonicalName();
                View rootView = activity.getWindow().getDecorView().getRootView();
                synchronized (this.f13561c) {
                    list = (List) this.f13561c.get(canonicalName);
                    list2 = (List) this.f13561c.get(null);
                }
                if (list != null) {
                    m16279a(rootView, list);
                }
                if (list2 != null) {
                    m16279a(rootView, list2);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m16279a(View view, List list) {
        try {
            synchronized (this.f13562d) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    this.f13562d.add(new ViewTreeObserver$OnGlobalLayoutListenerC2697a(view, (AbstractC2732bp) list.get(i), this.f13560b));
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bb$a */
    /* loaded from: classes2.dex */
    public static class ViewTreeObserver$OnGlobalLayoutListenerC2697a implements ViewTreeObserver.OnGlobalLayoutListener, Runnable {
        private boolean mAlive = true;
        private volatile boolean mDying = false;
        private final AbstractC2732bp mEdit;
        private final Handler mHandler;
        private final WeakReference mViewRoot;

        ViewTreeObserver$OnGlobalLayoutListenerC2697a(View view, AbstractC2732bp bpVar, Handler handler) {
            this.mEdit = bpVar;
            this.mViewRoot = new WeakReference(view);
            this.mHandler = handler;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this);
            }
            run();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            run();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.mAlive) {
                    View view = (View) this.mViewRoot.get();
                    if (view != null && !this.mDying) {
                        this.mEdit.visit(view);
                        this.mHandler.removeCallbacks(this);
                        return;
                    }
                    cleanUp();
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        void kill() {
            try {
                this.mDying = true;
                this.mHandler.post(this);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void cleanUp() {
            if (this.mAlive) {
                View view = (View) this.mViewRoot.get();
                if (view != null) {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }
                }
                this.mEdit.cleanup();
            }
            this.mAlive = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Set m16280a() {
        Thread.currentThread();
        Looper.getMainLooper().getThread();
        return Collections.unmodifiableSet(this.f13559a.keySet());
    }

    /* renamed from: b */
    public boolean m16276b() {
        Thread.currentThread();
        Looper.getMainLooper().getThread();
        return this.f13559a.isEmpty();
    }
}
