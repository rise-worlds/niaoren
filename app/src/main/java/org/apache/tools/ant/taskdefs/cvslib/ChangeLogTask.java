package org.apache.tools.ant.taskdefs.cvslib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.AbstractCvsTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class ChangeLogTask extends AbstractCvsTask {
    private File destFile;
    private Date endDate;
    private String endTag;
    private File inputDir;
    private Date startDate;
    private String startTag;
    private File usersFile;
    private Vector cvsUsers = new Vector();
    private boolean remote = false;
    private final Vector filesets = new Vector();

    public void setDir(File file) {
        this.inputDir = file;
    }

    public void setDestfile(File file) {
        this.destFile = file;
    }

    public void setUsersfile(File file) {
        this.usersFile = file;
    }

    public void addUser(CvsUser cvsUser) {
        this.cvsUsers.addElement(cvsUser);
    }

    public void setStart(Date date) {
        this.startDate = date;
    }

    public void setEnd(Date date) {
        this.endDate = date;
    }

    public void setDaysinpast(int i) {
        setStart(new Date(System.currentTimeMillis() - ((((i * 24) * 60) * 60) * 1000)));
    }

    public void setRemote(boolean z) {
        this.remote = z;
    }

    public void setStartTag(String str) {
        this.startTag = str;
    }

    public void setEndTag(String str) {
        this.endTag = str;
    }

    public void addFileset(FileSet fileSet) {
        this.filesets.addElement(fileSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00f7 A[Catch: all -> 0x016d, TryCatch #1 {all -> 0x016d, blocks: (B:3:0x0002, B:5:0x0017, B:6:0x0030, B:8:0x0034, B:10:0x003f, B:12:0x0073, B:13:0x0079, B:14:0x008d, B:16:0x0091, B:19:0x0096, B:21:0x009a, B:22:0x00c1, B:25:0x00c8, B:26:0x00ca, B:29:0x00d1, B:30:0x00d3, B:31:0x00ef, B:33:0x00f7, B:34:0x00fd, B:36:0x0103, B:37:0x0116, B:39:0x0119, B:40:0x0121, B:42:0x0148, B:44:0x014e, B:45:0x0151, B:41:0x0145), top: B:51:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x014e A[Catch: all -> 0x016d, TryCatch #1 {all -> 0x016d, blocks: (B:3:0x0002, B:5:0x0017, B:6:0x0030, B:8:0x0034, B:10:0x003f, B:12:0x0073, B:13:0x0079, B:14:0x008d, B:16:0x0091, B:19:0x0096, B:21:0x009a, B:22:0x00c1, B:25:0x00c8, B:26:0x00ca, B:29:0x00d1, B:30:0x00d3, B:31:0x00ef, B:33:0x00f7, B:34:0x00fd, B:36:0x0103, B:37:0x0116, B:39:0x0119, B:40:0x0121, B:42:0x0148, B:44:0x014e, B:45:0x0151, B:41:0x0145), top: B:51:0x0002 }] */
    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask, org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.cvslib.ChangeLogTask.execute():void");
    }

    private void validate() throws BuildException {
        if (this.inputDir == null) {
            this.inputDir = getProject().getBaseDir();
        }
        if (this.destFile == null) {
            throw new BuildException("Destfile must be set.");
        } else if (this.inputDir.exists()) {
            File file = this.usersFile;
            if (file != null && !file.exists()) {
                throw new BuildException("Cannot find user lookup list " + this.usersFile.getAbsolutePath());
            } else if (this.startTag != null || this.endTag != null) {
                if (this.startDate != null || this.endDate != null) {
                    throw new BuildException("Specify either a tag or date range, not both");
                }
            }
        } else {
            throw new BuildException("Cannot find base dir " + this.inputDir.getAbsolutePath());
        }
    }

    private void loadUserlist(Properties properties) throws BuildException {
        File file = this.usersFile;
        if (file != null) {
            try {
                properties.load(new FileInputStream(file));
            } catch (IOException e) {
                throw new BuildException(e.toString(), e);
            }
        }
    }

    private CVSEntry[] filterEntrySet(CVSEntry[] cVSEntryArr) {
        Date date;
        Date date2;
        Vector vector = new Vector();
        for (CVSEntry cVSEntry : cVSEntryArr) {
            Date date3 = cVSEntry.getDate();
            if (date3 != null && (((date = this.startDate) == null || !date.after(date3)) && ((date2 = this.endDate) == null || !date2.before(date3)))) {
                vector.addElement(cVSEntry);
            }
        }
        CVSEntry[] cVSEntryArr2 = new CVSEntry[vector.size()];
        vector.copyInto(cVSEntryArr2);
        return cVSEntryArr2;
    }

    private void replaceAuthorIdWithName(Properties properties, CVSEntry[] cVSEntryArr) {
        for (CVSEntry cVSEntry : cVSEntryArr) {
            if (properties.containsKey(cVSEntry.getAuthor())) {
                cVSEntry.setAuthor(properties.getProperty(cVSEntry.getAuthor()));
            }
        }
    }

    private void writeChangeLog(CVSEntry[] cVSEntryArr) throws BuildException {
        Throwable th;
        FileOutputStream fileOutputStream;
        UnsupportedEncodingException e;
        IOException e2;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(this.destFile);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (UnsupportedEncodingException e3) {
            e = e3;
        } catch (IOException e4) {
            e2 = e4;
        }
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            new ChangeLogWriter().printChangeLog(printWriter, cVSEntryArr);
            if (!printWriter.checkError()) {
                FileUtils.close(fileOutputStream);
                return;
            }
            throw new IOException("Encountered an error writing changelog");
        } catch (UnsupportedEncodingException e5) {
            e = e5;
            fileOutputStream2 = fileOutputStream;
            getProject().log(e.toString(), 0);
            FileUtils.close(fileOutputStream2);
        } catch (IOException e6) {
            e2 = e6;
            throw new BuildException(e2.toString(), e2);
        } catch (Throwable th3) {
            th = th3;
            FileUtils.close(fileOutputStream);
            throw th;
        }
    }
}
