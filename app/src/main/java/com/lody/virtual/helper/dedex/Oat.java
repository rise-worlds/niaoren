package com.lody.virtual.helper.dedex;

import android.support.p003v4.media.session.PlaybackStateCompat;
import com.lody.virtual.helper.utils.FileUtils;
import com.tencent.smtt.sdk.TbsListener;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class Oat {
    public static final String SECTION_RODATA = ".rodata";
    public final Dex[] dexFiles;
    public final Header header;
    public final OatDexFile[] oatDexFiles;
    public final long oatPosition;
    public final File srcFile;

    /* loaded from: classes.dex */
    public static final class InstructionSet {
        public static final int kArm = 1;
        public static final int kArm64 = 2;
        public static final int kMips = 6;
        public static final int kMips64 = 7;
        public static final int kNone = 0;
        public static final int kThumb2 = 3;
        public static final int kX86 = 4;
        public static final int kX86_64 = 5;
    }

    /* loaded from: classes.dex */
    public enum Version {
        L_50(21, 39),
        L_MR1_51(22, 45),
        M_60(23, 64),
        N_70(24, 79),
        N_MR1_71(25, 88),
        O_80(26, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY),
        O_MR1_81(27, 131);
        
        public final int api;
        public final int oat;

        Version(int i, int i2) {
            this.api = i;
            this.oat = i2;
        }
    }

    /* loaded from: classes.dex */
    public static class Header {
        final int adler32_checksum_;
        int artVersion;
        final int dex_file_count_;
        final int executable_offset_;
        final int image_file_location_oat_checksum_;
        final int image_file_location_oat_data_begin_;
        final int image_patch_delta_;
        final int instruction_set_;
        final int instruction_set_features_;
        final int interpreter_to_compiled_code_bridge_offset_;
        final int interpreter_to_interpreter_bridge_offset_;
        final int jni_dlsym_lookup_offset_;
        final char[] key_value_store_;
        final int key_value_store_size_;
        int portable_imt_conflict_trampoline_offset_;
        int portable_resolution_trampoline_offset_;
        int portable_to_interpreter_bridge_offset_;
        final int quick_generic_jni_trampoline_offset_;
        final int quick_imt_conflict_trampoline_offset_;
        final int quick_resolution_trampoline_offset_;
        final int quick_to_interpreter_bridge_offset_;
        final char[] magic_ = new char[4];
        final char[] version_ = new char[4];

        public Header(DataReader dataReader) throws IOException {
            dataReader.readBytes(this.magic_);
            char[] cArr = this.magic_;
            if (cArr[0] == 'o' && cArr[1] == 'a' && cArr[2] == 't') {
                dataReader.readBytes(this.version_);
                this.artVersion = DataReader.toInt(new String(this.version_));
                this.adler32_checksum_ = dataReader.readInt();
                this.instruction_set_ = dataReader.readInt();
                this.instruction_set_features_ = dataReader.readInt();
                this.dex_file_count_ = dataReader.readInt();
                this.executable_offset_ = dataReader.readInt();
                this.interpreter_to_interpreter_bridge_offset_ = dataReader.readInt();
                this.interpreter_to_compiled_code_bridge_offset_ = dataReader.readInt();
                this.jni_dlsym_lookup_offset_ = dataReader.readInt();
                if (this.artVersion < 52) {
                    this.portable_imt_conflict_trampoline_offset_ = dataReader.readInt();
                    this.portable_resolution_trampoline_offset_ = dataReader.readInt();
                    this.portable_to_interpreter_bridge_offset_ = dataReader.readInt();
                }
                this.quick_generic_jni_trampoline_offset_ = dataReader.readInt();
                this.quick_imt_conflict_trampoline_offset_ = dataReader.readInt();
                this.quick_resolution_trampoline_offset_ = dataReader.readInt();
                this.quick_to_interpreter_bridge_offset_ = dataReader.readInt();
                this.image_patch_delta_ = dataReader.readInt();
                this.image_file_location_oat_checksum_ = dataReader.readInt();
                this.image_file_location_oat_data_begin_ = dataReader.readInt();
                this.key_value_store_size_ = dataReader.readInt();
                this.key_value_store_ = new char[this.key_value_store_size_];
                dataReader.readBytes(this.key_value_store_);
                return;
            }
            throw new IOException(String.format("Invalid art magic %c%c%c", Character.valueOf(this.magic_[0]), Character.valueOf(this.magic_[1]), Character.valueOf(this.magic_[2])));
        }
    }

    /* loaded from: classes.dex */
    public static class OatDexFile {
        int class_offsets_offset_;
        final int dex_file_location_checksum_;
        public final byte[] dex_file_location_data_;
        public final int dex_file_location_size_;
        final int dex_file_offset_;
        File dex_file_pointer_;
        int lookup_table_offset_;

        public OatDexFile(DataReader dataReader, int i) throws IOException {
            this.dex_file_location_size_ = dataReader.readInt();
            this.dex_file_location_data_ = new byte[this.dex_file_location_size_];
            dataReader.readBytes(this.dex_file_location_data_);
            this.dex_file_location_checksum_ = dataReader.readInt();
            this.dex_file_offset_ = dataReader.readInt();
            File changeExt = FileUtils.changeExt(dataReader.getFile(), "vdex");
            if (changeExt.exists()) {
                this.dex_file_pointer_ = changeExt;
            } else if (this.dex_file_offset_ == 28) {
                throw new IOException("dex_file_offset_=" + this.dex_file_offset_ + ", does " + changeExt.getName() + " miss?");
            }
            if (i >= Version.N_70.oat) {
                this.class_offsets_offset_ = dataReader.readInt();
                this.lookup_table_offset_ = dataReader.readInt();
            }
        }

        public String getLocation() {
            return new String(this.dex_file_location_data_);
        }
    }

    public Oat(DataReader dataReader) throws Exception {
        Dex dex;
        this.oatPosition = dataReader.position();
        if (this.oatPosition == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            this.srcFile = dataReader.getFile();
            this.header = new Header(dataReader);
            this.oatDexFiles = new OatDexFile[this.header.dex_file_count_];
            this.dexFiles = new Dex[this.header.dex_file_count_];
            for (int i = 0; i < this.oatDexFiles.length; i++) {
                OatDexFile oatDexFile = new OatDexFile(dataReader, this.header.artVersion);
                this.oatDexFiles[i] = oatDexFile;
                long position = dataReader.position();
                if (oatDexFile.dex_file_pointer_ != null) {
                    DataReader dataReader2 = new DataReader(oatDexFile.dex_file_pointer_);
                    dataReader.addAssociatedReader(dataReader2);
                    dataReader2.seek(oatDexFile.dex_file_offset_);
                    dex = new Dex(dataReader2);
                } else {
                    dataReader.seek(this.oatPosition + oatDexFile.dex_file_offset_);
                    dex = new Dex(dataReader);
                }
                this.dexFiles[i] = dex;
                if (this.header.artVersion < Version.N_70.oat) {
                    dataReader.seek(position + (dex.header.class_defs_size_ * 4));
                    if (dataReader.previewInt() > 255) {
                        dataReader.readInt();
                    }
                } else {
                    dataReader.seek(position);
                }
            }
            return;
        }
        throw new IOException("Strange oat position " + this.oatPosition);
    }

    public int getArtVersion() {
        return this.header.artVersion;
    }
}
