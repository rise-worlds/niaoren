package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/* renamed from: z1.dd */
/* loaded from: classes3.dex */
public final class C5258dd {
    /* renamed from: a */
    public static String m3228a(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    /* renamed from: a */
    public static void m3227a(Context context, String str, Map<String, String> map) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        if (edit != null) {
            for (String str2 : map.keySet()) {
                edit.putString(str2, map.get(str2));
            }
            edit.commit();
        }
    }
}
