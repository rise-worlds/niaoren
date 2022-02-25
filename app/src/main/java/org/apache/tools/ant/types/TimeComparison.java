package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class TimeComparison extends EnumeratedAttribute {
    private static final String[] VALUES = {"before", "after", "equal"};
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    public static final TimeComparison BEFORE = new TimeComparison("before");
    public static final TimeComparison AFTER = new TimeComparison("after");
    public static final TimeComparison EQUAL = new TimeComparison("equal");

    public TimeComparison() {
    }

    public TimeComparison(String str) {
        setValue(str);
    }

    @Override // org.apache.tools.ant.types.EnumeratedAttribute
    public String[] getValues() {
        return VALUES;
    }

    public boolean evaluate(long j, long j2) {
        return evaluate(j, j2, FILE_UTILS.getFileTimestampGranularity());
    }

    public boolean evaluate(long j, long j2, long j3) {
        int index = getIndex();
        if (index != -1) {
            return index == 0 ? j - j3 < j2 : index == 1 ? j + j3 > j2 : Math.abs(j - j2) <= j3;
        }
        throw new BuildException("TimeComparison value not set.");
    }

    public static int compare(long j, long j2) {
        return compare(j, j2, FILE_UTILS.getFileTimestampGranularity());
    }

    public static int compare(long j, long j2, long j3) {
        long j4 = j - j2;
        long abs = Math.abs(j4);
        if (abs > Math.abs(j3)) {
            return (int) (j4 / abs);
        }
        return 0;
    }
}
