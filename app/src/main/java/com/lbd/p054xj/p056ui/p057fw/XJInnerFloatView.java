package com.lbd.p054xj.p056ui.p057fw;

import android.content.Context;
import android.content.Intent;
import android.support.p003v4.view.ViewPager;
import android.support.p006v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.p056ui.view.gvp.widget.GridViewPager;
import com.lbd.p054xj.receiver.KeyCodeReceiver;
import com.lbd.p054xj.service.XJFloatService;
import com.nrzs.base.router.RouterUtils;
import java.util.ArrayList;
import java.util.List;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FwManager;
import p110z1.GridApdater;
import p110z1.GridItem;
import p110z1.IntentToAssistService;
import p110z1.LoginManager;
import p110z1.OnItemClickListener;
import p110z1.ResolutionEvent;
import p110z1.XJEvent;

/* renamed from: com.lbd.xj.ui.fw.XJInnerFloatView */
/* loaded from: classes.dex */
public class XJInnerFloatView extends LinearLayout implements ViewPager.OnPageChangeListener {

    /* renamed from: a */
    private WindowManager f9848a;

    /* renamed from: b */
    private WindowManager.LayoutParams f9849b;

    /* renamed from: c */
    private RelativeLayout f9850c;

    /* renamed from: d */
    private TextView f9851d;

    /* renamed from: e */
    private TextView f9852e;

    /* renamed from: f */
    private LinearLayout f9853f;

    /* renamed from: g */
    private GridViewPager f9854g;

    /* renamed from: h */
    private GridApdater f9855h;

    /* renamed from: i */
    private List<AppCompatTextView> f9856i = new ArrayList();

    /* renamed from: j */
    private List<GridItem> f9857j = new ArrayList();

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    public XJInnerFloatView(Context context, WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        super(context);
        this.f9848a = windowManager;
        this.f9849b = layoutParams;
        m19367d();
        m19365e();
        m19363f();
    }

    /* renamed from: d */
    private void m19367d() {
        LayoutInflater.from(getContext()).inflate(C1467R.layout.xj_float_layout_inner_float, (ViewGroup) this, true);
        this.f9850c = (RelativeLayout) findViewById(C1467R.C1469id.xj_ll_root);
        this.f9851d = (TextView) findViewById(C1467R.C1469id.tv_xj_ckyx);
        this.f9852e = (TextView) findViewById(C1467R.C1469id.tv_xj_htyx);
        this.f9853f = (LinearLayout) findViewById(C1467R.C1469id.layout_red_point);
        this.f9854g = (GridViewPager) findViewById(C1467R.C1469id.grid_view_pager);
    }

    /* renamed from: e */
    private void m19365e() {
        m19362g();
    }

