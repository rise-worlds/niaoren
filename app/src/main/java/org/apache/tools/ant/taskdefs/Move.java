package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterSet;
import org.apache.tools.ant.types.FilterSetCollection;

/* loaded from: classes2.dex */
public class Move extends Copy {
    private boolean performGc = C3209Os.isFamily(C3209Os.FAMILY_WINDOWS);

    public Move() {
        setOverwrite(true);
    }

    public void setPerformGcOnFailedDelete(boolean z) {
        this.performGc = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Copy
    public void validateAttributes() throws BuildException {
        if (this.file == null || !this.file.isDirectory()) {
            super.validateAttributes();
        } else if ((this.destFile == null || this.destDir == null) && !(this.destFile == null && this.destDir == null)) {
            this.destFile = this.destFile == null ? new File(this.destDir, this.file.getName()) : this.destFile;
            this.destDir = this.destDir == null ? this.destFile.getParentFile() : this.destDir;
            this.completeDirMap.put(this.file, this.destFile);
            this.file = null;
        } else {
            throw new BuildException("One and only one of tofile and todir must be set.");
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Copy
    protected void doFileOperations() {
        if (this.completeDirMap.size() > 0) {
            for (File file : this.completeDirMap.keySet()) {
                File file2 = this.completeDirMap.get(file);
                try {
                    log("Attempting to rename dir: " + file + " to " + file2, this.verbosity);
                    if (!renameFile(file, file2, this.filtering, this.forceOverwrite)) {
                        FileSet fileSet = new FileSet();
                        fileSet.setProject(getProject());
                        fileSet.setDir(file);
                        addFileset(fileSet);
                        DirectoryScanner directoryScanner = fileSet.getDirectoryScanner(getProject());
                        scan(file, file2, directoryScanner.getIncludedFiles(), directoryScanner.getIncludedDirectories());
                    }
                } catch (IOException e) {
                    throw new BuildException("Failed to rename dir " + file + " to " + file2 + " due to " + e.getMessage(), e, getLocation());
                }
            }
        }
        int size = this.fileCopyMap.size();
        if (size > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Moving ");
            sb.append(size);
            sb.append(" file");
            sb.append(size == 1 ? "" : "s");
            sb.append(" to ");
            sb.append(this.destDir.getAbsolutePath());
            log(sb.toString());
            for (String str : this.fileCopyMap.keySet()) {
                File file3 = new File(str);
                if (file3.exists()) {
                    String[] strArr = this.fileCopyMap.get(str);
                    boolean z = false;
                    for (int i = 0; i < strArr.length; i++) {
                        String str2 = strArr[i];
                        if (str.equals(str2)) {
                            log("Skipping self-move of " + str, this.verbosity);
                            z = true;
                        } else {
                            File file4 = new File(str2);
                            if (i + 1 != strArr.length || z) {
                                copyFile(file3, file4, this.filtering, this.forceOverwrite);
                            } else {
                                moveFile(file3, file4, this.filtering, this.forceOverwrite);
                            }
                        }
                    }
                }
            }
        }
        if (this.includeEmpty) {
            int i2 = 0;
            for (String str3 : this.dirCopyMap.keySet()) {
                String[] strArr2 = this.dirCopyMap.get(str3);
                int i3 = i2;
                boolean z2 = false;
                for (int i4 = 0; i4 < strArr2.length; i4++) {
                    if (str3.equals(strArr2[i4])) {
                        log("Skipping self-move of " + str3, this.verbosity);
                        z2 = true;
                    } else {
                        File file5 = new File(strArr2[i4]);
                        if (!file5.exists()) {
                            if (file5.mkdirs() || file5.exists()) {
                                i3++;
                            } else {
                                log("Unable to create directory " + file5.getAbsolutePath(), 0);
                            }
                        }
                    }
                }
                File file6 = new File(str3);
                if (!z2 && okToDelete(file6)) {
                    deleteDir(file6);
                }
                i2 = i3;
            }
            if (i2 > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Moved ");
                sb2.append(this.dirCopyMap.size());
                sb2.append(" empty director");
                sb2.append(this.dirCopyMap.size() == 1 ? "y" : "ies");
                sb2.append(" to ");
                sb2.append(i2);
                sb2.append(" empty director");
                sb2.append(i2 == 1 ? "y" : "ies");
                sb2.append(" under ");
                sb2.append(this.destDir.getAbsolutePath());
                log(sb2.toString());
            }
        }
    }

    private void moveFile(File file, File file2, boolean z, boolean z2) {
        try {
            log("Attempting to rename: " + file + " to " + file2, this.verbosity);
            if (!renameFile(file, file2, z, this.forceOverwrite)) {
                copyFile(file, file2, z, z2);
                if (!getFileUtils().tryHardToDelete(file, this.performGc)) {
                    throw new BuildException("Unable to delete file " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            throw new BuildException("Failed to rename " + file + " to " + file2 + " due to " + e.getMessage(), e, getLocation());
        }
    }

    private void copyFile(File file, File file2, boolean z, boolean z2) {
        try {
            log("Copying " + file + " to " + file2, this.verbosity);
            FilterSetCollection filterSetCollection = new FilterSetCollection();
            if (z) {
                filterSetCollection.addFilterSet(getProject().getGlobalFilterSet());
            }
            Iterator<FilterSet> it = getFilterSets().iterator();
            while (it.hasNext()) {
                filterSetCollection.addFilterSet(it.next());
            }
            getFileUtils().copyFile(file, file2, filterSetCollection, getFilterChains(), this.forceOverwrite, getPreserveLastModified(), false, getEncoding(), getOutputEncoding(), getProject(), getForce());
        } catch (IOException e) {
            throw new BuildException("Failed to copy " + file + " to " + file2 + " due to " + e.getMessage(), e, getLocation());
        }
    }

    protected boolean okToDelete(File file) {
        String[] list = file.list();
        if (list == null) {
            return false;
        }
        for (String str : list) {
            File file2 = new File(file, str);
            if (!file2.isDirectory() || !okToDelete(file2)) {
                return false;
            }
        }
        return true;
    }

    protected void deleteDir(File file) {
        deleteDir(file, false);
    }

    protected void deleteDir(File file, boolean z) {
        String[] list = file.list();
        if (list != null) {
            for (String str : list) {
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    deleteDir(file2);
                } else if (!z || getFileUtils().tryHardToDelete(file2, this.performGc)) {
                    throw new BuildException("UNEXPECTED ERROR - The file " + file2.getAbsolutePath() + " should not exist!");
                } else {
                    throw new BuildException("Unable to delete file " + file2.getAbsolutePath());
                }
            }
            log("Deleting directory " + file.getAbsolutePath(), this.verbosity);
            if (!getFileUtils().tryHardToDelete(file, this.performGc)) {
                throw new BuildException("Unable to delete directory " + file.getAbsolutePath());
            }
        }
    }

    protected boolean renameFile(File file, File file2, boolean z, boolean z2) throws IOException, BuildException {
        if (file2.isDirectory() || z || getFilterSets().size() > 0 || getFilterChains().size() > 0) {
            return false;
        }
        if (file2.isFile() && !file2.canWrite()) {
            if (!getForce()) {
                throw new IOException("can't replace read-only destination file " + file2);
            } else if (!getFileUtils().tryHardToDelete(file2)) {
                throw new IOException("failed to delete read-only destination file " + file2);
            }
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        } else if (file2.isFile()) {
            file = getFileUtils().normalize(file.getAbsolutePath()).getCanonicalFile();
            file2 = getFileUtils().normalize(file2.getAbsolutePath());
            if (file2.getAbsolutePath().equals(file.getAbsolutePath())) {
                log("Rename of " + file + " to " + file2 + " is a no-op.", 3);
                return true;
            } else if (!getFileUtils().areSame(file, file2) && !getFileUtils().tryHardToDelete(file2, this.performGc)) {
                throw new BuildException("Unable to remove existing file " + file2);
            }
        }
        return file.renameTo(file2);
    }
}
