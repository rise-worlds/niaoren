package com.stripe.android;

import android.support.annotation.NonNull;

/* renamed from: com.stripe.android.n */
/* loaded from: classes2.dex */
class PaymentSessionUtils {
    PaymentSessionUtils() {
    }

    @NonNull
    /* renamed from: a */
    public static String m17582a(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1867169789) {
            if (str.equals("success")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == -1010022050) {
            if (str.equals(PaymentResultListener.f11904d)) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 96784904) {
            if (hashCode == 2043678173 && str.equals(PaymentResultListener.f11902b)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(PaymentResultListener.f11903c)) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return "success";
            case 1:
                return PaymentResultListener.f11902b;
            case 2:
                return PaymentResultListener.f11903c;
            case 3:
                return PaymentResultListener.f11904d;
            default:
                return PaymentResultListener.f11903c;
        }
    }
}
