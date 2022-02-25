package p110z1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* renamed from: z1.fi */
/* loaded from: classes3.dex */
public class VersionUtils {
    /* renamed from: a */
    public static boolean m2839a(UpdateBean ajkVar, Context context) {
        try {
            int a = m2842a(context, context.getPackageName());
            if (ajkVar.getVersionCode() > 0 && a != -1) {
                if (a < ajkVar.getVersionCode()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: a */
    public static int m2842a(Context context, String str) {
        if (context == null || str == null || str.trim().equals("")) {
            return -1;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionCode;
    }

    /* renamed from: b */
    public static boolean m2837b(UpdateBean ajkVar, Context context) {
        try {
            String b = m2838b(context, context.getPackageName());
            if (!(ajkVar.getVersion() == null || "".equals(ajkVar.getVersion()) || b == null)) {
                if (b.compareTo(ajkVar.getVersion()) < 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static String m2838b(Context context, String str) {
        PackageInfo packageInfo = null;
        if (context == null || str == null || str.trim().equals("")) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionName;
    }

    /* renamed from: a */
    public static void m2840a(final String str) {
        new Thread(new Runnable() { // from class: z1.fi.1
            /* JADX WARN: Code restructure failed: missing block: B:34:0x00fa, code lost:
                if (r0 == 0) goto L_0x00b7;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00f4 A[Catch: Exception -> 0x00fd, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x00fd, blocks: (B:13:0x00ae, B:14:0x00b4, B:15:0x00b7, B:33:0x00f4), top: B:47:0x0014 }] */
            /* JADX WARN: Removed duplicated region for block: B:40:0x0109 A[Catch: Exception -> 0x010f, TryCatch #0 {Exception -> 0x010f, blocks: (B:38:0x0101, B:40:0x0109, B:41:0x010c), top: B:45:0x0101 }] */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Type inference failed for: r0v11, types: [java.io.BufferedReader] */
            /* JADX WARN: Type inference failed for: r0v15 */
            /* JADX WARN: Type inference failed for: r0v17 */
            /* JADX WARN: Type inference failed for: r0v2, types: [java.io.BufferedReader] */
            /* JADX WARN: Type inference failed for: r0v22, types: [java.io.BufferedReader] */
            /* JADX WARN: Type inference failed for: r0v3 */
            /* JADX WARN: Type inference failed for: r0v4 */
            /* JADX WARN: Type inference failed for: r0v6 */
            /* JADX WARN: Type inference failed for: r0v9 */
            /* JADX WARN: Unknown variable types count: 1 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 272
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: p110z1.VersionUtils.RunnableC53531.run():void");
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x010a A[Catch: Exception -> 0x0111, TryCatch #10 {Exception -> 0x0111, blocks: (B:36:0x0102, B:38:0x010a, B:39:0x010d), top: B:58:0x0102 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x011d A[Catch: Exception -> 0x0124, TryCatch #9 {Exception -> 0x0124, blocks: (B:44:0x0115, B:46:0x011d, B:47:0x0120), top: B:56:0x0115 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0115 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0102 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v20, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unknown variable types count: 2 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m2841a(java.io.File r8, android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.VersionUtils.m2841a(java.io.File, android.content.Context):boolean");
    }
}
