package org.apache.tools.ant.taskdefs.optional;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.LayoutPreservingProperties;
import org.slf4j.Marker;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class PropertyFile extends Task {
    private String comment;
    private Vector entries = new Vector();
    private Properties properties;
    private File propertyfile;
    private boolean useJDKProperties;

    private boolean checkParam(File file) {
        return file != null;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        checkParameters();
        readFile();
        executeOperation();
        writeFile();
    }

    public Entry createEntry() {
        Entry entry = new Entry();
        this.entries.addElement(entry);
        return entry;
    }

    private void executeOperation() throws BuildException {
        Enumeration elements = this.entries.elements();
        while (elements.hasMoreElements()) {
            ((Entry) elements.nextElement()).executeOn(this.properties);
        }
    }

    private void readFile() throws BuildException {
        Throwable th;
        Throwable th2;
        FileInputStream fileInputStream;
        if (this.useJDKProperties) {
            this.properties = new Properties();
        } else {
            this.properties = new LayoutPreservingProperties();
        }
        try {
            FileOutputStream fileOutputStream = null;
            if (this.propertyfile.exists()) {
                log("Updating property file: " + this.propertyfile.getAbsolutePath());
                try {
                    fileInputStream = new FileInputStream(this.propertyfile);
                    try {
                        this.properties.load(new BufferedInputStream(fileInputStream));
                        fileInputStream.close();
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    fileInputStream = null;
                }
            } else {
                log("Creating new property file: " + this.propertyfile.getAbsolutePath());
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(this.propertyfile.getAbsolutePath());
                    try {
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Throwable th5) {
                        th = th5;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }
        } catch (IOException e) {
            throw new BuildException(e.toString());
        }
    }

    private void checkParameters() throws BuildException {
        if (!checkParam(this.propertyfile)) {
            throw new BuildException("file token must not be null.", getLocation());
        }
    }

    public void setFile(File file) {
        this.propertyfile = file;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setJDKProperties(boolean z) {
        this.useJDKProperties = z;
    }

    private void writeFile() throws BuildException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.properties.store(byteArrayOutputStream, this.comment);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.propertyfile);
                try {
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.close();
                } catch (IOException e) {
                    FileUtils.getFileUtils().tryHardToDelete(this.propertyfile);
                    throw e;
                }
            } catch (IOException e2) {
                throw new BuildException(e2, getLocation());
            }
        } catch (IOException e3) {
            throw new BuildException(e3, getLocation());
        }
    }

    /* loaded from: classes2.dex */
    public static class Entry {
        private static final String DEFAULT_DATE_VALUE = "now";
        private static final int DEFAULT_INT_VALUE = 0;
        private static final String DEFAULT_STRING_VALUE = "";
        private String key = null;
        private int type = 2;
        private int operation = 2;
        private String value = null;
        private String defaultValue = null;
        private String newValue = null;
        private String pattern = null;
        private int field = 5;

        public void setKey(String str) {
            this.key = str;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public void setOperation(Operation operation) {
            this.operation = Operation.toOperation(operation.getValue());
        }

        public void setType(Type type) {
            this.type = Type.toType(type.getValue());
        }

        public void setDefault(String str) {
            this.defaultValue = str;
        }

        public void setPattern(String str) {
            this.pattern = str;
        }

        public void setUnit(Unit unit) {
            this.field = unit.getCalendarField();
        }

        protected void executeOn(Properties properties) throws BuildException {
            checkParameters();
            if (this.operation == 3) {
                properties.remove(this.key);
                return;
            }
            String str = (String) properties.get(this.key);
            try {
                if (this.type == 0) {
                    executeInteger(str);
                } else if (this.type == 1) {
                    executeDate(str);
                } else if (this.type == 2) {
                    executeString(str);
                } else {
                    throw new BuildException("Unknown operation type: " + this.type);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (this.newValue == null) {
                this.newValue = "";
            }
            properties.put(this.key, this.newValue);
        }

        private void executeDate(String str) throws BuildException {
            Calendar instance = Calendar.getInstance();
            if (this.pattern == null) {
                this.pattern = "yyyy/MM/dd HH:mm";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.pattern);
            String currentValue = getCurrentValue(str);
            if (currentValue == null) {
                currentValue = DEFAULT_DATE_VALUE;
            }
            if (DEFAULT_DATE_VALUE.equals(currentValue)) {
                instance.setTime(new Date());
            } else {
                try {
                    instance.setTime(simpleDateFormat.parse(currentValue));
                } catch (ParseException unused) {
                }
            }
            if (this.operation != 2) {
                try {
                    int parseInt = Integer.parseInt(this.value);
                    if (this.operation == 1) {
                        parseInt *= -1;
                    }
                    instance.add(this.field, parseInt);
                } catch (NumberFormatException unused2) {
                    throw new BuildException("Value not an integer on " + this.key);
                }
            }
            this.newValue = simpleDateFormat.format(instance.getTime());
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void executeInteger(java.lang.String r6) throws org.apache.tools.ant.BuildException {
            /*
                r5 = this;
                java.lang.String r0 = r5.pattern
                if (r0 == 0) goto L_0x000a
                java.text.DecimalFormat r1 = new java.text.DecimalFormat
                r1.<init>(r0)
                goto L_0x000f
            L_0x000a:
                java.text.DecimalFormat r1 = new java.text.DecimalFormat
                r1.<init>()
            L_0x000f:
                r0 = 0
                java.lang.String r6 = r5.getCurrentValue(r6)     // Catch: NumberFormatException | ParseException -> 0x0021
                if (r6 == 0) goto L_0x001f
                java.lang.Number r6 = r1.parse(r6)     // Catch: NumberFormatException | ParseException -> 0x0021
                int r6 = r6.intValue()     // Catch: NumberFormatException | ParseException -> 0x0021
                goto L_0x0022
            L_0x001f:
                r6 = 0
                goto L_0x0022
            L_0x0021:
                r6 = 0
            L_0x0022:
                int r2 = r5.operation
                r3 = 2
                if (r2 != r3) goto L_0x0029
                r0 = r6
                goto L_0x0043
            L_0x0029:
                java.lang.String r2 = r5.value
                r3 = 1
                if (r2 == 0) goto L_0x0037
                java.lang.Number r2 = r1.parse(r2)     // Catch: NumberFormatException | ParseException -> 0x0037
                int r2 = r2.intValue()     // Catch: NumberFormatException | ParseException -> 0x0037
                goto L_0x0038
            L_0x0037:
                r2 = 1
            L_0x0038:
                int r4 = r5.operation
                if (r4 != 0) goto L_0x003f
                int r0 = r6 + r2
                goto L_0x0043
            L_0x003f:
                if (r4 != r3) goto L_0x0043
                int r0 = r6 - r2
            L_0x0043:
                long r2 = (long) r0
                java.lang.String r6 = r1.format(r2)
                r5.newValue = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.optional.PropertyFile.Entry.executeInteger(java.lang.String):void");
        }

        private void executeString(String str) throws BuildException {
            String str2 = "";
            String currentValue = getCurrentValue(str);
            if (currentValue == null) {
                currentValue = "";
            }
            int i = this.operation;
            if (i == 2) {
                str2 = currentValue;
            } else if (i == 0) {
                str2 = currentValue + this.value;
            }
            this.newValue = str2;
        }

        private void checkParameters() throws BuildException {
            if (this.type == 2 && this.operation == 1) {
                throw new BuildException("- is not supported for string properties (key:" + this.key + ")");
            } else if (this.value == null && this.defaultValue == null && this.operation != 3) {
                throw new BuildException("\"value\" and/or \"default\" attribute must be specified (key:" + this.key + ")");
            } else if (this.key == null) {
                throw new BuildException("key is mandatory");
            } else if (this.type == 2 && this.pattern != null) {
                throw new BuildException("pattern is not supported for string properties (key:" + this.key + ")");
            }
        }

        private String getCurrentValue(String str) {
            String str2;
            String str3;
            if (this.operation == 2) {
                String str4 = this.value;
                if (str4 == null || this.defaultValue != null) {
                    str4 = null;
                }
                if (!(this.value != null || this.defaultValue == null || str == null)) {
                    str4 = str;
                }
                if (this.value == null && (str3 = this.defaultValue) != null && str == null) {
                    str4 = str3;
                }
                String str5 = this.value;
                if (!(str5 == null || this.defaultValue == null || str == null)) {
                    str4 = str5;
                }
                return (this.value == null || (str2 = this.defaultValue) == null || str != null) ? str4 : str2;
            }
            if (str == null) {
                str = this.defaultValue;
            }
            return str;
        }

        /* loaded from: classes2.dex */
        public static class Operation extends EnumeratedAttribute {
            public static final int DECREMENT_OPER = 1;
            public static final int DELETE_OPER = 3;
            public static final int EQUALS_OPER = 2;
            public static final int INCREMENT_OPER = 0;

            @Override // org.apache.tools.ant.types.EnumeratedAttribute
            public String[] getValues() {
                return new String[]{Marker.ANY_NON_NULL_MARKER, "-", SimpleComparison.f23609c, "del"};
            }

            public static int toOperation(String str) {
                if (Marker.ANY_NON_NULL_MARKER.equals(str)) {
                    return 0;
                }
                if ("-".equals(str)) {
                    return 1;
                }
                return "del".equals(str) ? 3 : 2;
            }
        }

        /* loaded from: classes2.dex */
        public static class Type extends EnumeratedAttribute {
            public static final int DATE_TYPE = 1;
            public static final int INTEGER_TYPE = 0;
            public static final int STRING_TYPE = 2;

            @Override // org.apache.tools.ant.types.EnumeratedAttribute
            public String[] getValues() {
                return new String[]{"int", "date", "string"};
            }

            public static int toType(String str) {
                if ("int".equals(str)) {
                    return 0;
                }
                return "date".equals(str) ? 1 : 2;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Unit extends EnumeratedAttribute {
        private static final String DAY = "day";
        private static final String HOUR = "hour";
        private static final String MILLISECOND = "millisecond";
        private static final String MINUTE = "minute";
        private static final String SECOND = "second";
        private static final String WEEK = "week";
        private Map calendarFields = new HashMap();
        private static final String MONTH = "month";
        private static final String YEAR = "year";
        private static final String[] UNITS = {"millisecond", "second", "minute", "hour", "day", "week", MONTH, YEAR};

        public Unit() {
            this.calendarFields.put("millisecond", new Integer(14));
            this.calendarFields.put("second", new Integer(13));
            this.calendarFields.put("minute", new Integer(12));
            this.calendarFields.put("hour", new Integer(11));
            this.calendarFields.put("day", new Integer(5));
            this.calendarFields.put("week", new Integer(3));
            this.calendarFields.put(MONTH, new Integer(2));
            this.calendarFields.put(YEAR, new Integer(1));
        }

        public int getCalendarField() {
            return ((Integer) this.calendarFields.get(getValue().toLowerCase())).intValue();
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return UNITS;
        }
    }
}
