package p110z1;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: z1.sw */
/* loaded from: classes3.dex */
public abstract class BaseDateType extends BaseDataType {

    /* renamed from: a */
    protected static final C5564a f23372a = new C5564a("yyyy-MM-dd HH:mm:ss.SSSSSS");

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForVersion() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDateType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static C5564a m679a(FieldType ssVar, C5564a aVar) {
        C5564a aVar2;
        return (ssVar == null || (aVar2 = (C5564a) ssVar.m711g()) == null) ? aVar : aVar2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static Date m678a(C5564a aVar, String str) throws ParseException {
        return aVar.m676a().parse(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static String m677b(C5564a aVar, String str) throws ParseException {
        DateFormat a = aVar.m676a();
        return a.format(a.parse(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: BaseDateType.java */
    /* renamed from: z1.sw$a */
    /* loaded from: classes3.dex */
    public static class C5564a {

        /* renamed from: a */
        final String f23373a;

        /* renamed from: b */
        private final ThreadLocal<DateFormat> f23374b = new ThreadLocal<DateFormat>() { // from class: z1.sw.a.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* renamed from: a */
            public DateFormat initialValue() {
                return new SimpleDateFormat(C5564a.this.f23373a);
            }
        };

        public C5564a(String str) {
            this.f23373a = str;
        }

        /* renamed from: a */
        public DateFormat m676a() {
            return this.f23374b.get();
        }

        public String toString() {
            return this.f23373a;
        }
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object moveToNextValue(Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        if (obj == null) {
            return new Date(currentTimeMillis);
        }
        if (currentTimeMillis == ((Date) obj).getTime()) {
            return new Date(currentTimeMillis + 1);
        }
        return new Date(currentTimeMillis);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForField(Field field) {
        return field.getType() == Date.class;
    }
}
