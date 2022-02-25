package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;

/* renamed from: com.tencent.smtt.utils.q */
/* loaded from: classes2.dex */
public class TbsUtils {

    /* renamed from: a */
    private static File f13418a;

    /* renamed from: a */
    public static long m16365a() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    @TargetApi(9)
    /* renamed from: a */
    public static boolean m16364a(Context context) {
        File dir;
        if (context == null) {
            return false;
        }
        if (f13418a != null) {
            return true;
        }
        try {
            if (context.getApplicationInfo().processName.contains(TbsConfig.APP_WX) && (dir = context.getDir("tbs", 0)) != null && dir.isDirectory()) {
                File file = new File(dir, "share");
                if (!file.isDirectory() && !file.mkdir()) {
                    return false;
                }
                f13418a = file;
                file.setExecutable(true, false);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
