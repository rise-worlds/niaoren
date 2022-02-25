package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.TimeComparison;
import org.apache.tools.ant.util.FileUtils;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class DateSelector extends BaseExtendSelector {
    public static final String CHECKDIRS_KEY = "checkdirs";
    public static final String DATETIME_KEY = "datetime";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    public static final String GRANULARITY_KEY = "granularity";
    public static final String MILLIS_KEY = "millis";
    public static final String PATTERN_KEY = "pattern";
    public static final String WHEN_KEY = "when";
    private long granularity;
    private String pattern;
    private long millis = -1;
    private String dateTime = null;
    private boolean includeDirs = false;
    private TimeComparison when = TimeComparison.EQUAL;

    /* loaded from: classes2.dex */
    public static class TimeComparisons extends TimeComparison {
    }

    public DateSelector() {
        this.granularity = 0L;
        this.granularity = FILE_UTILS.getFileTimestampGranularity();
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder("{dateselector date: ");
        sb.append(this.dateTime);
        sb.append(" compare: ");
        sb.append(this.when.getValue());
        sb.append(" granularity: ");
        sb.append(this.granularity);
        if (this.pattern != null) {
            sb.append(" pattern: ");
            sb.append(this.pattern);
        }
        sb.append(C4963cj.f20747d);
        return sb.toString();
    }

    public void setMillis(long j) {
        this.millis = j;
    }

    public long getMillis() {
        if (this.dateTime != null) {
            validate();
        }
        return this.millis;
    }

    public void setDatetime(String str) {
        this.dateTime = str;
        this.millis = -1L;
    }

    public void setCheckdirs(boolean z) {
        this.includeDirs = z;
    }

    public void setGranularity(int i) {
        this.granularity = i;
    }

    public void setWhen(TimeComparisons timeComparisons) {
        setWhen((TimeComparison) timeComparisons);
    }

    public void setWhen(TimeComparison timeComparison) {
        this.when = timeComparison;
    }

    public void setPattern(String str) {
        this.pattern = str;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameterArr) {
        super.setParameters(parameterArr);
        if (parameterArr != null) {
            for (int i = 0; i < parameterArr.length; i++) {
                String name = parameterArr[i].getName();
                if (MILLIS_KEY.equalsIgnoreCase(name)) {
                    try {
                        setMillis(Long.parseLong(parameterArr[i].getValue()));
                    } catch (NumberFormatException unused) {
                        setError("Invalid millisecond setting " + parameterArr[i].getValue());
                    }
                } else if (DATETIME_KEY.equalsIgnoreCase(name)) {
                    setDatetime(parameterArr[i].getValue());
                } else if (CHECKDIRS_KEY.equalsIgnoreCase(name)) {
                    setCheckdirs(Project.toBoolean(parameterArr[i].getValue()));
                } else if (GRANULARITY_KEY.equalsIgnoreCase(name)) {
                    try {
                        setGranularity(Integer.parseInt(parameterArr[i].getValue()));
                    } catch (NumberFormatException unused2) {
                        setError("Invalid granularity setting " + parameterArr[i].getValue());
                    }
                } else if ("when".equalsIgnoreCase(name)) {
                    setWhen(new TimeComparison(parameterArr[i].getValue()));
                } else if (PATTERN_KEY.equalsIgnoreCase(name)) {
                    setPattern(parameterArr[i].getValue());
                } else {
                    setError("Invalid parameter " + name);
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.dateTime == null && this.millis < 0) {
            setError("You must provide a datetime or the number of milliseconds.");
        } else if (this.millis < 0 && this.dateTime != null) {
            String str = this.pattern;
            try {
                setMillis((str == null ? DateFormat.getDateTimeInstance(3, 3, Locale.US) : new SimpleDateFormat(str)).parse(this.dateTime).getTime());
                if (this.millis < 0) {
                    setError("Date of " + this.dateTime + " results in negative milliseconds value relative to epoch (January 1, 1970, 00:00:00 GMT).");
                }
            } catch (ParseException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Date of ");
                sb.append(this.dateTime);
                sb.append(" Cannot be parsed correctly. It should be in");
                String str2 = this.pattern;
                if (str2 == null) {
                    str2 = " MM/DD/YYYY HH:MM AM_PM";
                }
                sb.append(str2);
                sb.append(" format.");
                setError(sb.toString());
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        validate();
        return (file2.isDirectory() && !this.includeDirs) || this.when.evaluate(file2.lastModified(), this.millis, this.granularity);
    }
}
