package com.lidroid.xutils.p058db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;

/* renamed from: com.lidroid.xutils.db.converter.CharColumnConverter */
/* loaded from: classes.dex */
public class CharColumnConverter implements ColumnConverter<Character> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Character getFieldValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Character.valueOf((char) cursor.getInt(i));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public Character getFieldValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public Object fieldValue2ColumnValue(Character ch) {
        if (ch == null) {
            return null;
        }
        return Integer.valueOf(ch.charValue());
    }

    @Override // com.lidroid.xutils.p058db.converter.ColumnConverter
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
