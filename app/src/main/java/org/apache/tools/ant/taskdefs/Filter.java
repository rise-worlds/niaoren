package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class Filter extends Task {
    private File filtersFile;
    private String token;
    private String value;

    public void setToken(String str) {
        this.token = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setFiltersfile(File file) {
        this.filtersFile = file;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        boolean z = true;
        boolean z2 = this.filtersFile != null && this.token == null && this.value == null;
        if (this.filtersFile != null || this.token == null || this.value == null) {
            z = false;
        }
        if (z2 || z) {
            if (z) {
                getProject().getGlobalFilterSet().addFilter(this.token, this.value);
            }
            if (z2) {
                readFilters();
                return;
            }
            return;
        }
        throw new BuildException("both token and value parameters, or only a filtersFile parameter is required", getLocation());
    }

    protected void readFilters() throws BuildException {
        log("Reading filters from " + this.filtersFile, 3);
        getProject().getGlobalFilterSet().readFiltersFromFile(this.filtersFile);
    }
}
