package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Touchable;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class Touch extends Task {
    public static final DateFormatFactory DEFAULT_DF_FACTORY = new DateFormatFactory() { // from class: org.apache.tools.ant.taskdefs.Touch.1
        @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
        public DateFormat getPrimaryFormat() {
            return DateFormat.getDateTimeInstance(3, 3, Locale.US);
        }

        @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
        public DateFormat getFallbackFormat() {
            return DateFormat.getDateTimeInstance(3, 2, Locale.US);
        }
    };
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private String dateTime;
    private boolean dateTimeConfigured;
    private File file;
    private boolean mkdirs;
    private Union resources;
    private long millis = -1;
    private Vector filesets = new Vector();
    private boolean verbose = true;
    private FileNameMapper fileNameMapper = null;
    private DateFormatFactory dfFactory = DEFAULT_DF_FACTORY;

    /* loaded from: classes2.dex */
    public interface DateFormatFactory {
        DateFormat getFallbackFormat();

        DateFormat getPrimaryFormat();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMillis(long j) {
        this.millis = j;
    }

    public void setDatetime(String str) {
        if (this.dateTime != null) {
            log("Resetting datetime attribute to " + str, 3);
        }
        this.dateTime = str;
        this.dateTimeConfigured = false;
    }

    public void setMkdirs(boolean z) {
        this.mkdirs = z;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setPattern(final String str) {
        this.dfFactory = new DateFormatFactory() { // from class: org.apache.tools.ant.taskdefs.Touch.2
            @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
            public DateFormat getFallbackFormat() {
                return null;
            }

            @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
            public DateFormat getPrimaryFormat() {
                return new SimpleDateFormat(str);
            }
        };
    }

    public void addConfiguredMapper(Mapper mapper) {
        add(mapper.getImplementation());
    }

    public void add(FileNameMapper fileNameMapper) throws BuildException {
        if (this.fileNameMapper == null) {
            this.fileNameMapper = fileNameMapper;
            return;
        }
        throw new BuildException("Only one mapper may be added to the " + getTaskName() + " task.");
    }

    public void addFileset(FileSet fileSet) {
        this.filesets.add(fileSet);
        add(fileSet);
    }

    public void addFilelist(FileList fileList) {
        add(fileList);
    }

    public synchronized void add(ResourceCollection resourceCollection) {
        this.resources = this.resources == null ? new Union() : this.resources;
        this.resources.add(resourceCollection);
    }

    protected synchronized void checkConfiguration() throws BuildException {
        if (this.file == null && this.resources == null) {
            throw new BuildException("Specify at least one source--a file or resource collection.");
        }
        if (this.file != null && this.file.exists() && this.file.isDirectory()) {
            throw new BuildException("Use a resource collection to touch directories.");
        }
        if (this.dateTime != null && !this.dateTimeConfigured) {
            long j = this.millis;
            if ("now".equalsIgnoreCase(this.dateTime)) {
                j = System.currentTimeMillis();
            } else {
                ParseException e = null;
                try {
                    j = this.dfFactory.getPrimaryFormat().parse(this.dateTime).getTime();
                } catch (ParseException e2) {
                    e = e2;
                    DateFormat fallbackFormat = this.dfFactory.getFallbackFormat();
                    if (fallbackFormat != null) {
                        try {
                            j = fallbackFormat.parse(this.dateTime).getTime();
                        } catch (ParseException e3) {
                            e = e3;
                        }
                    }
                }
                if (e != null) {
                    throw new BuildException(e.getMessage(), e, getLocation());
                } else if (j < 0) {
                    throw new BuildException("Date of " + this.dateTime + " results in negative milliseconds value relative to epoch (January 1, 1970, 00:00:00 GMT).");
                }
            }
            log("Setting millis to " + j + " from datetime attribute", this.millis < 0 ? 4 : 3);
            setMillis(j);
            this.dateTimeConfigured = true;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        checkConfiguration();
        touch();
    }

    protected void touch() throws BuildException {
        long timestamp = getTimestamp();
        File file = this.file;
        if (file != null) {
            touch(new FileResource(file.getParentFile(), this.file.getName()), timestamp);
        }
        Union union = this.resources;
        if (union != null) {
            Iterator<Resource> it = union.iterator();
            while (it.hasNext()) {
                Resource next = it.next();
                if (((Touchable) next.mo14823as(Touchable.class)) != null) {
                    touch(next, timestamp);
                } else {
                    throw new BuildException("Can't touch " + next);
                }
            }
            int size = this.filesets.size();
            for (int i = 0; i < size; i++) {
                FileSet fileSet = (FileSet) this.filesets.elementAt(i);
                DirectoryScanner directoryScanner = fileSet.getDirectoryScanner(getProject());
                File dir = fileSet.getDir(getProject());
                for (String str : directoryScanner.getIncludedDirectories()) {
                    touch(new FileResource(dir, str), timestamp);
                }
            }
        }
    }

    protected void touch(File file) {
        touch(file, getTimestamp());
    }

    private long getTimestamp() {
        long j = this.millis;
        return j < 0 ? System.currentTimeMillis() : j;
    }

    private void touch(Resource resource, long j) {
        FileNameMapper fileNameMapper = this.fileNameMapper;
        if (fileNameMapper == null) {
            FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
            if (fileProvider != null) {
                touch(fileProvider.getFile(), j);
            } else {
                ((Touchable) resource.mo14823as(Touchable.class)).touch(j);
            }
        } else {
            String[] mapFileName = fileNameMapper.mapFileName(resource.getName());
            if (mapFileName != null && mapFileName.length > 0) {
                if (this.millis < 0 && resource.isExists()) {
                    j = resource.getLastModified();
                }
                for (String str : mapFileName) {
                    touch(getProject().resolveFile(str), j);
                }
            }
        }
    }

    private void touch(File file, long j) {
        if (!file.exists()) {
            log("Creating " + file, this.verbose ? 2 : 3);
            try {
                FILE_UTILS.createNewFile(file, this.mkdirs);
            } catch (IOException e) {
                throw new BuildException("Could not create " + file, e, getLocation());
            }
        }
        if (file.canWrite()) {
            FILE_UTILS.setFileLastModified(file, j);
            return;
        }
        throw new BuildException("Can not change modification date of read-only file " + file);
    }
}
