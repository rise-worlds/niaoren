package com.lbd.xj.ui.p057fw;

import android.content.Context;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.cyjh.ddy.media.media.ActionCode;

/* renamed from: com.lbd.xj.ui.fw.FtBaseView */
/* loaded from: classes.dex */
public abstract class FtBaseView extends LinearLayout {

    /* renamed from: a */
    public WindowManager.LayoutParams f9802a;

    /* renamed from: b */
    public WindowManager f9803b;

    /* renamed from: a */
    public abstract void mo19389a();

    /* renamed from: b */
    public abstract void mo19387b();

    /* renamed from: c */
    public abstract void mo19385c();

    /* renamed from: d */
    public abstract void mo19384d();

    public abstract int getLayoutId();

    public FtBaseView(Context context, WindowManager windowManager) {
        super(context);
        LayoutInflater.from(context).inflate(getLayoutId(), this);
        this.f9803b = windowManager;
        mo19389a();
    }

    public WindowManager.LayoutParams getParams() {
        this.f9802a = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f9802a.type = 2005;
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.f9802a.type = 2038;
        } else {
            this.f9802a.type = ActionCode.CtrlConnectRefuse_2002;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.f9802a.layoutInDisplayCutoutMode = 1;
        }
        WindowManager.LayoutParams layoutParams = this.f9802a;
        layoutParams.format = 1;
        layoutParams.gravity = 51;
        layoutParams.systemUiVisibility = 2;
        return layoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() == 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        mo19384d();
        return true;
    }
}
