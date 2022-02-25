package dice.tree.builder;

import dice.data.Instance;
import dice.data.Instances;
import dice.tree.structure.InnerNode;
import dice.tree.structure.Leaf;
import dice.tree.structure.Node;
import dice.util.BiArrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* compiled from: td */
/* loaded from: classes2.dex */
public class TreeBuilder {
    public static final byte CBR_RDT = 2;
    public static final byte CLASSIFICATION = 0;
    public static final byte REGRESSION = 1;
    private int clsSize = 1;
    private Instances insts;
    private List ions;
    private List level;
    private int maxDeep;
    private int maxS;
    private Map parents;

    /* renamed from: rd */
    private Random f14642rd;
    private byte type;

    public TreeBuilder(long j, byte b) {
        this.f14642rd = new Random(j);
        this.type = b;
    }

    public void setClsSize(int i) {
        this.clsSize = i;
    }

    public Instances getInstances() {
        return this.insts;
    }

    public void setInstances(Instances instances) {
        this.insts = instances;
    }

    public int getMaxDeep() {
        return this.maxDeep;
    }

    public void setMaxDeep(int i) {
        if (i >= (this.insts.getAttrSize() - this.clsSize) + 1) {
            i = (this.insts.getAttrSize() - this.clsSize) + 1;
        }
        this.maxDeep = i;
    }

    public int getMaxS() {
        return this.maxS;
    }

    public void setMaxS(int i) {
        this.maxS = i;
    }

    public int getClsSize() {
        return this.clsSize;
    }

    public void setRandomSeed(long j) {
        this.f14642rd = new Random(j);
    }

    public void init() {
        this.level = new LinkedList();
        this.ions = new LinkedList();
        this.parents = new HashMap();
    }

    public void clear() {
        this.level = null;
        this.ions = null;
        this.parents = null;
    }

    public Node[] buildTrees(int i) {
        Node[] nodeArr = new Node[i];
        for (int i2 = 0; i2 < nodeArr.length; i2++) {
            nodeArr[i2] = build();
        }
        clear();
        return nodeArr;
    }

