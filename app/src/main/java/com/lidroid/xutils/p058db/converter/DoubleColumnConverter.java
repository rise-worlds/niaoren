package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;

/* renamed from: com.lidroid.xutils.db.converter.DoubleColumnConverter */
/* loaded from: classes.dex */
public class DoubleColumnConverter implements ColumnConverter<Double> {
    public Object fieldValue2ColumnValue(Double d) {
        return d;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Double getFieldValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Double.valueOf(cursor.getDouble(i));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Double getFieldValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Double.valueOf(str);
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.REAL;
    }
}
