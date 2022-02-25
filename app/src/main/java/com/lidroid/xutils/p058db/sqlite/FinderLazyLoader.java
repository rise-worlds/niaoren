package com.lidroid.xutils.p058db.sqlite;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p058db.table.ColumnUtils;
import com.lidroid.xutils.p058db.table.Finder;
import com.lidroid.xutils.p058db.table.Table;
import java.util.List;
import p110z1.SimpleComparison;

/* renamed from: com.lidroid.xutils.db.sqlite.FinderLazyLoader */
/* loaded from: classes.dex */
public class FinderLazyLoader<T> {
    private final Finder finderColumn;
    private final Object finderValue;

    public FinderLazyLoader(Finder finder, Object obj) {
        this.finderColumn = finder;
        this.finderValue = ColumnUtils.convert2DbColumnValueIfNeeded(obj);
    }

    public List<T> getAllFromDb() throws DbException {
        Table table = this.finderColumn.getTable();
        if (table != null) {
            return table.f10234db.findAll(Selector.from(this.finderColumn.getTargetEntityType()).where(this.finderColumn.getTargetColumnName(), SimpleComparison.f23609c, this.finderValue));
        }
        return null;
    }

    public T getFirstFromDb() throws DbException {
        Table table = this.finderColumn.getTable();
        if (table != null) {
            return (T) table.f10234db.findFirst(Selector.from(this.finderColumn.getTargetEntityType()).where(this.finderColumn.getTargetColumnName(), SimpleComparison.f23609c, this.finderValue));
        }
        return null;
    }
}
