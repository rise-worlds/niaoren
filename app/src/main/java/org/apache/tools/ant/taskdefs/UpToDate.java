package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.MergingMapper;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.ant.util.SourceFileScanner;

/* loaded from: classes2.dex */
public class UpToDate extends Task implements Condition {
    private String property;
    private File sourceFile;
    private File targetFile;
    private String value;
    private Vector sourceFileSets = new Vector();
    private Union sourceResources = new Union();
    protected Mapper mapperElement = null;

    public void setProperty(String str) {
        this.property = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    private String getValue() {
        String str = this.value;
        return str != null ? str : "true";
    }

    public void setTargetFile(File file) {
        this.targetFile = file;
    }

    public void setSrcfile(File file) {
        this.sourceFile = file;
    }

    public void addSrcfiles(FileSet fileSet) {
        this.sourceFileSets.addElement(fileSet);
    }

    public Union createSrcResources() {
        return this.sourceResources;
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapperElement == null) {
            this.mapperElement = new Mapper(getProject());
            return this.mapperElement;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS, getLocation());
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() {
        boolean z;
        if (this.sourceFileSets.size() == 0 && this.sourceResources.size() == 0 && this.sourceFile == null) {
            throw new BuildException("At least one srcfile or a nested <srcfiles> or <srcresources> element must be set.");
        } else if ((this.sourceFileSets.size() > 0 || this.sourceResources.size() > 0) && this.sourceFile != null) {
            throw new BuildException("Cannot specify both the srcfile attribute and a nested <srcfiles> or <srcresources> element.");
        } else if (this.targetFile == null && this.mapperElement == null) {
            throw new BuildException("The targetfile attribute or a nested mapper element must be set.");
        } else {
            File file = this.targetFile;
            if (file == null || file.exists()) {
                File file2 = this.sourceFile;
                if (file2 == null || file2.exists()) {
                    if (this.sourceFile != null) {
                        if (this.mapperElement == null) {
                            z = this.targetFile.lastModified() >= this.sourceFile.lastModified();
                        } else {
                            z = new SourceFileScanner(this).restrict(new String[]{this.sourceFile.getAbsolutePath()}, null, null, this.mapperElement.getImplementation()).length == 0;
                        }
                        if (!z) {
                            log(this.sourceFile.getAbsolutePath() + " is newer than (one of) its target(s).", 3);
                        }
                    } else {
                        z = true;
                    }
                    Enumeration elements = this.sourceFileSets.elements();
                    while (z && elements.hasMoreElements()) {
                        FileSet fileSet = (FileSet) elements.nextElement();
                        z = scanDir(fileSet.getDir(getProject()), fileSet.getDirectoryScanner(getProject()).getIncludedFiles());
                    }
                    if (!z) {
                        return z;
                    }
                    Resource[] listResources = this.sourceResources.listResources();
                    return listResources.length > 0 ? ResourceUtils.selectOutOfDateSources(this, listResources, getMapper(), getProject()).length == 0 : z;
                }
                throw new BuildException(this.sourceFile.getAbsolutePath() + " not found.");
            }
            log("The targetfile \"" + this.targetFile.getAbsolutePath() + "\" does not exist.", 3);
            return false;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.property == null) {
            throw new BuildException("property attribute is required.", getLocation());
        } else if (eval()) {
            getProject().setNewProperty(this.property, getValue());
            if (this.mapperElement == null) {
                log("File \"" + this.targetFile.getAbsolutePath() + "\" is up-to-date.", 3);
                return;
            }
            log("All target files are up-to-date.", 3);
        }
    }

    protected boolean scanDir(File file, String[] strArr) {
        return new SourceFileScanner(this).restrict(strArr, file, this.mapperElement == null ? null : file, getMapper()).length == 0;
    }

    private FileNameMapper getMapper() {
        Mapper mapper = this.mapperElement;
        if (mapper != null) {
            return mapper.getImplementation();
        }
        MergingMapper mergingMapper = new MergingMapper();
        mergingMapper.setTo(this.targetFile.getAbsolutePath());
        return mergingMapper;
    }
}
