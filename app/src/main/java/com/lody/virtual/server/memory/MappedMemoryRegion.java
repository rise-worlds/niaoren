package com.lody.virtual.server.memory;

/* loaded from: classes.dex */
public class MappedMemoryRegion {
    public final String description;
    public final long endAddress;
    public final FileMapping fileMapInfo;
    public final boolean isExecutable;
    public final boolean isReadable;
    public final boolean isShared;
    public final boolean isWritable;
    public final long startAddress;

    /* loaded from: classes.dex */
    public static class FileMapping {
        public final long inode;
        public final long majorDeviceNumber;
        public final long minorDeviceNumber;
        public final long offset;

        public FileMapping(long j, long j2, long j3, long j4) {
            this.offset = j;
            this.majorDeviceNumber = j2;
            this.minorDeviceNumber = j3;
            this.inode = j4;
        }
    }

    public MappedMemoryRegion(long j, long j2, boolean z, boolean z2, boolean z3, boolean z4, long j3, long j4, long j5, long j6, String str) {
        this.startAddress = j;
        this.endAddress = j2;
        this.isReadable = z;
        this.isWritable = z2;
        this.isExecutable = z3;
        this.isShared = z4;
        this.fileMapInfo = j6 == 0 ? null : new FileMapping(j3, j4, j5, j6);
        this.description = str;
    }

    public boolean isMappedFromFile() {
        return this.fileMapInfo != null;
    }
}
