package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;
import java.util.Date;

/* renamed from: com.lidroid.xutils.db.converter.DateColumnConverter */
/* loaded from: classes.dex */
public class DateColumnConverter implements ColumnConverter<Date> {
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Date getFieldValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return new Date(cursor.getLong(i));
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Date getFieldValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new Date(Long.valueOf(str).longValue());
    }

    public Object fieldValue2ColumnValue(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