    /* renamed from: f */
    private void m19363f() {
        this.f9851d.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJInnerFloatView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FwManager.getInstance().isCkyx = true;
                FwManager.getInstance().isFristOuter = true;
                XJInnerFloatView.this.m19372a(KeyCodeReceiver.f9486c);
            }
        });
        this.f9852e.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJInnerFloatView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJInnerFloatView.this.m19372a(KeyCodeReceiver.f9485b);
            }
        });
        this.f9850c.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJInnerFloatView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FwManager.getInstance().initXJFloatView(true);
            }
        });
    }

    /* renamed from: g */
    private void m19362g() {
        this.f9854g.removeOnPageChangeListener(this);
        this.f9857j.clear();
        this.f9856i.clear();
        this.f9853f.removeAllViews();
        this.f9857j.add(new GridItem(0, C1467R.C1468drawable.bird_ic_back_pop_import, C1467R.string.bird_back_pop_item_import));
        if (FwManager.getInstance().isRunScript) {
            this.f9857j.add(new GridItem(1, C1467R.C1468drawable.bird_ic_back_pop_assist_stop, C1467R.string.bird_back_pop_item_assist_stop));
        } else {
            this.f9857j.add(new GridItem(1, C1467R.C1468drawable.bird_ic_back_pop_assist, C1467R.string.bird_back_pop_item_assist));
        }
        if (FwManager.getInstance().isNav) {
            this.f9857j.add(new GridItem(2, C1467R.C1468drawable.bird_ic_back_pop_key_open, C1467R.string.bird_back_pop_item_key));
        } else {
            this.f9857j.add(new GridItem(2, C1467R.C1468drawable.bird_ic_back_pop_key_close, C1467R.string.bird_back_pop_item_key));
        }
        this.f9857j.add(new GridItem(3, C1467R.C1468drawable.bird_ic_back_pop_shutdown, C1467R.string.bird_back_pop_item_shutdown));
        this.f9857j.add(new GridItem(4, C1467R.C1468drawable.bird_ic_back_pop_resolution_setting, C1467R.string.bird_back_pop_item_resolution_setting));
        this.f9855h = new GridApdater(getContext(), C1467R.layout.xj_float_item_inner_grid, this.f9857j);
        this.f9854g.setGVPAdapter(this.f9855h);
        this.f9855h.m14234a(new OnItemClickListener<GridItem>() { // from class: com.lbd.xj.ui.fw.XJInnerFloatView.4
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo14228a(View view, int i, GridItem adnVar) {
                switch (i) {
                    case 0:
                        XJInnerFloatView.this.m19359j();
                        return;
                    case 1:
                        XJInnerFloatView.this.m19357l();
                        return;
                    case 2:
                        XJInnerFloatView.this.m19358k();
                        return;
                    case 3:
                        XJInnerFloatView.this.m19360i();
                        return;
                    case 4:
                        XJInnerFloatView.this.m19361h();
                        return;
                    default:
                        return;
                }
            }
        });
        this.f9854g.addOnPageChangeListener(this);
        for (int i = 0; i < this.f9854g.getPageCount(); i++) {
            AppCompatTextView bannerSelectPoint = getBannerSelectPoint();
            this.f9856i.add(bannerSelectPoint);
            this.f9853f.addView(bannerSelectPoint);
        }
        setBannerSelectPoint(0);
    }

    /* renamed from: a */
    public void m19376a() {
        LogUtils.m23742b("setGridScriptData");
        if (FwManager.getInstance().isRunScript) {
            GridItem adnVar = this.f9857j.get(1);
            if (adnVar != null) {
                adnVar.f15302b = C1467R.C1468drawable.bird_ic_back_pop_stop_tool;
                adnVar.f15301a = C1467R.string.bird_back_pop_item_assist_stop;
                return;
            }
            return;
        }
        GridItem adnVar2 = this.f9857j.get(1);
        if (adnVar2 != null) {
            adnVar2.f15302b = C1467R.C1468drawable.bird_ic_back_pop_assist;
            adnVar2.f15301a = C1467R.string.bird_back_pop_item_tool_list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m19361h() {
        EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗分辨率设置点击", "后台挂机内部悬浮窗分辨率设置点击", EventConstants.f16415S);
        FwManager.getInstance().initXJFloatView(true);
        EventBus.m3448a().m3427d(new ResolutionEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m19360i() {
        EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗关机休息点击", "后台挂机内部悬浮窗关机休息点击", EventConstants.f16414R);
        FwManager.getInstance().shutDownXJ();
        FwManager.getInstance().initXJFloatView(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m19359j() {
        FwManager.getInstance().sendOpenImport();
        EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗应用导入点击", "后台挂机内部悬浮窗应用导入点击", EventConstants.f16409M);
        FwManager.getInstance().initXJFloatView(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m19358k() {
        boolean z = !FwManager.getInstance().isNav;
        if (z) {
            EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗虚拟键开启点击", "后台挂机内部悬浮窗虚拟键开启点击", EventConstants.f16412P);
        } else {
            EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗虚拟键关闭点击", "后台挂机内部悬浮窗虚拟键关闭点击", EventConstants.f16413Q);
        }
        EventBus.m3448a().m3427d(new XJEvent.C3333a(2, z + ""));
        FwManager.getInstance().initXJFloatView(true);
        FwManager.getInstance().isNav = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m19357l() {
        FwManager.getInstance().removeXJInnerFloatView();
        if (FwManager.getInstance().isRunScript) {
            EventCollectManager.m12642a().m12640a(getContext(), "后台挂机内部悬浮窗停止辅助点击", "后台挂机内部悬浮窗停止辅助点击", EventConstants.f16411O);
            FwManager.getInstance().stopScript();
        } else if (!LoginManager.m12620d().m12606r()) {
            IntentToAssistService.m12813a(getContext());
        } else {
            EventCollectManager.m12642a().m12640a(getContext(), "后台挂机辅助列表", "后台挂机辅助列表", EventConstants.f16406J);
            RouterUtils.toAllGame(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19372a(String str) {
        Intent intent = new Intent();
        intent.putExtra(KeyCodeReceiver.f9488e, str);
        intent.setAction("XJKEYACTION");
        getContext().sendBroadcast(intent);
    }

    /* renamed from: a */
    private void m19375a(int i) {
        Intent intent = new Intent(getContext(), XJFloatService.class);
        intent.putExtra("Action", i);
        intent.putExtra("key", 2);
        getContext().startService(intent);
    }

    /* renamed from: b */
    public void m19371b() {
        this.f9849b.width = ScreenUtils.m23306a();
        this.f9849b.height = ScreenUtils.m23302b();
        if (getParent() == null) {
            this.f9848a.addView(this, this.f9849b);
        }
        m19365e();
    }

    /* renamed from: c */
    public void m19369c() {
        if (getParent() != null) {
            this.f9848a.removeView(this);
        }
    }

    private AppCompatTextView getBannerSelectPoint() {
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext() != null ? getContext() : XJApp.getInstance().getApplicationContext());
        appCompatTextView.setBackgroundResource(C1467R.C1468drawable.bird_ic_slide_dot);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SizeUtils.m23262a(4.0f), SizeUtils.m23262a(4.0f));
        layoutParams.setMargins(SizeUtils.m23262a(3.0f), SizeUtils.m23262a(3.0f), SizeUtils.m23262a(3.0f), SizeUtils.m23262a(3.0f));
        appCompatTextView.setLayoutParams(layoutParams);
        return appCompatTextView;
    }

    private void setBannerSelectPoint(int i) {
        List<AppCompatTextView> list = this.f9856i;
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < this.f9856i.size(); i2++) {
                AppCompatTextView appCompatTextView = this.f9856i.get(i2);
                if (i == i2) {
                    appCompatTextView.setSelected(true);
                } else {
                    appCompatTextView.setSelected(false);
                }
            }
        }
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setBannerSelectPoint(i);
    }
}
