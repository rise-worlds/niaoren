package org.apache.tools.ant.types.resources;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.selectors.AbstractSelectorContainer;
import org.apache.tools.ant.types.selectors.FileSelector;

/* loaded from: classes2.dex */
public class Files extends AbstractSelectorContainer implements ResourceCollection {
    private static final Iterator<Resource> EMPTY_ITERATOR = Collections.emptySet().iterator();
    private Vector<PatternSet> additionalPatterns;
    private boolean caseSensitive;
    private PatternSet defaultPatterns;

    /* renamed from: ds */
    private DirectoryScanner f14767ds;
    private boolean followSymlinks;
    private boolean useDefaultExcludes;

    @Override // org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        return true;
    }

    public Files() {
        this.defaultPatterns = new PatternSet();
        this.additionalPatterns = new Vector<>();
        this.useDefaultExcludes = true;
        this.caseSensitive = true;
        this.followSymlinks = true;
        this.f14767ds = null;
    }

    protected Files(Files files) {
        this.defaultPatterns = new PatternSet();
        this.additionalPatterns = new Vector<>();
        this.useDefaultExcludes = true;
        this.caseSensitive = true;
        this.followSymlinks = true;
        this.f14767ds = null;
        this.defaultPatterns = files.defaultPatterns;
        this.additionalPatterns = files.additionalPatterns;
        this.useDefaultExcludes = files.useDefaultExcludes;
        this.caseSensitive = files.caseSensitive;
        this.followSymlinks = files.followSymlinks;
        this.f14767ds = files.f14767ds;
        setProject(files.getProject());
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (hasPatterns(this.defaultPatterns)) {
            throw tooManyAttributes();
        } else if (!this.additionalPatterns.isEmpty()) {
            throw noChildrenAllowed();
        } else if (!hasSelectors()) {
            super.setRefid(reference);
        } else {
            throw noChildrenAllowed();
        }
    }

    public synchronized PatternSet createPatternSet() {
        PatternSet patternSet;
        if (!isReference()) {
            patternSet = new PatternSet();
            this.additionalPatterns.addElement(patternSet);
            this.f14767ds = null;
            setChecked(false);
        } else {
            throw noChildrenAllowed();
        }
        return patternSet;
    }

    public synchronized PatternSet.NameEntry createInclude() {
        if (!isReference()) {
            this.f14767ds = null;
        } else {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createInclude();
    }

    public synchronized PatternSet.NameEntry createIncludesFile() {
        if (!isReference()) {
            this.f14767ds = null;
        } else {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createIncludesFile();
    }

    public synchronized PatternSet.NameEntry createExclude() {
        if (!isReference()) {
            this.f14767ds = null;
        } else {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createExclude();
    }

    public synchronized PatternSet.NameEntry createExcludesFile() {
        if (!isReference()) {
            this.f14767ds = null;
        } else {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createExcludesFile();
    }

    public synchronized void setIncludes(String str) {
        checkAttributesAllowed();
        this.defaultPatterns.setIncludes(str);
        this.f14767ds = null;
    }

    public synchronized void appendIncludes(String[] strArr) {
        checkAttributesAllowed();
        if (strArr != null) {
            for (String str : strArr) {
                this.defaultPatterns.createInclude().setName(str);
            }
            this.f14767ds = null;
        }
    }

    public synchronized void setExcludes(String str) {
        checkAttributesAllowed();
        this.defaultPatterns.setExcludes(str);
        this.f14767ds = null;
    }

    public synchronized void appendExcludes(String[] strArr) {
        checkAttributesAllowed();
        if (strArr != null) {
            for (String str : strArr) {
                this.defaultPatterns.createExclude().setName(str);
            }
            this.f14767ds = null;
        }
    }

    public synchronized void setIncludesfile(File file) throws BuildException {
        checkAttributesAllowed();
        this.defaultPatterns.setIncludesfile(file);
        this.f14767ds = null;
    }

    public synchronized void setExcludesfile(File file) throws BuildException {
        checkAttributesAllowed();
        this.defaultPatterns.setExcludesfile(file);
        this.f14767ds = null;
    }

    public synchronized void setDefaultexcludes(boolean z) {
        checkAttributesAllowed();
        this.useDefaultExcludes = z;
        this.f14767ds = null;
    }

    public synchronized boolean getDefaultexcludes() {
        return isReference() ? getRef().getDefaultexcludes() : this.useDefaultExcludes;
    }

    public synchronized void setCaseSensitive(boolean z) {
        checkAttributesAllowed();
        this.caseSensitive = z;
        this.f14767ds = null;
    }

    public synchronized boolean isCaseSensitive() {
        return isReference() ? getRef().isCaseSensitive() : this.caseSensitive;
    }

    public synchronized void setFollowSymlinks(boolean z) {
        checkAttributesAllowed();
        this.followSymlinks = z;
        this.f14767ds = null;
    }

    public synchronized boolean isFollowSymlinks() {
        return isReference() ? getRef().isFollowSymlinks() : this.followSymlinks;
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return getRef().iterator();
        }
        ensureDirectoryScannerSetup();
        this.f14767ds.scan();
        int includedFilesCount = this.f14767ds.getIncludedFilesCount();
        int includedDirsCount = this.f14767ds.getIncludedDirsCount();
        if (includedFilesCount + includedDirsCount == 0) {
            return EMPTY_ITERATOR;
        }
        FileResourceIterator fileResourceIterator = new FileResourceIterator(getProject());
        if (includedFilesCount > 0) {
            fileResourceIterator.addFiles(this.f14767ds.getIncludedFiles());
        }
        if (includedDirsCount > 0) {
            fileResourceIterator.addFiles(this.f14767ds.getIncludedDirectories());
        }
        return fileResourceIterator;
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return getRef().size();
        }
        ensureDirectoryScannerSetup();
        this.f14767ds.scan();
        return this.f14767ds.getIncludedFilesCount() + this.f14767ds.getIncludedDirsCount();
    }

    public synchronized boolean hasPatterns() {
        if (isReference()) {
            return getRef().hasPatterns();
        }
        dieOnCircularReference();
        if (hasPatterns(this.defaultPatterns)) {
            return true;
        }
        Iterator<PatternSet> it = this.additionalPatterns.iterator();
        while (it.hasNext()) {
            if (hasPatterns(it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.tools.ant.types.selectors.AbstractSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public synchronized void appendSelector(FileSelector fileSelector) {
        if (!isReference()) {
            super.appendSelector(fileSelector);
            this.f14767ds = null;
        } else {
            throw noChildrenAllowed();
        }
    }

    @Override // org.apache.tools.ant.types.selectors.AbstractSelectorContainer, org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getRef().toString();
        }
        Iterator<Resource> it = iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext()) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(File.pathSeparatorChar);
            }
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    @Override // org.apache.tools.ant.types.selectors.AbstractSelectorContainer, org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public synchronized Object clone() {
        if (isReference()) {
            return getRef().clone();
        }
        Files files = (Files) super.clone();
        files.defaultPatterns = (PatternSet) this.defaultPatterns.clone();
        files.additionalPatterns = new Vector<>(this.additionalPatterns.size());
        Iterator<PatternSet> it = this.additionalPatterns.iterator();
        while (it.hasNext()) {
            files.additionalPatterns.add((PatternSet) it.next().clone());
        }
        return files;
    }

    public String[] mergeIncludes(Project project) {
        return mergePatterns(project).getIncludePatterns(project);
    }

    public String[] mergeExcludes(Project project) {
        return mergePatterns(project).getExcludePatterns(project);
    }

    public synchronized PatternSet mergePatterns(Project project) {
        if (isReference()) {
            return getRef().mergePatterns(project);
        }
        dieOnCircularReference();
        PatternSet patternSet = new PatternSet();
        patternSet.append(this.defaultPatterns, project);
        int size = this.additionalPatterns.size();
        for (int i = 0; i < size; i++) {
            patternSet.append(this.additionalPatterns.elementAt(i), project);
        }
        return patternSet;
    }

    protected Files getRef() {
        return (Files) getCheckedRef();
    }

    private synchronized void ensureDirectoryScannerSetup() {
        dieOnCircularReference();
        if (this.f14767ds == null) {
            this.f14767ds = new DirectoryScanner();
            PatternSet mergePatterns = mergePatterns(getProject());
            this.f14767ds.setIncludes(mergePatterns.getIncludePatterns(getProject()));
            this.f14767ds.setExcludes(mergePatterns.getExcludePatterns(getProject()));
            this.f14767ds.setSelectors(getSelectors(getProject()));
            if (this.useDefaultExcludes) {
                this.f14767ds.addDefaultExcludes();
            }
            this.f14767ds.setCaseSensitive(this.caseSensitive);
            this.f14767ds.setFollowSymlinks(this.followSymlinks);
        }
    }

    private boolean hasPatterns(PatternSet patternSet) {
        String[] includePatterns = patternSet.getIncludePatterns(getProject());
        return (includePatterns != null && includePatterns.length > 0) || (includePatterns != null && patternSet.getExcludePatterns(getProject()).length > 0);
    }
}
