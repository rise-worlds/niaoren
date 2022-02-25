package com.lidroid.xutils.p058db.table;

import android.text.TextUtils;
import com.lidroid.xutils.p058db.annotation.AbstractC1664Id;
import com.lidroid.xutils.p058db.annotation.Table;
import com.lidroid.xutils.p058db.converter.ColumnConverterFactory;
import com.lidroid.xutils.util.LogUtils;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: com.lidroid.xutils.db.table.TableUtils */
/* loaded from: classes.dex */
public class TableUtils {
    private static ConcurrentHashMap<String, HashMap<String, Column>> entityColumnsMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, C1665Id> entityIdMap = new ConcurrentHashMap<>();

    private TableUtils() {
    }

    public static String getTableName(Class<?> cls) {
        Table table = (Table) cls.getAnnotation(Table.class);
        if (table == null || TextUtils.isEmpty(table.name())) {
            return cls.getName().replace(FilenameUtils.EXTENSION_SEPARATOR, '_');
        }
        return table.name();
    }

    public static String getExecAfterTableCreated(Class<?> cls) {
        Table table = (Table) cls.getAnnotation(Table.class);
        if (table != null) {
            return table.execAfterTableCreated();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized HashMap<String, Column> getColumnMap(Class<?> cls) {
        synchronized (TableUtils.class) {
            if (entityColumnsMap.containsKey(cls.getName())) {
                return entityColumnsMap.get(cls.getName());
            }
            HashMap<String, Column> hashMap = new HashMap<>();
            addColumns2Map(cls, getPrimaryKeyFieldName(cls), hashMap);
            entityColumnsMap.put(cls.getName(), hashMap);
            return hashMap;
        }
    }

    private static void addColumns2Map(Class<?> cls, String str, HashMap<String, Column> hashMap) {
        Field[] declaredFields;
        if (!Object.class.equals(cls)) {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    if (!ColumnUtils.isTransient(field) && !Modifier.isStatic(field.getModifiers())) {
                        if (ColumnConverterFactory.isSupportColumnConverter(field.getType())) {
                            if (!field.getName().equals(str)) {
                                Column column = new Column(cls, field);
                                if (!hashMap.containsKey(column.getColumnName())) {
                                    hashMap.put(column.getColumnName(), column);
                                }
                            }
                        } else if (ColumnUtils.isForeign(field)) {
                            Foreign foreign = new Foreign(cls, field);
                            if (!hashMap.containsKey(foreign.getColumnName())) {
                                hashMap.put(foreign.getColumnName(), foreign);
                            }
                        } else if (ColumnUtils.isFinder(field)) {
                            Finder finder = new Finder(cls, field);
                            if (!hashMap.containsKey(finder.getColumnName())) {
                                hashMap.put(finder.getColumnName(), finder);
                            }
                        }
                    }
                }
                if (!Object.class.equals(cls.getSuperclass())) {
                    addColumns2Map(cls.getSuperclass(), str, hashMap);
                }
            } catch (Throwable th) {
                LogUtils.m19219e(th.getMessage(), th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Column getColumnOrId(Class<?> cls, String str) {
        if (getPrimaryKeyColumnName(cls).equals(str)) {
            return getId(cls);
        }
        return getColumnMap(cls).get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized C1665Id getId(Class<?> cls) {
        synchronized (TableUtils.class) {
            if (Object.class.equals(cls)) {
                throw new RuntimeException("field 'id' not found");
            } else if (entityIdMap.containsKey(cls.getName())) {
                return entityIdMap.get(cls.getName());
            } else {
                Field field = null;
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields != null) {
                    int length = declaredFields.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Field field2 = declaredFields[i];
                        if (field2.getAnnotation(AbstractC1664Id.class) != null) {
                            field = field2;
                            break;
                        }
                        i++;
                    }
                    if (field == null) {
                        for (Field field3 : declaredFields) {
                            if (!ConnectionModel.f10389a.equals(field3.getName()) && !"_id".equals(field3.getName())) {
                            }
                            field = field3;
                            break;
                        }
                    }
                }
                if (field == null) {
                    return getId(cls.getSuperclass());
                }
                C1665Id id = new C1665Id(cls, field);
                entityIdMap.put(cls.getName(), id);
                return id;
            }
        }
    }

    private static String getPrimaryKeyFieldName(Class<?> cls) {
        C1665Id id = getId(cls);
        if (id == null) {
            return null;
        }
        return id.getColumnField().getName();
    }

    private static String getPrimaryKeyColumnName(Class<?> cls) {
        C1665Id id = getId(cls);
        if (id == null) {
            return null;
        }
        return id.getColumnName();
    }
}
