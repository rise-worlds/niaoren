package com.lidroid.xutils.p058db.sqlite;

/* renamed from: com.lidroid.xutils.db.sqlite.ColumnDbType */
/* loaded from: classes.dex */
public enum ColumnDbType {
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
    BLOB("BLOB");
    
    private String value;

    ColumnDbType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.value;
    }
}
