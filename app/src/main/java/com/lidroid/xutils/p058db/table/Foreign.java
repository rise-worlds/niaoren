package com.lidroid.xutils.p058db.table;

import android.database.Cursor;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p058db.converter.ColumnConverter;
import com.lidroid.xutils.p058db.converter.ColumnConverterFactory;
import com.lidroid.xutils.p058db.sqlite.ColumnDbType;
import com.lidroid.xutils.p058db.sqlite.ForeignLazyLoader;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.List;

/* renamed from: com.lidroid.xutils.db.table.Foreign */
/* loaded from: classes.dex */
public class Foreign extends Column {
    private final ColumnConverter foreignColumnConverter;
    private final String foreignColumnName;

    @Override // com.lidroid.xutils.p058db.table.Column
    public Object getDefaultValue() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Foreign(Class<?> cls, Field field) {
        super(cls, field);
        this.foreignColumnName = ColumnUtils.getForeignColumnNameByField(field);
        this.foreignColumnConverter = ColumnConverterFactory.getColumnConverter(TableUtils.getColumnOrId(getForeignEntityType(), this.foreignColumnName).columnField.getType());
    }

    public String getForeignColumnName() {
        return this.foreignColumnName;
    }

    public Class<?> getForeignEntityType() {
        return ColumnUtils.getForeignEntityType(this);
    }

    @Override // com.lidroid.xutils.p058db.table.Column
    public void setValue2Entity(Object obj, Cursor cursor, int i) {
        Object fieldValue = this.foreignColumnConverter.getFieldValue(cursor, i);
        if (fieldValue != null) {
            Object obj2 = null;
            Class<?> type = this.columnField.getType();
            if (type.equals(ForeignLazyLoader.class)) {
                obj2 = new ForeignLazyLoader(this, fieldValue);
            } else if (type.equals(List.class)) {
                try {
                    obj2 = new ForeignLazyLoader(this, fieldValue).getAllFromDb();
                } catch (DbException e) {
                    LogUtils.m19219e(e.getMessage(), e);
                }
            } else {
                try {
                    obj2 = new ForeignLazyLoader(this, fieldValue).getFirstFromDb();
                } catch (DbException e2) {
                    LogUtils.m19219e(e2.getMessage(), e2);
                }
            }
            if (this.setMethod != null) {
                try {
                    this.setMethod.invoke(obj, obj2);
                } catch (Throwable th) {
                    LogUtils.m19219e(th.getMessage(), th);
                }
            } else {
                try {
                    this.columnField.setAccessible(true);
                    this.columnField.set(obj, obj2);
                } catch (Throwable th2) {
                    LogUtils.m19219e(th2.getMessage(), th2);
                }
            }
        }
    }

    @Override // com.lidroid.xutils.p058db.table.Column
    public Object getColumnValue(Object obj) {
        Object fieldValue = getFieldValue(obj);
        if (fieldValue == null) {
            return null;
        }
        Class<?> type = this.columnField.getType();
        if (type.equals(ForeignLazyLoader.class)) {
            return ((ForeignLazyLoader) fieldValue).getColumnValue();
        }
        if (type.equals(List.class)) {
            try {
                List list = (List) fieldValue;
                if (list.size() <= 0) {
                    return null;
                }
                Column columnOrId = TableUtils.getColumnOrId(ColumnUtils.getForeignEntityType(this), this.foreignColumnName);
                columnOrId.getColumnValue(list.get(0));
                Table table = getTable();
                if (table != null && (columnOrId instanceof C1665Id)) {
                    for (Object obj2 : list) {
                        if (columnOrId.getColumnValue(obj2) == null) {
                            table.f10234db.saveOrUpdate(obj2);
                        }
                    }
                }
                return columnOrId.getColumnValue(list.get(0));
            } catch (Throwable th) {
                LogUtils.m19219e(th.getMessage(), th);
                return null;
            }
        } else {
            try {
                Column columnOrId2 = TableUtils.getColumnOrId(type, this.foreignColumnName);
                Object columnValue = columnOrId2.getColumnValue(fieldValue);
                Table table2 = getTable();
                if (table2 != null && columnValue == null && (columnOrId2 instanceof C1665Id)) {
                    table2.f10234db.saveOrUpdate(fieldValue);
                }
                return columnOrId2.getColumnValue(fieldValue);
            } catch (Throwable th2) {
                LogUtils.m19219e(th2.getMessage(), th2);
                return null;
            }
        }
    }

    @Override // com.lidroid.xutils.p058db.table.Column
    public ColumnDbType getColumnDbType() {
        return this.foreignColumnConverter.getColumnDbType();
    }
}
