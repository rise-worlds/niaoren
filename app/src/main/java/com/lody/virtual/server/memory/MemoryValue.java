package com.lody.virtual.server.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public abstract class MemoryValue {
    private static final ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;

    /* loaded from: classes.dex */
    public enum ValueType {
        INT2,
        INT4,
        INT8
    }

    public abstract byte[] toBytes();

    /* loaded from: classes.dex */
    public static class INT2 extends MemoryValue {
        private short val;

        public INT2(short s) {
            this.val = s;
        }

        @Override // com.lody.virtual.server.memory.MemoryValue
        public byte[] toBytes() {
            return ByteBuffer.allocate(2).putShort(this.val).order(MemoryValue.BYTE_ORDER).array();
        }
    }

    /* loaded from: classes.dex */
    public static class INT4 extends MemoryValue {
        private int val;

        public INT4(int i) {
            this.val = i;
        }

        @Override // com.lody.virtual.server.memory.MemoryValue
        public byte[] toBytes() {
            return ByteBuffer.allocate(4).order(MemoryValue.BYTE_ORDER).putInt(this.val).array();
        }
    }

    /* loaded from: classes.dex */
    public static class INT8 extends MemoryValue {
        private long val;

        public INT8(long j) {
            this.val = j;
        }

        @Override // com.lody.virtual.server.memory.MemoryValue
        public byte[] toBytes() {
            return ByteBuffer.allocate(8).order(MemoryValue.BYTE_ORDER).putLong(this.val).array();
        }
    }
}
