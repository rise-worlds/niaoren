package com.cyjh.ddy.base.utils;

import android.content.Context;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyjh.ddy.base.utils.o */
/* loaded from: classes.dex */
public class SDUtils {

    /* renamed from: a */
    private static final Map<String, SDUtils> f7132a = new HashMap();

    /* renamed from: d */
    private static Context f7133d;

    /* renamed from: b */
    private JSONObject f7134b;

    /* renamed from: c */
    private String f7135c;

    public SDUtils(String str) {
        String str2 = f7133d.getFilesDir().getAbsolutePath() + File.separator;
        CLog.m21882i("SDUtils", "dir=" + str2);
        if (SDCardUtils.m23409a()) {
            str2 = SDCardUtils.m23408b() + File.separator + AppUtils.m22920i() + File.separator + "SDUtils/";
        }
        this.f7135c = str2 + str;
        FileUtils.m22212e(str2);
        FileUtils.m22209f(this.f7135c);
        try {
            this.f7134b = new JSONObject(FileIOUtils.m22274b(this.f7135c));
        } catch (Exception unused) {
        }
        if (this.f7134b == null) {
            this.f7134b = new JSONObject();
        }
        CLog.m21882i("SDUtils", "file path=" + this.f7135c);
    }

    /* renamed from: a */
    public void m21765a(String str, String str2) {
        try {
            synchronized (this.f7134b) {
                this.f7134b.put(str, str2);
                FileUtils.m22209f(this.f7135c);
                FileIOUtils.m22283a(this.f7135c, this.f7134b.toString());
            }
        } catch (JSONException unused) {
        }
    }

    /* renamed from: a */
    public void m21766a(String str, int i) {
        try {
            synchronized (this.f7134b) {
                this.f7134b.put(str, i);
                FileUtils.m22209f(this.f7135c);
                FileIOUtils.m22283a(this.f7135c, this.f7134b.toString());
            }
        } catch (JSONException unused) {
        }
    }

    /* renamed from: b */
    public int m21763b(String str, int i) {
        try {
            synchronized (this.f7134b) {
                i = this.f7134b.getInt(str);
            }
        } catch (JSONException unused) {
        }
        return i;
    }

    /* renamed from: a */
    public String m21767a(String str) {
        return m21762b(str, "");
    }

    /* renamed from: b */
    public String m21762b(String str, String str2) {
        try {
            synchronized (this.f7134b) {
                str2 = this.f7134b.getString(str);
            }
        } catch (JSONException unused) {
        }
        return str2;
    }

    /* renamed from: a */
    public void m21769a() {
        synchronized (this.f7134b) {
            this.f7134b = new JSONObject();
            FileUtils.m22209f(this.f7135c);
            FileIOUtils.m22283a(this.f7135c, this.f7134b.toString());
        }
    }

    /* renamed from: b */
    public static SDUtils m21764b(String str) {
        SDUtils oVar = f7132a.get(str);
        if (oVar == null) {
            synchronized (SDUtils.class) {
                oVar = f7132a.get(str);
                if (oVar == null) {
                    oVar = new SDUtils(str);
                    f7132a.put(str, oVar);
                }
            }
        }
        return oVar;
    }

    /* renamed from: a */
    public static void m21768a(Context context) {
        f7133d = context;
    }
}
