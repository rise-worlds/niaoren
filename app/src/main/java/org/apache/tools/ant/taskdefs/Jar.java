package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.ArchiveFileSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.types.spi.Service;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.zip.JarMarker;
import org.apache.tools.zip.ZipExtraField;
import org.apache.tools.zip.ZipOutputStream;

/* loaded from: classes2.dex */
public class Jar extends Zip {
    private static final String INDEX_NAME = "META-INF/INDEX.LIST";
    private static final ZipExtraField[] JAR_MARKER = {JarMarker.getInstance()};
    private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
    private Manifest configuredManifest;
    private Manifest filesetManifest;
    private FilesetManifestConfig filesetManifestConfig;
    private Path indexJars;
    private Manifest manifest;
    private String manifestEncoding;
    private File manifestFile;
    private Manifest originalManifest;
    private Manifest savedConfiguredManifest;
    private List<Service> serviceList = new ArrayList();
    private boolean mergeManifestsMain = true;
    private boolean index = false;
    private boolean indexMetaInf = false;
    private boolean createEmpty = false;
    private StrictMode strict = new StrictMode(Definer.OnError.POLICY_IGNORE);
    private boolean mergeClassPaths = false;
    private boolean flattenClassPaths = false;
    private Vector<String> rootEntries = new Vector<>();

