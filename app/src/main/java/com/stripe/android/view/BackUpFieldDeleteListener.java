package com.stripe.android.view;

import com.stripe.android.view.StripeEditText;

/* renamed from: com.stripe.android.view.a */
/* loaded from: classes2.dex */
class BackUpFieldDeleteListener implements StripeEditText.AbstractC2473b {

    /* renamed from: a */
    private StripeEditText f12596a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackUpFieldDeleteListener(StripeEditText stripeEditText) {
        this.f12596a = stripeEditText;
    }

    @Override // com.stripe.android.view.StripeEditText.AbstractC2473b
    /* renamed from: a */
    public void mo17274a() {
        String obj = this.f12596a.getText().toString();
        if (obj.length() > 1) {
            this.f12596a.setText(obj.substring(0, obj.length() - 1));
        }
        this.f12596a.requestFocus();
        StripeEditText stripeEditText = this.f12596a;
        stripeEditText.setSelection(stripeEditText.length());
    }
}
