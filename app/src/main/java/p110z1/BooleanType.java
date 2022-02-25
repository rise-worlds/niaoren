package p110z1;

/* renamed from: z1.tc */
/* loaded from: classes3.dex */
public class BooleanType extends BooleanObjectType {

    /* renamed from: a */
    private static final BooleanType f23393a = new BooleanType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: b */
    public static BooleanType m663b() {
        return f23393a;
    }

    private BooleanType() {
        super(SqlType.BOOLEAN, new Class[]{Boolean.TYPE});
    }

    protected BooleanType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
