package org.apache.tools.ant;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import org.apache.tools.ant.util.DateUtils;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class DefaultLogger implements BuildLogger {
    public static final int LEFT_COLUMN_SIZE = 12;
    protected static final String lSep = StringUtils.LINE_SEP;
    protected PrintStream err;
    protected PrintStream out;
    protected int msgOutputLevel = 0;
    private long startTime = System.currentTimeMillis();
    protected boolean emacsMode = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public String getBuildFailedMessage() {
        return "BUILD FAILED";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getBuildSuccessfulMessage() {
        return "BUILD SUCCESSFUL";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void log(String str) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setMessageOutputLevel(int i) {
        this.msgOutputLevel = i;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setOutputPrintStream(PrintStream printStream) {
        this.out = new PrintStream((OutputStream) printStream, true);
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setErrorPrintStream(PrintStream printStream) {
        this.err = new PrintStream((OutputStream) printStream, true);
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setEmacsMode(boolean z) {
        this.emacsMode = z;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent buildEvent) {
        this.startTime = System.currentTimeMillis();
    }

    static void throwableMessage(StringBuffer stringBuffer, Throwable th, boolean z) {
        boolean z2;
        Throwable cause;
        while (true) {
            z2 = th instanceof BuildException;
            if (!z2 || (cause = th.getCause()) == null) {
                break;
            }
            String th2 = th.toString();
            String th3 = cause.toString();
            if (!th2.endsWith(th3)) {
                break;
            }
            stringBuffer.append(th2.substring(0, th2.length() - th3.length()));
            th = cause;
        }
        if (z || !z2) {
            stringBuffer.append(StringUtils.getStackTrace(th));
            return;
        }
        stringBuffer.append(th);
        stringBuffer.append(lSep);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent buildEvent) {
        Throwable exception = buildEvent.getException();
        StringBuffer stringBuffer = new StringBuffer();
        if (exception == null) {
            stringBuffer.append(StringUtils.LINE_SEP);
            stringBuffer.append(getBuildSuccessfulMessage());
        } else {
            stringBuffer.append(StringUtils.LINE_SEP);
            stringBuffer.append(getBuildFailedMessage());
            stringBuffer.append(StringUtils.LINE_SEP);
            throwableMessage(stringBuffer, exception, 3 <= this.msgOutputLevel);
        }
        stringBuffer.append(StringUtils.LINE_SEP);
        stringBuffer.append("Total time: ");
        stringBuffer.append(formatTime(System.currentTimeMillis() - this.startTime));
        String stringBuffer2 = stringBuffer.toString();
        if (exception == null) {
            printMessage(stringBuffer2, this.out, 3);
        } else {
            printMessage(stringBuffer2, this.err, 0);
        }
        log(stringBuffer2);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent buildEvent) {
        if (2 <= this.msgOutputLevel && !buildEvent.getTarget().getName().equals("")) {
            String str = StringUtils.LINE_SEP + buildEvent.getTarget().getName() + ":";
            printMessage(str, this.out, buildEvent.getPriority());
            log(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006b, code lost:
        r1.append(r2);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c8  */
    /* JADX WARN: Type inference failed for: r3v19 */
    @Override // org.apache.tools.ant.BuildListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void messageLogged(org.apache.tools.ant.BuildEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getPriority()
            int r1 = r8.msgOutputLevel
            if (r0 > r1) goto L_0x00d0
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            org.apache.tools.ant.Task r2 = r9.getTask()
            if (r2 == 0) goto L_0x00a3
            boolean r2 = r8.emacsMode
            if (r2 != 0) goto L_0x00a3
            org.apache.tools.ant.Task r2 = r9.getTask()
            java.lang.String r2 = r2.getTaskName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "["
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = "] "
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            int r3 = r2.length()
            int r3 = 12 - r3
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            r5 = 0
            r6 = 0
        L_0x0042:
            if (r6 >= r3) goto L_0x004c
            java.lang.String r7 = " "
            r4.append(r7)
            int r6 = r6 + 1
            goto L_0x0042
        L_0x004c:
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r3 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: all -> 0x008b, IOException -> 0x008d
            java.io.StringReader r6 = new java.io.StringReader     // Catch: all -> 0x008b, IOException -> 0x008d
            java.lang.String r7 = r9.getMessage()     // Catch: all -> 0x008b, IOException -> 0x008d
            r6.<init>(r7)     // Catch: all -> 0x008b, IOException -> 0x008d
            r4.<init>(r6)     // Catch: all -> 0x008b, IOException -> 0x008d
            java.lang.String r3 = r4.readLine()     // Catch: all -> 0x0086, IOException -> 0x0089
            r6 = 1
        L_0x0067:
            if (r6 == 0) goto L_0x006f
            if (r3 != 0) goto L_0x0074
            r1.append(r2)     // Catch: all -> 0x0086, IOException -> 0x0089
            goto L_0x0080
        L_0x006f:
            java.lang.String r6 = org.apache.tools.ant.util.StringUtils.LINE_SEP     // Catch: all -> 0x0086, IOException -> 0x0089
            r1.append(r6)     // Catch: all -> 0x0086, IOException -> 0x0089
        L_0x0074:
            r1.append(r2)     // Catch: all -> 0x0086, IOException -> 0x0089
            r1.append(r3)     // Catch: all -> 0x0086, IOException -> 0x0089
            java.lang.String r3 = r4.readLine()     // Catch: all -> 0x0086, IOException -> 0x0089
            if (r3 != 0) goto L_0x0084
        L_0x0080:
            org.apache.tools.ant.util.FileUtils.close(r4)
            goto L_0x00aa
        L_0x0084:
            r6 = 0
            goto L_0x0067
        L_0x0086:
            r9 = move-exception
            r3 = r4
            goto L_0x009d
        L_0x0089:
            r3 = r4
            goto L_0x008d
        L_0x008b:
            r9 = move-exception
            goto L_0x009d
        L_0x008d:
            r1.append(r2)     // Catch: all -> 0x008b
            java.lang.String r2 = r9.getMessage()     // Catch: all -> 0x008b
            r1.append(r2)     // Catch: all -> 0x008b
            if (r3 == 0) goto L_0x00aa
            org.apache.tools.ant.util.FileUtils.close(r3)
            goto L_0x00aa
        L_0x009d:
            if (r3 == 0) goto L_0x00a2
            org.apache.tools.ant.util.FileUtils.close(r3)
        L_0x00a2:
            throw r9
        L_0x00a3:
            java.lang.String r2 = r9.getMessage()
            r1.append(r2)
        L_0x00aa:
            java.lang.Throwable r9 = r9.getException()
            r2 = 4
            int r3 = r8.msgOutputLevel
            if (r2 > r3) goto L_0x00bc
            if (r9 == 0) goto L_0x00bc
            java.lang.String r9 = org.apache.tools.ant.util.StringUtils.getStackTrace(r9)
            r1.append(r9)
        L_0x00bc:
            java.lang.String r9 = r1.toString()
            if (r0 == 0) goto L_0x00c8
            java.io.PrintStream r1 = r8.out
            r8.printMessage(r9, r1, r0)
            goto L_0x00cd
        L_0x00c8:
            java.io.PrintStream r1 = r8.err
            r8.printMessage(r9, r1, r0)
        L_0x00cd:
            r8.log(r9)
        L_0x00d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.DefaultLogger.messageLogged(org.apache.tools.ant.BuildEvent):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String formatTime(long j) {
        return DateUtils.formatElapsedTime(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void printMessage(String str, PrintStream printStream, int i) {
        printStream.println(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTimestamp() {
        return DateFormat.getDateTimeInstance(3, 3).format(new Date(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String extractProjectName(BuildEvent buildEvent) {
        Project project = buildEvent.getProject();
        if (project != null) {
            return project.getName();
        }
        return null;
    }
}
