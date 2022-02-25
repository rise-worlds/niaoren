package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class CopyPath extends Task {
    public static final String ERROR_NO_DESTDIR = "No destDir specified";
    public static final String ERROR_NO_MAPPER = "No mapper specified";
    public static final String ERROR_NO_PATH = "No path specified";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File destDir;
    private FileNameMapper mapper;
    private Path path;
    private long granularity = FILE_UTILS.getFileTimestampGranularity();
    private boolean preserveLastModified = false;

    public void setDestDir(File file) {
        this.destDir = file;
    }

    public void add(FileNameMapper fileNameMapper) {
        if (this.mapper == null) {
            this.mapper = fileNameMapper;
            return;
        }
        throw new BuildException("Only one mapper allowed");
    }

    public void setPath(Path path) {
        createPath().append(path);
    }

    public void setPathRef(Reference reference) {
        createPath().setRefid(reference);
    }

    public Path createPath() {
        if (this.path == null) {
            this.path = new Path(getProject());
        }
        return this.path;
    }

    public void setGranularity(long j) {
        this.granularity = j;
    }

    public void setPreserveLastModified(boolean z) {
        this.preserveLastModified = z;
    }

    protected void validateAttributes() throws BuildException {
        if (this.destDir == null) {
            throw new BuildException(ERROR_NO_DESTDIR);
        } else if (this.mapper == null) {
            throw new BuildException(ERROR_NO_MAPPER);
        } else if (this.path == null) {
            throw new BuildException(ERROR_NO_PATH);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        int i;
        File file;
        IOException e;
        log("This task should have never been released and was obsoleted by ResourceCollection support in <copy> available since Ant 1.7.0.  Don't use it.", 0);
        validateAttributes();
        String[] list = this.path.list();
        int i2 = 3;
        if (list.length == 0) {
            log("Path is empty", 3);
            return;
        }
        int i3 = 0;
        while (i3 < list.length) {
            String str = list[i3];
            File file2 = new File(str);
            String[] mapFileName = this.mapper.mapFileName(str);
            int i4 = 0;
            while (i4 < mapFileName.length) {
                File file3 = new File(this.destDir, mapFileName[i4]);
                if (file2.equals(file3)) {
                    log("Skipping self-copy of " + str, i2);
                    i = i4;
                    mapFileName = mapFileName;
                    file2 = file2;
                } else if (file2.isDirectory()) {
                    log("Skipping directory " + str);
                    i = i4;
                    mapFileName = mapFileName;
                    file2 = file2;
                } else {
                    try {
                        log("Copying " + file2 + " to " + file3, i2);
                        file = file3;
                        i = i4;
                        mapFileName = mapFileName;
                        file2 = file2;
                    } catch (IOException e2) {
                        e = e2;
                        file = file3;
                        file2 = file2;
                    }
                    try {
                        FILE_UTILS.copyFile(file2, file3, (FilterSetCollection) null, (Vector) null, false, this.preserveLastModified, (String) null, (String) null, getProject());
                    } catch (IOException e3) {
                        e = e3;
                        String str2 = "Failed to copy " + file2 + " to " + file + " due to " + e.getMessage();
                        if (file.exists() && !file.delete()) {
                            str2 = str2 + " and I couldn't delete the corrupt " + file;
                        }
                        throw new BuildException(str2, e, getLocation());
                    }
                }
                i4 = i + 1;
                i2 = 3;
            }
            i3++;
            i2 = 3;
        }
    }
}
