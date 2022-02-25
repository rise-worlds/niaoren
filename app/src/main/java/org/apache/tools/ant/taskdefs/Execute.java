package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.taskdefs.launcher.CommandLauncher;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class Execute {
    public static final int INVALID = Integer.MAX_VALUE;
    private static final int ONE_SECOND = 1000;
    private static boolean environmentCaseInSensitive;
    private String[] cmdl;
    private String[] env;
    private int exitValue;
    private boolean newEnvironment;
    private Project project;
    private ExecuteStreamHandler streamHandler;
    private boolean useVMLauncher;
    private final ExecuteWatchdog watchdog;
    private File workingDirectory;
    private static String antWorkingDirectory = System.getProperty("user.dir");
    private static Map<String, String> procEnvironment = null;
    private static ProcessDestroyer processDestroyer = new ProcessDestroyer();

    @Deprecated
    public void setSpawn(boolean z) {
    }

    static {
        environmentCaseInSensitive = false;
        if (C3209Os.isFamily(C3209Os.FAMILY_WINDOWS)) {
            environmentCaseInSensitive = true;
        }
    }

    public static synchronized Map<String, String> getEnvironmentVariables() {
        BufferedReader bufferedReader;
        synchronized (Execute.class) {
            if (procEnvironment != null) {
                return procEnvironment;
            }
            if (!C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
                try {
                    procEnvironment = System.getenv();
                    return procEnvironment;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            procEnvironment = new LinkedHashMap();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Execute execute = new Execute(new PumpStreamHandler(byteArrayOutputStream));
                execute.setCommandline(getProcEnvCommand());
                execute.setNewenvironment(true);
                execute.execute();
                bufferedReader = new BufferedReader(new StringReader(toString(byteArrayOutputStream)));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
                procEnvironment = getVMSLogicals(bufferedReader);
                return procEnvironment;
            }
            String str = null;
            String str2 = StringUtils.LINE_SEP;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.indexOf(61) != -1) {
                    if (str != null) {
                        int indexOf = str.indexOf(SimpleComparison.f23609c);
                        procEnvironment.put(str.substring(0, indexOf), str.substring(indexOf + 1));
                    }
                    str = readLine;
                } else if (str == null) {
                    str = str2 + readLine;
                } else {
                    str = str + str2 + readLine;
                }
            }
            if (str != null) {
                int indexOf2 = str.indexOf(SimpleComparison.f23609c);
                procEnvironment.put(str.substring(0, indexOf2), str.substring(indexOf2 + 1));
            }
            return procEnvironment;
        }
    }

    @Deprecated
    public static synchronized Vector<String> getProcEnvironment() {
        Vector<String> vector;
        synchronized (Execute.class) {
            vector = new Vector<>();
            for (Map.Entry<String, String> entry : getEnvironmentVariables().entrySet()) {
                vector.add(entry.getKey() + SimpleComparison.f23609c + entry.getValue());
            }
        }
        return vector;
    }

    private static String[] getProcEnvCommand() {
        if (C3209Os.isFamily(C3209Os.FAMILY_OS2)) {
            return new String[]{"cmd", "/c", "set"};
        }
        if (C3209Os.isFamily(C3209Os.FAMILY_WINDOWS)) {
            if (C3209Os.isFamily(C3209Os.FAMILY_9X)) {
                return new String[]{"command.com", "/c", "set"};
            }
            return new String[]{"cmd", "/c", "set"};
        } else if (C3209Os.isFamily(C3209Os.FAMILY_ZOS) || C3209Os.isFamily(C3209Os.FAMILY_UNIX)) {
            String[] strArr = new String[1];
            if (new File("/bin/env").canRead()) {
                strArr[0] = "/bin/env";
            } else if (new File("/usr/bin/env").canRead()) {
                strArr[0] = "/usr/bin/env";
            } else {
                strArr[0] = "env";
            }
            return strArr;
        } else if (C3209Os.isFamily(C3209Os.FAMILY_NETWARE) || C3209Os.isFamily(C3209Os.FAMILY_OS400)) {
            return new String[]{"env"};
        } else {
            if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
                return new String[]{"show", "logical"};
            }
            return null;
        }
    }

    public static String toString(ByteArrayOutputStream byteArrayOutputStream) {
        if (C3209Os.isFamily(C3209Os.FAMILY_ZOS)) {
            return byteArrayOutputStream.toString("Cp1047");
        }
        if (C3209Os.isFamily(C3209Os.FAMILY_OS400)) {
            return byteArrayOutputStream.toString("Cp500");
        }
        return byteArrayOutputStream.toString();
    }

    public Execute() {
        this(new PumpStreamHandler(), null);
    }

    public Execute(ExecuteStreamHandler executeStreamHandler) {
        this(executeStreamHandler, null);
    }

    public Execute(ExecuteStreamHandler executeStreamHandler, ExecuteWatchdog executeWatchdog) {
        this.cmdl = null;
        this.env = null;
        this.exitValue = Integer.MAX_VALUE;
        this.workingDirectory = null;
        this.project = null;
        this.newEnvironment = false;
        this.useVMLauncher = true;
        setStreamHandler(executeStreamHandler);
        this.watchdog = executeWatchdog;
        if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
            this.useVMLauncher = false;
        }
    }

    public void setStreamHandler(ExecuteStreamHandler executeStreamHandler) {
        this.streamHandler = executeStreamHandler;
    }

    public String[] getCommandline() {
        return this.cmdl;
    }

    public void setCommandline(String[] strArr) {
        this.cmdl = strArr;
    }

    public void setNewenvironment(boolean z) {
        this.newEnvironment = z;
    }

    public String[] getEnvironment() {
        return (this.env == null || this.newEnvironment) ? this.env : patchEnvironment();
    }

    public void setEnvironment(String[] strArr) {
        this.env = strArr;
    }

    public void setWorkingDirectory(File file) {
        if (file == null || file.getAbsolutePath().equals(antWorkingDirectory)) {
            file = null;
        }
        this.workingDirectory = file;
    }

    public File getWorkingDirectory() {
        File file = this.workingDirectory;
        return file == null ? new File(antWorkingDirectory) : file;
    }

    public void setAntRun(Project project) throws BuildException {
        this.project = project;
    }

    public void setVMLauncher(boolean z) {
        this.useVMLauncher = z;
    }

    public static Process launch(Project project, String[] strArr, String[] strArr2, File file, boolean z) throws IOException {
        if (file == null || file.exists()) {
            CommandLauncher vMLauncher = CommandLauncher.getVMLauncher(project);
            if (!z || vMLauncher == null) {
                vMLauncher = CommandLauncher.getShellLauncher(project);
            }
            return vMLauncher.exec(project, strArr, strArr2, file);
        }
        throw new BuildException(file + " doesn't exist.");
    }

    public int execute() throws IOException {
        File file = this.workingDirectory;
        if (file == null || file.exists()) {
            Process launch = launch(this.project, getCommandline(), getEnvironment(), this.workingDirectory, this.useVMLauncher);
            try {
                this.streamHandler.setProcessInputStream(launch.getOutputStream());
                this.streamHandler.setProcessOutputStream(launch.getInputStream());
                this.streamHandler.setProcessErrorStream(launch.getErrorStream());
                this.streamHandler.start();
                try {
                    try {
                        processDestroyer.add(launch);
                        if (this.watchdog != null) {
                            this.watchdog.start(launch);
                        }
                        waitFor(launch);
                        if (this.watchdog != null) {
                            this.watchdog.stop();
                        }
                        this.streamHandler.stop();
                        closeStreams(launch);
                        if (this.watchdog != null) {
                            this.watchdog.checkException();
                        }
                        return getExitValue();
                    } catch (ThreadDeath e) {
                        launch.destroy();
                        throw e;
                    }
                } finally {
                    processDestroyer.remove(launch);
                }
            } catch (IOException e2) {
                launch.destroy();
                throw e2;
            }
        } else {
            throw new BuildException(this.workingDirectory + " doesn't exist.");
        }
    }

    public void spawn() throws IOException {
        File file = this.workingDirectory;
        if (file == null || file.exists()) {
            Process launch = launch(this.project, getCommandline(), getEnvironment(), this.workingDirectory, this.useVMLauncher);
            if (C3209Os.isFamily(C3209Os.FAMILY_WINDOWS)) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException unused) {
                    this.project.log("interruption in the sleep after having spawned a process", 3);
                }
            }
            PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(new OutputStream() { // from class: org.apache.tools.ant.taskdefs.Execute.1
                @Override // java.io.OutputStream
                public void write(int i) throws IOException {
                }
            });
            pumpStreamHandler.setProcessErrorStream(launch.getErrorStream());
            pumpStreamHandler.setProcessOutputStream(launch.getInputStream());
            pumpStreamHandler.start();
            launch.getOutputStream().close();
            Project project = this.project;
            project.log("spawned process " + launch.toString(), 3);
            return;
        }
        throw new BuildException(this.workingDirectory + " doesn't exist.");
    }

    protected void waitFor(Process process) {
        try {
            process.waitFor();
            setExitValue(process.exitValue());
        } catch (InterruptedException unused) {
            process.destroy();
        }
    }

    protected void setExitValue(int i) {
        this.exitValue = i;
    }

    public int getExitValue() {
        return this.exitValue;
    }

    public static boolean isFailure(int i) {
        if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
            if (i % 2 == 0) {
                return true;
            }
        } else if (i != 0) {
            return true;
        }
        return false;
    }

    public boolean isFailure() {
        return isFailure(getExitValue());
    }

    public boolean killedProcess() {
        ExecuteWatchdog executeWatchdog = this.watchdog;
        return executeWatchdog != null && executeWatchdog.killedProcess();
    }

    private String[] patchEnvironment() {
        if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
            return this.env;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(getEnvironmentVariables());
        int i = 0;
        while (true) {
            String[] strArr = this.env;
            if (i >= strArr.length) {
                break;
            }
            String str = strArr[i];
            String substring = str.substring(0, str.indexOf(61));
            if (linkedHashMap.remove(substring) == null && environmentCaseInSensitive) {
                Iterator it = linkedHashMap.keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        String str2 = (String) it.next();
                        if (str2.toLowerCase().equals(substring.toLowerCase())) {
                            substring = str2;
                            break;
                        }
                    }
                }
            }
            linkedHashMap.put(substring, str.substring(substring.length() + 1));
            i++;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(((String) entry.getKey()) + SimpleComparison.f23609c + ((String) entry.getValue()));
        }
        return (String[]) arrayList.toArray(new String[linkedHashMap.size()]);
    }

    public static void runCommand(Task task, String[] strArr) throws BuildException {
        try {
            task.log(Commandline.describeCommand(strArr), 3);
            Execute execute = new Execute(new LogStreamHandler(task, 2, 0));
            execute.setAntRun(task.getProject());
            execute.setCommandline(strArr);
            int execute2 = execute.execute();
            if (isFailure(execute2)) {
                throw new BuildException(strArr[0] + " failed with return code " + execute2, task.getLocation());
            }
        } catch (IOException e) {
            throw new BuildException("Could not launch " + strArr[0] + ": " + e, task.getLocation());
        }
    }

    public static void closeStreams(Process process) {
        FileUtils.close(process.getInputStream());
        FileUtils.close(process.getOutputStream());
        FileUtils.close(process.getErrorStream());
    }

    private static Map<String, String> getVMSLogicals(BufferedReader bufferedReader) throws IOException {
        HashMap hashMap = new HashMap();
        String str = null;
        String str2 = null;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            } else if (readLine.startsWith("\t=")) {
                if (str != null) {
                    str2 = str2 + "," + readLine.substring(4, readLine.length() - 1);
                }
            } else if (readLine.startsWith("  \"")) {
                if (str != null) {
                    hashMap.put(str, str2);
                }
                int indexOf = readLine.indexOf(61);
                String substring = readLine.substring(3, indexOf - 2);
                if (hashMap.containsKey(substring)) {
                    str = null;
                } else {
                    str2 = readLine.substring(indexOf + 3, readLine.length() - 1);
                    str = substring;
                }
            }
        }
        if (str != null) {
            hashMap.put(str, str2);
        }
        return hashMap;
    }
}
