package com.stripe.android.view;

import com.stripe.android.C2364R;

/* renamed from: com.stripe.android.view.i */
/* loaded from: classes2.dex */
enum PaymentFlowPagerEnum {
    SHIPPING_INFO(C2364R.string.title_add_an_address, C2364R.layout.activity_enter_shipping_info),
    SHIPPING_METHOD(C2364R.string.title_select_shipping_method, C2364R.layout.activity_select_shipping_method);
    
    private int mLayoutResId;
    private int mTitleResId;

    PaymentFlowPagerEnum(int i, int i2) {
        this.mTitleResId = i;
        this.mLayoutResId = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTitleResId() {
        return this.mTitleResId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLayoutResId() {
        return this.mLayoutResId;
    }
}
