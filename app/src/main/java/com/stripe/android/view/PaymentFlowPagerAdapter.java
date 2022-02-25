package com.stripe.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.stripe.android.C2364R;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSessionConfig;
import com.stripe.android.model.ShippingMethod;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.stripe.android.view.h */
/* loaded from: classes2.dex */
public class PaymentFlowPagerAdapter extends PagerAdapter {

    /* renamed from: g */
    private static final String f12619g = "ShippingInfoScreen";

    /* renamed from: h */
    private static final String f12620h = "ShippingMethodScreen";
    @NonNull

    /* renamed from: a */
    private Context f12621a;
    @NonNull

    /* renamed from: b */
    private PaymentSessionConfig f12622b;

    /* renamed from: d */
    private boolean f12624d;

    /* renamed from: f */
    private ShippingMethod f12626f;

    /* renamed from: e */
    private List<ShippingMethod> f12625e = new ArrayList();
    @NonNull

    /* renamed from: c */
    private List<PaymentFlowPagerEnum> f12623c = new ArrayList();

    @Override // android.support.p003v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PaymentFlowPagerAdapter(@NonNull Context context, @NonNull PaymentSessionConfig paymentSessionConfig) {
        this.f12621a = context;
        this.f12622b = paymentSessionConfig;
        if (this.f12622b.m18109d()) {
            this.f12623c.add(PaymentFlowPagerEnum.SHIPPING_INFO);
        }
        if (m17235b()) {
            this.f12623c.add(PaymentFlowPagerEnum.SHIPPING_METHOD);
        }
    }

    /* renamed from: b */
    private boolean m17235b() {
        return this.f12622b.m18108e() && (!this.f12622b.m18109d() || this.f12624d) && !this.f12623c.contains(PaymentFlowPagerEnum.SHIPPING_METHOD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17236a(boolean z) {
        this.f12624d = z;
        if (m17235b()) {
            this.f12623c.add(PaymentFlowPagerEnum.SHIPPING_METHOD);
        }
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17237a(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        this.f12625e = list;
        this.f12626f = shippingMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17239a() {
        this.f12623c.remove(PaymentFlowPagerEnum.SHIPPING_METHOD);
        notifyDataSetChanged();
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        PaymentFlowPagerEnum iVar = this.f12623c.get(i);
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.f12621a).inflate(iVar.getLayoutResId(), viewGroup, false);
        if (iVar.equals(PaymentFlowPagerEnum.SHIPPING_METHOD)) {
            CustomerSession.m18072a().m18055a(f12620h);
            ((SelectShippingMethodWidget) viewGroup2.findViewById(C2364R.C2366id.select_shipping_method_widget)).m17297a(this.f12625e, this.f12626f);
        }
        if (iVar.equals(PaymentFlowPagerEnum.SHIPPING_INFO)) {
            CustomerSession.m18072a().m18055a(f12619g);
            ShippingInfoWidget shippingInfoWidget = (ShippingInfoWidget) viewGroup2.findViewById(C2364R.C2366id.shipping_info_widget);
            shippingInfoWidget.setHiddenFields(this.f12622b.m18112a());
            shippingInfoWidget.setOptionalFields(this.f12622b.m18111b());
            shippingInfoWidget.m17295a(this.f12622b.m18110c());
        }
        viewGroup.addView(viewGroup2);
        return viewGroup2;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public int getCount() {
        return this.f12623c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public PaymentFlowPagerEnum m17238a(int i) {
        if (i < this.f12623c.size()) {
            return this.f12623c.get(i);
        }
        return null;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.f12621a.getString(this.f12623c.get(i).getTitleResId());
    }
}
