package com.goldcoast.sdk.p052c;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* renamed from: com.goldcoast.sdk.c.g */
/* loaded from: classes.dex */
public final class LogUtil {

    /* renamed from: a */
    private static LogUtil f9013a = null;

    /* renamed from: b */
    private static Context f9014b = null;

    /* renamed from: c */
    private static boolean f9015c = false;

    private LogUtil() {
    }

    /* renamed from: a */
    public static void m20320a(Context context) {
        f9014b = context;
    }

    /* renamed from: a */
    public static LogUtil m20321a() {
        if (f9013a == null) {
            f9013a = new LogUtil();
        }
        return f9013a;
    }

    /* renamed from: a */
    public static void m20319a(String str) {
        if (f9015c) {
            Log.i("GOLD_COAST", str);
        }
    }

    /* renamed from: b */
    public static void m20318b(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(f9014b.getFilesDir().getAbsolutePath() + File.separator + "dump"), true);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
