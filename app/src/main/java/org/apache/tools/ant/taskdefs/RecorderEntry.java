package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.SubBuildListener;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class RecorderEntry implements BuildLogger, SubBuildListener {
    private String filename;
    private Project project;
    private long targetStartTime;
    private boolean record = true;
    private int loglevel = 2;
    private PrintStream out = null;
    private boolean emacsMode = false;

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildStarted(BuildEvent buildEvent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RecorderEntry(String str) {
        this.filename = null;
        this.targetStartTime = 0L;
        this.targetStartTime = System.currentTimeMillis();
        this.filename = str;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setRecordState(Boolean bool) {
        if (bool != null) {
            flush();
            this.record = bool.booleanValue();
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent buildEvent) {
        log("> BUILD STARTED", 4);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent buildEvent) {
        log("< BUILD FINISHED", 4);
        if (this.record && this.out != null) {
            Throwable exception = buildEvent.getException();
            if (exception == null) {
                PrintStream printStream = this.out;
                printStream.println(StringUtils.LINE_SEP + "BUILD SUCCESSFUL");
            } else {
                PrintStream printStream2 = this.out;
                printStream2.println(StringUtils.LINE_SEP + "BUILD FAILED" + StringUtils.LINE_SEP);
                exception.printStackTrace(this.out);
            }
        }
        cleanup();
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildFinished(BuildEvent buildEvent) {
        if (buildEvent.getProject() == this.project) {
            cleanup();
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent buildEvent) {
        log(">> TARGET STARTED -- " + buildEvent.getTarget(), 4);
        log(StringUtils.LINE_SEP + buildEvent.getTarget().getName() + ":", 2);
        this.targetStartTime = System.currentTimeMillis();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent buildEvent) {
        log("<< TARGET FINISHED -- " + buildEvent.getTarget(), 4);
        String formatTime = formatTime(System.currentTimeMillis() - this.targetStartTime);
        log(buildEvent.getTarget() + ":  duration " + formatTime, 3);
        flush();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent buildEvent) {
        log(">>> TASK STARTED -- " + buildEvent.getTask(), 4);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent buildEvent) {
        log("<<< TASK FINISHED -- " + buildEvent.getTask(), 4);
        flush();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent buildEvent) {
        log("--- MESSAGE LOGGED", 4);
        StringBuffer stringBuffer = new StringBuffer();
        if (buildEvent.getTask() != null) {
            String taskName = buildEvent.getTask().getTaskName();
            if (!this.emacsMode) {
                String str = "[" + taskName + "] ";
                int length = 12 - str.length();
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(ExpandableTextView.f6958c);
                }
                stringBuffer.append(str);
            }
        }
        stringBuffer.append(buildEvent.getMessage());
        log(stringBuffer.toString(), buildEvent.getPriority());
    }

    private void log(String str, int i) {
        PrintStream printStream;
        if (this.record && i <= this.loglevel && (printStream = this.out) != null) {
            printStream.println(str);
        }
    }

    private void flush() {
        PrintStream printStream;
        if (this.record && (printStream = this.out) != null) {
            printStream.flush();
        }
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setMessageOutputLevel(int i) {
        if (i >= 0 && i <= 4) {
            this.loglevel = i;
        }
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setOutputPrintStream(PrintStream printStream) {
        closeFile();
        this.out = printStream;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setEmacsMode(boolean z) {
        this.emacsMode = z;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setErrorPrintStream(PrintStream printStream) {
        setOutputPrintStream(printStream);
    }

    private static String formatTime(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 60;
        if (j3 > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(Long.toString(j3));
            sb.append(" minute");
            sb.append(j3 == 1 ? ExpandableTextView.f6958c : "s ");
            long j4 = j2 % 60;
            sb.append(Long.toString(j4));
            sb.append(" second");
            sb.append(j4 == 1 ? "" : "s");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Long.toString(j2));
        sb2.append(" second");
        sb2.append(j2 % 60 == 1 ? "" : "s");
        return sb2.toString();
    }

    public void setProject(Project project) {
        this.project = project;
        if (project != null) {
            project.addBuildListener(this);
        }
    }

    public Project getProject() {
        return this.project;
    }

    public void cleanup() {
        closeFile();
        Project project = this.project;
        if (project != null) {
            project.removeBuildListener(this);
        }
        this.project = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void openFile(boolean z) throws BuildException {
        openFileImpl(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeFile() {
        PrintStream printStream = this.out;
        if (printStream != null) {
            printStream.close();
            this.out = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reopenFile() throws BuildException {
        openFileImpl(true);
    }

    private void openFileImpl(boolean z) throws BuildException {
        if (this.out == null) {
            try {
                this.out = new PrintStream(new FileOutputStream(this.filename, z));
            } catch (IOException e) {
                throw new BuildException("Problems opening file using a recorder entry", e);
            }
        }
    }
}
