package dice.data;

/* compiled from: td */
/* loaded from: classes2.dex */
public interface Instance {
    int getIndex();

    int[] getIndexs();

    Instances getInstances();

    double getValue(int i);

    double[] getValues();

    void setIndexs(int[] iArr);

    void setValue(int i, double d);

    void setValues(double[] dArr);
}
