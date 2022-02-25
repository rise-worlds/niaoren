package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RopeByteString extends ByteString {
    private static final int[] minLengthByDepth;
    private int hash;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    static {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            i = i2 + i;
            i2 = i;
        }
        arrayList.add(Integer.MAX_VALUE);
        minLengthByDepth = new int[arrayList.size()];
        int i3 = 0;
        while (true) {
            int[] iArr = minLengthByDepth;
            if (i3 < iArr.length) {
                iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
                i3++;
            } else {
                return;
            }
        }
    }

    private RopeByteString(ByteString byteString, ByteString byteString2) {
        this.hash = 0;
        this.left = byteString;
        this.right = byteString2;
        this.leftLength = byteString.size();
        this.totalLength = this.leftLength + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    /* synthetic */ RopeByteString(ByteString byteString, ByteString byteString2, RopeByteString ropeByteString) {
        this(byteString, byteString2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        RopeByteString ropeByteString = byteString instanceof RopeByteString ? (RopeByteString) byteString : null;
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString.size() + byteString2.size();
        if (size < 128) {
            return concatenateBytes(byteString, byteString2);
        }
        if (ropeByteString != null && ropeByteString.right.size() + byteString2.size() < 128) {
            return new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
        } else if (ropeByteString == null || ropeByteString.left.getTreeDepth() <= ropeByteString.right.getTreeDepth() || ropeByteString.getTreeDepth() <= byteString2.getTreeDepth()) {
            if (size >= minLengthByDepth[Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1]) {
                return new RopeByteString(byteString, byteString2);
            }
            return new Balancer(null).balance(byteString, byteString2);
        } else {
            return new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
        }
    }

    private static LiteralByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return new LiteralByteString(bArr);
    }

    static RopeByteString newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new RopeByteString(byteString, byteString2);
    }

    @Override // com.google.protobuf.ByteString
    public byte byteAt(int i) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        } else if (i <= this.totalLength) {
            int i2 = this.leftLength;
            if (i < i2) {
                return this.left.byteAt(i);
            }
            return this.right.byteAt(i - i2);
        } else {
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + this.totalLength);
        }
    }

    @Override // com.google.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public int getTreeDepth() {
        return this.treeDepth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public boolean isBalanced() {
        return this.totalLength >= minLengthByDepth[this.treeDepth];
    }

    @Override // com.google.protobuf.ByteString
    public ByteString substring(int i, int i2) {
        if (i >= 0) {
            int i3 = this.totalLength;
            if (i2 <= i3) {
                int i4 = i2 - i;
                if (i4 < 0) {
                    throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
                } else if (i4 == 0) {
                    return ByteString.EMPTY;
                } else {
                    if (i4 == i3) {
                        return this;
                    }
                    int i5 = this.leftLength;
                    if (i2 <= i5) {
                        return this.left.substring(i, i2);
                    }
                    if (i >= i5) {
                        return this.right.substring(i - i5, i2 - i5);
                    }
                    return new RopeByteString(this.left.substring(i), this.right.substring(0, i2 - this.leftLength));
                }
            } else {
                throw new IndexOutOfBoundsException("End index: " + i2 + " > " + this.totalLength);
            }
        } else {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            this.left.copyToInternal(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.right.copyToInternal(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.left.copyToInternal(bArr, i, i2, i6);
            this.right.copyToInternal(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    @Override // com.google.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    @Override // com.google.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // com.google.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        PieceIterator pieceIterator = new PieceIterator(this, null);
        while (pieceIterator.hasNext()) {
            arrayList.add(pieceIterator.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    @Override // com.google.protobuf.ByteString
    public void writeTo(OutputStream outputStream) throws IOException {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }

    @Override // com.google.protobuf.ByteString
    public String toString(String str) throws UnsupportedEncodingException {
        return new String(toByteArray(), str);
    }

    @Override // com.google.protobuf.ByteString
    public boolean isValidUtf8() {
        int partialIsValidUtf8 = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        ByteString byteString = this.right;
        return byteString.partialIsValidUtf8(partialIsValidUtf8, 0, byteString.size()) == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public int partialIsValidUtf8(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialIsValidUtf8(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.protobuf.ByteString
    public boolean equals(Object obj) {
        int peekCachedHashCode;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (this.totalLength != byteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        if (this.hash == 0 || (peekCachedHashCode = byteString.peekCachedHashCode()) == 0 || this.hash == peekCachedHashCode) {
            return equalsFragments(byteString);
        }
        return false;
    }

    private boolean equalsFragments(ByteString byteString) {
        boolean z;
        PieceIterator pieceIterator = new PieceIterator(this, null);
        LiteralByteString next = pieceIterator.next();
        PieceIterator pieceIterator2 = new PieceIterator(byteString, null);
        LiteralByteString next2 = pieceIterator2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = next.size() - i;
            int size2 = next2.size() - i2;
            int min = Math.min(size, size2);
            if (i == 0) {
                z = next.equalsRange(next2, i2, min);
            } else {
                z = next2.equalsRange(next, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            int i4 = this.totalLength;
            if (i3 < i4) {
                if (min == size) {
                    next = pieceIterator.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == size2) {
                    next2 = pieceIterator2.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == i4) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override // com.google.protobuf.ByteString
    public int hashCode() {
        int i = this.hash;
        if (i == 0) {
            int i2 = this.totalLength;
            i = partialHash(i2, 0, i2);
            if (i == 0) {
                i = 1;
            }
            this.hash = i;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public int peekCachedHashCode() {
        return this.hash;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public int partialHash(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialHash(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialHash(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialHash(this.left.partialHash(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new RopeInputStream());
    }

    @Override // com.google.protobuf.ByteString
    public InputStream newInput() {
        return new RopeInputStream();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Balancer {
        private final Stack<ByteString> prefixesStack;

        private Balancer() {
            this.prefixesStack = new Stack<>();
        }

        /* synthetic */ Balancer(Balancer balancer) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ByteString balance(ByteString byteString, ByteString byteString2) {
            doBalance(byteString);
            doBalance(byteString2);
            ByteString pop = this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty()) {
                pop = new RopeByteString(this.prefixesStack.pop(), pop, null);
            }
            return pop;
        }

        private void doBalance(ByteString byteString) {
            while (!byteString.isBalanced()) {
                if (byteString instanceof RopeByteString) {
                    RopeByteString ropeByteString = (RopeByteString) byteString;
                    doBalance(ropeByteString.left);
                    byteString = ropeByteString.right;
                } else {
                    throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString.getClass());
                }
            }
            insert(byteString);
        }

        private void insert(ByteString byteString) {
            int depthBinForLength = getDepthBinForLength(byteString.size());
            int i = RopeByteString.minLengthByDepth[depthBinForLength + 1];
            if (this.prefixesStack.isEmpty() || this.prefixesStack.peek().size() >= i) {
                this.prefixesStack.push(byteString);
                return;
            }
            int i2 = RopeByteString.minLengthByDepth[depthBinForLength];
            ByteString pop = this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty() && this.prefixesStack.peek().size() < i2) {
                pop = new RopeByteString(this.prefixesStack.pop(), pop, null);
            }
            RopeByteString ropeByteString = new RopeByteString(pop, byteString, null);
            while (!this.prefixesStack.isEmpty()) {
                if (this.prefixesStack.peek().size() >= RopeByteString.minLengthByDepth[getDepthBinForLength(ropeByteString.size()) + 1]) {
                    break;
                }
                ropeByteString = new RopeByteString(this.prefixesStack.pop(), ropeByteString, null);
            }
            this.prefixesStack.push(ropeByteString);
        }

        private int getDepthBinForLength(int i) {
            int binarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PieceIterator implements Iterator<LiteralByteString> {
        private final Stack<RopeByteString> breadCrumbs;
        private LiteralByteString next;

        private PieceIterator(ByteString byteString) {
            this.breadCrumbs = new Stack<>();
            this.next = getLeafByLeft(byteString);
        }

        /* synthetic */ PieceIterator(ByteString byteString, PieceIterator pieceIterator) {
            this(byteString);
        }

        private LiteralByteString getLeafByLeft(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.breadCrumbs.push(ropeByteString);
                byteString = ropeByteString.left;
            }
            return (LiteralByteString) byteString;
        }

        private LiteralByteString getNextNonEmptyLeaf() {
            while (!this.breadCrumbs.isEmpty()) {
                LiteralByteString leafByLeft = getLeafByLeft(this.breadCrumbs.pop().right);
                if (!leafByLeft.isEmpty()) {
                    return leafByLeft;
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public LiteralByteString next() {
            LiteralByteString literalByteString = this.next;
            if (literalByteString != null) {
                this.next = getNextNonEmptyLeaf();
                return literalByteString;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator */
    public Iterator<Byte> iterator2() {
        return new RopeByteIterator(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RopeByteIterator implements ByteString.ByteIterator {
        private ByteString.ByteIterator bytes;
        int bytesRemaining;
        private final PieceIterator pieces;

        /* synthetic */ RopeByteIterator(RopeByteString ropeByteString, RopeByteIterator ropeByteIterator) {
            this();
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [com.google.protobuf.ByteString$ByteIterator] */
        private RopeByteIterator() {
            this.pieces = new PieceIterator(RopeByteString.this, null);
            this.bytes = this.pieces.next().iterator2();
            this.bytesRemaining = RopeByteString.this.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.bytesRemaining > 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        /* JADX WARN: Type inference failed for: r0v8, types: [com.google.protobuf.ByteString$ByteIterator] */
        @Override // com.google.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            if (!this.bytes.hasNext()) {
                this.bytes = this.pieces.next().iterator2();
            }
            this.bytesRemaining--;
            return this.bytes.nextByte();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    private class RopeInputStream extends InputStream {
        private LiteralByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        public RopeInputStream() {
            initialize();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException();
            } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
                return readSkipInternal(bArr, i, i2);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j >= 0) {
                if (j > 2147483647L) {
                    j = 2147483647L;
                }
                return readSkipInternal(null, 0, (int) j);
            }
            throw new IndexOutOfBoundsException();
        }

        private int readSkipInternal(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            while (true) {
                if (i4 <= 0) {
                    break;
                }
                advanceIfCurrentPieceFullyRead();
                if (this.currentPiece != null) {
                    int min = Math.min(this.currentPieceSize - this.currentPieceIndex, i4);
                    if (bArr != null) {
                        this.currentPiece.copyTo(bArr, this.currentPieceIndex, i3, min);
                        i3 += min;
                    }
                    this.currentPieceIndex += min;
                    i4 -= min;
                } else if (i4 == i2) {
                    return -1;
                }
            }
            return i2 - i4;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            advanceIfCurrentPieceFullyRead();
            LiteralByteString literalByteString = this.currentPiece;
            if (literalByteString == null) {
                return -1;
            }
            int i = this.currentPieceIndex;
            this.currentPieceIndex = i + 1;
            return literalByteString.byteAt(i) & 255;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            initialize();
            readSkipInternal(null, 0, this.mark);
        }

        private void initialize() {
            this.pieceIterator = new PieceIterator(RopeByteString.this, null);
            this.currentPiece = this.pieceIterator.next();
            this.currentPieceSize = this.currentPiece.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        private void advanceIfCurrentPieceFullyRead() {
            if (this.currentPiece != null) {
                int i = this.currentPieceIndex;
                int i2 = this.currentPieceSize;
                if (i == i2) {
                    this.currentPieceOffsetInRope += i2;
                    this.currentPieceIndex = 0;
                    if (this.pieceIterator.hasNext()) {
                        this.currentPiece = this.pieceIterator.next();
                        this.currentPieceSize = this.currentPiece.size();
                        return;
                    }
                    this.currentPiece = null;
                    this.currentPieceSize = 0;
                }
            }
        }
    }
}
