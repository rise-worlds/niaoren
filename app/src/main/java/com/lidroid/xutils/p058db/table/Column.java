package com.lidroid.xutils.p058db.table;

import android.database.Cursor;
import com.lidroid.xutils.p058db.converter.ColumnConverter;
import com.lidroid.xutils.p058db.converter.ColumnConverterFactory;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* renamed from: com.lidroid.xutils.db.table.Column */
/* loaded from: classes.dex */
public class Column {
    protected final ColumnConverter columnConverter;
    protected final Field columnField;
    protected final String columnName;
    private final Object defaultValue;
    protected final Method getMethod;
    private int index = -1;
    protected final Method setMethod;
    private Table table;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Column(Class<?> cls, Field field) {
        this.columnField = field;
        this.columnConverter = ColumnConverterFactory.getColumnConverter(field.getType());
        this.columnName = ColumnUtils.getColumnNameByField(field);
        ColumnConverter columnConverter = this.columnConverter;
        if (columnConverter != null) {
            this.defaultValue = columnConverter.getFieldValue(ColumnUtils.getColumnDefaultValue(field));
        } else {
            this.defaultValue = null;
        }
        this.getMethod = ColumnUtils.getColumnGetMethod(cls, field);
        this.setMethod = ColumnUtils.getColumnSetMethod(cls, field);
    }

    public void setValue2Entity(Object obj, Cursor cursor, int i) {
        this.index = i;
        Object fieldValue = this.columnConverter.getFieldValue(cursor, i);
        if (fieldValue != null || this.defaultValue != null) {
            Method method = this.setMethod;
            if (method != null) {
                try {
                    Object[] objArr = new Object[1];
                    if (fieldValue == null) {
                        fieldValue = this.defaultValue;
                    }
                    objArr[0] = fieldValue;
                    method.invoke(obj, objArr);
                } catch (Throwable th) {
                    LogUtils.m19219e(th.getMessage(), th);
                }
            } else {
                try {
                    this.columnField.setAccessible(true);
                    Field field = this.columnField;
                    if (fieldValue == null) {
                        fieldValue = this.defaultValue;
                    }
                    field.set(obj, fieldValue);
                } catch (Throwable th2) {
                    LogUtils.m19219e(th2.getMessage(), th2);
                }
            }
        }
    }

    public Object getColumnValue(Object obj) {
        return this.columnConverter.fieldValue2ColumnValue(getFieldValue(obj));
    }

    public Object getFieldValue(Object obj) {
        if (obj != null) {
            Method method = this.getMethod;
            if (method != null) {
                try {
                    return method.invoke(obj, new Object[0]);
                } catch (Throwable th) {
                    LogUtils.m19219e(th.getMessage(), th);
                }
            } else {
                try {
                    this.columnField.setAccessible(true);
                    return this.columnField.get(obj);
                } catch (Throwable th2) {
                    LogUtils.m19219e(th2.getMessage(), th2);
                }
            }
        }
        return null;
    }

    public Table getTable() {
        return this.table;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTable(Table table) {
        this.table = table;
    }

    public int getIndex() {
        return this.index;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }

    public Field getColumnField() {
        return this.columnField;
    }

    public ColumnConverter getColumnConverter() {
        return this.columnConverter;
    }

    public ColumnDbType getColumnDbType() {
        return this.columnConverter.getColumnDbType();
    }
}
