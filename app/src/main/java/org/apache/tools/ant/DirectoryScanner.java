package org.apache.tools.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceFactory;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.ant.types.selectors.SelectorScanner;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.types.selectors.TokenizedPath;
import org.apache.tools.ant.types.selectors.TokenizedPattern;
import org.apache.tools.ant.util.CollectionUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.apache.tools.ant.util.VectorSet;

/* loaded from: classes2.dex */
public class DirectoryScanner implements FileScanner, ResourceFactory, SelectorScanner {
    public static final String DOES_NOT_EXIST_POSTFIX = " does not exist.";
    public static final int MAX_LEVELS_OF_SYMLINKS = 5;
    protected File basedir;
    protected Vector<String> dirsDeselected;
    protected Vector<String> dirsExcluded;
    protected Vector<String> dirsIncluded;
    protected Vector<String> dirsNotIncluded;
    private TokenizedPattern[] excludePatterns;
    protected String[] excludes;
    protected Vector<String> filesDeselected;
    protected Vector<String> filesExcluded;
    protected Vector<String> filesIncluded;
    protected Vector<String> filesNotIncluded;
    private TokenizedPattern[] includePatterns;
    protected String[] includes;
    private static final boolean ON_VMS = C3209Os.isFamily(C3209Os.FAMILY_VMS);
    @Deprecated
    protected static final String[] DEFAULTEXCLUDES = {"**/*~", "**/#*#", "**/.#*", "**/%*%", "**/._*", "**/CVS", "**/CVS/**", "**/.cvsignore", "**/SCCS", "**/SCCS/**", "**/vssver.scc", "**/.svn", "**/.svn/**", "**/.git", "**/.git/**", "**/.gitattributes", "**/.gitignore", "**/.gitmodules", "**/.hg", "**/.hg/**", "**/.hgignore", "**/.hgsub", "**/.hgsubstate", "**/.hgtags", "**/.bzr", "**/.bzr/**", "**/.bzrignore", "**/.DS_Store"};
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final SymbolicLinkUtils SYMLINK_UTILS = SymbolicLinkUtils.getSymbolicLinkUtils();
    private static final Set<String> defaultExcludes = new HashSet();
    protected FileSelector[] selectors = null;
    protected boolean haveSlowResults = false;
    protected boolean isCaseSensitive = true;
    protected boolean errorOnMissingDir = true;
    private boolean followSymlinks = true;
    protected boolean everythingIncluded = true;
    private final Set<String> scannedDirs = new HashSet();
    private final Map<String, TokenizedPath> includeNonPatterns = new HashMap();
    private final Map<String, TokenizedPath> excludeNonPatterns = new HashMap();
    private boolean areNonPatternSetsReady = false;
    private boolean scanning = false;
    private final Object scanLock = new Object();
    private boolean slowScanning = false;
    private final Object slowScanLock = new Object();
    private IllegalStateException illegal = null;
    private int maxLevelsOfSymlinks = 5;
    private final Set<String> notFollowedSymlinks = new HashSet();

    static {
        resetDefaultExcludes();
    }

    protected static boolean matchPatternStart(String str, String str2) {
        return SelectorUtils.matchPatternStart(str, str2);
    }

    protected static boolean matchPatternStart(String str, String str2, boolean z) {
        return SelectorUtils.matchPatternStart(str, str2, z);
    }

    protected static boolean matchPath(String str, String str2) {
        return SelectorUtils.matchPath(str, str2);
    }

    protected static boolean matchPath(String str, String str2, boolean z) {
        return SelectorUtils.matchPath(str, str2, z);
    }

    public static boolean match(String str, String str2) {
        return SelectorUtils.match(str, str2);
    }

    protected static boolean match(String str, String str2, boolean z) {
        return SelectorUtils.match(str, str2, z);
    }

    public static String[] getDefaultExcludes() {
        String[] strArr;
        synchronized (defaultExcludes) {
            strArr = (String[]) defaultExcludes.toArray(new String[defaultExcludes.size()]);
        }
        return strArr;
    }

