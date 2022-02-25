package org.apache.tools.ant.taskdefs;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.JavaResource;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class LoadProperties extends Task {
    private Resource src = null;
    private final Vector<FilterChain> filterChains = new Vector<>();
    private String encoding = null;
    private String prefix = null;
    private boolean prefixValues = true;

    public final void setSrcFile(File file) {
        addConfigured(new FileResource(file));
    }

    public void setResource(String str) {
        getRequiredJavaResource().setName(str);
    }

    public final void setEncoding(String str) {
        this.encoding = str;
    }

    public void setClasspath(Path path) {
        getRequiredJavaResource().setClasspath(path);
    }

    public Path createClasspath() {
        return getRequiredJavaResource().createClasspath();
    }

    public void setClasspathRef(Reference reference) {
        getRequiredJavaResource().setClasspathRef(reference);
    }

    public Path getClasspath() {
        return getRequiredJavaResource().getClasspath();
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setPrefixValues(boolean z) {
        this.prefixValues = z;
    }

    @Override // org.apache.tools.ant.Task
    public final void execute() throws BuildException {
        IOException e;
        InputStreamReader inputStreamReader;
        Resource resource = this.src;
        if (resource == null) {
            throw new BuildException("A source resource is required.");
        } else if (resource.isExists()) {
            InputStream inputStream = null;
            try {
                inputStream = new BufferedInputStream(this.src.getInputStream());
                try {
                    if (this.encoding == null) {
                        inputStreamReader = new InputStreamReader(inputStream);
                    } else {
                        inputStreamReader = new InputStreamReader(inputStream, this.encoding);
                    }
                    ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
                    chainReaderHelper.setPrimaryReader(inputStreamReader);
                    chainReaderHelper.setFilterChains(this.filterChains);
                    chainReaderHelper.setProject(getProject());
                    String readFully = chainReaderHelper.readFully(chainReaderHelper.getAssembledReader());
                    if (!(readFully == null || readFully.length() == 0)) {
                        if (!readFully.endsWith("\n")) {
                            readFully = readFully + "\n";
                        }
                        inputStream = new ByteArrayInputStream(readFully.getBytes("ISO-8859-1"));
                        try {
                            Properties properties = new Properties();
                            properties.load(inputStream);
                            Property property = new Property();
                            property.bindToOwner(this);
                            property.setPrefix(this.prefix);
                            property.setPrefixValues(this.prefixValues);
                            property.addProperties(properties);
                            inputStream = inputStream;
                        } catch (IOException e2) {
                            e = e2;
                            inputStream = inputStream;
                            try {
                                throw new BuildException("Unable to load file: " + e, e, getLocation());
                            } catch (Throwable th) {
                                th = th;
                                FileUtils.close(inputStream);
                                FileUtils.close(inputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            FileUtils.close(inputStream);
                            FileUtils.close(inputStream);
                            throw th;
                        }
                    }
                    FileUtils.close(inputStream);
                    FileUtils.close(inputStream);
                } catch (IOException e3) {
                    e = e3;
                    inputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    FileUtils.close(inputStream);
                    FileUtils.close(inputStream);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                inputStream = null;
            } catch (Throwable th4) {
                th = th4;
                inputStream = null;
            }
        } else if (this.src instanceof JavaResource) {
            log("Unable to find resource " + this.src, 1);
        } else {
            throw new BuildException("Source resource does not exist: " + this.src);
        }
    }

    public final void addFilterChain(FilterChain filterChain) {
        this.filterChains.addElement(filterChain);
    }

    public synchronized void addConfigured(ResourceCollection resourceCollection) {
        if (this.src != null) {
            throw new BuildException("only a single source is supported");
        } else if (resourceCollection.size() == 1) {
            this.src = resourceCollection.iterator().next();
        } else {
            throw new BuildException("only single-element resource collections are supported");
        }
    }

    private synchronized JavaResource getRequiredJavaResource() {
        if (this.src == null) {
            this.src = new JavaResource();
            this.src.setProject(getProject());
        } else if (!(this.src instanceof JavaResource)) {
            throw new BuildException("expected a java resource as source");
        }
        return (JavaResource) this.src;
    }
}
