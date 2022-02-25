package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.FilterSet;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.ResourceFactory;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.FlatFileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.LinkedHashtable;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.ant.util.SourceFileScanner;

/* loaded from: classes2.dex */
public class Copy extends Task {
    private static final String MSG_WHEN_COPYING_EMPTY_RC_TO_FILE = "Cannot perform operation from directory to file.";
    private long granularity;
    static final File NULL_FILE_PLACEHOLDER = new File("/NULL_FILE");
    static final String LINE_SEPARATOR = System.getProperty("line.separator");
    protected File file = null;
    protected File destFile = null;
    protected File destDir = null;
    protected Vector<ResourceCollection> rcs = new Vector<>();
    protected Vector<ResourceCollection> filesets = this.rcs;
    private boolean enableMultipleMappings = false;
    protected boolean filtering = false;
    protected boolean preserveLastModified = false;
    protected boolean forceOverwrite = false;
    protected boolean flatten = false;
    protected int verbosity = 3;
    protected boolean includeEmpty = true;
    protected boolean failonerror = true;
    protected Hashtable<String, String[]> fileCopyMap = new LinkedHashtable();
    protected Hashtable<String, String[]> dirCopyMap = new LinkedHashtable();
    protected Hashtable<File, File> completeDirMap = new LinkedHashtable();
    protected Mapper mapperElement = null;
    private final Vector<FilterChain> filterChains = new Vector<>();
    private final Vector<FilterSet> filterSets = new Vector<>();
    private String inputEncoding = null;
    private String outputEncoding = null;
    private boolean force = false;
    private boolean quiet = false;
    private Resource singleResource = null;
    protected FileUtils fileUtils = FileUtils.getFileUtils();

