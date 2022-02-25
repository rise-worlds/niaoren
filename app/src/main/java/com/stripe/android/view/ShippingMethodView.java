package com.stripe.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.stripe.android.C2364R;
import com.stripe.android.model.ShippingMethod;

/* renamed from: com.stripe.android.view.l */
/* loaded from: classes2.dex */
class ShippingMethodView extends RelativeLayout {
    @ColorInt

    /* renamed from: a */
    int f12634a;
    @ColorInt

    /* renamed from: b */
    int f12635b;
    @ColorInt

    /* renamed from: c */
    int f12636c;

    /* renamed from: d */
    private ShippingMethod f12637d;

    /* renamed from: e */
    private TextView f12638e;

    /* renamed from: f */
    private TextView f12639f;

    /* renamed from: g */
    private TextView f12640g;

    /* renamed from: h */
    private ImageView f12641h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShippingMethodView(Context context) {
        super(context);
        m17224a();
    }

    ShippingMethodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17224a();
    }

    ShippingMethodView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17224a();
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (z) {
            this.f12638e.setTextColor(this.f12634a);
            this.f12639f.setTextColor(this.f12634a);
            this.f12640g.setTextColor(this.f12634a);
            this.f12641h.setVisibility(0);
            return;
        }
        this.f12638e.setTextColor(this.f12636c);
        this.f12639f.setTextColor(this.f12635b);
        this.f12640g.setTextColor(this.f12636c);
        this.f12641h.setVisibility(4);
    }

    /* renamed from: a */
    private void m17224a() {
        inflate(getContext(), C2364R.layout.shipping_method_view, this);
        this.f12638e = (TextView) findViewById(C2364R.C2366id.tv_label_smv);
        this.f12639f = (TextView) findViewById(C2364R.C2366id.tv_detail_smv);
        this.f12640g = (TextView) findViewById(C2364R.C2366id.tv_amount_smv);
        this.f12641h = (ImageView) findViewById(C2364R.C2366id.iv_selected_icon);
        this.f12634a = C2486n.m17215a(getContext()).data;
        this.f12636c = C2486n.m17206d(getContext()).data;
        this.f12635b = C2486n.m17207c(getContext()).data;
        m17222b();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(16);
        layoutParams.height = C2486n.m17214a(getContext(), 72);
        setLayoutParams(layoutParams);
    }

    /* renamed from: b */
    private void m17222b() {
        Resources resources = getResources();
        Context context = getContext();
        if (Build.VERSION.SDK_INT >= 23) {
            this.f12634a = C2486n.m17216a(this.f12634a) ? resources.getColor(C2364R.color.accent_color_default, context.getTheme()) : this.f12634a;
            this.f12636c = C2486n.m17216a(this.f12636c) ? resources.getColor(C2364R.color.color_text_unselected_primary_default, context.getTheme()) : this.f12636c;
            this.f12635b = C2486n.m17216a(this.f12635b) ? resources.getColor(C2364R.color.color_text_unselected_secondary_default, context.getTheme()) : this.f12635b;
            return;
        }
        this.f12634a = C2486n.m17216a(this.f12634a) ? resources.getColor(C2364R.color.accent_color_default) : this.f12634a;
        this.f12636c = C2486n.m17216a(this.f12636c) ? resources.getColor(C2364R.color.color_text_unselected_primary_default) : this.f12636c;
        this.f12635b = C2486n.m17216a(this.f12635b) ? resources.getColor(C2364R.color.color_text_unselected_secondary_default) : this.f12635b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17223a(@NonNull ShippingMethod shippingMethod) {
        this.f12637d = shippingMethod;
        this.f12638e.setText(this.f12637d.m17932e());
        this.f12639f.setText(this.f12637d.m17931f());
        this.f12640g.setText(PaymentUtils.m17233a(this.f12637d.m17933d(), this.f12637d.m17934c(), getContext().getString(C2364R.string.price_free)));
    }
}
