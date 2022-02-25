package org.apache.tools.ant.taskdefs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class LoadResource extends Task {
    private Resource src;
    private boolean failOnError = true;
    private boolean quiet = false;
    private String encoding = null;
    private String property = null;
    private final Vector<FilterChain> filterChains = new Vector<>();

    public final void setEncoding(String str) {
        this.encoding = str;
    }

    public final void setProperty(String str) {
        this.property = str;
    }

    public final void setFailonerror(boolean z) {
        this.failOnError = z;
    }

    public void setQuiet(boolean z) {
        this.quiet = z;
        if (z) {
            this.failOnError = false;
        }
    }

    @Override // org.apache.tools.ant.Task
    public final void execute() throws BuildException {
        InputStreamReader inputStreamReader;
        if (this.src == null) {
            throw new BuildException("source resource not defined");
        } else if (this.property == null) {
            throw new BuildException("output property not defined");
        } else if (this.quiet && this.failOnError) {
            throw new BuildException("quiet and failonerror cannot both be set to true");
        } else if (!this.src.isExists()) {
            String str = this.src + " doesn't exist";
            if (!this.failOnError) {
                log(str, this.quiet ? 1 : 0);
                return;
            }
            throw new BuildException(str);
        } else {
            InputStream inputStream = null;
            log("loading " + this.src + " into property " + this.property, 3);
            int i = 0;
            try {
                try {
                    long size = this.src.getSize();
                    StringBuilder sb = new StringBuilder();
                    sb.append("resource size = ");
                    int i2 = (size > (-1L) ? 1 : (size == (-1L) ? 0 : -1));
                    sb.append(i2 != 0 ? String.valueOf(size) : "unknown");
                    log(sb.toString(), 4);
                    int i3 = (int) size;
                    inputStream = this.src.getInputStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    if (this.encoding == null) {
                        inputStreamReader = new InputStreamReader(bufferedInputStream);
                    } else {
                        inputStreamReader = new InputStreamReader(bufferedInputStream, this.encoding);
                    }
                    String str2 = "";
                    if (i3 != 0) {
                        ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
                        if (i2 != 0) {
                            chainReaderHelper.setBufferSize(i3);
                        }
                        chainReaderHelper.setPrimaryReader(inputStreamReader);
                        chainReaderHelper.setFilterChains(this.filterChains);
                        chainReaderHelper.setProject(getProject());
                        str2 = chainReaderHelper.readFully(chainReaderHelper.getAssembledReader());
                    } else {
                        log("Do not set property " + this.property + " as its length is 0.", this.quiet ? 3 : 2);
                    }
                    if (str2 != null && str2.length() > 0) {
                        getProject().setNewProperty(this.property, str2);
                        log("loaded " + str2.length() + " characters", 3);
                        log(this.property + " := " + str2, 4);
                    }
                } catch (IOException e) {
                    String str3 = "Unable to load resource: " + e.toString();
                    if (!this.failOnError) {
                        if (this.quiet) {
                            i = 3;
                        }
                        log(str3, i);
                    } else {
                        throw new BuildException(str3, e, getLocation());
                    }
                } catch (BuildException e2) {
                    if (!this.failOnError) {
                        String message = e2.getMessage();
                        if (this.quiet) {
                            i = 3;
                        }
                        log(message, i);
                    } else {
                        throw e2;
                    }
                }
            } finally {
                FileUtils.close(inputStream);
            }
        }
    }

    public final void addFilterChain(FilterChain filterChain) {
        this.filterChains.addElement(filterChain);
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        if (resourceCollection.size() == 1) {
            this.src = resourceCollection.iterator().next();
            return;
        }
        throw new BuildException("only single argument resource collections are supported");
    }
}
