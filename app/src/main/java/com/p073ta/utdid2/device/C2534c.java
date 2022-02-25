package com.p073ta.utdid2.device;

import android.content.Context;
import android.os.Binder;
import android.provider.Settings;
import android.text.TextUtils;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.p073ta.utdid2.p074a.p075a.C2511b;
import com.p073ta.utdid2.p074a.p075a.C2516d;
import com.p073ta.utdid2.p074a.p075a.C2517e;
import com.p073ta.utdid2.p074a.p075a.C2518f;
import com.p073ta.utdid2.p074a.p075a.C2521g;
import com.p073ta.utdid2.p076b.p077a.C2527c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tools.tar.TarConstants;

/* renamed from: com.ta.utdid2.device.c */
/* loaded from: classes2.dex */
public class C2534c {

    /* renamed from: a */
    private C2527c f12730a;

    /* renamed from: a */
    private C2535d f12731a;

    /* renamed from: b */
    private C2527c f12732b;

    /* renamed from: i */
    private String f12735i;

    /* renamed from: j */
    private String f12736j;
    private Context mContext;

    /* renamed from: e */
    private static final Object f12728e = new Object();

    /* renamed from: a */
    private static C2534c f12727a = null;

    /* renamed from: k */
    private static final String f12729k = ".UTSystemConfig" + File.separator + "Global";

    /* renamed from: h */
    private String f12734h = null;

    /* renamed from: b */
    private Pattern f12733b = Pattern.compile("[^0-9a-zA-Z=/+]+");

    private C2534c(Context context) {
        this.mContext = null;
        this.f12731a = null;
        this.f12735i = "xx_utdid_key";
        this.f12736j = "xx_utdid_domain";
        this.f12730a = null;
        this.f12732b = null;
        this.mContext = context;
        this.f12732b = new C2527c(context, f12729k, "Alvin2", false, true);
        this.f12730a = new C2527c(context, ".DataStorage", "ContextData", false, true);
        this.f12731a = new C2535d();
        this.f12735i = String.format("K_%d", Integer.valueOf(C2521g.m17167a(this.f12735i)));
        this.f12736j = String.format("D_%d", Integer.valueOf(C2521g.m17167a(this.f12736j)));
    }

    /* renamed from: c */
    private void m17100c() {
        C2527c cVar = this.f12732b;
        if (cVar != null) {
            if (C2521g.m17166a(cVar.getString("UTDID2"))) {
                String string = this.f12732b.getString("UTDID");
                if (!C2521g.m17166a(string)) {
                    m17097f(string);
                }
            }
            boolean z = false;
            if (!C2521g.m17166a(this.f12732b.getString("DID"))) {
                this.f12732b.remove("DID");
                z = true;
            }
            if (!C2521g.m17166a(this.f12732b.getString("EI"))) {
                this.f12732b.remove("EI");
                z = true;
            }
            if (!C2521g.m17166a(this.f12732b.getString("SI"))) {
                this.f12732b.remove("SI");
                z = true;
            }
            if (z) {
                this.f12732b.commit();
            }
        }
    }

    /* renamed from: a */
    public static C2534c m17103a(Context context) {
        if (context != null && f12727a == null) {
            synchronized (f12728e) {
                if (f12727a == null) {
                    f12727a = new C2534c(context);
                    f12727a.m17100c();
                }
            }
        }
        return f12727a;
    }

