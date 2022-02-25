package com.stripe.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.model.Card;

/* renamed from: com.stripe.android.b */
/* loaded from: classes2.dex */
public class CardUtils {

    /* renamed from: a */
    private static final int f11784a = 16;

    /* renamed from: b */
    private static final int f11785b = 15;

    /* renamed from: c */
    private static final int f11786c = 14;

    @NonNull
    /* renamed from: a */
    public static String m18078a(@Nullable String str) {
        return m18076a(str, true);
    }

    /* renamed from: b */
    public static boolean m18075b(@Nullable String str) {
        String c = StripeTextUtils.m17201c(str);
        return m18074c(c) && m18073d(c);
    }

    /* renamed from: c */
    static boolean m18074c(@Nullable String str) {
        if (str == null) {
            return false;
        }
        int i = 0;
        boolean z = true;
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (!Character.isDigit(charAt)) {
                return false;
            }
            int numericValue = Character.getNumericValue(charAt);
            z = !z;
            if (z) {
                numericValue *= 2;
            }
            if (numericValue > 9) {
                numericValue -= 9;
            }
            i += numericValue;
        }
        return i % 10 == 0;
    }

    /* renamed from: d */
    static boolean m18073d(@Nullable String str) {
        return str != null && m18077a(str, m18076a(str, false));
    }

    /* renamed from: a */
    static boolean m18077a(@Nullable String str, @NonNull String str2) {
        if (str == null || Card.f12006h.equals(str2)) {
            return false;
        }
        int length = str.length();
        char c = 65535;
        int hashCode = str2.hashCode();
        if (hashCode != -993787207) {
            if (hashCode == -298759312 && str2.equals(Card.f11999a)) {
                c = 0;
            }
        } else if (str2.equals(Card.f12002d)) {
            c = 1;
        }
        switch (c) {
            case 0:
                return length == 15;
            case 1:
                return length == 14;
            default:
                return length == 16;
        }
    }

    @NonNull
    /* renamed from: a */
    private static String m18076a(@Nullable String str, boolean z) {
        if (StripeTextUtils.m17202b(str)) {
            return Card.f12006h;
        }
        if (z) {
            str = StripeTextUtils.m17201c(str);
        }
        return StripeTextUtils.m17204a(str, Card.f12014p) ? Card.f11999a : StripeTextUtils.m17204a(str, Card.f12015q) ? Card.f12000b : StripeTextUtils.m17204a(str, Card.f12016r) ? Card.f12001c : StripeTextUtils.m17204a(str, Card.f12017s) ? Card.f12002d : StripeTextUtils.m17204a(str, Card.f12018t) ? Card.f12003e : StripeTextUtils.m17204a(str, Card.f12019u) ? Card.f12004f : StripeTextUtils.m17204a(str, Card.f12020v) ? Card.f12005g : Card.f12006h;
    }
}
