package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.AbstractFileSet;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.ant.util.SourceFileScanner;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class ExecuteOn extends ExecTask {
    protected Vector<AbstractFileSet> filesets = new Vector<>();
    private Union resources = null;
    private boolean relative = false;
    private boolean parallel = false;
    private boolean forwardSlash = false;
    protected String type = "file";
    protected Commandline.Marker srcFilePos = null;
    private boolean skipEmpty = false;
    protected Commandline.Marker targetFilePos = null;
    protected Mapper mapperElement = null;
    protected FileNameMapper mapper = null;
    protected File destDir = null;
    private int maxParallel = -1;
    private boolean addSourceFile = true;
    private boolean verbose = false;
    private boolean ignoreMissing = true;
    private boolean force = false;
    protected boolean srcIsFirst = true;

    public void addFileset(FileSet fileSet) {
        this.filesets.addElement(fileSet);
    }

    public void addDirset(DirSet dirSet) {
        this.filesets.addElement(dirSet);
    }

    public void addFilelist(FileList fileList) {
        add(fileList);
    }

    public void add(ResourceCollection resourceCollection) {
        if (this.resources == null) {
            this.resources = new Union();
        }
        this.resources.add(resourceCollection);
    }

    public void setRelative(boolean z) {
        this.relative = z;
    }

    public void setParallel(boolean z) {
        this.parallel = z;
    }

    public void setType(FileDirBoth fileDirBoth) {
        this.type = fileDirBoth.getValue();
    }

    public void setSkipEmptyFilesets(boolean z) {
        this.skipEmpty = z;
    }

    public void setDest(File file) {
        this.destDir = file;
    }

    public void setForwardslash(boolean z) {
        this.forwardSlash = z;
    }

    public void setMaxParallel(int i) {
        this.maxParallel = i;
    }

    public void setAddsourcefile(boolean z) {
        this.addSourceFile = z;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setIgnoremissing(boolean z) {
        this.ignoreMissing = z;
    }

    public void setForce(boolean z) {
        this.force = z;
    }

    public Commandline.Marker createSrcfile() {
        if (this.srcFilePos == null) {
            this.srcFilePos = this.cmdl.createMarker();
            return this.srcFilePos;
        }
        throw new BuildException(getTaskType() + " doesn't support multiple srcfile elements.", getLocation());
    }

    public Commandline.Marker createTargetfile() {
        if (this.targetFilePos == null) {
            this.targetFilePos = this.cmdl.createMarker();
            this.srcIsFirst = this.srcFilePos != null;
            return this.targetFilePos;
        }
        throw new BuildException(getTaskType() + " doesn't support multiple targetfile elements.", getLocation());
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapperElement == null) {
            this.mapperElement = new Mapper(getProject());
            return this.mapperElement;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS, getLocation());
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void checkConfiguration() {
        if ("execon".equals(getTaskName())) {
            log("!! execon is deprecated. Use apply instead. !!");
        }
        super.checkConfiguration();
        if (this.filesets.size() == 0 && this.resources == null) {
            throw new BuildException("no resources specified", getLocation());
        } else if (this.targetFilePos != null && this.mapperElement == null) {
            throw new BuildException("targetfile specified without mapper", getLocation());
        } else if (this.destDir == null || this.mapperElement != null) {
            Mapper mapper = this.mapperElement;
            if (mapper != null) {
                this.mapper = mapper.getImplementation();
            }
        } else {
            throw new BuildException("dest specified without mapper", getLocation());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public ExecuteStreamHandler createHandler() throws BuildException {
        return this.redirectorElement == null ? super.createHandler() : new PumpStreamHandler();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void setupRedirector() {
        super.setupRedirector();
        this.redirector.setAppendProperties(true);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    protected void runExec(Execute execute) throws BuildException {
        int i;
        boolean z;
        int i2;
        try {
            try {
                Vector<String> vector = new Vector<>();
                Vector<File> vector2 = new Vector<>();
                int size = this.filesets.size();
                int i3 = 0;
                boolean z2 = false;
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    i = 3;
                    if (i3 >= size) {
                        break;
                    }
                    String str = this.type;
                    AbstractFileSet elementAt = this.filesets.elementAt(i3);
                    if ((elementAt instanceof DirSet) && !"dir".equals(this.type)) {
                        log("Found a nested dirset but type is " + this.type + ". Temporarily switching to type=\"dir\" on the assumption that you really did mean <dirset> not <fileset>.", 4);
                        str = "dir";
                    }
                    File dir = elementAt.getDir(getProject());
                    DirectoryScanner directoryScanner = elementAt.getDirectoryScanner(getProject());
                    if (!"dir".equals(str)) {
                        int i6 = i4;
                        for (String str2 : getFiles(dir, directoryScanner)) {
                            i6++;
                            vector.addElement(str2);
                            vector2.addElement(dir);
                        }
                        i4 = i6;
                    }
                    if (!"file".equals(str)) {
                        int i7 = i5;
                        for (String str3 : getDirs(dir, directoryScanner)) {
                            i7++;
                            vector.addElement(str3);
                            vector2.addElement(dir);
                        }
                        i5 = i7;
                    }
                    if (vector.size() == 0 && this.skipEmpty) {
                        logSkippingFileset(str, directoryScanner, dir);
                    } else if (!this.parallel) {
                        String[] strArr = new String[vector.size()];
                        vector.copyInto(strArr);
                        boolean z3 = z2;
                        int i8 = 0;
                        while (i8 < strArr.length) {
                            String[] commandline = getCommandline(strArr[i8], dir);
                            log(Commandline.describeCommand(commandline), 3);
                            execute.setCommandline(commandline);
                            if (this.redirectorElement != null) {
                                setupRedirector();
                                this.redirectorElement.configure(this.redirector, strArr[i8]);
                            }
                            if (this.redirectorElement != null || z3) {
                                execute.setStreamHandler(this.redirector.createHandler());
                            }
                            runExecute(execute);
                            i8++;
                            z3 = true;
                        }
                        vector.removeAllElements();
                        vector2.removeAllElements();
                        z2 = z3;
                    }
                    i3++;
                }
                if (this.resources != null) {
                    Iterator<Resource> it = this.resources.iterator();
                    while (it.hasNext()) {
                        Resource next = it.next();
                        if (next.isExists() || !this.ignoreMissing) {
                            File file = null;
                            String name = next.getName();
                            FileProvider fileProvider = (FileProvider) next.mo14823as(FileProvider.class);
                            if (fileProvider != null) {
                                FileResource asFileResource = ResourceUtils.asFileResource(fileProvider);
                                file = asFileResource.getBaseDir();
                                if (file == null) {
                                    name = asFileResource.getFile().getAbsolutePath();
                                    i2 = 1;
                                } else {
                                    i2 = 1;
                                }
                            } else {
                                i2 = 1;
                            }
                            String[] strArr2 = new String[i2];
                            strArr2[0] = name;
                            if (restrict(strArr2, file).length != 0) {
                                if ((!next.isDirectory() || !next.isExists()) && !"dir".equals(this.type)) {
                                    i4++;
                                } else if (next.isDirectory() && !"file".equals(this.type)) {
                                    i5++;
                                }
                                vector2.add(file);
                                vector.add(name);
                                if (!this.parallel) {
                                    String[] commandline2 = getCommandline(name, file);
                                    log(Commandline.describeCommand(commandline2), 3);
                                    execute.setCommandline(commandline2);
                                    if (this.redirectorElement != null) {
                                        setupRedirector();
                                        this.redirectorElement.configure(this.redirector, name);
                                    }
                                    if (this.redirectorElement != null || z2) {
                                        execute.setStreamHandler(this.redirector.createHandler());
                                    }
                                    runExecute(execute);
                                    vector.removeAllElements();
                                    vector2.removeAllElements();
                                    z2 = true;
                                }
                            }
                        }
                    }
                    z = z2;
                } else {
                    z = z2;
                }
                if (this.parallel && (vector.size() > 0 || !this.skipEmpty)) {
                    runParallel(execute, vector, vector2);
                    z = true;
                }
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Applied ");
                    sb.append(this.cmdl.getExecutable());
                    sb.append(" to ");
                    sb.append(i4);
                    sb.append(" file");
                    sb.append(i4 != 1 ? "s" : "");
                    sb.append(" and ");
                    sb.append(i5);
                    sb.append(" director");
                    sb.append(i5 != 1 ? "ies" : "y");
                    sb.append(Consts.f23430h);
                    String sb2 = sb.toString();
                    if (this.verbose) {
                        i = 2;
                    }
                    log(sb2, i);
                }
            } catch (IOException e) {
                throw new BuildException("Execute failed: " + e, e, getLocation());
            }
        } finally {
            logFlush();
            this.redirector.setAppendProperties(false);
            this.redirector.setProperties();
        }
    }

    private void logSkippingFileset(String str, DirectoryScanner directoryScanner, File file) {
        int i = 0;
        int includedFilesCount = !"dir".equals(str) ? directoryScanner.getIncludedFilesCount() : 0;
        if (!"file".equals(str)) {
            i = directoryScanner.getIncludedDirsCount();
        }
        int i2 = includedFilesCount + i;
        StringBuilder sb = new StringBuilder();
        sb.append("Skipping fileset for directory ");
        sb.append(file);
        sb.append(". It is ");
        sb.append(i2 > 0 ? "up to date." : "empty.");
        log(sb.toString(), this.verbose ? 2 : 3);
    }

    protected String[] getCommandline(String[] strArr, File[] fileArr) {
        String str;
        String str2;
        char c = File.separatorChar;
        Vector vector = new Vector();
        if (this.targetFilePos != null) {
            HashSet hashSet = new HashSet();
            for (String str3 : strArr) {
                String[] mapFileName = this.mapper.mapFileName(str3);
                if (mapFileName != null) {
                    for (int i = 0; i < mapFileName.length; i++) {
                        if (!this.relative) {
                            str2 = new File(this.destDir, mapFileName[i]).getAbsolutePath();
                        } else {
                            str2 = mapFileName[i];
                        }
                        if (this.forwardSlash && c != '/') {
                            str2 = str2.replace(c, IOUtils.DIR_SEPARATOR_UNIX);
                        }
                        if (!hashSet.contains(str2)) {
                            vector.addElement(str2);
                            hashSet.add(str2);
                        }
                    }
                }
            }
        }
        String[] strArr2 = (String[]) vector.toArray(new String[vector.size()]);
        if (!this.addSourceFile) {
            strArr = new String[0];
        }
        String[] commandline = this.cmdl.getCommandline();
        String[] strArr3 = new String[commandline.length + strArr.length + strArr2.length];
        int length = commandline.length;
        Commandline.Marker marker = this.srcFilePos;
        if (marker != null) {
            length = marker.getPosition();
        }
        Commandline.Marker marker2 = this.targetFilePos;
        if (marker2 != null) {
            int position = marker2.getPosition();
            if (length < position || (length == position && this.srcIsFirst)) {
                System.arraycopy(commandline, 0, strArr3, 0, length);
                System.arraycopy(commandline, length, strArr3, strArr.length + length, position - length);
                insertTargetFiles(strArr2, strArr3, strArr.length + position, this.targetFilePos.getPrefix(), this.targetFilePos.getSuffix());
                System.arraycopy(commandline, position, strArr3, strArr.length + position + strArr2.length, commandline.length - position);
            } else {
                System.arraycopy(commandline, 0, strArr3, 0, position);
                insertTargetFiles(strArr2, strArr3, position, this.targetFilePos.getPrefix(), this.targetFilePos.getSuffix());
                System.arraycopy(commandline, position, strArr3, strArr2.length + position, length - position);
                System.arraycopy(commandline, length, strArr3, strArr.length + length + strArr2.length, commandline.length - length);
                length += strArr2.length;
            }
        } else {
            System.arraycopy(commandline, 0, strArr3, 0, length);
            System.arraycopy(commandline, length, strArr3, strArr.length + length, commandline.length - length);
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (this.relative) {
                str = strArr[i2];
            } else {
                str = new File(fileArr[i2], strArr[i2]).getAbsolutePath();
            }
            if (this.forwardSlash && c != '/') {
                str = str.replace(c, IOUtils.DIR_SEPARATOR_UNIX);
            }
            Commandline.Marker marker3 = this.srcFilePos;
            if (marker3 != null && (marker3.getPrefix().length() > 0 || this.srcFilePos.getSuffix().length() > 0)) {
                str = this.srcFilePos.getPrefix() + str + this.srcFilePos.getSuffix();
            }
            strArr3[length + i2] = str;
        }
        return strArr3;
    }

    protected String[] getCommandline(String str, File file) {
        return getCommandline(new String[]{str}, new File[]{file});
    }

    protected String[] getFiles(File file, DirectoryScanner directoryScanner) {
        return restrict(directoryScanner.getIncludedFiles(), file);
    }

    protected String[] getDirs(File file, DirectoryScanner directoryScanner) {
        return restrict(directoryScanner.getIncludedDirectories(), file);
    }

    protected String[] getFilesAndDirs(FileList fileList) {
        return restrict(fileList.getFiles(getProject()), fileList.getDir(getProject()));
    }

    private String[] restrict(String[] strArr, File file) {
        return (this.mapper == null || this.force) ? strArr : new SourceFileScanner(this).restrict(strArr, file, this.destDir, this.mapper);
    }

    protected void runParallel(Execute execute, Vector<String> vector, Vector<File> vector2) throws IOException, BuildException {
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        File[] fileArr = new File[vector2.size()];
        vector2.copyInto(fileArr);
        if (this.maxParallel <= 0 || strArr.length == 0) {
            String[] commandline = getCommandline(strArr, fileArr);
            log(Commandline.describeCommand(commandline), 3);
            execute.setCommandline(commandline);
            if (this.redirectorElement != null) {
                setupRedirector();
                this.redirectorElement.configure(this.redirector, null);
                execute.setStreamHandler(this.redirector.createHandler());
            }
            runExecute(execute);
            return;
        }
        int size = vector.size();
        int i = 0;
        while (size > 0) {
            int min = Math.min(size, this.maxParallel);
            String[] strArr2 = new String[min];
            System.arraycopy(strArr, i, strArr2, 0, min);
            File[] fileArr2 = new File[min];
            System.arraycopy(fileArr, i, fileArr2, 0, min);
            String[] commandline2 = getCommandline(strArr2, fileArr2);
            log(Commandline.describeCommand(commandline2), 3);
            execute.setCommandline(commandline2);
            if (this.redirectorElement != null) {
                setupRedirector();
                this.redirectorElement.configure(this.redirector, null);
            }
            if (this.redirectorElement != null || i > 0) {
                execute.setStreamHandler(this.redirector.createHandler());
            }
            runExecute(execute);
            size -= min;
            i += min;
        }
    }

    private static void insertTargetFiles(String[] strArr, String[] strArr2, int i, String str, String str2) {
        if (str.length() == 0 && str2.length() == 0) {
            System.arraycopy(strArr, 0, strArr2, i, strArr.length);
            return;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr2[i + i2] = str + strArr[i2] + str2;
        }
    }

    /* loaded from: classes2.dex */
    public static class FileDirBoth extends EnumeratedAttribute {
        public static final String DIR = "dir";
        public static final String FILE = "file";

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"file", "dir", "both"};
        }
    }
}
