package p110z1;

import android.os.Environment;
import java.io.File;

/* renamed from: z1.db */
/* loaded from: classes3.dex */
public final class C5240db {
    /* renamed from: a */
    public static String m3334a(String str) {
        try {
            if (!m3335a()) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), str);
            if (!file.exists()) {
                return null;
            }
            file.delete();
            return "";
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m3335a() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState == null || externalStorageState.length() <= 0) {
            return false;
        }
        return (externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) && Environment.getExternalStorageDirectory() != null;
    }
}
