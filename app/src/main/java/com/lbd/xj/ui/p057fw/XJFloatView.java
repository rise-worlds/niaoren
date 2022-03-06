package com.lbd.xj.ui.p057fw;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.lbd.xj.C1467R;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FwManager;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.XNKJEvent;

/* renamed from: com.lbd.xj.ui.fw.XJFloatView */
/* loaded from: classes.dex */
public class XJFloatView extends AppCompatImageView {

    /* renamed from: a */
    long f9843a;

    /* renamed from: b */
    private WindowManager f9844b;

    /* renamed from: c */
    private WindowManager.LayoutParams f9845c;

    /* renamed from: d */
    private int f9846d;

    /* renamed from: e */
    private int f9847e;

    /* renamed from: a */
    private void m19380a(Context context) {
    }

    public XJFloatView(Context context, WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        super(context);
        this.f9844b = windowManager;
        this.f9845c = layoutParams;
        EventBus.m3448a().m3427d(this);
        m19377c();
        m19380a(context);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.m3448a().m3430c(this);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19379a(XNKJEvent.C3577g gVar) {
        FwManager.getInstance().initXJFloatView(true);
    }

    /* renamed from: c */
    private void m19377c() {
        setBackgroundResource(C1467R.C1468drawable.vs_ic_ball_real);
    }

    /* renamed from: a */
    public void m19381a() {
        this.f9845c.width = SizeUtils.m23262a(50.0f);
        this.f9845c.height = SizeUtils.m23262a(50.0f);
        this.f9845c.x = ScreenUtils.m23300c() - this.f9845c.width;
        this.f9845c.y = (ScreenUtils.m23298d() - this.f9845c.height) / 2;
        try {
            this.f9844b.addView(this, this.f9845c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m19378b() {
        if (getParent() != null) {
            try {
                this.f9844b.removeView(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f9843a = System.currentTimeMillis();
            this.f9846d = (int) motionEvent.getRawX();
            this.f9847e = (int) motionEvent.getRawY();
            LogUtils.m23734c("onTouchEvent", "我被点击了：x = " + this.f9846d + "，y = " + this.f9846d);
        } else if (action == 1) {
            this.f9843a = System.currentTimeMillis() - this.f9843a;
            if (this.f9843a < 150) {
                if (FwManager.getInstance().isFront) {
                    FwManager.getInstance().initXJOuterFloatView();
                } else {
                    EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗点击", "后台挂机内部悬浮窗点击", EventConstants.f16408L);
                    FwManager.getInstance().initXJInnerFloatView();
                }
                return false;
            }
            this.f9844b.updateViewLayout(this, this.f9845c);
        } else if (action != 2) {
            return false;
        } else {
            this.f9845c.x += ((int) motionEvent.getRawX()) - this.f9846d;
            this.f9845c.y += ((int) motionEvent.getRawY()) - this.f9847e;
            this.f9846d = (int) motionEvent.getRawX();
            this.f9847e = (int) motionEvent.getRawY();
            this.f9844b.updateViewLayout(this, this.f9845c);
            return false;
        }
        return false;
    }
}
