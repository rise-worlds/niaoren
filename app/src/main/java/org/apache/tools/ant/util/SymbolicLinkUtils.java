package org.apache.tools.ant.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;

/* loaded from: classes2.dex */
public class SymbolicLinkUtils {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final SymbolicLinkUtils PRIMARY_INSTANCE = new SymbolicLinkUtils();

    public static SymbolicLinkUtils getSymbolicLinkUtils() {
        return PRIMARY_INSTANCE;
    }

    protected SymbolicLinkUtils() {
    }

    public boolean isSymbolicLink(File file) throws IOException {
        return isSymbolicLink(file.getParentFile(), file.getName());
    }

    public boolean isSymbolicLink(String str) throws IOException {
        return isSymbolicLink(new File(str));
    }

    public boolean isSymbolicLink(File file, String str) throws IOException {
        File file2 = file != null ? new File(file.getCanonicalPath(), str) : new File(str);
        return !file2.getAbsolutePath().equals(file2.getCanonicalPath());
    }

    public boolean isDanglingSymbolicLink(String str) throws IOException {
        return isDanglingSymbolicLink(new File(str));
    }

    public boolean isDanglingSymbolicLink(File file) throws IOException {
        return isDanglingSymbolicLink(file.getParentFile(), file.getName());
    }

    public boolean isDanglingSymbolicLink(File file, String str) throws IOException {
        File file2 = new File(file, str);
        if (file2.exists()) {
            return false;
        }
        final String name = file2.getName();
        String[] list = file.list(new FilenameFilter() { // from class: org.apache.tools.ant.util.SymbolicLinkUtils.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file3, String str2) {
                return name.equals(str2);
            }
        });
        return list != null && list.length > 0;
    }

    public void deleteSymbolicLink(File file, Task task) throws IOException {
        Throwable th;
        if (isDanglingSymbolicLink(file)) {
            if (!file.delete()) {
                throw new IOException("failed to remove dangling symbolic link " + file);
            }
        } else if (isSymbolicLink(file)) {
            if (file.exists()) {
                File canonicalFile = file.getCanonicalFile();
                boolean z = false;
                if (task == null || canonicalFile.getParentFile().canWrite()) {
                    File createTempFile = FILE_UTILS.createTempFile("symlink", ".tmp", canonicalFile.getParentFile(), false, false);
                    if (FILE_UTILS.isLeadingPath(canonicalFile, file)) {
                        file = new File(createTempFile, FILE_UTILS.removeLeadingPath(canonicalFile, file));
                    }
                    try {
                        try {
                            FILE_UTILS.rename(canonicalFile, createTempFile);
                            try {
                                if (file.delete()) {
                                    try {
                                        FILE_UTILS.rename(createTempFile, canonicalFile);
                                    } catch (IOException e) {
                                        throw new IOException("Couldn't return resource " + createTempFile + " to its original name: " + canonicalFile.getAbsolutePath() + ". Reason: " + e.getMessage() + "\n THE RESOURCE'S NAME ON DISK HAS BEEN CHANGED BY THIS ERROR!\n");
                                    }
                                } else {
                                    throw new IOException("Couldn't delete symlink: " + file + " (was it a real file? is this not a UNIX system?)");
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                z = true;
                                if (z) {
                                    try {
                                        FILE_UTILS.rename(createTempFile, canonicalFile);
                                    } catch (IOException e2) {
                                        throw new IOException("Couldn't return resource " + createTempFile + " to its original name: " + canonicalFile.getAbsolutePath() + ". Reason: " + e2.getMessage() + "\n THE RESOURCE'S NAME ON DISK HAS BEEN CHANGED BY THIS ERROR!\n");
                                    }
                                }
                                throw th;
                            }
                        } catch (IOException e3) {
                            throw new IOException("Couldn't rename resource when attempting to delete '" + file + "'.  Reason: " + e3.getMessage());
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    Execute.runCommand(task, new String[]{"rm", file.getAbsolutePath()});
                }
            } else {
                throw new FileNotFoundException("No such symbolic link: " + file);
            }
        }
    }
}
