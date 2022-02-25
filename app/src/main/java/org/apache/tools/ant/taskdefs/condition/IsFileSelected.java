package org.apache.tools.ant.taskdefs.condition;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.selectors.AbstractSelectorContainer;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class IsFileSelected extends AbstractSelectorContainer implements Condition {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File baseDir;
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public void setBaseDir(File file) {
        this.baseDir = file;
    }

    @Override // org.apache.tools.ant.types.selectors.AbstractSelectorContainer
    public void validate() {
        if (selectorCount() == 1) {
            super.validate();
            return;
        }
        throw new BuildException("Only one selector allowed");
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() {
        if (this.file != null) {
            validate();
            File file = this.baseDir;
            if (file == null) {
                file = getProject().getBaseDir();
            }
            return getSelectors(getProject())[0].isSelected(file, FILE_UTILS.removeLeadingPath(file, this.file), this.file);
        }
        throw new BuildException("file attribute not set");
    }
}