    private Node build() {
        init();
        try {
            if (this.maxDeep != 1 && this.insts.size() > this.maxS) {
                InnerNode innerNode = new InnerNode();
                try {
                    int[] iArr = new int[this.insts.size()];
                    for (int i = 0; i < this.insts.size(); i++) {
                        iArr[i] = i;
                    }
                    this.level.add(innerNode);
                    this.ions.add(iArr);
                    for (int i2 = 0; i2 < this.maxDeep; i2++) {
                        incLevel(innerNode);
                        if (this.level.isEmpty()) {
                            break;
                        }
                    }
                    Iterator it = this.ions.iterator();
                    for (Node node : this.level) {
                        int[] iArr2 = (int[]) it.next();
                        InnerNode innerNode2 = (InnerNode) this.parents.get(node);
                        int i3 = 0;
                        while (true) {
                            if (i3 >= innerNode2.children.length) {
                                break;
                            } else if (innerNode2.children[i3].equals(node)) {
                                innerNode2.children[i3] = closeNode(node, iArr2);
                                this.parents.remove(node);
                                break;
                            } else {
                                i3++;
                            }
                        }
                    }
                    return innerNode;
                } catch (Throwable unused) {
                    return innerNode;
                }
            }
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private void incLevel(Node node) {
        try {
            int[] attributes = this.insts.getAttributes();
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            Iterator it = this.ions.iterator();
            Iterator it2 = this.level.iterator();
            Leaf leaf = node;
            while (it2.hasNext()) {
                Node node2 = (Node) it2.next();
                InnerNode innerNode = (InnerNode) node2;
                int[] iArr = (int[]) it.next();
                int i = 0;
                if (iArr.length <= this.maxS) {
                    InnerNode innerNode2 = (InnerNode) this.parents.get(node2);
                    while (true) {
                        if (i >= innerNode2.children.length) {
                            break;
                        } else if (innerNode2.children[i].equals(node2)) {
                            innerNode2.children[i] = closeNode(innerNode, iArr);
                            this.parents.remove(node2);
                            break;
                        } else {
                            i++;
                        }
                    }
                    innerNode.clear();
                    it = it;
                    it2 = it2;
                } else {
                    int selectAttr = selectAttr(innerNode, (InnerNode) leaf, this.insts.get(iArr[0]));
                    if (selectAttr == -1) {
                        InnerNode innerNode3 = (InnerNode) this.parents.get(node2);
                        while (true) {
                            if (i >= innerNode3.children.length) {
                                break;
                            } else if (innerNode3.children[i].equals(node2)) {
                                innerNode3.children[i] = closeNode(innerNode, iArr);
                                this.parents.remove(node2);
                                break;
                            } else {
                                i++;
                            }
                        }
                        innerNode.clear();
                        it = it;
                        it2 = it2;
                    } else {
                        innerNode.attr = selectAttr;
                        if (attributes[selectAttr] > 0) {
                            innerNode.addChildren(attributes[selectAttr]);
                            int[] iArr2 = new int[iArr.length];
                            for (int i2 = 0; i2 < iArr.length; i2++) {
                                iArr2[i2] = (int) this.insts.get(iArr[i2]).getValue(selectAttr);
                            }
                            BiArrays.sort(iArr2, iArr);
                            ArrayList<Integer> arrayList = new ArrayList();
                            double d = iArr2[0];
                            int i3 = 0;
                            while (i3 < iArr2.length) {
                                if (iArr2[i3] != d) {
                                    d = iArr2[i3];
                                    arrayList.add(Integer.valueOf(i3));
                                }
                                i3++;
                                it = it;
                                it2 = it2;
                            }
                            it = it;
                            it2 = it2;
                            arrayList.add(Integer.valueOf(iArr2.length));
                            int i4 = 0;
                            for (Integer num : arrayList) {
                                int intValue = num.intValue();
                                int i5 = intValue - i4;
                                int[] iArr3 = new int[i5];
                                System.arraycopy(iArr, i4, iArr3, 0, i5);
                                if (iArr3.length <= this.maxS) {
                                    Node leaf2 = new Leaf();
                                    closeNode(leaf2, iArr3);
                                    innerNode.addChild(iArr2[intValue - 1], leaf2);
                                } else {
                                    InnerNode innerNode4 = new InnerNode();
                                    innerNode.addChild(iArr2[intValue - 1], innerNode4);
                                    linkedList.add(innerNode4);
                                    linkedList2.add(iArr3);
                                    this.parents.put(innerNode4, node2);
                                }
                                i4 = intValue;
                            }
                            innerNode.clear();
                        } else {
                            it = it;
                            it2 = it2;
                            innerNode.addChildren(2);
                            double[] dArr = new double[iArr.length];
                            HashSet hashSet = new HashSet();
                            int i6 = 0;
                            for (int i7 = 0; i7 < iArr.length; i7++) {
                                double value = this.insts.get(iArr[i7]).getValue(selectAttr);
                                if (value == Double.MAX_VALUE) {
                                    i6++;
                                }
                                dArr[i7] = value;
                                hashSet.add(Double.valueOf(value));
                            }
                            if (hashSet.size() != 1) {
                                ArrayList arrayList2 = new ArrayList(hashSet);
                                Collections.sort(arrayList2);
                                if (hashSet.size() <= 2) {
                                    innerNode.split = ((Double) arrayList2.get(0)).doubleValue();
                                } else {
                                    innerNode.split = ((Double) arrayList2.get(this.f14642rd.nextInt(arrayList2.size() - 2) + 1)).doubleValue();
                                }
                                BiArrays.sort(dArr, iArr);
                                int binarySearch = Arrays.binarySearch(dArr, innerNode.split);
                                while (binarySearch < dArr.length) {
                                    int i8 = binarySearch + 1;
                                    if (dArr[binarySearch] != dArr[i8]) {
                                        break;
                                    }
                                    binarySearch = i8;
                                }
                                int i9 = binarySearch + 1;
                                int[] iArr4 = new int[i9];
                                System.arraycopy(iArr, 0, iArr4, 0, i9);
                                if (iArr4.length <= this.maxS) {
                                    Node leaf3 = new Leaf();
                                    closeNode(leaf3, iArr4);
                                    innerNode.addChild(1, leaf3);
                                } else {
                                    InnerNode innerNode5 = new InnerNode();
                                    innerNode.addChild(1, innerNode5);
                                    linkedList.add(innerNode5);
                                    linkedList2.add(iArr4);
                                    this.parents.put(innerNode5, node2);
                                }
                                int length = ((dArr.length - i6) - binarySearch) - 1;
                                if (length > 0) {
                                    int[] iArr5 = new int[length];
                                    System.arraycopy(iArr, i9, iArr5, 0, length);
                                    if (iArr5.length <= this.maxS) {
                                        Node leaf4 = new Leaf();
                                        closeNode(leaf4, iArr5);
                                        innerNode.addChild(2, leaf4);
                                    } else {
                                        InnerNode innerNode6 = new InnerNode();
                                        innerNode.addChild(2, innerNode6);
                                        linkedList.add(innerNode6);
                                        linkedList2.add(iArr5);
                                        this.parents.put(innerNode6, node2);
                                    }
                                }
                                if (i6 > 0) {
                                    int[] iArr6 = new int[i6];
                                    System.arraycopy(iArr, dArr.length - i6, iArr6, 0, i6);
                                    if (iArr6.length <= this.maxS) {
                                        Node leaf5 = new Leaf();
                                        closeNode(leaf5, iArr6);
                                        innerNode.addChild(0, leaf5);
                                    } else {
                                        InnerNode innerNode7 = new InnerNode();
                                        innerNode.addChild(0, innerNode7);
                                        linkedList.add(innerNode7);
                                        linkedList2.add(iArr6);
                                        this.parents.put(innerNode7, node2);
                                    }
                                }
                                innerNode.clear();
                            } else if (iArr.length <= this.maxS) {
                                InnerNode innerNode8 = (InnerNode) this.parents.get(node2);
                                if (innerNode8 == null) {
                                    leaf = closeNode(innerNode, iArr);
                                } else {
                                    int i10 = 0;
                                    while (true) {
                                        if (i10 >= innerNode8.children.length) {
                                            break;
                                        } else if (innerNode8.children[i10].equals(node2)) {
                                            innerNode8.children[i10] = closeNode(innerNode, iArr);
                                            this.parents.remove(node2);
                                            break;
                                        } else {
                                            i10++;
                                        }
                                    }
                                }
                                innerNode.clear();
                                it = it;
                                it2 = it2;
                            } else {
                                innerNode.attr = -1;
                                linkedList.add(innerNode);
                                linkedList2.add(iArr);
                            }
                        }
                        this.parents.remove(node2);
                    }
                }
            }
            this.level = linkedList;
            this.ions = linkedList2;
        } catch (Throwable unused) {
        }
    }

    private Leaf closeNode(Node node, int[] iArr) {
        try {
            switch (this.type) {
                case 0:
                    return closeClassificationNode(node, iArr);
                case 1:
                    return closeRegressionNode(node, iArr);
                case 2:
                    return closeCBRNode(node, iArr);
                default:
                    return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    private Leaf closeClassificationNode(Node node, int[] iArr) {
        Leaf leaf = null;
        try {
            if (node instanceof Leaf) {
                leaf = (Leaf) node;
            } else {
                leaf = new Leaf();
            }
            leaf.addDists(this.insts.getAttributes()[this.insts.getAttrSize() - this.clsSize]);
            for (int i : iArr) {
                for (int i2 = 0; i2 < this.clsSize; i2++) {
                    leaf.incDist((int) this.insts.get(i).getValue(this.insts.getAttrSize() - 1));
                }
            }
            leaf.clear();
            for (int i3 = 0; i3 < leaf.dist.length; i3++) {
                double[] dArr = leaf.dist;
                dArr[i3] = dArr[i3] / iArr.length;
            }
            leaf.size = iArr.length;
        } catch (Throwable unused) {
        }
        return leaf;
    }

    private Leaf closeRegressionNode(Node node, int[] iArr) {
        Leaf leaf = null;
        try {
            if (node instanceof Leaf) {
                leaf = (Leaf) node;
            } else {
                leaf = new Leaf();
            }
            for (int i : iArr) {
                leaf.addValue(this.insts.get(i).getValue(this.insts.getAttrSize() - this.clsSize));
            }
            leaf.clear();
            leaf.f14644v /= iArr.length;
            leaf.size = iArr.length;
        } catch (Throwable unused) {
        }
        return leaf;
    }

    private Leaf closeCBRNode(Node node, int[] iArr) {
        Leaf leaf = null;
        try {
            if (node instanceof Leaf) {
                leaf = (Leaf) node;
            } else {
                leaf = new Leaf();
            }
            leaf.addDists(this.clsSize);
            for (int i : iArr) {
                int i2 = 0;
                for (int i3 = 0; i3 < this.clsSize; i3++) {
                    if (this.insts.get(i).getValue((this.insts.getAttrSize() - this.clsSize) + i3) == 1.0d) {
                        leaf.incDist(i3);
                        i2++;
                    }
                }
                leaf.addValue(i2);
            }
            leaf.clear();
            for (int i4 = 0; i4 < leaf.dist.length; i4++) {
                double[] dArr = leaf.dist;
                dArr[i4] = dArr[i4] / iArr.length;
            }
            leaf.f14644v /= iArr.length;
            leaf.size = iArr.length;
        } catch (Throwable unused) {
        }
        return leaf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [dice.tree.structure.InnerNode] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v7 */
    private int selectAttr(InnerNode innerNode, InnerNode innerNode2, Instance instance) {
        boolean z;
        try {
            int[] attributes = this.insts.getAttributes();
            int attrSize = this.insts.getAttrSize() - this.clsSize;
            int nextInt = this.f14642rd.nextInt(attrSize);
            HashSet hashSet = new HashSet();
            while (true) {
                z = false;
                if (!(innerNode2 instanceof InnerNode)) {
                    break;
                }
                InnerNode innerNode3 = (InnerNode) innerNode2;
                if (innerNode3.attr == -1) {
                    break;
                } else if (attributes[innerNode3.attr] > 0) {
                    hashSet.add(Integer.valueOf(innerNode3.attr));
                    innerNode2 = innerNode3.getChild((int) instance.getValue(innerNode3.attr));
                } else {
                    double value = instance.getValue(innerNode3.attr);
                    if (value == Double.NaN) {
                        innerNode2 = innerNode3.getChild(0);
                    } else if (value <= innerNode3.split) {
                        innerNode2 = innerNode3.getChild(1);
                    } else if (value > innerNode3.split) {
                        innerNode2 = innerNode3.getChild(2);
                    }
                }
            }
            int i = nextInt;
            while (true) {
                if (!hashSet.contains(Integer.valueOf(i))) {
                    z = true;
                    break;
                }
                i = (i + 1) % attrSize;
                if (i == nextInt) {
                    break;
                }
            }
            if (z) {
                return i;
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
