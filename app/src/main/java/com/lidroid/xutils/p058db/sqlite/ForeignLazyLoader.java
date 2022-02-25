package com.lidroid.xutils.p058db.sqlite;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p058db.table.ColumnUtils;
import com.lidroid.xutils.p058db.table.Foreign;
import com.lidroid.xutils.p058db.table.Table;
import java.util.List;
import p110z1.SimpleComparison;

/* renamed from: com.lidroid.xutils.db.sqlite.ForeignLazyLoader */
/* loaded from: classes.dex */
public class ForeignLazyLoader<T> {
    private Object columnValue;
    private final Foreign foreignColumn;

    public ForeignLazyLoader(Foreign foreign, Object obj) {
        this.foreignColumn = foreign;
        this.columnValue = ColumnUtils.convert2DbColumnValueIfNeeded(obj);
    }

    public List<T> getAllFromDb() throws DbException {
        Table table = this.foreignColumn.getTable();
        if (table != null) {
            return table.f10234db.findAll(Selector.from(this.foreignColumn.getForeignEntityType()).where(this.foreignColumn.getForeignColumnName(), SimpleComparison.f23609c, this.columnValue));
        }
        return null;
    }

    public T getFirstFromDb() throws DbException {
        Table table = this.foreignColumn.getTable();
        if (table != null) {
            return (T) table.f10234db.findFirst(Selector.from(this.foreignColumn.getForeignEntityType()).where(this.foreignColumn.getForeignColumnName(), SimpleComparison.f23609c, this.columnValue));
        }
        return null;
    }

    public void setColumnValue(Object obj) {
        this.columnValue = ColumnUtils.convert2DbColumnValueIfNeeded(obj);
    }

    public Object getColumnValue() {
        return this.columnValue;
    }
}
