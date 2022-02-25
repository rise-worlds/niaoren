package p110z1;

/* renamed from: z1.tn */
/* loaded from: classes3.dex */
public class DoubleType extends DoubleObjectType {

    /* renamed from: a */
    private static final DoubleType f23409a = new DoubleType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: b */
    public static DoubleType m649b() {
        return f23409a;
    }

    private DoubleType() {
        super(SqlType.DOUBLE, new Class[]{Double.TYPE});
    }

    protected DoubleType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
