package com.cyjh.mobileanjian.ipc.p044ui;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mqsdk.C1375R;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.b */
/* loaded from: classes.dex */
public final class FloatAlertDialog {

    /* renamed from: a */
    public static final int f8442a = 0;

    /* renamed from: b */
    public static final int f8443b = 1;

    /* renamed from: c */
    public static final int f8444c = 2;

    /* renamed from: d */
    public static final int f8445d = 3;

    /* renamed from: e */
    public static final int f8446e = 0;

    /* renamed from: f */
    public static final int f8447f = 1;

    /* renamed from: g */
    public static final int f8448g = 2;

    /* renamed from: h */
    public static final int f8449h = 3;

    /* renamed from: i */
    AbstractC1300a f8450i;

    /* renamed from: j */
    private WindowManager f8451j;

    /* renamed from: k */
    private WindowManager.LayoutParams f8452k = new WindowManager.LayoutParams();

    /* renamed from: l */
    private View f8453l;

    /* renamed from: m */
    private int f8454m;

    /* renamed from: n */
    private int f8455n;

    /* renamed from: o */
    private LinearLayout f8456o;

    /* renamed from: p */
    private LinearLayout f8457p;

    /* renamed from: q */
    private Button f8458q;

    /* renamed from: r */
    private Button f8459r;

    /* renamed from: s */
    private Button f8460s;

    /* compiled from: FloatAlertDialog.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.b$a */
    /* loaded from: classes.dex */
    public interface AbstractC1300a {
        /* renamed from: a */
        void mo20463a(int i);
    }

    public FloatAlertDialog(Context context, String str, final int i, AbstractC1300a aVar) {
        this.f8450i = aVar;
        this.f8451j = (WindowManager) context.getSystemService("window");
        this.f8453l = LayoutInflater.from(context).inflate(C1375R.layout.dialog_alert, (ViewGroup) null);
        ((TextView) this.f8453l.findViewById(C1375R.C1377id.prompt)).setText(str);
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8452k.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8452k.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8452k.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8452k;
        layoutParams.format = 1;
        layoutParams.gravity = 16;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.alpha = 1.0f;
        layoutParams.width = -1;
        layoutParams.height = -1;
        switch (i) {
            case 1:
                this.f8453l.findViewById(C1375R.C1377id.single_ok_button).setVisibility(8);
                this.f8453l.findViewById(C1375R.C1377id.layout_type3).setVisibility(8);
                this.f8458q = (Button) this.f8453l.findViewById(C1375R.C1377id.positive_button);
                this.f8459r = (Button) this.f8453l.findViewById(C1375R.C1377id.negative_button);
                break;
            case 2:
                this.f8453l.findViewById(C1375R.C1377id.single_ok_button).setVisibility(8);
                this.f8453l.findViewById(C1375R.C1377id.layout_type3).setVisibility(8);
                this.f8458q = (Button) this.f8453l.findViewById(C1375R.C1377id.positive_button);
                this.f8459r = (Button) this.f8453l.findViewById(C1375R.C1377id.negative_button);
                this.f8458q.setText(C1375R.string.dialog_yes_button);
                this.f8459r.setText(C1375R.string.dialog_no_button);
                break;
            case 3:
                this.f8453l.findViewById(C1375R.C1377id.single_ok_button).setVisibility(8);
                this.f8453l.findViewById(C1375R.C1377id.layout_type2).setVisibility(8);
                this.f8458q = (Button) this.f8453l.findViewById(C1375R.C1377id.yes_button);
                this.f8459r = (Button) this.f8453l.findViewById(C1375R.C1377id.no_button);
                this.f8460s = (Button) this.f8453l.findViewById(C1375R.C1377id.cancel_button);
                break;
            default:
                this.f8453l.findViewById(C1375R.C1377id.layout_type2).setVisibility(8);
                this.f8453l.findViewById(C1375R.C1377id.layout_type3).setVisibility(8);
                this.f8458q = (Button) this.f8453l.findViewById(C1375R.C1377id.single_ok_button);
                break;
        }
        Button button = this.f8458q;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.b.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (FloatAlertDialog.this.f8450i != null) {
                        AbstractC1300a aVar2 = FloatAlertDialog.this.f8450i;
                        int i2 = i;
                        aVar2.mo20463a((i2 == 0 || i2 == 1) ? 0 : 2);
                    }
                    FloatAlertDialog.m20904a(FloatAlertDialog.this);
                }
            });
        }
        Button button2 = this.f8459r;
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.b.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (FloatAlertDialog.this.f8450i != null) {
                        AbstractC1300a aVar2 = FloatAlertDialog.this.f8450i;
                        int i2 = 1;
                        if (i != 1) {
                            i2 = 3;
                        }
                        aVar2.mo20463a(i2);
                    }
                    FloatAlertDialog.m20904a(FloatAlertDialog.this);
                }
            });
        }
        Button button3 = this.f8460s;
        if (button3 != null) {
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.b.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (FloatAlertDialog.this.f8450i != null) {
                        FloatAlertDialog.this.f8450i.mo20463a(1);
                    }
                    FloatAlertDialog.m20904a(FloatAlertDialog.this);
                }
            });
        }
    }

    /* renamed from: a */
    public final void m20905a() {
        this.f8451j.addView(this.f8453l, this.f8452k);
    }

    /* renamed from: b */
    private void m20903b() {
        this.f8451j.removeView(this.f8453l);
    }

    /* renamed from: c */
    private void m20901c() {
        this.f8452k = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8452k.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8452k.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8452k.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8452k;
        layoutParams.format = 1;
        layoutParams.gravity = 16;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.alpha = 1.0f;
        layoutParams.width = -1;
        layoutParams.height = -1;
    }

    /* renamed from: a */
    static /* synthetic */ void m20904a(FloatAlertDialog bVar) {
        bVar.f8451j.removeView(bVar.f8453l);
    }
}
