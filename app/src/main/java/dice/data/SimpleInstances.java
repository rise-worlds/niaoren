package dice.data;

import java.util.Iterator;

/* compiled from: td */
/* loaded from: classes2.dex */
public class SimpleInstances implements Instances {
    private static final long serialVersionUID = 8836910060167142716L;
    int[] attributes;
    int cursor;
    int[][] ids;
    Instance instance;
    boolean isSparse;
    Iterator iterator;
    double[][] mat;
    double miss;
    String relation;
    Instances thiz = this;

    public SimpleInstances(int[] iArr, double[][] dArr, int[][] iArr2, String str) {
        if (iArr2 == null) {
            this.isSparse = false;
        } else {
            this.isSparse = true;
        }
        this.attributes = iArr;
        this.ids = iArr2;
        this.mat = dArr;
        this.relation = str;
        this.cursor = -1;
        this.instance = new Instance() { // from class: dice.data.SimpleInstances.1
            @Override // dice.data.Instance
            public void setIndexs(int[] iArr3) {
            }

            @Override // dice.data.Instance
            public double getValue(int i) {
                return SimpleInstances.this.mat[SimpleInstances.this.cursor][i];
            }

            @Override // dice.data.Instance
            public double[] getValues() {
                return SimpleInstances.this.mat[SimpleInstances.this.cursor];
            }

            @Override // dice.data.Instance
            public void setValue(int i, double d) {
                SimpleInstances.this.mat[SimpleInstances.this.cursor][i] = d;
            }

            @Override // dice.data.Instance
            public void setValues(double[] dArr2) {
                SimpleInstances.this.mat[SimpleInstances.this.cursor] = dArr2;
            }

            @Override // dice.data.Instance
            public int getIndex() {
                return SimpleInstances.this.cursor;
            }

            @Override // dice.data.Instance
            public int[] getIndexs() {
                return SimpleInstances.this.ids[SimpleInstances.this.cursor];
            }

            @Override // dice.data.Instance
            public Instances getInstances() {
                return SimpleInstances.this.thiz;
            }
        };
        this.iterator = new Iterator() { // from class: dice.data.SimpleInstances.2
            @Override // java.util.Iterator
            public void remove() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return SimpleInstances.this.cursor < SimpleInstances.this.mat.length - 1;
            }

            @Override // java.util.Iterator
            public Instance next() {
                SimpleInstances.this.cursor++;
                return SimpleInstances.this.instance;
            }
        };
    }

    @Override // dice.data.Instances
    public Iterator iterator() {
        this.cursor = -1;
        return this.iterator;
    }

    @Override // dice.data.Instances
    public int[] getAttributes() {
        return this.attributes;
    }

    @Override // dice.data.Instances
    public String getRelation() {
        return this.relation;
    }

    @Override // dice.data.Instances
    public int getAttrSize() {
        return this.attributes.length;
    }

    @Override // dice.data.Instances
    public int size() {
        return this.mat.length;
    }

    @Override // dice.data.Instances
    public Instance get(int i) {
        this.cursor = i;
        return this.instance;
    }

    @Override // dice.data.Instances
    public boolean isSparse() {
        return this.isSparse;
    }

    @Override // dice.data.Instances
    public int[][] getIds() {
        return this.ids;
    }

    @Override // dice.data.Instances
    public double[][] getMat() {
        return this.mat;
    }

    @Override // dice.data.Instances
    public void setMiss(double d) {
        this.miss = d;
    }

    @Override // dice.data.Instances
    public void setData(int[][] iArr, double[][] dArr) {
        this.ids = iArr;
        this.mat = dArr;
    }
}
