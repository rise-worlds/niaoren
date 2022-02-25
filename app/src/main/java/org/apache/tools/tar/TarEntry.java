package org.apache.tools.tar;

import com.tencent.smtt.sdk.TbsListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.p105io.IOUtils;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.zip.ZipEncoding;

/* loaded from: classes2.dex */
public class TarEntry implements TarConstants {
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    public static final int MAX_NAMELEN = 31;
    public static final int MILLIS_PER_SECOND = 1000;
    private int devMajor;
    private int devMinor;
    private File file;
    private long groupId;
    private String groupName;
    private boolean isExtended;
    private byte linkFlag;
    private String linkName;
    private String magic;
    private long modTime;
    private int mode;
    private String name;
    private long realSize;
    private long size;
    private long userId;
    private String userName;
    private String version;

    private TarEntry() {
        this.magic = TarConstants.MAGIC_POSIX;
        this.version = TarConstants.VERSION_POSIX;
        this.name = "";
        this.linkName = "";
        String property = System.getProperty("user.name", "");
        property = property.length() > 31 ? property.substring(0, 31) : property;
        this.userId = 0L;
        this.groupId = 0L;
        this.userName = property;
        this.groupName = "";
        this.file = null;
    }

    public TarEntry(String str) {
        this(str, false);
    }

    public TarEntry(String str, boolean z) {
        this();
        String normalizeFileName = normalizeFileName(str, z);
        boolean endsWith = normalizeFileName.endsWith("/");
        this.devMajor = 0;
        this.devMinor = 0;
        this.name = normalizeFileName;
        this.mode = endsWith ? 16877 : 33188;
        this.linkFlag = endsWith ? TarConstants.LF_DIR : TarConstants.LF_NORMAL;
        this.userId = 0L;
        this.groupId = 0L;
        this.size = 0L;
        this.modTime = new Date().getTime() / 1000;
        this.linkName = "";
        this.userName = "";
        this.groupName = "";
    }

    public TarEntry(String str, byte b) {
        this(str);
        this.linkFlag = b;
        if (b == 76) {
            this.magic = TarConstants.GNU_TMAGIC;
            this.version = TarConstants.VERSION_GNU_SPACE;
        }
    }

    public TarEntry(File file) {
        this(file, file.getPath());
    }

    public TarEntry(File file, String str) {
        this();
        String normalizeFileName = normalizeFileName(str, false);
        this.file = file;
        this.linkName = "";
        if (file.isDirectory()) {
            this.mode = 16877;
            this.linkFlag = TarConstants.LF_DIR;
            int length = normalizeFileName.length();
            if (length == 0 || normalizeFileName.charAt(length - 1) != '/') {
                this.name = normalizeFileName + "/";
            } else {
                this.name = normalizeFileName;
            }
            this.size = 0L;
        } else {
            this.mode = 33188;
            this.linkFlag = TarConstants.LF_NORMAL;
            this.size = file.length();
            this.name = normalizeFileName;
        }
        this.modTime = file.lastModified() / 1000;
        this.devMajor = 0;
        this.devMinor = 0;
    }

    public TarEntry(byte[] bArr) {
        this();
        parseTarHeader(bArr);
    }

    public TarEntry(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        this();
        parseTarHeader(bArr, zipEncoding);
    }