    public Copy() {
        this.granularity = 0L;
        this.granularity = this.fileUtils.getFileTimestampGranularity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileUtils getFileUtils() {
        return this.fileUtils;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setTofile(File file) {
        this.destFile = file;
    }

    public void setTodir(File file) {
        this.destDir = file;
    }

    public FilterChain createFilterChain() {
        FilterChain filterChain = new FilterChain();
        this.filterChains.addElement(filterChain);
        return filterChain;
    }

    public FilterSet createFilterSet() {
        FilterSet filterSet = new FilterSet();
        this.filterSets.addElement(filterSet);
        return filterSet;
    }

    @Deprecated
    public void setPreserveLastModified(String str) {
        setPreserveLastModified(Project.toBoolean(str));
    }

    public void setPreserveLastModified(boolean z) {
        this.preserveLastModified = z;
    }

    public boolean getPreserveLastModified() {
        return this.preserveLastModified;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Vector<FilterSet> getFilterSets() {
        return this.filterSets;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Vector<FilterChain> getFilterChains() {
        return this.filterChains;
    }

    public void setFiltering(boolean z) {
        this.filtering = z;
    }

    public void setOverwrite(boolean z) {
        this.forceOverwrite = z;
    }

    public void setForce(boolean z) {
        this.force = z;
    }

    public boolean getForce() {
        return this.force;
    }

    public void setFlatten(boolean z) {
        this.flatten = z;
    }

    public void setVerbose(boolean z) {
        this.verbosity = z ? 2 : 3;
    }

    public void setIncludeEmptyDirs(boolean z) {
        this.includeEmpty = z;
    }

    public void setQuiet(boolean z) {
        this.quiet = z;
    }

    public void setEnableMultipleMappings(boolean z) {
        this.enableMultipleMappings = z;
    }

    public boolean isEnableMultipleMapping() {
        return this.enableMultipleMappings;
    }

    public void setFailOnError(boolean z) {
        this.failonerror = z;
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void add(ResourceCollection resourceCollection) {
        this.rcs.add(resourceCollection);
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

    public void setEncoding(String str) {
        this.inputEncoding = str;
        if (this.outputEncoding == null) {
            this.outputEncoding = str;
        }
    }

    public String getEncoding() {
        return this.inputEncoding;
    }

    public void setOutputEncoding(String str) {
        this.outputEncoding = str;
    }

    public String getOutputEncoding() {
        return this.outputEncoding;
    }

    public void setGranularity(long j) {
        this.granularity = j;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:128|9|10|(3:12|(5:37|(3:140|42|43)|44|(4:47|(3:148|49|(2:51|(3:146|53|153)(1:152))(3:136|54|55))(9:145|56|(2:58|(1:60))(1:61)|62|(3:150|66|154)|67|(1:69)(1:70)|71|155)|151|45)|144)(7:16|132|17|18|(1:26)|27|141)|72)|139|73|(2:134|74)|82|(5:87|102|(1:104)|105|106)|88|(1:90)|130|91|92|102|(0)|105|106) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x01fd, code lost:
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01ff, code lost:
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0220, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01da, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01de, code lost:
        if (r18.failonerror == false) goto L_0x01e0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01e2, code lost:
        if (r18.quiet == false) goto L_0x01e4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01e4, code lost:
        log("Warning: " + getMessage(r0), 0);
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x020a A[DONT_GENERATE] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 657
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Copy.execute():void");
    }

    private void copySingleFile() {
        File file = this.file;
        if (file == null) {
            return;
        }
        if (file.exists()) {
            if (this.destFile == null) {
                this.destFile = new File(this.destDir, this.file.getName());
            }
            if (this.forceOverwrite || !this.destFile.exists() || this.file.lastModified() - this.granularity > this.destFile.lastModified()) {
                this.fileCopyMap.put(this.file.getAbsolutePath(), new String[]{this.destFile.getAbsolutePath()});
                return;
            }
            log(this.file + " omitted as " + this.destFile + " is up to date.", 3);
            return;
        }
        String str = "Warning: Could not find file " + this.file.getAbsolutePath() + " to copy.";
        if (this.failonerror) {
            throw new BuildException(str);
        } else if (!this.quiet) {
            log(str, 0);
        }
    }

    private void iterateOverBaseDirs(HashSet<File> hashSet, HashMap<File, List<String>> hashMap, HashMap<File, List<String>> hashMap2) {
        Iterator<File> it = hashSet.iterator();
        while (it.hasNext()) {
            File next = it.next();
            List<String> list = hashMap2.get(next);
            List<String> list2 = hashMap.get(next);
            String[] strArr = new String[0];
            if (list != null) {
                strArr = (String[]) list.toArray(strArr);
            }
            String[] strArr2 = new String[0];
            if (list2 != null) {
                strArr2 = (String[]) list2.toArray(strArr2);
            }
            if (next == NULL_FILE_PLACEHOLDER) {
                next = null;
            }
            scan(next, this.destDir, strArr, strArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateAttributes() throws BuildException {
        if (this.file == null && this.rcs.size() == 0) {
            throw new BuildException("Specify at least one source--a file or a resource collection.");
        } else if (this.destFile != null && this.destDir != null) {
            throw new BuildException("Only one of tofile and todir may be set.");
        } else if (this.destFile == null && this.destDir == null) {
            throw new BuildException("One of tofile or todir must be set.");
        } else {
            File file = this.file;
            if (file == null || !file.isDirectory()) {
                if (this.destFile != null && this.rcs.size() > 0) {
                    if (this.rcs.size() <= 1) {
                        ResourceCollection elementAt = this.rcs.elementAt(0);
                        if (!elementAt.isFilesystemOnly() && !supportsNonFileResources()) {
                            throw new BuildException("Only FileSystem resources are supported.");
                        } else if (elementAt.size() == 0) {
                            throw new BuildException(MSG_WHEN_COPYING_EMPTY_RC_TO_FILE);
                        } else if (elementAt.size() == 1) {
                            Resource next = elementAt.iterator().next();
                            FileProvider fileProvider = (FileProvider) next.mo14823as(FileProvider.class);
                            if (this.file == null) {
                                if (fileProvider != null) {
                                    this.file = fileProvider.getFile();
                                } else {
                                    this.singleResource = next;
                                }
                                this.rcs.removeElementAt(0);
                            } else {
                                throw new BuildException("Cannot concatenate multiple files into a single file.");
                            }
                        } else {
                            throw new BuildException("Cannot concatenate multiple files into a single file.");
                        }
                    } else {
                        throw new BuildException("Cannot concatenate multiple files into a single file.");
                    }
                }
                File file2 = this.destFile;
                if (file2 != null) {
                    this.destDir = file2.getParentFile();
                    return;
                }
                return;
            }
            throw new BuildException("Use a resource collection to copy directories.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scan(File file, File file2, String[] strArr, String[] strArr2) {
        FileNameMapper mapper = getMapper();
        buildMap(file, file2, strArr, mapper, this.fileCopyMap);
        if (this.includeEmpty) {
            buildMap(file, file2, strArr2, mapper, this.dirCopyMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<Resource, String[]> scan(Resource[] resourceArr, File file) {
        return buildMap(resourceArr, file, getMapper());
    }

    protected void buildMap(File file, File file2, String[] strArr, FileNameMapper fileNameMapper, Hashtable<String, String[]> hashtable) {
        String[] strArr2;
        if (this.forceOverwrite) {
            Vector vector = new Vector();
            for (int i = 0; i < strArr.length; i++) {
                if (fileNameMapper.mapFileName(strArr[i]) != null) {
                    vector.addElement(strArr[i]);
                }
            }
            strArr2 = new String[vector.size()];
            vector.copyInto(strArr2);
        } else {
            strArr2 = new SourceFileScanner(this).restrict(strArr, file, file2, fileNameMapper, this.granularity);
        }
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            File file3 = new File(file, strArr2[i2]);
            String[] mapFileName = fileNameMapper.mapFileName(strArr2[i2]);
            if (!this.enableMultipleMappings) {
                hashtable.put(file3.getAbsolutePath(), new String[]{new File(file2, mapFileName[0]).getAbsolutePath()});
            } else {
                for (int i3 = 0; i3 < mapFileName.length; i3++) {
                    mapFileName[i3] = new File(file2, mapFileName[i3]).getAbsolutePath();
                }
                hashtable.put(file3.getAbsolutePath(), mapFileName);
            }
        }
    }

    protected Map<Resource, String[]> buildMap(Resource[] resourceArr, final File file, FileNameMapper fileNameMapper) {
        Resource[] resourceArr2;
        HashMap hashMap = new HashMap();
        if (this.forceOverwrite) {
            Vector vector = new Vector();
            for (int i = 0; i < resourceArr.length; i++) {
                if (fileNameMapper.mapFileName(resourceArr[i].getName()) != null) {
                    vector.addElement(resourceArr[i]);
                }
            }
            resourceArr2 = new Resource[vector.size()];
            vector.copyInto(resourceArr2);
        } else {
            resourceArr2 = ResourceUtils.selectOutOfDateSources(this, resourceArr, fileNameMapper, new ResourceFactory() { // from class: org.apache.tools.ant.taskdefs.Copy.1
                @Override // org.apache.tools.ant.types.ResourceFactory
                public Resource getResource(String str) {
                    return new FileResource(file, str);
                }
            }, this.granularity);
        }
        for (int i2 = 0; i2 < resourceArr2.length; i2++) {
            String[] mapFileName = fileNameMapper.mapFileName(resourceArr2[i2].getName());
            for (String str : mapFileName) {
                if (str == null) {
                    throw new BuildException("Can't copy a resource without a name if the mapper doesn't provide one.");
                }
            }
            if (!this.enableMultipleMappings) {
                hashMap.put(resourceArr2[i2], new String[]{new File(file, mapFileName[0]).getAbsolutePath()});
            } else {
                for (int i3 = 0; i3 < mapFileName.length; i3++) {
                    mapFileName[i3] = new File(file, mapFileName[i3]).getAbsolutePath();
                }
                hashMap.put(resourceArr2[i2], mapFileName);
            }
        }
        return hashMap;
    }

    protected void doFileOperations() {
        String[] value;
        if (this.fileCopyMap.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Copying ");
            sb.append(this.fileCopyMap.size());
            sb.append(" file");
            sb.append(this.fileCopyMap.size() == 1 ? "" : "s");
            sb.append(" to ");
            sb.append(this.destDir.getAbsolutePath());
            log(sb.toString());
            for (Map.Entry<String, String[]> entry : this.fileCopyMap.entrySet()) {
                String key = entry.getKey();
                for (String str : entry.getValue()) {
                    if (key.equals(str)) {
                        log("Skipping self-copy of " + key, this.verbosity);
                    } else {
                        try {
                            log("Copying " + key + " to " + str, this.verbosity);
                            FilterSetCollection filterSetCollection = new FilterSetCollection();
                            if (this.filtering) {
                                filterSetCollection.addFilterSet(getProject().getGlobalFilterSet());
                            }
                            Iterator<FilterSet> it = this.filterSets.iterator();
                            while (it.hasNext()) {
                                filterSetCollection.addFilterSet(it.next());
                            }
                            this.fileUtils.copyFile(new File(key), new File(str), filterSetCollection, this.filterChains, this.forceOverwrite, this.preserveLastModified, false, this.inputEncoding, this.outputEncoding, getProject(), getForce());
                        } catch (IOException e) {
                            String str2 = "Failed to copy " + key + " to " + str + " due to " + getDueTo(e);
                            File file = new File(str);
                            if (!(e instanceof ResourceUtils.ReadOnlyTargetFileException) && file.exists() && !file.delete()) {
                                str2 = str2 + " and I couldn't delete the corrupt " + str;
                            }
                            if (!this.failonerror) {
                                log(str2, 0);
                            } else {
                                throw new BuildException(str2, e, getLocation());
                            }
                        }
                    }
                }
            }
        }
        if (this.includeEmpty) {
            int i = 0;
            for (String[] strArr : this.dirCopyMap.values()) {
                int i2 = i;
                for (String str3 : strArr) {
                    File file2 = new File(str3);
                    if (!file2.exists()) {
                        if (file2.mkdirs() || file2.isDirectory()) {
                            i2++;
                        } else {
                            log("Unable to create directory " + file2.getAbsolutePath(), 0);
                        }
                    }
                }
                i = i2;
            }
            if (i > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Copied ");
                sb2.append(this.dirCopyMap.size());
                sb2.append(" empty director");
                sb2.append(this.dirCopyMap.size() == 1 ? "y" : "ies");
                sb2.append(" to ");
                sb2.append(i);
                sb2.append(" empty director");
                sb2.append(i == 1 ? "y" : "ies");
                sb2.append(" under ");
                sb2.append(this.destDir.getAbsolutePath());
                log(sb2.toString());
            }
        }
    }

    protected void doResourceOperations(Map<Resource, String[]> map) {
        int i;
        String str;
        IOException e;
        FilterSetCollection filterSetCollection;
        if (map.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Copying ");
            sb.append(map.size());
            sb.append(" resource");
            sb.append(map.size() == 1 ? "" : "s");
            sb.append(" to ");
            sb.append(this.destDir.getAbsolutePath());
            log(sb.toString());
            for (Map.Entry<Resource, String[]> entry : map.entrySet()) {
                Resource key = entry.getKey();
                String[] value = entry.getValue();
                int length = value.length;
                int i2 = 0;
                while (i2 < length) {
                    String str2 = value[i2];
                    try {
                        log("Copying " + key + " to " + str2, this.verbosity);
                        filterSetCollection = new FilterSetCollection();
                        if (this.filtering) {
                            filterSetCollection.addFilterSet(getProject().getGlobalFilterSet());
                        }
                        Iterator<FilterSet> it = this.filterSets.iterator();
                        while (it.hasNext()) {
                            filterSetCollection.addFilterSet(it.next());
                        }
                        str = str2;
                        i = i2;
                        length = length;
                    } catch (IOException e2) {
                        e = e2;
                        str = str2;
                        i = i2;
                        length = length;
                    }
                    try {
                        ResourceUtils.copyResource(key, new FileResource(this.destDir, str2), filterSetCollection, this.filterChains, this.forceOverwrite, this.preserveLastModified, false, this.inputEncoding, this.outputEncoding, getProject(), getForce());
                    } catch (IOException e3) {
                        e = e3;
                        String str3 = "Failed to copy " + key + " to " + str + " due to " + getDueTo(e);
                        File file = new File(str);
                        if (!(e instanceof ResourceUtils.ReadOnlyTargetFileException) && file.exists() && !file.delete()) {
                            str3 = str3 + " and I couldn't delete the corrupt " + str;
                        }
                        if (!this.failonerror) {
                            log(str3, 0);
                            i2 = i + 1;
                        } else {
                            throw new BuildException(str3, e, getLocation());
                        }
                    }
                    i2 = i + 1;
                }
            }
        }
    }

    protected boolean supportsNonFileResources() {
        return getClass().equals(Copy.class);
    }

    private static void add(File file, String[] strArr, Map<File, List<String>> map) {
        if (strArr != null) {
            File keyFile = getKeyFile(file);
            List<String> list = map.get(keyFile);
            if (list == null) {
                list = new ArrayList<>(strArr.length);
                map.put(keyFile, list);
            }
            list.addAll(Arrays.asList(strArr));
        }
    }

    private static void add(File file, String str, Map<File, List<String>> map) {
        if (str != null) {
            add(file, new String[]{str}, map);
        }
    }

    private static File getKeyFile(File file) {
        return file == null ? NULL_FILE_PLACEHOLDER : file;
    }

    private FileNameMapper getMapper() {
        Mapper mapper = this.mapperElement;
        if (mapper != null) {
            return mapper.getImplementation();
        }
        if (this.flatten) {
            return new FlatFileNameMapper();
        }
        return new IdentityMapper();
    }

    private String getMessage(Exception exc) {
        return exc.getMessage() == null ? exc.toString() : exc.getMessage();
    }

    private String getDueTo(Exception exc) {
        boolean z = exc.getClass() == IOException.class;
        StringBuffer stringBuffer = new StringBuffer();
        if (!z || exc.getMessage() == null) {
            stringBuffer.append(exc.getClass().getName());
        }
        if (exc.getMessage() != null) {
            if (!z) {
                stringBuffer.append(ExpandableTextView.f6958c);
            }
            stringBuffer.append(exc.getMessage());
        }
        if (exc.getClass().getName().indexOf("MalformedInput") != -1) {
            stringBuffer.append(LINE_SEPARATOR);
            stringBuffer.append("This is normally due to the input file containing invalid");
            stringBuffer.append(LINE_SEPARATOR);
            stringBuffer.append("bytes for the character encoding used : ");
            String str = this.inputEncoding;
            if (str == null) {
                str = this.fileUtils.getDefaultEncoding();
            }
            stringBuffer.append(str);
            stringBuffer.append(LINE_SEPARATOR);
        }
        return stringBuffer.toString();
    }
}
