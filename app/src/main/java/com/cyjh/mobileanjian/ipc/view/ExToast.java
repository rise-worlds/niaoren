package com.cyjh.mobileanjian.ipc.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.WindowManager;
import android.widget.Toast;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mqsdk.C1375R;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class ExToast {
    public static final int LENGTH_LONG = 3500;
    public static final int LENGTH_SHORT = 2000;

    /* renamed from: g */
    private static final int f8713g = 51;

    /* renamed from: a */
    private WindowManager f8714a;

    /* renamed from: d */
    private Toast f8717d;

    /* renamed from: e */
    private boolean f8718e;

    /* renamed from: f */
    private int f8719f;

    /* renamed from: h */
    private int f8720h;

    /* renamed from: k */
    private CharSequence f8723k;

    /* renamed from: i */
    private int f8721i = -1;

    /* renamed from: j */
    private int f8722j = -1;

    /* renamed from: l */
    private int f8724l = 2000;

    /* renamed from: m */
    private C1343a f8725m = new C1343a(this, (byte) 0);

    /* renamed from: c */
    private Timer f8716c = new Timer();

    /* renamed from: b */
    private WindowManager.LayoutParams f8715b = new WindowManager.LayoutParams();

    public ExToast(Context context) {
        this.f8714a = (WindowManager) context.getSystemService("window");
        this.f8717d = Toast.makeText(context, "", 0);
        WindowManager.LayoutParams layoutParams = this.f8715b;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8715b.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8715b.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8715b.type = 2005;
        }
        this.f8715b.flags = 152;
        int identifier = Resources.getSystem().getIdentifier("config_toastDefaultGravity", "Integer", "android");
        this.f8719f = identifier == 0 ? 81 : context.getResources().getInteger(identifier);
        int identifier2 = Resources.getSystem().getIdentifier("toast_y_offset", "dimen", "android");
        this.f8720h = context.getResources().getDimensionPixelSize(identifier2 == 0 ? C1375R.dimen.toast_y_offset : identifier2);
        this.f8715b.setTitle("Toast");
    }

    public void setText(CharSequence charSequence) {
        this.f8723k = charSequence;
        this.f8717d.setText(charSequence);
    }

    public void setDuration(int i) {
        this.f8724l = i;
    }

    public static ExToast makeText(Context context, CharSequence charSequence, int i) {
        ExToast exToast = new ExToast(context);
        exToast.setText(charSequence);
        exToast.setDuration(i);
        return exToast;
    }

    public static ExToast makeText(Context context, int i, int i2) {
        return makeText(context, context.getString(i), i2);
    }

    /* renamed from: a */
    private void m20603a(Context context) {
        this.f8715b = new WindowManager.LayoutParams();
        WindowManager.LayoutParams layoutParams = this.f8715b;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8715b.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8715b.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8715b.type = 2005;
        }
        this.f8715b.flags = 152;
        int identifier = Resources.getSystem().getIdentifier("config_toastDefaultGravity", "Integer", "android");
        this.f8719f = identifier == 0 ? 81 : context.getResources().getInteger(identifier);
        int identifier2 = Resources.getSystem().getIdentifier("toast_y_offset", "dimen", "android");
        Resources resources = context.getResources();
        if (identifier2 == 0) {
            identifier2 = C1375R.dimen.toast_y_offset;
        }
        this.f8720h = resources.getDimensionPixelSize(identifier2);
        this.f8715b.setTitle("Toast");
    }

    /* renamed from: a */
    private void m20604a(int i, CharSequence charSequence, int i2, int i3, boolean z) {
        WindowManager.LayoutParams layoutParams = this.f8715b;
        layoutParams.gravity = i;
        if (i == this.f8719f) {
            layoutParams.x = 0;
            layoutParams.y = this.f8720h;
        } else {
            layoutParams.x = i2;
            layoutParams.y = i3;
        }
        setText(charSequence);
        if (!this.f8718e) {
            this.f8714a.addView(this.f8717d.getView(), this.f8715b);
        } else if (z) {
            this.f8714a.updateViewLayout(this.f8717d.getView(), this.f8715b);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0021 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void show(java.lang.CharSequence r12, int r13, int r14, int r15) {
        /*
            r11 = this;
            android.widget.Toast r0 = r11.f8717d
            monitor-enter(r0)
            r1 = 0
            r2 = 1
            if (r13 >= 0) goto L_0x000e
            int r3 = r11.f8721i     // Catch: all -> 0x000c
            if (r3 < 0) goto L_0x001c
            goto L_0x000e
        L_0x000c:
            r12 = move-exception
            goto L_0x0062
        L_0x000e:
            if (r14 >= 0) goto L_0x0014
            int r3 = r11.f8722j     // Catch: all -> 0x000c
            if (r3 < 0) goto L_0x001c
        L_0x0014:
            int r3 = r11.f8721i     // Catch: all -> 0x000c
            if (r13 != r3) goto L_0x001e
            int r3 = r11.f8722j     // Catch: all -> 0x000c
            if (r14 != r3) goto L_0x001e
        L_0x001c:
            r3 = 1
            goto L_0x001f
        L_0x001e:
            r3 = 0
        L_0x001f:
            if (r13 < 0) goto L_0x0029
            if (r14 >= 0) goto L_0x0024
            goto L_0x0029
        L_0x0024:
            r4 = 51
            r6 = 51
            goto L_0x002c
        L_0x0029:
            int r4 = r11.f8719f     // Catch: all -> 0x000c
            r6 = r4
        L_0x002c:
            r11.f8721i = r13     // Catch: all -> 0x000c
            r11.f8722j = r14     // Catch: all -> 0x000c
            com.cyjh.mobileanjian.ipc.view.ExToast$a r4 = r11.f8725m     // Catch: all -> 0x000c
            r4.cancel()     // Catch: all -> 0x000c
            if (r3 != 0) goto L_0x0039
            r10 = 1
            goto L_0x003a
        L_0x0039:
            r10 = 0
        L_0x003a:
            r5 = r11
            r7 = r12
            r8 = r13
            r9 = r14
            r5.m20604a(r6, r7, r8, r9, r10)     // Catch: all -> 0x000c
            r11.f8718e = r2     // Catch: all -> 0x000c
            com.cyjh.mobileanjian.ipc.view.ExToast$a r12 = new com.cyjh.mobileanjian.ipc.view.ExToast$a     // Catch: all -> 0x000c
            r12.<init>(r11, r1)     // Catch: all -> 0x000c
            r11.f8725m = r12     // Catch: all -> 0x000c
            r12 = 2000(0x7d0, float:2.803E-42)
            if (r15 >= 0) goto L_0x0052
            r15 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0058
        L_0x0052:
            if (r15 < 0) goto L_0x0058
            if (r15 >= r12) goto L_0x0058
            r15 = 2000(0x7d0, float:2.803E-42)
        L_0x0058:
            java.util.Timer r12 = r11.f8716c     // Catch: all -> 0x000c
            com.cyjh.mobileanjian.ipc.view.ExToast$a r13 = r11.f8725m     // Catch: all -> 0x000c
            long r14 = (long) r15     // Catch: all -> 0x000c
            r12.schedule(r13, r14)     // Catch: all -> 0x000c
            monitor-exit(r0)     // Catch: all -> 0x000c
            return
        L_0x0062:
            monitor-exit(r0)     // Catch: all -> 0x000c
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.ipc.view.ExToast.show(java.lang.CharSequence, int, int, int):void");
    }

    public void show() {
        m20604a(this.f8719f, this.f8723k, 0, 0, true);
        this.f8725m = new C1343a(this, (byte) 0);
        this.f8716c.schedule(this.f8725m, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.cyjh.mobileanjian.ipc.view.ExToast$a */
    /* loaded from: classes.dex */
    public class C1343a extends TimerTask {
        private C1343a() {
        }

        /* synthetic */ C1343a(ExToast exToast, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            ExToast.this.hide();
        }
    }

    public void hide() {
        synchronized (this.f8717d) {
            this.f8725m.cancel();
            if (this.f8717d.getView().getParent() != null) {
                this.f8714a.removeView(this.f8717d.getView());
            }
            this.f8718e = false;
        }
    }
}