    public boolean equals(TarEntry tarEntry) {
        return getName().equals(tarEntry.getName());
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return equals((TarEntry) obj);
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isDescendent(TarEntry tarEntry) {
        return tarEntry.getName().startsWith(getName());
    }

    public String getName() {
        return this.name.toString();
    }

    public void setName(String str) {
        this.name = normalizeFileName(str, false);
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public String getLinkName() {
        return this.linkName.toString();
    }

    public void setLinkName(String str) {
        this.linkName = str;
    }

    @Deprecated
    public int getUserId() {
        return (int) (this.userId & (-1));
    }

    public void setUserId(int i) {
        setUserId(i);
    }

    public long getLongUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    @Deprecated
    public int getGroupId() {
        return (int) (this.groupId & (-1));
    }

    public void setGroupId(int i) {
        setGroupId(i);
    }

    public long getLongGroupId() {
        return this.groupId;
    }

    public void setGroupId(long j) {
        this.groupId = j;
    }

    public String getUserName() {
        return this.userName.toString();
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getGroupName() {
        return this.groupName.toString();
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public void setIds(int i, int i2) {
        setUserId(i);
        setGroupId(i2);
    }

    public void setNames(String str, String str2) {
        setUserName(str);
        setGroupName(str2);
    }

    public void setModTime(long j) {
        this.modTime = j / 1000;
    }

    public void setModTime(Date date) {
        this.modTime = date.getTime() / 1000;
    }

    public Date getModTime() {
        return new Date(this.modTime * 1000);
    }

    public File getFile() {
        return this.file;
    }

    public int getMode() {
        return this.mode;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        if (j >= 0) {
            this.size = j;
            return;
        }
        throw new IllegalArgumentException("Size is out of range: " + j);
    }

    public int getDevMajor() {
        return this.devMajor;
    }

    public void setDevMajor(int i) {
        if (i >= 0) {
            this.devMajor = i;
            return;
        }
        throw new IllegalArgumentException("Major device number is out of range: " + i);
    }

    public int getDevMinor() {
        return this.devMinor;
    }

    public void setDevMinor(int i) {
        if (i >= 0) {
            this.devMinor = i;
            return;
        }
        throw new IllegalArgumentException("Minor device number is out of range: " + i);
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    public long getRealSize() {
        return this.realSize;
    }

    public boolean isGNUSparse() {
        return this.linkFlag == 83;
    }

    public boolean isGNULongLinkEntry() {
        return this.linkFlag == 75 && this.name.equals(TarConstants.GNU_LONGLINK);
    }

    public boolean isGNULongNameEntry() {
        return this.linkFlag == 76 && this.name.equals(TarConstants.GNU_LONGLINK);
    }

    public boolean isPaxHeader() {
        byte b = this.linkFlag;
        return b == 120 || b == 88;
    }

    public boolean isGlobalPaxHeader() {
        return this.linkFlag == 103;
    }

    public boolean isDirectory() {
        File file = this.file;
        if (file != null) {
            return file.isDirectory();
        }
        return this.linkFlag == 53 || getName().endsWith("/");
    }

    public boolean isFile() {
        File file = this.file;
        if (file != null) {
            return file.isFile();
        }
        byte b = this.linkFlag;
        if (b == 0 || b == 48) {
            return true;
        }
        return !getName().endsWith("/");
    }

    public boolean isSymbolicLink() {
        return this.linkFlag == 50;
    }

    public boolean isLink() {
        return this.linkFlag == 49;
    }

    public boolean isCharacterDevice() {
        return this.linkFlag == 51;
    }

    public boolean isBlockDevice() {
        return this.linkFlag == 52;
    }

    public boolean isFIFO() {
        return this.linkFlag == 54;
    }

    public TarEntry[] getDirectoryEntries() {
        File file = this.file;
        if (file == null || !file.isDirectory()) {
            return new TarEntry[0];
        }
        String[] list = this.file.list();
        TarEntry[] tarEntryArr = new TarEntry[list.length];
        for (int i = 0; i < list.length; i++) {
            tarEntryArr[i] = new TarEntry(new File(this.file, list[i]));
        }
        return tarEntryArr;
    }

    public void writeEntryHeader(byte[] bArr) {
        try {
            try {
                writeEntryHeader(bArr, TarUtils.DEFAULT_ENCODING, false);
            } catch (IOException unused) {
                writeEntryHeader(bArr, TarUtils.FALLBACK_ENCODING, false);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeEntryHeader(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        int writeEntryHeaderField = writeEntryHeaderField(this.modTime, bArr, writeEntryHeaderField(this.size, bArr, writeEntryHeaderField(this.groupId, bArr, writeEntryHeaderField(this.userId, bArr, writeEntryHeaderField(this.mode, bArr, TarUtils.formatNameBytes(this.name, bArr, 0, 100, zipEncoding), 8, z), 8, z), 8, z), 12, z), 12, z);
        int i = writeEntryHeaderField;
        for (int i2 = 0; i2 < 8; i2++) {
            i++;
            bArr[i] = 32;
        }
        bArr[i] = this.linkFlag;
        int writeEntryHeaderField2 = writeEntryHeaderField(this.devMinor, bArr, writeEntryHeaderField(this.devMajor, bArr, TarUtils.formatNameBytes(this.groupName, bArr, TarUtils.formatNameBytes(this.userName, bArr, TarUtils.formatNameBytes(this.version, bArr, TarUtils.formatNameBytes(this.magic, bArr, TarUtils.formatNameBytes(this.linkName, bArr, i + 1, 100, zipEncoding), 6), 2), 32, zipEncoding), 32, zipEncoding), 8, z), 8, z);
        while (writeEntryHeaderField2 < bArr.length) {
            writeEntryHeaderField2++;
            bArr[writeEntryHeaderField2] = 0;
        }
        TarUtils.formatCheckSumOctalBytes(TarUtils.computeCheckSum(bArr), bArr, writeEntryHeaderField, 8);
    }

    private int writeEntryHeaderField(long j, byte[] bArr, int i, int i2, boolean z) {
        if (z || (j >= 0 && j < (1 << ((i2 - 1) * 3)))) {
            return TarUtils.formatLongOctalOrBinaryBytes(j, bArr, i, i2);
        }
        return TarUtils.formatLongOctalBytes(0L, bArr, i, i2);
    }

    public void parseTarHeader(byte[] bArr) {
        try {
            try {
                parseTarHeader(bArr, TarUtils.DEFAULT_ENCODING);
            } catch (IOException unused) {
                parseTarHeader(bArr, TarUtils.DEFAULT_ENCODING, true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parseTarHeader(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        parseTarHeader(bArr, zipEncoding, false);
    }

    private void parseTarHeader(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        String str;
        this.name = z ? TarUtils.parseName(bArr, 0, 100) : TarUtils.parseName(bArr, 0, 100, zipEncoding);
        this.mode = (int) TarUtils.parseOctalOrBinary(bArr, 100, 8);
        this.userId = (int) TarUtils.parseOctalOrBinary(bArr, 108, 8);
        this.groupId = (int) TarUtils.parseOctalOrBinary(bArr, 116, 8);
        this.size = TarUtils.parseOctalOrBinary(bArr, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY, 12);
        this.modTime = TarUtils.parseOctalOrBinary(bArr, 136, 12);
        this.linkFlag = bArr[156];
        if (z) {
            str = TarUtils.parseName(bArr, 157, 100);
        } else {
            str = TarUtils.parseName(bArr, 157, 100, zipEncoding);
        }
        this.linkName = str;
        this.magic = TarUtils.parseName(bArr, 257, 6);
        this.version = TarUtils.parseName(bArr, TarConstants.VERSION_OFFSET, 2);
        this.userName = z ? TarUtils.parseName(bArr, 265, 32) : TarUtils.parseName(bArr, 265, 32, zipEncoding);
        this.groupName = z ? TarUtils.parseName(bArr, 297, 32) : TarUtils.parseName(bArr, 297, 32, zipEncoding);
        this.devMajor = (int) TarUtils.parseOctalOrBinary(bArr, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 8);
        this.devMinor = (int) TarUtils.parseOctalOrBinary(bArr, 337, 8);
        if (evaluateType(bArr) != 2) {
            String parseName = z ? TarUtils.parseName(bArr, 345, TarConstants.PREFIXLEN) : TarUtils.parseName(bArr, 345, TarConstants.PREFIXLEN, zipEncoding);
            if (isDirectory() && !this.name.endsWith("/")) {
                this.name += "/";
            }
            if (parseName.length() > 0) {
                this.name = parseName + "/" + this.name;
                return;
            }
            return;
        }
        this.isExtended = TarUtils.parseBoolean(bArr, 482);
        this.realSize = TarUtils.parseOctal(bArr, 483, 12);
    }

    private static String normalizeFileName(String str, boolean z) {
        int indexOf;
        String lowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (lowerCase != null) {
            if (lowerCase.startsWith(C3209Os.FAMILY_WINDOWS)) {
                if (str.length() > 2) {
                    char charAt = str.charAt(0);
                    if (str.charAt(1) == ':' && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                        str = str.substring(2);
                    }
                }
            } else if (lowerCase.indexOf(C3209Os.FAMILY_NETWARE) > -1 && (indexOf = str.indexOf(58)) != -1) {
                str = str.substring(indexOf + 1);
            }
        }
        String replace = str.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX);
        while (!z && replace.startsWith("/")) {
            replace = replace.substring(1);
        }
        return replace;
    }

    private int evaluateType(byte[] bArr) {
        if (matchAsciiBuffer(TarConstants.GNU_TMAGIC, bArr, 257, 6)) {
            return 2;
        }
        return matchAsciiBuffer(TarConstants.MAGIC_POSIX, bArr, 257, 6) ? 3 : 0;
    }

    private static boolean matchAsciiBuffer(String str, byte[] bArr, int i, int i2) {
        try {
            byte[] bytes = str.getBytes(HTTP.ASCII);
            return isEqual(bytes, 0, bytes.length, bArr, i, i2, false);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isEqual(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, boolean z) {
        int i5 = i2 < i4 ? i2 : i4;
        for (int i6 = 0; i6 < i5; i6++) {
            if (bArr[i + i6] != bArr2[i3 + i6]) {
                return false;
            }
        }
        if (i2 == i4) {
            return true;
        }
        if (!z) {
            return false;
        }
        if (i2 > i4) {
            while (i4 < i2) {
                if (bArr[i + i4] != 0) {
                    return false;
                }
                i4++;
            }
        } else {
            while (i2 < i4) {
                if (bArr2[i3 + i2] != 0) {
                    return false;
                }
                i2++;
            }
        }
        return true;
    }
}
