package com.cyjh.mobileanjian.ipc.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.cyjh.mobileanjian.ipc.view.a */
/* loaded from: classes.dex */
public final class ExToast0 {

    /* renamed from: a */
    public static final int f8727a = 0;

    /* renamed from: b */
    public static final int f8728b = 2;

    /* renamed from: c */
    public static final int f8729c = 4;

    /* renamed from: d */
    private Toast f8730d;

    /* renamed from: e */
    private Context f8731e;

    /* renamed from: i */
    private final int f8735i;

    /* renamed from: j */
    private final int f8736j;

    /* renamed from: k */
    private final int f8737k;

    /* renamed from: l */
    private int f8738l;

    /* renamed from: m */
    private int f8739m;

    /* renamed from: n */
    private Object f8740n;

    /* renamed from: o */
    private Method f8741o;

    /* renamed from: p */
    private Method f8742p;

    /* renamed from: q */
    private WindowManager f8743q;

    /* renamed from: r */
    private WindowManager.LayoutParams f8744r;

    /* renamed from: t */
    private View f8746t;

    /* renamed from: f */
    private boolean f8732f = false;

    /* renamed from: g */
    private int f8733g = -1;

    /* renamed from: h */
    private int f8734h = 2;

    /* renamed from: s */
    private WindowManager.LayoutParams f8745s = null;

    /* renamed from: u */
    private Handler f8747u = new Handler();

    /* renamed from: v */
    private Timer f8748v = new Timer("hideTimer");

    /* renamed from: w */
    private C1345a f8749w = new C1345a(this, (byte) 0);

    /* renamed from: x */
    private Runnable f8750x = new Runnable() { // from class: com.cyjh.mobileanjian.ipc.view.a.1
        @Override // java.lang.Runnable
        public final void run() {
            ExToast0.this.m20602a();
        }
    };

    private ExToast0(Context context) {
        this.f8731e = context;
        Toast toast = new Toast(context);
        this.f8735i = toast.getGravity();
        this.f8736j = toast.getXOffset();
        this.f8737k = toast.getYOffset();
        this.f8738l = this.f8736j;
        this.f8739m = this.f8737k;
    }

    /* renamed from: a */
    private static ExToast0 m20598a(Context context, CharSequence charSequence, int i) {
        Toast makeText = Toast.makeText(context, charSequence, i);
        ExToast0 aVar = new ExToast0(context);
        aVar.f8730d = makeText;
        aVar.f8734h = i;
        return aVar;
    }

    /* renamed from: a */
    private static ExToast0 m20599a(Context context, int i, int i2) {
        Toast makeText = Toast.makeText(context, i, i2);
        ExToast0 aVar = new ExToast0(context);
        aVar.f8730d = makeText;
        aVar.f8734h = i2;
        return aVar;
    }

