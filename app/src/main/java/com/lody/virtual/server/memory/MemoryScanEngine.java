package com.lody.virtual.server.memory;

import android.support.p003v4.media.session.PlaybackStateCompat;
import com.lody.virtual.helper.utils.VLog;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class MemoryScanEngine {
    private static final int PAGE = 4096;
    private List<Match> matches;
    private ProcessMemory memory;
    private int pid;
    private List<MappedMemoryRegion> regions;

    public MemoryScanEngine(int i) throws IOException {
        this.pid = i;
        this.memory = new ProcessMemory(i);
        updateMemoryLayout();
    }

    public void updateMemoryLayout() {
        try {
            this.regions = MemoryRegionParser.getMemoryRegions(this.pid);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Match> getMatches() {
        return this.matches;
    }

    public void search(MemoryValue memoryValue) throws IOException {
        MemoryScanEngine memoryScanEngine = this;
        memoryScanEngine.matches = new LinkedList();
        byte[] bArr = new byte[4096];
        byte[] bytes = memoryValue.toBytes();
        for (MappedMemoryRegion mappedMemoryRegion : memoryScanEngine.regions) {
            long j = mappedMemoryRegion.startAddress;
            long j2 = mappedMemoryRegion.endAddress;
            long j3 = j;
            while (j3 < j2) {
                try {
                    memoryScanEngine.matches.addAll(matchBytes(mappedMemoryRegion, j3, bArr, memoryScanEngine.memory.read(j3, bArr, Math.min(bArr.length, (int) (j2 - j3))), bytes));
                    j3 += PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    memoryScanEngine = this;
                } catch (IOException unused) {
                    String simpleName = getClass().getSimpleName();
                    VLog.m18992e(simpleName, "Unable to read region : " + mappedMemoryRegion.description);
                }
            }
            memoryScanEngine = this;
        }
    }

    public void modify(Match match, MemoryValue memoryValue) throws IOException {
        this.memory.write(match.address, memoryValue.toBytes());
    }

    public void modifyAll(MemoryValue memoryValue) throws IOException {
        for (Match match : this.matches) {
            modify(match, memoryValue);
        }
    }

    /* loaded from: classes.dex */
    public class Match {
        long address;
        int len;
        MappedMemoryRegion region;

        public Match(MappedMemoryRegion mappedMemoryRegion, long j, int i) {
            this.region = mappedMemoryRegion;
            this.address = j;
            this.len = i;
        }
    }

    private List<Match> matchBytes(MappedMemoryRegion mappedMemoryRegion, long j, byte[] bArr, int i, byte[] bArr2) {
        boolean z;
        LinkedList linkedList = new LinkedList();
        int length = bArr2.length;
        for (int i2 = 0; i2 < i; i2 += 2) {
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = i3 + i2;
                if (i4 >= i) {
                    break;
                } else if (bArr[i4] != bArr2[i3]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                linkedList.add(new Match(mappedMemoryRegion, j + i2, length));
            }
        }
        return linkedList;
    }

    public void close() {
        try {
            this.memory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
