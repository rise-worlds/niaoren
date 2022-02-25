package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/* loaded from: classes2.dex */
public class Expand extends Task {
    private static final int BUFFER_SIZE = 1024;
    public static final String ERROR_MULTIPLE_MAPPERS = "Cannot define more than one mapper";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    public static final String NATIVE_ENCODING = "native-encoding";
    private File dest;
    private String encoding;
    private boolean failOnEmptyArchive;
    private Mapper mapperElement;
    private boolean overwrite;
    private Vector<PatternSet> patternsets;
    private Union resources;
    private boolean resourcesSpecified;
    private boolean scanForUnicodeExtraFields;
    private File source;
    private boolean stripAbsolutePathSpec;

    public Expand() {
        this("UTF8");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Expand(String str) {
        this.overwrite = true;
        this.mapperElement = null;
        this.patternsets = new Vector<>();
        this.resources = new Union();
        this.resourcesSpecified = false;
        this.failOnEmptyArchive = false;
        this.stripAbsolutePathSpec = false;
        this.scanForUnicodeExtraFields = true;
        this.encoding = str;
    }

    public void setFailOnEmptyArchive(boolean z) {
        this.failOnEmptyArchive = z;
    }

    public boolean getFailOnEmptyArchive() {
        return this.failOnEmptyArchive;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if ("expand".equals(getTaskType())) {
            log("!! expand is deprecated. Use unzip instead. !!");
        }
        if (this.source != null || this.resourcesSpecified) {
            File file = this.dest;
            if (file == null) {
                throw new BuildException("Dest attribute must be specified");
            } else if (!file.exists() || this.dest.isDirectory()) {
                File file2 = this.source;
                if (file2 != null) {
                    if (file2.isDirectory()) {
                        throw new BuildException("Src must not be a directory. Use nested filesets instead.", getLocation());
                    } else if (!this.source.exists()) {
                        throw new BuildException("src '" + this.source + "' doesn't exist.");
                    } else if (this.source.canRead()) {
                        expandFile(FILE_UTILS, this.source, this.dest);
                    } else {
                        throw new BuildException("src '" + this.source + "' cannot be read.");
                    }
                }
                Iterator<Resource> it = this.resources.iterator();
                while (it.hasNext()) {
                    Resource next = it.next();
                    if (!next.isExists()) {
                        log("Skipping '" + next.getName() + "' because it doesn't exist.");
                    } else {
                        FileProvider fileProvider = (FileProvider) next.mo14823as(FileProvider.class);
                        if (fileProvider != null) {
                            expandFile(FILE_UTILS, fileProvider.getFile(), this.dest);
                        } else {
                            expandResource(next, this.dest);
                        }
                    }
                }
            } else {
                throw new BuildException("Dest must be a directory.", getLocation());
            }
        } else {
            throw new BuildException("src attribute and/or resources must be specified");
        }
    }

    protected void expandFile(FileUtils fileUtils, File file, File file2) {
        ZipFile zipFile;
        Throwable th;
        IOException e;
        boolean z;
        InputStream inputStream;
        Throwable th2;
        log("Expanding: " + file + " into " + file2, 2);
        FileNameMapper mapper = getMapper();
        if (file.exists()) {
            try {
                try {
                    zipFile = new ZipFile(file, this.encoding, this.scanForUnicodeExtraFields);
                    z = true;
                } catch (Throwable th3) {
                    th = th3;
                    zipFile = null;
                }
            } catch (IOException e2) {
                e = e2;
            }
            try {
                Enumeration<ZipEntry> entries = zipFile.getEntries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    log("extracting " + nextElement.getName(), 4);
                    try {
                        inputStream = zipFile.getInputStream(nextElement);
                        try {
                            extractFile(fileUtils, file, file2, inputStream, nextElement.getName(), new Date(nextElement.getTime()), nextElement.isDirectory(), mapper);
                            FileUtils.close(inputStream);
                            z = false;
                        } catch (Throwable th4) {
                            th2 = th4;
                            FileUtils.close(inputStream);
                            throw th2;
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        inputStream = null;
                    }
                }
                if (z && getFailOnEmptyArchive()) {
                    throw new BuildException("archive '" + file + "' is empty");
                }
                log("expand complete", 3);
                ZipFile.closeQuietly(zipFile);
            } catch (IOException e3) {
                e = e3;
                throw new BuildException("Error while expanding " + file.getPath() + "\n" + e.toString(), e);
            } catch (Throwable th6) {
                th = th6;
                ZipFile.closeQuietly(zipFile);
                throw th;
            }
        } else {
            throw new BuildException("Unable to expand " + file + " as the file does not exist", getLocation());
        }
    }

    protected void expandResource(Resource resource, File file) {
        throw new BuildException("only filesystem based resources are supported by this task.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileNameMapper getMapper() {
        Mapper mapper = this.mapperElement;
        if (mapper != null) {
            return mapper.getImplementation();
        }
        return new IdentityMapper();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void extractFile(FileUtils fileUtils, File file, File file2, InputStream inputStream, String str, Date date, boolean z, FileNameMapper fileNameMapper) throws IOException {
        FileNameMapper fileNameMapper2;
        char c;
        FileOutputStream fileOutputStream;
        Throwable th;
        String str2 = str;
        if (this.stripAbsolutePathSpec && str.length() > 0 && (str2.charAt(0) == File.separatorChar || str2.charAt(0) == '/' || str2.charAt(0) == '\\')) {
            log("stripped absolute path spec from " + str2, 3);
            str2 = str2.substring(1);
        }
        Vector<PatternSet> vector = this.patternsets;
        if (vector == null || vector.size() <= 0) {
            fileNameMapper2 = fileNameMapper;
        } else {
            String replace = str2.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            int size = this.patternsets.size();
            for (int i = 0; i < size; i++) {
                PatternSet elementAt = this.patternsets.elementAt(i);
                String[] includePatterns = elementAt.getIncludePatterns(getProject());
                if (includePatterns == null || includePatterns.length == 0) {
                    includePatterns = new String[]{SelectorUtils.DEEP_TREE_MATCH};
                }
                for (String str3 : includePatterns) {
                    String replace2 = str3.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
                    if (replace2.endsWith(File.separator)) {
                        replace2 = replace2 + SelectorUtils.DEEP_TREE_MATCH;
                    }
                    hashSet.add(replace2);
                }
                String[] excludePatterns = elementAt.getExcludePatterns(getProject());
                if (excludePatterns != null) {
                    for (String str4 : excludePatterns) {
                        String replace3 = str4.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
                        if (replace3.endsWith(File.separator)) {
                            replace3 = replace3 + SelectorUtils.DEEP_TREE_MATCH;
                        }
                        hashSet2.add(replace3);
                    }
                }
            }
            Iterator it = hashSet.iterator();
            boolean z2 = false;
            while (!z2 && it.hasNext()) {
                z2 = SelectorUtils.matchPath((String) it.next(), replace);
            }
            Iterator it2 = hashSet2.iterator();
            while (z2 && it2.hasNext()) {
                z2 = !SelectorUtils.matchPath((String) it2.next(), replace);
            }
            if (!z2) {
                log("skipping " + str2 + " as it is excluded or not included.", 3);
                return;
            }
            fileNameMapper2 = fileNameMapper;
        }
        String[] mapFileName = fileNameMapper2.mapFileName(str2);
        if (mapFileName == null || mapFileName.length == 0) {
            c = 0;
            mapFileName = new String[]{str2};
        } else {
            c = 0;
        }
        File resolveFile = fileUtils.resolveFile(file2, mapFileName[c]);
        try {
            if (this.overwrite || !resolveFile.exists() || resolveFile.lastModified() < date.getTime()) {
                log("expanding " + str2 + " to " + resolveFile, 3);
                File parentFile = resolveFile.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                if (z) {
                    resolveFile.mkdirs();
                } else {
                    byte[] bArr = new byte[1024];
                    try {
                        fileOutputStream = new FileOutputStream(resolveFile);
                        while (true) {
                            try {
                                int read = inputStream.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            } catch (Throwable th2) {
                                th = th2;
                                FileUtils.close(fileOutputStream);
                                throw th;
                            }
                        }
                        fileOutputStream.close();
                        FileUtils.close((OutputStream) null);
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                    }
                }
                fileUtils.setFileLastModified(resolveFile, date.getTime());
                return;
            }
            log("Skipping " + resolveFile + " as it is up-to-date", 4);
        } catch (FileNotFoundException e) {
            log("Unable to expand to file " + resolveFile.getPath(), e, 1);
        }
    }

    public void setDest(File file) {
        this.dest = file;
    }

    public void setSrc(File file) {
        this.source = file;
    }

    public void setOverwrite(boolean z) {
        this.overwrite = z;
    }

    public void addPatternset(PatternSet patternSet) {
        this.patternsets.addElement(patternSet);
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void add(ResourceCollection resourceCollection) {
        this.resourcesSpecified = true;
        this.resources.add(resourceCollection);
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapperElement == null) {
            this.mapperElement = new Mapper(getProject());
            return this.mapperElement;
        }
        throw new BuildException(ERROR_MULTIPLE_MAPPERS, getLocation());
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    public void setEncoding(String str) {
        internalSetEncoding(str);
    }

    protected void internalSetEncoding(String str) {
        if (NATIVE_ENCODING.equals(str)) {
            str = null;
        }
        this.encoding = str;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setStripAbsolutePathSpec(boolean z) {
        this.stripAbsolutePathSpec = z;
    }

    public void setScanForUnicodeExtraFields(boolean z) {
        internalSetScanForUnicodeExtraFields(z);
    }

    protected void internalSetScanForUnicodeExtraFields(boolean z) {
        this.scanForUnicodeExtraFields = z;
    }

    public boolean getScanForUnicodeExtraFields() {
        return this.scanForUnicodeExtraFields;
    }
}
