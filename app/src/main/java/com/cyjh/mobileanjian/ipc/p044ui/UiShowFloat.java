package com.cyjh.mobileanjian.ipc.p044ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.mq.sdk.MqRunner;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.j */
/* loaded from: classes.dex */
public final class UiShowFloat extends RelativeLayout {

    /* renamed from: a */
    WindowManager f8541a;

    /* renamed from: c */
    RelativeLayout f8543c;

    /* renamed from: j */
    private Context f8550j;

    /* renamed from: k */
    private UiFactory f8551k;

    /* renamed from: d */
    boolean f8544d = false;

    /* renamed from: e */
    boolean f8545e = false;

    /* renamed from: f */
    int f8546f = Color.parseColor("#FFFFFF");

    /* renamed from: g */
    float f8547g = 0.0f;

    /* renamed from: h */
    int f8548h = 100;

    /* renamed from: i */
    String f8549i = "";

    /* renamed from: l */
    private Handler f8552l = new Handler();

    /* renamed from: b */
    WindowManager.LayoutParams f8542b = new WindowManager.LayoutParams();

    public UiShowFloat(Context context, String str, int i, int i2, int i3, int i4) {
        super(context);
        this.f8550j = context;
        this.f8551k = UiFactory.m20882a(context);
        this.f8541a = (WindowManager) this.f8550j.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8542b.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8542b.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8542b.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8542b;
        layoutParams.format = 1;
        layoutParams.flags = 1320;
        layoutParams.systemUiVisibility = 2;
        layoutParams.gravity = 51;
        layoutParams.x = i;
        layoutParams.y = i2;
        layoutParams.alpha = 1.0f;
        this.f8550j.getResources().getDisplayMetrics();
        this.f8543c = (RelativeLayout) LayoutInflater.from(this.f8550j).inflate(C1375R.layout.ui_show_float, (ViewGroup) null);
        WindowManager.LayoutParams layoutParams2 = this.f8542b;
        layoutParams2.width = i3;
        layoutParams2.height = i4;
        setTag(str);
    }

    /* renamed from: a */
    private void m20767a(int i, int i2) {
        this.f8541a = (WindowManager) this.f8550j.getSystemService("window");
        this.f8542b = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8542b.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8542b.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8542b.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8542b;
        layoutParams.format = 1;
        layoutParams.flags = 1320;
        layoutParams.systemUiVisibility = 2;
        layoutParams.gravity = 51;
        layoutParams.x = i;
        layoutParams.y = i2;
        layoutParams.alpha = 1.0f;
    }

    /* renamed from: b */
    private void m20759b(int i, int i2) {
        this.f8550j.getResources().getDisplayMetrics();
        this.f8543c = (RelativeLayout) LayoutInflater.from(this.f8550j).inflate(C1375R.layout.ui_show_float, (ViewGroup) null);
        WindowManager.LayoutParams layoutParams = this.f8542b;
        layoutParams.width = i;
        layoutParams.height = i2;
    }

