package dice.tree.model;

import dice.data.Instance;
import dice.tree.structure.InnerNode;
import dice.tree.structure.Leaf;
import dice.tree.structure.Node;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/* compiled from: td */
/* loaded from: classes2.dex */
public class CBRRDTModel {
    public static double frac = 0.75d;
    private int[] attrs;
    private int minS;

    /* renamed from: rd */
    private Random f14643rd;
    private Node[] trees;

    /* compiled from: td */
    /* loaded from: classes2.dex */
    public class Prediction {
        public Map dist;
        double labelNum;

        public Prediction() {
        }
    }

    public void init(Node[] nodeArr, int[] iArr, int i) {
        this.trees = nodeArr;
        this.minS = i;
        this.attrs = iArr;
        this.f14643rd = new Random(0L);
    }

    public void clear() {
        this.trees = null;
        this.attrs = null;
    }

    public Prediction estimate(Instance instance) {
        Prediction prediction = new Prediction();
        double d = 0.0d;
        try {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < this.trees.length; i++) {
                Node findLeaf = findLeaf(this.trees[i], instance);
                if (findLeaf instanceof Leaf) {
                    Leaf leaf = (Leaf) findLeaf;
                    d += leaf.f14644v;
                    for (int i2 = 0; i2 < leaf.distIndex.length; i2++) {
                        Double d2 = (Double) hashMap.get(Integer.valueOf(leaf.distIndex[i2]));
                        if (d2 == null) {
                            hashMap.put(Integer.valueOf(leaf.distIndex[i2]), Double.valueOf(leaf.dist[i2]));
                        } else {
                            hashMap.put(Integer.valueOf(leaf.distIndex[i2]), Double.valueOf(d2.doubleValue() + leaf.dist[i2]));
                        }
                    }
                } else {
                    Prediction childrenPrediction = getChildrenPrediction(findLeaf);
                    Map map = childrenPrediction.dist;
                    d += childrenPrediction.labelNum;
                    for (Integer num : map.keySet()) {
                        Double d3 = (Double) hashMap.get(num);
                        if (d3 == null) {
                            hashMap.put(num, map.get(num));
                        } else {
                            hashMap.put(num, Double.valueOf(((Double) map.get(num)).doubleValue() + d3.doubleValue()));
                        }
                    }
                }
            }
            for (Integer num2 : hashMap.keySet()) {
                hashMap.put(num2, Double.valueOf(((Double) hashMap.get(num2)).doubleValue() / this.trees.length));
            }
            prediction.labelNum = d / this.trees.length;
            prediction.dist = hashMap;
        } catch (Throwable unused) {
        }
        return prediction;
    }

    private Node findLeaf(Node node, Instance instance) {
        InnerNode innerNode = node;
        while (innerNode instanceof InnerNode) {
            try {
                InnerNode innerNode2 = (InnerNode) innerNode;
                if (this.attrs[innerNode2.attr] > 0) {
                    Node child = innerNode2.getChild((int) instance.getValue(innerNode2.attr));
                    if (child == null) {
                        return innerNode2;
                    }
                    if (!(child instanceof InnerNode)) {
                        return child;
                    }
                    innerNode = (InnerNode) child;
                } else {
                    double value = instance.getValue(innerNode2.attr);
                    if (Double.isNaN(value)) {
                        Node child2 = innerNode2.getChild(0);
                        if (child2 == null) {
                            return innerNode2;
                        }
                        if (!(child2 instanceof InnerNode)) {
                            return child2;
                        }
                        innerNode = (InnerNode) child2;
                    } else if (value <= innerNode2.split) {
                        Node child3 = innerNode2.getChild(1);
                        if (child3 == null) {
                            return innerNode2;
                        }
                        if (!(child3 instanceof InnerNode)) {
                            return child3;
                        }
                        innerNode = (InnerNode) child3;
                    } else {
                        Node child4 = innerNode2.getChild(2);
                        if (child4 == null) {
                            return innerNode2;
                        }
                        if (!(child4 instanceof InnerNode)) {
                            return child4;
                        }
                        innerNode = (InnerNode) child4;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return node;
    }

    private Prediction getChildrenPrediction(Node node) {
        Prediction prediction = new Prediction();
        try {
            HashMap hashMap = new HashMap();
            double d = 0.0d;
            LinkedList<Node> linkedList = new LinkedList();
            linkedList.add(node);
            LinkedList linkedList2 = new LinkedList();
            int i = 0;
            while (linkedList.size() > 0) {
                for (Node node2 : linkedList) {
                    if (node2 instanceof Leaf) {
                        Leaf leaf = (Leaf) node2;
                        double d2 = d + (leaf.size * leaf.f14644v);
                        for (int i2 = 0; i2 < leaf.distIndex.length; i2++) {
                            Double d3 = (Double) hashMap.get(Integer.valueOf(leaf.distIndex[i2]));
                            if (d3 == null) {
                                linkedList2 = linkedList2;
                                hashMap.put(Integer.valueOf(leaf.distIndex[i2]), Double.valueOf(leaf.dist[i2] * leaf.size));
                                d2 = d2;
                            } else {
                                linkedList2 = linkedList2;
                                d2 = d2;
                                hashMap.put(Integer.valueOf(leaf.distIndex[i2]), Double.valueOf(d3.doubleValue() + (leaf.dist[i2] * leaf.size)));
                            }
                        }
                        i += leaf.size;
                        linkedList2 = linkedList2;
                        d = d2;
                    } else {
                        LinkedList linkedList3 = linkedList2;
                        InnerNode innerNode = (InnerNode) node2;
                        int i3 = 0;
                        while (i3 < innerNode.children.length) {
                            linkedList3.add(innerNode.children[i3]);
                            i3++;
                            linkedList3 = linkedList3;
                        }
                        linkedList2 = linkedList3;
                    }
                }
                linkedList2 = new LinkedList();
                linkedList = linkedList2;
            }
            double d4 = i;
            double d5 = d / d4;
            for (Integer num : hashMap.keySet()) {
                hashMap.put(num, Double.valueOf(((Double) hashMap.get(num)).doubleValue() / d4));
            }
            prediction.labelNum = d5;
            prediction.dist = hashMap;
        } catch (Throwable unused) {
        }
        return prediction;
    }
}
