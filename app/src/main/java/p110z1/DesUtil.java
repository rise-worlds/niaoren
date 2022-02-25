package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.nrzs.data.p065en.Abcd;
import com.nrzs.data.p065en.Ufc;

/* renamed from: z1.alv */
/* loaded from: classes3.dex */
public class DesUtil {
    /* renamed from: a */
    public static String m12535a(String str, Context context) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (context == null) {
            Log.e("decode", "报错decode: context=null");
        }
        try {
            Abcd abcd = new Abcd();
            abcd.setSource(str);
            abcd.setCryptType(2);
            abcd.setPurpose(0);
            abcd.setIndex(0);
            return new Ufc().y11(abcd, context);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
