package com.lbd.p054xj.p056ui.fragment;

import android.os.Bundle;
import android.support.p006v7.widget.AppCompatImageView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.lbd.p054xj.C1467R;
import com.nrzs.data.xnkj.bean.XJBannerInfo;
import p110z1.C5277dw;
import p110z1.aej;

/* renamed from: com.lbd.xj.ui.fragment.BannerFragment */
/* loaded from: classes.dex */
public class BannerFragment extends AppBaseFragment {

    /* renamed from: f */
    private AppCompatImageView f9770f;

    /* renamed from: g */
    private XJBannerInfo f9771g;

    /* renamed from: a */
    public static BannerFragment m19427a(XJBannerInfo xJBannerInfo) {
        BannerFragment bannerFragment = new BannerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("imgUrl", xJBannerInfo);
        bannerFragment.setArguments(bundle);
        return bannerFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: a */
    public void mo19429a(Bundle bundle) {
        super.mo19429a(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f9771g = (XJBannerInfo) arguments.getParcelable("imgUrl");
        }
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: e */
    protected int mo19416e() {
        return C1467R.layout.xj_fragment_banner;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: b */
    public void mo19417b(View view) {
        super.mo19417b(view);
        this.f9770f = (AppCompatImageView) view.findViewById(C1467R.C1469id.xj_img_banner);
        this.f9770f.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fragment.BannerFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (C5277dw.f21346b.equals(BannerFragment.this.f9771g.execCommand)) {
                    aej.m13956a(BannerFragment.this.getContext(), BannerFragment.this.f9771g.execArg);
                } else if (C5277dw.f21345a.equals(BannerFragment.this.f9771g.execCommand)) {
                    aej.m13955b(BannerFragment.this.getContext(), BannerFragment.this.f9771g.execArg);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: a */
    public void mo19419a() {
        super.mo19419a();
        Glide.with(this).load(this.f9771g.imgUrl).into(this.f9770f);
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment, android.support.p003v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Glide.with(this).clear(this.f9770f);
    }
}
