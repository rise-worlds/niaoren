package com.lbd.p054xj.p056ui.fragment;

import android.os.Bundle;
import android.support.p003v4.view.ViewPager;
import android.support.p006v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.SizeUtils;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.p056ui.adapter.XJBannerAdapter;
import com.nrzs.data.xnkj.bean.XJBannerInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;
import p110z1.OnBannerListener;
import p110z1.SharepreferenceUtil;
import p110z1.TypeToken;
import p110z1.ValShare;
import p110z1.apa;

/* renamed from: com.lbd.xj.ui.fragment.SplashFragment */
/* loaded from: classes.dex */
public class SplashFragment extends AppBaseFragment implements ViewPager.OnPageChangeListener, OnBannerListener {

    /* renamed from: f */
    private Banner f9781f;

    /* renamed from: g */
    private LinearLayout f9782g;

    /* renamed from: h */
    private ViewPager f9783h;

    /* renamed from: i */
    private List<XJBannerInfo> f9784i;

    /* renamed from: j */
    private XJBannerAdapter f9785j;

    /* renamed from: k */
    private List<AppCompatTextView> f9786k;

    @Override // p110z1.OnBannerListener
    /* renamed from: a */
    public void mo11419a(int i) {
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    /* renamed from: f */
    public static SplashFragment m19415f() {
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setArguments(new Bundle());
        return splashFragment;
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: e */
    protected int mo19416e() {
        return C1467R.layout.xj_fragment_splash;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: b */
    public void mo19417b(View view) {
        super.mo19417b(view);
        this.f9783h = (ViewPager) view.findViewById(C1467R.C1469id.xj_viewpager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: a */
    public void mo19419a() {
        XJBaseAppReponse xJBaseAppReponse;
        super.mo19419a();
        this.f9786k = new ArrayList();
        this.f9784i = new ArrayList();
        String b = SharepreferenceUtil.m13882b(ValShare.f16642a, "");
        if (!TextUtils.isEmpty(b) && (xJBaseAppReponse = (XJBaseAppReponse) apa.m11877a(b, new TypeToken<XJBaseAppReponse<List<XJBannerInfo>>>() { // from class: com.lbd.xj.ui.fragment.SplashFragment.1
        })) != null && xJBaseAppReponse.Data != 0 && ((List) xJBaseAppReponse.Data).size() > 0) {
            for (XJBannerInfo xJBannerInfo : (List) xJBaseAppReponse.Data) {
                if (xJBannerInfo.showPosition == 3) {
                    this.f9784i.add(xJBannerInfo);
                }
            }
        }
        m19418b(0);
        this.f9785j = new XJBannerAdapter(getFragmentManager(), this.f9784i);
        this.f9783h.setAdapter(this.f9785j);
        this.f9783h.addOnPageChangeListener(this);
        if (this.f9784i.size() == 1) {
            this.f9782g.setVisibility(0);
        } else {
            this.f9782g.setVisibility(8);
        }
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        m19418b(i);
        if (this.f9784i.size() == i + 1) {
            this.f9782g.setVisibility(0);
        } else {
            this.f9782g.setVisibility(8);
        }
    }

    /* renamed from: b */
    private void m19418b(int i) {
        List<AppCompatTextView> list = this.f9786k;
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < this.f9786k.size(); i2++) {
                AppCompatTextView appCompatTextView = this.f9786k.get(i2);
                if (i == i2) {
                    appCompatTextView.setSelected(true);
                } else {
                    appCompatTextView.setSelected(false);
                }
            }
        }
    }

    /* renamed from: g */
    private AppCompatTextView m19414g() {
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext() != null ? getContext() : XJApp.getInstance().getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SizeUtils.m23262a(10.0f), SizeUtils.m23262a(10.0f));
        layoutParams.setMargins(SizeUtils.m23262a(3.0f), SizeUtils.m23262a(3.0f), SizeUtils.m23262a(3.0f), SizeUtils.m23262a(3.0f));
        appCompatTextView.setLayoutParams(layoutParams);
        return appCompatTextView;
    }
}
