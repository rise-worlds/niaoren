package com.gyf.barlibrary;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

/* renamed from: com.gyf.barlibrary.e */
/* loaded from: classes.dex */
public class FitsKeyboard implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    private final int f9299a;

    /* renamed from: b */
    private final int f9300b;

    /* renamed from: c */
    private ImmersionBar f9301c;

    /* renamed from: d */
    private Activity f9302d;

    /* renamed from: e */
    private Window f9303e;

    /* renamed from: f */
    private View f9304f;

    /* renamed from: g */
    private View f9305g;

    /* renamed from: h */
    private View f9306h;

    /* renamed from: i */
    private int f9307i;

    /* renamed from: j */
    private int f9308j;

    /* renamed from: k */
    private int f9309k;

    /* renamed from: l */
    private int f9310l;

    /* renamed from: m */
    private int f9311m;

    /* renamed from: n */
    private boolean f9312n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.view.View] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public FitsKeyboard(com.gyf.barlibrary.ImmersionBar r2, android.app.Activity r3, android.view.Window r4) {
        /*
            r1 = this;
            r1.<init>()
            r0 = 0
            r1.f9307i = r0
            r1.f9308j = r0
            r1.f9309k = r0
            r1.f9310l = r0
            r1.f9301c = r2
            r1.f9302d = r3
            r1.f9303e = r4
            android.view.Window r2 = r1.f9303e
            android.view.View r2 = r2.getDecorView()
            r1.f9304f = r2
            android.view.View r2 = r1.f9304f
            r3 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r2 = r2.findViewById(r3)
            android.widget.FrameLayout r2 = (android.widget.FrameLayout) r2
            android.view.View r3 = r2.getChildAt(r0)
            r1.f9306h = r3
            android.view.View r3 = r1.f9306h
            if (r3 == 0) goto L_0x005d
            boolean r4 = r3 instanceof android.support.p003v4.widget.DrawerLayout
            if (r4 == 0) goto L_0x003b
            android.support.v4.widget.DrawerLayout r3 = (android.support.p003v4.widget.DrawerLayout) r3
            android.view.View r3 = r3.getChildAt(r0)
            r1.f9306h = r3
        L_0x003b:
            android.view.View r3 = r1.f9306h
            if (r3 == 0) goto L_0x005d
            int r3 = r3.getPaddingLeft()
            r1.f9307i = r3
            android.view.View r3 = r1.f9306h
            int r3 = r3.getPaddingTop()
            r1.f9308j = r3
            android.view.View r3 = r1.f9306h
            int r3 = r3.getPaddingRight()
            r1.f9309k = r3
            android.view.View r3 = r1.f9306h
            int r3 = r3.getPaddingBottom()
            r1.f9310l = r3
        L_0x005d:
            android.view.View r3 = r1.f9306h
            if (r3 == 0) goto L_0x0062
            r2 = r3
        L_0x0062:
            r1.f9305g = r2
            com.gyf.barlibrary.a r2 = new com.gyf.barlibrary.a
            android.app.Activity r3 = r1.f9302d
            r2.<init>(r3)
            int r3 = r2.m20115b()
            r1.f9299a = r3
            int r2 = r2.m20112c()
            r1.f9300b = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gyf.barlibrary.FitsKeyboard.<init>(com.gyf.barlibrary.g, android.app.Activity, android.view.Window):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20104a(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f9303e.setSoftInputMode(i);
            if (!this.f9312n) {
                this.f9304f.getViewTreeObserver().addOnGlobalLayoutListener(this);
                this.f9312n = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20105a() {
        if (Build.VERSION.SDK_INT >= 19 && this.f9312n) {
            if (this.f9306h != null) {
                this.f9305g.setPadding(this.f9307i, this.f9308j, this.f9309k, this.f9310l);
            } else {
                this.f9305g.setPadding(this.f9301c.m19973i(), this.f9301c.m19968j(), this.f9301c.m19964k(), this.f9301c.m19961l());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m20103b() {
        if (Build.VERSION.SDK_INT >= 19 && this.f9312n) {
            this.f9304f.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.f9312n = false;
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        int i;
        ImmersionBar gVar = this.f9301c;
        if (gVar != null && gVar.m19978h() != null && this.f9301c.m19978h().f9260B) {
            int d = ImmersionBar.m20010d(this.f9302d);
            Rect rect = new Rect();
            this.f9304f.getWindowVisibleDisplayFrame(rect);
            int height = this.f9305g.getHeight() - rect.bottom;
            if (height != this.f9311m) {
                this.f9311m = height;
                boolean z = true;
                if (ImmersionBar.m19989f(this.f9303e.getDecorView().findViewById(16908290))) {
                    height -= d;
                    if (height <= d) {
                        z = false;
                    }
                } else if (this.f9306h != null) {
                    if (this.f9301c.m19978h().f9259A) {
                        height += this.f9300b + this.f9299a;
                    }
                    if (this.f9301c.m19978h().f9289w) {
                        height += this.f9299a;
                    }
                    if (height > d) {
                        i = this.f9310l + height;
                    } else {
                        i = 0;
                        z = false;
                    }
                    this.f9305g.setPadding(this.f9307i, this.f9308j, this.f9309k, i);
                } else {
                    int l = this.f9301c.m19961l();
                    height -= d;
                    if (height > d) {
                        l = height + d;
                    } else {
                        z = false;
                    }
                    this.f9305g.setPadding(this.f9301c.m19973i(), this.f9301c.m19968j(), this.f9301c.m19964k(), l);
                }
                if (height < 0) {
                    height = 0;
                }
                if (this.f9301c.m19978h().f9266H != null) {
                    this.f9301c.m19978h().f9266H.m19895a(z, height);
                }
            }
        }
    }
}
