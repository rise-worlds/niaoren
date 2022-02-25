package org.apache.tools.ant.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.ResourceFactory;
import org.apache.tools.ant.types.TimeComparison;
import org.apache.tools.ant.types.resources.Appendable;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.Restrict;
import org.apache.tools.ant.types.resources.StringResource;
import org.apache.tools.ant.types.resources.Touchable;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.resources.selectors.Date;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;
import org.apache.tools.ant.types.selectors.SelectorUtils;

/* loaded from: classes2.dex */
public class ResourceUtils {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    public static final String ISO_8859_1 = "ISO-8859-1";
    private static final long MAX_IO_CHUNK_SIZE = 16777216;

    /* loaded from: classes2.dex */
    public interface ResourceSelectorProvider {
        ResourceSelector getTargetSelectorForSource(Resource resource);
    }

    public static Resource[] selectOutOfDateSources(ProjectComponent projectComponent, Resource[] resourceArr, FileNameMapper fileNameMapper, ResourceFactory resourceFactory) {
        return selectOutOfDateSources(projectComponent, resourceArr, fileNameMapper, resourceFactory, FILE_UTILS.getFileTimestampGranularity());
    }

    public static Resource[] selectOutOfDateSources(ProjectComponent projectComponent, Resource[] resourceArr, FileNameMapper fileNameMapper, ResourceFactory resourceFactory, long j) {
        Union union = new Union();
        union.addAll(Arrays.asList(resourceArr));
        ResourceCollection selectOutOfDateSources = selectOutOfDateSources(projectComponent, union, fileNameMapper, resourceFactory, j);
        return selectOutOfDateSources.size() == 0 ? new Resource[0] : ((Union) selectOutOfDateSources).listResources();
    }

    public static ResourceCollection selectOutOfDateSources(ProjectComponent projectComponent, ResourceCollection resourceCollection, FileNameMapper fileNameMapper, ResourceFactory resourceFactory, final long j) {
        logFuture(projectComponent, resourceCollection, j);
        return selectSources(projectComponent, resourceCollection, fileNameMapper, resourceFactory, new ResourceSelectorProvider() { // from class: org.apache.tools.ant.util.ResourceUtils.1
            @Override // org.apache.tools.ant.util.ResourceUtils.ResourceSelectorProvider
            public ResourceSelector getTargetSelectorForSource(final Resource resource) {
                return new ResourceSelector() { // from class: org.apache.tools.ant.util.ResourceUtils.1.1
                    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
                    public boolean isSelected(Resource resource2) {
                        return SelectorUtils.isOutOfDate(resource, resource2, j);
                    }
                };
            }
        });
    }

