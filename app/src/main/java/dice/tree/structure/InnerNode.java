package dice.tree.structure;

import dice.util.BiArrays;
import java.io.PrintStream;
import java.util.Arrays;

/* compiled from: td */
/* loaded from: classes2.dex */
public class InnerNode implements Node {
    private static final long serialVersionUID = -1715121901868539138L;
    public int attr = -1;
    public Node[] children;
    private int[] childrenIndex;
    public double split;

    @Override // dice.tree.structure.Node
    public void clear() {
        try {
            if (this.childrenIndex != null) {
                int[] iArr = this.childrenIndex;
                int length = iArr.length;
                int i = 0;
                for (int i2 = 0; i2 < length && iArr[i2] == -1; i2++) {
                    i++;
                }
                int[] iArr2 = new int[this.childrenIndex.length - i];
                System.arraycopy(this.childrenIndex, i, iArr2, 0, this.childrenIndex.length - i);
                this.childrenIndex = iArr2;
                Node[] nodeArr = new Node[this.children.length - i];
                System.arraycopy(this.children, i, nodeArr, 0, this.children.length - i);
                this.children = nodeArr;
            }
        } catch (Throwable unused) {
        }
    }

    public void addChildren(int i) {
        int i2 = 3;
        if (i >= 3) {
            try {
                i2 = 3 + (i / 10);
            } catch (Throwable unused) {
                return;
            }
        }
        this.children = new Node[i2];
        this.childrenIndex = new int[i2];
        for (int i3 = 0; i3 < this.childrenIndex.length; i3++) {
            this.childrenIndex[i3] = -1;
        }
    }

    public void addChild(int i, Node node) {
        try {
            if (this.childrenIndex[0] > -1) {
                int length = ((int) (this.childrenIndex.length / 0.75d)) + 1;
                int[] iArr = new int[length];
                for (int i2 = 0; i2 < length - this.childrenIndex.length; i2++) {
                    iArr[i2] = -1;
                }
                System.arraycopy(this.childrenIndex, 0, iArr, length - this.childrenIndex.length, this.childrenIndex.length);
                this.childrenIndex = iArr;
                Node[] nodeArr = new Node[length];
                System.arraycopy(this.children, 0, nodeArr, length - this.children.length, this.children.length);
                this.children = nodeArr;
            }
            this.childrenIndex[0] = i;
            this.children[0] = node;
        } catch (Throwable unused) {
        }
        try {
            BiArrays.sort(this.childrenIndex, (Object[]) this.children);
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println(this.childrenIndex.length + ":" + this.children.length);
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Node getChild(int i) {
        int binarySearch = Arrays.binarySearch(this.childrenIndex, i);
        if (binarySearch < 0) {
            return null;
        }
        return this.children[binarySearch];
    }
}
