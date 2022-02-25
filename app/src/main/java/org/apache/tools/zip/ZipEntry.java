package org.apache.tools.zip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.zip.ZipException;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.zip.ExtraFieldUtils;

/* loaded from: classes2.dex */
public class ZipEntry extends java.util.zip.ZipEntry implements Cloneable {
    public static final int CRC_UNKNOWN = -1;
    public static final int PLATFORM_FAT = 0;
    public static final int PLATFORM_UNIX = 3;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_SHIFT = 16;
    private long externalAttributes;
    private ZipExtraField[] extraFields;
    private GeneralPurposeBit gpb;
    private int internalAttributes;
    private int method;
    private String name;
    private int platform;
    private byte[] rawName;
    private long size;
    private UnparseableExtraFieldData unparseableExtra;
    private static final byte[] EMPTY = new byte[0];
    private static final ZipExtraField[] noExtraFields = new ZipExtraField[0];

    public ZipEntry(String str) {
        super(str);
        this.method = -1;
        this.size = -1L;
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.unparseableExtra = null;
        this.name = null;
        this.rawName = null;
        this.gpb = new GeneralPurposeBit();
        setName(str);
    }

    public ZipEntry(java.util.zip.ZipEntry zipEntry) throws ZipException {
        super(zipEntry);
        this.method = -1;
        this.size = -1L;
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.unparseableExtra = null;
        this.name = null;
        this.rawName = null;
        this.gpb = new GeneralPurposeBit();
        setName(zipEntry.getName());
        byte[] extra = zipEntry.getExtra();
        if (extra != null) {
            setExtraFields(ExtraFieldUtils.parse(extra, true, ExtraFieldUtils.UnparseableExtraField.READ));
        } else {
            setExtra();
        }
        setMethod(zipEntry.getMethod());
        this.size = zipEntry.getSize();
    }

