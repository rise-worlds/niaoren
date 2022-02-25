package org.apache.tools.ant.taskdefs.optional.pvcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.ExecuteStreamHandler;
import org.apache.tools.ant.taskdefs.LogOutputStream;
import org.apache.tools.ant.taskdefs.LogStreamHandler;
import org.apache.tools.ant.taskdefs.PumpStreamHandler;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class Pvcs extends Task {
    private static final String GET_EXE = "get";
    private static final String PCLI_EXE = "pcli";
    private static final int POS_1 = 1;
    private static final int POS_2 = 2;
    private static final int POS_3 = 3;
    private String config;
    private String revision;
    private String userId;
    private String pvcsProject = null;
    private Vector pvcsProjects = new Vector();
    private String workspace = null;
    private String repository = null;
    private String pvcsbin = null;
    private String force = null;
    private String promotiongroup = null;
    private String label = null;
    private boolean ignorerc = false;
    private boolean updateOnly = false;
    private String lineStart = "\"P:";
    private String filenameFormat = "{0}-arc({1})";

    protected int runCmd(Commandline commandline, ExecuteStreamHandler executeStreamHandler) {
        try {
            Project project = getProject();
            Execute execute = new Execute(executeStreamHandler);
            execute.setAntRun(project);
            execute.setWorkingDirectory(project.getBaseDir());
            execute.setCommandline(commandline.getCommandline());
            return execute.execute();
        } catch (IOException e) {
            throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e.getMessage(), getLocation());
        }
    }

    private String getExecutable(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (getPvcsbin() != null) {
            if (this.pvcsbin.endsWith(File.separator)) {
                stringBuffer.append(this.pvcsbin);
            } else {
                stringBuffer.append(this.pvcsbin);
                stringBuffer.append(File.separator);
            }
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file;
        Throwable th;
        FileNotFoundException e;
        IOException e2;
        ParseException e3;
        Random random;
        String str = this.repository;
        if (str == null || str.trim().equals("")) {
            throw new BuildException("Required argument repository not specified");
        }
        Commandline commandline = new Commandline();
        commandline.setExecutable(getExecutable(PCLI_EXE));
        commandline.createArgument().setValue("lvf");
        commandline.createArgument().setValue("-z");
        commandline.createArgument().setValue("-aw");
        if (getWorkspace() != null) {
            commandline.createArgument().setValue("-sp" + getWorkspace());
        }
        Commandline.Argument createArgument = commandline.createArgument();
        StringBuilder sb = new StringBuilder();
        sb.append("-pr");
        String repository = getRepository();
        sb.append(repository);
        createArgument.setValue(sb.toString());
        String userId = getUserId();
        File file2 = repository;
        if (userId != null) {
            Commandline.Argument createArgument2 = commandline.createArgument();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("-id");
            sb2.append(userId);
            createArgument2.setValue(sb2.toString());
            file2 = sb2;
        }
        if (getPvcsproject() == null && getPvcsprojects().isEmpty()) {
            this.pvcsProject = "/";
        }
        if (getPvcsproject() != null) {
            commandline.createArgument().setValue(getPvcsproject());
        }
        if (!getPvcsprojects().isEmpty()) {
            Enumeration elements = getPvcsprojects().elements();
            file2 = file2;
            while (elements.hasMoreElements()) {
                String name = ((PvcsProject) elements.nextElement()).getName();
                if (name == null || name.trim().equals("")) {
                    throw new BuildException("name is a required attribute of pvcsproject");
                }
                Commandline.Argument createArgument3 = commandline.createArgument();
                createArgument3.setValue(name);
                file2 = createArgument3;
            }
        }
        try {
            try {
                random = new Random(System.currentTimeMillis());
                file2 = new File("pvcs_ant_" + random.nextLong() + ".log");
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                File file3 = new File("pvcs_ant_" + random.nextLong() + ".log");
                try {
                    log(commandline.describeCommand(), 3);
                    try {
                        int runCmd = runCmd(commandline, new PumpStreamHandler(fileOutputStream, new LogOutputStream((Task) this, 1)));
                        FileUtils.close(fileOutputStream);
                        if (Execute.isFailure(runCmd) && !this.ignorerc) {
                            throw new BuildException("Failed executing: " + commandline.toString(), getLocation());
                        }
                        if (file2.exists()) {
                            log("Creating folders", 2);
                            createFolders(file2);
                            massagePCLI(file2, file3);
                            commandline.clearArgs();
                            commandline.setExecutable(getExecutable(GET_EXE));
                            if (getConfig() != null && getConfig().length() > 0) {
                                commandline.createArgument().setValue("-c" + getConfig());
                            }
                            if (getForce() == null || !getForce().equals("yes")) {
                                commandline.createArgument().setValue(MSVSSConstants.VALUE_NO);
                            } else {
                                commandline.createArgument().setValue("-Y");
                            }
                            if (getPromotiongroup() != null) {
                                commandline.createArgument().setValue("-G" + getPromotiongroup());
                            } else if (getLabel() != null) {
                                commandline.createArgument().setValue("-v" + getLabel());
                            } else if (getRevision() != null) {
                                commandline.createArgument().setValue("-r" + getRevision());
                            }
                            if (this.updateOnly) {
                                commandline.createArgument().setValue(MSVSSConstants.FLAG_USER);
                            }
                            commandline.createArgument().setValue("@" + file3.getAbsolutePath());
                            log("Getting files", 2);
                            log("Executing " + commandline.toString(), 3);
                            int runCmd2 = runCmd(commandline, new LogStreamHandler((Task) this, 2, 1));
                            if (runCmd2 != 0 && !this.ignorerc) {
                                throw new BuildException("Failed executing: " + commandline.toString() + ". Return code was " + runCmd2, getLocation());
                            }
                            file2.delete();
                            file3.delete();
                            return;
                        }
                        throw new BuildException("Communication between ant and pvcs failed. No output generated from executing PVCS commandline interface \"pcli\" and \"get\"");
                    } catch (Throwable th3) {
                        FileUtils.close(fileOutputStream);
                        throw th3;
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e.getMessage(), getLocation());
                } catch (IOException e5) {
                    e2 = e5;
                    throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e2.getMessage(), getLocation());
                } catch (ParseException e6) {
                    e3 = e6;
                    throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e3.getMessage(), getLocation());
                }
            } catch (FileNotFoundException e7) {
                e = e7;
                throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e.getMessage(), getLocation());
            } catch (IOException e8) {
                e2 = e8;
                throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e2.getMessage(), getLocation());
            } catch (ParseException e9) {
                e3 = e9;
                throw new BuildException("Failed executing: " + commandline.toString() + ". Exception: " + e3.getMessage(), getLocation());
            } catch (Throwable th4) {
                th = th4;
                file = null;
                if (file2 != null) {
                    file2.delete();
                }
                if (file != null) {
                    file.delete();
                }
                throw th;
            }
        } catch (FileNotFoundException e10) {
            e = e10;
        } catch (IOException e11) {
            e2 = e11;
        } catch (ParseException e12) {
            e3 = e12;
        } catch (Throwable th5) {
            th = th5;
            file2 = null;
            file = null;
        }
    }

    private void createFolders(File file) throws IOException, ParseException {
        Throwable th;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                MessageFormat messageFormat = new MessageFormat(getFilenameFormat());
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    log("Considering \"" + readLine + "\"", 3);
                    if (!readLine.startsWith(BasicHeaderValueFormatter.UNSAFE_CHARS) && !readLine.startsWith("\"/") && (readLine.length() <= 3 || !readLine.startsWith("\"") || !Character.isLetter(readLine.charAt(1)) || !String.valueOf(readLine.charAt(2)).equals(":") || !String.valueOf(readLine.charAt(3)).equals("\\"))) {
                        log("Skipped \"" + readLine + "\"", 3);
                    }
                    String str = (String) messageFormat.parse(readLine)[1];
                    int lastIndexOf = str.lastIndexOf(File.separator);
                    if (lastIndexOf > -1) {
                        File file2 = new File(str.substring(0, lastIndexOf));
                        if (!file2.exists()) {
                            log("Creating " + file2.getAbsolutePath(), 3);
                            if (!file2.mkdirs() && !file2.isDirectory()) {
                                log("Failed to create " + file2.getAbsolutePath(), 2);
                            }
                            log("Created " + file2.getAbsolutePath(), 2);
                        } else {
                            log(file2.getAbsolutePath() + " exists. Skipping", 3);
                        }
                    } else {
                        log("File separator problem with " + readLine, 1);
                    }
                }
                FileUtils.close(bufferedReader);
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close(bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    private void massagePCLI(File file, File file2) throws IOException {
        Throwable th;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            bufferedWriter2.write(readLine.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX));
                            bufferedWriter2.newLine();
                        } else {
                            FileUtils.close(bufferedReader);
                            FileUtils.close(bufferedWriter2);
                            return;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter = bufferedWriter2;
                        FileUtils.close(bufferedReader);
                        FileUtils.close(bufferedWriter);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    public String getRepository() {
        return this.repository;
    }

    public String getFilenameFormat() {
        return this.filenameFormat;
    }

    public void setFilenameFormat(String str) {
        this.filenameFormat = str;
    }

    public String getLineStart() {
        return this.lineStart;
    }

    public void setLineStart(String str) {
        this.lineStart = str;
    }

    public void setRepository(String str) {
        this.repository = str;
    }

    public String getPvcsproject() {
        return this.pvcsProject;
    }

    public void setPvcsproject(String str) {
        this.pvcsProject = str;
    }

    public Vector getPvcsprojects() {
        return this.pvcsProjects;
    }

    public String getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(String str) {
        this.workspace = str;
    }

    public String getPvcsbin() {
        return this.pvcsbin;
    }

    public void setPvcsbin(String str) {
        this.pvcsbin = str;
    }

    public String getForce() {
        return this.force;
    }

    public void setForce(String str) {
        if (str == null || !str.equalsIgnoreCase("yes")) {
            this.force = "no";
        } else {
            this.force = "yes";
        }
    }

    public String getPromotiongroup() {
        return this.promotiongroup;
    }

    public void setPromotiongroup(String str) {
        this.promotiongroup = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getRevision() {
        return this.revision;
    }

    public void setRevision(String str) {
        this.revision = str;
    }

    public boolean getIgnoreReturnCode() {
        return this.ignorerc;
    }

    public void setIgnoreReturnCode(boolean z) {
        this.ignorerc = z;
    }

    public void addPvcsproject(PvcsProject pvcsProject) {
        this.pvcsProjects.addElement(pvcsProject);
    }

    public boolean getUpdateOnly() {
        return this.updateOnly;
    }

    public void setUpdateOnly(boolean z) {
        this.updateOnly = z;
    }

    public String getConfig() {
        return this.config;
    }

    public void setConfig(File file) {
        this.config = file.toString();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
