package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.AbstractFileSet;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.Restrict;
import org.apache.tools.ant.types.resources.selectors.Exists;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.ant.types.selectors.NoneSelector;

/* loaded from: classes2.dex */
public class Sync extends Task {
    private MyCopy myCopy;
    private Resources resources = null;
    private SyncTarget syncTarget;

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        this.myCopy = new MyCopy();
        configureTask(this.myCopy);
        this.myCopy.setFiltering(false);
        this.myCopy.setIncludeEmptyDirs(false);
        this.myCopy.setPreserveLastModified(true);
    }

    private void configureTask(Task task) {
        task.setProject(getProject());
        task.setTaskName(getTaskName());
        task.setOwningTarget(getOwningTarget());
        task.init();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        int i;
        File toDir = this.myCopy.getToDir();
        Set set = this.myCopy.nonOrphans;
        boolean z = !toDir.exists() || toDir.list().length < 1;
        log("PASS#1: Copying files to " + toDir, 4);
        this.myCopy.execute();
        if (z) {
            log("NO removing necessary in " + toDir, 4);
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        log("PASS#2: Removing orphan files from " + toDir, 4);
        int[] removeOrphanFiles = removeOrphanFiles(set, toDir, linkedHashSet);
        logRemovedCount(removeOrphanFiles[0], "dangling director", "y", "ies");
        logRemovedCount(removeOrphanFiles[1], "dangling file", "", "s");
        if (!this.myCopy.getIncludeEmptyDirs() || getExplicitPreserveEmptyDirs() == Boolean.FALSE) {
            log("PASS#3: Removing empty directories from " + toDir, 4);
            if (!this.myCopy.getIncludeEmptyDirs()) {
                i = removeEmptyDirectories(toDir, false, linkedHashSet);
            } else {
                i = removeEmptyDirectories(linkedHashSet);
            }
            logRemovedCount(i, "empty director", "y", "ies");
        }
    }

    private void logRemovedCount(int i, String str, String str2, String str3) {
        File toDir = this.myCopy.getToDir();
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (i >= 2) {
            str2 = str3;
        }
        sb.append(str2);
        String sb2 = sb.toString();
        if (i > 0) {
            log("Removed " + i + ExpandableTextView.f6958c + sb2 + " from " + toDir, 2);
            return;
        }
        log("NO " + sb2 + " to remove from " + toDir, 3);
    }

    private int[] removeOrphanFiles(Set set, File file, Set set2) {
        DirectoryScanner directoryScanner;
        int[] iArr = {0, 0};
        String[] strArr = (String[]) set.toArray(new String[set.size() + 1]);
        strArr[set.size()] = "";
        SyncTarget syncTarget = this.syncTarget;
        if (syncTarget != null) {
            FileSet fileSet = syncTarget.toFileSet(false);
            fileSet.setDir(file);
            PatternSet mergePatterns = this.syncTarget.mergePatterns(getProject());
            fileSet.appendExcludes(mergePatterns.getIncludePatterns(getProject()));
            fileSet.appendIncludes(mergePatterns.getExcludePatterns(getProject()));
            fileSet.setDefaultexcludes(!this.syncTarget.getDefaultexcludes());
            FileSelector[] selectors = this.syncTarget.getSelectors(getProject());
            if (selectors.length > 0) {
                NoneSelector noneSelector = new NoneSelector();
                for (FileSelector fileSelector : selectors) {
                    noneSelector.appendSelector(fileSelector);
                }
                fileSet.appendSelector(noneSelector);
            }
            directoryScanner = fileSet.getDirectoryScanner(getProject());
        } else {
            directoryScanner = new DirectoryScanner();
            directoryScanner.setBasedir(file);
        }
        directoryScanner.addExcludes(strArr);
        directoryScanner.scan();
        for (String str : directoryScanner.getIncludedFiles()) {
            File file2 = new File(file, str);
            log("Removing orphan file: " + file2, 4);
            file2.delete();
            iArr[1] = iArr[1] + 1;
        }
        String[] includedDirectories = directoryScanner.getIncludedDirectories();
        for (int length = includedDirectories.length - 1; length >= 0; length--) {
            File file3 = new File(file, includedDirectories[length]);
            String[] list = file3.list();
            if (list == null || list.length < 1) {
                log("Removing orphan directory: " + file3, 4);
                file3.delete();
                iArr[0] = iArr[0] + 1;
            }
        }
        Boolean explicitPreserveEmptyDirs = getExplicitPreserveEmptyDirs();
        if (!(explicitPreserveEmptyDirs == null || explicitPreserveEmptyDirs.booleanValue() == this.myCopy.getIncludeEmptyDirs())) {
            FileSet fileSet2 = this.syncTarget.toFileSet(true);
            fileSet2.setDir(file);
            String[] includedDirectories2 = fileSet2.getDirectoryScanner(getProject()).getIncludedDirectories();
            for (int length2 = includedDirectories2.length - 1; length2 >= 0; length2--) {
                set2.add(new File(file, includedDirectories2[length2]));
            }
        }
        return iArr;
    }

    private int removeEmptyDirectories(File file, boolean z, Set set) {
        if (!file.isDirectory()) {
            return 0;
        }
        File[] listFiles = file.listFiles();
        int i = 0;
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                i += removeEmptyDirectories(file2, true, set);
            }
        }
        if (listFiles.length > 0) {
            listFiles = file.listFiles();
        }
        if (listFiles.length >= 1 || !z || set.contains(file)) {
            return i;
        }
        log("Removing empty directory: " + file, 4);
        file.delete();
        return i + 1;
    }

    private int removeEmptyDirectories(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            File file = (File) it.next();
            String[] list = file.list();
            if (list == null || list.length == 0) {
                log("Removing empty directory: " + file, 4);
                file.delete();
                i++;
            }
        }
        return i;
    }

    public void setTodir(File file) {
        this.myCopy.setTodir(file);
    }

    public void setVerbose(boolean z) {
        this.myCopy.setVerbose(z);
    }

    public void setOverwrite(boolean z) {
        this.myCopy.setOverwrite(z);
    }

    public void setIncludeEmptyDirs(boolean z) {
        this.myCopy.setIncludeEmptyDirs(z);
    }

    public void setFailOnError(boolean z) {
        this.myCopy.setFailOnError(z);
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void add(ResourceCollection resourceCollection) {
        if (!(resourceCollection instanceof FileSet) || !resourceCollection.isFilesystemOnly()) {
            if (this.resources == null) {
                Restrict restrict = new Restrict();
                restrict.add(new Exists());
                Resources resources = new Resources();
                this.resources = resources;
                restrict.add(resources);
                this.myCopy.add(restrict);
            }
            this.resources.add(resourceCollection);
            return;
        }
        this.myCopy.add(resourceCollection);
    }

    public void setGranularity(long j) {
        this.myCopy.setGranularity(j);
    }

    public void addPreserveInTarget(SyncTarget syncTarget) {
        if (this.syncTarget == null) {
            this.syncTarget = syncTarget;
            return;
        }
        throw new BuildException("you must not specify multiple preserveintarget elements.");
    }

    private Boolean getExplicitPreserveEmptyDirs() {
        SyncTarget syncTarget = this.syncTarget;
        if (syncTarget == null) {
            return null;
        }
        return syncTarget.getPreserveEmptyDirs();
    }

    /* loaded from: classes2.dex */
    public static class MyCopy extends Copy {
        private Set nonOrphans = new HashSet();

        @Override // org.apache.tools.ant.taskdefs.Copy
        protected boolean supportsNonFileResources() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.tools.ant.taskdefs.Copy
        public void scan(File file, File file2, String[] strArr, String[] strArr2) {
            Sync.assertTrue("No mapper", this.mapperElement == null);
            super.scan(file, file2, strArr, strArr2);
            for (String str : strArr) {
                this.nonOrphans.add(str);
            }
            for (String str2 : strArr2) {
                this.nonOrphans.add(str2);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.tools.ant.taskdefs.Copy
        public Map scan(Resource[] resourceArr, File file) {
            Sync.assertTrue("No mapper", this.mapperElement == null);
            for (Resource resource : resourceArr) {
                this.nonOrphans.add(resource.getName());
            }
            return super.scan(resourceArr, file);
        }

        public File getToDir() {
            return this.destDir;
        }

        public boolean getIncludeEmptyDirs() {
            return this.includeEmpty;
        }
    }

    /* loaded from: classes2.dex */
    public static class SyncTarget extends AbstractFileSet {
        private Boolean preserveEmptyDirs;

        @Override // org.apache.tools.ant.types.AbstractFileSet
        public void setDir(File file) throws BuildException {
            throw new BuildException("preserveintarget doesn't support the dir attribute");
        }

        public void setPreserveEmptyDirs(boolean z) {
            this.preserveEmptyDirs = Boolean.valueOf(z);
        }

        public Boolean getPreserveEmptyDirs() {
            return this.preserveEmptyDirs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FileSet toFileSet(boolean z) {
            FileSet fileSet = new FileSet();
            fileSet.setCaseSensitive(isCaseSensitive());
            fileSet.setFollowSymlinks(isFollowSymlinks());
            fileSet.setMaxLevelsOfSymlinks(getMaxLevelsOfSymlinks());
            fileSet.setProject(getProject());
            if (z) {
                PatternSet mergePatterns = mergePatterns(getProject());
                fileSet.appendIncludes(mergePatterns.getIncludePatterns(getProject()));
                fileSet.appendExcludes(mergePatterns.getExcludePatterns(getProject()));
                Enumeration<FileSelector> selectorElements = selectorElements();
                while (selectorElements.hasMoreElements()) {
                    fileSet.appendSelector(selectorElements.nextElement());
                }
                fileSet.setDefaultexcludes(getDefaultexcludes());
            }
            return fileSet;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(String str, boolean z) {
        if (!z) {
            throw new BuildException("Assertion Error: " + str);
        }
    }
}
