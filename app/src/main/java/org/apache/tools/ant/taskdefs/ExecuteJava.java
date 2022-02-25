package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Permissions;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.TimeoutObserver;
import org.apache.tools.ant.util.Watchdog;

/* loaded from: classes2.dex */
public class ExecuteJava implements Runnable, TimeoutObserver {
    private Commandline javaCommand = null;
    private Path classpath = null;
    private CommandlineJava.SysProperties sysProperties = null;
    private Permissions perm = null;
    private Method main = null;
    private Long timeout = null;
    private volatile Throwable caught = null;
    private volatile boolean timedOut = false;
    private Thread thread = null;

    public void setOutput(PrintStream printStream) {
    }

    public void setJavaCommand(Commandline commandline) {
        this.javaCommand = commandline;
    }

    public void setClasspath(Path path) {
        this.classpath = path;
    }

    public void setSystemProperties(CommandlineJava.SysProperties sysProperties) {
        this.sysProperties = sysProperties;
    }

    public void setPermissions(Permissions permissions) {
        this.perm = permissions;
    }

    public void setTimeout(Long l) {
        this.timeout = l;
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0155  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(org.apache.tools.ant.Project r11) throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.ExecuteJava.execute(org.apache.tools.ant.Project):void");
    }

    @Override // java.lang.Runnable
    public void run() {
        Object[] objArr = {this.javaCommand.getArguments()};
        try {
            try {
                if (this.perm != null) {
                    this.perm.setSecurityManager();
                }
                this.main.invoke(null, objArr);
                Permissions permissions = this.perm;
                if (permissions != null) {
                    permissions.restoreSecurityManager();
                }
                synchronized (this) {
                    notifyAll();
                }
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException();
                if (!(targetException instanceof InterruptedException)) {
                    this.caught = targetException;
                }
                Permissions permissions2 = this.perm;
                if (permissions2 != null) {
                    permissions2.restoreSecurityManager();
                }
                synchronized (this) {
                    notifyAll();
                }
            } catch (Throwable th) {
                this.caught = th;
                Permissions permissions3 = this.perm;
                if (permissions3 != null) {
                    permissions3.restoreSecurityManager();
                }
                synchronized (this) {
                    notifyAll();
                }
            }
        } catch (Throwable th2) {
            Permissions permissions4 = this.perm;
            if (permissions4 != null) {
                permissions4.restoreSecurityManager();
            }
            synchronized (this) {
                notifyAll();
                throw th2;
            }
        }
    }

    @Override // org.apache.tools.ant.util.TimeoutObserver
    public synchronized void timeoutOccured(Watchdog watchdog) {
        if (this.thread != null) {
            this.timedOut = true;
            this.thread.interrupt();
        }
        notifyAll();
    }

    public synchronized boolean killedProcess() {
        return this.timedOut;
    }

    public int fork(ProjectComponent projectComponent) throws BuildException {
        CommandlineJava commandlineJava = new CommandlineJava();
        commandlineJava.setClassname(this.javaCommand.getExecutable());
        for (String str : this.javaCommand.getArguments()) {
            commandlineJava.createArgument().setValue(str);
        }
        if (this.classpath != null) {
            commandlineJava.createClasspath(projectComponent.getProject()).append(this.classpath);
        }
        CommandlineJava.SysProperties sysProperties = this.sysProperties;
        if (sysProperties != null) {
            commandlineJava.addSysproperties(sysProperties);
        }
        Redirector redirector = new Redirector(projectComponent);
        ExecuteStreamHandler createHandler = redirector.createHandler();
        Long l = this.timeout;
        Execute execute = new Execute(createHandler, l == null ? null : new ExecuteWatchdog(l.longValue()));
        execute.setAntRun(projectComponent.getProject());
        if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
            setupCommandLineForVMS(execute, commandlineJava.getCommandline());
        } else {
            execute.setCommandline(commandlineJava.getCommandline());
        }
        try {
            try {
                int execute2 = execute.execute();
                redirector.complete();
                return execute2;
            } catch (IOException e) {
                throw new BuildException(e);
            }
        } finally {
            this.timedOut = execute.killedProcess();
        }
    }

    public static void setupCommandLineForVMS(Execute execute, String[] strArr) {
        execute.setVMLauncher(true);
        try {
            String[] strArr2 = new String[strArr.length - 1];
            System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
            File createVmsJavaOptionFile = JavaEnvUtils.createVmsJavaOptionFile(strArr2);
            createVmsJavaOptionFile.deleteOnExit();
            execute.setCommandline(new String[]{strArr[0], MSVSSConstants.FLAG_VERSION, createVmsJavaOptionFile.getPath()});
        } catch (IOException unused) {
            throw new BuildException("Failed to create a temporary file for \"-V\" switch");
        }
    }
}
