package com.tencent.smtt.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

/* renamed from: com.tencent.smtt.sdk.h */
/* loaded from: classes2.dex */
public class SqliteDataManager {

    /* renamed from: a */
    public static final String f13165a = CookieManager.LOGTAG;

    /* renamed from: b */
    static File f13166b;

    /* renamed from: a */
    public static File m16812a(Context context) {
        if (f13166b == null && context != null) {
            f13166b = new File(context.getDir("webview", 0), "Cookies");
        }
        if (f13166b == null) {
            f13166b = new File("/data/data/" + context.getPackageName() + File.separator + "app_webview" + File.separator + "Cookies");
        }
        return f13166b;
    }

    /* renamed from: b */
    public static boolean m16808b(Context context) {
        if (context == null) {
            return false;
        }
        FileUtil.m16455a(m16812a(context), false);
        return true;
    }

    /* renamed from: c */
    public static SQLiteDatabase m16807c(Context context) {
        File a;
        SQLiteDatabase sQLiteDatabase = null;
        if (context == null || (a = m16812a(context)) == null) {
            return null;
        }
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(a.getAbsolutePath(), null, 0);
        } catch (Exception unused) {
        }
        if (sQLiteDatabase == null) {
            TbsLog.m16531i(f13165a, "dbPath is not exist!");
        }
        return sQLiteDatabase;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
        if (r4.isOpen() != false) goto L_0x0058;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0056, code lost:
        if (r4.isOpen() != false) goto L_0x0058;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
        r4.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.String> m16810a(android.database.sqlite.SQLiteDatabase r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "select * from sqlite_master where type='table'"
            android.database.Cursor r0 = r4.rawQuery(r2, r0)     // Catch: Throwable -> 0x004a
            boolean r2 = r0.moveToFirst()     // Catch: Throwable -> 0x004a
            if (r2 == 0) goto L_0x002a
        L_0x0015:
            r2 = 1
            java.lang.String r2 = r0.getString(r2)     // Catch: Throwable -> 0x004a
            r3 = 4
            r0.getString(r3)     // Catch: Throwable -> 0x004a
            r1.add(r2)     // Catch: Throwable -> 0x004a
            m16809a(r4, r2)     // Catch: Throwable -> 0x004a
            boolean r2 = r0.moveToNext()     // Catch: Throwable -> 0x004a
            if (r2 != 0) goto L_0x0015
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            r0.close()
        L_0x002f:
            if (r4 == 0) goto L_0x005b
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x005b
            goto L_0x0058
        L_0x0038:
            r1 = move-exception
            if (r0 == 0) goto L_0x003e
            r0.close()
        L_0x003e:
            if (r4 == 0) goto L_0x0049
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x0049
            r4.close()
        L_0x0049:
            throw r1
        L_0x004a:
            if (r0 == 0) goto L_0x0050
            r0.close()
        L_0x0050:
            if (r4 == 0) goto L_0x005b
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x005b
        L_0x0058:
            r4.close()
        L_0x005b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SqliteDataManager.m16810a(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    /* renamed from: a */
    private static String m16809a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + str, null);
        int count = rawQuery.getCount();
        int columnCount = rawQuery.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("raws:" + count + ",columns:" + columnCount + "\n");
        if (count <= 0 || !rawQuery.moveToFirst()) {
            return sb.toString();
        }
        do {
            sb.append("\n");
            for (int i = 0; i < columnCount; i++) {
                try {
                    sb.append(rawQuery.getString(i));
                    sb.append(",");
                } catch (Exception unused) {
                }
            }
            sb.append("\n");
        } while (rawQuery.moveToNext());
        return sb.toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:26:0x005a
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: d */
    public static int m16806d(android.content.Context r4) {
        /*
            java.lang.System.currentTimeMillis()
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r4 = m16807c(r4)     // Catch: Throwable -> 0x0071
            if (r4 != 0) goto L_0x0018
            r0 = -1
            if (r4 == 0) goto L_0x0017
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0017
            r4.close()
        L_0x0017:
            return r0
        L_0x0018:
            java.lang.String r2 = "select * from meta"
            android.database.Cursor r1 = r4.rawQuery(r2, r1)     // Catch: Throwable -> 0x005c
            int r2 = r1.getCount()     // Catch: Throwable -> 0x005c
            r1.getColumnCount()     // Catch: Throwable -> 0x005c
            if (r2 <= 0) goto L_0x0049
            boolean r2 = r1.moveToFirst()     // Catch: Throwable -> 0x005c
            if (r2 == 0) goto L_0x0049
        L_0x002d:
            java.lang.String r2 = r1.getString(r0)     // Catch: Throwable -> 0x005c
            java.lang.String r3 = "version"
            boolean r2 = r2.equals(r3)     // Catch: Throwable -> 0x005c
            if (r2 == 0) goto L_0x0043
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch: Throwable -> 0x005c
            int r0 = java.lang.Integer.parseInt(r2)     // Catch: Throwable -> 0x005c
            goto L_0x0049
        L_0x0043:
            boolean r2 = r1.moveToNext()     // Catch: Throwable -> 0x005c
            if (r2 != 0) goto L_0x002d
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.close()
        L_0x004e:
            if (r4 == 0) goto L_0x0080
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0080
        L_0x0056:
            r4.close()
            goto L_0x0080
        L_0x005a:
            r0 = move-exception
            goto L_0x0060
        L_0x005c:
            goto L_0x0072
        L_0x005e:
            r0 = move-exception
            r4 = r1
        L_0x0060:
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            if (r4 == 0) goto L_0x0070
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0070
            r4.close()
        L_0x0070:
            throw r0
        L_0x0071:
            r4 = r1
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r1.close()
        L_0x0077:
            if (r4 == 0) goto L_0x0080
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0080
            goto L_0x0056
        L_0x0080:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SqliteDataManager.m16806d(android.content.Context):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ed, code lost:
        if (r12.isOpen() != false) goto L_0x00c0;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b5 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ba A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m16811a(android.content.Context r8, com.tencent.smtt.sdk.CookieManager.EnumC2544a r9, java.lang.String r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SqliteDataManager.m16811a(android.content.Context, com.tencent.smtt.sdk.CookieManager$a, java.lang.String, boolean, boolean):void");
    }
}
