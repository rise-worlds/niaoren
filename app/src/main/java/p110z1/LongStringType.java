package p110z1;

/* renamed from: z1.tv */
/* loaded from: classes3.dex */
public class LongStringType extends StringType {

    /* renamed from: b */
    private static final LongStringType f23418b = new LongStringType();

    @Override // p110z1.StringType, p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return 0;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    /* renamed from: a */
    public static LongStringType m641a() {
        return f23418b;
    }

    private LongStringType() {
        super(SqlType.LONG_STRING, new Class[0]);
    }

    protected LongStringType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return String.class;
    }
}
