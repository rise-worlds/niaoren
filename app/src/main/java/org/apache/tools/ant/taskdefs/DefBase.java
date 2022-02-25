package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.ClasspathUtils;

/* loaded from: classes2.dex */
public abstract class DefBase extends AntlibDefinition {
    private ClasspathUtils.Delegate cpDelegate;
    private ClassLoader createdLoader;

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasCpDelegate() {
        return this.cpDelegate != null;
    }

    public void setReverseLoader(boolean z) {
        getDelegate().setReverseLoader(z);
        log("The reverseloader attribute is DEPRECATED. It will be removed", 1);
    }

    public Path getClasspath() {
        return getDelegate().getClasspath();
    }

    public boolean isReverseLoader() {
        return getDelegate().isReverseLoader();
    }

    public String getLoaderId() {
        return getDelegate().getClassLoadId();
    }

    public String getClasspathId() {
        return getDelegate().getClassLoadId();
    }

    public void setClasspath(Path path) {
        getDelegate().setClasspath(path);
    }

    public Path createClasspath() {
        return getDelegate().createClasspath();
    }

    public void setClasspathRef(Reference reference) {
        getDelegate().setClasspathref(reference);
    }

    public void setLoaderRef(Reference reference) {
        getDelegate().setLoaderRef(reference);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassLoader createLoader() {
        if (getAntlibClassLoader() != null && this.cpDelegate == null) {
            return getAntlibClassLoader();
        }
        if (this.createdLoader == null) {
            this.createdLoader = getDelegate().getClassLoader();
            ((AntClassLoader) this.createdLoader).addSystemPackageRoot("org.apache.tools.ant");
        }
        return this.createdLoader;
    }

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        super.init();
    }

    private ClasspathUtils.Delegate getDelegate() {
        if (this.cpDelegate == null) {
            this.cpDelegate = ClasspathUtils.getDelegate(this);
        }
        return this.cpDelegate;
    }
}
