package org.apache.tools.ant.taskdefs.condition;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class ResourceContains implements Condition {
    private boolean casesensitive = true;
    private Project project;
    private String refid;
    private Resource resource;
    private String substring;

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
    }

    public void setResource(String str) {
        this.resource = new FileResource(new File(str));
    }

    public void setRefid(String str) {
        this.refid = str;
    }

    private void resolveRefid() {
        try {
            if (getProject() != null) {
                Object reference = getProject().getReference(this.refid);
                if (!(reference instanceof Resource)) {
                    if (reference instanceof ResourceCollection) {
                        ResourceCollection resourceCollection = (ResourceCollection) reference;
                        if (resourceCollection.size() == 1) {
                            reference = resourceCollection.iterator().next();
                        }
                    } else {
                        throw new BuildException("Illegal value at '" + this.refid + "': " + String.valueOf(reference));
                    }
                }
                this.resource = (Resource) reference;
                return;
            }
            throw new BuildException("Cannot retrieve refid; project unset");
        } finally {
            this.refid = null;
        }
    }

    public void setSubstring(String str) {
        this.substring = str;
    }

    public void setCasesensitive(boolean z) {
        this.casesensitive = z;
    }

    private void validate() {
        if (this.resource == null || this.refid == null) {
            if (this.resource == null && this.refid != null) {
                resolveRefid();
            }
            if (this.resource == null || this.substring == null) {
                throw new BuildException("both resource and substring are required in <resourcecontains>");
            }
            return;
        }
        throw new BuildException("Cannot set both resource and refid");
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public synchronized boolean eval() throws BuildException {
        Throwable th;
        BufferedReader bufferedReader;
        validate();
        if (this.substring.length() == 0) {
            if (getProject() != null) {
                getProject().log("Substring is empty; returning true", 3);
            }
            return true;
        }
        boolean z = false;
        if (this.resource.getSize() == 0) {
            return false;
        }
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(this.resource.getInputStream()));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused) {
        }
        try {
            String safeReadFully = FileUtils.safeReadFully(bufferedReader);
            String str = this.substring;
            if (!this.casesensitive) {
                safeReadFully = safeReadFully.toLowerCase();
                str = str.toLowerCase();
            }
            if (safeReadFully.indexOf(str) >= 0) {
                z = true;
            }
            FileUtils.close(bufferedReader);
            return z;
        } catch (IOException unused2) {
            throw new BuildException("There was a problem accessing resource : " + this.resource);
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = bufferedReader;
            FileUtils.close(bufferedReader2);
            throw th;
        }
    }
}
