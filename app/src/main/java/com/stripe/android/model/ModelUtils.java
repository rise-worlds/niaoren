package com.stripe.android.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: com.stripe.android.model.f */
/* loaded from: classes2.dex */
class ModelUtils {
    ModelUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17797a(@Nullable String str) {
        return str != null && TextUtils.isDigitsOnly(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17799a(int i, int i2, Calendar calendar) {
        if (m17798a(i, calendar)) {
            return true;
        }
        return m17796b(i, calendar) == calendar.get(1) && i2 < calendar.get(2) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17798a(int i, Calendar calendar) {
        return m17796b(i, calendar) < calendar.get(1);
    }

    /* renamed from: b */
    static int m17796b(int i, Calendar calendar) {
        if (i >= 100 || i < 0) {
            return i;
        }
        String valueOf = String.valueOf(calendar.get(1));
        return Integer.parseInt(String.format(Locale.US, "%s%02d", valueOf.substring(0, valueOf.length() - 2), Integer.valueOf(i)));
    }
}
