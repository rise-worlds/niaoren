package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class Quantifier extends EnumeratedAttribute {
    private static final String[] VALUES = {"all", "each", "every", "any", "some", "one", "majority", "most", "none"};
    public static final Quantifier ALL = new Quantifier("all");
    public static final Quantifier ANY = new Quantifier("any");
    public static final Quantifier ONE = new Quantifier("one");
    public static final Quantifier MAJORITY = new Quantifier("majority");
    public static final Quantifier NONE = new Quantifier("none");
    private static final Predicate ALL_PRED = new Predicate() { // from class: org.apache.tools.ant.types.Quantifier.1
        @Override // org.apache.tools.ant.types.Quantifier.Predicate
        boolean eval(int i, int i2) {
            return i2 == 0;
        }
    };
    private static final Predicate ANY_PRED = new Predicate() { // from class: org.apache.tools.ant.types.Quantifier.2
        @Override // org.apache.tools.ant.types.Quantifier.Predicate
        boolean eval(int i, int i2) {
            return i > 0;
        }
    };
    private static final Predicate ONE_PRED = new Predicate() { // from class: org.apache.tools.ant.types.Quantifier.3
        @Override // org.apache.tools.ant.types.Quantifier.Predicate
        boolean eval(int i, int i2) {
            return i == 1;
        }
    };
    private static final Predicate MAJORITY_PRED = new Predicate() { // from class: org.apache.tools.ant.types.Quantifier.4
        @Override // org.apache.tools.ant.types.Quantifier.Predicate
        boolean eval(int i, int i2) {
            return i > i2;
        }
    };
    private static final Predicate NONE_PRED = new Predicate() { // from class: org.apache.tools.ant.types.Quantifier.5
        @Override // org.apache.tools.ant.types.Quantifier.Predicate
        boolean eval(int i, int i2) {
            return i == 0;
        }
    };
    private static final Predicate[] PREDS = new Predicate[VALUES.length];

    static {
        Predicate[] predicateArr = PREDS;
        Predicate predicate = ALL_PRED;
        predicateArr[0] = predicate;
        predicateArr[1] = predicate;
        predicateArr[2] = predicate;
        Predicate predicate2 = ANY_PRED;
        predicateArr[3] = predicate2;
        predicateArr[4] = predicate2;
        predicateArr[5] = ONE_PRED;
        Predicate predicate3 = MAJORITY_PRED;
        predicateArr[6] = predicate3;
        predicateArr[7] = predicate3;
        predicateArr[8] = NONE_PRED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class Predicate {
        abstract boolean eval(int i, int i2);

        private Predicate() {
        }
    }

    public Quantifier() {
    }

    public Quantifier(String str) {
        setValue(str);
    }

    @Override // org.apache.tools.ant.types.EnumeratedAttribute
    public String[] getValues() {
        return VALUES;
    }

    public boolean evaluate(boolean[] zArr) {
        int i = 0;
        for (boolean z : zArr) {
            if (z) {
                i++;
            }
        }
        return evaluate(i, zArr.length - i);
    }

    public boolean evaluate(int i, int i2) {
        int index = getIndex();
        if (index != -1) {
            return PREDS[index].eval(i, i2);
        }
        throw new BuildException("Quantifier value not set.");
    }
}
