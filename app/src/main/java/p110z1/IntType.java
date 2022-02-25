package p110z1;

/* renamed from: z1.ts */
/* loaded from: classes3.dex */
public class IntType extends IntegerObjectType {

    /* renamed from: a */
    private static final IntType f23415a = new IntType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: a */
    public static IntType m644a() {
        return f23415a;
    }

    private IntType() {
        super(SqlType.INTEGER, new Class[]{Integer.TYPE});
    }

    protected IntType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
