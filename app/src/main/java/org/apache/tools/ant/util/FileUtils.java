package org.apache.tools.ant.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.PathTokenizer;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.launch.Locator;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.types.resources.FileResource;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class FileUtils {
    static final int BUF_SIZE = 8192;
    private static final int DELETE_RETRY_SLEEP_MILLIS = 10;
    private static final int EXPAND_SPACE = 50;
    public static final long FAT_FILE_TIMESTAMP_GRANULARITY = 2000;
    public static final long NTFS_FILE_TIMESTAMP_GRANULARITY = 1;
    private static final String NULL_PLACEHOLDER = "null";
    public static final long UNIX_FILE_TIMESTAMP_GRANULARITY = 1000;
    private Object cacheFromUriLock = new Object();
    private String cacheFromUriRequest = null;
    private String cacheFromUriResponse = null;
    private static final FileUtils PRIMARY_INSTANCE = new FileUtils();
    private static Random rand = new Random(System.currentTimeMillis() + Runtime.getRuntime().freeMemory());
    private static final boolean ON_NETWARE = C3209Os.isFamily(C3209Os.FAMILY_NETWARE);
    private static final boolean ON_DOS = C3209Os.isFamily(C3209Os.FAMILY_DOS);
    private static final boolean ON_WIN9X = C3209Os.isFamily(C3209Os.FAMILY_9X);
    private static final boolean ON_WINDOWS = C3209Os.isFamily(C3209Os.FAMILY_WINDOWS);

    public boolean isUpToDate(long j, long j2, long j3) {
        return j2 != -1 && j2 >= j + j3;
    }

    public static FileUtils newFileUtils() {
        return new FileUtils();
    }

    public static FileUtils getFileUtils() {
        return PRIMARY_INSTANCE;
    }

    protected FileUtils() {
    }

    public URL getFileURL(File file) throws MalformedURLException {
        return new URL(file.toURI().toASCIIString());
    }

    public void copyFile(String str, String str2) throws IOException {
        copyFile(new File(str), new File(str2), (FilterSetCollection) null, false, false);
    }

    public void copyFile(String str, String str2, FilterSetCollection filterSetCollection) throws IOException {
        copyFile(new File(str), new File(str2), filterSetCollection, false, false);
    }

    public void copyFile(String str, String str2, FilterSetCollection filterSetCollection, boolean z) throws IOException {
        copyFile(new File(str), new File(str2), filterSetCollection, z, false);
    }

    public void copyFile(String str, String str2, FilterSetCollection filterSetCollection, boolean z, boolean z2) throws IOException {
        copyFile(new File(str), new File(str2), filterSetCollection, z, z2);
    }

    public void copyFile(String str, String str2, FilterSetCollection filterSetCollection, boolean z, boolean z2, String str3) throws IOException {
        copyFile(new File(str), new File(str2), filterSetCollection, z, z2, str3);
    }

    public void copyFile(String str, String str2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, String str3, Project project) throws IOException {
        copyFile(new File(str), new File(str2), filterSetCollection, vector, z, z2, str3, project);
    }

    public void copyFile(String str, String str2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, String str3, String str4, Project project) throws IOException {
        copyFile(new File(str), new File(str2), filterSetCollection, vector, z, z2, str3, str4, project);
    }

    public void copyFile(File file, File file2) throws IOException {
        copyFile(file, file2, (FilterSetCollection) null, false, false);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection) throws IOException {
        copyFile(file, file2, filterSetCollection, false, false);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, boolean z) throws IOException {
        copyFile(file, file2, filterSetCollection, z, false);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, boolean z, boolean z2) throws IOException {
        copyFile(file, file2, filterSetCollection, z, z2, (String) null);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, boolean z, boolean z2, String str) throws IOException {
        copyFile(file, file2, filterSetCollection, (Vector) null, z, z2, str, (Project) null);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, String str, Project project) throws IOException {
        copyFile(file, file2, filterSetCollection, vector, z, z2, str, str, project);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, String str, String str2, Project project) throws IOException {
        copyFile(file, file2, filterSetCollection, vector, z, z2, false, str, str2, project);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, boolean z3, String str, String str2, Project project) throws IOException {
        copyFile(file, file2, filterSetCollection, vector, z, z2, z3, str, str2, project, false);
    }

    public void copyFile(File file, File file2, FilterSetCollection filterSetCollection, Vector vector, boolean z, boolean z2, boolean z3, String str, String str2, Project project, boolean z4) throws IOException {
        ResourceUtils.copyResource(new FileResource(file), new FileResource(file2), filterSetCollection, vector, z, z2, z3, str, str2, project, z4);
    }

    public void setFileLastModified(File file, long j) {
        ResourceUtils.setLastModified(new FileResource(file), j);
    }

    public File resolveFile(File file, String str) {
        if (!isAbsolutePath(str)) {
            char c = File.separatorChar;
            String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, c).replace(IOUtils.DIR_SEPARATOR_WINDOWS, c);
            if (isContextRelativePath(replace)) {
                file = null;
                String property = System.getProperty("user.dir");
                if (replace.charAt(0) == c && property.charAt(0) == c) {
                    replace = dissect(property)[0] + replace.substring(1);
                }
            }
            str = new File(file, replace).getAbsolutePath();
        }
        return normalize(str);
    }

    public static boolean isContextRelativePath(String str) {
        if ((!ON_DOS && !ON_NETWARE) || str.length() == 0) {
            return false;
        }
        char c = File.separatorChar;
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, c).replace(IOUtils.DIR_SEPARATOR_WINDOWS, c);
        char charAt = replace.charAt(0);
        int length = replace.length();
        if (charAt != c || (length != 1 && replace.charAt(1) == c)) {
            if (!Character.isLetter(charAt) || length <= 1 || replace.charAt(1) != ':') {
                return false;
            }
            if (length != 2 && replace.charAt(2) == c) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAbsolutePath(String str) {
        int indexOf;
        int length = str.length();
        if (length == 0) {
            return false;
        }
        char c = File.separatorChar;
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, c).replace(IOUtils.DIR_SEPARATOR_WINDOWS, c);
        char charAt = replace.charAt(0);
        if (!ON_DOS && !ON_NETWARE) {
            return charAt == c;
        }
        if (charAt == c) {
            return ON_DOS && length > 4 && replace.charAt(1) == c && (indexOf = replace.indexOf(c, 2)) > 2 && indexOf + 1 < length;
        }
        int indexOf2 = replace.indexOf(58);
        return (Character.isLetter(charAt) && indexOf2 == 1 && replace.length() > 2 && replace.charAt(2) == c) || (ON_NETWARE && indexOf2 > 0);
    }

    public static String translatePath(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 50);
        PathTokenizer pathTokenizer = new PathTokenizer(str);
        while (pathTokenizer.hasMoreTokens()) {
            String replace = pathTokenizer.nextToken().replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
            if (stringBuffer.length() != 0) {
                stringBuffer.append(File.pathSeparatorChar);
            }
            stringBuffer.append(replace);
        }
        return stringBuffer.toString();
    }

    public File normalize(String str) {
        Stack stack = new Stack();
        String[] dissect = dissect(str);
        stack.push(dissect[0]);
        StringTokenizer stringTokenizer = new StringTokenizer(dissect[1], File.separator);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!Consts.f23430h.equals(nextToken)) {
                if (!"..".equals(nextToken)) {
                    stack.push(nextToken);
                } else if (stack.size() < 2) {
                    return new File(str);
                } else {
                    stack.pop();
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (i > 1) {
                stringBuffer.append(File.separatorChar);
            }
            stringBuffer.append(stack.elementAt(i));
        }
        return new File(stringBuffer.toString());
    }

    public String[] dissect(String str) {
        String str2;
        String str3;
        char c = File.separatorChar;
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, c).replace(IOUtils.DIR_SEPARATOR_WINDOWS, c);
        if (isAbsolutePath(replace)) {
            int indexOf = replace.indexOf(58);
            if (indexOf > 0 && (ON_DOS || ON_NETWARE)) {
                int i = indexOf + 1;
                String substring = replace.substring(0, i);
                char[] charArray = replace.toCharArray();
                str3 = substring + c;
                if (charArray[i] == c) {
                    i++;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (i < charArray.length) {
                    if (charArray[i] != c || charArray[i - 1] != c) {
                        stringBuffer.append(charArray[i]);
                    }
                    i++;
                }
                str2 = stringBuffer.toString();
            } else if (replace.length() <= 1 || replace.charAt(1) != c) {
                str3 = File.separator;
                str2 = replace.substring(1);
            } else {
                int indexOf2 = replace.indexOf(c, replace.indexOf(c, 2) + 1);
                str3 = indexOf2 > 2 ? replace.substring(0, indexOf2 + 1) : replace;
                str2 = replace.substring(str3.length());
            }
            return new String[]{str3, str2};
        }
        throw new BuildException(replace + " is not an absolute path");
    }

    public String toVMSPath(File file) {
        int i;
        String str;
        StringBuffer stringBuffer;
        String str2;
        String str3;
        String path = normalize(file.getAbsolutePath()).getPath();
        String name = file.getName();
        boolean z = path.charAt(0) == File.separatorChar;
        boolean z2 = file.isDirectory() && !name.regionMatches(true, name.length() + (-4), ".DIR", 0, 4);
        String str4 = null;
        if (z) {
            int indexOf = path.indexOf(File.separatorChar, 1);
            if (indexOf == -1) {
                return path.substring(1) + ":[000000]";
            }
            i = indexOf + 1;
            str = path.substring(1, indexOf);
        } else {
            str = null;
            i = 0;
        }
        if (z2) {
            stringBuffer = new StringBuffer(path.substring(i).replace(File.separatorChar, FilenameUtils.EXTENSION_SEPARATOR));
        } else {
            int lastIndexOf = path.lastIndexOf(File.separatorChar, path.length());
            if (lastIndexOf == -1 || lastIndexOf < i) {
                stringBuffer = null;
                str4 = path.substring(i);
            } else {
                stringBuffer = new StringBuffer(path.substring(i, lastIndexOf).replace(File.separatorChar, FilenameUtils.EXTENSION_SEPARATOR));
                int i2 = lastIndexOf + 1;
                if (path.length() > i2) {
                    str4 = path.substring(i2);
                }
            }
        }
        if (!z && stringBuffer != null) {
            stringBuffer.insert(0, FilenameUtils.EXTENSION_SEPARATOR);
        }
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            str2 = str + ":";
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (stringBuffer != null) {
            str3 = "[" + ((Object) stringBuffer) + "]";
        } else {
            str3 = "";
        }
        sb.append(str3);
        if (str4 == null) {
            str4 = "";
        }
        sb.append(str4);
        return sb.toString();
    }

    public File createTempFile(String str, String str2, File file) {
        return createTempFile(str, str2, file, false, false);
    }

    public File createTempFile(String str, String str2, File file, boolean z, boolean z2) {
        File createTempFile;
        File file2;
        String property = file == null ? System.getProperty("java.io.tmpdir") : file.getPath();
        if (str == null) {
            str = NULL_PLACEHOLDER;
        }
        if (str2 == null) {
            str2 = NULL_PLACEHOLDER;
        }
        if (z2) {
            try {
                createTempFile = File.createTempFile(str, str2, new File(property));
            } catch (IOException e) {
                throw new BuildException("Could not create tempfile in " + property, e);
            }
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#####");
            synchronized (rand) {
                do {
                    file2 = new File(property, str + decimalFormat.format(rand.nextInt(Integer.MAX_VALUE)) + str2);
                } while (file2.exists());
            }
            createTempFile = file2;
        }
        if (z) {
            createTempFile.deleteOnExit();
        }
        return createTempFile;
    }

    public File createTempFile(String str, String str2, File file, boolean z) {
        return createTempFile(str, str2, file, z, false);
    }

    public boolean contentEquals(File file, File file2) throws IOException {
        return contentEquals(file, file2, false);
    }

    public boolean contentEquals(File file, File file2, boolean z) throws IOException {
        return ResourceUtils.contentEquals(new FileResource(file), new FileResource(file2), z);
    }

    public File getParentFile(File file) {
        if (file == null) {
            return null;
        }
        return file.getParentFile();
    }

    public static String readFully(Reader reader) throws IOException {
        return readFully(reader, 8192);
    }

    public static String readFully(Reader reader, int i) throws IOException {
        if (i > 0) {
            char[] cArr = new char[i];
            StringBuffer stringBuffer = null;
            int i2 = 0;
            while (i2 != -1) {
                i2 = reader.read(cArr);
                if (i2 > 0) {
                    if (stringBuffer == null) {
                        stringBuffer = new StringBuffer();
                    }
                    stringBuffer.append(new String(cArr, 0, i2));
                }
            }
            if (stringBuffer == null) {
                return null;
            }
            return stringBuffer.toString();
        }
        throw new IllegalArgumentException("Buffer size must be greater than 0");
    }

    public static String safeReadFully(Reader reader) throws IOException {
        String readFully = readFully(reader);
        return readFully == null ? "" : readFully;
    }

    public boolean createNewFile(File file) throws IOException {
        return file.createNewFile();
    }

    public boolean createNewFile(File file, boolean z) throws IOException {
        File parentFile = file.getParentFile();
        if (z && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file.createNewFile();
    }

    public boolean isSymbolicLink(File file, String str) throws IOException {
        SymbolicLinkUtils symbolicLinkUtils = SymbolicLinkUtils.getSymbolicLinkUtils();
        if (file == null) {
            return symbolicLinkUtils.isSymbolicLink(str);
        }
        return symbolicLinkUtils.isSymbolicLink(file, str);
    }

    public String removeLeadingPath(File file, File file2) {
        String absolutePath = normalize(file.getAbsolutePath()).getAbsolutePath();
        String absolutePath2 = normalize(file2.getAbsolutePath()).getAbsolutePath();
        if (absolutePath.equals(absolutePath2)) {
            return "";
        }
        if (!absolutePath.endsWith(File.separator)) {
            absolutePath = absolutePath + File.separator;
        }
        return absolutePath2.startsWith(absolutePath) ? absolutePath2.substring(absolutePath.length()) : absolutePath2;
    }

    public boolean isLeadingPath(File file, File file2) {
        String absolutePath = normalize(file.getAbsolutePath()).getAbsolutePath();
        String absolutePath2 = normalize(file2.getAbsolutePath()).getAbsolutePath();
        if (absolutePath.equals(absolutePath2)) {
            return true;
        }
        if (!absolutePath.endsWith(File.separator)) {
            absolutePath = absolutePath + File.separator;
        }
        return absolutePath2.startsWith(absolutePath);
    }

    public String toURI(String str) {
        return new File(str).toURI().toASCIIString();
    }

    public String fromURI(String str) {
        synchronized (this.cacheFromUriLock) {
            if (str.equals(this.cacheFromUriRequest)) {
                return this.cacheFromUriResponse;
            }
            String fromURI = Locator.fromURI(str);
            if (isAbsolutePath(fromURI)) {
                fromURI = normalize(fromURI).getAbsolutePath();
            }
            this.cacheFromUriRequest = str;
            this.cacheFromUriResponse = fromURI;
            return fromURI;
        }
    }

    public boolean fileNameEquals(File file, File file2) {
        return normalize(file.getAbsolutePath()).getAbsolutePath().equals(normalize(file2.getAbsolutePath()).getAbsolutePath());
    }

    public boolean areSame(File file, File file2) throws IOException {
        if (file == null && file2 == null) {
            return true;
        }
        if (file == null || file2 == null) {
            return false;
        }
        File normalize = normalize(file.getAbsolutePath());
        File normalize2 = normalize(file2.getAbsolutePath());
        return normalize.equals(normalize2) || normalize.getCanonicalFile().equals(normalize2.getCanonicalFile());
    }

    public void rename(File file, File file2) throws IOException {
        File canonicalFile = normalize(file.getAbsolutePath()).getCanonicalFile();
        File normalize = normalize(file2.getAbsolutePath());
        if (!canonicalFile.exists()) {
            PrintStream printStream = System.err;
            printStream.println("Cannot rename nonexistent file " + canonicalFile);
        } else if (canonicalFile.getAbsolutePath().equals(normalize.getAbsolutePath())) {
            PrintStream printStream2 = System.err;
            printStream2.println("Rename of " + canonicalFile + " to " + normalize + " is a no-op.");
        } else if (!normalize.exists() || areSame(canonicalFile, normalize) || tryHardToDelete(normalize)) {
            File parentFile = normalize.getParentFile();
            if (parentFile != null && !parentFile.isDirectory() && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Failed to create directory " + parentFile + " while trying to rename " + canonicalFile);
            } else if (!canonicalFile.renameTo(normalize)) {
                copyFile(canonicalFile, normalize);
                if (!tryHardToDelete(canonicalFile)) {
                    throw new IOException("Failed to delete " + canonicalFile + " while trying to rename it.");
                }
            }
        } else {
            throw new IOException("Failed to delete " + normalize + " while trying to rename " + canonicalFile);
        }
    }

    public long getFileTimestampGranularity() {
        if (ON_WIN9X) {
            return 2000L;
        }
        if (ON_WINDOWS) {
            return 1L;
        }
        return ON_DOS ? 2000L : 1000L;
    }

    public boolean hasErrorInCase(File file) {
        File normalize = normalize(file.getAbsolutePath());
        if (!normalize.exists()) {
            return false;
        }
        final String name = normalize.getName();
        String[] list = normalize.getParentFile().list(new FilenameFilter() { // from class: org.apache.tools.ant.util.FileUtils.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.equalsIgnoreCase(name) && !str.equals(name);
            }
        });
        return list != null && list.length == 1;
    }

    public boolean isUpToDate(File file, File file2, long j) {
        if (!file2.exists()) {
            return false;
        }
        return isUpToDate(file.lastModified(), file2.lastModified(), j);
    }

    public boolean isUpToDate(File file, File file2) {
        return isUpToDate(file, file2, getFileTimestampGranularity());
    }

    public boolean isUpToDate(long j, long j2) {
        return isUpToDate(j, j2, getFileTimestampGranularity());
    }

    public static void close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(Channel channel) {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(URLConnection uRLConnection) {
        if (uRLConnection != null) {
            try {
                if (uRLConnection instanceof JarURLConnection) {
                    ((JarURLConnection) uRLConnection).getJarFile().close();
                } else if (uRLConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnection).disconnect();
                }
            } catch (IOException unused) {
            }
        }
    }

    public static void delete(File file) {
        if (file != null) {
            file.delete();
        }
    }

    public boolean tryHardToDelete(File file) {
        return tryHardToDelete(file, ON_WINDOWS);
    }

    public boolean tryHardToDelete(File file, boolean z) {
        if (file.delete()) {
            return true;
        }
        if (z) {
            System.gc();
        }
        try {
            Thread.sleep(10L);
        } catch (InterruptedException unused) {
        }
        return file.delete();
    }

    public static String getRelativePath(File file, File file2) throws Exception {
        String canonicalPath = file.getCanonicalPath();
        String canonicalPath2 = file2.getCanonicalPath();
        String[] pathStack = getPathStack(canonicalPath);
        String[] pathStack2 = getPathStack(canonicalPath2);
        if (pathStack2.length <= 0 || pathStack.length <= 0) {
            return getPath(Arrays.asList(pathStack2));
        }
        if (!pathStack[0].equals(pathStack2[0])) {
            return getPath(Arrays.asList(pathStack2));
        }
        int min = Math.min(pathStack.length, pathStack2.length);
        int i = 1;
        while (i < min && pathStack[i].equals(pathStack2[i])) {
            i++;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = i; i2 < pathStack.length; i2++) {
            arrayList.add("..");
        }
        while (i < pathStack2.length) {
            arrayList.add(pathStack2[i]);
            i++;
        }
        return getPath(arrayList);
    }

    public static String[] getPathStack(String str) {
        return str.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX).split("/");
    }

    public static String getPath(List list) {
        return getPath(list, IOUtils.DIR_SEPARATOR_UNIX);
    }

    public static String getPath(List list, char c) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = list.iterator();
        if (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        while (it.hasNext()) {
            stringBuffer.append(c);
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    public String getDefaultEncoding() {
        InputStreamReader inputStreamReader = new InputStreamReader(new InputStream() { // from class: org.apache.tools.ant.util.FileUtils.2
            @Override // java.io.InputStream
            public int read() {
                return -1;
            }
        });
        try {
            return inputStreamReader.getEncoding();
        } finally {
            close(inputStreamReader);
        }
    }
}
