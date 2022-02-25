package com.tendcloud.tenddata;

import android.text.TextUtils;
import com.tendcloud.tenddata.C2832ea;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ff */
/* loaded from: classes2.dex */
public final class C2870ff {

    /* renamed from: a */
    private static final String f13954a = "Archimedes_p";

    /* renamed from: b */
    private static int f13955b = 0;

    /* renamed from: c */
    private static final int f13956c = 1000000000;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m15743a() {
        int i = f13955b;
        if (i != 0) {
            return i;
        }
        try {
            C2832ea.getFileLock(C2832ea.EnumC2834b.AES_DATA_LOCK.toString());
            f13955b = C2867fc.m15763a(m15739b());
            if (f13955b == 0) {
                f13955b = C2855es.m15794b().nextInt(f13956c);
                m15742a(f13955b);
            }
            int i2 = f13955b;
            C2832ea.releaseFileLock(C2832ea.EnumC2834b.AES_DATA_LOCK.toString());
            return i2;
        } catch (Throwable unused) {
            C2832ea.releaseFileLock(C2832ea.EnumC2834b.AES_DATA_LOCK.toString());
            return 0;
        }
    }

    /* renamed from: b */
    private static int[][] m15739b() {
        int i;
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            for (int i3 = 1; i3 < 4; i3++) {
                String a = C2869fe.m15753a(f13954a + i3);
                if (!TextUtils.isEmpty(a)) {
                    arrayList.add(a);
                    i2++;
                }
            }
            int i4 = 4;
            while (true) {
                if (i4 >= 6) {
                    break;
                }
                String b = C2869fe.m15748b(f13954a + i4);
                if (!TextUtils.isEmpty(b)) {
                    arrayList.add(b);
                    i2++;
                }
                i4++;
            }
            if (i2 < 4) {
                for (i = 6; i < 8; i++) {
                    String c = C2869fe.m15746c(C2664ab.f13513g.getPackageName() + i);
                    if (!TextUtils.isEmpty(c)) {
                        arrayList.add(c);
                    }
                }
            }
            if (arrayList.size() < 4) {
                return null;
            }
            for (int i5 = 0; i5 < 4; i5++) {
                sb.append((String) arrayList.get(i5));
            }
            return m15741a(sb.toString(), 4, 2);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m15742a(int i) {
        int i2;
        int i3;
        int i4;
        ArrayList arrayList = new ArrayList(16);
        int i5 = 0;
        do {
            int[] iArr = new int[3];
            int[] iArr2 = new int[7];
            int[] iArr3 = new int[7];
            SecureRandom b = C2855es.m15794b();
            for (int i6 = 0; i6 < iArr.length; i6++) {
                iArr[i6] = b.nextInt(1000);
            }
            for (int i7 = 0; i7 < iArr2.length; i7++) {
                iArr2[i7] = b.nextInt(100);
            }
            int i8 = 0;
            while (true) {
                i2 = 1;
                if (i8 >= iArr3.length) {
                    break;
                }
                iArr3[i8] = (iArr[0] * iArr2[i8] * iArr2[i8] * iArr2[i8]) + (iArr[1] * iArr2[i8] * iArr2[i8]) + (iArr[2] * iArr2[i8]) + i;
                arrayList.add(iArr2[i8] + "," + iArr3[i8] + ",");
                i8++;
            }
            if (m15740a(arrayList, i)) {
                break;
            }
            arrayList.clear();
            i5++;
        } while (i5 < 7);
        while (true) {
            i3 = 4;
            if (i2 < 4) {
                C2869fe.m15751a(f13954a + i2, (String) arrayList.get(i2 - 1));
                i2++;
            }
        }
        while (true) {
            if (i3 < 6) {
                C2869fe.m15747b(f13954a + i3, (String) arrayList.get(i3 - 1));
                i3++;
            }
        }
        for (i4 = 6; i4 < 8; i4++) {
            C2869fe.m15745c(C2664ab.f13513g.getPackageName() + i4, (String) arrayList.get(i4 - 1));
        }
    }

    /* renamed from: a */
    private static boolean m15740a(ArrayList arrayList, int i) {
        String str = "";
        for (int i2 = 0; i2 < 4; i2++) {
            try {
                str = str.concat((String) arrayList.get(i2));
            } catch (Throwable unused) {
                return false;
            }
        }
        return i == C2867fc.m15763a(m15741a(str, 4, 2));
    }

    /* renamed from: a */
    private static int[][] m15741a(String str, int i, int i2) {
        int[][] iArr = (int[][]) Array.newInstance(int.class, i, i2);
        String[] split = str.split(",");
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            for (int i5 = 0; i5 < i2; i5++) {
                iArr[i3][i5] = Integer.parseInt(split[i4]);
                i4++;
            }
            i3++;
            i4 = i4;
        }
        return iArr;
    }
}
