package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;

/* renamed from: com.lidroid.xutils.db.converter.ColumnConverter */
/* loaded from: classes.dex */
public interface ColumnConverter<T> {
    Object fieldValue2ColumnValue(T t);

    ColumnDbType getColumnDbType();

    T getFieldValue(Cursor cursor, int i);

    T getFieldValue(String str);
}
