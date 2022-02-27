package com.lbd.p054xj.p056ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lbd.p054xj.p056ui.fragment.BannerFragment;
import com.nrzs.data.xnkj.bean.XJBannerInfo;
import java.util.List;

/* renamed from: com.lbd.xj.ui.adapter.XJBannerAdapter */
/* loaded from: classes.dex */
public class XJBannerAdapter extends FragmentStatePagerAdapter {

    /* renamed from: a */
    private List<XJBannerInfo> f9679a;

    public XJBannerAdapter(FragmentManager fragmentManager, List<XJBannerInfo> list) {
        super(fragmentManager);
        this.f9679a = list;
    }

    /* renamed from: a */
    public BannerFragment getItem(int i) {
        return BannerFragment.m19427a(this.f9679a.get(i));
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f9679a.size();
    }
}
