package org.apache.tools.ant.taskdefs.optional;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.LogOutputStream;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.taskdefs.StreamPumper;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class Cab extends MatchingTask {
    private static final int DEFAULT_RESULT = -99;
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File baseDir;
    private File cabFile;
    private String cmdOptions;
    private Vector filesets = new Vector();
    private boolean doCompress = true;
    private boolean doVerbose = false;
    protected String archiveType = "cab";

    public void setCabfile(File file) {
        this.cabFile = file;
    }

    public void setBasedir(File file) {
        this.baseDir = file;
    }

    public void setCompress(boolean z) {
        this.doCompress = z;
    }

    public void setVerbose(boolean z) {
        this.doVerbose = z;
    }

    public void setOptions(String str) {
        this.cmdOptions = str;
    }

    public void addFileset(FileSet fileSet) {
        if (this.filesets.size() <= 0) {
            this.filesets.addElement(fileSet);
            return;
        }
        throw new BuildException("Only one nested fileset allowed");
    }

    protected void checkConfiguration() throws BuildException {
        if (this.baseDir == null && this.filesets.size() == 0) {
            throw new BuildException("basedir attribute or one nested fileset is required!", getLocation());
        }
        File file = this.baseDir;
        if (file != null && !file.exists()) {
            throw new BuildException("basedir does not exist!", getLocation());
        } else if (this.baseDir != null && this.filesets.size() > 0) {
            throw new BuildException("Both basedir attribute and a nested fileset is not allowed");
        } else if (this.cabFile == null) {
            throw new BuildException("cabfile attribute must be set!", getLocation());
        }
    }

    protected ExecTask createExec() throws BuildException {
        return new ExecTask(this);
    }

    protected boolean isUpToDate(Vector vector) {
        int size = vector.size();
        boolean z = true;
        for (int i = 0; i < size && z; i++) {
            if (FILE_UTILS.resolveFile(this.baseDir, vector.elementAt(i).toString()).lastModified() > this.cabFile.lastModified()) {
                z = false;
            }
        }
        return z;
    }

    protected File createListFile(Vector vector) throws IOException {
        Throwable th;
        File createTempFile = FILE_UTILS.createTempFile("ant", "", null, true, true);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(createTempFile));
            try {
                int size = vector.size();
                for (int i = 0; i < size; i++) {
                    bufferedWriter.write(Typography.f21049a + vector.elementAt(i).toString() + Typography.f21049a);
                    bufferedWriter.newLine();
                }
                FileUtils.close(bufferedWriter);
                return createTempFile;
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close(bufferedWriter);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    protected void appendFiles(Vector vector, DirectoryScanner directoryScanner) {
        for (String str : directoryScanner.getIncludedFiles()) {
            vector.addElement(str);
        }
    }

    protected Vector getFileList() throws BuildException {
        Vector vector = new Vector();
        File file = this.baseDir;
        if (file != null) {
            appendFiles(vector, super.getDirectoryScanner(file));
        } else {
            FileSet fileSet = (FileSet) this.filesets.elementAt(0);
            this.baseDir = fileSet.getDir();
            appendFiles(vector, fileSet.getDirectoryScanner(getProject()));
        }
        return vector;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        checkConfiguration();
        Vector fileList = getFileList();
        if (!isUpToDate(fileList)) {
            log("Building " + this.archiveType + ": " + this.cabFile.getAbsolutePath());
            File file = null;
            if (!C3209Os.isFamily(C3209Os.FAMILY_WINDOWS)) {
                log("Using listcab/libcabinet", 3);
                StringBuffer stringBuffer = new StringBuffer();
                Enumeration elements = fileList.elements();
                while (elements.hasMoreElements()) {
                    stringBuffer.append(elements.nextElement());
                    stringBuffer.append("\n");
                }
                stringBuffer.append("\n");
                stringBuffer.append(this.cabFile.getAbsolutePath());
                stringBuffer.append("\n");
                try {
                    Process launch = Execute.launch(getProject(), new String[]{"listcab"}, null, this.baseDir != null ? this.baseDir : getProject().getBaseDir(), true);
                    OutputStream outputStream = launch.getOutputStream();
                    LogOutputStream logOutputStream = new LogOutputStream((Task) this, 3);
                    LogOutputStream logOutputStream2 = new LogOutputStream((Task) this, 0);
                    StreamPumper streamPumper = new StreamPumper(launch.getInputStream(), logOutputStream);
                    StreamPumper streamPumper2 = new StreamPumper(launch.getErrorStream(), logOutputStream2);
                    new Thread(streamPumper).start();
                    new Thread(streamPumper2).start();
                    outputStream.write(stringBuffer.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
                    int i = DEFAULT_RESULT;
                    try {
                        i = launch.waitFor();
                        streamPumper.waitFor();
                        logOutputStream.close();
                        streamPumper2.waitFor();
                        logOutputStream2.close();
                    } catch (InterruptedException e) {
                        log("Thread interrupted: " + e);
                    }
                    if (Execute.isFailure(i)) {
                        log("Error executing listcab; error code: " + i);
                    }
                } catch (IOException e2) {
                    throw new BuildException("Problem creating " + this.cabFile + ExpandableTextView.f6958c + e2.getMessage(), getLocation());
                }
            } else {
                try {
                    File createListFile = createListFile(fileList);
                    ExecTask createExec = createExec();
                    createExec.setFailonerror(true);
                    createExec.setDir(this.baseDir);
                    if (!this.doVerbose) {
                        file = FILE_UTILS.createTempFile("ant", "", null, true, true);
                        createExec.setOutput(file);
                    }
                    createExec.setExecutable("cabarc");
                    createExec.createArg().setValue("-r");
                    createExec.createArg().setValue("-p");
                    if (!this.doCompress) {
                        createExec.createArg().setValue("-m");
                        createExec.createArg().setValue("none");
                    }
                    if (this.cmdOptions != null) {
                        createExec.createArg().setLine(this.cmdOptions);
                    }
                    createExec.createArg().setValue("n");
                    createExec.createArg().setFile(this.cabFile);
                    Commandline.Argument createArg = createExec.createArg();
                    createArg.setValue("@" + createListFile.getAbsolutePath());
                    createExec.execute();
                    if (file != null) {
                        file.delete();
                    }
                    createListFile.delete();
                } catch (IOException e3) {
                    throw new BuildException("Problem creating " + this.cabFile + ExpandableTextView.f6958c + e3.getMessage(), getLocation());
                }
            }
        }
    }
}
