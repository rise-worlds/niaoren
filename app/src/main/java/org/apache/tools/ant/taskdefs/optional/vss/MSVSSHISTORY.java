package org.apache.tools.ant.taskdefs.optional.vss;

import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* loaded from: classes2.dex */
public class MSVSSHISTORY extends MSVSS {
    @Override // org.apache.tools.ant.taskdefs.optional.vss.MSVSS
    Commandline buildCmdLine() {
        Commandline commandline = new Commandline();
        if (getVsspath() != null) {
            commandline.setExecutable(getSSCommand());
            commandline.createArgument().setValue(MSVSSConstants.COMMAND_HISTORY);
            commandline.createArgument().setValue(getVsspath());
            commandline.createArgument().setValue(MSVSSConstants.FLAG_AUTORESPONSE_DEF);
            commandline.createArgument().setValue(getVersionDate());
            commandline.createArgument().setValue(getVersionLabel());
            commandline.createArgument().setValue(getRecursive());
            commandline.createArgument().setValue(getStyle());
            commandline.createArgument().setValue(getLogin());
            commandline.createArgument().setValue(getOutput());
            return commandline;
        }
        throw new BuildException("vsspath attribute must be set!", getLocation());
    }

    public void setRecursive(boolean z) {
        super.setInternalRecursive(z);
    }

    public void setUser(String str) {
        super.setInternalUser(str);
    }

    public void setFromDate(String str) {
        super.setInternalFromDate(str);
    }

    public void setToDate(String str) {
        super.setInternalToDate(str);
    }

    public void setFromLabel(String str) {
        super.setInternalFromLabel(str);
    }

    public void setToLabel(String str) {
        super.setInternalToLabel(str);
    }

    public void setNumdays(int i) {
        super.setInternalNumDays(i);
    }

    public void setOutput(File file) {
        if (file != null) {
            super.setInternalOutputFilename(file.getAbsolutePath());
        }
    }

    public void setDateFormat(String str) {
        super.setInternalDateFormat(new SimpleDateFormat(str));
    }

    public void setStyle(BriefCodediffNofile briefCodediffNofile) {
        String value = briefCodediffNofile.getValue();
        if (value.equals(MSVSSConstants.STYLE_BRIEF)) {
            super.setInternalStyle(MSVSSConstants.FLAG_BRIEF);
        } else if (value.equals(MSVSSConstants.STYLE_CODEDIFF)) {
            super.setInternalStyle(MSVSSConstants.FLAG_CODEDIFF);
        } else if (value.equals("default")) {
            super.setInternalStyle("");
        } else if (value.equals(MSVSSConstants.STYLE_NOFILE)) {
            super.setInternalStyle(MSVSSConstants.FLAG_NO_FILE);
        } else {
            throw new BuildException("Style " + briefCodediffNofile + " unknown.", getLocation());
        }
    }

    /* loaded from: classes2.dex */
    public static class BriefCodediffNofile extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{MSVSSConstants.STYLE_BRIEF, MSVSSConstants.STYLE_CODEDIFF, MSVSSConstants.STYLE_NOFILE, "default"};
        }
    }
}
