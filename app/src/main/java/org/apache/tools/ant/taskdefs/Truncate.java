package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class Truncate extends Task {
    private static final int BUFFER_SIZE = 1024;
    private static final String INVALID_LENGTH = "Cannot truncate to length ";
    private static final String NO_CHILD = "No files specified.";
    private static final String READ_WRITE = "rw";
    private Long adjust;
    private Long length;
    private Path path;
    private static final Long ZERO = new Long(0);
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final byte[] FILL_BUFFER = new byte[1024];
    private boolean create = true;
    private boolean mkdirs = false;

    public void setFile(File file) {
        add(new FileResource(file));
    }

    public void add(ResourceCollection resourceCollection) {
        getPath().add(resourceCollection);
    }

    public void setAdjust(Long l) {
        this.adjust = l;
    }

    public void setLength(Long l) {
        this.length = l;
        if (l != null && l.longValue() < 0) {
            throw new BuildException(INVALID_LENGTH + l);
        }
    }

    public void setCreate(boolean z) {
        this.create = z;
    }

    public void setMkdirs(boolean z) {
        this.mkdirs = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.length == null || this.adjust == null) {
            if (this.length == null && this.adjust == null) {
                this.length = ZERO;
            }
            Path path = this.path;
            if (path != null) {
                Iterator<Resource> it = path.iterator();
                while (it.hasNext()) {
                    File file = ((FileProvider) it.next().mo14823as(FileProvider.class)).getFile();
                    if (shouldProcess(file)) {
                        process(file);
                    }
                }
                return;
            }
            throw new BuildException(NO_CHILD);
        }
        throw new BuildException("length and adjust are mutually exclusive options");
    }

    private boolean shouldProcess(File file) {
        if (file.isFile()) {
            return true;
        }
        if (!this.create) {
            return false;
        }
        IOException e = null;
        try {
            if (FILE_UTILS.createNewFile(file, this.mkdirs)) {
                return true;
            }
        } catch (IOException e2) {
            e = e2;
        }
        String str = "Unable to create " + file;
        if (e == null) {
            log(str, 1);
            return false;
        }
        throw new BuildException(str, e);
    }

    private void process(File file) {
        long length = file.length();
        Long l = this.length;
        long longValue = l == null ? this.adjust.longValue() + length : l.longValue();
        if (length != longValue) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, READ_WRITE);
                try {
                    try {
                        if (longValue > length) {
                            randomAccessFile.seek(length);
                            while (length < longValue) {
                                long min = Math.min(FILL_BUFFER.length, longValue - length);
                                randomAccessFile.write(FILL_BUFFER, 0, (int) min);
                                length += min;
                            }
                        } else {
                            randomAccessFile.setLength(longValue);
                        }
                        try {
                            randomAccessFile.close();
                        } catch (IOException e) {
                            log("Caught " + e + " closing " + randomAccessFile, 1);
                        }
                    } catch (Throwable th) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e2) {
                            log("Caught " + e2 + " closing " + randomAccessFile, 1);
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    throw new BuildException("Exception working with " + randomAccessFile, e3);
                }
            } catch (Exception e4) {
                throw new BuildException("Could not open " + file + " for writing", e4);
            }
        }
    }

    private synchronized Path getPath() {
        if (this.path == null) {
            this.path = new Path(getProject());
        }
        return this.path;
    }
}
