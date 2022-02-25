package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* loaded from: classes2.dex */
public class Classloader extends Task {
    public static final String SYSTEM_LOADER_REF = "ant.coreLoader";
    private Path classpath;
    private String name = null;
    private boolean reset = false;
    private boolean parentFirst = true;
    private String parentName = null;

    public void setName(String str) {
        this.name = str;
    }

    public void setReset(boolean z) {
        this.reset = z;
    }

    public void setReverse(boolean z) {
        this.parentFirst = !z;
    }

    public void setParentFirst(boolean z) {
        this.parentFirst = z;
    }

    public void setParentName(String str) {
        this.parentName = str;
    }

    public void setClasspathRef(Reference reference) throws BuildException {
        this.classpath = (Path) reference.getReferencedObject(getProject());
    }

    public void setClasspath(Path path) {
        Path path2 = this.classpath;
        if (path2 == null) {
            this.classpath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(null);
        }
        return this.classpath.createPath();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        try {
            boolean z = true;
            if (!"only".equals(getProject().getProperty(MagicNames.BUILD_SYSCLASSPATH)) || (this.name != null && !"ant.coreLoader".equals(this.name))) {
                String str = this.name == null ? "ant.coreLoader" : this.name;
                Object reference = getProject().getReference(str);
                Object obj = null;
                if (this.reset) {
                    reference = null;
                }
                if (reference == null || (reference instanceof AntClassLoader)) {
                    AntClassLoader antClassLoader = (AntClassLoader) reference;
                    if (antClassLoader == null) {
                        z = false;
                    }
                    if (antClassLoader == null) {
                        if (this.parentName != null) {
                            Object reference2 = getProject().getReference(this.parentName);
                            if (reference2 instanceof ClassLoader) {
                                obj = reference2;
                            }
                        }
                        if (obj == null) {
                            obj = getClass().getClassLoader();
                        }
                        String str2 = this.name;
                        getProject().log("Setting parent loader " + this.name + ExpandableTextView.f6958c + obj + ExpandableTextView.f6958c + this.parentFirst, 4);
                        antClassLoader = AntClassLoader.newAntClassLoader((ClassLoader) obj, getProject(), this.classpath, this.parentFirst);
                        getProject().addReference(str, antClassLoader);
                        if (this.name == null) {
                            antClassLoader.addLoaderPackageRoot("org.apache.tools.ant.taskdefs.optional");
                            getProject().setCoreLoader(antClassLoader);
                        }
                    }
                    if (z && this.classpath != null) {
                        for (String str3 : this.classpath.list()) {
                            File file = new File(str3);
                            if (file.exists()) {
                                log("Adding to class loader " + antClassLoader + ExpandableTextView.f6958c + file.getAbsolutePath(), 4);
                                antClassLoader.addPathElement(file.getAbsolutePath());
                            }
                        }
                        return;
                    }
                    return;
                }
                log("Referenced object is not an AntClassLoader", 0);
                return;
            }
            log("Changing the system loader is disabled by build.sysclasspath=only", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
