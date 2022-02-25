package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.filters.LineContainsRegExp;
import org.apache.tools.ant.taskdefs.optional.sos.SOSCmd;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.RedirectorElement;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.util.JavaEnvUtils;

/* loaded from: classes2.dex */
public abstract class AbstractJarSignerTask extends Task {
    public static final String ERROR_NO_SOURCE = "jar must be set through jar attribute or nested filesets";
    protected static final String JARSIGNER_COMMAND = "jarsigner";
    protected String alias;
    private String executable;
    protected File jar;
    protected String keypass;
    protected String keystore;
    protected String maxMemory;
    private RedirectorElement redirector;
    protected String storepass;
    protected String storetype;
    protected boolean verbose;
    protected boolean strict = false;
    protected Vector<FileSet> filesets = new Vector<>();
    private Environment sysProperties = new Environment();
    private Path path = null;

    public void setMaxmemory(String str) {
        this.maxMemory = str;
    }

    public void setJar(File file) {
        this.jar = file;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public void setKeystore(String str) {
        this.keystore = str;
    }

    public void setStorepass(String str) {
        this.storepass = str;
    }

    public void setStoretype(String str) {
        this.storetype = str;
    }

    public void setKeypass(String str) {
        this.keypass = str;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setStrict(boolean z) {
        this.strict = z;
    }

    public void addFileset(FileSet fileSet) {
        this.filesets.addElement(fileSet);
    }

    public void addSysproperty(Environment.Variable variable) {
        this.sysProperties.addVariable(variable);
    }

    public Path createPath() {
        if (this.path == null) {
            this.path = new Path(getProject());
        }
        return this.path.createPath();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beginExecution() {
        this.redirector = createRedirector();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void endExecution() {
        this.redirector = null;
    }

    private RedirectorElement createRedirector() {
        RedirectorElement redirectorElement = new RedirectorElement();
        String str = this.storepass;
        if (str != null) {
            StringBuffer stringBuffer = new StringBuffer(str);
            stringBuffer.append('\n');
            String str2 = this.keypass;
            if (str2 != null) {
                stringBuffer.append(str2);
                stringBuffer.append('\n');
            }
            redirectorElement.setInputString(stringBuffer.toString());
            redirectorElement.setLogInputString(false);
            LineContainsRegExp lineContainsRegExp = new LineContainsRegExp();
            RegularExpression regularExpression = new RegularExpression();
            regularExpression.setPattern("^(Enter Passphrase for keystore: |Enter key password for .+: )$");
            lineContainsRegExp.addConfiguredRegexp(regularExpression);
            lineContainsRegExp.setNegate(true);
            redirectorElement.createErrorFilterChain().addLineContainsRegExp(lineContainsRegExp);
        }
        return redirectorElement;
    }

    public RedirectorElement getRedirector() {
        return this.redirector;
    }

    public void setExecutable(String str) {
        this.executable = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCommonOptions(ExecTask execTask) {
        if (this.maxMemory != null) {
            addValue(execTask, "-J-Xmx" + this.maxMemory);
        }
        if (this.verbose) {
            addValue(execTask, SOSCmd.FLAG_VERBOSE);
        }
        if (this.strict) {
            addValue(execTask, "-strict");
        }
        Iterator<Environment.Variable> it = this.sysProperties.getVariablesVector().iterator();
        while (it.hasNext()) {
            declareSysProperty(execTask, it.next());
        }
    }

    protected void declareSysProperty(ExecTask execTask, Environment.Variable variable) throws BuildException {
        addValue(execTask, "-J-D" + variable.getContent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindToKeystore(ExecTask execTask) {
        String str;
        if (this.keystore != null) {
            addValue(execTask, "-keystore");
            File resolveFile = getProject().resolveFile(this.keystore);
            if (resolveFile.exists()) {
                str = resolveFile.getPath();
            } else {
                str = this.keystore;
            }
            addValue(execTask, str);
        }
        if (this.storetype != null) {
            addValue(execTask, "-storetype");
            addValue(execTask, this.storetype);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExecTask createJarSigner() {
        ExecTask execTask = new ExecTask(this);
        String str = this.executable;
        if (str == null) {
            execTask.setExecutable(JavaEnvUtils.getJdkExecutable(JARSIGNER_COMMAND));
        } else {
            execTask.setExecutable(str);
        }
        execTask.setTaskType(JARSIGNER_COMMAND);
        execTask.setFailonerror(true);
        execTask.addConfiguredRedirector(this.redirector);
        return execTask;
    }

    protected Vector<FileSet> createUnifiedSources() {
        Vector<FileSet> vector = (Vector) this.filesets.clone();
        if (this.jar != null) {
            FileSet fileSet = new FileSet();
            fileSet.setProject(getProject());
            fileSet.setFile(this.jar);
            fileSet.setDir(this.jar.getParentFile());
            vector.add(fileSet);
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Path createUnifiedSourcePath() {
        Path path = this.path;
        Path path2 = path == null ? new Path(getProject()) : (Path) path.clone();
        Iterator<FileSet> it = createUnifiedSources().iterator();
        while (it.hasNext()) {
            path2.add(it.next());
        }
        return path2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasResources() {
        return this.path != null || this.filesets.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addValue(ExecTask execTask, String str) {
        execTask.createArg().setValue(str);
    }
}
