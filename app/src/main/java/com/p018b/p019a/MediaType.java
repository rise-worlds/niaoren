package com.p018b.p019a;

import com.github.kevinsawicki.http.HttpRequest;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.b.a.ah */
/* loaded from: classes.dex */
public final class MediaType {

    /* renamed from: a */
    private static final Pattern f6083a = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* renamed from: b */
    private static final Pattern f6084b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* renamed from: c */
    private final String f6085c;

    /* renamed from: d */
    private final String f6086d;

    /* renamed from: e */
    private final String f6087e;

    /* renamed from: f */
    private final String f6088f;

    private MediaType(String str, String str2, String str3, String str4) {
        this.f6085c = str;
        this.f6086d = str2;
        this.f6087e = str3;
        this.f6088f = str4;
    }

    /* renamed from: a */
    public static MediaType m24510a(String str) {
        Matcher matcher = f6083a.matcher(str);
        if (!matcher.lookingAt()) {
            return null;
        }
        String lowerCase = matcher.group(1).toLowerCase(Locale.US);
        String lowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        Matcher matcher2 = f6084b.matcher(str);
        String str2 = null;
        for (int end = matcher.end(); end < str.length(); end = matcher2.end()) {
            matcher2.region(end, str.length());
            if (!matcher2.lookingAt()) {
                return null;
            }
            String group = matcher2.group(1);
            if (group != null && group.equalsIgnoreCase(HttpRequest.PARAM_CHARSET)) {
                String group2 = matcher2.group(2);
                if (group2 == null) {
                    group2 = matcher2.group(3);
                } else if (group2.startsWith("'") && group2.endsWith("'") && group2.length() > 2) {
                    group2 = group2.substring(1, group2.length() - 1);
                }
                if (str2 == null || group2.equalsIgnoreCase(str2)) {
                    str2 = group2;
                } else {
                    throw new IllegalArgumentException("Multiple different charsets: " + str);
                }
            }
        }
        return new MediaType(str, lowerCase, lowerCase2, str2);
    }

    /* renamed from: a */
    public final Charset m24509a(Charset charset) {
        String str = this.f6088f;
        return str != null ? Charset.forName(str) : charset;
    }

    public final String toString() {
        return this.f6085c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof MediaType) && ((MediaType) obj).f6085c.equals(this.f6085c);
    }

    public final int hashCode() {
        return this.f6085c.hashCode();
    }
}
