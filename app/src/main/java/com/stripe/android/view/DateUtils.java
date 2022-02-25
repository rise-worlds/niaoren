package com.stripe.android.view;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.VisibleForTesting;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.util.Calendar;

/* renamed from: com.stripe.android.view.e */
/* loaded from: classes2.dex */
class DateUtils {

    /* renamed from: a */
    static final int f12609a = 9980;

    DateUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17255a(@Nullable String str) {
        if (str == null) {
            return false;
        }
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt > 0 && parseInt <= 12;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Size(2)
    @NonNull
    /* renamed from: b */
    public static String[] m17253b(@Size(max = 4) @NonNull String str) {
        String[] strArr = new String[2];
        if (str.length() >= 2) {
            strArr[0] = str.substring(0, 2);
            strArr[1] = str.substring(2);
        } else {
            strArr[0] = str;
            strArr[1] = "";
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17258a(int i, int i2) {
        return m17257a(i, i2, Calendar.getInstance());
    }

    @VisibleForTesting
    /* renamed from: a */
    static boolean m17257a(int i, int i2, @NonNull Calendar calendar) {
        int i3;
        if (i < 1 || i > 12 || i2 < 0 || i2 > f12609a || i2 < (i3 = calendar.get(1))) {
            return false;
        }
        return i2 > i3 || i >= calendar.get(2) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m17254b(@IntRange(from = 1, m25695to = 12) int i, @IntRange(from = 0, m25695to = 9999) int i2) {
        String valueOf = String.valueOf(i);
        if (valueOf.length() == 1) {
            valueOf = ResultTypeConstant.f7213z + valueOf;
        }
        String valueOf2 = String.valueOf(i2);
        if (valueOf2.length() == 3) {
            return "";
        }
        if (valueOf2.length() > 2) {
            valueOf2 = valueOf2.substring(valueOf2.length() - 2);
        } else if (valueOf2.length() == 1) {
            valueOf2 = ResultTypeConstant.f7213z + valueOf2;
        }
        return valueOf + valueOf2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @IntRange(from = 1000, m25695to = 9999)
    /* renamed from: a */
    public static int m17259a(@IntRange(from = 0, m25695to = 99) int i) {
        return m17256a(i, Calendar.getInstance());
    }

    @VisibleForTesting
    @IntRange(from = 1000, m25695to = 9999)
    /* renamed from: a */
    static int m17256a(@IntRange(from = 0, m25695to = 99) int i, @NonNull Calendar calendar) {
        int i2 = calendar.get(1);
        int i3 = i2 / 100;
        int i4 = i2 % 100;
        if (i4 > 80 && i < 20) {
            i3++;
        } else if (i4 < 20 && i > 80) {
            i3--;
        }
        return (i3 * 100) + i;
    }
}
