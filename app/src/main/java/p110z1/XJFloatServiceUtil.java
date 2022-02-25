package p110z1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;

/* renamed from: z1.adg */
/* loaded from: classes3.dex */
public class XJFloatServiceUtil {
    /* renamed from: a */
    public static void m14316a(Context context) {
        Intent intent = new Intent(m14315a(context, new Intent("com.lbs.xjservice.xx")));
        intent.putExtra("key", 1);
        context.startService(intent);
    }

    /* renamed from: a */
    private static Intent m14315a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        ResolveInfo resolveInfo = null;
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            return null;
        }
        if (queryIntentServices.size() > 1) {
            for (ResolveInfo resolveInfo2 : queryIntentServices) {
                if (resolveInfo2.serviceInfo.packageName.equals(context.getPackageName())) {
                    resolveInfo = resolveInfo2;
                }
            }
        } else {
            resolveInfo = queryIntentServices.get(0);
        }
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }
}
