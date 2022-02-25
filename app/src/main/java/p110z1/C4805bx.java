package p110z1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.ref.WeakReference;

/* renamed from: z1.bx */
/* loaded from: classes3.dex */
final class C4805bx extends SQLiteOpenHelper {

    /* renamed from: a */
    private static final String f20362a = "msp.db";

    /* renamed from: b */
    private static final int f20363b = 1;

    /* renamed from: c */
    private WeakReference<Context> f20364c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4805bx(Context context) {
        super(context, f20362a, (SQLiteDatabase.CursorFactory) null, 1);
        this.f20364c = new WeakReference<>(context);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m8749a() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.execSQL("drop table if exists tb_tid");
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
            } catch (Exception e) {
                C4921cd.m5618a(e);
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
            }
            sQLiteDatabase.close();
        } catch (Throwable th) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r2.isOpen() != false) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
        if (r2.isOpen() != false) goto L_0x002c;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m8748a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: all -> 0x0039, Exception -> 0x004c
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: all -> 0x0035, Exception -> 0x0037
            java.lang.String r5 = r4.m8746c(r5, r6)     // Catch: all -> 0x0035, Exception -> 0x0037
            r6 = 0
            r3[r6] = r5     // Catch: all -> 0x0035, Exception -> 0x0037
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: all -> 0x0035, Exception -> 0x0037
            boolean r0 = r5.moveToFirst()     // Catch: all -> 0x0030, Exception -> 0x0033
            if (r0 == 0) goto L_0x001f
            java.lang.String r1 = r5.getString(r6)     // Catch: all -> 0x0030, Exception -> 0x0033
        L_0x001f:
            if (r5 == 0) goto L_0x0024
            r5.close()
        L_0x0024:
            if (r2 == 0) goto L_0x005c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005c
        L_0x002c:
            r2.close()
            goto L_0x005c
        L_0x0030:
            r6 = move-exception
            r1 = r5
            goto L_0x003b
        L_0x0033:
            goto L_0x004e
        L_0x0035:
            r6 = move-exception
            goto L_0x003b
        L_0x0037:
            r5 = r1
            goto L_0x004e
        L_0x0039:
            r6 = move-exception
            r2 = r1
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()
        L_0x0040:
            if (r2 == 0) goto L_0x004b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x004b
            r2.close()
        L_0x004b:
            throw r6
        L_0x004c:
            r5 = r1
            r2 = r5
        L_0x004e:
            if (r5 == 0) goto L_0x0053
            r5.close()
        L_0x0053:
            if (r2 == 0) goto L_0x005c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005c
            goto L_0x002c
        L_0x005c:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L_0x0072
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.f20364c
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = p110z1.C4873ca.m6513c(r5)
            java.lang.String r1 = p110z1.C3990az.m9818b(r1, r5)
        L_0x0072:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4805bx.m8748a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r2.isOpen() != false) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
        if (r2.isOpen() != false) goto L_0x002c;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m8747b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select key_tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: all -> 0x0039, Exception -> 0x004c
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: all -> 0x0035, Exception -> 0x0037
            java.lang.String r5 = r4.m8746c(r5, r6)     // Catch: all -> 0x0035, Exception -> 0x0037
            r6 = 0
            r3[r6] = r5     // Catch: all -> 0x0035, Exception -> 0x0037
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch: all -> 0x0035, Exception -> 0x0037
            boolean r0 = r5.moveToFirst()     // Catch: all -> 0x0030, Exception -> 0x0033
            if (r0 == 0) goto L_0x001f
            java.lang.String r1 = r5.getString(r6)     // Catch: all -> 0x0030, Exception -> 0x0033
        L_0x001f:
            if (r5 == 0) goto L_0x0024
            r5.close()
        L_0x0024:
            if (r2 == 0) goto L_0x005c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005c
        L_0x002c:
            r2.close()
            goto L_0x005c
        L_0x0030:
            r6 = move-exception
            r1 = r5
            goto L_0x003b
        L_0x0033:
            goto L_0x004e
        L_0x0035:
            r6 = move-exception
            goto L_0x003b
        L_0x0037:
            r5 = r1
            goto L_0x004e
        L_0x0039:
            r6 = move-exception
            r2 = r1
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()
        L_0x0040:
            if (r2 == 0) goto L_0x004b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x004b
            r2.close()
        L_0x004b:
            throw r6
        L_0x004c:
            r5 = r1
            r2 = r5
        L_0x004e:
            if (r5 == 0) goto L_0x0053
            r5.close()
        L_0x0053:
            if (r2 == 0) goto L_0x005c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005c
            goto L_0x002c
        L_0x005c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4805bx.m8747b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    private String m8746c(String str, String str2) {
        return str + str2;
    }
}