    public ZipEntry(ZipEntry zipEntry) throws ZipException {
        this((java.util.zip.ZipEntry) zipEntry);
        setInternalAttributes(zipEntry.getInternalAttributes());
        setExternalAttributes(zipEntry.getExternalAttributes());
        setExtraFields(getAllExtraFieldsNoCopy());
        setPlatform(zipEntry.getPlatform());
        GeneralPurposeBit generalPurposeBit = zipEntry.getGeneralPurposeBit();
        setGeneralPurposeBit(generalPurposeBit == null ? null : (GeneralPurposeBit) generalPurposeBit.clone());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ZipEntry() {
        this("");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ZipEntry(java.io.File r3, java.lang.String r4) {
        /*
            r2 = this;
            boolean r0 = r3.isDirectory()
            if (r0 == 0) goto L_0x001f
            java.lang.String r0 = "/"
            boolean r0 = r4.endsWith(r0)
            if (r0 != 0) goto L_0x001f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = "/"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
        L_0x001f:
            r2.<init>(r4)
            boolean r4 = r3.isFile()
            if (r4 == 0) goto L_0x002f
            long r0 = r3.length()
            r2.setSize(r0)
        L_0x002f:
            long r3 = r3.lastModified()
            r2.setTime(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.zip.ZipEntry.<init>(java.io.File, java.lang.String):void");
    }

    @Override // java.util.zip.ZipEntry
    public Object clone() {
        ZipEntry zipEntry = (ZipEntry) super.clone();
        zipEntry.setInternalAttributes(getInternalAttributes());
        zipEntry.setExternalAttributes(getExternalAttributes());
        zipEntry.setExtraFields(getAllExtraFieldsNoCopy());
        return zipEntry;
    }

    @Override // java.util.zip.ZipEntry
    public int getMethod() {
        return this.method;
    }

    @Override // java.util.zip.ZipEntry
    public void setMethod(int i) {
        if (i >= 0) {
            this.method = i;
            return;
        }
        throw new IllegalArgumentException("ZIP compression method can not be negative: " + i);
    }

    public int getInternalAttributes() {
        return this.internalAttributes;
    }

    public void setInternalAttributes(int i) {
        this.internalAttributes = i;
    }

    public long getExternalAttributes() {
        return this.externalAttributes;
    }

    public void setExternalAttributes(long j) {
        this.externalAttributes = j;
    }

    public void setUnixMode(int i) {
        int i2 = 0;
        int i3 = ((i & 128) == 0 ? 1 : 0) | (i << 16);
        if (isDirectory()) {
            i2 = 16;
        }
        setExternalAttributes(i3 | i2);
        this.platform = 3;
    }

    public int getUnixMode() {
        if (this.platform != 3) {
            return 0;
        }
        return (int) ((getExternalAttributes() >> 16) & 65535);
    }

    public int getPlatform() {
        return this.platform;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPlatform(int i) {
        this.platform = i;
    }

    public void setExtraFields(ZipExtraField[] zipExtraFieldArr) {
        ArrayList arrayList = new ArrayList();
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            if (zipExtraField instanceof UnparseableExtraFieldData) {
                this.unparseableExtra = (UnparseableExtraFieldData) zipExtraField;
            } else {
                arrayList.add(zipExtraField);
            }
        }
        this.extraFields = (ZipExtraField[]) arrayList.toArray(new ZipExtraField[arrayList.size()]);
        setExtra();
    }

    public ZipExtraField[] getExtraFields() {
        return getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(boolean z) {
        return z ? getAllExtraFields() : getParseableExtraFields();
    }

    private ZipExtraField[] getParseableExtraFieldsNoCopy() {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        return zipExtraFieldArr == null ? noExtraFields : zipExtraFieldArr;
    }

    private ZipExtraField[] getParseableExtraFields() {
        ZipExtraField[] parseableExtraFieldsNoCopy = getParseableExtraFieldsNoCopy();
        return parseableExtraFieldsNoCopy == this.extraFields ? copyOf(parseableExtraFieldsNoCopy) : parseableExtraFieldsNoCopy;
    }

    private ZipExtraField[] copyOf(ZipExtraField[] zipExtraFieldArr) {
        return copyOf(zipExtraFieldArr, zipExtraFieldArr.length);
    }

    private ZipExtraField[] copyOf(ZipExtraField[] zipExtraFieldArr, int i) {
        ZipExtraField[] zipExtraFieldArr2 = new ZipExtraField[i];
        System.arraycopy(zipExtraFieldArr, 0, zipExtraFieldArr2, 0, Math.min(zipExtraFieldArr.length, i));
        return zipExtraFieldArr2;
    }

    private ZipExtraField[] getMergedFields() {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        ZipExtraField[] copyOf = copyOf(zipExtraFieldArr, zipExtraFieldArr.length + 1);
        copyOf[this.extraFields.length] = this.unparseableExtra;
        return copyOf;
    }

    private ZipExtraField[] getUnparseableOnly() {
        UnparseableExtraFieldData unparseableExtraFieldData = this.unparseableExtra;
        return unparseableExtraFieldData == null ? noExtraFields : new ZipExtraField[]{unparseableExtraFieldData};
    }

    private ZipExtraField[] getAllExtraFields() {
        ZipExtraField[] allExtraFieldsNoCopy = getAllExtraFieldsNoCopy();
        return allExtraFieldsNoCopy == this.extraFields ? copyOf(allExtraFieldsNoCopy) : allExtraFieldsNoCopy;
    }

    private ZipExtraField[] getAllExtraFieldsNoCopy() {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        if (zipExtraFieldArr == null) {
            return getUnparseableOnly();
        }
        return this.unparseableExtra != null ? getMergedFields() : zipExtraFieldArr;
    }

    public void addExtraField(ZipExtraField zipExtraField) {
        if (zipExtraField instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData) zipExtraField;
        } else if (this.extraFields == null) {
            this.extraFields = new ZipExtraField[]{zipExtraField};
        } else {
            if (getExtraField(zipExtraField.getHeaderId()) != null) {
                removeExtraField(zipExtraField.getHeaderId());
            }
            ZipExtraField[] zipExtraFieldArr = this.extraFields;
            ZipExtraField[] copyOf = copyOf(zipExtraFieldArr, zipExtraFieldArr.length + 1);
            copyOf[this.extraFields.length] = zipExtraField;
            this.extraFields = copyOf;
        }
        setExtra();
    }

    public void addAsFirstExtraField(ZipExtraField zipExtraField) {
        if (zipExtraField instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData) zipExtraField;
        } else {
            if (getExtraField(zipExtraField.getHeaderId()) != null) {
                removeExtraField(zipExtraField.getHeaderId());
            }
            ZipExtraField[] zipExtraFieldArr = this.extraFields;
            this.extraFields = new ZipExtraField[zipExtraFieldArr != null ? zipExtraFieldArr.length + 1 : 1];
            ZipExtraField[] zipExtraFieldArr2 = this.extraFields;
            zipExtraFieldArr2[0] = zipExtraField;
            if (zipExtraFieldArr != null) {
                System.arraycopy(zipExtraFieldArr, 0, zipExtraFieldArr2, 1, zipExtraFieldArr2.length - 1);
            }
        }
        setExtra();
    }

    public void removeExtraField(ZipShort zipShort) {
        ZipExtraField[] zipExtraFieldArr;
        if (this.extraFields != null) {
            ArrayList arrayList = new ArrayList();
            for (ZipExtraField zipExtraField : this.extraFields) {
                if (!zipShort.equals(zipExtraField.getHeaderId())) {
                    arrayList.add(zipExtraField);
                }
            }
            if (this.extraFields.length != arrayList.size()) {
                this.extraFields = (ZipExtraField[]) arrayList.toArray(new ZipExtraField[arrayList.size()]);
                setExtra();
                return;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public void removeUnparseableExtraFieldData() {
        if (this.unparseableExtra != null) {
            this.unparseableExtra = null;
            setExtra();
            return;
        }
        throw new NoSuchElementException();
    }

    public ZipExtraField getExtraField(ZipShort zipShort) {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        if (zipExtraFieldArr == null) {
            return null;
        }
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            if (zipShort.equals(zipExtraField.getHeaderId())) {
                return zipExtraField;
            }
        }
        return null;
    }

    public UnparseableExtraFieldData getUnparseableExtraFieldData() {
        return this.unparseableExtra;
    }

    @Override // java.util.zip.ZipEntry
    public void setExtra(byte[] bArr) throws RuntimeException {
        try {
            mergeExtraFields(ExtraFieldUtils.parse(bArr, true, ExtraFieldUtils.UnparseableExtraField.READ), true);
        } catch (ZipException e) {
            throw new RuntimeException("Error parsing extra fields for entry: " + getName() + " - " + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setExtra() {
        super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(getExtraFields(true)));
    }

    public void setCentralDirectoryExtra(byte[] bArr) {
        try {
            mergeExtraFields(ExtraFieldUtils.parse(bArr, false, ExtraFieldUtils.UnparseableExtraField.READ), false);
        } catch (ZipException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] getLocalFileDataExtra() {
        byte[] extra = getExtra();
        return extra != null ? extra : EMPTY;
    }

    public byte[] getCentralDirectoryExtra() {
        return ExtraFieldUtils.mergeCentralDirectoryData(getExtraFields(true));
    }

    @Deprecated
    public void setComprSize(long j) {
        setCompressedSize(j);
    }

    @Override // java.util.zip.ZipEntry
    public String getName() {
        String str = this.name;
        return str == null ? super.getName() : str;
    }

    @Override // java.util.zip.ZipEntry
    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String str) {
        if (str != null && getPlatform() == 0 && str.indexOf("/") == -1) {
            str = str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
        }
        this.name = str;
    }

    @Override // java.util.zip.ZipEntry
    public long getSize() {
        return this.size;
    }

    @Override // java.util.zip.ZipEntry
    public void setSize(long j) {
        if (j >= 0) {
            this.size = j;
            return;
        }
        throw new IllegalArgumentException("invalid entry size");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String str, byte[] bArr) {
        setName(str);
        this.rawName = bArr;
    }

    public byte[] getRawName() {
        byte[] bArr = this.rawName;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    @Override // java.util.zip.ZipEntry
    public int hashCode() {
        return getName().hashCode();
    }

    public GeneralPurposeBit getGeneralPurposeBit() {
        return this.gpb;
    }

    public void setGeneralPurposeBit(GeneralPurposeBit generalPurposeBit) {
        this.gpb = generalPurposeBit;
    }

    private void mergeExtraFields(ZipExtraField[] zipExtraFieldArr, boolean z) throws ZipException {
        ZipExtraField zipExtraField;
        if (this.extraFields == null) {
            setExtraFields(zipExtraFieldArr);
            return;
        }
        for (ZipExtraField zipExtraField2 : zipExtraFieldArr) {
            if (zipExtraField2 instanceof UnparseableExtraFieldData) {
                zipExtraField = this.unparseableExtra;
            } else {
                zipExtraField = getExtraField(zipExtraField2.getHeaderId());
            }
            if (zipExtraField == null) {
                addExtraField(zipExtraField2);
            } else if (z || !(zipExtraField instanceof CentralDirectoryParsingZipExtraField)) {
                byte[] localFileDataData = zipExtraField2.getLocalFileDataData();
                zipExtraField.parseFromLocalFileData(localFileDataData, 0, localFileDataData.length);
            } else {
                byte[] centralDirectoryData = zipExtraField2.getCentralDirectoryData();
                ((CentralDirectoryParsingZipExtraField) zipExtraField).parseFromCentralDirectoryData(centralDirectoryData, 0, centralDirectoryData.length);
            }
        }
        setExtra();
    }

    public Date getLastModifiedDate() {
        return new Date(getTime());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZipEntry zipEntry = (ZipEntry) obj;
        String name = getName();
        String name2 = zipEntry.getName();
        if (name == null) {
            if (name2 != null) {
                return false;
            }
        } else if (!name.equals(name2)) {
            return false;
        }
        String comment = getComment();
        String comment2 = zipEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        if (comment2 == null) {
            comment2 = "";
        }
        return getTime() == zipEntry.getTime() && comment.equals(comment2) && getInternalAttributes() == zipEntry.getInternalAttributes() && getPlatform() == zipEntry.getPlatform() && getExternalAttributes() == zipEntry.getExternalAttributes() && getMethod() == zipEntry.getMethod() && getSize() == zipEntry.getSize() && getCrc() == zipEntry.getCrc() && getCompressedSize() == zipEntry.getCompressedSize() && Arrays.equals(getCentralDirectoryExtra(), zipEntry.getCentralDirectoryExtra()) && Arrays.equals(getLocalFileDataExtra(), zipEntry.getLocalFileDataExtra()) && this.gpb.equals(zipEntry.gpb);
    }
}
