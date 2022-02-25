package p110z1;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.SizeUtils;
import com.cyjh.ddy.media.media.ActionCode;
import com.nrzs.moudleui.C2202R;

/* renamed from: z1.apj */
/* loaded from: classes3.dex */
public class MyToast {

    /* renamed from: g */
    public static final int f17148g = 8000;

    /* renamed from: h */
    protected static MyToast f17149h;

    /* renamed from: a */
    protected WindowManager f17150a;

    /* renamed from: b */
    protected View f17151b;

    /* renamed from: c */
    protected Handler f17152c;

    /* renamed from: d */
    protected long f17153d;

    /* renamed from: e */
    protected WindowManager.LayoutParams f17154e;

    /* renamed from: f */
    protected long f17155f = 1000;

    public MyToast() {
        m11708a();
    }

    /* renamed from: a */
    public void m11706a(Context context, View view, long j) {
        long j2 = this.f17155f;
        if (j < j2) {
            this.f17153d = j2;
        } else {
            this.f17153d = j;
        }
        if (this.f17152c == null) {
            this.f17152c = new Handler() { // from class: z1.apj.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    MyToast.this.f17150a.removeView(MyToast.this.f17151b);
                    MyToast.f17149h = null;
                }
            };
        }
        this.f17150a = (WindowManager) context.getSystemService("window");
        View view2 = this.f17151b;
        if (view2 == null || !view2.equals(view)) {
            View view3 = this.f17151b;
            if (view3 != null) {
                this.f17150a.removeView(view3);
            }
            this.f17151b = view;
            WindowManager.LayoutParams layoutParams = this.f17154e;
            layoutParams.gravity = 81;
            layoutParams.y = SizeUtils.m23262a(50.0f);
            this.f17150a.addView(this.f17151b, this.f17154e);
        }
        this.f17152c.removeMessages(1);
        this.f17152c.sendEmptyMessageDelayed(1, this.f17153d);
    }

    /* renamed from: a */
    private void m11708a() {
        this.f17154e = new WindowManager.LayoutParams();
        WindowManager.LayoutParams layoutParams = this.f17154e;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f17154e.type = 2005;
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.f17154e.type = 2038;
        } else {
            this.f17154e.type = ActionCode.CtrlConnectRefuse_2002;
        }
        this.f17154e.setTitle("Toast");
        this.f17154e.flags = 152;
    }

    /* renamed from: b */
    public static void m11703b(Context context, View view, long j) {
        if (f17149h == null) {
            f17149h = new MyToast();
        }
        f17149h.m11706a(context, view, j);
    }

    /* renamed from: a */
    public static void m11704a(Context context, String str, long j) {
        LinearLayout linearLayout = new LinearLayout(context);
        TextView textView = (TextView) LayoutInflater.from(context).inflate(C2202R.layout.float_gui_text, (ViewGroup) null);
        textView.setText(str);
        linearLayout.addView(textView);
        m11703b(context, linearLayout, j);
    }

    /* renamed from: a */
    public static void m11707a(Context context, int i, long j) {
        m11704a(context, context.getString(i), j);
    }

    /* renamed from: a */
    public static void m11705a(Context context, String str) {
        LinearLayout linearLayout = new LinearLayout(context);
        TextView textView = (TextView) LayoutInflater.from(context).inflate(C2202R.layout.float_gui_text, (ViewGroup) null);
        textView.setText(str);
        linearLayout.addView(textView);
        m11703b(context, linearLayout, 2000L);
    }
}