    /* renamed from: c */
    private int m20754c(int i) {
        return (int) (i * (this.f8550j.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* renamed from: a */
    public final void m20769a() {
        String str = (String) getTag();
        String str2 = this.f8549i;
        if (str2 == null || str2.length() <= 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            float f = this.f8547g;
            gradientDrawable.setCornerRadii(new float[]{f, f, f, f, f, f, f, f});
            try {
                gradientDrawable.setColor(this.f8546f);
            } catch (Exception unused) {
            }
            if (Build.VERSION.SDK_INT >= 16) {
                this.f8543c.setBackground(gradientDrawable);
            }
        } else {
            Bitmap decodeFile = BitmapFactory.decodeFile(this.f8549i);
            if (Build.VERSION.SDK_INT >= 16) {
                this.f8543c.setBackground(new BitmapDrawable(decodeFile));
            }
        }
        this.f8544d = true;
        this.f8543c.setOnTouchListener(new View$OnTouchListenerC13151(str));
        this.f8541a.addView(this.f8543c, this.f8542b);
        this.f8543c.getBackground().setAlpha((this.f8548h * 255) / 100);
    }

    /* compiled from: UiShowFloat.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.j$1 */
    /* loaded from: classes.dex */
    final class View$OnTouchListenerC13151 implements View.OnTouchListener {

        /* renamed from: a */
        int f8553a;

        /* renamed from: b */
        int f8554b;

        /* renamed from: c */
        int f8555c;

        /* renamed from: d */
        int f8556d;

        /* renamed from: e */
        long f8557e;

        /* renamed from: f */
        long f8558f;

        /* renamed from: g */
        Runnable f8559g = new Runnable() { // from class: com.cyjh.mobileanjian.ipc.ui.j.1.1
            @Override // java.lang.Runnable
            public final void run() {
                UiShowFloat.m20763a(View$OnTouchListenerC13151.this.f8560h, UiMessage.ControlEvent.Event_Type.ON_TOUCH);
            }
        };

        /* renamed from: h */
        final /* synthetic */ String f8560h;

        /* JADX INFO: Access modifiers changed from: package-private */
        public View$OnTouchListenerC13151(String str) {
            this.f8560h = str;
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f8553a = (int) motionEvent.getRawX();
                    this.f8554b = (int) motionEvent.getRawY();
                    this.f8555c = UiShowFloat.this.f8542b.x;
                    this.f8556d = UiShowFloat.this.f8542b.y;
                    this.f8557e = System.currentTimeMillis();
                    this.f8558f = this.f8557e;
                    UiShowFloat.m20763a(this.f8560h, UiMessage.ControlEvent.Event_Type.ON_TOUCH_DOWN);
                    new StringBuilder("ACTION_DOWN ").append(System.currentTimeMillis());
                    UiShowFloat.this.f8552l.postDelayed(this.f8559g, 501L);
                    return true;
                case 1:
                    if (this.f8559g != null) {
                        UiShowFloat.this.f8552l.removeCallbacks(this.f8559g);
                    }
                    new StringBuilder("ACTION_UP ").append(System.currentTimeMillis());
                    if (Math.abs(motionEvent.getRawX() - this.f8553a) >= 30.0f || Math.abs(motionEvent.getRawY() - this.f8554b) >= 30.0f || System.currentTimeMillis() - this.f8557e >= 500) {
                        UiShowFloat.m20763a(this.f8560h, UiMessage.ControlEvent.Event_Type.ON_TOUCH_UP);
                        return true;
                    }
                    UiShowFloat.m20763a(this.f8560h, UiMessage.ControlEvent.Event_Type.ON_CLICK);
                    return true;
                case 2:
                    new StringBuilder("ACTION_MOVE ").append(System.currentTimeMillis());
                    if (UiShowFloat.this.f8545e) {
                        int rawX = ((int) motionEvent.getRawX()) - this.f8553a;
                        int rawY = ((int) motionEvent.getRawY()) - this.f8554b;
                        UiShowFloat.this.f8542b.x = this.f8555c + rawX;
                        UiShowFloat.this.f8542b.y = this.f8556d + rawY;
                        UiShowFloat.this.f8541a.updateViewLayout(UiShowFloat.this.f8543c, UiShowFloat.this.f8542b);
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if ((Math.abs(motionEvent.getRawX() - this.f8553a) == 5.0f && Math.abs(motionEvent.getRawY() - this.f8554b) == 5.0f) || currentTimeMillis - this.f8558f <= 50) {
                        return true;
                    }
                    UiShowFloat.m20763a(this.f8560h, UiMessage.ControlEvent.Event_Type.ON_TOUCH_MOVE);
                    this.f8558f = currentTimeMillis;
                    return true;
                default:
                    return true;
            }
        }
    }

    /* renamed from: b */
    public final void m20761b() {
        this.f8544d = false;
        try {
            this.f8541a.removeView(this.f8543c);
        } catch (Exception unused) {
        }
    }

    /* renamed from: d */
    public final boolean m20752d() {
        return this.f8544d;
    }

    /* renamed from: a */
    private void m20762a(boolean z) {
        this.f8545e = z;
    }

    public final void setOpacity(int i) {
        this.f8543c.getBackground().setAlpha((i * 255) / 100);
        this.f8548h = i;
    }

    public final int getOpacity() {
        return this.f8548h;
    }

    /* renamed from: a */
    public final void m20768a(int i) {
        this.f8547g = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        float f = this.f8547g;
        gradientDrawable.setCornerRadii(new float[]{f, f, f, f, f, f, f, f});
        try {
            gradientDrawable.setColor(this.f8546f);
        } catch (Exception unused) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f8543c.setBackground(gradientDrawable);
        }
        this.f8543c.getBackground().setAlpha((this.f8548h * 255) / 100);
    }

    /* renamed from: b */
    public final void m20760b(int i) {
        this.f8546f = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        float f = this.f8547g;
        gradientDrawable.setCornerRadii(new float[]{f, f, f, f, f, f, f, f});
        try {
            gradientDrawable.setColor(this.f8546f);
        } catch (Exception unused) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f8543c.setBackground(gradientDrawable);
        }
        this.f8543c.getBackground().setAlpha((this.f8548h * 255) / 100);
    }

    /* renamed from: b */
    private void m20757b(String str) {
        this.f8549i = str;
        Bitmap decodeFile = BitmapFactory.decodeFile(this.f8549i);
        if (Build.VERSION.SDK_INT >= 16) {
            this.f8543c.setBackground(new BitmapDrawable(decodeFile));
        }
        this.f8543c.getBackground().setAlpha((this.f8548h * 255) / 100);
    }

    /* renamed from: a */
    public final void m20766a(View view) {
        final String str = (String) view.getTag();
        if (!(view instanceof Button)) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyjh.mobileanjian.ipc.ui.j.2

                /* renamed from: a */
                int f8563a;

                /* renamed from: b */
                int f8564b;

                /* renamed from: c */
                int f8565c;

                /* renamed from: d */
                int f8566d;

                /* renamed from: e */
                long f8567e;

                /* renamed from: f */
                long f8568f;

                /* renamed from: g */
                Runnable f8569g = new Runnable() { // from class: com.cyjh.mobileanjian.ipc.ui.j.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UiShowFloat.m20763a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH);
                    }
                };

                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            this.f8563a = (int) motionEvent.getRawX();
                            this.f8564b = (int) motionEvent.getRawY();
                            this.f8565c = UiShowFloat.this.f8542b.x;
                            this.f8566d = UiShowFloat.this.f8542b.y;
                            this.f8567e = System.currentTimeMillis();
                            this.f8568f = this.f8567e;
                            UiShowFloat.m20763a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH_DOWN);
                            new StringBuilder("ACTION_DOWN ").append(System.currentTimeMillis());
                            UiShowFloat.this.f8552l.postDelayed(this.f8569g, 501L);
                            return true;
                        case 1:
                            UiShowFloat.this.f8552l.removeCallbacks(this.f8569g);
                            new StringBuilder("ACTION_UP ").append(System.currentTimeMillis());
                            if (Math.abs(motionEvent.getRawX() - this.f8563a) >= 30.0f || Math.abs(motionEvent.getRawY() - this.f8564b) >= 30.0f || System.currentTimeMillis() - this.f8567e >= 500) {
                                UiShowFloat.m20763a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH_UP);
                                return true;
                            }
                            UiShowFloat.m20763a(str, UiMessage.ControlEvent.Event_Type.ON_CLICK);
                            return true;
                        case 2:
                            new StringBuilder("ACTION_MOVE ").append(System.currentTimeMillis());
                            if (UiShowFloat.this.f8545e) {
                                int rawX = ((int) motionEvent.getRawX()) - this.f8563a;
                                int rawY = ((int) motionEvent.getRawY()) - this.f8564b;
                                UiShowFloat.this.f8542b.x = this.f8565c + rawX;
                                UiShowFloat.this.f8542b.y = this.f8566d + rawY;
                                UiShowFloat.this.f8541a.updateViewLayout(UiShowFloat.this.f8543c, UiShowFloat.this.f8542b);
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis - this.f8567e > 501) {
                                UiShowFloat.m20763a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH);
                            }
                            if ((Math.abs(motionEvent.getRawX() - this.f8563a) == 5.0f && Math.abs(motionEvent.getRawY() - this.f8564b) == 5.0f) || currentTimeMillis - this.f8568f <= 50) {
                                return true;
                            }
                            UiShowFloat.m20763a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH_MOVE);
                            this.f8568f = currentTimeMillis;
                            return true;
                        default:
                            return true;
                    }
                }
            });
        }
        this.f8543c.addView(view);
    }

    /* renamed from: a */
    public final View m20764a(String str) {
        return this.f8543c.findViewWithTag(str);
    }

    /* renamed from: f */
    private boolean m20749f() {
        return this.f8545e;
    }

    /* renamed from: b */
    private static void m20756b(String str, UiMessage.ControlEvent.Event_Type event_Type) {
        MqRunner.getInstance().mo20409c(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(event_Type).build()).setIsSuccess(true).build().toByteString());
    }

    public final WindowManager.LayoutParams getParams() {
        return this.f8542b;
    }

    /* renamed from: e */
    public final void m20750e() {
        RelativeLayout relativeLayout = this.f8543c;
        if (relativeLayout != null && this.f8544d) {
            this.f8541a.updateViewLayout(relativeLayout, this.f8542b);
        }
    }

    /* renamed from: g */
    private void m20748g() {
        RelativeLayout relativeLayout = this.f8543c;
        if (relativeLayout != null && this.f8544d) {
            this.f8541a.removeView(relativeLayout);
            this.f8541a.addView(this.f8543c, this.f8542b);
        }
    }

    public final void setFloatLeft(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatLeft wLayoutParams[ ");
        sb.append(params.x);
        sb.append(",");
        sb.append(params.y);
        sb.append("]");
        params.x = i;
        if (this.f8544d) {
            this.f8541a.updateViewLayout(this.f8543c, params);
        }
    }

    public final void setFloatTop(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatTop wLayoutParams[ ");
        sb.append(params.x);
        sb.append(",");
        sb.append(params.y);
        sb.append("]");
        params.y = i;
        if (this.f8544d) {
            this.f8541a.updateViewLayout(this.f8543c, params);
        }
    }

    public final void setFloatWidth(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatWidth wLayoutParams[ ");
        sb.append(params.width);
        sb.append(",");
        sb.append(params.height);
        sb.append("]");
        WindowManager.LayoutParams layoutParams = this.f8542b;
        layoutParams.width = i;
        layoutParams.height = params.height;
        if (this.f8544d) {
            this.f8541a.updateViewLayout(this.f8543c, this.f8542b);
        }
    }

    public final void setFloatHeight(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatHeight wLayoutParams[ ");
        sb.append(params.width);
        sb.append(",");
        sb.append(params.height);
        sb.append("]");
        this.f8542b.width = params.width;
        WindowManager.LayoutParams layoutParams = this.f8542b;
        layoutParams.height = i;
        if (this.f8544d) {
            this.f8541a.updateViewLayout(this.f8543c, layoutParams);
        }
    }

    /* renamed from: c */
    public final void m20755c() {
        if (this.f8544d) {
            m20761b();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m20763a(String str, UiMessage.ControlEvent.Event_Type event_Type) {
        MqRunner.getInstance().mo20409c(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(event_Type).build()).setIsSuccess(true).build().toByteString());
    }
}
