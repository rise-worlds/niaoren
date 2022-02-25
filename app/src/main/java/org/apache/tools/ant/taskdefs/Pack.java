package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;

/* loaded from: classes2.dex */
public abstract class Pack extends Task {
    private static final int BUFFER_SIZE = 8192;
    protected File source;
    private Resource src;
    protected File zipFile;

    protected abstract void pack();

    protected boolean supportsNonFileResources() {
        return false;
    }

    public void setZipfile(File file) {
        this.zipFile = file;
    }

    public void setDestfile(File file) {
        setZipfile(file);
    }

    public void setSrc(File file) {
        setSrcResource(new FileResource(file));
    }

    public void setSrcResource(Resource resource) {
        if (!resource.isDirectory()) {
            FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
            if (fileProvider != null) {
                this.source = fileProvider.getFile();
            } else if (!supportsNonFileResources()) {
                throw new BuildException("Only FileSystem resources are supported.");
            }
            this.src = resource;
            return;
        }
        throw new BuildException("the source can't be a directory");
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        if (resourceCollection.size() == 0) {
            throw new BuildException("No resource selected, " + getTaskName() + " needs exactly one resource.");
        } else if (resourceCollection.size() == 1) {
            setSrcResource(resourceCollection.iterator().next());
        } else {
            throw new BuildException(getTaskName() + " cannot handle multiple resources at once. (" + resourceCollection.size() + " resources were selected.)");
        }
    }

    private void validate() throws BuildException {
        File file = this.zipFile;
        if (file == null) {
            throw new BuildException("zipfile attribute is required", getLocation());
        } else if (file.isDirectory()) {
            throw new BuildException("zipfile attribute must not represent a directory!", getLocation());
        } else if (getSrcResource() == null) {
            throw new BuildException("src attribute or nested resource is required", getLocation());
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        validate();
        Resource srcResource = getSrcResource();
        if (!srcResource.isExists()) {
            log("Nothing to do: " + srcResource.toString() + " doesn't exist.");
        } else if (this.zipFile.lastModified() < srcResource.getLastModified()) {
            log("Building: " + this.zipFile.getAbsolutePath());
            pack();
        } else {
            log("Nothing to do: " + this.zipFile.getAbsolutePath() + " is up to date.");
        }
    }

    private void zipFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        int i = 0;
        do {
            outputStream.write(bArr, 0, i);
            i = inputStream.read(bArr, 0, bArr.length);
        } while (i != -1);
    }

    protected void zipFile(File file, OutputStream outputStream) throws IOException {
        zipResource(new FileResource(file), outputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zipResource(Resource resource, OutputStream outputStream) throws IOException {
        InputStream inputStream = resource.getInputStream();
        try {
            zipFile(inputStream, outputStream);
        } finally {
            inputStream.close();
        }
    }

    public Resource getSrcResource() {
        return this.src;
    }
}
