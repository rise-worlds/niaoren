package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class TempFile extends Task {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private boolean createFile;
    private boolean deleteOnExit;
    private String prefix;
    private String property;
    private File destDir = null;
    private String suffix = "";

    public void setProperty(String str) {
        this.property = str;
    }

    public void setDestDir(File file) {
        this.destDir = file;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    public void setDeleteOnExit(boolean z) {
        this.deleteOnExit = z;
    }

    public boolean isDeleteOnExit() {
        return this.deleteOnExit;
    }

    public void setCreateFile(boolean z) {
        this.createFile = z;
    }

    public boolean isCreateFile() {
        return this.createFile;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        String str = this.property;
        if (str == null || str.length() == 0) {
            throw new BuildException("no property specified");
        }
        if (this.destDir == null) {
            this.destDir = getProject().resolveFile(Consts.f23430h);
        }
        getProject().setNewProperty(this.property, FILE_UTILS.createTempFile(this.prefix, this.suffix, this.destDir, this.deleteOnExit, this.createFile).toString());
    }
}
