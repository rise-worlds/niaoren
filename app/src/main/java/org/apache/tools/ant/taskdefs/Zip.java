package org.apache.tools.ant.taskdefs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import java.util.zip.CRC32;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.types.ZipScanner;
import org.apache.tools.ant.types.resources.ArchiveResource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.resources.ZipResource;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.GlobPatternMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.MergingMapper;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.tar.TarConstants;
import org.apache.tools.zip.Zip64Mode;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipExtraField;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class Zip extends MatchingTask {
    private static final int BUFFER_SIZE = 8192;
    private static final int ROUNDUP_MILLIS = 1999;
    private File baseDir;
    private String encoding;
    protected File zipFile;

    /* renamed from: zs */
    private ZipScanner f14762zs;
    private static final long EMPTY_CRC = new CRC32().getValue();
    private static final ResourceSelector MISSING_SELECTOR = new ResourceSelector() { // from class: org.apache.tools.ant.taskdefs.Zip.1
        @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
        public boolean isSelected(Resource resource) {
            return !resource.isExists();
        }
    };
    private static final ResourceUtils.ResourceSelectorProvider MISSING_DIR_PROVIDER = new ResourceUtils.ResourceSelectorProvider() { // from class: org.apache.tools.ant.taskdefs.Zip.2
        @Override // org.apache.tools.ant.util.ResourceUtils.ResourceSelectorProvider
        public ResourceSelector getTargetSelectorForSource(Resource resource) {
            return Zip.MISSING_SELECTOR;
        }
    };
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final ThreadLocal<Boolean> HAVE_NON_FILE_SET_RESOURCES_TO_ADD = new ThreadLocal<Boolean>() { // from class: org.apache.tools.ant.taskdefs.Zip.3
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    private static final ThreadLocal<ZipExtraField[]> CURRENT_ZIP_EXTRA = new ThreadLocal<>();
    protected Hashtable<String, String> entries = new Hashtable<>();
    private final Vector<FileSet> groupfilesets = new Vector<>();
    private final Vector<ZipFileSet> filesetsFromGroupfilesets = new Vector<>();
    protected String duplicate = "add";
    private boolean doCompress = true;
    private boolean doUpdate = false;
    private boolean savedDoUpdate = false;
    private boolean doFilesonly = false;
    protected String archiveType = "zip";
    protected String emptyBehavior = MSVSSConstants.WRITABLE_SKIP;
    private final Vector<ResourceCollection> resources = new Vector<>();
    protected Hashtable<String, String> addedDirs = new Hashtable<>();
    private final Vector<String> addedFiles = new Vector<>();
    protected boolean doubleFilePass = false;
    protected boolean skipWriting = false;
    private boolean updatedFile = false;
    private boolean addingNewFiles = false;
    private boolean keepCompression = false;
    private boolean roundUp = true;
    private String comment = "";
    private int level = -1;
    private boolean preserve0Permissions = false;
    private boolean useLanguageEncodingFlag = true;
    private UnicodeExtraField createUnicodeExtraFields = UnicodeExtraField.NEVER;
    private boolean fallBackToUTF8 = false;
    private Zip64ModeAttribute zip64Mode = Zip64ModeAttribute.AS_NEEDED;

    protected void finalizeZipOutputStream(ZipOutputStream zipOutputStream) throws IOException, BuildException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initZipOutputStream(ZipOutputStream zipOutputStream) throws IOException, BuildException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isFirstPass() {
        return !this.doubleFilePass || this.skipWriting;
    }

    @Deprecated
    public void setZipfile(File file) {
        setDestFile(file);
    }

    @Deprecated
    public void setFile(File file) {
        setDestFile(file);
    }

    public void setDestFile(File file) {
        this.zipFile = file;
    }

    public File getDestFile() {
        return this.zipFile;
    }

    public void setBasedir(File file) {
        this.baseDir = file;
    }

    public void setCompress(boolean z) {
        this.doCompress = z;
    }

    public boolean isCompress() {
        return this.doCompress;
    }

    public void setFilesonly(boolean z) {
        this.doFilesonly = z;
    }

    public void setUpdate(boolean z) {
        this.doUpdate = z;
        this.savedDoUpdate = z;
    }

    public boolean isInUpdateMode() {
        return this.doUpdate;
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void addZipfileset(ZipFileSet zipFileSet) {
        add(zipFileSet);
    }

    public void add(ResourceCollection resourceCollection) {
        this.resources.add(resourceCollection);
    }

    public void addZipGroupFileset(FileSet fileSet) {
        this.groupfilesets.addElement(fileSet);
    }

    public void setDuplicate(Duplicate duplicate) {
        this.duplicate = duplicate.getValue();
    }

    /* loaded from: classes2.dex */
    public static class WhenEmpty extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"fail", MSVSSConstants.WRITABLE_SKIP, "create"};
        }
    }

    public void setWhenempty(WhenEmpty whenEmpty) {
        this.emptyBehavior = whenEmpty.getValue();
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setKeepCompression(boolean z) {
        this.keepCompression = z;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setRoundUp(boolean z) {
        this.roundUp = z;
    }

    public void setPreserve0Permissions(boolean z) {
        this.preserve0Permissions = z;
    }

    public boolean getPreserve0Permissions() {
        return this.preserve0Permissions;
    }

    public void setUseLanguageEncodingFlag(boolean z) {
        this.useLanguageEncodingFlag = z;
    }

    public boolean getUseLanguageEnodingFlag() {
        return this.useLanguageEncodingFlag;
    }

    public void setCreateUnicodeExtraFields(UnicodeExtraField unicodeExtraField) {
        this.createUnicodeExtraFields = unicodeExtraField;
    }

    public UnicodeExtraField getCreateUnicodeExtraFields() {
        return this.createUnicodeExtraFields;
    }

    public void setFallBackToUTF8(boolean z) {
        this.fallBackToUTF8 = z;
    }

    public boolean getFallBackToUTF8() {
        return this.fallBackToUTF8;
    }

    public void setZip64Mode(Zip64ModeAttribute zip64ModeAttribute) {
        this.zip64Mode = zip64ModeAttribute;
    }

    public Zip64ModeAttribute getZip64Mode() {
        return this.zip64Mode;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.doubleFilePass) {
            this.skipWriting = true;
            executeMain();
            this.skipWriting = false;
            executeMain();
            return;
        }
        executeMain();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasUpdatedFile() {
        return this.updatedFile;
    }

    public void executeMain() throws BuildException {
        IOException e;
        Throwable th;
        Resource[] resourceArr;
        checkAttributesAndElements();
        this.addingNewFiles = true;
        processDoUpdate();
        processGroupFilesets();
        Vector vector = new Vector();
        if (this.baseDir != null) {
            FileSet fileSet = (FileSet) getImplicitFileSet().clone();
            fileSet.setDir(this.baseDir);
            vector.addElement(fileSet);
        }
        int size = this.resources.size();
        for (int i = 0; i < size; i++) {
            vector.addElement(this.resources.elementAt(i));
        }
        ResourceCollection[] resourceCollectionArr = new ResourceCollection[vector.size()];
        vector.copyInto(resourceCollectionArr);
        File file = null;
        r1 = null;
        ZipOutputStream zipOutputStream = null;
        try {
            try {
                ArchiveState resourcesToAdd = getResourcesToAdd(resourceCollectionArr, this.zipFile, false);
                if (resourcesToAdd.isOutOfDate()) {
                    File parentFile = this.zipFile.getParentFile();
                    if (parentFile != null && !parentFile.isDirectory() && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                        throw new BuildException("Failed to create missing parent directory for " + this.zipFile);
                    }
                    this.updatedFile = true;
                    if (this.zipFile.exists() || !resourcesToAdd.isWithoutAnyResources()) {
                        Resource[][] resourcesToAdd2 = resourcesToAdd.getResourcesToAdd();
                        File renameFile = this.doUpdate ? renameFile() : null;
                        try {
                            String str = this.doUpdate ? "Updating " : "Building ";
                            if (!this.skipWriting) {
                                log(str + this.archiveType + ": " + this.zipFile.getAbsolutePath());
                            }
                            try {
                                if (!this.skipWriting) {
                                    ZipOutputStream zipOutputStream2 = new ZipOutputStream(this.zipFile);
                                    try {
                                        zipOutputStream2.setEncoding(this.encoding);
                                        zipOutputStream2.setUseLanguageEncodingFlag(this.useLanguageEncodingFlag);
                                        zipOutputStream2.setCreateUnicodeExtraFields(this.createUnicodeExtraFields.getPolicy());
                                        zipOutputStream2.setFallbackToUTF8(this.fallBackToUTF8);
                                        zipOutputStream2.setMethod(this.doCompress ? 8 : 0);
                                        zipOutputStream2.setLevel(this.level);
                                        zipOutputStream2.setUseZip64(this.zip64Mode.getMode());
                                        zipOutputStream = zipOutputStream2;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        zipOutputStream = zipOutputStream2;
                                        closeZout(zipOutputStream, false);
                                        throw th;
                                    }
                                }
                                initZipOutputStream(zipOutputStream);
                                for (int i2 = 0; i2 < resourceCollectionArr.length; i2++) {
                                    if (resourcesToAdd2[i2].length != 0) {
                                        addResources(resourceCollectionArr[i2], resourcesToAdd2[i2], zipOutputStream);
                                    }
                                }
                                if (this.doUpdate) {
                                    this.addingNewFiles = false;
                                    ZipFileSet zipFileSet = new ZipFileSet();
                                    zipFileSet.setProject(getProject());
                                    zipFileSet.setSrc(renameFile);
                                    zipFileSet.setDefaultexcludes(false);
                                    int size2 = this.addedFiles.size();
                                    for (int i3 = 0; i3 < size2; i3++) {
                                        zipFileSet.createExclude().setName(this.addedFiles.elementAt(i3));
                                    }
                                    DirectoryScanner directoryScanner = zipFileSet.getDirectoryScanner(getProject());
                                    ((ZipScanner) directoryScanner).setEncoding(this.encoding);
                                    String[] includedFiles = directoryScanner.getIncludedFiles();
                                    Resource[] resourceArr2 = new Resource[includedFiles.length];
                                    for (int i4 = 0; i4 < includedFiles.length; i4++) {
                                        resourceArr2[i4] = directoryScanner.getResource(includedFiles[i4]);
                                    }
                                    if (!this.doFilesonly) {
                                        String[] includedDirectories = directoryScanner.getIncludedDirectories();
                                        Resource[] resourceArr3 = new Resource[includedDirectories.length];
                                        for (int i5 = 0; i5 < includedDirectories.length; i5++) {
                                            resourceArr3[i5] = directoryScanner.getResource(includedDirectories[i5]);
                                        }
                                        resourceArr = new Resource[resourceArr2.length + resourceArr3.length];
                                        System.arraycopy(resourceArr3, 0, resourceArr, 0, resourceArr3.length);
                                        System.arraycopy(resourceArr2, 0, resourceArr, resourceArr3.length, resourceArr2.length);
                                    } else {
                                        resourceArr = resourceArr2;
                                    }
                                    addResources((FileSet) zipFileSet, resourceArr, zipOutputStream);
                                }
                                if (zipOutputStream != null) {
                                    zipOutputStream.setComment(this.comment);
                                }
                                finalizeZipOutputStream(zipOutputStream);
                                if (this.doUpdate && !renameFile.delete()) {
                                    log("Warning: unable to delete temporary file " + renameFile.getName(), 1);
                                }
                                closeZout(zipOutputStream, true);
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            file = renameFile;
                            String str2 = "Problem creating " + this.archiveType + ": " + e.getMessage();
                            if ((!this.doUpdate || file != null) && !this.zipFile.delete()) {
                                str2 = str2 + " (and the archive is probably corrupt but I could not delete it)";
                            }
                            if (this.doUpdate && file != null) {
                                try {
                                    FILE_UTILS.rename(file, this.zipFile);
                                } catch (IOException unused) {
                                    str2 = str2 + " (and I couldn't rename the temporary file " + file.getName() + " back)";
                                }
                            }
                            throw new BuildException(str2, e, getLocation());
                        }
                    } else {
                        createEmptyZip(this.zipFile);
                    }
                }
            } finally {
                cleanUp();
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    private File renameFile() {
        File createTempFile = FILE_UTILS.createTempFile("zip", ".tmp", this.zipFile.getParentFile(), true, false);
        try {
            FILE_UTILS.rename(this.zipFile, createTempFile);
            return createTempFile;
        } catch (IOException unused) {
            throw new BuildException("Unable to rename old file (" + this.zipFile.getAbsolutePath() + ") to temporary file");
        } catch (SecurityException unused2) {
            throw new BuildException("Not allowed to rename old file (" + this.zipFile.getAbsolutePath() + ") to temporary file");
        }
    }

    private void closeZout(ZipOutputStream zipOutputStream, boolean z) throws IOException {
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                if (z) {
                    throw e;
                }
            }
        }
    }

    private void checkAttributesAndElements() {
        if (this.baseDir == null && this.resources.size() == 0 && this.groupfilesets.size() == 0 && "zip".equals(this.archiveType)) {
            throw new BuildException("basedir attribute must be set, or at least one resource collection must be given!");
        }
        File file = this.zipFile;
        if (file == null) {
            throw new BuildException("You must specify the " + this.archiveType + " file to create!");
        } else if (file.exists() && !this.zipFile.isFile()) {
            throw new BuildException(this.zipFile + " is not a file.");
        } else if (this.zipFile.exists() && !this.zipFile.canWrite()) {
            throw new BuildException(this.zipFile + " is read-only.");
        }
    }

    private void processDoUpdate() {
        if (this.doUpdate && !this.zipFile.exists()) {
            this.doUpdate = false;
            logWhenWriting("ignoring update attribute as " + this.archiveType + " doesn't exist.", 4);
        }
    }

    private void processGroupFilesets() {
        int size = this.groupfilesets.size();
        for (int i = 0; i < size; i++) {
            logWhenWriting("Processing groupfileset ", 3);
            DirectoryScanner directoryScanner = this.groupfilesets.elementAt(i).getDirectoryScanner(getProject());
            String[] includedFiles = directoryScanner.getIncludedFiles();
            File basedir = directoryScanner.getBasedir();
            for (int i2 = 0; i2 < includedFiles.length; i2++) {
                logWhenWriting("Adding file " + includedFiles[i2] + " to fileset", 3);
                ZipFileSet zipFileSet = new ZipFileSet();
                zipFileSet.setProject(getProject());
                zipFileSet.setSrc(new File(basedir, includedFiles[i2]));
                add(zipFileSet);
                this.filesetsFromGroupfilesets.addElement(zipFileSet);
            }
        }
    }

    protected final boolean isAddingNewFiles() {
        return this.addingNewFiles;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd A[Catch: all -> 0x01c1, TryCatch #0 {all -> 0x01c1, blocks: (B:40:0x00da, B:42:0x00dd, B:45:0x00e5, B:46:0x00eb, B:49:0x0102, B:51:0x010a, B:55:0x0117, B:58:0x0120, B:59:0x0128), top: B:89:0x00da }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void addResources(org.apache.tools.ant.types.FileSet r21, org.apache.tools.ant.types.Resource[] r22, org.apache.tools.zip.ZipOutputStream r23) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Zip.addResources(org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.Resource[], org.apache.tools.zip.ZipOutputStream):void");
    }

    private void addDirectoryResource(Resource resource, String str, String str2, File file, ZipOutputStream zipOutputStream, int i, int i2) throws IOException {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        int lastIndexOf = str.lastIndexOf("/", str.length() - 2);
        if (lastIndexOf != -1) {
            addParentDirs(file, str.substring(0, lastIndexOf + 1), zipOutputStream, str2, i);
        }
        zipDir(resource, zipOutputStream, str2 + str, i2, resource instanceof ZipResource ? ((ZipResource) resource).getExtraFields() : null);
    }

    private int getUnixMode(Resource resource, ZipFile zipFile, int i) throws IOException {
        if (zipFile == null) {
            return resource instanceof ArchiveResource ? ((ArchiveResource) resource).getMode() : i;
        }
        int unixMode = zipFile.getEntry(resource.getName()).getUnixMode();
        return ((unixMode == 0 || unixMode == 16384) && !this.preserve0Permissions) ? i : unixMode;
    }

    private void addResource(Resource resource, String str, String str2, ZipOutputStream zipOutputStream, int i, ZipFile zipFile, File file) throws IOException {
        InputStream inputStream;
        Throwable th;
        InputStream inputStream2;
        Throwable th2;
        if (zipFile != null) {
            ZipEntry entry = zipFile.getEntry(resource.getName());
            if (entry != null) {
                boolean z = this.doCompress;
                if (this.keepCompression) {
                    this.doCompress = entry.getMethod() == 8;
                }
                try {
                    inputStream2 = zipFile.getInputStream(entry);
                    try {
                        zipFile(inputStream2, zipOutputStream, str2 + str, entry.getTime(), file, i, entry.getExtraFields(true));
                        this.doCompress = z;
                        FileUtils.close(inputStream2);
                    } catch (Throwable th3) {
                        th2 = th3;
                        this.doCompress = z;
                        FileUtils.close(inputStream2);
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    inputStream2 = null;
                }
            }
        } else {
            try {
                inputStream = resource.getInputStream();
                try {
                    zipFile(inputStream, zipOutputStream, str2 + str, resource.getLastModified(), file, i, resource instanceof ZipResource ? ((ZipResource) resource).getExtraFields() : null);
                    FileUtils.close(inputStream);
                } catch (Throwable th5) {
                    th = th5;
                    FileUtils.close(inputStream);
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
                inputStream = null;
            }
        }
    }

    protected final void addResources(ResourceCollection resourceCollection, Resource[] resourceArr, ZipOutputStream zipOutputStream) throws IOException {
        if (resourceCollection instanceof FileSet) {
            addResources((FileSet) resourceCollection, resourceArr, zipOutputStream);
            return;
        }
        for (Resource resource : resourceArr) {
            String name = resource.getName();
            if (name != null) {
                String replace = name.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX);
                if (!"".equals(replace) && (!resource.isDirectory() || !this.doFilesonly)) {
                    File file = null;
                    FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
                    if (fileProvider != null) {
                        file = ResourceUtils.asFileResource(fileProvider).getBaseDir();
                    }
                    if (resource.isDirectory()) {
                        addDirectoryResource(resource, replace, "", file, zipOutputStream, 16877, 16877);
                    } else {
                        addParentDirs(file, replace, zipOutputStream, "", 16877);
                        if (fileProvider != null) {
                            zipFile(fileProvider.getFile(), zipOutputStream, replace, 33188);
                        } else {
                            addResource(resource, replace, "", zipOutputStream, 33188, null, null);
                        }
                    }
                }
            }
        }
    }

    protected boolean createEmptyZip(File file) throws BuildException {
        Throwable th;
        IOException e;
        FileOutputStream fileOutputStream;
        if (!this.skipWriting) {
            log("Note: creating empty " + this.archiveType + " archive " + file, 2);
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[22];
            bArr[0] = 80;
            bArr[1] = TarConstants.LF_GNUTYPE_LONGLINK;
            bArr[2] = 5;
            bArr[3] = 6;
            fileOutputStream.write(bArr);
            FileUtils.close(fileOutputStream);
            return true;
        } catch (IOException e3) {
            e = e3;
            throw new BuildException("Could not create empty ZIP archive (" + e.getMessage() + ")", e, getLocation());
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            FileUtils.close(fileOutputStream2);
            throw th;
        }
    }

    private synchronized ZipScanner getZipScanner() {
        if (this.f14762zs == null) {
            this.f14762zs = new ZipScanner();
            this.f14762zs.setEncoding(this.encoding);
            this.f14762zs.setSrc(this.zipFile);
        }
        return this.f14762zs;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveState getResourcesToAdd(ResourceCollection[] resourceCollectionArr, File file, boolean z) throws BuildException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < resourceCollectionArr.length; i++) {
            if (resourceCollectionArr[i] instanceof FileSet) {
                arrayList.add(resourceCollectionArr[i]);
            } else {
                arrayList2.add(resourceCollectionArr[i]);
            }
        }
        ResourceCollection[] resourceCollectionArr2 = (ResourceCollection[]) arrayList2.toArray(new ResourceCollection[arrayList2.size()]);
        ArchiveState nonFileSetResourcesToAdd = getNonFileSetResourcesToAdd(resourceCollectionArr2, file, z);
        ArchiveState resourcesToAdd = getResourcesToAdd((FileSet[]) arrayList.toArray(new FileSet[arrayList.size()]), file, nonFileSetResourcesToAdd.isOutOfDate());
        if (!nonFileSetResourcesToAdd.isOutOfDate() && resourcesToAdd.isOutOfDate()) {
            nonFileSetResourcesToAdd = getNonFileSetResourcesToAdd(resourceCollectionArr2, file, true);
        }
        Resource[][] resourceArr = new Resource[resourceCollectionArr.length];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < resourceCollectionArr.length; i4++) {
            if (resourceCollectionArr[i4] instanceof FileSet) {
                i3++;
                resourceArr[i4] = resourcesToAdd.getResourcesToAdd()[i3];
            } else {
                i2++;
                resourceArr[i4] = nonFileSetResourcesToAdd.getResourcesToAdd()[i2];
            }
        }
        return new ArchiveState(resourcesToAdd.isOutOfDate(), resourceArr);
    }

    protected ArchiveState getResourcesToAdd(FileSet[] fileSetArr, File file, boolean z) throws BuildException {
        Resource[][] grabResources = grabResources(fileSetArr);
        if (isEmpty(grabResources)) {
            if (Boolean.FALSE.equals(HAVE_NON_FILE_SET_RESOURCES_TO_ADD.get())) {
                if (z && this.doUpdate) {
                    return new ArchiveState(true, grabResources);
                }
                if (this.emptyBehavior.equals(MSVSSConstants.WRITABLE_SKIP)) {
                    if (this.doUpdate) {
                        logWhenWriting(this.archiveType + " archive " + file + " not updated because no new files were included.", 3);
                    } else {
                        logWhenWriting("Warning: skipping " + this.archiveType + " archive " + file + " because no files were included.", 1);
                    }
                } else if (this.emptyBehavior.equals("fail")) {
                    throw new BuildException("Cannot create " + this.archiveType + " archive " + file + ": no files were included.", getLocation());
                } else if (!file.exists()) {
                    z = true;
                }
            }
            return new ArchiveState(z, grabResources);
        } else if (!file.exists()) {
            return new ArchiveState(true, grabResources);
        } else {
            if (z && !this.doUpdate) {
                return new ArchiveState(true, grabResources);
            }
            Resource[][] resourceArr = new Resource[fileSetArr.length];
            for (int i = 0; i < fileSetArr.length; i++) {
                if (!(this.fileset instanceof ZipFileSet) || ((ZipFileSet) this.fileset).getSrc(getProject()) == null) {
                    File dir = fileSetArr[i].getDir(getProject());
                    for (int i2 = 0; i2 < grabResources[i].length; i2++) {
                        if (FILE_UTILS.resolveFile(dir, grabResources[i][i2].getName()).equals(file)) {
                            throw new BuildException("A zip file cannot include itself", getLocation());
                        }
                    }
                    continue;
                }
            }
            for (int i3 = 0; i3 < fileSetArr.length; i3++) {
                if (grabResources[i3].length != 0) {
                    FileNameMapper identityMapper = new IdentityMapper();
                    if (fileSetArr[i3] instanceof ZipFileSet) {
                        ZipFileSet zipFileSet = (ZipFileSet) fileSetArr[i3];
                        if (zipFileSet.getFullpath(getProject()) != null && !zipFileSet.getFullpath(getProject()).equals("")) {
                            identityMapper = new MergingMapper();
                            identityMapper.setTo(zipFileSet.getFullpath(getProject()));
                        } else if (zipFileSet.getPrefix(getProject()) != null && !zipFileSet.getPrefix(getProject()).equals("")) {
                            identityMapper = new GlobPatternMapper();
                            identityMapper.setFrom(Marker.ANY_MARKER);
                            String prefix = zipFileSet.getPrefix(getProject());
                            if (!prefix.endsWith("/") && !prefix.endsWith("\\")) {
                                prefix = prefix + "/";
                            }
                            identityMapper.setTo(prefix + Marker.ANY_MARKER);
                        }
                    }
                    resourceArr[i3] = selectOutOfDateResources(grabResources[i3], identityMapper);
                    z = z || resourceArr[i3].length > 0;
                    if (z && !this.doUpdate) {
                        break;
                    }
                } else {
                    resourceArr[i3] = new Resource[0];
                }
            }
            if (!z || this.doUpdate) {
                return new ArchiveState(z, resourceArr);
            }
            return new ArchiveState(true, grabResources);
        }
    }

    protected ArchiveState getNonFileSetResourcesToAdd(ResourceCollection[] resourceCollectionArr, File file, boolean z) throws BuildException {
        Resource[][] grabNonFileSetResources = grabNonFileSetResources(resourceCollectionArr);
        boolean isEmpty = isEmpty(grabNonFileSetResources);
        HAVE_NON_FILE_SET_RESOURCES_TO_ADD.set(Boolean.valueOf(!isEmpty));
        if (isEmpty) {
            return new ArchiveState(z, grabNonFileSetResources);
        }
        if (!file.exists()) {
            return new ArchiveState(true, grabNonFileSetResources);
        }
        if (z && !this.doUpdate) {
            return new ArchiveState(true, grabNonFileSetResources);
        }
        Resource[][] resourceArr = new Resource[resourceCollectionArr.length];
        boolean z2 = z;
        for (int i = 0; i < resourceCollectionArr.length; i++) {
            if (grabNonFileSetResources[i].length != 0) {
                for (int i2 = 0; i2 < grabNonFileSetResources[i].length; i2++) {
                    FileProvider fileProvider = (FileProvider) grabNonFileSetResources[i][i2].mo14823as(FileProvider.class);
                    if (fileProvider != null && file.equals(fileProvider.getFile())) {
                        throw new BuildException("A zip file cannot include itself", getLocation());
                    }
                }
                resourceArr[i] = selectOutOfDateResources(grabNonFileSetResources[i], new IdentityMapper());
                z2 = z2 || resourceArr[i].length > 0;
                if (z2 && !this.doUpdate) {
                    break;
                }
            } else {
                resourceArr[i] = new Resource[0];
            }
        }
        if (!z2 || this.doUpdate) {
            return new ArchiveState(z2, resourceArr);
        }
        return new ArchiveState(true, grabNonFileSetResources);
    }

    private Resource[] selectOutOfDateResources(Resource[] resourceArr, FileNameMapper fileNameMapper) {
        Resource[] selectOutOfDateSources = ResourceUtils.selectOutOfDateSources(this, selectFileResources(resourceArr), fileNameMapper, getZipScanner());
        if (this.doFilesonly) {
            return selectOutOfDateSources;
        }
        Union union = new Union();
        union.addAll(Arrays.asList(selectDirectoryResources(resourceArr)));
        ResourceCollection selectSources = ResourceUtils.selectSources(this, union, fileNameMapper, getZipScanner(), MISSING_DIR_PROVIDER);
        if (selectSources.size() <= 0) {
            return selectOutOfDateSources;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(((Union) selectSources).listResources()));
        arrayList.addAll(Arrays.asList(selectOutOfDateSources));
        return (Resource[]) arrayList.toArray(selectOutOfDateSources);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Resource[][] grabResources(FileSet[] fileSetArr) {
        Resource[][] resourceArr = new Resource[fileSetArr.length];
        for (int i = 0; i < fileSetArr.length; i++) {
            boolean z = true;
            if (fileSetArr[i] instanceof ZipFileSet) {
                ZipFileSet zipFileSet = (ZipFileSet) fileSetArr[i];
                if (!zipFileSet.getPrefix(getProject()).equals("") || !zipFileSet.getFullpath(getProject()).equals("")) {
                    z = false;
                }
            }
            DirectoryScanner directoryScanner = fileSetArr[i].getDirectoryScanner(getProject());
            if (directoryScanner instanceof ZipScanner) {
                ((ZipScanner) directoryScanner).setEncoding(this.encoding);
            }
            Vector vector = new Vector();
            if (!this.doFilesonly) {
                String[] includedDirectories = directoryScanner.getIncludedDirectories();
                for (int i2 = 0; i2 < includedDirectories.length; i2++) {
                    if (!"".equals(includedDirectories[i2]) || !z) {
                        vector.addElement(directoryScanner.getResource(includedDirectories[i2]));
                    }
                }
            }
            String[] includedFiles = directoryScanner.getIncludedFiles();
            for (int i3 = 0; i3 < includedFiles.length; i3++) {
                if (!"".equals(includedFiles[i3]) || !z) {
                    vector.addElement(directoryScanner.getResource(includedFiles[i3]));
                }
            }
            resourceArr[i] = new Resource[vector.size()];
            vector.copyInto(resourceArr[i]);
        }
        return resourceArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Resource[][] grabNonFileSetResources(ResourceCollection[] resourceCollectionArr) {
        Resource[][] resourceArr = new Resource[resourceCollectionArr.length];
        for (int i = 0; i < resourceCollectionArr.length; i++) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Resource resource : resourceCollectionArr[i]) {
                if (resource.isExists()) {
                    if (resource.isDirectory()) {
                        arrayList.add(resource);
                    } else {
                        arrayList2.add(resource);
                    }
                }
            }
            Collections.sort(arrayList, new Comparator<Resource>() { // from class: org.apache.tools.ant.taskdefs.Zip.4
                public int compare(Resource resource2, Resource resource3) {
                    return resource2.getName().compareTo(resource3.getName());
                }
            });
            ArrayList arrayList3 = new ArrayList(arrayList);
            arrayList3.addAll(arrayList2);
            resourceArr[i] = (Resource[]) arrayList3.toArray(new Resource[arrayList3.size()]);
        }
        return resourceArr;
    }

    protected void zipDir(File file, ZipOutputStream zipOutputStream, String str, int i) throws IOException {
        zipDir(file, zipOutputStream, str, i, (ZipExtraField[]) null);
    }

    protected void zipDir(File file, ZipOutputStream zipOutputStream, String str, int i, ZipExtraField[] zipExtraFieldArr) throws IOException {
        zipDir(file == null ? null : new FileResource(file), zipOutputStream, str, i, zipExtraFieldArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zipDir(Resource resource, ZipOutputStream zipOutputStream, String str, int i, ZipExtraField[] zipExtraFieldArr) throws IOException {
        if (this.doFilesonly) {
            logWhenWriting("skipping directory " + str + " for file-only archive", 3);
        } else if (this.addedDirs.get(str) == null) {
            logWhenWriting("adding directory " + str, 3);
            this.addedDirs.put(str, str);
            if (!this.skipWriting) {
                ZipEntry zipEntry = new ZipEntry(str);
                int i2 = this.roundUp ? 1999 : 0;
                if (resource == null || !resource.isExists()) {
                    zipEntry.setTime(System.currentTimeMillis() + i2);
                } else {
                    zipEntry.setTime(resource.getLastModified() + i2);
                }
                zipEntry.setSize(0L);
                zipEntry.setMethod(0);
                zipEntry.setCrc(EMPTY_CRC);
                zipEntry.setUnixMode(i);
                if (zipExtraFieldArr != null) {
                    zipEntry.setExtraFields(zipExtraFieldArr);
                }
                zipOutputStream.putNextEntry(zipEntry);
            }
        }
    }

    protected final ZipExtraField[] getCurrentExtraFields() {
        return CURRENT_ZIP_EXTRA.get();
    }

    protected final void setCurrentExtraFields(ZipExtraField[] zipExtraFieldArr) {
        CURRENT_ZIP_EXTRA.set(zipExtraFieldArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zipFile(InputStream inputStream, ZipOutputStream zipOutputStream, String str, long j, File file, int i) throws IOException {
        int i2;
        InputStream inputStream2 = inputStream;
        if (!this.entries.containsKey(str)) {
            logWhenWriting("adding entry " + str, 3);
        } else if (this.duplicate.equals("preserve")) {
            logWhenWriting(str + " already added, skipping", 2);
            return;
        } else if (!this.duplicate.equals("fail")) {
            logWhenWriting("duplicate file " + str + " found, adding.", 3);
        } else {
            throw new BuildException("Duplicate file " + str + " was found and the duplicate attribute is 'fail'.");
        }
        this.entries.put(str, str);
        if (!this.skipWriting) {
            ZipEntry zipEntry = new ZipEntry(str);
            zipEntry.setTime(j);
            zipEntry.setMethod(this.doCompress ? 8 : 0);
            if (zipOutputStream.isSeekable() || this.doCompress) {
                inputStream2 = inputStream2;
                i2 = i;
            } else {
                long j2 = 0;
                CRC32 crc32 = new CRC32();
                if (!inputStream.markSupported()) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[8192];
                    int i3 = 0;
                    do {
                        j2 += i3;
                        crc32.update(bArr, 0, i3);
                        byteArrayOutputStream.write(bArr, 0, i3);
                        i3 = inputStream2.read(bArr, 0, bArr.length);
                    } while (i3 != -1);
                    inputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                } else {
                    inputStream2.mark(Integer.MAX_VALUE);
                    byte[] bArr2 = new byte[8192];
                    int i4 = 0;
                    do {
                        j2 += i4;
                        crc32.update(bArr2, 0, i4);
                        i4 = inputStream2.read(bArr2, 0, bArr2.length);
                    } while (i4 != -1);
                    inputStream.reset();
                }
                zipEntry.setSize(j2);
                zipEntry.setCrc(crc32.getValue());
                i2 = i;
            }
            zipEntry.setUnixMode(i2);
            ZipExtraField[] currentExtraFields = getCurrentExtraFields();
            if (currentExtraFields != null) {
                zipEntry.setExtraFields(currentExtraFields);
            }
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bArr3 = new byte[8192];
            int i5 = 0;
            do {
                if (i5 != 0) {
                    zipOutputStream.write(bArr3, 0, i5);
                }
                i5 = inputStream2.read(bArr3, 0, bArr3.length);
            } while (i5 != -1);
            this.addedFiles.addElement(str);
        }
        this.addedFiles.addElement(str);
    }

    protected final void zipFile(InputStream inputStream, ZipOutputStream zipOutputStream, String str, long j, File file, int i, ZipExtraField[] zipExtraFieldArr) throws IOException {
        try {
            setCurrentExtraFields(zipExtraFieldArr);
            zipFile(inputStream, zipOutputStream, str, j, file, i);
        } finally {
            setCurrentExtraFields(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zipFile(File file, ZipOutputStream zipOutputStream, String str, int i) throws IOException {
        if (!file.equals(this.zipFile)) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                zipFile(fileInputStream, zipOutputStream, str, file.lastModified() + (this.roundUp ? 1999 : 0), null, i);
            } finally {
                fileInputStream.close();
            }
        } else {
            throw new BuildException("A zip file cannot include itself", getLocation());
        }
    }

    protected final void addParentDirs(File file, String str, ZipOutputStream zipOutputStream, String str2, int i) throws IOException {
        File file2;
        if (!this.doFilesonly) {
            Stack stack = new Stack();
            int length = str.length();
            while (true) {
                length = str.lastIndexOf(47, length - 1);
                if (length == -1) {
                    break;
                }
                String substring = str.substring(0, length + 1);
                Hashtable<String, String> hashtable = this.addedDirs;
                if (hashtable.get(str2 + substring) != null) {
                    break;
                }
                stack.push(substring);
            }
            while (!stack.isEmpty()) {
                String str3 = (String) stack.pop();
                if (file != null) {
                    file2 = new File(file, str3);
                } else {
                    file2 = new File(str3);
                }
                zipDir(file2, zipOutputStream, str2 + str3, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cleanUp() {
        this.addedDirs.clear();
        this.addedFiles.removeAllElements();
        this.entries.clear();
        this.addingNewFiles = false;
        this.doUpdate = this.savedDoUpdate;
        Enumeration<ZipFileSet> elements = this.filesetsFromGroupfilesets.elements();
        while (elements.hasMoreElements()) {
            this.resources.removeElement(elements.nextElement());
        }
        this.filesetsFromGroupfilesets.removeAllElements();
        HAVE_NON_FILE_SET_RESOURCES_TO_ADD.set(Boolean.FALSE);
    }

    public void reset() {
        this.resources.removeAllElements();
        this.zipFile = null;
        this.baseDir = null;
        this.groupfilesets.removeAllElements();
        this.duplicate = "add";
        this.archiveType = "zip";
        this.doCompress = true;
        this.emptyBehavior = MSVSSConstants.WRITABLE_SKIP;
        this.doUpdate = false;
        this.doFilesonly = false;
        this.encoding = null;
    }

    protected static final boolean isEmpty(Resource[][] resourceArr) {
        for (Resource[] resourceArr2 : resourceArr) {
            if (resourceArr2.length > 0) {
                return false;
            }
        }
        return true;
    }

    protected Resource[] selectFileResources(Resource[] resourceArr) {
        return selectResources(resourceArr, new ResourceSelector() { // from class: org.apache.tools.ant.taskdefs.Zip.5
            @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
            public boolean isSelected(Resource resource) {
                if (!resource.isDirectory()) {
                    return true;
                }
                if (!Zip.this.doFilesonly) {
                    return false;
                }
                Zip zip = Zip.this;
                zip.logWhenWriting("Ignoring directory " + resource.getName() + " as only files will be added.", 3);
                return false;
            }
        });
    }

    protected Resource[] selectDirectoryResources(Resource[] resourceArr) {
        return selectResources(resourceArr, new ResourceSelector() { // from class: org.apache.tools.ant.taskdefs.Zip.6
            @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
            public boolean isSelected(Resource resource) {
                return resource.isDirectory();
            }
        });
    }

    protected Resource[] selectResources(Resource[] resourceArr, ResourceSelector resourceSelector) {
        if (resourceArr.length == 0) {
            return resourceArr;
        }
        ArrayList arrayList = new ArrayList(resourceArr.length);
        for (int i = 0; i < resourceArr.length; i++) {
            if (resourceSelector.isSelected(resourceArr[i])) {
                arrayList.add(resourceArr[i]);
            }
        }
        return arrayList.size() != resourceArr.length ? (Resource[]) arrayList.toArray(new Resource[arrayList.size()]) : resourceArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logWhenWriting(String str, int i) {
        if (!this.skipWriting) {
            log(str, i);
        }
    }

    /* loaded from: classes2.dex */
    public static class Duplicate extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"add", "preserve", "fail"};
        }
    }

    /* loaded from: classes2.dex */
    public static class ArchiveState {
        private final boolean outOfDate;
        private final Resource[][] resourcesToAdd;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ArchiveState(boolean z, Resource[][] resourceArr) {
            this.outOfDate = z;
            this.resourcesToAdd = resourceArr;
        }

        public boolean isOutOfDate() {
            return this.outOfDate;
        }

        public Resource[][] getResourcesToAdd() {
            return this.resourcesToAdd;
        }

        public boolean isWithoutAnyResources() {
            if (this.resourcesToAdd == null) {
                return true;
            }
            int i = 0;
            while (true) {
                Resource[][] resourceArr = this.resourcesToAdd;
                if (i >= resourceArr.length) {
                    return true;
                }
                if (resourceArr[i] != null && resourceArr[i].length > 0) {
                    return false;
                }
                i++;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class UnicodeExtraField extends EnumeratedAttribute {
        private static final String ALWAYS_KEY = "always";
        private static final String N_E_KEY = "not-encodeable";
        private static final Map<String, ZipOutputStream.UnicodeExtraFieldPolicy> POLICIES = new HashMap();
        private static final String NEVER_KEY = "never";
        public static final UnicodeExtraField NEVER = new UnicodeExtraField(NEVER_KEY);

        static {
            POLICIES.put(NEVER_KEY, ZipOutputStream.UnicodeExtraFieldPolicy.NEVER);
            POLICIES.put(ALWAYS_KEY, ZipOutputStream.UnicodeExtraFieldPolicy.ALWAYS);
            POLICIES.put(N_E_KEY, ZipOutputStream.UnicodeExtraFieldPolicy.NOT_ENCODEABLE);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{NEVER_KEY, ALWAYS_KEY, N_E_KEY};
        }

        private UnicodeExtraField(String str) {
            setValue(str);
        }

        public UnicodeExtraField() {
        }

        public ZipOutputStream.UnicodeExtraFieldPolicy getPolicy() {
            return POLICIES.get(getValue());
        }
    }

    /* loaded from: classes2.dex */
    public static final class Zip64ModeAttribute extends EnumeratedAttribute {
        private static final String ALWAYS_KEY = "always";
        private static final Map<String, Zip64Mode> MODES = new HashMap();
        private static final String NEVER_KEY = "never";
        public static final Zip64ModeAttribute NEVER = new Zip64ModeAttribute(NEVER_KEY);
        private static final String A_N_KEY = "as-needed";
        public static final Zip64ModeAttribute AS_NEEDED = new Zip64ModeAttribute(A_N_KEY);

        static {
            MODES.put(NEVER_KEY, Zip64Mode.Never);
            MODES.put(ALWAYS_KEY, Zip64Mode.Always);
            MODES.put(A_N_KEY, Zip64Mode.AsNeeded);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{NEVER_KEY, ALWAYS_KEY, A_N_KEY};
        }

        private Zip64ModeAttribute(String str) {
            setValue(str);
        }

        public Zip64ModeAttribute() {
        }

        public Zip64Mode getMode() {
            return MODES.get(getValue());
        }
    }
}
