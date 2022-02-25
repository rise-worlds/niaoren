package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.p003v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Field;

/* renamed from: com.blankj.utilcode.util.bc */
/* loaded from: classes.dex */
public final class ToastUtils {

    /* renamed from: a */
    private static final int f6812a = -16777217;

    /* renamed from: b */
    private static final String f6813b = "null";

    /* renamed from: c */
    private static AbstractC1006b f6814c = null;

    /* renamed from: d */
    private static int f6815d = -1;

    /* renamed from: e */
    private static int f6816e = -1;

    /* renamed from: f */
    private static int f6817f = -1;

    /* renamed from: g */
    private static int f6818g = -16777217;

    /* renamed from: h */
    private static int f6819h = -1;

    /* renamed from: i */
    private static int f6820i = -16777217;

    /* renamed from: j */
    private static int f6821j = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ToastUtils.java */
    /* renamed from: com.blankj.utilcode.util.bc$b */
    /* loaded from: classes.dex */
    public interface AbstractC1006b {
        /* renamed from: a */
        View mo23005a();

        /* renamed from: a */
        void mo23004a(int i);

        /* renamed from: a */
        void mo23003a(int i, int i2, int i3);

        /* renamed from: a */
        void mo23002a(View view);

        /* renamed from: a */
        void mo23001a(CharSequence charSequence);

        /* renamed from: b */
        void mo22996b();

        /* renamed from: b */
        void mo23000b(@StringRes int i);

        /* renamed from: c */
        void mo22995c();
    }

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m23037a(int i, int i2, int i3) {
        f6815d = i;
        f6816e = i2;
        f6817f = i3;
    }

    /* renamed from: a */
    public static void m23039a(@ColorInt int i) {
        f6818g = i;
    }

    /* renamed from: b */
    public static void m23025b(@DrawableRes int i) {
        f6819h = i;
    }

    /* renamed from: c */
    public static void m23018c(@ColorInt int i) {
        f6820i = i;
    }

    /* renamed from: d */
    public static void m23016d(int i) {
        f6821j = i;
    }

