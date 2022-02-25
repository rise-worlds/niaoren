package org.apache.tools.ant.taskdefs.optional.javacc;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.LogStreamHandler;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.JavaEnvUtils;

/* loaded from: classes2.dex */
public class JJDoc extends Task {
    private static final String DEFAULT_SUFFIX_HTML = ".html";
    private static final String DEFAULT_SUFFIX_TEXT = ".txt";
    private static final String ONE_TABLE = "ONE_TABLE";
    private static final String OUTPUT_FILE = "OUTPUT_FILE";
    private static final String TEXT = "TEXT";
    private final Hashtable optionalAttrs = new Hashtable();
    private String outputFile = null;
    private boolean plainText = false;
    private File targetFile = null;
    private File javaccHome = null;
    private CommandlineJava cmdl = new CommandlineJava();
    private String maxMemory = null;

    public void setText(boolean z) {
        this.optionalAttrs.put(TEXT, z ? Boolean.TRUE : Boolean.FALSE);
        this.plainText = z;
    }

    public void setOnetable(boolean z) {
        this.optionalAttrs.put(ONE_TABLE, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setOutputfile(String str) {
        this.outputFile = str;
    }

    public void setTarget(File file) {
        this.targetFile = file;
    }

    public void setJavacchome(File file) {
        this.javaccHome = file;
    }

    public void setMaxmemory(String str) {
        this.maxMemory = str;
    }

    public JJDoc() {
        this.cmdl.setVm(JavaEnvUtils.getJreExecutable("java"));
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Enumeration keys = this.optionalAttrs.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            Object obj = this.optionalAttrs.get(str);
            Commandline.Argument createArgument = this.cmdl.createArgument();
            createArgument.setValue("-" + str + ":" + obj.toString());
        }
        File file = this.targetFile;
        if (file == null || !file.isFile()) {
            throw new BuildException("Invalid target: " + this.targetFile);
        }
        if (this.outputFile != null) {
            Commandline.Argument createArgument2 = this.cmdl.createArgument();
            createArgument2.setValue("-OUTPUT_FILE:" + this.outputFile.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX));
        }
        File file2 = new File(createOutputFileName(this.targetFile, this.outputFile, this.plainText));
        if (!file2.exists() || this.targetFile.lastModified() >= file2.lastModified()) {
            this.cmdl.createArgument().setValue(this.targetFile.getAbsolutePath());
            Path createClasspath = this.cmdl.createClasspath(getProject());
            createClasspath.createPathElement().setPath(JavaCC.getArchiveFile(this.javaccHome).getAbsolutePath());
            createClasspath.addJavaRuntime();
            this.cmdl.setClassname(JavaCC.getMainClass(createClasspath, 3));
            this.cmdl.setMaxmemory(this.maxMemory);
            Commandline.Argument createVmArgument = this.cmdl.createVmArgument();
            createVmArgument.setValue("-Dinstall.root=" + this.javaccHome.getAbsolutePath());
            Execute execute = new Execute(new LogStreamHandler((Task) this, 2, 2), null);
            log(this.cmdl.describeCommand(), 3);
            execute.setCommandline(this.cmdl.getCommandline());
            try {
                if (execute.execute() != 0) {
                    throw new BuildException("JJDoc failed.");
                }
            } catch (IOException e) {
                throw new BuildException("Failed to launch JJDoc", e);
            }
        } else {
            log("Target is already built - skipping (" + this.targetFile + ")", 3);
        }
    }

    private String createOutputFileName(File file, String str, boolean z) {
        String str2;
        String str3 = DEFAULT_SUFFIX_HTML;
        String replace = file.getAbsolutePath().replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
        if (z) {
            str3 = DEFAULT_SUFFIX_TEXT;
        }
        if (str == null || str.equals("")) {
            int lastIndexOf = replace.lastIndexOf("/");
            if (lastIndexOf >= 0) {
                replace = replace.substring(lastIndexOf + 1);
            }
            int lastIndexOf2 = replace.lastIndexOf(46);
            if (lastIndexOf2 == -1) {
                str2 = replace + str3;
            } else if (replace.substring(lastIndexOf2).equals(str3)) {
                str2 = replace + str3;
            } else {
                str2 = replace.substring(0, lastIndexOf2) + str3;
            }
        } else {
            str2 = str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
        }
        return (getProject().getBaseDir() + "/" + str2).replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
    }
}
