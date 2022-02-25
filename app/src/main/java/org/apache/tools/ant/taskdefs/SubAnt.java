package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public class SubAnt extends Task {
    private Path buildpath;
    private Ant ant = null;
    private String subTarget = null;
    private String antfile = getDefaultBuildFile();
    private File genericantfile = null;
    private boolean verbose = false;
    private boolean inheritAll = false;
    private boolean inheritRefs = false;
    private boolean failOnError = true;
    private String output = null;
    private Vector properties = new Vector();
    private Vector references = new Vector();
    private Vector propertySets = new Vector();
    private Vector targets = new Vector();

    protected String getDefaultBuildFile() {
        return Main.DEFAULT_BUILD_FILENAME;
    }

    @Override // org.apache.tools.ant.Task
    public void handleOutput(String str) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleOutput(str);
        } else {
            super.handleOutput(str);
        }
    }

    @Override // org.apache.tools.ant.Task
    public int handleInput(byte[] bArr, int i, int i2) throws IOException {
        Ant ant = this.ant;
        if (ant != null) {
            return ant.handleInput(bArr, i, i2);
        }
        return super.handleInput(bArr, i, i2);
    }

    @Override // org.apache.tools.ant.Task
    public void handleFlush(String str) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleFlush(str);
        } else {
            super.handleFlush(str);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorOutput(String str) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleErrorOutput(str);
        } else {
            super.handleErrorOutput(str);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorFlush(String str) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleErrorFlush(str);
        } else {
            super.handleErrorFlush(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x017a A[SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.SubAnt.execute():void");
    }

    private void execute(File file, File file2) throws BuildException {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            String str = "Invalid file: " + file;
            if (!this.failOnError) {
                log(str, 1);
                return;
            }
            throw new BuildException(str);
        }
        this.ant = createAntTask(file2);
        String absolutePath = file.getAbsolutePath();
        this.ant.setAntfile(absolutePath);
        int size = this.targets.size();
        for (int i = 0; i < size; i++) {
            this.ant.addConfiguredTarget((Ant.TargetElement) this.targets.get(i));
        }
        try {
            try {
                if (this.verbose) {
                    log("Executing: " + absolutePath, 2);
                }
                this.ant.execute();
            } catch (BuildException e) {
                if (this.failOnError || isHardError(e)) {
                    throw e;
                }
                log("Failure for target '" + this.subTarget + "' of: " + absolutePath + "\n" + e.getMessage(), 1);
            }
        } finally {
            this.ant = null;
        }
    }

    private boolean isHardError(Throwable th) {
        if (th instanceof BuildException) {
            return isHardError(th.getCause());
        }
        return (th instanceof OutOfMemoryError) || (th instanceof ThreadDeath);
    }

    public void setAntfile(String str) {
        this.antfile = str;
    }

    public void setGenericAntfile(File file) {
        this.genericantfile = file;
    }

    public void setFailonerror(boolean z) {
        this.failOnError = z;
    }

    public void setTarget(String str) {
        this.subTarget = str;
    }

    public void addConfiguredTarget(Ant.TargetElement targetElement) {
        if (!"".equals(targetElement.getName())) {
            this.targets.add(targetElement);
            return;
        }
        throw new BuildException("target name must not be empty");
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setOutput(String str) {
        this.output = str;
    }

    public void setInheritall(boolean z) {
        this.inheritAll = z;
    }

    public void setInheritrefs(boolean z) {
        this.inheritRefs = z;
    }

    public void addProperty(Property property) {
        this.properties.addElement(property);
    }

    public void addReference(Ant.Reference reference) {
        this.references.addElement(reference);
    }

    public void addPropertyset(PropertySet propertySet) {
        this.propertySets.addElement(propertySet);
    }

    public void addDirset(DirSet dirSet) {
        add(dirSet);
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void addFilelist(FileList fileList) {
        add(fileList);
    }

    public void add(ResourceCollection resourceCollection) {
        getBuildpath().add(resourceCollection);
    }

    public void setBuildpath(Path path) {
        getBuildpath().append(path);
    }

    public Path createBuildpath() {
        return getBuildpath().createPath();
    }

    public Path.PathElement createBuildpathElement() {
        return getBuildpath().createPathElement();
    }

    private Path getBuildpath() {
        if (this.buildpath == null) {
            this.buildpath = new Path(getProject());
        }
        return this.buildpath;
    }

    public void setBuildpathRef(Reference reference) {
        createBuildpath().setRefid(reference);
    }

    private Ant createAntTask(File file) {
        Ant ant = new Ant(this);
        ant.init();
        String str = this.subTarget;
        if (str != null && str.length() > 0) {
            ant.setTarget(this.subTarget);
        }
        String str2 = this.output;
        if (str2 != null) {
            ant.setOutput(str2);
        }
        if (file != null) {
            ant.setDir(file);
        } else {
            ant.setUseNativeBasedir(true);
        }
        ant.setInheritAll(this.inheritAll);
        Enumeration elements = this.properties.elements();
        while (elements.hasMoreElements()) {
            copyProperty(ant.createProperty(), (Property) elements.nextElement());
        }
        Enumeration elements2 = this.propertySets.elements();
        while (elements2.hasMoreElements()) {
            ant.addPropertyset((PropertySet) elements2.nextElement());
        }
        ant.setInheritRefs(this.inheritRefs);
        Enumeration elements3 = this.references.elements();
        while (elements3.hasMoreElements()) {
            ant.addReference((Ant.Reference) elements3.nextElement());
        }
        return ant;
    }

    private static void copyProperty(Property property, Property property2) {
        property.setName(property2.getName());
        if (property2.getValue() != null) {
            property.setValue(property2.getValue());
        }
        if (property2.getFile() != null) {
            property.setFile(property2.getFile());
        }
        if (property2.getResource() != null) {
            property.setResource(property2.getResource());
        }
        if (property2.getPrefix() != null) {
            property.setPrefix(property2.getPrefix());
        }
        if (property2.getRefid() != null) {
            property.setRefid(property2.getRefid());
        }
        if (property2.getEnvironment() != null) {
            property.setEnvironment(property2.getEnvironment());
        }
        if (property2.getClasspath() != null) {
            property.setClasspath(property2.getClasspath());
        }
    }
}
