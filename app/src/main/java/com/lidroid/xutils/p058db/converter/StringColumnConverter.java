package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;

/* renamed from: com.lidroid.xutils.db.converter.StringColumnConverter */
/* loaded from: classes.dex */
public class StringColumnConverter implements ColumnConverter<String> {
    public Object fieldValue2ColumnValue(String str) {
        return str;
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public String getFieldValue(String str) {
        return str;
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public String getFieldValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return cursor.getString(i);
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
