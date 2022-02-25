package com.stripe.android.view;

import android.support.annotation.NonNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* renamed from: com.stripe.android.view.d */
/* loaded from: classes2.dex */
class CountryUtils {

    /* renamed from: a */
    static final String[] f12607a = {"AE", "AG", "AN", "AO", "AW", "BF", "BI", "BJ", "BO", "BS", "BW", "BZ", "CD", "CF", "CG", "CI", "CK", "CM", "DJ", "DM", "ER", "FJ", "GD", "GH", "GM", "GN", "GQ", "GY", "HK", "IE", "JM", "KE", "KI", "KM", "KN", "KP", "LC", "ML", "MO", "MR", "MS", "MU", "MW", "NR", "NU", "PA", "QA", "RW", "SA", "SB", "SC", "SL", "SO", "SR", "ST", "SY", "TF", "TK", "TL", "TO", "TT", "TV", "TZ", "UG", "VU", "YE", "ZA", "ZW"};

    /* renamed from: b */
    static final Set<String> f12608b = new HashSet(Arrays.asList(f12607a));

    CountryUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17263a(@NonNull String str) {
        return !f12608b.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m17262b(@NonNull String str) {
        return Pattern.matches("^[0-9]{5}(?:-[0-9]{4})?$", str);
    }

    /* renamed from: c */
    static boolean m17261c(@NonNull String str) {
        return Pattern.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$", str);
    }

    /* renamed from: d */
    static boolean m17260d(@NonNull String str) {
        return Pattern.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Map<String, String> m17264a() {
        String[] iSOCountries;
        HashMap hashMap = new HashMap();
        for (String str : Locale.getISOCountries()) {
            hashMap.put(new Locale("", str).getDisplayCountry(), str);
        }
        return hashMap;
    }
}
