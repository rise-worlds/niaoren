package dice.tree.structure;

import dice.util.BiArrays;
import java.util.Arrays;

/* compiled from: td */
/* loaded from: classes2.dex */
public class Leaf implements Node {
    private static final long serialVersionUID = -4275978923452814810L;
    public double[] dist;
    public int[] distIndex;
    public int size;

    /* renamed from: v */
    public double f14644v;

    @Override // dice.tree.structure.Node
    public void clear() {
        int[] iArr = this.distIndex;
        if (iArr != null) {
            int length = iArr.length;
            int i = 0;
            for (int i2 = 0; i2 < length && iArr[i2] == -1; i2++) {
                i++;
            }
            int[] iArr2 = this.distIndex;
            int[] iArr3 = new int[iArr2.length - i];
            System.arraycopy(iArr2, i, iArr3, 0, iArr2.length - i);
            this.distIndex = iArr3;
            double[] dArr = this.dist;
            double[] dArr2 = new double[dArr.length - i];
            System.arraycopy(dArr, i, dArr2, 0, dArr.length - i);
            this.dist = dArr2;
        }
    }

    public void addDists(int i) {
        int i2 = 3;
        if (i >= 3) {
            i2 = 3 + (i / 10);
        }
        this.dist = new double[i2];
        this.distIndex = new int[i2];
        int i3 = 0;
        while (true) {
            int[] iArr = this.distIndex;
            if (i3 < iArr.length) {
                iArr[i3] = -1;
                i3++;
            } else {
                return;
            }
        }
    }

    private void addDist(int i) {
        int[] iArr;
        if (Arrays.binarySearch(this.distIndex, i) <= -1) {
            int[] iArr2 = this.distIndex;
            if (iArr2[0] > -1) {
                int length = ((int) (iArr2.length / 0.75d)) + 1;
                int[] iArr3 = new int[length];
                int i2 = 0;
                while (true) {
                    iArr = this.distIndex;
                    if (i2 >= length - iArr.length) {
                        break;
                    }
                    iArr3[i2] = -1;
                    i2++;
                }
                System.arraycopy(iArr, 0, iArr3, length - iArr.length, iArr.length);
                this.distIndex = iArr3;
                double[] dArr = new double[length];
                double[] dArr2 = this.dist;
                System.arraycopy(dArr2, 0, dArr, length - dArr2.length, dArr2.length);
                this.dist = dArr;
            }
            int[] iArr4 = this.distIndex;
            iArr4[0] = i;
            BiArrays.sort(iArr4, this.dist);
        }
    }

    public void incDist(int i) {
        int binarySearch = Arrays.binarySearch(this.distIndex, i);
        if (binarySearch > 0) {
            double[] dArr = this.dist;
            dArr[binarySearch] = dArr[binarySearch] + 1.0d;
            return;
        }
        addDist(i);
        double[] dArr2 = this.dist;
        int binarySearch2 = Arrays.binarySearch(this.distIndex, i);
        dArr2[binarySearch2] = dArr2[binarySearch2] + 1.0d;
    }

    public double getDist(int i) {
        int binarySearch = Arrays.binarySearch(this.distIndex, i);
        if (binarySearch < 0) {
            return 0.0d;
        }
        return this.dist[binarySearch];
    }

    public void addValue(double d) {
        this.f14644v += d;
    }

    public double getValue() {
        int i = this.size;
        if (i > 0) {
            return this.f14644v / i;
        }
        return 0.0d;
    }
}