    public Jar() {
        this.archiveType = "jar";
        this.emptyBehavior = "create";
        setEncoding("UTF8");
        setZip64Mode(Zip.Zip64ModeAttribute.NEVER);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    public void setWhenempty(Zip.WhenEmpty whenEmpty) {
        log("JARs are never empty, they contain at least a manifest file", 1);
    }

    public void setWhenmanifestonly(Zip.WhenEmpty whenEmpty) {
        this.emptyBehavior = whenEmpty.getValue();
    }

    public void setStrict(StrictMode strictMode) {
        this.strict = strictMode;
    }

    public void setJarfile(File file) {
        setDestFile(file);
    }

    public void setIndex(boolean z) {
        this.index = z;
    }

    public void setIndexMetaInf(boolean z) {
        this.indexMetaInf = z;
    }

    public void setManifestEncoding(String str) {
        this.manifestEncoding = str;
    }

    public void addConfiguredManifest(Manifest manifest) throws ManifestException {
        Manifest manifest2 = this.configuredManifest;
        if (manifest2 == null) {
            this.configuredManifest = manifest;
        } else {
            manifest2.merge(manifest, false, this.mergeClassPaths);
        }
        this.savedConfiguredManifest = this.configuredManifest;
    }

    public void setManifest(File file) {
        if (file.exists()) {
            this.manifestFile = file;
            return;
        }
        throw new BuildException("Manifest file: " + file + DirectoryScanner.DOES_NOT_EXIST_POSTFIX, getLocation());
    }

    private Manifest getManifest(File file) {
        InputStreamReader inputStreamReader = null;
        try {
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    if (this.manifestEncoding == null) {
                        inputStreamReader = new InputStreamReader(fileInputStream);
                    } else {
                        inputStreamReader = new InputStreamReader(fileInputStream, this.manifestEncoding);
                    }
                    return getManifest(inputStreamReader);
                } catch (IOException e) {
                    throw new BuildException("Unable to read manifest file: " + file + " (" + e.getMessage() + ")", e);
                }
            } catch (UnsupportedEncodingException e2) {
                throw new BuildException("Unsupported encoding while reading manifest: " + e2.getMessage(), e2);
            }
        } finally {
            FileUtils.close(inputStreamReader);
        }
    }

    private Manifest getManifestFromJar(File file) throws IOException {
        Throwable th;
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(file);
            try {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    if (zipEntry.getName().equalsIgnoreCase(MANIFEST_NAME)) {
                        Manifest manifest = getManifest(new InputStreamReader(zipFile.getInputStream(zipEntry), "UTF-8"));
                        try {
                            zipFile.close();
                        } catch (IOException unused) {
                        }
                        return manifest;
                    }
                }
                try {
                    zipFile.close();
                } catch (IOException unused2) {
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
        }
    }

    private Manifest getManifest(Reader reader) {
        try {
            return new Manifest(reader);
        } catch (IOException e) {
            throw new BuildException("Unable to read manifest file (" + e.getMessage() + ")", e);
        } catch (ManifestException e2) {
            log("Manifest is invalid: " + e2.getMessage(), 0);
            throw new BuildException("Invalid Manifest: " + this.manifestFile, e2, getLocation());
        }
    }

    private boolean jarHasIndex(File file) throws IOException {
        Throwable th;
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(file);
            try {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    if (((ZipEntry) entries.nextElement()).getName().equalsIgnoreCase(INDEX_NAME)) {
                        try {
                            zipFile.close();
                        } catch (IOException unused) {
                        }
                        return true;
                    }
                }
                try {
                    zipFile.close();
                } catch (IOException unused2) {
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
        }
    }

    public void setFilesetmanifest(FilesetManifestConfig filesetManifestConfig) {
        this.filesetManifestConfig = filesetManifestConfig;
        this.mergeManifestsMain = "merge".equals(filesetManifestConfig.getValue());
        FilesetManifestConfig filesetManifestConfig2 = this.filesetManifestConfig;
        if (filesetManifestConfig2 != null && !filesetManifestConfig2.getValue().equals(MSVSSConstants.WRITABLE_SKIP)) {
            this.doubleFilePass = true;
        }
    }

    public void addMetainf(ZipFileSet zipFileSet) {
        zipFileSet.setPrefix("META-INF/");
        super.addFileset(zipFileSet);
    }

    public void addConfiguredIndexJars(Path path) {
        if (this.indexJars == null) {
            this.indexJars = new Path(getProject());
        }
        this.indexJars.append(path);
    }

    public void addConfiguredService(Service service) {
        service.check();
        this.serviceList.add(service);
    }

    private void writeServices(ZipOutputStream zipOutputStream) throws IOException {
        for (Service service : this.serviceList) {
            InputStream inputStream = null;
            try {
                inputStream = service.getAsStream();
                super.zipFile(inputStream, zipOutputStream, "META-INF/services/" + service.getType(), System.currentTimeMillis(), null, 33188);
            } finally {
                FileUtils.close(inputStream);
            }
        }
    }

    public void setMergeClassPathAttributes(boolean z) {
        this.mergeClassPaths = z;
    }

    public void setFlattenAttributes(boolean z) {
        this.flattenClassPaths = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Zip
    public void initZipOutputStream(ZipOutputStream zipOutputStream) throws IOException, BuildException {
        if (!this.skipWriting) {
            writeManifest(zipOutputStream, createManifest());
            writeServices(zipOutputStream);
        }
    }

    private Manifest createManifest() throws BuildException {
        Manifest manifest;
        try {
            if (this.manifest == null && this.manifestFile != null) {
                this.manifest = getManifest(this.manifestFile);
            }
            boolean z = true;
            boolean z2 = !this.mergeManifestsMain && this.filesetManifest != null && this.configuredManifest == null && this.manifest == null;
            if (z2) {
                manifest = new Manifest();
                manifest.merge(this.filesetManifest, false, this.mergeClassPaths);
                manifest.merge(Manifest.getDefaultManifest(), true, this.mergeClassPaths);
            } else {
                manifest = Manifest.getDefaultManifest();
            }
            if (isInUpdateMode()) {
                manifest.merge(this.originalManifest, false, this.mergeClassPaths);
            }
            if (!z2) {
                manifest.merge(this.filesetManifest, false, this.mergeClassPaths);
            }
            manifest.merge(this.configuredManifest, !this.mergeManifestsMain, this.mergeClassPaths);
            Manifest manifest2 = this.manifest;
            if (this.mergeManifestsMain) {
                z = false;
            }
            manifest.merge(manifest2, z, this.mergeClassPaths);
            return manifest;
        } catch (ManifestException e) {
            log("Manifest is invalid: " + e.getMessage(), 0);
            throw new BuildException("Invalid Manifest", e, getLocation());
        }
    }

    /* JADX WARN: Finally extract failed */
    private void writeManifest(ZipOutputStream zipOutputStream, Manifest manifest) throws IOException {
        Enumeration<String> warnings = manifest.getWarnings();
        while (warnings.hasMoreElements()) {
            log("Manifest warning: " + warnings.nextElement(), 1);
        }
        zipDir((Resource) null, zipOutputStream, "META-INF/", 16877, JAR_MARKER);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF-8"));
        manifest.write(printWriter, this.flattenClassPaths);
        if (!printWriter.checkError()) {
            printWriter.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            try {
                super.zipFile(byteArrayInputStream, zipOutputStream, MANIFEST_NAME, System.currentTimeMillis(), null, 33188);
                FileUtils.close(byteArrayInputStream);
                super.initZipOutputStream(zipOutputStream);
            } catch (Throwable th) {
                FileUtils.close(byteArrayInputStream);
                throw th;
            }
        } else {
            throw new IOException("Encountered an error writing the manifest");
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void finalizeZipOutputStream(ZipOutputStream zipOutputStream) throws IOException, BuildException {
        if (this.index) {
            createIndexList(zipOutputStream);
        }
    }

    private void createIndexList(ZipOutputStream zipOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF8"));
        printWriter.println("JarIndex-Version: 1.0");
        printWriter.println();
        printWriter.println(this.zipFile.getName());
        writeIndexLikeList(new ArrayList<>(this.addedDirs.keySet()), this.rootEntries, printWriter);
        printWriter.println();
        if (this.indexJars != null) {
            Manifest.Attribute attribute = createManifest().getMainSection().getAttribute(Manifest.ATTRIBUTE_CLASSPATH);
            String[] strArr = null;
            if (attribute != null && attribute.getValue() != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(attribute.getValue(), ExpandableTextView.f6958c);
                strArr = new String[stringTokenizer.countTokens()];
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    i++;
                    strArr[i] = stringTokenizer.nextToken();
                }
            }
            String[] list = this.indexJars.list();
            for (int i2 = 0; i2 < list.length; i2++) {
                String findJarName = findJarName(list[i2], strArr);
                if (findJarName != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    grabFilesAndDirs(list[i2], arrayList, arrayList2);
                    if (arrayList.size() + arrayList2.size() > 0) {
                        printWriter.println(findJarName);
                        writeIndexLikeList(arrayList, arrayList2, printWriter);
                        printWriter.println();
                    }
                }
            }
        }
        if (!printWriter.checkError()) {
            printWriter.close();
            InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            try {
                super.zipFile(byteArrayInputStream, zipOutputStream, INDEX_NAME, System.currentTimeMillis(), null, 33188);
            } finally {
                FileUtils.close(byteArrayInputStream);
            }
        } else {
            throw new IOException("Encountered an error writing jar index");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Zip
    public void zipFile(InputStream inputStream, ZipOutputStream zipOutputStream, String str, long j, File file, int i) throws IOException {
        if (MANIFEST_NAME.equalsIgnoreCase(str)) {
            if (isFirstPass()) {
                filesetManifest(file, inputStream);
            }
        } else if (!INDEX_NAME.equalsIgnoreCase(str) || !this.index) {
            if (this.index && str.indexOf("/") == -1) {
                this.rootEntries.addElement(str);
            }
            super.zipFile(inputStream, zipOutputStream, str, j, file, i);
        } else {
            logWhenWriting("Warning: selected " + this.archiveType + " files include a " + INDEX_NAME + " which will be replaced by a newly generated one.", 1);
        }
    }

    private void filesetManifest(File file, InputStream inputStream) throws IOException {
        Manifest manifest;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        File file2 = this.manifestFile;
        if (file2 == null || !file2.equals(file)) {
            FilesetManifestConfig filesetManifestConfig = this.filesetManifestConfig;
            if (filesetManifestConfig != null && !filesetManifestConfig.getValue().equals(MSVSSConstants.WRITABLE_SKIP)) {
                logWhenWriting("Found manifest to merge in file " + file, 3);
                try {
                    if (inputStream != null) {
                        if (this.manifestEncoding == null) {
                            inputStreamReader = new InputStreamReader(inputStream);
                        } else {
                            inputStreamReader = new InputStreamReader(inputStream, this.manifestEncoding);
                        }
                        manifest = getManifest(inputStreamReader);
                    } else {
                        manifest = getManifest(file);
                    }
                    if (this.filesetManifest == null) {
                        this.filesetManifest = manifest;
                    } else {
                        this.filesetManifest.merge(manifest, false, this.mergeClassPaths);
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new BuildException("Unsupported encoding while reading manifest: " + e.getMessage(), e);
                } catch (ManifestException e2) {
                    log("Manifest in file " + file + " is invalid: " + e2.getMessage(), 0);
                    throw new BuildException("Invalid Manifest", e2, getLocation());
                }
            }
        } else {
            log("Found manifest " + file, 3);
            try {
                if (inputStream != null) {
                    if (this.manifestEncoding == null) {
                        inputStreamReader2 = new InputStreamReader(inputStream);
                    } else {
                        inputStreamReader2 = new InputStreamReader(inputStream, this.manifestEncoding);
                    }
                    this.manifest = getManifest(inputStreamReader2);
                    return;
                }
                this.manifest = getManifest(file);
            } catch (UnsupportedEncodingException e3) {
                throw new BuildException("Unsupported encoding while reading manifest: " + e3.getMessage(), e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Zip
    public Zip.ArchiveState getResourcesToAdd(ResourceCollection[] resourceCollectionArr, File file, boolean z) throws BuildException {
        boolean z2 = true;
        if (this.skipWriting) {
            Resource[][] grabManifests = grabManifests(resourceCollectionArr);
            int i = 0;
            for (Resource[] resourceArr : grabManifests) {
                i += resourceArr.length;
            }
            log("found a total of " + i + " manifests in " + grabManifests.length + " resource collections", 3);
            return new Zip.ArchiveState(true, grabManifests);
        }
        if (file.exists()) {
            try {
                this.originalManifest = getManifestFromJar(file);
                if (this.originalManifest == null) {
                    log("Updating jar since the current jar has no manifest", 3);
                    z = true;
                } else if (!createManifest().equals(this.originalManifest)) {
                    log("Updating jar since jar manifest has changed", 3);
                    z = true;
                }
            } catch (Throwable th) {
                log("error while reading original manifest in file: " + file.toString() + " due to " + th.getMessage(), 1);
                z = true;
            }
        } else {
            z = true;
        }
        this.createEmpty = z;
        if (z || !this.index) {
            z2 = z;
        } else {
            try {
                z2 = true ^ jarHasIndex(file);
            } catch (IOException unused) {
            }
        }
        return super.getResourcesToAdd(resourceCollectionArr, file, z2);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected boolean createEmptyZip(File file) throws BuildException {
        Throwable th;
        IOException e;
        ZipOutputStream zipOutputStream;
        if (!this.createEmpty) {
            return true;
        }
        if (this.emptyBehavior.equals(MSVSSConstants.WRITABLE_SKIP)) {
            if (!this.skipWriting) {
                log("Warning: skipping " + this.archiveType + " archive " + file + " because no files were included.", 1);
            }
            return true;
        } else if (!this.emptyBehavior.equals("fail")) {
            ZipOutputStream zipOutputStream2 = null;
            try {
                try {
                    if (!this.skipWriting) {
                        log("Building MANIFEST-only jar: " + getDestFile().getAbsolutePath());
                    }
                    zipOutputStream = new ZipOutputStream(getDestFile());
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                zipOutputStream.setEncoding(getEncoding());
                if (isCompress()) {
                    zipOutputStream.setMethod(8);
                } else {
                    zipOutputStream.setMethod(0);
                }
                initZipOutputStream(zipOutputStream);
                finalizeZipOutputStream(zipOutputStream);
                FileUtils.close(zipOutputStream);
                this.createEmpty = false;
                return true;
            } catch (IOException e3) {
                e = e3;
                zipOutputStream2 = zipOutputStream;
                throw new BuildException("Could not create almost empty JAR archive (" + e.getMessage() + ")", e, getLocation());
            } catch (Throwable th3) {
                th = th3;
                zipOutputStream2 = zipOutputStream;
                FileUtils.close(zipOutputStream2);
                this.createEmpty = false;
                throw th;
            }
        } else {
            throw new BuildException("Cannot create " + this.archiveType + " archive " + file + ": no files were included.", getLocation());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Zip
    public void cleanUp() {
        super.cleanUp();
        checkJarSpec();
        if (!this.doubleFilePass || !this.skipWriting) {
            this.manifest = null;
            this.configuredManifest = this.savedConfiguredManifest;
            this.filesetManifest = null;
            this.originalManifest = null;
        }
        this.rootEntries.removeAllElements();
    }

    private void checkJarSpec() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        Manifest manifest = this.configuredManifest;
        Manifest.Section mainSection = manifest == null ? null : manifest.getMainSection();
        if (mainSection == null) {
            stringBuffer.append("No Implementation-Title set.");
            stringBuffer.append("No Implementation-Version set.");
            stringBuffer.append("No Implementation-Vendor set.");
        } else {
            if (mainSection.getAttribute("Implementation-Title") == null) {
                stringBuffer.append("No Implementation-Title set.");
            }
            if (mainSection.getAttribute("Implementation-Version") == null) {
                stringBuffer.append("No Implementation-Version set.");
            }
            if (mainSection.getAttribute("Implementation-Vendor") == null) {
                stringBuffer.append("No Implementation-Vendor set.");
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.append(property);
            stringBuffer.append("Location: ");
            stringBuffer.append(getLocation());
            stringBuffer.append(property);
            if (!this.strict.getValue().equalsIgnoreCase("fail")) {
                logWhenWriting(stringBuffer.toString(), this.strict.getLogLevel());
                return;
            }
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    public void reset() {
        super.reset();
        this.emptyBehavior = "create";
        this.configuredManifest = null;
        this.filesetManifestConfig = null;
        this.mergeManifestsMain = false;
        this.manifestFile = null;
        this.index = false;
    }

    /* loaded from: classes2.dex */
    public static class FilesetManifestConfig extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{MSVSSConstants.WRITABLE_SKIP, "merge", "mergewithoutmain"};
        }
    }

    protected final void writeIndexLikeList(List<String> list, List<String> list2, PrintWriter printWriter) throws IOException {
        Collections.sort(list);
        Collections.sort(list2);
        for (String str : list) {
            String replace = str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
            if (replace.startsWith("./")) {
                replace = replace.substring(2);
            }
            while (replace.startsWith("/")) {
                replace = replace.substring(1);
            }
            int lastIndexOf = replace.lastIndexOf(47);
            if (lastIndexOf != -1) {
                replace = replace.substring(0, lastIndexOf);
            }
            if (this.indexMetaInf || !replace.startsWith("META-INF")) {
                printWriter.println(replace);
            }
        }
        for (String str2 : list2) {
            printWriter.println(str2);
        }
    }

    protected static String findJarName(String str, String[] strArr) {
        if (strArr == null) {
            return new File(str).getName();
        }
        String replace = str.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX);
        TreeMap treeMap = new TreeMap(new Comparator<Object>() { // from class: org.apache.tools.ant.taskdefs.Jar.1
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                if (!(obj instanceof String) || !(obj2 instanceof String)) {
                    return 0;
                }
                return ((String) obj2).length() - ((String) obj).length();
            }
        });
        for (int i = 0; i < strArr.length; i++) {
            if (replace.endsWith(strArr[i])) {
                treeMap.put(strArr[i], strArr[i]);
            } else {
                int indexOf = strArr[i].indexOf("/");
                String str2 = strArr[i];
                while (true) {
                    if (indexOf > -1) {
                        str2 = str2.substring(indexOf + 1);
                        if (replace.endsWith(str2)) {
                            treeMap.put(str2, strArr[i]);
                            break;
                        }
                        indexOf = str2.indexOf("/");
                    }
                }
            }
        }
        if (treeMap.size() == 0) {
            return null;
        }
        return (String) treeMap.get(treeMap.firstKey());
    }

    protected static void grabFilesAndDirs(String str, List<String> list, List<String> list2) throws IOException {
        Throwable th;
        org.apache.tools.zip.ZipFile zipFile;
        try {
            zipFile = new org.apache.tools.zip.ZipFile(str, EmailConstants.UTF_8);
            try {
                Enumeration<org.apache.tools.zip.ZipEntry> entries = zipFile.getEntries();
                HashSet hashSet = new HashSet();
                while (entries.hasMoreElements()) {
                    org.apache.tools.zip.ZipEntry nextElement = entries.nextElement();
                    String name = nextElement.getName();
                    if (nextElement.isDirectory()) {
                        hashSet.add(name);
                    } else if (name.indexOf("/") == -1) {
                        list2.add(name);
                    } else {
                        hashSet.add(name.substring(0, name.lastIndexOf("/") + 1));
                    }
                }
                list.addAll(hashSet);
                zipFile.close();
            } catch (Throwable th2) {
                th = th2;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
        }
    }

    private Resource[][] grabManifests(ResourceCollection[] resourceCollectionArr) {
        Resource[][] resourceArr = new Resource[resourceCollectionArr.length];
        for (int i = 0; i < resourceCollectionArr.length; i++) {
            Resource[][] grabResources = resourceCollectionArr[i] instanceof FileSet ? grabResources(new FileSet[]{(FileSet) resourceCollectionArr[i]}) : grabNonFileSetResources(new ResourceCollection[]{resourceCollectionArr[i]});
            int i2 = 0;
            while (true) {
                if (i2 >= grabResources[0].length) {
                    break;
                }
                String replace = grabResources[0][i2].getName().replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
                if (resourceCollectionArr[i] instanceof ArchiveFileSet) {
                    ArchiveFileSet archiveFileSet = (ArchiveFileSet) resourceCollectionArr[i];
                    if (!"".equals(archiveFileSet.getFullpath(getProject()))) {
                        replace = archiveFileSet.getFullpath(getProject());
                    } else if (!"".equals(archiveFileSet.getPrefix(getProject()))) {
                        String prefix = archiveFileSet.getPrefix(getProject());
                        if (!prefix.endsWith("/") && !prefix.endsWith("\\")) {
                            prefix = prefix + "/";
                        }
                        replace = prefix + replace;
                    }
                }
                if (replace.equalsIgnoreCase(MANIFEST_NAME)) {
                    Resource[] resourceArr2 = new Resource[1];
                    resourceArr2[0] = grabResources[0][i2];
                    resourceArr[i] = resourceArr2;
                    break;
                }
                i2++;
            }
            if (resourceArr[i] == null) {
                resourceArr[i] = new Resource[0];
            }
        }
        return resourceArr;
    }

    /* loaded from: classes2.dex */
    public static class StrictMode extends EnumeratedAttribute {
        public StrictMode() {
        }

        public StrictMode(String str) {
            setValue(str);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"fail", "warn", Definer.OnError.POLICY_IGNORE};
        }

        public int getLogLevel() {
            return getValue().equals(Definer.OnError.POLICY_IGNORE) ? 3 : 1;
        }
    }
}