    /* renamed from: a */
    public static void m23030a(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = f6813b;
        }
        m23029a(charSequence, 0);
    }

    /* renamed from: e */
    public static void m23014e(@StringRes int i) {
        m23038a(i, 0);
    }

    /* renamed from: a */
    public static void m23035a(@StringRes int i, Object... objArr) {
        m23036a(i, 0, objArr);
    }

    /* renamed from: a */
    public static void m23027a(String str, Object... objArr) {
        m23028a(str, 0, objArr);
    }

    /* renamed from: b */
    public static void m23021b(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = f6813b;
        }
        m23029a(charSequence, 1);
    }

    /* renamed from: f */
    public static void m23012f(@StringRes int i) {
        m23038a(i, 1);
    }

    /* renamed from: b */
    public static void m23024b(@StringRes int i, Object... objArr) {
        m23036a(i, 1, objArr);
    }

    /* renamed from: b */
    public static void m23020b(String str, Object... objArr) {
        m23028a(str, 1, objArr);
    }

    /* renamed from: g */
    public static View m23010g(@LayoutRes int i) {
        return m23034a(m23006i(i));
    }

    /* renamed from: a */
    public static View m23034a(View view) {
        m23033a(view, 0);
        return view;
    }

    /* renamed from: h */
    public static View m23008h(@LayoutRes int i) {
        return m23023b(m23006i(i));
    }

    /* renamed from: b */
    public static View m23023b(View view) {
        m23033a(view, 1);
        return view;
    }

    /* renamed from: a */
    public static void m23040a() {
        AbstractC1006b bVar = f6814c;
        if (bVar != null) {
            bVar.mo22995c();
        }
    }

    /* renamed from: a */
    private static void m23038a(int i, int i2) {
        try {
            m23029a(Utils.m24103a().getResources().getText(i), i2);
        } catch (Exception unused) {
            m23029a(String.valueOf(i), i2);
        }
    }

    /* renamed from: a */
    private static void m23036a(int i, int i2, Object... objArr) {
        try {
            m23029a(String.format(Utils.m24103a().getResources().getText(i).toString(), objArr), i2);
        } catch (Exception unused) {
            m23029a(String.valueOf(i), i2);
        }
    }

    /* renamed from: a */
    private static void m23028a(String str, int i, Object... objArr) {
        String str2;
        if (str == null) {
            str2 = f6813b;
        } else {
            str2 = String.format(str, objArr);
            if (str2 == null) {
                str2 = f6813b;
            }
        }
        m23029a(str2, i);
    }

    /* renamed from: a */
    private static void m23029a(final CharSequence charSequence, final int i) {
        Utils.m24099a(new Runnable() { // from class: com.blankj.utilcode.util.bc.1
            @Override // java.lang.Runnable
            @SuppressLint({"ShowToast"})
            public void run() {
                ToastUtils.m23040a();
                AbstractC1006b unused = ToastUtils.f6814c = C1009d.m22998a(Utils.m24103a(), charSequence, i);
                View a = ToastUtils.f6814c.mo23005a();
                if (a != null) {
                    TextView textView = (TextView) a.findViewById(16908299);
                    if (ToastUtils.f6820i != ToastUtils.f6812a) {
                        textView.setTextColor(ToastUtils.f6820i);
                    }
                    if (ToastUtils.f6821j != -1) {
                        textView.setTextSize(ToastUtils.f6821j);
                    }
                    if (!(ToastUtils.f6815d == -1 && ToastUtils.f6816e == -1 && ToastUtils.f6817f == -1)) {
                        ToastUtils.f6814c.mo23003a(ToastUtils.f6815d, ToastUtils.f6816e, ToastUtils.f6817f);
                    }
                    ToastUtils.m23022b(textView);
                    ToastUtils.f6814c.mo22996b();
                }
            }
        });
    }

    /* renamed from: a */
    private static void m23033a(final View view, final int i) {
        Utils.m24099a(new Runnable() { // from class: com.blankj.utilcode.util.bc.2
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.m23040a();
                AbstractC1006b unused = ToastUtils.f6814c = C1009d.m22999a(Utils.m24103a());
                ToastUtils.f6814c.mo23002a(view);
                ToastUtils.f6814c.mo23004a(i);
                if (!(ToastUtils.f6815d == -1 && ToastUtils.f6816e == -1 && ToastUtils.f6817f == -1)) {
                    ToastUtils.f6814c.mo23003a(ToastUtils.f6815d, ToastUtils.f6816e, ToastUtils.f6817f);
                }
                ToastUtils.m23007i();
                ToastUtils.f6814c.mo22996b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public static void m23007i() {
        if (f6819h != -1) {
            f6814c.mo23005a().setBackgroundResource(f6819h);
        } else if (f6818g != f6812a) {
            View a = f6814c.mo23005a();
            Drawable background = a.getBackground();
            if (background != null) {
                background.setColorFilter(new PorterDuffColorFilter(f6818g, PorterDuff.Mode.SRC_IN));
            } else if (Build.VERSION.SDK_INT >= 16) {
                a.setBackground(new ColorDrawable(f6818g));
            } else {
                a.setBackgroundDrawable(new ColorDrawable(f6818g));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m23022b(TextView textView) {
        if (f6819h != -1) {
            f6814c.mo23005a().setBackgroundResource(f6819h);
            textView.setBackgroundColor(0);
        } else if (f6818g != f6812a) {
            View a = f6814c.mo23005a();
            Drawable background = a.getBackground();
            Drawable background2 = textView.getBackground();
            if (background != null && background2 != null) {
                background.setColorFilter(new PorterDuffColorFilter(f6818g, PorterDuff.Mode.SRC_IN));
                textView.setBackgroundColor(0);
            } else if (background != null) {
                background.setColorFilter(new PorterDuffColorFilter(f6818g, PorterDuff.Mode.SRC_IN));
            } else if (background2 != null) {
                background2.setColorFilter(new PorterDuffColorFilter(f6818g, PorterDuff.Mode.SRC_IN));
            } else {
                a.setBackgroundColor(f6818g);
            }
        }
    }

    /* renamed from: i */
    private static View m23006i(@LayoutRes int i) {
        return ((LayoutInflater) Utils.m24103a().getSystemService("layout_inflater")).inflate(i, (ViewGroup) null);
    }

    /* compiled from: ToastUtils.java */
    /* renamed from: com.blankj.utilcode.util.bc$d */
    /* loaded from: classes.dex */
    static class C1009d {
        C1009d() {
        }

        /* renamed from: a */
        static AbstractC1006b m22998a(Context context, CharSequence charSequence, int i) {
            if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                return new C1007c(m22997b(context, charSequence, i));
            }
            return new C1010e(m22997b(context, charSequence, i));
        }

        /* renamed from: a */
        static AbstractC1006b m22999a(Context context) {
            if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                return new C1007c(new Toast(context));
            }
            return new C1010e(new Toast(context));
        }

        /* renamed from: b */
        private static Toast m22997b(Context context, CharSequence charSequence, int i) {
            Toast makeText = Toast.makeText(context, "", i);
            makeText.setText(charSequence);
            return makeText;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ToastUtils.java */
    /* renamed from: com.blankj.utilcode.util.bc$c */
    /* loaded from: classes.dex */
    public static class C1007c extends AbstractC1005a {
        C1007c(Toast toast) {
            super(toast);
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    Field declaredField = Toast.class.getDeclaredField("mTN");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(toast);
                    Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, new HandlerC1008a((Handler) declaredField2.get(obj)));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: b */
        public void mo22996b() {
            this.f6826a.show();
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: c */
        public void mo22995c() {
            this.f6826a.cancel();
        }

        /* compiled from: ToastUtils.java */
        /* renamed from: com.blankj.utilcode.util.bc$c$a */
        /* loaded from: classes.dex */
        static class HandlerC1008a extends Handler {

            /* renamed from: a */
            private Handler f6827a;

            HandlerC1008a(Handler handler) {
                this.f6827a = handler;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                this.f6827a.handleMessage(message);
            }

            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                try {
                    this.f6827a.dispatchMessage(message);
                } catch (Exception e) {
                    Log.e("ToastUtils", e.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ToastUtils.java */
    /* renamed from: com.blankj.utilcode.util.bc$e */
    /* loaded from: classes.dex */
    public static class C1010e extends AbstractC1005a {

        /* renamed from: e */
        private static final Utils.AbstractC0954c f6828e = new Utils.AbstractC0954c() { // from class: com.blankj.utilcode.util.bc.e.1
            @Override // com.blankj.utilcode.util.Utils.AbstractC0954c
            /* renamed from: a */
            public void mo22994a(Activity activity) {
                if (ToastUtils.f6814c != null) {
                    activity.getWindow().getDecorView().setVisibility(8);
                    ToastUtils.f6814c.mo22995c();
                }
            }
        };

        /* renamed from: b */
        private View f6829b;

        /* renamed from: c */
        private WindowManager f6830c;

        /* renamed from: d */
        private WindowManager.LayoutParams f6831d = new WindowManager.LayoutParams();

        C1010e(Toast toast) {
            super(toast);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: b */
        public void mo22996b() {
            this.f6829b = this.f6826a.getView();
            if (this.f6829b != null) {
                Context context = this.f6826a.getView().getContext();
                if (Build.VERSION.SDK_INT < 25) {
                    this.f6830c = (WindowManager) context.getSystemService("window");
                    this.f6831d.type = 2005;
                } else {
                    Context d = Utils.m24095d();
                    if (!(d instanceof Activity)) {
                        Log.e("ToastUtils", "Couldn't get top Activity.");
                        return;
                    }
                    Activity activity = (Activity) d;
                    if (activity.isFinishing() || activity.isDestroyed()) {
                        Log.e("ToastUtils", activity + " is useless");
                        return;
                    }
                    this.f6830c = activity.getWindowManager();
                    this.f6831d.type = 99;
                    Utils.m24097b().m24084a(activity, f6828e);
                }
                WindowManager.LayoutParams layoutParams = this.f6831d;
                layoutParams.height = -2;
                layoutParams.width = -2;
                layoutParams.format = -3;
                layoutParams.windowAnimations = 16973828;
                layoutParams.setTitle("ToastWithoutNotification");
                WindowManager.LayoutParams layoutParams2 = this.f6831d;
                layoutParams2.flags = 152;
                layoutParams2.packageName = Utils.m24103a().getPackageName();
                this.f6831d.gravity = this.f6826a.getGravity();
                if ((this.f6831d.gravity & 7) == 7) {
                    this.f6831d.horizontalWeight = 1.0f;
                }
                if ((this.f6831d.gravity & 112) == 112) {
                    this.f6831d.verticalWeight = 1.0f;
                }
                this.f6831d.x = this.f6826a.getXOffset();
                this.f6831d.y = this.f6826a.getYOffset();
                this.f6831d.horizontalMargin = this.f6826a.getHorizontalMargin();
                this.f6831d.verticalMargin = this.f6826a.getVerticalMargin();
                try {
                    if (this.f6830c != null) {
                        this.f6830c.addView(this.f6829b, this.f6831d);
                    }
                } catch (Exception unused) {
                }
                Utils.m24098a(new Runnable() { // from class: com.blankj.utilcode.util.bc.e.2
                    @Override // java.lang.Runnable
                    public void run() {
                        C1010e.this.mo22995c();
                    }
                }, this.f6826a.getDuration() == 0 ? 2000L : 3500L);
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: c */
        public void mo22995c() {
            try {
                if (this.f6830c != null) {
                    this.f6830c.removeViewImmediate(this.f6829b);
                }
            } catch (Exception unused) {
            }
            this.f6829b = null;
            this.f6830c = null;
            this.f6826a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ToastUtils.java */
    /* renamed from: com.blankj.utilcode.util.bc$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractC1005a implements AbstractC1006b {

        /* renamed from: a */
        Toast f6826a;

        AbstractC1005a(Toast toast) {
            this.f6826a = toast;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: a */
        public void mo23002a(View view) {
            this.f6826a.setView(view);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: a */
        public View mo23005a() {
            return this.f6826a.getView();
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: a */
        public void mo23004a(int i) {
            this.f6826a.setDuration(i);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: a */
        public void mo23003a(int i, int i2, int i3) {
            this.f6826a.setGravity(i, i2, i3);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: b */
        public void mo23000b(int i) {
            this.f6826a.setText(i);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbstractC1006b
        /* renamed from: a */
        public void mo23001a(CharSequence charSequence) {
            this.f6826a.setText(charSequence);
        }
    }
}
