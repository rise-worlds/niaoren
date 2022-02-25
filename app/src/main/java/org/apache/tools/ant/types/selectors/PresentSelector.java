package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.IdentityMapper;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class PresentSelector extends BaseSelector {
    private File targetdir = null;
    private Mapper mapperElement = null;
    private FileNameMapper map = null;
    private boolean destmustexist = true;

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder("{presentselector targetdir: ");
        File file = this.targetdir;
        if (file == null) {
            sb.append("NOT YET SET");
        } else {
            sb.append(file.getName());
        }
        sb.append(" present: ");
        if (this.destmustexist) {
            sb.append("both");
        } else {
            sb.append("srconly");
        }
        FileNameMapper fileNameMapper = this.map;
        if (fileNameMapper != null) {
            sb.append(fileNameMapper.toString());
        } else {
            Mapper mapper = this.mapperElement;
            if (mapper != null) {
                sb.append(mapper.toString());
            }
        }
        sb.append(C4963cj.f20747d);
        return sb.toString();
    }

    public void setTargetdir(File file) {
        this.targetdir = file;
    }

    public Mapper createMapper() throws BuildException {
        if (this.map == null && this.mapperElement == null) {
            this.mapperElement = new Mapper(getProject());
            return this.mapperElement;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS);
    }

    public void addConfigured(FileNameMapper fileNameMapper) {
        if (this.map == null && this.mapperElement == null) {
            this.map = fileNameMapper;
            return;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS);
    }

    public void setPresent(FilePresence filePresence) {
        if (filePresence.getIndex() == 0) {
            this.destmustexist = false;
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.targetdir == null) {
            setError("The targetdir attribute is required.");
        }
        if (this.map == null) {
            Mapper mapper = this.mapperElement;
            if (mapper == null) {
                this.map = new IdentityMapper();
                return;
            }
            this.map = mapper.getImplementation();
            if (this.map == null) {
                setError("Could not set <mapper> element.");
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        validate();
        String[] mapFileName = this.map.mapFileName(str);
        if (mapFileName == null) {
            return false;
        }
        if (mapFileName.length != 1 || mapFileName[0] == null) {
            throw new BuildException("Invalid destination file results for " + this.targetdir + " with filename " + str);
        }
        return FileUtils.getFileUtils().resolveFile(this.targetdir, mapFileName[0]).exists() == this.destmustexist;
    }

    /* loaded from: classes2.dex */
    public static class FilePresence extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"srconly", "both"};
        }
    }
}
