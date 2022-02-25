package com.cyjh.mobileanjian.ipc.p044ui;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mqsdk.C1375R;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.c */
/* loaded from: classes.dex */
public final class FloatInputDialog {

    /* renamed from: a */
    View f8466a;

    /* renamed from: b */
    EditText f8467b;

    /* renamed from: c */
    AbstractC1302a f8468c;

    /* renamed from: d */
    WindowManager f8469d;

    /* renamed from: e */
    private WindowManager.LayoutParams f8470e = new WindowManager.LayoutParams();

    /* compiled from: FloatInputDialog.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.c$a */
    /* loaded from: classes.dex */
    public interface AbstractC1302a {
        /* renamed from: a */
        void mo20462a(String str);
    }

    public FloatInputDialog(Context context, String str, AbstractC1302a aVar) {
        this.f8468c = aVar;
        this.f8466a = LayoutInflater.from(context).inflate(C1375R.layout.dialog_input, (ViewGroup) null);
        ((TextView) this.f8466a.findViewById(C1375R.C1377id.prompt)).setText(str);
        this.f8467b = (EditText) this.f8466a.findViewById(C1375R.C1377id.et_input);
        this.f8466a.findViewById(C1375R.C1377id.single_ok_button).setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.c.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (FloatInputDialog.this.f8468c != null) {
                    FloatInputDialog.this.f8468c.mo20462a(FloatInputDialog.this.f8467b.getText().toString());
                }
                FloatInputDialog cVar = FloatInputDialog.this;
                cVar.f8469d.removeView(cVar.f8466a);
            }
        });
        this.f8469d = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8470e.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8470e.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8470e.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8470e;
        layoutParams.format = 1;
        layoutParams.gravity = 17;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.alpha = 1.0f;
        layoutParams.width = -1;
        layoutParams.height = -1;
    }

    /* renamed from: a */
    public final void m20900a() {
        this.f8469d.addView(this.f8466a, this.f8470e);
    }

    /* renamed from: b */
    private void m20898b() {
        this.f8469d.removeView(this.f8466a);
    }

    /* renamed from: c */
    private void m20896c() {
        this.f8470e = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8470e.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8470e.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8470e.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8470e;
        layoutParams.format = 1;
        layoutParams.gravity = 17;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.alpha = 1.0f;
        layoutParams.width = -1;
        layoutParams.height = -1;
    }
}
