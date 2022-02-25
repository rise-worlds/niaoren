package org.apache.tools.ant.taskdefs.optional.vss;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.LogStreamHandler;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public abstract class MSVSS extends Task implements MSVSSConstants {
    private String ssDir = null;
    private String vssLogin = null;
    private String vssPath = null;
    private String serverPath = null;
    private String version = null;
    private String date = null;
    private String label = null;
    private String autoResponse = null;
    private String localPath = null;
    private String comment = null;
    private String fromLabel = null;
    private String toLabel = null;
    private String outputFileName = null;
    private String user = null;
    private String fromDate = null;
    private String toDate = null;
    private String style = null;
    private boolean quiet = false;
    private boolean recursive = false;
    private boolean writable = false;
    private boolean failOnError = true;
    private boolean getLocalCopy = true;
    private int numDays = Integer.MIN_VALUE;
    private DateFormat dateFormat = DateFormat.getDateInstance(3);
    private CurrentModUpdated timestamp = null;
    private WritableFiles writableFiles = null;

    abstract Commandline buildCmdLine();

    public final void setSsdir(String str) {
        this.ssDir = FileUtils.translatePath(str);
    }

    public final void setLogin(String str) {
        this.vssLogin = str;
    }

    public final void setVsspath(String str) {
        if (str.startsWith("vss://")) {
            str = str.substring(5);
        }
        if (str.startsWith("$")) {
            this.vssPath = str;
            return;
        }
        this.vssPath = "$" + str;
    }

    public final void setServerpath(String str) {
        this.serverPath = str;
    }

    public final void setFailOnError(boolean z) {
        this.failOnError = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Commandline buildCmdLine = buildCmdLine();
        int run = run(buildCmdLine);
        if (Execute.isFailure(run) && getFailOnError()) {
            throw new BuildException("Failed executing: " + formatCommandLine(buildCmdLine) + " With a return code of " + run, getLocation());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalComment(String str) {
        this.comment = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalAutoResponse(String str) {
        this.autoResponse = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalDate(String str) {
        this.date = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    protected void setInternalFailOnError(boolean z) {
        this.failOnError = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalFromDate(String str) {
        this.fromDate = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalFromLabel(String str) {
        this.fromLabel = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalLabel(String str) {
        this.label = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalLocalPath(String str) {
        this.localPath = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalNumDays(int i) {
        this.numDays = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalOutputFilename(String str) {
        this.outputFileName = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalQuiet(boolean z) {
        this.quiet = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalRecursive(boolean z) {
        this.recursive = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalStyle(String str) {
        this.style = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalToDate(String str) {
        this.toDate = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalToLabel(String str) {
        this.toLabel = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalUser(String str) {
        this.user = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalVersion(String str) {
        this.version = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalWritable(boolean z) {
        this.writable = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalFileTimeStamp(CurrentModUpdated currentModUpdated) {
        this.timestamp = currentModUpdated;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalWritableFiles(WritableFiles writableFiles) {
        this.writableFiles = writableFiles;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalGetLocalCopy(boolean z) {
        this.getLocalCopy = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSSCommand() {
        String str;
        StringBuilder sb;
        String str2 = this.ssDir;
        if (str2 == null) {
            return MSVSSConstants.SS_EXE;
        }
        if (str2.endsWith(File.separator)) {
            sb = new StringBuilder();
            str = this.ssDir;
        } else {
            sb = new StringBuilder();
            sb.append(this.ssDir);
            str = File.separator;
        }
        sb.append(str);
        sb.append(MSVSSConstants.SS_EXE);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVsspath() {
        return this.vssPath;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getQuiet() {
        return this.quiet ? MSVSSConstants.FLAG_QUIET : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getRecursive() {
        return this.recursive ? MSVSSConstants.FLAG_RECURSION : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getWritable() {
        return this.writable ? MSVSSConstants.FLAG_WRITABLE : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLabel() {
        String str = this.label;
        if (str == null || str.length() <= 0) {
            return "";
        }
        return MSVSSConstants.FLAG_LABEL + getShortLabel();
    }

    private String getShortLabel() {
        String str = this.label;
        if (str == null || str.length() <= 31) {
            return this.label;
        }
        String substring = this.label.substring(0, 30);
        log("Label is longer than 31 characters, truncated to: " + substring, 1);
        return substring;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getStyle() {
        String str = this.style;
        return str != null ? str : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVersionDateLabel() {
        if (this.version != null) {
            return MSVSSConstants.FLAG_VERSION + this.version;
        } else if (this.date != null) {
            return MSVSSConstants.FLAG_VERSION_DATE + this.date;
        } else {
            String shortLabel = getShortLabel();
            if (shortLabel == null || shortLabel.equals("")) {
                return "";
            }
            return MSVSSConstants.FLAG_VERSION_LABEL + shortLabel;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVersion() {
        if (this.version == null) {
            return "";
        }
        return MSVSSConstants.FLAG_VERSION + this.version;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLocalpath() {
        if (this.localPath == null) {
            return "";
        }
        File resolveFile = getProject().resolveFile(this.localPath);
        if (!resolveFile.exists()) {
            if (resolveFile.mkdirs()) {
                Project project = getProject();
                project.log("Created dir: " + resolveFile.getAbsolutePath());
            } else {
                throw new BuildException("Directory " + this.localPath + " creation was not successful for an unknown reason", getLocation());
            }
        }
        return MSVSSConstants.FLAG_OVERRIDE_WORKING_DIR + this.localPath;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getComment() {
        if (this.comment == null) {
            return "-C-";
        }
        return MSVSSConstants.FLAG_COMMENT + this.comment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getAutoresponse() {
        String str = this.autoResponse;
        return str == null ? MSVSSConstants.FLAG_AUTORESPONSE_DEF : str.equalsIgnoreCase("Y") ? MSVSSConstants.FLAG_AUTORESPONSE_YES : this.autoResponse.equalsIgnoreCase("N") ? MSVSSConstants.FLAG_AUTORESPONSE_NO : MSVSSConstants.FLAG_AUTORESPONSE_DEF;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLogin() {
        if (this.vssLogin == null) {
            return "";
        }
        return "-Y" + this.vssLogin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getOutput() {
        if (this.outputFileName == null) {
            return "";
        }
        return MSVSSConstants.FLAG_OUTPUT + this.outputFileName;
    }

    protected String getUser() {
        if (this.user == null) {
            return "";
        }
        return MSVSSConstants.FLAG_USER + this.user;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVersionLabel() {
        if (this.fromLabel == null && this.toLabel == null) {
            return "";
        }
        String str = this.fromLabel;
        if (str == null || this.toLabel == null) {
            String str2 = this.fromLabel;
            if (str2 != null) {
                if (str2.length() > 31) {
                    this.fromLabel = this.fromLabel.substring(0, 30);
                    log("FromLabel is longer than 31 characters, truncated to: " + this.fromLabel, 1);
                }
                return "-V~L" + this.fromLabel;
            }
            if (this.toLabel.length() > 31) {
                this.toLabel = this.toLabel.substring(0, 30);
                log("ToLabel is longer than 31 characters, truncated to: " + this.toLabel, 1);
            }
            return MSVSSConstants.FLAG_VERSION_LABEL + this.toLabel;
        }
        if (str.length() > 31) {
            this.fromLabel = this.fromLabel.substring(0, 30);
            log("FromLabel is longer than 31 characters, truncated to: " + this.fromLabel, 1);
        }
        if (this.toLabel.length() > 31) {
            this.toLabel = this.toLabel.substring(0, 30);
            log("ToLabel is longer than 31 characters, truncated to: " + this.toLabel, 1);
        }
        return MSVSSConstants.FLAG_VERSION_LABEL + this.toLabel + MSVSSConstants.VALUE_FROMLABEL + this.fromLabel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVersionDate() throws BuildException {
        String str;
        StringBuilder sb;
        if (this.fromDate == null && this.toDate == null && this.numDays == Integer.MIN_VALUE) {
            return "";
        }
        if (this.fromDate != null && this.toDate != null) {
            return MSVSSConstants.FLAG_VERSION_DATE + this.toDate + MSVSSConstants.VALUE_FROMDATE + this.fromDate;
        } else if (this.toDate != null && this.numDays != Integer.MIN_VALUE) {
            try {
                return MSVSSConstants.FLAG_VERSION_DATE + this.toDate + MSVSSConstants.VALUE_FROMDATE + calcDate(this.toDate, this.numDays);
            } catch (ParseException unused) {
                throw new BuildException("Error parsing date: " + this.toDate, getLocation());
            }
        } else if (this.fromDate == null || this.numDays == Integer.MIN_VALUE) {
            if (this.fromDate != null) {
                sb = new StringBuilder();
                sb.append("-V~d");
                str = this.fromDate;
            } else {
                sb = new StringBuilder();
                sb.append(MSVSSConstants.FLAG_VERSION_DATE);
                str = this.toDate;
            }
            sb.append(str);
            return sb.toString();
        } else {
            try {
                return MSVSSConstants.FLAG_VERSION_DATE + calcDate(this.fromDate, this.numDays) + MSVSSConstants.VALUE_FROMDATE + this.fromDate;
            } catch (ParseException unused2) {
                throw new BuildException("Error parsing date: " + this.fromDate, getLocation());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getGetLocalCopy() {
        return !this.getLocalCopy ? MSVSSConstants.FLAG_NO_GET : "";
    }

    private boolean getFailOnError() {
        if (getWritableFiles().equals(MSVSSConstants.WRITABLE_SKIP)) {
            return false;
        }
        return this.failOnError;
    }

    public String getFileTimeStamp() {
        CurrentModUpdated currentModUpdated = this.timestamp;
        return currentModUpdated == null ? "" : currentModUpdated.getValue().equals(MSVSSConstants.TIME_MODIFIED) ? MSVSSConstants.FLAG_FILETIME_MODIFIED : this.timestamp.getValue().equals(MSVSSConstants.TIME_UPDATED) ? MSVSSConstants.FLAG_FILETIME_UPDATED : MSVSSConstants.FLAG_FILETIME_DEF;
    }

    public String getWritableFiles() {
        WritableFiles writableFiles = this.writableFiles;
        if (writableFiles == null) {
            return "";
        }
        if (writableFiles.getValue().equals(MSVSSConstants.WRITABLE_REPLACE)) {
            return MSVSSConstants.FLAG_REPLACE_WRITABLE;
        }
        if (!this.writableFiles.getValue().equals(MSVSSConstants.WRITABLE_SKIP)) {
            return "";
        }
        this.failOnError = false;
        return MSVSSConstants.FLAG_SKIP_WRITABLE;
    }

    private int run(Commandline commandline) {
        try {
            Execute execute = new Execute(new LogStreamHandler((Task) this, 2, 1));
            if (this.serverPath != null) {
                String[] environment = execute.getEnvironment();
                if (environment == null) {
                    environment = new String[0];
                }
                String[] strArr = new String[environment.length + 1];
                System.arraycopy(environment, 0, strArr, 0, environment.length);
                int length = environment.length;
                strArr[length] = "SSDIR=" + this.serverPath;
                execute.setEnvironment(strArr);
            }
            execute.setAntRun(getProject());
            execute.setWorkingDirectory(getProject().getBaseDir());
            execute.setCommandline(commandline.getCommandline());
            execute.setVMLauncher(false);
            return execute.execute();
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    private String calcDate(String str, int i) throws ParseException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(this.dateFormat.parse(str));
        gregorianCalendar.add(5, i);
        return this.dateFormat.format(gregorianCalendar.getTime());
    }

    private String formatCommandLine(Commandline commandline) {
        StringBuffer stringBuffer = new StringBuffer(commandline.toString());
        int indexOf = stringBuffer.substring(0).indexOf("-Y");
        if (indexOf > 0) {
            int indexOf2 = stringBuffer.substring(0).indexOf(",", indexOf);
            int indexOf3 = stringBuffer.substring(0).indexOf(ExpandableTextView.f6958c, indexOf2);
            while (true) {
                indexOf2++;
                if (indexOf2 >= indexOf3) {
                    break;
                }
                stringBuffer.setCharAt(indexOf2, '*');
            }
        }
        return stringBuffer.toString();
    }

    /* loaded from: classes2.dex */
    public static class CurrentModUpdated extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{MSVSSConstants.TIME_CURRENT, MSVSSConstants.TIME_MODIFIED, MSVSSConstants.TIME_UPDATED};
        }
    }

    /* loaded from: classes2.dex */
    public static class WritableFiles extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{MSVSSConstants.WRITABLE_REPLACE, MSVSSConstants.WRITABLE_SKIP, "fail"};
        }
    }
}
