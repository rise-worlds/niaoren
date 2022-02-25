package p110z1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.apd */
/* loaded from: classes3.dex */
public class PackageUtil {

    /* renamed from: a */
    private static final String f17113a = "bluestacks";

    /* renamed from: a */
    public static PackageInfo m11850a(Context context, String str) {
        if (context == null || str == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static List<PackageInfo> m11851a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            if (!packageInfo.packageName.contains(f17113a) && (packageInfo.applicationInfo.flags & 1) == 0 && (packageInfo.applicationInfo.flags & 128) == 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static void m11849b(Context context, String str) {
        new Intent();
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        launchIntentForPackage.setFlags(337641472);
        context.startActivity(launchIntentForPackage);
    }
}
