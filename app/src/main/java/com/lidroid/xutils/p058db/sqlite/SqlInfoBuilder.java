package com.lidroid.xutils.p058db.sqlite;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p058db.table.C1665Id;
import com.lidroid.xutils.p058db.table.Column;
import com.lidroid.xutils.p058db.table.ColumnUtils;
import com.lidroid.xutils.p058db.table.Finder;
import com.lidroid.xutils.p058db.table.KeyValue;
import com.lidroid.xutils.p058db.table.Table;
import com.lidroid.xutils.p058db.table.TableUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import p110z1.SimpleComparison;

/* renamed from: com.lidroid.xutils.db.sqlite.SqlInfoBuilder */
/* loaded from: classes.dex */
public class SqlInfoBuilder {
    private SqlInfoBuilder() {
    }

    public static SqlInfo buildInsertSqlInfo(DbUtils dbUtils, Object obj) throws DbException {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("INSERT INTO ");
        stringBuffer.append(TableUtils.getTableName(obj.getClass()));
        stringBuffer.append(" (");
        for (KeyValue keyValue : entity2KeyValueList) {
            stringBuffer.append(keyValue.key);
            stringBuffer.append(",");
            sqlInfo.addBindArgWithoutConverter(keyValue.value);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES (");
        int size = entity2KeyValueList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(")");
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo buildReplaceSqlInfo(DbUtils dbUtils, Object obj) throws DbException {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("REPLACE INTO ");
        stringBuffer.append(TableUtils.getTableName(obj.getClass()));
        stringBuffer.append(" (");
        for (KeyValue keyValue : entity2KeyValueList) {
            stringBuffer.append(keyValue.key);
            stringBuffer.append(",");
            sqlInfo.addBindArgWithoutConverter(keyValue.value);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES (");
        int size = entity2KeyValueList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(")");
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    private static String buildDeleteSqlByTableName(String str) {
        return "DELETE FROM " + str;
    }

    public static SqlInfo buildDeleteSqlInfo(DbUtils dbUtils, Object obj) throws DbException {
        SqlInfo sqlInfo = new SqlInfo();
        Table table = Table.get(dbUtils, obj.getClass());
        C1665Id id = table.f10235id;
        Object columnValue = id.getColumnValue(obj);
        if (columnValue != null) {
            sqlInfo.setSql(buildDeleteSqlByTableName(table.tableName) + " WHERE " + WhereBuilder.m19225b(id.getColumnName(), SimpleComparison.f23609c, columnValue));
            return sqlInfo;
        }
        throw new DbException("this entity[" + obj.getClass() + "]'s id value is null");
    }

    public static SqlInfo buildDeleteSqlInfo(DbUtils dbUtils, Class<?> cls, Object obj) throws DbException {
        SqlInfo sqlInfo = new SqlInfo();
        Table table = Table.get(dbUtils, cls);
        C1665Id id = table.f10235id;
        if (obj != null) {
            sqlInfo.setSql(buildDeleteSqlByTableName(table.tableName) + " WHERE " + WhereBuilder.m19225b(id.getColumnName(), SimpleComparison.f23609c, obj));
            return sqlInfo;
        }
        throw new DbException("this entity[" + cls + "]'s id value is null");
    }

    public static SqlInfo buildDeleteSqlInfo(DbUtils dbUtils, Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        StringBuilder sb = new StringBuilder(buildDeleteSqlByTableName(Table.get(dbUtils, cls).tableName));
        if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        return new SqlInfo(sb.toString());
    }

    public static SqlInfo buildUpdateSqlInfo(DbUtils dbUtils, Object obj, String... strArr) throws DbException {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        HashSet hashSet = null;
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        if (strArr != null && strArr.length > 0) {
            hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
        }
        Table table = Table.get(dbUtils, obj.getClass());
        C1665Id id = table.f10235id;
        Object columnValue = id.getColumnValue(obj);
        if (columnValue != null) {
            SqlInfo sqlInfo = new SqlInfo();
            StringBuffer stringBuffer = new StringBuffer("UPDATE ");
            stringBuffer.append(table.tableName);
            stringBuffer.append(" SET ");
            for (KeyValue keyValue : entity2KeyValueList) {
                if (hashSet == null || hashSet.contains(keyValue.key)) {
                    stringBuffer.append(keyValue.key);
                    stringBuffer.append("=?,");
                    sqlInfo.addBindArgWithoutConverter(keyValue.value);
                }
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(" WHERE ");
            stringBuffer.append(WhereBuilder.m19225b(id.getColumnName(), SimpleComparison.f23609c, columnValue));
            sqlInfo.setSql(stringBuffer.toString());
            return sqlInfo;
        }
        throw new DbException("this entity[" + obj.getClass() + "]'s id value is null");
    }

    public static SqlInfo buildUpdateSqlInfo(DbUtils dbUtils, Object obj, WhereBuilder whereBuilder, String... strArr) throws DbException {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        HashSet hashSet = null;
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        if (strArr != null && strArr.length > 0) {
            hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
        }
        String tableName = TableUtils.getTableName(obj.getClass());
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer("UPDATE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" SET ");
        for (KeyValue keyValue : entity2KeyValueList) {
            if (hashSet == null || hashSet.contains(keyValue.key)) {
                stringBuffer.append(keyValue.key);
                stringBuffer.append("=?,");
                sqlInfo.addBindArgWithoutConverter(keyValue.value);
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
            stringBuffer.append(" WHERE ");
            stringBuffer.append(whereBuilder.toString());
        }
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo buildCreateTableSqlInfo(DbUtils dbUtils, Class<?> cls) throws DbException {
        Table table = Table.get(dbUtils, cls);
        C1665Id id = table.f10235id;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
        stringBuffer.append(table.tableName);
        stringBuffer.append(" ( ");
        if (id.isAutoIncrement()) {
            stringBuffer.append("\"");
            stringBuffer.append(id.getColumnName());
            stringBuffer.append("\"  INTEGER PRIMARY KEY AUTOINCREMENT,");
        } else {
            stringBuffer.append("\"");
            stringBuffer.append(id.getColumnName());
            stringBuffer.append("\"  ");
            stringBuffer.append(id.getColumnDbType());
            stringBuffer.append(" PRIMARY KEY,");
        }
        for (Column column : table.columnMap.values()) {
            if (!(column instanceof Finder)) {
                stringBuffer.append("\"");
                stringBuffer.append(column.getColumnName());
                stringBuffer.append("\"  ");
                stringBuffer.append(column.getColumnDbType());
                if (ColumnUtils.isUnique(column.getColumnField())) {
                    stringBuffer.append(" UNIQUE");
                }
                if (ColumnUtils.isNotNull(column.getColumnField())) {
                    stringBuffer.append(" NOT NULL");
                }
                String check = ColumnUtils.getCheck(column.getColumnField());
                if (check != null) {
                    stringBuffer.append(" CHECK(");
                    stringBuffer.append(check);
                    stringBuffer.append(")");
                }
                stringBuffer.append(",");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(" )");
        return new SqlInfo(stringBuffer.toString());
    }

    private static KeyValue column2KeyValue(Object obj, Column column) {
        String columnName = column.getColumnName();
        if (columnName == null) {
            return null;
        }
        Object columnValue = column.getColumnValue(obj);
        if (columnValue == null) {
            columnValue = column.getDefaultValue();
        }
        return new KeyValue(columnName, columnValue);
    }

    public static List<KeyValue> entity2KeyValueList(DbUtils dbUtils, Object obj) {
        KeyValue column2KeyValue;
        ArrayList arrayList = new ArrayList();
        Table table = Table.get(dbUtils, obj.getClass());
        C1665Id id = table.f10235id;
        if (!id.isAutoIncrement()) {
            arrayList.add(new KeyValue(id.getColumnName(), id.getColumnValue(obj)));
        }
        for (Column column : table.columnMap.values()) {
            if (!(column instanceof Finder) && (column2KeyValue = column2KeyValue(obj, column)) != null) {
                arrayList.add(column2KeyValue);
            }
        }
        return arrayList;
    }
}
