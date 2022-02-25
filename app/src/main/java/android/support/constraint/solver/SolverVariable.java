package android.support.constraint.solver;

import java.util.Arrays;

/* loaded from: classes.dex */
public class SolverVariable {
    private static final boolean INTERNAL_DEBUG = false;
    static final int MAX_STRENGTH = 7;
    public static final int STRENGTH_BARRIER = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 6;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int uniqueConstantId = 1;
    private static int uniqueErrorId = 1;
    private static int uniqueId = 1;
    private static int uniqueSlackId = 1;
    private static int uniqueUnrestrictedId = 1;
    public float computedValue;
    int definitionId;

    /* renamed from: id */
    public int f4id;
    ArrayRow[] mClientEquations;
    int mClientEquationsCount;
    private String mName;
    Type mType;
    public int strength;
    float[] strengthVector;
    public int usageInRowCount;

    /* loaded from: classes.dex */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void increaseErrorId() {
        uniqueErrorId++;
    }

    private static String getUniqueName(Type type, String str) {
        if (str != null) {
            return str + uniqueErrorId;
        }
        switch (type) {
            case UNRESTRICTED:
                StringBuilder sb = new StringBuilder();
                sb.append("U");
                int i = uniqueUnrestrictedId + 1;
                uniqueUnrestrictedId = i;
                sb.append(i);
                return sb.toString();
            case CONSTANT:
                StringBuilder sb2 = new StringBuilder();
                sb2.append("C");
                int i2 = uniqueConstantId + 1;
                uniqueConstantId = i2;
                sb2.append(i2);
                return sb2.toString();
            case SLACK:
                StringBuilder sb3 = new StringBuilder();
                sb3.append("S");
                int i3 = uniqueSlackId + 1;
                uniqueSlackId = i3;
                sb3.append(i3);
                return sb3.toString();
            case ERROR:
                StringBuilder sb4 = new StringBuilder();
                sb4.append("e");
                int i4 = uniqueErrorId + 1;
                uniqueErrorId = i4;
                sb4.append(i4);
                return sb4.toString();
            case UNKNOWN:
                StringBuilder sb5 = new StringBuilder();
                sb5.append("V");
                int i5 = uniqueId + 1;
                uniqueId = i5;
                sb5.append(i5);
                return sb5.toString();
            default:
                throw new AssertionError(type.name());
        }
    }

    public SolverVariable(String str, Type type) {
        this.f4id = -1;
        this.definitionId = -1;
        this.strength = 0;
        this.strengthVector = new float[7];
        this.mClientEquations = new ArrayRow[8];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mName = str;
        this.mType = type;
    }

    public SolverVariable(Type type, String str) {
        this.f4id = -1;
        this.definitionId = -1;
        this.strength = 0;
        this.strengthVector = new float[7];
        this.mClientEquations = new ArrayRow[8];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mType = type;
    }

    void clearStrengths() {
        for (int i = 0; i < 7; i++) {
            this.strengthVector[i] = 0.0f;
        }
    }

    String strengthsToString() {
        String str = this + "[";
        boolean z = false;
        boolean z2 = true;
        for (int i = 0; i < this.strengthVector.length; i++) {
            String str2 = str + this.strengthVector[i];
            float[] fArr = this.strengthVector;
            if (fArr[i] > 0.0f) {
                z = false;
            } else if (fArr[i] < 0.0f) {
                z = true;
            }
            if (this.strengthVector[i] != 0.0f) {
                z2 = false;
            }
            str = i < this.strengthVector.length - 1 ? str2 + ", " : str2 + "] ";
        }
        if (z) {
            str = str + " (-)";
        }
        if (!z2) {
            return str;
        }
        return str + " (*)";
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.mClientEquationsCount;
            if (i >= i2) {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i2 >= arrayRowArr.length) {
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i3 = this.mClientEquationsCount;
                arrayRowArr2[i3] = arrayRow;
                this.mClientEquationsCount = i3 + 1;
                return;
            } else if (this.mClientEquations[i] != arrayRow) {
                i++;
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (r1 >= ((r0 - r2) - 1)) goto L_0x001f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0012, code lost:
        r6 = r5.mClientEquations;
        r3 = r2 + r1;
        r6[r3] = r6[r3 + 1];
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5.mClientEquationsCount--;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void removeFromRow(android.support.constraint.solver.ArrayRow r6) {
        /*
            r5 = this;
            int r0 = r5.mClientEquationsCount
            r1 = 0
            r2 = 0
        L_0x0004:
            if (r2 >= r0) goto L_0x0029
            android.support.constraint.solver.ArrayRow[] r3 = r5.mClientEquations
            r3 = r3[r2]
            if (r3 != r6) goto L_0x0026
        L_0x000c:
            int r6 = r0 - r2
            int r6 = r6 + (-1)
            if (r1 >= r6) goto L_0x001f
            android.support.constraint.solver.ArrayRow[] r6 = r5.mClientEquations
            int r3 = r2 + r1
            int r4 = r3 + 1
            r4 = r6[r4]
            r6[r3] = r4
            int r1 = r1 + 1
            goto L_0x000c
        L_0x001f:
            int r6 = r5.mClientEquationsCount
            int r6 = r6 + (-1)
            r5.mClientEquationsCount = r6
            return
        L_0x0026:
            int r2 = r2 + 1
            goto L_0x0004
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.SolverVariable.removeFromRow(android.support.constraint.solver.ArrayRow):void");
    }

    public final void updateReferencesWithNewDefinition(ArrayRow arrayRow) {
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2].variables.updateFromRow(this.mClientEquations[i2], arrayRow, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.f4id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(Type type, String str) {
        this.mType = type;
    }

    public String toString() {
        return "" + this.mName;
    }
}