    public static ResourceCollection selectSources(ProjectComponent projectComponent, ResourceCollection resourceCollection, FileNameMapper fileNameMapper, ResourceFactory resourceFactory, ResourceSelectorProvider resourceSelectorProvider) {
        if (resourceCollection.size() == 0) {
            projectComponent.log("No sources found.", 3);
            return Resources.NONE;
        }
        Union instance = Union.getInstance(resourceCollection);
        Union union = new Union();
        for (Resource resource : instance) {
            String name = resource.getName();
            if (name != null) {
                name = name.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar);
            }
            String[] strArr = null;
            try {
                strArr = fileNameMapper.mapFileName(name);
            } catch (Exception e) {
                projectComponent.log("Caught " + e + " mapping resource " + resource, 3);
            }
            if (strArr == null || strArr.length == 0) {
                projectComponent.log(resource + " skipped - don't know how to handle it", 3);
            } else {
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i] == null) {
                        strArr[i] = "(no name)";
                    }
                }
                Union union2 = new Union();
                for (String str : strArr) {
                    union2.add(resourceFactory.getResource(str.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX)));
                }
                Restrict restrict = new Restrict();
                restrict.add(resourceSelectorProvider.getTargetSelectorForSource(resource));
                restrict.add(union2);
                if (restrict.size() > 0) {
                    union.add(resource);
                    Resource next = restrict.iterator().next();
                    StringBuilder sb = new StringBuilder();
                    sb.append(resource.getName());
                    sb.append(" added as ");
                    sb.append(next.getName());
                    sb.append(next.isExists() ? " is outdated." : " doesn't exist.");
                    projectComponent.log(sb.toString(), 3);
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(resource.getName());
                    sb2.append(" omitted as ");
                    sb2.append(union2.toString());
                    sb2.append(union2.size() == 1 ? " is" : " are ");
                    sb2.append(" up to date.");
                    projectComponent.log(sb2.toString(), 3);
                }
            }
        }
        return union;
    }

    public static void copyResource(Resource resource, Resource resource2) throws IOException {
        copyResource(resource, resource2, null);
    }

    public static void copyResource(Resource resource, Resource resource2, Project project) throws IOException {
        copyResource(resource, resource2, null, null, false, false, null, null, project);
    }

    public static void copyResource(Resource resource, Resource resource2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, String str, String str2, Project project) throws IOException {
        copyResource(resource, resource2, filterSetCollection, vector, z, z2, false, str, str2, project);
    }

    public static void copyResource(Resource resource, Resource resource2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, boolean z3, String str, String str2, Project project) throws IOException {
        copyResource(resource, resource2, filterSetCollection, vector, z, z2, z3, str, str2, project, false);
    }

    public static void copyResource(Resource resource, Resource resource2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, boolean z3, String str, String str2, Project project, boolean z4) throws IOException {
        Touchable touchable;
        if (z || SelectorUtils.isOutOfDate(resource, resource2, FileUtils.getFileUtils().getFileTimestampGranularity())) {
            boolean z5 = false;
            boolean z6 = filterSetCollection != null && filterSetCollection.hasFilters();
            boolean z7 = vector != null && vector.size() > 0;
            String encoding = resource instanceof StringResource ? ((StringResource) resource).getEncoding() : str;
            File file = null;
            if (resource2.mo14823as(FileProvider.class) != null) {
                file = ((FileProvider) resource2.mo14823as(FileProvider.class)).getFile();
            }
            if (file != null && file.isFile() && !file.canWrite()) {
                if (!z4) {
                    throw new ReadOnlyTargetFileException(file);
                } else if (!FILE_UTILS.tryHardToDelete(file)) {
                    throw new IOException("failed to delete read-only destination file " + file);
                }
            }
            if (z6) {
                copyWithFilterSets(resource, resource2, filterSetCollection, vector, z7, z3, encoding, str2, project);
            } else if (z7 || ((encoding != null && !encoding.equals(str2)) || (encoding == null && str2 != null))) {
                copyWithFilterChainsOrTranscoding(resource, resource2, vector, z7, z3, encoding, str2, project);
            } else {
                if (!(resource.mo14823as(FileProvider.class) == null || file == null || z3)) {
                    File file2 = ((FileProvider) resource.mo14823as(FileProvider.class)).getFile();
                    try {
                        copyUsingFileChannels(file2, file);
                        z5 = true;
                    } catch (IOException e) {
                        String str3 = "Attempt to copy " + file2 + " to " + file + " using NIO Channels failed due to '" + e.getMessage() + "'.  Falling back to streams.";
                        if (project != null) {
                            project.log(str3, 1);
                        } else {
                            System.err.println(str3);
                        }
                    }
                }
                if (!z5) {
                    copyUsingStreams(resource, resource2, z3, project);
                }
            }
            if (z2 && (touchable = (Touchable) resource2.mo14823as(Touchable.class)) != null) {
                setLastModified(touchable, resource.getLastModified());
            }
        }
    }

    public static void setLastModified(Touchable touchable, long j) {
        if (j < 0) {
            j = System.currentTimeMillis();
        }
        touchable.touch(j);
    }

    public static boolean contentEquals(Resource resource, Resource resource2, boolean z) throws IOException {
        if (resource.isExists() != resource2.isExists()) {
            return false;
        }
        if (!resource.isExists()) {
            return true;
        }
        if (resource.isDirectory() || resource2.isDirectory()) {
            return false;
        }
        if (resource.equals(resource2)) {
            return true;
        }
        if (!z) {
            long size = resource.getSize();
            long size2 = resource2.getSize();
            if (!(size == -1 || size2 == -1 || size == size2)) {
                return false;
            }
        }
        return compareContent(resource, resource2, z) == 0;
    }

    public static int compareContent(Resource resource, Resource resource2, boolean z) throws IOException {
        if (resource.equals(resource2)) {
            return 0;
        }
        boolean isExists = resource.isExists();
        boolean isExists2 = resource2.isExists();
        if (!isExists && !isExists2) {
            return 0;
        }
        if (isExists != isExists2) {
            return isExists ? 1 : -1;
        }
        boolean isDirectory = resource.isDirectory();
        boolean isDirectory2 = resource2.isDirectory();
        if (!isDirectory || !isDirectory2) {
            return (isDirectory || isDirectory2) ? isDirectory ? -1 : 1 : z ? textCompare(resource, resource2) : binaryCompare(resource, resource2);
        }
        return 0;
    }

    public static FileResource asFileResource(FileProvider fileProvider) {
        if ((fileProvider instanceof FileResource) || fileProvider == null) {
            return (FileResource) fileProvider;
        }
        FileResource fileResource = new FileResource(fileProvider.getFile());
        fileResource.setProject(Project.getProject(fileProvider));
        return fileResource;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        if (r3.read() != (-1)) goto L_0x0023;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
        r0 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int binaryCompare(org.apache.tools.ant.types.Resource r3, org.apache.tools.ant.types.Resource r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: all -> 0x0037
            java.io.InputStream r3 = r3.getInputStream()     // Catch: all -> 0x0037
            r1.<init>(r3)     // Catch: all -> 0x0037
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: all -> 0x0035
            java.io.InputStream r4 = r4.getInputStream()     // Catch: all -> 0x0035
            r3.<init>(r4)     // Catch: all -> 0x0035
        L_0x0013:
            int r4 = r1.read()     // Catch: all -> 0x0032
            r0 = -1
            if (r4 == r0) goto L_0x002a
            int r2 = r3.read()     // Catch: all -> 0x0032
            if (r4 == r2) goto L_0x0013
            if (r4 <= r2) goto L_0x0023
            r0 = 1
        L_0x0023:
            org.apache.tools.ant.util.FileUtils.close(r1)
            org.apache.tools.ant.util.FileUtils.close(r3)
            return r0
        L_0x002a:
            int r4 = r3.read()     // Catch: all -> 0x0032
            if (r4 != r0) goto L_0x0023
            r0 = 0
            goto L_0x0023
        L_0x0032:
            r4 = move-exception
            r0 = r3
            goto L_0x0039
        L_0x0035:
            r4 = move-exception
            goto L_0x0039
        L_0x0037:
            r4 = move-exception
            r1 = r0
        L_0x0039:
            org.apache.tools.ant.util.FileUtils.close(r1)
            org.apache.tools.ant.util.FileUtils.close(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.util.ResourceUtils.binaryCompare(org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.Resource):int");
    }

    private static int textCompare(Resource resource, Resource resource2) throws IOException {
        Throwable th;
        BufferedReader bufferedReader;
        int i;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            try {
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(resource2.getInputStream()));
                try {
                    String readLine = bufferedReader.readLine();
                    while (true) {
                        if (readLine != null) {
                            String readLine2 = bufferedReader3.readLine();
                            if (!readLine.equals(readLine2)) {
                                i = readLine2 == null ? 1 : readLine.compareTo(readLine2);
                            } else {
                                readLine = bufferedReader.readLine();
                            }
                        } else {
                            i = bufferedReader3.readLine() == null ? 0 : -1;
                        }
                    }
                    FileUtils.close(bufferedReader);
                    FileUtils.close(bufferedReader3);
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader3;
                    FileUtils.close(bufferedReader);
                    FileUtils.close(bufferedReader2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    private static void logFuture(ProjectComponent projectComponent, ResourceCollection resourceCollection, long j) {
        long currentTimeMillis = System.currentTimeMillis() + j;
        Date date = new Date();
        date.setMillis(currentTimeMillis);
        date.setWhen(TimeComparison.AFTER);
        Restrict restrict = new Restrict();
        restrict.add(date);
        restrict.add(resourceCollection);
        Iterator<Resource> it = restrict.iterator();
        while (it.hasNext()) {
            projectComponent.log("Warning: " + it.next().getName() + " modified in the future.", 1);
        }
    }

    private static void copyWithFilterSets(Resource resource, Resource resource2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, String str, String str2, Project project) throws IOException {
        BufferedWriter bufferedWriter;
        Throwable th;
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        BufferedReader bufferedReader = null;
        try {
            if (str == null) {
                inputStreamReader = new InputStreamReader(resource.getInputStream());
            } else {
                inputStreamReader = new InputStreamReader(resource.getInputStream(), str);
            }
            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
            try {
                OutputStream outputStream = getOutputStream(resource2, z2, project);
                if (str2 == null) {
                    outputStreamWriter = new OutputStreamWriter(outputStream);
                } else {
                    outputStreamWriter = new OutputStreamWriter(outputStream, str2);
                }
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                if (z) {
                    try {
                        ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
                        chainReaderHelper.setBufferSize(8192);
                        chainReaderHelper.setPrimaryReader(bufferedReader2);
                        chainReaderHelper.setFilterChains(vector);
                        chainReaderHelper.setProject(project);
                        bufferedReader = new BufferedReader(chainReaderHelper.getAssembledReader());
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        FileUtils.close(bufferedWriter);
                        FileUtils.close(bufferedReader);
                        throw th;
                    }
                } else {
                    bufferedReader = bufferedReader2;
                }
                try {
                    LineTokenizer lineTokenizer = new LineTokenizer();
                    lineTokenizer.setIncludeDelims(true);
                    for (String token = lineTokenizer.getToken(bufferedReader); token != null; token = lineTokenizer.getToken(bufferedReader)) {
                        if (token.length() == 0) {
                            bufferedWriter.newLine();
                        } else {
                            bufferedWriter.write(filterSetCollection.replaceTokens(token));
                        }
                    }
                    FileUtils.close(bufferedWriter);
                    FileUtils.close(bufferedReader);
                } catch (Throwable th3) {
                    th = th3;
                    FileUtils.close(bufferedWriter);
                    FileUtils.close(bufferedReader);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = bufferedReader2;
                bufferedWriter = null;
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedWriter = null;
        }
    }

    private static void copyWithFilterChainsOrTranscoding(Resource resource, Resource resource2, Vector vector, boolean z, boolean z2, String str, String str2, Project project) throws IOException {
        BufferedReader bufferedReader;
        Throwable th;
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader2;
        BufferedWriter bufferedWriter2 = null;
        try {
            if (str == null) {
                inputStreamReader = new InputStreamReader(resource.getInputStream());
            } else {
                inputStreamReader = new InputStreamReader(resource.getInputStream(), str);
            }
            BufferedReader bufferedReader3 = new BufferedReader(inputStreamReader);
            try {
                OutputStream outputStream = getOutputStream(resource2, z2, project);
                if (str2 == null) {
                    outputStreamWriter = new OutputStreamWriter(outputStream);
                } else {
                    outputStreamWriter = new OutputStreamWriter(outputStream, str2);
                }
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                if (z) {
                    try {
                        ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
                        chainReaderHelper.setBufferSize(8192);
                        chainReaderHelper.setPrimaryReader(bufferedReader3);
                        chainReaderHelper.setFilterChains(vector);
                        chainReaderHelper.setProject(project);
                        bufferedReader2 = new BufferedReader(chainReaderHelper.getAssembledReader());
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter2 = bufferedWriter;
                        bufferedReader = bufferedReader3;
                        FileUtils.close(bufferedWriter2);
                        FileUtils.close(bufferedReader);
                        throw th;
                    }
                } else {
                    bufferedReader2 = bufferedReader3;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = bufferedReader3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
        try {
            char[] cArr = new char[8192];
            while (true) {
                int read = bufferedReader2.read(cArr, 0, cArr.length);
                if (read == -1) {
                    FileUtils.close(bufferedWriter);
                    FileUtils.close(bufferedReader2);
                    return;
                }
                bufferedWriter.write(cArr, 0, read);
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedWriter2 = bufferedWriter;
            bufferedReader = bufferedReader2;
            FileUtils.close(bufferedWriter2);
            FileUtils.close(bufferedReader);
            throw th;
        }
    }

    private static void copyUsingFileChannels(File file, File file2) throws IOException {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream2;
        FileChannel channel;
        File parentFile = file2.getParentFile();
        if (parentFile == null || parentFile.isDirectory() || parentFile.mkdirs() || parentFile.isDirectory()) {
            FileChannel fileChannel = 0;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream2 = new FileOutputStream(file2);
                    try {
                        channel = fileInputStream.getChannel();
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        fileChannel = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel = null;
                    fileOutputStream = null;
                }
            } catch (Throwable th4) {
                th = th4;
                fileChannel = null;
                fileOutputStream = null;
                fileInputStream = null;
            }
            try {
                fileChannel = fileOutputStream2.getChannel();
                long size = channel.size();
                for (long j = 0; j < size; j += fileChannel.transferFrom(channel, j, Math.min((long) MAX_IO_CHUNK_SIZE, size - j))) {
                }
                FileUtils.close(channel);
                FileUtils.close(fileChannel);
                FileUtils.close(fileOutputStream2);
                FileUtils.close(fileInputStream);
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = fileOutputStream2;
                fileChannel = channel;
                FileUtils.close(fileChannel);
                FileUtils.close(fileChannel);
                FileUtils.close(fileOutputStream);
                FileUtils.close(fileInputStream);
                throw th;
            }
        } else {
            throw new IOException("failed to create the parent directory for " + file2);
        }
    }

    private static void copyUsingStreams(Resource resource, Resource resource2, boolean z, Project project) throws IOException {
        Throwable th;
        InputStream inputStream;
        OutputStream outputStream = null;
        try {
            inputStream = resource.getInputStream();
            try {
                outputStream = getOutputStream(resource2, z, project);
                byte[] bArr = new byte[8192];
                int i = 0;
                do {
                    outputStream.write(bArr, 0, i);
                    i = inputStream.read(bArr, 0, bArr.length);
                } while (i != -1);
                FileUtils.close(outputStream);
                FileUtils.close(inputStream);
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close(outputStream);
                FileUtils.close(inputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    private static OutputStream getOutputStream(Resource resource, boolean z, Project project) throws IOException {
        if (z) {
            Appendable appendable = (Appendable) resource.mo14823as(Appendable.class);
            if (appendable != null) {
                return appendable.getAppendOutputStream();
            }
            String str = "Appendable OutputStream not available for non-appendable resource " + resource + "; using plain OutputStream";
            if (project != null) {
                project.log(str, 3);
            } else {
                System.out.println(str);
            }
        }
        return resource.getOutputStream();
    }

    /* loaded from: classes2.dex */
    public static class ReadOnlyTargetFileException extends IOException {
        private static final long serialVersionUID = 1;

        public ReadOnlyTargetFileException(File file) {
            super("can't write to read-only destination file " + file);
        }
    }
}
