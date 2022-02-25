package p110z1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.app.C0648a;

/* renamed from: z1.cn */
/* loaded from: classes3.dex */
public class C4998cn {

    /* renamed from: a */
    private static final String f20876a = "content://com.alipay.android.app.settings.data.ServerProvider/current_server";

    /* renamed from: a */
    public static String m4582a(Context context) {
        if (C0648a.m25301b()) {
            return C3876ar.f17425b;
        }
        if (context == null) {
            return C3876ar.f17424a;
        }
        String str = C3876ar.f17424a;
        return TextUtils.isEmpty(str) ? C3876ar.f17424a : str;
    }

    /* renamed from: b */
    private static String m4581b(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse(f20876a), null, null, null, null);
        String str = null;
        if (query != null && query.getCount() > 0) {
            if (query.moveToFirst()) {
                str = query.getString(query.getColumnIndex("url"));
            }
            query.close();
        }
        return str;
    }
}
