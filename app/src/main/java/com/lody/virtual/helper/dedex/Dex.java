package com.lody.virtual.helper.dedex;

import com.lody.virtual.helper.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.zip.Adler32;

/* loaded from: classes.dex */
public class Dex {
    public final int dataEnd;
    public final int dexPosition;
    public final Header header;
    private final DataReader mReader;

    /* loaded from: classes.dex */
    public static class Header {
        static final String MAGIC_COMPACT_DEX = "cdex";
        static final String MAGIC_DEX = "dex";
        final int checksum_;
        final int class_defs_off_;
        final int class_defs_size_;
        public final int data_off_;
        final int data_size_;
        final int endian_tag_;
        final int field_ids_off_;
        final int field_ids_size_;
        public final int file_size_;
        public final int header_size_;
        final int link_off_;
        final int link_size_;
        final int map_off_;
        final int method_ids_off_;
        final int method_ids_size_;
        final int proto_ids_off_;
        final int proto_ids_size_;
        final int string_ids_off_;
        final int string_ids_size_;
        final int type_ids_off_;
        final int type_ids_size_;
        final String version;
        final char[] magic_ = new char[4];
        final char[] version_ = new char[4];
        final byte[] signature_ = new byte[20];
        final String magic = new String(this.magic_).trim();
        final boolean isCompactDex = this.magic.equals(MAGIC_COMPACT_DEX);

        public Header(DataReader dataReader) throws IOException {
            dataReader.readBytes(this.magic_);
            if (this.magic.equals(MAGIC_DEX) || this.isCompactDex) {
                dataReader.readBytes(this.version_);
                this.version = new String(this.version_).trim();
                if (this.isCompactDex || this.version.compareTo("035") >= 0) {
                    this.checksum_ = dataReader.readInt();
                    dataReader.readBytes(this.signature_);
                    this.file_size_ = dataReader.readInt();
                    this.header_size_ = dataReader.readInt();
                    this.endian_tag_ = dataReader.readInt();
                    this.link_size_ = dataReader.readInt();
                    this.link_off_ = dataReader.readInt();
                    this.map_off_ = dataReader.readInt();
                    this.string_ids_size_ = dataReader.readInt();
                    this.string_ids_off_ = dataReader.readInt();
                    this.type_ids_size_ = dataReader.readInt();
                    this.type_ids_off_ = dataReader.readInt();
                    this.proto_ids_size_ = dataReader.readInt();
                    this.proto_ids_off_ = dataReader.readInt();
                    this.field_ids_size_ = dataReader.readInt();
                    this.field_ids_off_ = dataReader.readInt();
                    this.method_ids_size_ = dataReader.readInt();
                    this.method_ids_off_ = dataReader.readInt();
                    this.class_defs_size_ = dataReader.readInt();
                    this.class_defs_off_ = dataReader.readInt();
                    this.data_size_ = dataReader.readInt();
                    this.data_off_ = dataReader.readInt();
                    return;
                }
                throw new IOException(String.format("Invalid dex version '%s'", this.version));
            }
            throw new IOException(String.format("Invalid dex magic '%s'", this.magic));
        }
    }

    public Dex(DataReader dataReader) throws IOException {
        this.dexPosition = dataReader.position();
        this.mReader = dataReader;
        this.header = new Header(dataReader);
        this.dataEnd = this.header.isCompactDex ? this.header.data_off_ + this.header.data_size_ : this.header.file_size_;
    }

    private void calcSignature(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.reset();
            instance.update(bArr, 32, bArr.length - 32);
            instance.digest(bArr, 12, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcChecksum(byte[] bArr) {
        Adler32 adler32 = new Adler32();
        adler32.update(bArr, 12, bArr.length - 12);
        int value = (int) adler32.getValue();
        if (this.header.checksum_ != value) {
            bArr[8] = (byte) value;
            bArr[9] = (byte) (value >> 8);
            bArr[10] = (byte) (value >> 16);
            bArr[11] = (byte) (value >> 24);
        }
    }

    public byte[] getFixedBytes() {
        byte[] bytes = getBytes();
        calcSignature(bytes);
        calcChecksum(bytes);
        return bytes;
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[this.dataEnd];
        this.mReader.position(this.dexPosition);
        this.mReader.readBytes(bArr);
        return bArr;
    }

    public void writeTo(File file) throws IOException {
        FileUtils.writeToFile(getFixedBytes(), file);
    }
}
