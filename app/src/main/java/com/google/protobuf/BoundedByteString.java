package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.slf4j.Marker;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BoundedByteString extends LiteralByteString {
    private final int bytesLength;
    private final int bytesOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundedByteString(byte[] bArr, int i, int i2) {
        super(bArr);
        if (i < 0) {
            throw new IllegalArgumentException("Offset too small: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Length too small: " + i);
        } else if (i + i2 <= bArr.length) {
            this.bytesOffset = i;
            this.bytesLength = i2;
        } else {
            throw new IllegalArgumentException("Offset+Length too large: " + i + Marker.ANY_NON_NULL_MARKER + i2);
        }
    }

    @Override // com.google.protobuf.LiteralByteString, com.google.protobuf.ByteString
    public byte byteAt(int i) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index too small: " + i);
        } else if (i < size()) {
            return this.bytes[this.bytesOffset + i];
        } else {
            throw new ArrayIndexOutOfBoundsException("Index too large: " + i + ", " + size());
        }
    }

    @Override // com.google.protobuf.LiteralByteString, com.google.protobuf.ByteString
    public int size() {
        return this.bytesLength;
    }

    @Override // com.google.protobuf.LiteralByteString
    protected int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.LiteralByteString, com.google.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.bytes, getOffsetIntoBytes() + i, bArr, i2, i3);
    }

    @Override // com.google.protobuf.LiteralByteString, com.google.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator */
    public Iterator<Byte> iterator2() {
        return new BoundedByteIterator(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BoundedByteIterator implements ByteString.ByteIterator {
        private final int limit;
        private int position;

        /* synthetic */ BoundedByteIterator(BoundedByteString boundedByteString, BoundedByteIterator boundedByteIterator) {
            this();
        }

        private BoundedByteIterator() {
            this.position = BoundedByteString.this.getOffsetIntoBytes();
            this.limit = this.position + BoundedByteString.this.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.limit;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        @Override // com.google.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            if (this.position < this.limit) {
                byte[] bArr = BoundedByteString.this.bytes;
                int i = this.position;
                this.position = i + 1;
                return bArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
