package org.apache.tools.zip;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* loaded from: classes2.dex */
public class AsiExtraField implements Cloneable, UnixStat, ZipExtraField {
    private static final ZipShort HEADER_ID = new ZipShort(30062);
    private static final int WORD = 4;
    private int mode = 0;
    private int uid = 0;
    private int gid = 0;
    private String link = "";
    private boolean dirFlag = false;
    private CRC32 crc = new CRC32();

    @Override // org.apache.tools.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(getLinkedFile().getBytes().length + 14);
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] bArr = new byte[getLocalFileDataLength().getValue() - 4];
        System.arraycopy(ZipShort.getBytes(getMode()), 0, bArr, 0, 2);
        byte[] bytes = getLinkedFile().getBytes();
        System.arraycopy(ZipLong.getBytes(bytes.length), 0, bArr, 2, 4);
        System.arraycopy(ZipShort.getBytes(getUserId()), 0, bArr, 6, 2);
        System.arraycopy(ZipShort.getBytes(getGroupId()), 0, bArr, 8, 2);
        System.arraycopy(bytes, 0, bArr, 10, bytes.length);
        this.crc.reset();
        this.crc.update(bArr);
        long value = this.crc.getValue();
        byte[] bArr2 = new byte[bArr.length + 4];
        System.arraycopy(ZipLong.getBytes(value), 0, bArr2, 0, 4);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        return bArr2;
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    public void setUserId(int i) {
        this.uid = i;
    }

    public int getUserId() {
        return this.uid;
    }

    public void setGroupId(int i) {
        this.gid = i;
    }

    public int getGroupId() {
        return this.gid;
    }

    public void setLinkedFile(String str) {
        this.link = str;
        this.mode = getMode(this.mode);
    }

    public String getLinkedFile() {
        return this.link;
    }

    public boolean isLink() {
        return getLinkedFile().length() != 0;
    }

    public void setMode(int i) {
        this.mode = getMode(i);
    }

    public int getMode() {
        return this.mode;
    }

    public void setDirectory(boolean z) {
        this.dirFlag = z;
        this.mode = getMode(this.mode);
    }

    public boolean isDirectory() {
        return this.dirFlag && !isLink();
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        long value;
        long value2 = ZipLong.getValue(bArr, i);
        int i3 = i2 - 4;
        byte[] bArr2 = new byte[i3];
        boolean z = false;
        System.arraycopy(bArr, i + 4, bArr2, 0, i3);
        this.crc.reset();
        this.crc.update(bArr2);
        if (value2 == this.crc.getValue()) {
            int value3 = ZipShort.getValue(bArr2, 0);
            byte[] bArr3 = new byte[(int) ZipLong.getValue(bArr2, 2)];
            this.uid = ZipShort.getValue(bArr2, 6);
            this.gid = ZipShort.getValue(bArr2, 8);
            if (bArr3.length == 0) {
                this.link = "";
            } else {
                System.arraycopy(bArr2, 10, bArr3, 0, bArr3.length);
                this.link = new String(bArr3);
            }
            if ((value3 & 16384) != 0) {
                z = true;
            }
            setDirectory(z);
            setMode(value3);
            return;
        }
        throw new ZipException("bad CRC checksum " + Long.toHexString(value2) + " instead of " + Long.toHexString(value));
    }

    protected int getMode(int i) {
        int i2;
        if (isLink()) {
            i2 = UnixStat.LINK_FLAG;
        } else {
            i2 = isDirectory() ? 16384 : 32768;
        }
        return (i & UnixStat.PERM_MASK) | i2;
    }

    public Object clone() {
        try {
            AsiExtraField asiExtraField = (AsiExtraField) super.clone();
            asiExtraField.crc = new CRC32();
            return asiExtraField;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
