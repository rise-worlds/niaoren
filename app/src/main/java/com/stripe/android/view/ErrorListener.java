package com.stripe.android.view;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import com.stripe.android.view.StripeEditText;

/* renamed from: com.stripe.android.view.f */
/* loaded from: classes2.dex */
class ErrorListener implements StripeEditText.AbstractC2474c {

    /* renamed from: a */
    TextInputLayout f12610a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorListener(TextInputLayout textInputLayout) {
        this.f12610a = textInputLayout;
    }

    @Override // com.stripe.android.view.StripeEditText.AbstractC2474c
    /* renamed from: a */
    public void mo17252a(@Nullable String str) {
        if (str == null) {
            this.f12610a.setErrorEnabled(false);
        } else {
            this.f12610a.setError(str);
        }
    }
}
