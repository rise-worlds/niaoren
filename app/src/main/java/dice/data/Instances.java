package dice.data;

import java.io.Serializable;
import java.util.Iterator;

/* compiled from: td */
/* loaded from: classes2.dex */
public interface Instances extends Serializable {
    Instance get(int i);

    int getAttrSize();

    int[] getAttributes();

    int[][] getIds();

    double[][] getMat();

    String getRelation();

    boolean isSparse();

    Iterator iterator();

    void setData(int[][] iArr, double[][] dArr);

    void setMiss(double d);

    int size();
}
