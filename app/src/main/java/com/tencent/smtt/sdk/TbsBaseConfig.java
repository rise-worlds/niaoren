package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes2.dex */
public abstract class TbsBaseConfig {
    public static final String TAG = "TbsBaseConfig";

    /* renamed from: a */
    Map<String, String> f12878a;

    /* renamed from: b */
    private Context f12879b;

    public abstract String getConfigFileName();

    public void init(Context context) {
        this.f12878a = new HashMap();
        this.f12879b = context.getApplicationContext();
        if (this.f12879b == null) {
            this.f12879b = context;
        }
        refreshSyncMap(context);
    }

    /* renamed from: a */
    private static File m17038a(Context context, String str) {
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

    public void clear() {
        this.f12878a.clear();
        commit();
    }

    public synchronized void refreshSyncMap(Context context) {
        Throwable th;
        File a;
        BufferedInputStream bufferedInputStream = null;
        try {
            a = m17038a(this.f12879b, getConfigFileName());
        } catch (Throwable th2) {
            th = th2;
        }
        if (a != null) {
            this.f12878a.clear();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(a));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                for (String str : properties.stringPropertyNames()) {
                    this.f12878a.put(str, properties.getProperty(str));
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

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:22:0x009e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public synchronized void writeTbsDownloadInfo() {
        /*
            Method dump skipped, instructions count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.writeTbsDownloadInfo():void");
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }
}
