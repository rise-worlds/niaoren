package org.apache.tools.ant.taskdefs;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.Restrict;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.resources.selectors.Type;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class Checksum extends MatchingTask implements Condition {
    private static final int BUFFER_SIZE = 8192;
    private static final int BYTE_MASK = 255;
    private static final int NIBBLE = 4;
    private static final int WORD = 16;
    private String fileext;
    private boolean forceOverwrite;
    private boolean isCondition;
    private MessageDigest messageDigest;
    private String property;
    private File todir;
    private String totalproperty;
    private String verifyProperty;
    private File file = null;
    private String algorithm = "MD5";
    private String provider = null;
    private Map<File, byte[]> allDigests = new HashMap();
    private Map<File, String> relativeFilePaths = new HashMap();
    private FileUnion resources = null;
    private Hashtable<File, Object> includeFileMap = new Hashtable<>();
    private int readBufferSize = 8192;
    private MessageFormat format = FormatElement.getDefault().getFormat();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FileUnion extends Restrict {

        /* renamed from: u */
        private Union f14744u = new Union();

        FileUnion() {
            super.add(this.f14744u);
            super.add(Type.FILE);
        }

        @Override // org.apache.tools.ant.types.resources.Restrict
        public void add(ResourceCollection resourceCollection) {
            this.f14744u.add(resourceCollection);
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setTodir(File file) {
        this.todir = file;
    }

    public void setAlgorithm(String str) {
        this.algorithm = str;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setFileext(String str) {
        this.fileext = str;
    }

    public void setProperty(String str) {
        this.property = str;
    }

    public void setTotalproperty(String str) {
        this.totalproperty = str;
    }

    public void setVerifyproperty(String str) {
        this.verifyProperty = str;
    }

    public void setForceOverwrite(boolean z) {
        this.forceOverwrite = z;
    }

    public void setReadBufferSize(int i) {
        this.readBufferSize = i;
    }

    public void setFormat(FormatElement formatElement) {
        this.format = formatElement.getFormat();
    }

    public void setPattern(String str) {
        this.format = new MessageFormat(str);
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void add(ResourceCollection resourceCollection) {
        if (resourceCollection != null) {
            FileUnion fileUnion = this.resources;
            if (fileUnion == null) {
                fileUnion = new FileUnion();
            }
            this.resources = fileUnion;
            this.resources.add(resourceCollection);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        this.isCondition = false;
        boolean validateAndExecute = validateAndExecute();
        if (this.verifyProperty != null) {
            getProject().setNewProperty(this.verifyProperty, (validateAndExecute ? Boolean.TRUE : Boolean.FALSE).toString());
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        this.isCondition = true;
        return validateAndExecute();
    }

    private boolean validateAndExecute() throws BuildException {
        FileUnion fileUnion;
        String str = this.fileext;
        if (this.file == null && ((fileUnion = this.resources) == null || fileUnion.size() == 0)) {
            throw new BuildException("Specify at least one source - a file or a resource collection.");
        }
        FileUnion fileUnion2 = this.resources;
        if (fileUnion2 == null || fileUnion2.isFilesystemOnly()) {
            File file = this.file;
            if (file != null && file.exists() && this.file.isDirectory()) {
                throw new BuildException("Checksum cannot be generated for directories");
            } else if (this.file != null && this.totalproperty != null) {
                throw new BuildException("File and Totalproperty cannot co-exist.");
            } else if (this.property == null || this.fileext == null) {
                if (this.property != null) {
                    if (!this.forceOverwrite) {
                        int i = 0;
                        FileUnion fileUnion3 = this.resources;
                        if (fileUnion3 != null) {
                            i = 0 + fileUnion3.size();
                        }
                        if (this.file != null) {
                            i++;
                        }
                        if (i > 1) {
                            throw new BuildException("Multiple files cannot be used when Property is specified");
                        }
                    } else {
                        throw new BuildException("ForceOverwrite cannot be used when Property is specified");
                    }
                }
                if (this.verifyProperty != null) {
                    this.isCondition = true;
                }
                if (this.verifyProperty != null && this.forceOverwrite) {
                    throw new BuildException("VerifyProperty and ForceOverwrite cannot co-exist.");
                } else if (!this.isCondition || !this.forceOverwrite) {
                    this.messageDigest = null;
                    String str2 = this.provider;
                    if (str2 != null) {
                        try {
                            this.messageDigest = MessageDigest.getInstance(this.algorithm, str2);
                        } catch (NoSuchAlgorithmException e) {
                            throw new BuildException(e, getLocation());
                        } catch (NoSuchProviderException e2) {
                            throw new BuildException(e2, getLocation());
                        }
                    } else {
                        try {
                            this.messageDigest = MessageDigest.getInstance(this.algorithm);
                        } catch (NoSuchAlgorithmException e3) {
                            throw new BuildException(e3, getLocation());
                        }
                    }
                    if (this.messageDigest != null) {
                        String str3 = this.fileext;
                        if (str3 == null) {
                            this.fileext = Consts.f23430h + this.algorithm;
                        } else if (str3.trim().length() == 0) {
                            throw new BuildException("File extension when specified must not be an empty string");
                        }
                        try {
                            if (this.resources != null) {
                                Iterator<Resource> it = this.resources.iterator();
                                while (it.hasNext()) {
                                    Resource next = it.next();
                                    File file2 = ((FileProvider) next.mo14823as(FileProvider.class)).getFile();
                                    if (this.totalproperty != null || this.todir != null) {
                                        this.relativeFilePaths.put(file2, next.getName().replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX));
                                    }
                                    addToIncludeFileMap(file2);
                                }
                            }
                            if (this.file != null) {
                                if (!(this.totalproperty == null && this.todir == null)) {
                                    this.relativeFilePaths.put(this.file, this.file.getName().replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX));
                                }
                                addToIncludeFileMap(this.file);
                            }
                            return generateChecksums();
                        } finally {
                            this.fileext = str;
                            this.includeFileMap.clear();
                        }
                    } else {
                        throw new BuildException("Unable to create Message Digest", getLocation());
                    }
                } else {
                    throw new BuildException("ForceOverwrite cannot be used when conditions are being used.");
                }
            } else {
                throw new BuildException("Property and FileExt cannot co-exist.");
            }
        } else {
            throw new BuildException("Can only calculate checksums for file-based resources.");
        }
    }

    private void addToIncludeFileMap(File file) throws BuildException {
        if (file.exists()) {
            String str = this.property;
            if (str == null) {
                File checksumFile = getChecksumFile(file);
                if (this.forceOverwrite || this.isCondition || file.lastModified() > checksumFile.lastModified()) {
                    this.includeFileMap.put(file, checksumFile);
                    return;
                }
                log(file + " omitted as " + checksumFile + " is up to date.", 3);
                if (this.totalproperty != null) {
                    this.allDigests.put(file, decodeHex(readChecksum(checksumFile).toCharArray()));
                    return;
                }
                return;
            }
            this.includeFileMap.put(file, str);
            return;
        }
        String str2 = "Could not find file " + file.getAbsolutePath() + " to generate checksum for.";
        log(str2);
        throw new BuildException(str2, getLocation());
    }

    private File getChecksumFile(File file) {
        File file2;
        if (this.todir != null) {
            file2 = new File(this.todir, getRelativeFilePath(file)).getParentFile();
            file2.mkdirs();
        } else {
            file2 = file.getParentFile();
        }
        return new File(file2, file.getName() + this.fileext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean generateChecksums() throws BuildException {
        FileOutputStream fileOutputStream;
        Throwable th;
        Exception e;
        byte[] bArr = new byte[this.readBufferSize];
        FileInputStream fileInputStream = null;
        try {
            Iterator<Map.Entry<File, Object>> it = this.includeFileMap.entrySet().iterator();
            boolean z = 1;
            while (true) {
                z = 0;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<File, Object> next = it.next();
                this.messageDigest.reset();
                File key = next.getKey();
                if (!this.isCondition) {
                    log("Calculating " + this.algorithm + " checksum for " + key, 3);
                }
                FileInputStream fileInputStream2 = new FileInputStream(key);
                try {
                    DigestInputStream digestInputStream = new DigestInputStream(fileInputStream2, this.messageDigest);
                    while (digestInputStream.read(bArr, 0, this.readBufferSize) != -1) {
                    }
                    digestInputStream.close();
                    fileInputStream2.close();
                    byte[] digest = this.messageDigest.digest();
                    if (this.totalproperty != null) {
                        this.allDigests.put(key, digest);
                    }
                    String createDigestString = createDigestString(digest);
                    Object value = next.getValue();
                    if (value instanceof String) {
                        String str = (String) value;
                        if (this.isCondition) {
                            z = (z == 0 || !createDigestString.equals(this.property)) ? 0 : 1;
                        } else {
                            getProject().setNewProperty(str, createDigestString);
                        }
                    } else if (!(value instanceof File)) {
                        continue;
                    } else if (this.isCondition) {
                        File file = (File) value;
                        if (file.exists()) {
                            try {
                                String readChecksum = readChecksum(file);
                                if (z != 0 && createDigestString.equals(readChecksum)) {
                                    z = 1;
                                }
                            } catch (BuildException unused) {
                            }
                        }
                    } else {
                        File file2 = (File) value;
                        fileOutputStream = new FileOutputStream(file2);
                        try {
                            try {
                                fileOutputStream.write(this.format.format(new Object[]{createDigestString, key.getName(), FileUtils.getRelativePath(file2.getParentFile(), key), FileUtils.getRelativePath(getProject().getBaseDir(), key), key.getAbsolutePath()}).getBytes());
                                fileOutputStream.write(StringUtils.LINE_SEP.getBytes());
                                fileOutputStream.close();
                            } catch (Exception e2) {
                                e = e2;
                                throw new BuildException(e, getLocation());
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            FileUtils.close(fileInputStream);
                            FileUtils.close(fileOutputStream);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    FileUtils.close(fileInputStream);
                    FileUtils.close(fileOutputStream);
                    throw th;
                }
            }
            if (this.totalproperty != null) {
                File[] fileArr = (File[]) this.allDigests.keySet().toArray(new File[this.allDigests.size()]);
                Arrays.sort(fileArr, new Comparator<File>() { // from class: org.apache.tools.ant.taskdefs.Checksum.1
                    public int compare(File file3, File file4) {
                        if (file3 == null) {
                            return file4 == null ? 0 : -1;
                        }
                        if (file4 == null) {
                            return 1;
                        }
                        return Checksum.this.getRelativeFilePath(file3).compareTo(Checksum.this.getRelativeFilePath(file4));
                    }
                });
                this.messageDigest.reset();
                int length = fileArr.length;
                while (z < length) {
                    File file3 = fileArr[z];
                    this.messageDigest.update(this.allDigests.get(file3));
                    this.messageDigest.update(getRelativeFilePath(file3).getBytes());
                    z++;
                }
                getProject().setNewProperty(this.totalproperty, createDigestString(this.messageDigest.digest()));
            }
            FileUtils.close((InputStream) null);
            FileUtils.close((OutputStream) null);
            return z;
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    private String createDigestString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(ResultTypeConstant.f7213z);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static byte[] decodeHex(char[] cArr) throws BuildException {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[length >> 1];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                int digit = Character.digit(cArr[i], 16) << 4;
                i = i3 + 1;
                bArr[i2] = (byte) ((digit | Character.digit(cArr[i3], 16)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new BuildException("odd number of characters.");
    }

    private String readChecksum(File file) {
        Throwable th;
        IOException e;
        ParseException e2;
        try {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    Object[] parse = this.format.parse(bufferedReader.readLine());
                    if (parse == null || parse.length == 0 || parse[0] == null) {
                        throw new BuildException("failed to find a checksum");
                    }
                    String str = (String) parse[0];
                    FileUtils.close(bufferedReader);
                    return str;
                } catch (IOException e3) {
                    e = e3;
                    throw new BuildException("Couldn't read checksum file " + file, e);
                } catch (ParseException e4) {
                    e2 = e4;
                    throw new BuildException("Couldn't read checksum file " + file, e2);
                }
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close((Reader) null);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
        } catch (ParseException e6) {
            e2 = e6;
        } catch (Throwable th3) {
            th = th3;
            FileUtils.close((Reader) null);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getRelativeFilePath(File file) {
        String str = this.relativeFilePaths.get(file);
        if (str != null) {
            return str;
        }
        throw new BuildException("Internal error: relativeFilePaths could not match file " + file + "\nplease file a bug report on this");
    }

    /* loaded from: classes2.dex */
    public static class FormatElement extends EnumeratedAttribute {
        private static final String CHECKSUM = "CHECKSUM";
        private static final String MD5SUM = "MD5SUM";
        private static final String SVF = "SVF";
        private static HashMap<String, MessageFormat> formatMap = new HashMap<>();

        static {
            formatMap.put(CHECKSUM, new MessageFormat("{0}"));
            formatMap.put(MD5SUM, new MessageFormat("{0} *{1}"));
            formatMap.put(SVF, new MessageFormat("MD5 ({1}) = {0}"));
        }

        public static FormatElement getDefault() {
            FormatElement formatElement = new FormatElement();
            formatElement.setValue(CHECKSUM);
            return formatElement;
        }

        public MessageFormat getFormat() {
            return formatMap.get(getValue());
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{CHECKSUM, MD5SUM, SVF};
        }
    }
}
