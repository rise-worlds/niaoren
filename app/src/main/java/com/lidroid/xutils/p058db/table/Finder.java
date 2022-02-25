package com.lidroid.xutils.p058db.table;

import com.lidroid.xutils.p058db.sqlite.ColumnDbType;
import java.lang.reflect.Field;

/* renamed from: com.lidroid.xutils.db.table.Finder */
/* loaded from: classes.dex */
public class Finder extends Column {
    private final String targetColumnName;
    private final String valueColumnName;

    @Override // com.lidroid.xutils.p058db.table.Column
    public Object getColumnValue(Object obj) {
        return null;
    }

    @Override // com.lidroid.xutils.p058db.table.Column
    public Object getDefaultValue() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Finder(Class<?> cls, Field field) {
        super(cls, field);
        com.lidroid.xutils.p058db.annotation.Finder finder = (com.lidroid.xutils.p058db.annotation.Finder) field.getAnnotation(com.lidroid.xutils.p058db.annotation.Finder.class);
        this.valueColumnName = finder.valueColumn();
        this.targetColumnName = finder.targetColumn();
    }

    public Class<?> getTargetEntityType() {
        return ColumnUtils.getFinderTargetEntityType(this);
    }

    public String getTargetColumnName() {
        return this.targetColumnName;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.lidroid.xutils.p058db.table.Column
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setValue2Entity(java.lang.Object r3, android.database.Cursor r4, int r5) {
        /*
            r2 = this;
            java.lang.reflect.Field r4 = r2.columnField
            java.lang.Class r4 = r4.getType()
            java.lang.Class r5 = r3.getClass()
            java.lang.String r0 = r2.valueColumnName
            com.lidroid.xutils.db.table.Column r5 = com.lidroid.xutils.p058db.table.TableUtils.getColumnOrId(r5, r0)
            java.lang.Object r5 = r5.getColumnValue(r3)
            java.lang.Class<com.lidroid.xutils.db.sqlite.FinderLazyLoader> r0 = com.lidroid.xutils.p058db.sqlite.FinderLazyLoader.class
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0022
            com.lidroid.xutils.db.sqlite.FinderLazyLoader r4 = new com.lidroid.xutils.db.sqlite.FinderLazyLoader
            r4.<init>(r2, r5)
            goto L_0x0050
        L_0x0022:
            java.lang.Class<java.util.List> r0 = java.util.List.class
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x003d
            com.lidroid.xutils.db.sqlite.FinderLazyLoader r4 = new com.lidroid.xutils.db.sqlite.FinderLazyLoader     // Catch: DbException -> 0x0034
            r4.<init>(r2, r5)     // Catch: DbException -> 0x0034
            java.util.List r4 = r4.getAllFromDb()     // Catch: DbException -> 0x0034
            goto L_0x0050
        L_0x0034:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            com.lidroid.xutils.util.LogUtils.m19219e(r5, r4)
            goto L_0x004f
        L_0x003d:
            com.lidroid.xutils.db.sqlite.FinderLazyLoader r4 = new com.lidroid.xutils.db.sqlite.FinderLazyLoader     // Catch: DbException -> 0x0047
            r4.<init>(r2, r5)     // Catch: DbException -> 0x0047
            java.lang.Object r4 = r4.getFirstFromDb()     // Catch: DbException -> 0x0047
            goto L_0x0050
        L_0x0047:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            com.lidroid.xutils.util.LogUtils.m19219e(r5, r4)
        L_0x004f:
            r4 = 0
        L_0x0050:
            java.lang.reflect.Method r5 = r2.setMethod
            r0 = 1
            if (r5 == 0) goto L_0x0069
            java.lang.reflect.Method r5 = r2.setMethod     // Catch: Throwable -> 0x0060
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: Throwable -> 0x0060
            r1 = 0
            r0[r1] = r4     // Catch: Throwable -> 0x0060
            r5.invoke(r3, r0)     // Catch: Throwable -> 0x0060
            return
        L_0x0060:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()
            com.lidroid.xutils.util.LogUtils.m19219e(r4, r3)
            return
        L_0x0069:
            java.lang.reflect.Field r5 = r2.columnField     // Catch: Throwable -> 0x0074
            r5.setAccessible(r0)     // Catch: Throwable -> 0x0074
            java.lang.reflect.Field r5 = r2.columnField     // Catch: Throwable -> 0x0074
            r5.set(r3, r4)     // Catch: Throwable -> 0x0074
            return
        L_0x0074:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()
            com.lidroid.xutils.util.LogUtils.m19219e(r4, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.p058db.table.Finder.setValue2Entity(java.lang.Object, android.database.Cursor, int):void");
    }

    @Override // com.lidroid.xutils.p058db.table.Column
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
