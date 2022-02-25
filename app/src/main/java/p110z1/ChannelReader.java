package p110z1;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: z1.aik */
/* loaded from: classes3.dex */
public final class ChannelReader {

    /* renamed from: a */
    public static final String f15967a = "channel";

    private ChannelReader() {
    }

    /* renamed from: a */
    public static ChannelInfo m13052a(File file) {
        Map<String, String> b = m13051b(file);
        if (b == null) {
            return null;
        }
        b.remove(f15967a);
        return new ChannelInfo(b.get(f15967a), b);
    }

    /* renamed from: b */
    public static Map<String, String> m13051b(File file) {
        try {
            String c = m13050c(file);
            if (c == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(c);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                hashMap.put(obj, jSONObject.getString(obj));
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static String m13050c(File file) {
        return PayloadReader.m13045a(file, aih.f15952d);
    }
}
