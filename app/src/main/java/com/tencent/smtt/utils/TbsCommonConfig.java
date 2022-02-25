package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

/* renamed from: com.tencent.smtt.utils.n */
/* loaded from: classes2.dex */
public class TbsCommonConfig {

    /* renamed from: c */
    private static TbsCommonConfig f13393c;

    /* renamed from: a */
    private Context f13394a;

    /* renamed from: b */
    private File f13395b = null;

    /* renamed from: d */
    private String f13396d = "http://log.tbs.qq.com/ajax?c=pu&v=2&k=";

    /* renamed from: e */
    private String f13397e = "http://log.tbs.qq.com/ajax?c=pu&tk=";

    /* renamed from: f */
    private String f13398f = "http://wup.imtt.qq.com:8080";

    /* renamed from: g */
    private String f13399g = "http://log.tbs.qq.com/ajax?c=dl&k=";

    /* renamed from: h */
    private String f13400h = "http://cfg.imtt.qq.com/tbs?v=2&mk=";

    /* renamed from: i */
    private String f13401i = "http://log.tbs.qq.com/ajax?c=ul&v=2&k=";

    /* renamed from: j */
    private String f13402j = "http://mqqad.html5.qq.com/adjs";

    /* renamed from: k */
    private String f13403k = "http://log.tbs.qq.com/ajax?c=ucfu&k=";

    /* renamed from: a */
    public static synchronized TbsCommonConfig m16388a(Context context) {
        TbsCommonConfig nVar;
        synchronized (TbsCommonConfig.class) {
            if (f13393c == null) {
                f13393c = new TbsCommonConfig(context);
            }
            nVar = f13393c;
        }
        return nVar;
    }

    /* renamed from: a */
    public static synchronized TbsCommonConfig m16389a() {
        TbsCommonConfig nVar;
        synchronized (TbsCommonConfig.class) {
            nVar = f13393c;
        }
        return nVar;
    }

    @TargetApi(11)
    private TbsCommonConfig(Context context) {
        this.f13394a = null;
        TbsLog.m16527w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.f13394a = context.getApplicationContext();
        m16382g();
    }

    /* renamed from: g */
    private synchronized void m16382g() {
        Throwable th;
        File h;
        BufferedInputStream bufferedInputStream = null;
        try {
            h = m16381h();
        } catch (Throwable th2) {
            th = th2;
        }
        if (h == null) {
            TbsLog.m16533e("TbsCommonConfig", "Config file is null, default values will be applied");
            return;
        }
        bufferedInputStream = new BufferedInputStream(new FileInputStream(h));
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream);
            String property = properties.getProperty("pv_post_url", "");
            if (!"".equals(property)) {
                this.f13396d = property;
            }
            String property2 = properties.getProperty("wup_proxy_domain", "");
            if (!"".equals(property2)) {
                this.f13398f = property2;
            }
            String property3 = properties.getProperty("tbs_download_stat_post_url", "");
            if (!"".equals(property3)) {
                this.f13399g = property3;
            }
            String property4 = properties.getProperty("tbs_downloader_post_url", "");
            if (!"".equals(property4)) {
                this.f13400h = property4;
            }
            String property5 = properties.getProperty("tbs_log_post_url", "");
            if (!"".equals(property5)) {
                this.f13401i = property5;
            }
            String property6 = properties.getProperty("tips_url", "");
            if (!"".equals(property6)) {
                this.f13402j = property6;
            }
            String property7 = properties.getProperty("tbs_cmd_post_url", "");
            if (!"".equals(property7)) {
                this.f13403k = property7;
            }
            String property8 = properties.getProperty("pv_post_url_tk", "");
            if (!"".equals(property8)) {
                this.f13397e = property8;
            }
        } catch (Throwable th3) {
            th = th3;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            TbsLog.m16533e("TbsCommonConfig", "exceptions occurred1:" + stringWriter.toString());
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                }
            }
        }
        try {
            bufferedInputStream.close();
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
        }
    }

    /* renamed from: h */
    private File m16381h() {
        Throwable th;
        File file = null;
        try {
            if (this.f13395b == null) {
                this.f13395b = new File(FileUtil.m16465a(this.f13394a, 5));
                if (this.f13395b == null || !this.f13395b.isDirectory()) {
                    return null;
                }
            }
            File file2 = new File(this.f13395b, "tbsnet.conf");
            if (!file2.exists()) {
                TbsLog.m16533e("TbsCommonConfig", "Get file(" + file2.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.m16527w("TbsCommonConfig", "pathc:" + file2.getCanonicalPath());
                return file2;
            } catch (Throwable th2) {
                th = th2;
                file = file2;
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                TbsLog.m16533e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
                return file;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: b */
    public String m16387b() {
        return this.f13396d;
    }

    /* renamed from: c */
    public String m16386c() {
        return this.f13399g;
    }

    /* renamed from: d */
    public String m16385d() {
        return this.f13400h;
    }

    /* renamed from: e */
    public String m16384e() {
        return this.f13401i;
    }

    /* renamed from: f */
    public String m16383f() {
        return this.f13397e;
    }
}