    public static boolean addDefaultExclude(String str) {
        boolean add;
        synchronized (defaultExcludes) {
            add = defaultExcludes.add(str);
        }
        return add;
    }

    public static boolean removeDefaultExclude(String str) {
        boolean remove;
        synchronized (defaultExcludes) {
            remove = defaultExcludes.remove(str);
        }
        return remove;
    }

    public static void resetDefaultExcludes() {
        synchronized (defaultExcludes) {
            defaultExcludes.clear();
            for (int i = 0; i < DEFAULTEXCLUDES.length; i++) {
                defaultExcludes.add(DEFAULTEXCLUDES[i]);
            }
        }
    }

    @Override // org.apache.tools.ant.FileScanner
    public void setBasedir(String str) {
        setBasedir(str == null ? null : new File(str.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar)));
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setBasedir(File file) {
        this.basedir = file;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized File getBasedir() {
        return this.basedir;
    }

    public synchronized boolean isCaseSensitive() {
        return this.isCaseSensitive;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setCaseSensitive(boolean z) {
        this.isCaseSensitive = z;
    }

    public void setErrorOnMissingDir(boolean z) {
        this.errorOnMissingDir = z;
    }

    public synchronized boolean isFollowSymlinks() {
        return this.followSymlinks;
    }

    public synchronized void setFollowSymlinks(boolean z) {
        this.followSymlinks = z;
    }

    public void setMaxLevelsOfSymlinks(int i) {
        this.maxLevelsOfSymlinks = i;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setIncludes(String[] strArr) {
        if (strArr == null) {
            this.includes = null;
        } else {
            this.includes = new String[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                this.includes[i] = normalizePattern(strArr[i]);
            }
        }
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setExcludes(String[] strArr) {
        if (strArr == null) {
            this.excludes = null;
        } else {
            this.excludes = new String[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                this.excludes[i] = normalizePattern(strArr[i]);
            }
        }
    }

    public synchronized void addExcludes(String[] strArr) {
        if (strArr != null) {
            if (strArr.length > 0) {
                if (this.excludes == null || this.excludes.length <= 0) {
                    setExcludes(strArr);
                } else {
                    String[] strArr2 = new String[strArr.length + this.excludes.length];
                    System.arraycopy(this.excludes, 0, strArr2, 0, this.excludes.length);
                    for (int i = 0; i < strArr.length; i++) {
                        strArr2[this.excludes.length + i] = normalizePattern(strArr[i]);
                    }
                    this.excludes = strArr2;
                }
            }
        }
    }

    private static String normalizePattern(String str) {
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
        if (!replace.endsWith(File.separator)) {
            return replace;
        }
        return replace + SelectorUtils.DEEP_TREE_MATCH;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorScanner
    public synchronized void setSelectors(FileSelector[] fileSelectorArr) {
        this.selectors = fileSelectorArr;
    }

    public synchronized boolean isEverythingIncluded() {
        return this.everythingIncluded;
    }

    @Override // org.apache.tools.ant.FileScanner
    public void scan() throws IllegalStateException {
        synchronized (this.scanLock) {
            if (this.scanning) {
                while (this.scanning) {
                    try {
                        this.scanLock.wait();
                    } catch (InterruptedException unused) {
                    }
                }
                if (this.illegal != null) {
                    throw this.illegal;
                }
                return;
            }
            boolean z = true;
            this.scanning = true;
            File file = this.basedir;
            try {
                try {
                    synchronized (this) {
                        String[] strArr = null;
                        this.illegal = null;
                        clearResults();
                        boolean z2 = this.includes == null;
                        this.includes = z2 ? new String[]{SelectorUtils.DEEP_TREE_MATCH} : this.includes;
                        if (this.excludes != null) {
                            z = false;
                        }
                        this.excludes = z ? new String[0] : this.excludes;
                        if (this.basedir != null && !this.followSymlinks && SYMLINK_UTILS.isSymbolicLink(this.basedir)) {
                            this.notFollowedSymlinks.add(this.basedir.getAbsolutePath());
                            this.basedir = null;
                        }
                        if (this.basedir != null) {
                            if (!this.basedir.exists()) {
                                if (this.errorOnMissingDir) {
                                    this.illegal = new IllegalStateException("basedir " + this.basedir + DOES_NOT_EXIST_POSTFIX);
                                } else {
                                    this.basedir = file;
                                    synchronized (this.scanLock) {
                                        this.scanning = false;
                                        this.scanLock.notifyAll();
                                    }
                                    return;
                                }
                            } else if (!this.basedir.isDirectory()) {
                                this.illegal = new IllegalStateException("basedir " + this.basedir + " is not a directory.");
                            }
                            if (this.illegal != null) {
                                throw this.illegal;
                            }
                        } else if (z2) {
                            this.basedir = file;
                            synchronized (this.scanLock) {
                                this.scanning = false;
                                this.scanLock.notifyAll();
                            }
                            return;
                        }
                        if (!isIncluded(TokenizedPath.EMPTY_PATH)) {
                            this.dirsNotIncluded.addElement("");
                        } else if (isExcluded(TokenizedPath.EMPTY_PATH)) {
                            this.dirsExcluded.addElement("");
                        } else if (isSelected("", this.basedir)) {
                            this.dirsIncluded.addElement("");
                        } else {
                            this.dirsDeselected.addElement("");
                        }
                        checkIncludePatterns();
                        clearCaches();
                        this.includes = z2 ? null : this.includes;
                        if (!z) {
                            strArr = this.excludes;
                        }
                        this.excludes = strArr;
                        this.basedir = file;
                        synchronized (this.scanLock) {
                            this.scanning = false;
                            this.scanLock.notifyAll();
                        }
                    }
                } catch (IOException e) {
                    throw new BuildException(e);
                }
            } catch (Throwable th) {
                this.basedir = file;
                synchronized (this.scanLock) {
                    this.scanning = false;
                    this.scanLock.notifyAll();
                    throw th;
                }
            }
        }
    }

    private void checkIncludePatterns() {
        File findFile;
        File file;
        ensureNonPatternSetsReady();
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            TokenizedPattern[] tokenizedPatternArr = this.includePatterns;
            if (i >= tokenizedPatternArr.length) {
                break;
            }
            String tokenizedPattern = tokenizedPatternArr[i].toString();
            if (!shouldSkipPattern(tokenizedPattern)) {
                hashMap.put(this.includePatterns[i].rtrimWildcardTokens(), tokenizedPattern);
            }
            i++;
        }
        for (Map.Entry<String, TokenizedPath> entry : this.includeNonPatterns.entrySet()) {
            String key = entry.getKey();
            if (!shouldSkipPattern(key)) {
                hashMap.put(entry.getValue(), key);
            }
        }
        if (!hashMap.containsKey(TokenizedPath.EMPTY_PATH) || (file = this.basedir) == null) {
            File file2 = null;
            File file3 = this.basedir;
            if (file3 != null) {
                try {
                    file2 = file3.getCanonicalFile();
                } catch (IOException e) {
                    throw new BuildException(e);
                }
            }
            for (Map.Entry entry2 : hashMap.entrySet()) {
                TokenizedPath tokenizedPath = (TokenizedPath) entry2.getKey();
                String tokenizedPath2 = tokenizedPath.toString();
                if (this.basedir != null || FileUtils.isAbsolutePath(tokenizedPath2)) {
                    File file4 = new File(this.basedir, tokenizedPath2);
                    if (file4.exists()) {
                        try {
                            if (!(((this.basedir == null ? file4.getCanonicalPath() : FILE_UTILS.removeLeadingPath(file2, file4.getCanonicalFile())).equals(tokenizedPath2) && !ON_VMS) || (file4 = tokenizedPath.findFile(this.basedir, true)) == null || this.basedir == null)) {
                                tokenizedPath2 = FILE_UTILS.removeLeadingPath(this.basedir, file4);
                                if (!tokenizedPath.toString().equals(tokenizedPath2)) {
                                    tokenizedPath = new TokenizedPath(tokenizedPath2);
                                }
                            }
                        } catch (IOException e2) {
                            throw new BuildException(e2);
                        }
                    }
                    if ((file4 == null || !file4.exists()) && !isCaseSensitive() && (findFile = tokenizedPath.findFile(this.basedir, false)) != null && findFile.exists()) {
                        File file5 = this.basedir;
                        tokenizedPath2 = file5 == null ? findFile.getAbsolutePath() : FILE_UTILS.removeLeadingPath(file5, findFile);
                        tokenizedPath = new TokenizedPath(tokenizedPath2);
                        file4 = findFile;
                    }
                    if (file4 != null && file4.exists()) {
                        if (!this.followSymlinks && tokenizedPath.isSymlink(this.basedir)) {
                            accountForNotFollowedSymlink(tokenizedPath, file4);
                        } else if (file4.isDirectory()) {
                            if (!isIncluded(tokenizedPath) || tokenizedPath2.length() <= 0) {
                                scandir(file4, tokenizedPath, true);
                            } else {
                                accountForIncludedDir(tokenizedPath, file4, true);
                            }
                        } else if (file4.isFile()) {
                            String str = (String) entry2.getValue();
                            if (isCaseSensitive() ? str.equals(tokenizedPath2) : str.equalsIgnoreCase(tokenizedPath2)) {
                                accountForIncludedFile(tokenizedPath, file4);
                            }
                        }
                    }
                }
            }
            return;
        }
        scandir(file, "", true);
    }

    private boolean shouldSkipPattern(String str) {
        if (!FileUtils.isAbsolutePath(str)) {
            return this.basedir == null;
        }
        File file = this.basedir;
        return file != null && !SelectorUtils.matchPatternStart(str, file.getAbsolutePath(), isCaseSensitive());
    }

    protected synchronized void clearResults() {
        this.filesIncluded = new VectorSet();
        this.filesNotIncluded = new VectorSet();
        this.filesExcluded = new VectorSet();
        this.filesDeselected = new VectorSet();
        this.dirsIncluded = new VectorSet();
        this.dirsNotIncluded = new VectorSet();
        this.dirsExcluded = new VectorSet();
        this.dirsDeselected = new VectorSet();
        this.everythingIncluded = this.basedir != null;
        this.scannedDirs.clear();
        this.notFollowedSymlinks.clear();
    }

    protected void slowScan() {
        synchronized (this.slowScanLock) {
            if (!this.haveSlowResults) {
                if (this.slowScanning) {
                    while (this.slowScanning) {
                        try {
                            this.slowScanLock.wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                    return;
                }
                this.slowScanning = true;
                try {
                    synchronized (this) {
                        boolean z = this.includes == null;
                        this.includes = z ? new String[]{SelectorUtils.DEEP_TREE_MATCH} : this.includes;
                        boolean z2 = this.excludes == null;
                        this.excludes = z2 ? new String[0] : this.excludes;
                        String[] strArr = new String[this.dirsExcluded.size()];
                        this.dirsExcluded.copyInto(strArr);
                        String[] strArr2 = new String[this.dirsNotIncluded.size()];
                        this.dirsNotIncluded.copyInto(strArr2);
                        ensureNonPatternSetsReady();
                        processSlowScan(strArr);
                        processSlowScan(strArr2);
                        clearCaches();
                        String[] strArr3 = null;
                        this.includes = z ? null : this.includes;
                        if (!z2) {
                            strArr3 = this.excludes;
                        }
                        this.excludes = strArr3;
                    }
                    synchronized (this.slowScanLock) {
                        this.haveSlowResults = true;
                        this.slowScanning = false;
                        this.slowScanLock.notifyAll();
                    }
                } catch (Throwable th) {
                    synchronized (this.slowScanLock) {
                        this.haveSlowResults = true;
                        this.slowScanning = false;
                        this.slowScanLock.notifyAll();
                        throw th;
                    }
                }
            }
        }
    }

    private void processSlowScan(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            TokenizedPath tokenizedPath = new TokenizedPath(strArr[i]);
            if (!couldHoldIncluded(tokenizedPath) || contentsExcluded(tokenizedPath)) {
                scandir(new File(this.basedir, strArr[i]), tokenizedPath, false);
            }
        }
    }

    protected void scandir(File file, String str, boolean z) {
        scandir(file, new TokenizedPath(str), z);
    }

    private void scandir(File file, TokenizedPath tokenizedPath, boolean z) {
        if (file != null) {
            String[] list = file.list();
            if (list != null) {
                scandir(file, tokenizedPath, z, list, new LinkedList<>());
            } else if (!file.exists()) {
                throw new BuildException(file + DOES_NOT_EXIST_POSTFIX);
            } else if (!file.isDirectory()) {
                throw new BuildException(file + " is not a directory.");
            } else {
                throw new BuildException("IO error scanning directory '" + file.getAbsolutePath() + "'");
            }
        } else {
            throw new BuildException("dir must not be null.");
        }
    }

    private void scandir(File file, TokenizedPath tokenizedPath, boolean z, String[] strArr, LinkedList<String> linkedList) {
        String[] strArr2;
        String[] strArr3;
        String tokenizedPath2 = tokenizedPath.toString();
        String str = (tokenizedPath2.length() <= 0 || tokenizedPath2.endsWith(File.separator)) ? tokenizedPath2 : tokenizedPath2 + File.separator;
        if (!z || !hasBeenScanned(str)) {
            if (!this.followSymlinks) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < strArr.length; i++) {
                    try {
                        if (SYMLINK_UTILS.isSymbolicLink(file, strArr[i])) {
                            String str2 = str + strArr[i];
                            File file2 = new File(file, strArr[i]);
                            if (file2.isDirectory()) {
                                this.dirsExcluded.addElement(str2);
                            } else if (file2.isFile()) {
                                this.filesExcluded.addElement(str2);
                            }
                            accountForNotFollowedSymlink(str2, file2);
                        } else {
                            arrayList.add(strArr[i]);
                        }
                    } catch (IOException unused) {
                        System.err.println("IOException caught while checking for links, couldn't get canonical path!");
                        arrayList.add(strArr[i]);
                    }
                }
                strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
            } else {
                linkedList.addFirst(file.getName());
                strArr2 = strArr;
            }
            for (int i2 = 0; i2 < strArr2.length; i2++) {
                String str3 = str + strArr2[i2];
                TokenizedPath tokenizedPath3 = new TokenizedPath(tokenizedPath, strArr2[i2]);
                File file3 = new File(file, strArr2[i2]);
                String[] list = file3.list();
                if (list == null || (list.length == 0 && file3.isFile())) {
                    if (isIncluded(tokenizedPath3)) {
                        accountForIncludedFile(tokenizedPath3, file3);
                    } else {
                        this.everythingIncluded = false;
                        this.filesNotIncluded.addElement(str3);
                    }
                } else if (file3.isDirectory()) {
                    if (!this.followSymlinks || !causesIllegalSymlinkLoop(strArr2[i2], file, linkedList)) {
                        if (isIncluded(tokenizedPath3)) {
                            strArr3 = list;
                            accountForIncludedDir(tokenizedPath3, file3, z, list, linkedList);
                        } else {
                            strArr3 = list;
                            this.everythingIncluded = false;
                            this.dirsNotIncluded.addElement(str3);
                            if (z && couldHoldIncluded(tokenizedPath3) && !contentsExcluded(tokenizedPath3)) {
                                scandir(file3, tokenizedPath3, z, strArr3, linkedList);
                            }
                        }
                        if (!z) {
                            scandir(file3, tokenizedPath3, z, strArr3, linkedList);
                        }
                    } else {
                        System.err.println("skipping symbolic link " + file3.getAbsolutePath() + " -- too many levels of symbolic links.");
                        this.notFollowedSymlinks.add(file3.getAbsolutePath());
                    }
                }
            }
            if (this.followSymlinks) {
                linkedList.removeFirst();
            }
        }
    }

    private void accountForIncludedFile(TokenizedPath tokenizedPath, File file) {
        processIncluded(tokenizedPath, file, this.filesIncluded, this.filesExcluded, this.filesDeselected);
    }

    private void accountForIncludedDir(TokenizedPath tokenizedPath, File file, boolean z) {
        processIncluded(tokenizedPath, file, this.dirsIncluded, this.dirsExcluded, this.dirsDeselected);
        if (z && couldHoldIncluded(tokenizedPath) && !contentsExcluded(tokenizedPath)) {
            scandir(file, tokenizedPath, z);
        }
    }

    private void accountForIncludedDir(TokenizedPath tokenizedPath, File file, boolean z, String[] strArr, LinkedList<String> linkedList) {
        processIncluded(tokenizedPath, file, this.dirsIncluded, this.dirsExcluded, this.dirsDeselected);
        if (z && couldHoldIncluded(tokenizedPath) && !contentsExcluded(tokenizedPath)) {
            scandir(file, tokenizedPath, z, strArr, linkedList);
        }
    }

    private void accountForNotFollowedSymlink(String str, File file) {
        accountForNotFollowedSymlink(new TokenizedPath(str), file);
    }

    private void accountForNotFollowedSymlink(TokenizedPath tokenizedPath, File file) {
        if (isExcluded(tokenizedPath)) {
            return;
        }
        if (isIncluded(tokenizedPath) || (file.isDirectory() && couldHoldIncluded(tokenizedPath) && !contentsExcluded(tokenizedPath))) {
            this.notFollowedSymlinks.add(file.getAbsolutePath());
        }
    }

    private void processIncluded(TokenizedPath tokenizedPath, File file, Vector<String> vector, Vector<String> vector2, Vector<String> vector3) {
        String tokenizedPath2 = tokenizedPath.toString();
        if (!vector.contains(tokenizedPath2) && !vector2.contains(tokenizedPath2) && !vector3.contains(tokenizedPath2)) {
            boolean z = false;
            if (isExcluded(tokenizedPath)) {
                vector2.add(tokenizedPath2);
            } else if (isSelected(tokenizedPath2, file)) {
                z = true;
                vector.add(tokenizedPath2);
            } else {
                vector3.add(tokenizedPath2);
            }
            this.everythingIncluded &= z;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isIncluded(String str) {
        return isIncluded(new TokenizedPath(str));
    }

    private boolean isIncluded(TokenizedPath tokenizedPath) {
        ensureNonPatternSetsReady();
        if (!isCaseSensitive() ? this.includeNonPatterns.containsKey(tokenizedPath.toString().toUpperCase()) : this.includeNonPatterns.containsKey(tokenizedPath.toString())) {
            return true;
        }
        int i = 0;
        while (true) {
            TokenizedPattern[] tokenizedPatternArr = this.includePatterns;
            if (i >= tokenizedPatternArr.length) {
                return false;
            }
            if (tokenizedPatternArr[i].matchPath(tokenizedPath, isCaseSensitive())) {
                return true;
            }
            i++;
        }
    }

    protected boolean couldHoldIncluded(String str) {
        return couldHoldIncluded(new TokenizedPath(str));
    }

    private boolean couldHoldIncluded(TokenizedPath tokenizedPath) {
        int i = 0;
        while (true) {
            TokenizedPattern[] tokenizedPatternArr = this.includePatterns;
            if (i >= tokenizedPatternArr.length) {
                for (TokenizedPath tokenizedPath2 : this.includeNonPatterns.values()) {
                    if (couldHoldIncluded(tokenizedPath, tokenizedPath2.toPattern())) {
                        return true;
                    }
                }
                return false;
            } else if (couldHoldIncluded(tokenizedPath, tokenizedPatternArr[i])) {
                return true;
            } else {
                i++;
            }
        }
    }

    private boolean couldHoldIncluded(TokenizedPath tokenizedPath, TokenizedPattern tokenizedPattern) {
        return tokenizedPattern.matchStartOf(tokenizedPath, isCaseSensitive()) && isMorePowerfulThanExcludes(tokenizedPath.toString()) && isDeeper(tokenizedPattern, tokenizedPath);
    }

    private boolean isDeeper(TokenizedPattern tokenizedPattern, TokenizedPath tokenizedPath) {
        return tokenizedPattern.containsPattern(SelectorUtils.DEEP_TREE_MATCH) || tokenizedPattern.depth() > tokenizedPath.depth();
    }

    private boolean isMorePowerfulThanExcludes(String str) {
        String str2 = str + File.separatorChar + SelectorUtils.DEEP_TREE_MATCH;
        int i = 0;
        while (true) {
            TokenizedPattern[] tokenizedPatternArr = this.excludePatterns;
            if (i >= tokenizedPatternArr.length) {
                return true;
            }
            if (tokenizedPatternArr[i].toString().equals(str2)) {
                return false;
            }
            i++;
        }
    }

    boolean contentsExcluded(TokenizedPath tokenizedPath) {
        int i = 0;
        while (true) {
            TokenizedPattern[] tokenizedPatternArr = this.excludePatterns;
            if (i >= tokenizedPatternArr.length) {
                return false;
            }
            if (tokenizedPatternArr[i].endsWith(SelectorUtils.DEEP_TREE_MATCH) && this.excludePatterns[i].withoutLastToken().matchPath(tokenizedPath, isCaseSensitive())) {
                return true;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isExcluded(String str) {
        return isExcluded(new TokenizedPath(str));
    }

    private boolean isExcluded(TokenizedPath tokenizedPath) {
        ensureNonPatternSetsReady();
        if (!isCaseSensitive() ? this.excludeNonPatterns.containsKey(tokenizedPath.toString().toUpperCase()) : this.excludeNonPatterns.containsKey(tokenizedPath.toString())) {
            return true;
        }
        int i = 0;
        while (true) {
            TokenizedPattern[] tokenizedPatternArr = this.excludePatterns;
            if (i >= tokenizedPatternArr.length) {
                return false;
            }
            if (tokenizedPatternArr[i].matchPath(tokenizedPath, isCaseSensitive())) {
                return true;
            }
            i++;
        }
    }

    protected boolean isSelected(String str, File file) {
        if (this.selectors == null) {
            return true;
        }
        int i = 0;
        while (true) {
            FileSelector[] fileSelectorArr = this.selectors;
            if (i >= fileSelectorArr.length) {
                return true;
            }
            if (!fileSelectorArr[i].isSelected(this.basedir, str, file)) {
                return false;
            }
            i++;
        }
    }

    @Override // org.apache.tools.ant.FileScanner
    public String[] getIncludedFiles() {
        String[] strArr;
        synchronized (this) {
            if (this.filesIncluded != null) {
                strArr = new String[this.filesIncluded.size()];
                this.filesIncluded.copyInto(strArr);
            } else {
                throw new IllegalStateException("Must call scan() first");
            }
        }
        Arrays.sort(strArr);
        return strArr;
    }

    public synchronized int getIncludedFilesCount() {
        if (this.filesIncluded != null) {
        } else {
            throw new IllegalStateException("Must call scan() first");
        }
        return this.filesIncluded.size();
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getNotIncludedFiles() {
        String[] strArr;
        slowScan();
        strArr = new String[this.filesNotIncluded.size()];
        this.filesNotIncluded.copyInto(strArr);
        return strArr;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getExcludedFiles() {
        String[] strArr;
        slowScan();
        strArr = new String[this.filesExcluded.size()];
        this.filesExcluded.copyInto(strArr);
        return strArr;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorScanner
    public synchronized String[] getDeselectedFiles() {
        String[] strArr;
        slowScan();
        strArr = new String[this.filesDeselected.size()];
        this.filesDeselected.copyInto(strArr);
        return strArr;
    }

    @Override // org.apache.tools.ant.FileScanner
    public String[] getIncludedDirectories() {
        String[] strArr;
        synchronized (this) {
            if (this.dirsIncluded != null) {
                strArr = new String[this.dirsIncluded.size()];
                this.dirsIncluded.copyInto(strArr);
            } else {
                throw new IllegalStateException("Must call scan() first");
            }
        }
        Arrays.sort(strArr);
        return strArr;
    }

    public synchronized int getIncludedDirsCount() {
        if (this.dirsIncluded != null) {
        } else {
            throw new IllegalStateException("Must call scan() first");
        }
        return this.dirsIncluded.size();
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getNotIncludedDirectories() {
        String[] strArr;
        slowScan();
        strArr = new String[this.dirsNotIncluded.size()];
        this.dirsNotIncluded.copyInto(strArr);
        return strArr;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getExcludedDirectories() {
        String[] strArr;
        slowScan();
        strArr = new String[this.dirsExcluded.size()];
        this.dirsExcluded.copyInto(strArr);
        return strArr;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorScanner
    public synchronized String[] getDeselectedDirectories() {
        String[] strArr;
        slowScan();
        strArr = new String[this.dirsDeselected.size()];
        this.dirsDeselected.copyInto(strArr);
        return strArr;
    }

    public synchronized String[] getNotFollowedSymlinks() {
        String[] strArr;
        synchronized (this) {
            strArr = (String[]) this.notFollowedSymlinks.toArray(new String[this.notFollowedSymlinks.size()]);
        }
        return strArr;
        Arrays.sort(strArr);
        return strArr;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void addDefaultExcludes() {
        int length = this.excludes == null ? 0 : this.excludes.length;
        String[] defaultExcludes2 = getDefaultExcludes();
        String[] strArr = new String[defaultExcludes2.length + length];
        if (length > 0) {
            System.arraycopy(this.excludes, 0, strArr, 0, length);
        }
        for (int i = 0; i < defaultExcludes2.length; i++) {
            strArr[i + length] = defaultExcludes2[i].replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
        }
        this.excludes = strArr;
    }

    @Override // org.apache.tools.ant.types.ResourceFactory
    public synchronized Resource getResource(String str) {
        return new FileResource(this.basedir, str);
    }

    private boolean hasBeenScanned(String str) {
        return !this.scannedDirs.add(str);
    }

    Set<String> getScannedDirs() {
        return this.scannedDirs;
    }

    private synchronized void clearCaches() {
        this.includeNonPatterns.clear();
        this.excludeNonPatterns.clear();
        this.includePatterns = null;
        this.excludePatterns = null;
        this.areNonPatternSetsReady = false;
    }

    synchronized void ensureNonPatternSetsReady() {
        if (!this.areNonPatternSetsReady) {
            this.includePatterns = fillNonPatternSet(this.includeNonPatterns, this.includes);
            this.excludePatterns = fillNonPatternSet(this.excludeNonPatterns, this.excludes);
            this.areNonPatternSetsReady = true;
        }
    }

    private TokenizedPattern[] fillNonPatternSet(Map<String, TokenizedPath> map, String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            if (!SelectorUtils.hasWildcards(strArr[i])) {
                String upperCase = isCaseSensitive() ? strArr[i] : strArr[i].toUpperCase();
                map.put(upperCase, new TokenizedPath(upperCase));
            } else {
                arrayList.add(new TokenizedPattern(strArr[i]));
            }
        }
        return (TokenizedPattern[]) arrayList.toArray(new TokenizedPattern[arrayList.size()]);
    }

    private boolean causesIllegalSymlinkLoop(String str, File file, LinkedList<String> linkedList) {
        try {
            if (linkedList.size() < this.maxLevelsOfSymlinks || CollectionUtils.frequency(linkedList, str) < this.maxLevelsOfSymlinks || !SYMLINK_UTILS.isSymbolicLink(file, str)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            String canonicalPath = FILE_UTILS.resolveFile(file, str).getCanonicalPath();
            arrayList.add(canonicalPath);
            String str2 = "";
            Iterator<String> it = linkedList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                str2 = str2 + "../";
                if (str.equals(next)) {
                    arrayList.add(FILE_UTILS.resolveFile(file, str2 + next).getCanonicalPath());
                    if (arrayList.size() > this.maxLevelsOfSymlinks && CollectionUtils.frequency(arrayList, canonicalPath) > this.maxLevelsOfSymlinks) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            throw new BuildException("Caught error while checking for symbolic links", e);
        }
    }
}
