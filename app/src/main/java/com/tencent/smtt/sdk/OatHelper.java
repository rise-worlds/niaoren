package com.tencent.smtt.sdk;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.tencent.smtt.utils.DataReader;
import java.io.IOException;
import java.util.UnknownFormatConversionException;

/* renamed from: com.tencent.smtt.sdk.c */
/* loaded from: classes2.dex */
public class OatHelper {

    /* renamed from: a */
    static int f13132a = 5;

    /* renamed from: b */
    static int f13133b = 16;

    /* renamed from: c */
    static char[] f13134c = new char[f13133b];

    /* renamed from: d */
    static String f13135d = "dex2oat-cmdline";

    /* renamed from: e */
    static long f13136e = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;

    /* renamed from: a */
    public static String m16835a(Context context, String str) throws Exception {
        DataReader cVar = new DataReader(str);
        cVar.m16492a(f13134c);
        boolean z = true;
        if (f13134c[f13132a] != 1) {
            z = false;
        }
        cVar.m16494a(z);
        cVar.m16495a(f13136e);
        return m16833a(new String(m16834a(cVar)));
    }

    /* renamed from: a */
    private static String m16833a(String str) {
        String[] split = str.split(new String("\u0000"));
        int i = 0;
        while (i < split.length) {
            int i2 = i + 1;
            String str2 = split[i];
            i = i2 + 1;
            String str3 = split[i2];
            if (str2.equals(f13135d)) {
                return str3;
            }
        }
        return "";
    }

    /* renamed from: a */
    public static char[] m16834a(DataReader cVar) throws IOException {
        char[] cArr = new char[4];
        char[] cArr2 = new char[4];
        cVar.m16492a(cArr);
        if (cArr[0] == 'o' && cArr[1] == 'a' && cArr[2] == 't') {
            cVar.m16492a(cArr2);
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            if (cArr2[1] <= '4') {
                cVar.m16491b();
                cVar.m16491b();
                cVar.m16491b();
            }
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            cVar.m16491b();
            char[] cArr3 = new char[cVar.m16491b()];
            cVar.m16492a(cArr3);
            return cArr3;
        }
        throw new UnknownFormatConversionException(String.format("Invalid art magic %c%c%c", Character.valueOf(cArr[0]), Character.valueOf(cArr[1]), Character.valueOf(cArr[2])));
    }
}
