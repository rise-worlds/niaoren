package com.nrzs.ft.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.cyjh.ddy.media.media.ActionCode;

/* renamed from: com.nrzs.ft.ui.base.a */
/* loaded from: classes2.dex */
public abstract class FtBaseDialog extends Dialog {
    /* renamed from: a */
    protected abstract void mo3014a();

    /* renamed from: b */
    protected abstract void mo3013b();

    /* renamed from: c */
    protected abstract void mo3012c();

    /* renamed from: d */
    protected abstract void mo3011d();

    public FtBaseDialog(Context context) {
        super(context);
        requestWindowFeature(1);
        m18879g();
    }

    public FtBaseDialog(Context context, int i) {
        super(context, i);
        requestWindowFeature(1);
        m18879g();
    }

    public FtBaseDialog(Context context, boolean z, int i) {
        super(context, i);
        if (z) {
            requestWindowFeature(1);
            m18879g();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mo3014a();
        mo3013b();
        mo3012c();
        mo3011d();
    }

    /* renamed from: g */
    public void m18879g() {
        if (getWindow() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            getWindow().setType(2005);
        } else if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
        } else {
            getWindow().setType(ActionCode.CtrlConnectRefuse_2002);
        }
    }
}
