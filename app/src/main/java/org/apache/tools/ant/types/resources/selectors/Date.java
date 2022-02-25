package org.apache.tools.ant.types.resources.selectors;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.TimeComparison;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class Date implements ResourceSelector {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final String MILLIS_OR_DATETIME = "Either the millis or the datetime attribute must be set.";
    private Long millis = null;
    private String dateTime = null;
    private String pattern = null;
    private TimeComparison when = TimeComparison.EQUAL;
    private long granularity = FILE_UTILS.getFileTimestampGranularity();

    public synchronized void setMillis(long j) {
        this.millis = new Long(j);
    }

    public synchronized long getMillis() {
        return this.millis == null ? -1L : this.millis.longValue();
    }

    public synchronized void setDateTime(String str) {
        this.dateTime = str;
        this.millis = null;
    }

    public synchronized String getDatetime() {
        return this.dateTime;
    }

    public synchronized void setGranularity(long j) {
        this.granularity = j;
    }

    public synchronized long getGranularity() {
        return this.granularity;
    }

    public synchronized void setPattern(String str) {
        this.pattern = str;
    }

    public synchronized String getPattern() {
        return this.pattern;
    }

    public synchronized void setWhen(TimeComparison timeComparison) {
        this.when = timeComparison;
    }

    public synchronized TimeComparison getWhen() {
        return this.when;
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public synchronized boolean isSelected(Resource resource) {
        if (this.dateTime == null && this.millis == null) {
            throw new BuildException(MILLIS_OR_DATETIME);
        }
        if (this.millis == null) {
            try {
                long time = (this.pattern == null ? DateFormat.getDateTimeInstance(3, 3, Locale.US) : new SimpleDateFormat(this.pattern)).parse(this.dateTime).getTime();
                if (time >= 0) {
                    setMillis(time);
                } else {
                    throw new BuildException("Date of " + this.dateTime + " results in negative milliseconds value relative to epoch (January 1, 1970, 00:00:00 GMT).");
                }
            } catch (ParseException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Date of ");
                sb.append(this.dateTime);
                sb.append(" Cannot be parsed correctly. It should be in");
                sb.append(this.pattern == null ? " MM/DD/YYYY HH:MM AM_PM" : this.pattern);
                sb.append(" format.");
                throw new BuildException(sb.toString());
            }
        }
        return this.when.evaluate(resource.getLastModified(), this.millis.longValue(), this.granularity);
    }
}
