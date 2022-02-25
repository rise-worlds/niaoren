package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;

/* renamed from: com.lidroid.xutils.db.converter.ShortColumnConverter */
/* loaded from: classes.dex */
public class ShortColumnConverter implements ColumnConverter<Short> {
    public Object fieldValue2ColumnValue(Short sh) {
        return sh;
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Short getFieldValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Short.valueOf(cursor.getShort(i));
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Short getFieldValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Short.valueOf(str);
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
