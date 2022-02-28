package com.nrzs.ft.ui.base;

import android.content.Context;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.cyjh.ddy.media.media.ActionCode;
import p110z1.FloatViewManager;

/* renamed from: com.nrzs.ft.ui.base.FtBaseView */
/* loaded from: classes2.dex */
public abstract class FtBaseView extends LinearLayout {

    /* renamed from: a */
    private Context f10734a;

    /* renamed from: b */
    private WindowManager.LayoutParams f10735b;

    /* renamed from: c */
    private FloatViewManager f10736c;

    /* renamed from: d */
    private boolean f10737d = false;

    /* renamed from: e */
    private String f10738e;

    /* renamed from: a */
    public abstract void mo18870a();

    /* renamed from: c */
    public void mo18876c() {
    }

    public abstract int getLayoutId();

    public FtBaseView(Context context) {
        super(context);
        this.f10734a = context;
        LayoutInflater.from(context).inflate(getLayoutId(), this);
        m18881e();
        mo18870a();
    }

    /* renamed from: e */
    private void m18881e() {
        if (this.f10736c == null) {
            this.f10736c = FloatViewManager.m12346a(this.f10734a);
        }
        this.f10735b = getParams();
        this.f10735b.gravity = 51;
    }

    /* renamed from: a */
    public <E extends View> E m18884a(int i, View.OnClickListener onClickListener) {
        E e = (E) findViewById(i);
        e.setOnClickListener(onClickListener);
        return e;
    }

    public WindowManager.LayoutParams getParams() {
        this.f10735b = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f10735b.type = 2005;
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.f10735b.type = 2038;
        } else {
            this.f10735b.type = ActionCode.CtrlConnectRefuse_2002;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.f10735b.layoutInDisplayCutoutMode = 1;
        }
        WindowManager.LayoutParams layoutParams = this.f10735b;
        layoutParams.format = 1;
        layoutParams.gravity = 51;
        layoutParams.systemUiVisibility = 2;
        return layoutParams;
    }

    /* renamed from: a */
    public void m18885a(int i, int i2, boolean z, String str, boolean z2) {
        this.f10738e = str;
        if (z) {
            WindowManager.LayoutParams layoutParams = this.f10735b;
            layoutParams.width = -1;
            layoutParams.height = -1;
        } else {
            WindowManager.LayoutParams layoutParams2 = this.f10735b;
            layoutParams2.width = -2;
            layoutParams2.height = -2;
        }
        WindowManager.LayoutParams layoutParams3 = this.f10735b;
        layoutParams3.x = i;
        layoutParams3.y = i2;
        if (!this.f10737d) {
            int systemUiVisibility = getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 19) {
                setSystemUiVisibility(systemUiVisibility | 2 | 512 | 4 | 1024 | 2048);
            } else {
                setSystemUiVisibility(systemUiVisibility | 2 | 512 | 4 | 1024);
            }
            if (z2) {
                this.f10736c.m12327e();
            }
            this.f10736c.m12340a(this, this.f10735b, this.f10738e);
            this.f10737d = true;
            mo18876c();
        }
    }

    /* renamed from: a */
    public void m18886a(int i, int i2) {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
        layoutParams.x += i;
        layoutParams.y += i2;
    }

    /* renamed from: b */
    public void m18883b() {
        this.f10736c.m12335b(this.f10738e);
        this.f10737d = false;
    }

    /* renamed from: d */
    public boolean m18882d() {
        return this.f10737d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() == 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.f10736c.m12330d();
        return true;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            mo18876c();
        }
    }

    /* renamed from: i */
    public void m18880i() {
        this.f10736c.m12325f();
    }
}
