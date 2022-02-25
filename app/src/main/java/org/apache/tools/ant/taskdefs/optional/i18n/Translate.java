package org.apache.tools.ant.taskdefs.optional.i18n;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.LineTokenizer;

/* loaded from: classes2.dex */
public class Translate extends MatchingTask {
    private static final int BUNDLE_DEFAULT_LANGUAGE = 6;
    private static final int BUNDLE_DEFAULT_LANGUAGE_COUNTRY = 5;
    private static final int BUNDLE_DEFAULT_LANGUAGE_COUNTRY_VARIANT = 4;
    private static final int BUNDLE_MAX_ALTERNATIVES = 7;
    private static final int BUNDLE_NOMATCH = 3;
    private static final int BUNDLE_SPECIFIED_LANGUAGE = 2;
    private static final int BUNDLE_SPECIFIED_LANGUAGE_COUNTRY = 1;
    private static final int BUNDLE_SPECIFIED_LANGUAGE_COUNTRY_VARIANT = 0;
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private String bundle;
    private String bundleCountry;
    private String bundleEncoding;
    private String bundleLanguage;
    private String bundleVariant;
    private String destEncoding;
    private long destLastModified;
    private String endToken;
    private boolean forceOverwrite;
    private String srcEncoding;
    private long srcLastModified;
    private String startToken;
    private File toDir;
    private Vector filesets = new Vector();
    private Hashtable resourceMap = new Hashtable();
    private long[] bundleLastModified = new long[7];
    private boolean loaded = false;

    public void setBundle(String str) {
        this.bundle = str;
    }

    public void setBundleLanguage(String str) {
        this.bundleLanguage = str;
    }

    public void setBundleCountry(String str) {
        this.bundleCountry = str;
    }

    public void setBundleVariant(String str) {
        this.bundleVariant = str;
    }

    public void setToDir(File file) {
        this.toDir = file;
    }

    public void setStartToken(String str) {
        this.startToken = str;
    }

    public void setEndToken(String str) {
        this.endToken = str;
    }

    public void setSrcEncoding(String str) {
        this.srcEncoding = str;
    }

    public void setDestEncoding(String str) {
        this.destEncoding = str;
    }

    public void setBundleEncoding(String str) {
        this.bundleEncoding = str;
    }

    public void setForceOverwrite(boolean z) {
        this.forceOverwrite = z;
    }

