package com.lody.virtual.helper.dedex;

import java.io.IOException;

/* loaded from: classes.dex */
public class Vdex {
    private static final int VERSION_OREO_006 = 6;
    private static final int VERSION_OREO_MR1_010 = 10;
    private static final int VERSION_P_018 = 18;
    public final int dexBegin;
    public final QuickenDex[] dexFiles;
    public final Header header;
    public final int[] quickeningTableOffsets;

    /* loaded from: classes.dex */
    public static class Header {
        final int dex_shared_data_size_;
        final int dex_size_;
        public final int number_of_dex_files_;
        final int quickening_info_size_;
        final int[] vdexCheckSums;
        final int verifier_deps_size_;
        public final int version;
        final char[] magic_ = new char[4];
        final char[] version_ = new char[4];

        public Header(DataReader dataReader) throws IOException {
            dataReader.readBytes(this.magic_);
            String str = new String(this.magic_);
            if ("vdex".equals(str)) {
                dataReader.readBytes(this.version_);
                this.version = DataReader.toInt(new String(this.version_));
                this.number_of_dex_files_ = dataReader.readInt();
                this.dex_size_ = dataReader.readInt();
                int i = 0;
                this.dex_shared_data_size_ = versionNears(18) ? dataReader.readInt() : 0;
                this.verifier_deps_size_ = dataReader.readInt();
                this.quickening_info_size_ = dataReader.readInt();
                this.vdexCheckSums = new int[this.number_of_dex_files_];
                while (true) {
                    int[] iArr = this.vdexCheckSums;
                    if (i < iArr.length) {
                        iArr[i] = dataReader.readInt();
                        i++;
                    } else {
                        return;
                    }
                }
            } else {
                throw new IOException("Invalid dex magic '" + str + "'");
            }
        }

        public boolean versionNears(int i) {
            return Math.abs(this.version - i) <= 1;
        }
    }

    /* loaded from: classes.dex */
    public static class QuickenDex extends Dex {
        QuickenDex(DataReader dataReader) throws IOException {
            super(dataReader);
        }
    }

    public Vdex(DataReader dataReader) throws Exception {
        this.header = new Header(dataReader);
        this.dexBegin = dataReader.position();
        dataReader.position(this.dexBegin);
        this.quickeningTableOffsets = this.header.versionNears(18) ? new int[this.header.number_of_dex_files_] : null;
        this.dexFiles = new QuickenDex[this.header.number_of_dex_files_];
        for (int i = 0; i < this.header.number_of_dex_files_; i++) {
            int[] iArr = this.quickeningTableOffsets;
            if (iArr != null) {
                iArr[i] = dataReader.readInt();
            }
            QuickenDex quickenDex = new QuickenDex(dataReader);
            this.dexFiles[i] = quickenDex;
            dataReader.position(quickenDex.dexPosition + quickenDex.header.file_size_);
        }
    }
}
