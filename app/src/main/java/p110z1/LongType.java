package p110z1;

/* renamed from: z1.tw */
/* loaded from: classes3.dex */
public class LongType extends LongObjectType {

    /* renamed from: a */
    private static final LongType f23419a = new LongType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: b */
    public static LongType m640b() {
        return f23419a;
    }

    private LongType() {
        super(SqlType.LONG, new Class[]{Long.TYPE});
    }

    protected LongType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
