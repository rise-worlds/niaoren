package p110z1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: z1.sm */
/* loaded from: classes3.dex */
public class DataPersisterManager {

    /* renamed from: a */
    private static final DataPersister f23245a = EnumStringType.m647a();

    /* renamed from: c */
    private static List<DataPersister> f23247c = null;

    /* renamed from: b */
    private static final Map<String, DataPersister> f23246b = new HashMap();

    static {
        for (DataType snVar : DataType.values()) {
            DataPersister dataPersister = snVar.getDataPersister();
            if (dataPersister != null) {
                for (Class<?> cls : dataPersister.getAssociatedClasses()) {
                    f23246b.put(cls.getName(), dataPersister);
                }
                if (dataPersister.getAssociatedClassNames() != null) {
                    for (String str : dataPersister.getAssociatedClassNames()) {
                        f23246b.put(str, dataPersister);
                    }
                }
            }
        }
    }

    private DataPersisterManager() {
    }

    /* renamed from: a */
    public static void m868a(DataPersister... slVarArr) {
        ArrayList arrayList = new ArrayList();
        List<DataPersister> list = f23247c;
        if (list != null) {
            arrayList.addAll(list);
        }
        for (DataPersister slVar : slVarArr) {
            arrayList.add(slVar);
        }
        f23247c = arrayList;
    }

    /* renamed from: a */
    public static void m870a() {
        f23247c = null;
    }

    /* renamed from: a */
    public static DataPersister m869a(Field field) {
        List<DataPersister> list = f23247c;
        if (list != null) {
            for (DataPersister slVar : list) {
                if (slVar.isValidForField(field)) {
                    return slVar;
                }
                for (Class<?> cls : slVar.getAssociatedClasses()) {
                    if (field.getType() == cls) {
                        return slVar;
                    }
                }
            }
        }
        DataPersister slVar2 = f23246b.get(field.getType().getName());
        if (slVar2 != null) {
            return slVar2;
        }
        if (field.getType().isEnum()) {
            return f23245a;
        }
        return null;
    }
}
