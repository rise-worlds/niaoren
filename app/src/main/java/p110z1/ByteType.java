package p110z1;

/* renamed from: z1.tf */
/* loaded from: classes3.dex */
public class ByteType extends ByteObjectType {

    /* renamed from: a */
    private static final ByteType f23396a = new ByteType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: b */
    public static ByteType m660b() {
        return f23396a;
    }

    private ByteType() {
        super(SqlType.BYTE, new Class[]{Byte.TYPE});
    }

    protected ByteType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
