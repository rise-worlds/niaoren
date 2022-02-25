package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.LogLevel;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.LogOutputResource;
import org.apache.tools.ant.types.resources.StringResource;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class Echo extends Task {
    private Resource output;
    protected String message = "";
    protected File file = null;
    protected boolean append = false;
    private String encoding = "";
    private boolean force = false;
    protected int logLevel = 1;

    /* loaded from: classes2.dex */
    public static class EchoLevel extends LogLevel {
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        try {
            ResourceUtils.copyResource(new StringResource("".equals(this.message) ? StringUtils.LINE_SEP : this.message), this.output == null ? new LogOutputResource(this, this.logLevel) : this.output, null, null, false, false, this.append, null, "".equals(this.encoding) ? null : this.encoding, getProject(), this.force);
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    public void setMessage(String str) {
        if (str == null) {
            str = "";
        }
        this.message = str;
    }

    public void setFile(File file) {
        setOutput(new FileResource(getProject(), file));
    }

    public void setOutput(Resource resource) {
        if (this.output == null) {
            this.output = resource;
            FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
            this.file = fileProvider != null ? fileProvider.getFile() : null;
            return;
        }
        throw new BuildException("Cannot set > 1 output target");
    }

    public void setAppend(boolean z) {
        this.append = z;
    }

    public void addText(String str) {
        this.message += getProject().replaceProperties(str);
    }

    public void setLevel(EchoLevel echoLevel) {
        this.logLevel = echoLevel.getLevel();
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setForce(boolean z) {
        this.force = z;
    }
}