    /* renamed from: a */
    private void m20594a(CharSequence charSequence, int i, int i2) {
        Toast.makeText(this.f8731e, charSequence, 0).show();
        boolean z = (i < 0 && this.f8738l < 0) || (i2 < 0 && this.f8739m < 0) || (i == this.f8738l && i2 == this.f8739m);
        boolean z2 = i < 0 || i2 < 0;
        this.f8738l = i;
        this.f8739m = i2;
        if (z) {
            m20595a(charSequence);
        } else {
            m20602a();
            this.f8730d = Toast.makeText(this.f8731e, charSequence, 0);
            if (z2) {
                this.f8730d.setGravity(this.f8735i, this.f8736j, this.f8737k);
            } else {
                this.f8730d.setGravity(51, i, i2);
            }
        }
        try {
            if (this.f8732f) {
                this.f8749w.cancel();
                this.f8749w = new C1345a(this, (byte) 0);
                this.f8748v.schedule(this.f8749w, this.f8734h * 1000);
                return;
            }
            try {
                Field declaredField = this.f8730d.getClass().getDeclaredField("mTN");
                declaredField.setAccessible(true);
                this.f8740n = declaredField.get(this.f8730d);
                this.f8741o = this.f8740n.getClass().getMethod("show", new Class[0]);
                this.f8742p = this.f8740n.getClass().getMethod("hide", new Class[0]);
                Field declaredField2 = this.f8740n.getClass().getDeclaredField("mParams");
                declaredField2.setAccessible(true);
                this.f8744r = (WindowManager.LayoutParams) declaredField2.get(this.f8740n);
                if (this.f8745s != null) {
                    this.f8744r.flags = this.f8745s.flags;
                    this.f8744r.width = this.f8745s.width;
                    this.f8744r.height = this.f8745s.height;
                    this.f8744r.gravity = this.f8745s.gravity;
                    this.f8744r.windowAnimations = this.f8745s.windowAnimations;
                }
                if (this.f8733g != -1) {
                    this.f8744r.windowAnimations = this.f8733g;
                }
                Field declaredField3 = this.f8740n.getClass().getDeclaredField("mNextView");
                declaredField3.setAccessible(true);
                declaredField3.set(this.f8740n, this.f8730d.getView());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f8741o.invoke(this.f8740n, new Object[0]);
            this.f8732f = true;
            if (this.f8734h > 0) {
                this.f8749w.cancel();
                this.f8749w = new C1345a(this, (byte) 0);
                this.f8748v.schedule(this.f8749w, this.f8734h * 1000);
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
            m20602a();
            this.f8748v.cancel();
            this.f8748v = new Timer();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m20593b() {
        try {
            if (this.f8732f) {
                this.f8749w.cancel();
                this.f8749w = new C1345a(this, (byte) 0);
                this.f8748v.schedule(this.f8749w, this.f8734h * 1000);
                return;
            }
            try {
                Field declaredField = this.f8730d.getClass().getDeclaredField("mTN");
                declaredField.setAccessible(true);
                this.f8740n = declaredField.get(this.f8730d);
                this.f8741o = this.f8740n.getClass().getMethod("show", new Class[0]);
                this.f8742p = this.f8740n.getClass().getMethod("hide", new Class[0]);
                Field declaredField2 = this.f8740n.getClass().getDeclaredField("mParams");
                declaredField2.setAccessible(true);
                this.f8744r = (WindowManager.LayoutParams) declaredField2.get(this.f8740n);
                if (this.f8745s != null) {
                    this.f8744r.flags = this.f8745s.flags;
                    this.f8744r.width = this.f8745s.width;
                    this.f8744r.height = this.f8745s.height;
                    this.f8744r.gravity = this.f8745s.gravity;
                    this.f8744r.windowAnimations = this.f8745s.windowAnimations;
                }
                if (this.f8733g != -1) {
                    this.f8744r.windowAnimations = this.f8733g;
                }
                Field declaredField3 = this.f8740n.getClass().getDeclaredField("mNextView");
                declaredField3.setAccessible(true);
                declaredField3.set(this.f8740n, this.f8730d.getView());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f8741o.invoke(this.f8740n, new Object[0]);
            this.f8732f = true;
            if (this.f8734h > 0) {
                this.f8749w.cancel();
                this.f8749w = new C1345a(this, (byte) 0);
                this.f8748v.schedule(this.f8749w, this.f8734h * 1000);
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
            m20602a();
            this.f8748v.cancel();
            this.f8748v = new Timer();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m20600a(int i, int i2, int i3) {
        this.f8730d.setGravity(i, i2, i3);
    }

    /* renamed from: a */
    private void m20597a(View view) {
        this.f8730d.setView(view);
    }

    /* renamed from: c */
    private View m20590c() {
        return this.f8730d.getView();
    }

    /* renamed from: a */
    private void m20601a(int i) {
        this.f8734h = i;
    }

    /* renamed from: b */
    private void m20592b(int i) {
        m20595a(this.f8731e.getText(i));
    }

    /* renamed from: a */
    private void m20595a(CharSequence charSequence) {
        this.f8730d.setText(charSequence);
    }

    /* renamed from: d */
    private int m20588d() {
        return this.f8733g;
    }

    /* renamed from: c */
    private void m20589c(int i) {
        this.f8733g = i;
    }

    /* renamed from: a */
    public final void m20602a() {
        if (this.f8732f) {
            try {
                this.f8742p.invoke(this.f8740n, new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
            this.f8732f = false;
        }
    }

    /* renamed from: e */
    private void m20587e() {
        try {
            Field declaredField = this.f8730d.getClass().getDeclaredField("mTN");
            declaredField.setAccessible(true);
            this.f8740n = declaredField.get(this.f8730d);
            this.f8741o = this.f8740n.getClass().getMethod("show", new Class[0]);
            this.f8742p = this.f8740n.getClass().getMethod("hide", new Class[0]);
            Field declaredField2 = this.f8740n.getClass().getDeclaredField("mParams");
            declaredField2.setAccessible(true);
            this.f8744r = (WindowManager.LayoutParams) declaredField2.get(this.f8740n);
            if (this.f8745s != null) {
                this.f8744r.flags = this.f8745s.flags;
                this.f8744r.width = this.f8745s.width;
                this.f8744r.height = this.f8745s.height;
                this.f8744r.gravity = this.f8745s.gravity;
                this.f8744r.windowAnimations = this.f8745s.windowAnimations;
            }
            if (this.f8733g != -1) {
                this.f8744r.windowAnimations = this.f8733g;
            }
            Field declaredField3 = this.f8740n.getClass().getDeclaredField("mNextView");
            declaredField3.setAccessible(true);
            declaredField3.set(this.f8740n, this.f8730d.getView());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m20596a(WindowManager.LayoutParams layoutParams) {
        try {
            Field declaredField = this.f8730d.getClass().getDeclaredField("mTN");
            declaredField.setAccessible(true);
            this.f8740n = declaredField.get(this.f8730d);
            Field declaredField2 = this.f8740n.getClass().getDeclaredField("mParams");
            declaredField2.setAccessible(true);
            declaredField2.set(this.f8740n, layoutParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m20591b(WindowManager.LayoutParams layoutParams) {
        this.f8745s = layoutParams;
    }

    /* compiled from: ExToast0.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.view.a$a */
    /* loaded from: classes.dex */
    private class C1345a extends TimerTask {
        private C1345a() {
        }

        /* synthetic */ C1345a(ExToast0 aVar, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            ExToast0.this.m20602a();
        }
    }
}