    /* renamed from: f */
    private void m17097f(String str) {
        C2527c cVar;
        if (m17102b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && (cVar = this.f12732b) != null) {
                cVar.putString("UTDID2", str);
                this.f12732b.commit();
            }
        }
    }

    /* renamed from: g */
    private void m17095g(String str) {
        C2527c cVar;
        if (str != null && (cVar = this.f12730a) != null && !str.equals(cVar.getString(this.f12735i))) {
            this.f12730a.putString(this.f12735i, str);
            this.f12730a.commit();
        }
    }

    /* renamed from: h */
    private void m17093h(String str) {
        if (m17098f() && m17102b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length()) {
                String str2 = null;
                try {
                    str2 = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
                } catch (Exception unused) {
                }
                if (!m17102b(str2)) {
                    try {
                        Settings.System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", str);
                    } catch (Exception unused2) {
                    }
                }
            }
        }
    }

    /* renamed from: i */
    private void m17091i(String str) {
        String str2;
        try {
            str2 = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused) {
            str2 = null;
        }
        if (!str.equals(str2)) {
            try {
                Settings.System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", str);
            } catch (Exception unused2) {
            }
        }
    }

    /* renamed from: j */
    private void m17090j(String str) {
        if (m17098f() && str != null) {
            m17091i(str);
        }
    }

    /* renamed from: g */
    private String m17096g() {
        C2527c cVar = this.f12732b;
        if (cVar == null) {
            return null;
        }
        String string = cVar.getString("UTDID2");
        if (C2521g.m17166a(string) || this.f12731a.m17089c(string) == null) {
            return null;
        }
        return string;
    }

    /* renamed from: b */
    private boolean m17102b(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !this.f12733b.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    public synchronized String getValue() {
        if (this.f12734h != null) {
            return this.f12734h;
        }
        return m17094h();
    }

    /* renamed from: h */
    public synchronized String m17094h() {
        this.f12734h = m17092i();
        if (!TextUtils.isEmpty(this.f12734h)) {
            return this.f12734h;
        }
        try {
            byte[] c = m17099c();
            if (c != null) {
                this.f12734h = C2511b.encodeToString(c, 2);
                m17097f(this.f12734h);
                String c2 = this.f12731a.m17088c(c);
                if (c2 != null) {
                    m17090j(c2);
                    m17095g(c2);
                }
                return this.f12734h;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: i */
    public synchronized String m17092i() {
        String str;
        String string;
        try {
            string = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
        } catch (Exception unused) {
        }
        if (m17102b(string)) {
            return string;
        }
        C2536e eVar = new C2536e();
        boolean z = false;
        try {
            str = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused2) {
            str = null;
        }
        if (!C2521g.m17166a(str)) {
            String e = eVar.m17085e(str);
            if (m17102b(e)) {
                m17093h(e);
                return e;
            }
            String d = eVar.m17086d(str);
            if (m17102b(d)) {
                String c = this.f12731a.m17089c(d);
                if (!C2521g.m17166a(c)) {
                    m17090j(c);
                    try {
                        str = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
                    } catch (Exception unused3) {
                    }
                }
            }
            String d2 = this.f12731a.m17087d(str);
            if (m17102b(d2)) {
                this.f12734h = d2;
                m17097f(d2);
                m17095g(str);
                m17093h(this.f12734h);
                return this.f12734h;
            }
        } else {
            z = true;
        }
        String g = m17096g();
        if (m17102b(g)) {
            String c2 = this.f12731a.m17089c(g);
            if (z) {
                m17090j(c2);
            }
            m17093h(g);
            m17095g(c2);
            this.f12734h = g;
            return g;
        }
        String string2 = this.f12730a.getString(this.f12735i);
        if (!C2521g.m17166a(string2)) {
            String d3 = eVar.m17086d(string2);
            if (!m17102b(d3)) {
                d3 = this.f12731a.m17087d(string2);
            }
            if (m17102b(d3)) {
                String c3 = this.f12731a.m17089c(d3);
                if (!C2521g.m17166a(d3)) {
                    this.f12734h = d3;
                    if (z) {
                        m17090j(c3);
                    }
                    m17097f(this.f12734h);
                    return this.f12734h;
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    private byte[] m17099c() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextInt = new Random().nextInt();
        byte[] bytes = C2516d.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = C2516d.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = C2517e.m17175a(this.mContext);
        } catch (Exception unused) {
            str = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(C2516d.getBytes(C2521g.m17167a(str)), 0, 4);
        byteArrayOutputStream.write(C2516d.getBytes(C2521g.m17167a(m17101b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static String m17101b(byte[] bArr) throws Exception {
        byte[] bArr2 = {69, 114, 116, -33, 125, -54, -31, 86, -11, FileDownloadStatus.f10400b, -78, -96, -17, -99, 64, 23, -95, -126, -82, -64, 113, 116, -16, -103, TarConstants.LF_LINK, -30, 9, -39, 33, -80, -68, -78, -117, TarConstants.LF_DIR, 30, -122, 64, -104, 74, -49, 106, 85, -38, -93};
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(C2518f.m17169a(bArr2), instance.getAlgorithm()));
        return C2511b.encodeToString(instance.doFinal(bArr), 2);
    }

    /* renamed from: f */
    private boolean m17098f() {
        return this.mContext.checkPermission("android.permission.WRITE_SETTINGS", Binder.getCallingPid(), Binder.getCallingUid()) == 0;
    }
}
