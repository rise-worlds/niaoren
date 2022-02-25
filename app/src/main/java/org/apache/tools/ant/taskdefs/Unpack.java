package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;

/* loaded from: classes2.dex */
public abstract class Unpack extends Task {
    protected File dest;
    protected File source;
    protected Resource srcResource;

    protected abstract void extract();

    protected abstract String getDefaultExtension();

    protected boolean supportsNonFileResources() {
        return false;
    }

    public void setSrc(String str) {
        log("DEPRECATED - The setSrc(String) method has been deprecated. Use setSrc(File) instead.");
        setSrc(getProject().resolveFile(str));
    }

    public void setDest(String str) {
        log("DEPRECATED - The setDest(String) method has been deprecated. Use setDest(File) instead.");
        setDest(getProject().resolveFile(str));
    }

    public void setSrc(File file) {
        setSrcResource(new FileResource(file));
    }

    public void setSrcResource(Resource resource) {
        if (!resource.isExists()) {
            throw new BuildException("the archive " + resource.getName() + " doesn't exist");
        } else if (!resource.isDirectory()) {
            FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
            if (fileProvider != null) {
                this.source = fileProvider.getFile();
            } else if (!supportsNonFileResources()) {
                throw new BuildException("The source " + resource.getName() + " is not a FileSystem Only FileSystem resources are supported.");
            }
            this.srcResource = resource;
        } else {
            throw new BuildException("the archive " + resource.getName() + " can't be a directory");
        }
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        if (resourceCollection.size() == 1) {
            setSrcResource(resourceCollection.iterator().next());
            return;
        }
        throw new BuildException("only single argument resource collections are supported as archives");
    }

    public void setDest(File file) {
        this.dest = file;
    }

    private void validate() throws BuildException {
        if (this.srcResource != null) {
            if (this.dest == null) {
                this.dest = new File(this.source.getParent());
            }
            if (this.dest.isDirectory()) {
                createDestFile(getDefaultExtension());
                return;
            }
            return;
        }
        throw new BuildException("No Src specified", getLocation());
    }

    private void createDestFile(String str) {
        String name = this.source.getName();
        int length = name.length();
        if (str == null || length <= str.length() || !str.equalsIgnoreCase(name.substring(length - str.length()))) {
            this.dest = new File(this.dest, name);
        } else {
            this.dest = new File(this.dest, name.substring(0, length - str.length()));
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file = this.dest;
        try {
            validate();
            extract();
        } finally {
            this.dest = file;
        }
    }
}