    public void addFileset(FileSet fileSet) {
        this.filesets.addElement(fileSet);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.bundle == null) {
            throw new BuildException("The bundle attribute must be set.", getLocation());
        } else if (this.startToken == null) {
            throw new BuildException("The starttoken attribute must be set.", getLocation());
        } else if (this.endToken != null) {
            if (this.bundleLanguage == null) {
                this.bundleLanguage = Locale.getDefault().getLanguage();
            }
            if (this.bundleCountry == null) {
                this.bundleCountry = Locale.getDefault().getCountry();
            }
            if (this.bundleVariant == null) {
                this.bundleVariant = new Locale(this.bundleLanguage, this.bundleCountry).getVariant();
            }
            File file = this.toDir;
            if (file != null) {
                if (!file.exists()) {
                    this.toDir.mkdirs();
                } else if (this.toDir.isFile()) {
                    throw new BuildException(this.toDir + " is not a directory");
                }
                if (this.srcEncoding == null) {
                    this.srcEncoding = System.getProperty("file.encoding");
                }
                if (this.destEncoding == null) {
                    this.destEncoding = this.srcEncoding;
                }
                if (this.bundleEncoding == null) {
                    this.bundleEncoding = this.srcEncoding;
                }
                loadResourceMaps();
                translate();
                return;
            }
            throw new BuildException("The todir attribute must be set.", getLocation());
        } else {
            throw new BuildException("The endtoken attribute must be set.", getLocation());
        }
    }

    private void loadResourceMaps() throws BuildException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Locale locale = new Locale(this.bundleLanguage, this.bundleCountry, this.bundleVariant);
        if (locale.getLanguage().length() > 0) {
            str = "_" + locale.getLanguage();
        } else {
            str = "";
        }
        if (locale.getCountry().length() > 0) {
            str2 = "_" + locale.getCountry();
        } else {
            str2 = "";
        }
        if (locale.getVariant().length() > 0) {
            str3 = "_" + locale.getVariant();
        } else {
            str3 = "";
        }
        processBundle(this.bundle + str + str2 + str3, 0, false);
        StringBuilder sb = new StringBuilder();
        sb.append(this.bundle);
        sb.append(str);
        sb.append(str2);
        processBundle(sb.toString(), 1, false);
        processBundle(this.bundle + str, 2, false);
        processBundle(this.bundle, 3, false);
        Locale locale2 = Locale.getDefault();
        if (locale2.getLanguage().length() > 0) {
            str4 = "_" + locale2.getLanguage();
        } else {
            str4 = "";
        }
        if (locale2.getCountry().length() > 0) {
            str5 = "_" + locale2.getCountry();
        } else {
            str5 = "";
        }
        if (locale2.getVariant().length() > 0) {
            str6 = "_" + locale2.getVariant();
        } else {
            str6 = "";
        }
        this.bundleEncoding = System.getProperty("file.encoding");
        processBundle(this.bundle + str4 + str5 + str6, 4, false);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.bundle);
        sb2.append(str4);
        sb2.append(str5);
        processBundle(sb2.toString(), 5, false);
        processBundle(this.bundle + str4, 6, true);
    }

    private void processBundle(String str, int i, boolean z) throws BuildException {
        Project project = getProject();
        File resolveFile = project.resolveFile(str + ".properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(resolveFile);
            this.loaded = true;
            this.bundleLastModified[i] = resolveFile.lastModified();
            log("Using " + resolveFile, 4);
            loadResourceMap(fileInputStream);
        } catch (IOException e) {
            log(resolveFile + " not found.", 4);
            if (!this.loaded && z) {
                throw new BuildException(e.getMessage(), getLocation());
            }
        }
    }

    private void loadResourceMap(FileInputStream fileInputStream) throws BuildException {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, this.bundleEncoding));
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 == null) {
                    bufferedReader.close();
                    return;
                } else if (!(readLine2.trim().length() <= 1 || '#' == readLine2.charAt(0) || '!' == readLine2.charAt(0))) {
                    int indexOf = readLine2.indexOf(61);
                    if (-1 == indexOf) {
                        indexOf = readLine2.indexOf(58);
                    }
                    if (-1 == indexOf) {
                        int i = 0;
                        while (true) {
                            if (i >= readLine2.length()) {
                                break;
                            } else if (Character.isSpaceChar(readLine2.charAt(i))) {
                                indexOf = i;
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                    if (-1 != indexOf) {
                        String trim = readLine2.substring(0, indexOf).trim();
                        String trim2 = readLine2.substring(indexOf + 1).trim();
                        while (trim2.endsWith("\\")) {
                            trim2 = trim2.substring(0, trim2.length() - 1);
                            if (bufferedReader.readLine() == null) {
                                break;
                            }
                            trim2 = trim2 + readLine.trim();
                        }
                        if (trim.length() > 0 && this.resourceMap.get(trim) == null) {
                            this.resourceMap.put(trim, trim2);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new BuildException(e.getMessage(), getLocation());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a4 A[Catch: IOException -> 0x00e1, TryCatch #0 {IOException -> 0x00e1, blocks: (B:8:0x0026, B:13:0x0044, B:14:0x005c, B:16:0x0079, B:26:0x008e, B:32:0x009e, B:35:0x00a4, B:36:0x00c0, B:9:0x0030, B:11:0x003f), top: B:44:0x0026, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c0 A[Catch: IOException -> 0x00e1, TRY_LEAVE, TryCatch #0 {IOException -> 0x00e1, blocks: (B:8:0x0026, B:13:0x0044, B:14:0x005c, B:16:0x0079, B:26:0x008e, B:32:0x009e, B:35:0x00a4, B:36:0x00c0, B:9:0x0030, B:11:0x003f), top: B:44:0x0026, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void translate() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.optional.i18n.Translate.translate():void");
    }

    private void translateOneFile(File file, File file2) throws IOException {
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        Throwable th;
        int i;
        String str;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), this.destEncoding));
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), this.srcEncoding));
                try {
                    LineTokenizer lineTokenizer = new LineTokenizer();
                    lineTokenizer.setIncludeDelims(true);
                    String token = lineTokenizer.getToken(bufferedReader);
                    while (token != null) {
                        int indexOf = token.indexOf(this.startToken);
                        while (indexOf >= 0 && this.startToken.length() + indexOf <= token.length()) {
                            int indexOf2 = token.indexOf(this.endToken, this.startToken.length() + indexOf);
                            if (indexOf2 < 0) {
                                i = indexOf + 1;
                            } else {
                                String substring = token.substring(this.startToken.length() + indexOf, indexOf2);
                                boolean z = true;
                                for (int i2 = 0; i2 < substring.length() && z; i2++) {
                                    char charAt = substring.charAt(i2);
                                    if (charAt == ':' || charAt == '=' || Character.isSpaceChar(charAt)) {
                                        z = false;
                                    }
                                }
                                if (!z) {
                                    i = indexOf + 1;
                                } else {
                                    if (this.resourceMap.containsKey(substring)) {
                                        str = (String) this.resourceMap.get(substring);
                                    } else {
                                        log("Replacement string missing for: " + substring, 3);
                                        str = this.startToken + substring + this.endToken;
                                    }
                                    token = token.substring(0, indexOf) + str + token.substring(indexOf2 + this.endToken.length());
                                    i = indexOf + str.length();
                                }
                            }
                            indexOf = token.indexOf(this.startToken, i);
                        }
                        bufferedWriter.write(token);
                        token = lineTokenizer.getToken(bufferedReader);
                    }
                    FileUtils.close(bufferedReader);
                    FileUtils.close(bufferedWriter);
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.close(bufferedReader);
                    FileUtils.close(bufferedWriter);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter = null;
            bufferedReader = null;
        }
    }
}
