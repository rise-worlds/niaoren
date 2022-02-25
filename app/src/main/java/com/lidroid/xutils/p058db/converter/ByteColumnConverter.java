package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;

/* renamed from: com.lidroid.xutils.db.converter.ByteColumnConverter */
/* loaded from: classes.dex */
public class ByteColumnConverter implements ColumnConverter<Byte> {
    public Object fieldValue2ColumnValue(Byte b) {
        return b;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Byte getFieldValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Byte.valueOf((byte) cursor.getInt(i));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Byte getFieldValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Byte.valueOf(str);
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
