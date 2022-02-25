package org.apache.tools.ant.types.resources;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class ResourceList extends DataType implements ResourceCollection {
    private final Vector<FilterChain> filterChains = new Vector<>();
    private final ArrayList<ResourceCollection> textDocuments = new ArrayList<>();
    private final Union cachedResources = new Union();
    private volatile boolean cached = false;
    private String encoding = null;

    public ResourceList() {
        this.cachedResources.setCache(true);
    }

    public void add(ResourceCollection resourceCollection) {
        if (!isReference()) {
            this.textDocuments.add(resourceCollection);
            setChecked(false);
            return;
        }
        throw noChildrenAllowed();
    }

    public final void addFilterChain(FilterChain filterChain) {
        if (!isReference()) {
            this.filterChains.add(filterChain);
            setChecked(false);
            return;
        }
        throw noChildrenAllowed();
    }

    public final void setEncoding(String str) {
        if (!isReference()) {
            this.encoding = str;
            return;
        }
        throw tooManyAttributes();
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.encoding != null) {
            throw tooManyAttributes();
        } else if (this.filterChains.size() > 0 || this.textDocuments.size() > 0) {
            throw noChildrenAllowed();
        } else {
            super.setRefid(reference);
        }
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public final synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return ((ResourceList) getCheckedRef()).iterator();
        }
        return cache().iterator();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return ((ResourceList) getCheckedRef()).size();
        }
        return cache().size();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized boolean isFilesystemOnly() {
        if (isReference()) {
            return ((ResourceList) getCheckedRef()).isFilesystemOnly();
        }
        return cache().isFilesystemOnly();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                Iterator<ResourceCollection> it = this.textDocuments.iterator();
                while (it.hasNext()) {
                    ResourceCollection next = it.next();
                    if (next instanceof DataType) {
                        pushAndInvokeCircularReferenceCheck((DataType) next, stack, project);
                    }
                }
                Iterator<FilterChain> it2 = this.filterChains.iterator();
                while (it2.hasNext()) {
                    pushAndInvokeCircularReferenceCheck(it2.next(), stack, project);
                }
                setChecked(true);
            }
        }
    }

    private synchronized ResourceCollection cache() {
        if (!this.cached) {
            dieOnCircularReference();
            Iterator<ResourceCollection> it = this.textDocuments.iterator();
            while (it.hasNext()) {
                for (Resource resource : it.next()) {
                    this.cachedResources.add(read(resource));
                }
            }
            this.cached = true;
        }
        return this.cachedResources;
    }

    private ResourceCollection read(Resource resource) {
        Throwable th;
        IOException e;
        InputStreamReader inputStreamReader;
        try {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(resource.getInputStream());
                try {
                    if (this.encoding == null) {
                        inputStreamReader = new InputStreamReader(bufferedInputStream);
                    } else {
                        inputStreamReader = new InputStreamReader(bufferedInputStream, this.encoding);
                    }
                    ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
                    chainReaderHelper.setPrimaryReader(inputStreamReader);
                    chainReaderHelper.setFilterChains(this.filterChains);
                    chainReaderHelper.setProject(getProject());
                    BufferedReader bufferedReader = new BufferedReader(chainReaderHelper.getAssembledReader());
                    Union union = new Union();
                    union.setCache(true);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            union.add(parse(readLine));
                        } else {
                            FileUtils.close(bufferedInputStream);
                            return union;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    throw new BuildException("Unable to read resource " + resource.getName() + ": " + e, e, getLocation());
                }
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close((InputStream) null);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            FileUtils.close((InputStream) null);
            throw th;
        }
    }

    private Resource parse(String str) {
        Object parseProperties = PropertyHelper.getPropertyHelper(getProject()).parseProperties(str);
        if (parseProperties instanceof Resource) {
            return (Resource) parseProperties;
        }
        String obj = parseProperties.toString();
        if (obj.indexOf(":") != -1) {
            try {
                return new URLResource(obj);
            } catch (BuildException unused) {
            }
        }
        return new FileResource(getProject(), obj);
    }
}
