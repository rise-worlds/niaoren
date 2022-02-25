package com.blankj.utilcode.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: com.blankj.utilcode.util.m */
/* loaded from: classes.dex */
public class ClickUtils {

    /* renamed from: a */
    private static final int f6887a = -1;

    /* renamed from: b */
    private static final float f6888b = -0.08f;

    /* renamed from: c */
    private static final long f6889c = 200;

    /* renamed from: a */
    public static void m22503a(View... viewArr) {
        m22499a(viewArr, (float[]) null);
    }

    /* renamed from: a */
    public static void m22499a(View[] viewArr, float[] fArr) {
        if (!(viewArr == null || viewArr.length == 0)) {
            for (int i = 0; i < viewArr.length; i++) {
                if (viewArr[i] != null) {
                    if (fArr == null || i >= fArr.length) {
                        viewArr[i].setTag(-1, Float.valueOf((float) f6888b));
                    } else {
                        viewArr[i].setTag(-1, Float.valueOf(fArr[i]));
                    }
                    viewArr[i].setClickable(true);
                    viewArr[i].setOnTouchListener(View$OnTouchListenerC1025c.m22491a());
                }
            }
        }
    }

    /* renamed from: a */
    public static void m22504a(View view, View.OnClickListener onClickListener) {
        m22501a(new View[]{view}, onClickListener);
    }

    /* renamed from: a */
    public static void m22505a(View view, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        m22502a(new View[]{view}, j, onClickListener);
    }

    /* renamed from: a */
    public static void m22501a(View[] viewArr, View.OnClickListener onClickListener) {
        m22502a(viewArr, (long) f6889c, onClickListener);
    }

    /* renamed from: a */
    public static void m22502a(View[] viewArr, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        m22500a(viewArr, false, j, onClickListener);
    }

    /* renamed from: b */
    public static void m22497b(View view, View.OnClickListener onClickListener) {
        m22495b(new View[]{view}, onClickListener);
    }

    /* renamed from: b */
    public static void m22498b(View view, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        m22496b(new View[]{view}, j, onClickListener);
    }

    /* renamed from: b */
    public static void m22495b(View[] viewArr, View.OnClickListener onClickListener) {
        m22496b(viewArr, (long) f6889c, onClickListener);
    }

    /* renamed from: b */
    public static void m22496b(View[] viewArr, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        m22500a(viewArr, true, j, onClickListener);
    }

    /* renamed from: a */
    private static void m22500a(View[] viewArr, boolean z, @IntRange(from = 0) long j, final View.OnClickListener onClickListener) {
        if (!(viewArr == null || viewArr.length == 0 || onClickListener == null)) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setOnClickListener(new AbstractView$OnClickListenerC1023b(z, j) { // from class: com.blankj.utilcode.util.m.1
                        @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
                        /* renamed from: a */
                        public void mo3004a(View view2) {
                            onClickListener.onClick(view2);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ClickUtils.java */
    /* renamed from: com.blankj.utilcode.util.m$c */
    /* loaded from: classes.dex */
    public static class View$OnTouchListenerC1025c implements View.OnTouchListener {
        /* renamed from: a */
        public static View$OnTouchListenerC1025c m22491a() {
            return C1022a.f6891a;
        }

        private View$OnTouchListenerC1025c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                m22490a(view, true);
            } else if (action == 1 || action == 3) {
                m22490a(view, false);
            }
            return false;
        }

        /* renamed from: a */
        private void m22490a(View view, boolean z) {
            Object tag = view.getTag(-1);
            if (tag instanceof Float) {
                float f = 1.0f;
                if (z) {
                    f = 1.0f + ((Float) tag).floatValue();
                }
                view.animate().scaleX(f).scaleY(f).setDuration(100L).start();
            }
        }
    }

    /* compiled from: ClickUtils.java */
    /* renamed from: com.blankj.utilcode.util.m$b */
    /* loaded from: classes.dex */
    public static abstract class AbstractView$OnClickListenerC1023b implements View.OnClickListener {

        /* renamed from: a */
        private static final int f6892a = 2130706431;

        /* renamed from: b */
        private static boolean f6893b = true;

        /* renamed from: c */
        private static final Runnable f6894c = new Runnable() { // from class: com.blankj.utilcode.util.m.b.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = AbstractView$OnClickListenerC1023b.f6893b = true;
            }
        };

        /* renamed from: d */
        private long f6895d;

        /* renamed from: e */
        private boolean f6896e;

        /* renamed from: a */
        public abstract void mo3004a(View view);

        /* renamed from: a */
        private static boolean m22493a(@NonNull View view, long j) {
            if (view != null) {
                long currentTimeMillis = System.currentTimeMillis();
                Object tag = view.getTag(f6892a);
                if (!(tag instanceof Long)) {
                    view.setTag(f6892a, Long.valueOf(currentTimeMillis));
                    return true;
                } else if (currentTimeMillis - ((Long) tag).longValue() <= j) {
                    return false;
                } else {
                    view.setTag(f6892a, Long.valueOf(currentTimeMillis));
                    return true;
                }
            } else {
                throw new NullPointerException("Argument 'view' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public AbstractView$OnClickListenerC1023b() {
            this(true, ClickUtils.f6889c);
        }

        public AbstractView$OnClickListenerC1023b(boolean z) {
            this(z, ClickUtils.f6889c);
        }

        public AbstractView$OnClickListenerC1023b(long j) {
            this(true, j);
        }

        public AbstractView$OnClickListenerC1023b(boolean z, long j) {
            this.f6896e = z;
            this.f6895d = j;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.f6896e) {
                if (f6893b) {
                    f6893b = false;
                    view.postDelayed(f6894c, this.f6895d);
                    mo3004a(view);
                }
            } else if (m22493a(view, this.f6895d)) {
                mo3004a(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ClickUtils.java */
    /* renamed from: com.blankj.utilcode.util.m$a */
    /* loaded from: classes.dex */
    public static class C1022a {

        /* renamed from: a */
        private static final View$OnTouchListenerC1025c f6891a = new View$OnTouchListenerC1025c();

        private C1022a() {
        }
    }
}
