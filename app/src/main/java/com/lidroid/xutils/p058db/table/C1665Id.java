package com.lidroid.xutils.p058db.table;

import com.lidroid.xutils.p058db.annotation.NoAutoIncrement;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.HashSet;

/* renamed from: com.lidroid.xutils.db.table.Id */
/* loaded from: classes.dex */
public class C1665Id extends Column {
    private static final HashSet<String> INTEGER_TYPES = new HashSet<>(2);
    private static final HashSet<String> AUTO_INCREMENT_TYPES = new HashSet<>(4);
    private boolean isAutoIncrementChecked = false;
    private boolean isAutoIncrement = false;
    private String columnFieldClassName = this.columnField.getType().getName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1665Id(Class<?> cls, Field field) {
        super(cls, field);
    }

    public boolean isAutoIncrement() {
        if (!this.isAutoIncrementChecked) {
            boolean z = true;
            this.isAutoIncrementChecked = true;
            if (this.columnField.getAnnotation(NoAutoIncrement.class) != null || !AUTO_INCREMENT_TYPES.contains(this.columnFieldClassName)) {
                z = false;
            }
            this.isAutoIncrement = z;
        }
        return this.isAutoIncrement;
    }

    public void setAutoIncrementId(Object obj, long j) {
        Object valueOf = Long.valueOf(j);
        if (INTEGER_TYPES.contains(this.columnFieldClassName)) {
            valueOf = Integer.valueOf((int) j);
        }
        if (this.setMethod != null) {
            try {
                this.setMethod.invoke(obj, valueOf);
            } catch (Throwable th) {
                LogUtils.m19219e(th.getMessage(), th);
            }
        } else {
            try {
                this.columnField.setAccessible(true);
                this.columnField.set(obj, valueOf);
            } catch (Throwable th2) {
                LogUtils.m19219e(th2.getMessage(), th2);
            }
        }
    }

    @Override // com.lidroid.xutils.p058db.table.Column
    public Object getColumnValue(Object obj) {
        Object columnValue = super.getColumnValue(obj);
        if (columnValue == null) {
            return null;
        }
        if (!isAutoIncrement() || (!columnValue.equals(0) && !columnValue.equals(0L))) {
            return columnValue;
        }
        return null;
    }

    static {
        INTEGER_TYPES.add(Integer.TYPE.getName());
        INTEGER_TYPES.add(Integer.class.getName());
        AUTO_INCREMENT_TYPES.addAll(INTEGER_TYPES);
        AUTO_INCREMENT_TYPES.add(Long.TYPE.getName());
        AUTO_INCREMENT_TYPES.add(Long.class.getName());
    }
}
