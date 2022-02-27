package com.stripe.android.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.stripe.android.C2364R;
import com.stripe.android.model.ShippingMethod;
import java.util.List;

/* loaded from: classes2.dex */
public class SelectShippingMethodWidget extends FrameLayout {

    /* renamed from: a */
    RecyclerView f12556a;

    /* renamed from: b */
    ShippingMethodAdapter f12557b;

    public SelectShippingMethodWidget(Context context) {
        super(context);
        m17298a();
    }

    public SelectShippingMethodWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17298a();
    }

    public SelectShippingMethodWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17298a();
    }

    public ShippingMethod getSelectedShippingMethod() {
        return this.f12557b.m17232a();
    }

    /* renamed from: a */
    public void m17297a(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        this.f12557b.m17228a(list, shippingMethod);
    }

    /* renamed from: a */
    private void m17298a() {
        inflate(getContext(), C2364R.layout.select_shipping_method_widget, this);
        this.f12556a = (RecyclerView) findViewById(C2364R.C2366id.rv_shipping_methods_ssmw);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.f12557b = new ShippingMethodAdapter();
        this.f12556a.setHasFixedSize(true);
        this.f12556a.setAdapter(this.f12557b);
        this.f12556a.setLayoutManager(linearLayoutManager);
    }
}
