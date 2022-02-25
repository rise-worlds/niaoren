package com.lidroid.xutils.p058db.table;

import android.text.TextUtils;
import com.lidroid.xutils.DbUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.lidroid.xutils.db.table.Table */
/* loaded from: classes.dex */
public class Table {
    private static final HashMap<String, Table> tableMap = new HashMap<>();
    private boolean checkedDatabase;
    public final HashMap<String, Column> columnMap;

    /* renamed from: db */
    public final DbUtils f10234db;
    public final HashMap<String, Finder> finderMap = new HashMap<>();

    /* renamed from: id */
    public final C1665Id f10235id;
    public final String tableName;

    private Table(DbUtils dbUtils, Class<?> cls) {
        this.f10234db = dbUtils;
        this.tableName = TableUtils.getTableName(cls);
        this.f10235id = TableUtils.getId(cls);
        this.columnMap = TableUtils.getColumnMap(cls);
        for (Column column : this.columnMap.values()) {
            column.setTable(this);
            if (column instanceof Finder) {
                this.finderMap.put(column.getColumnName(), (Finder) column);
            }
        }
    }

    public static synchronized Table get(DbUtils dbUtils, Class<?> cls) {
        Table table;
        synchronized (Table.class) {
            String str = String.valueOf(dbUtils.getDaoConfig().getDbName()) + "#" + cls.getName();
            table = tableMap.get(str);
            if (table == null) {
                table = new Table(dbUtils, cls);
                tableMap.put(str, table);
            }
        }
        return table;
    }

    public static synchronized void remove(DbUtils dbUtils, Class<?> cls) {
        synchronized (Table.class) {
            tableMap.remove(String.valueOf(dbUtils.getDaoConfig().getDbName()) + "#" + cls.getName());
        }
    }

    public static synchronized void remove(DbUtils dbUtils, String str) {
        synchronized (Table.class) {
            if (tableMap.size() > 0) {
                String str2 = null;
                for (Map.Entry<String, Table> entry : tableMap.entrySet()) {
                    Table value = entry.getValue();
                    if (value != null && value.tableName.equals(str)) {
                        str2 = entry.getKey();
                        if (str2.startsWith(String.valueOf(dbUtils.getDaoConfig().getDbName()) + "#")) {
                            break;
                        }
                    }
                }
                if (TextUtils.isEmpty(str2)) {
                    tableMap.remove(str2);
                }
            }
        }
    }

    public boolean isCheckedDatabase() {
        return this.checkedDatabase;
    }

    public void setCheckedDatabase(boolean z) {
        this.checkedDatabase = z;
    }
}
