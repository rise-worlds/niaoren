package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.smtt.sdk.TbsListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes2.dex */
public class TbsDownloadUpload {

    /* renamed from: b */
    private static TbsDownloadUpload f12893b;

    /* renamed from: a */
    Map<String, Object> f12894a = new HashMap();

    /* renamed from: c */
    private Context f12895c;

    /* renamed from: d */
    private int f12896d;

    /* renamed from: e */
    private int f12897e;

    /* renamed from: f */
    private int f12898f;

    /* renamed from: g */
    private int f12899g;

    /* renamed from: h */
    private int f12900h;

    /* renamed from: i */
    private int f12901i;
    public SharedPreferences mPreferences;

    /* loaded from: classes2.dex */
    public interface TbsUploadKey {
        public static final String KEY_LOCAL_CORE_VERSION = "tbs_local_core_version";
        public static final String KEY_NEEDDOWNLOAD_CODE = "tbs_needdownload_code";
        public static final String KEY_NEEDDOWNLOAD_RETURN = "tbs_needdownload_return";
        public static final String KEY_NEEDDOWNLOAD_SENT = "tbs_needdownload_sent";
        public static final String KEY_STARTDOWNLOAD_CODE = "tbs_startdownload_code";
        public static final String KEY_STARTDOWNLOAD_SENT = "tbs_startdownload_sent";
    }

    public TbsDownloadUpload(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_upload", 4);
        this.f12895c = context.getApplicationContext();
        if (this.f12895c == null) {
            this.f12895c = context;
        }
    }

    public static synchronized TbsDownloadUpload getInstance(Context context) {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            if (f12893b == null) {
                f12893b = new TbsDownloadUpload(context);
            }
            tbsDownloadUpload = f12893b;
        }
        return tbsDownloadUpload;
    }

    public static synchronized TbsDownloadUpload getInstance() {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            tbsDownloadUpload = f12893b;
        }
        return tbsDownloadUpload;
    }

    public static synchronized void clear() {
        synchronized (TbsDownloadUpload.class) {
            f12893b = null;
        }
    }

    public void clearUploadCode() {
        this.f12894a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, 0);
        this.f12894a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 0);
        this.f12894a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, 0);
        this.f12894a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, 0);
        this.f12894a.put(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, 0);
        this.f12894a.put(TbsUploadKey.KEY_LOCAL_CORE_VERSION, 0);
        writeTbsDownloadInfo();
    }

    public synchronized int getNeedDownloadCode() {
        if (this.f12899g == 1) {
            return TbsListener.ErrorCode.NEEDDOWNLOAD_9;
        }
        return this.f12896d;
    }

    public synchronized int getLocalCoreVersion() {
        return this.f12901i;
    }

    public synchronized int getStartDownloadCode() {
        if (this.f12900h == 1) {
            return TbsListener.ErrorCode.STARTDOWNLOAD_9;
        }
        return this.f12897e;
    }

    public synchronized int getNeedDownloadReturn() {
        return this.f12898f;
    }

    public synchronized void readTbsDownloadInfo(Context context) {
        Throwable th;
        File a;
        BufferedInputStream bufferedInputStream = null;
        try {
            a = m17034a(this.f12895c, "download_upload");
        } catch (Throwable th2) {
            th = th2;
        }
        if (a != null) {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(a));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                String property = properties.getProperty(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, "");
                if (!"".equals(property)) {
                    this.f12896d = Math.max(Integer.parseInt(property), 0);
                }
                String property2 = properties.getProperty(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, "");
                if (!"".equals(property2)) {
                    this.f12897e = Math.max(Integer.parseInt(property2), 0);
                }
                String property3 = properties.getProperty(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, "");
                if (!"".equals(property3)) {
                    this.f12898f = Math.max(Integer.parseInt(property3), 0);
                }
                String property4 = properties.getProperty(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, "");
                if (!"".equals(property4)) {
                    this.f12899g = Math.max(Integer.parseInt(property4), 0);
                }
                String property5 = properties.getProperty(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, "");
                if (!"".equals(property5)) {
                    this.f12900h = Math.max(Integer.parseInt(property5), 0);
                }
                String property6 = properties.getProperty(TbsUploadKey.KEY_LOCAL_CORE_VERSION, "");
                if (!"".equals(property6)) {
                    this.f12901i = Math.max(Integer.parseInt(property6), 0);
                }
            } catch (Throwable th3) {
                th = th3;
                th.printStackTrace();
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                    }
                }
            }
            try {
                bufferedInputStream.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static File m17034a(Context context, String str) {
        TbsInstaller.m16742a();
        File s = TbsInstaller.m16676s(context);
        if (s == null) {
            return null;
        }
        File file = new File(s, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:22:0x0099
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public synchronized void writeTbsDownloadInfo() {
        /*
            Method dump skipped, instructions count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadUpload.writeTbsDownloadInfo():void");
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }
}
