package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
        continue;
     */
    /* renamed from: of */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.Options m14865of(okio.ByteString... r10) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.m14865of(okio.ByteString[]):okio.Options");
    }

    private static void buildTrieRecursive(long j, Buffer buffer, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        if (i2 < i3) {
            for (int i7 = i2; i7 < i3; i7++) {
                if (list.get(i7).size() < i) {
                    throw new AssertionError();
                }
            }
            ByteString byteString = list.get(i2);
            ByteString byteString2 = list.get(i3 - 1);
            int i8 = -1;
            if (i == byteString.size()) {
                i8 = list2.get(i2).intValue();
                int i9 = i2 + 1;
                byteString = list.get(i9);
                i4 = i9;
            } else {
                i4 = i2;
            }
            if (byteString.getByte(i) != byteString2.getByte(i)) {
                int i10 = 1;
                for (int i11 = i4 + 1; i11 < i3; i11++) {
                    if (list.get(i11 - 1).getByte(i) != list.get(i11).getByte(i)) {
                        i10++;
                    }
                }
                long intCount = j + intCount(buffer) + 2 + (i10 * 2);
                buffer.writeInt(i10);
                buffer.writeInt(i8);
                for (int i12 = i4; i12 < i3; i12++) {
                    byte b = list.get(i12).getByte(i);
                    if (i12 == i4 || b != list.get(i12 - 1).getByte(i)) {
                        buffer.writeInt(b & 255);
                    }
                }
                Buffer buffer2 = new Buffer();
                int i13 = i4;
                while (i13 < i3) {
                    byte b2 = list.get(i13).getByte(i);
                    int i14 = i13 + 1;
                    int i15 = i14;
                    while (true) {
                        if (i15 >= i3) {
                            i6 = i3;
                            break;
                        } else if (b2 != list.get(i15).getByte(i)) {
                            i6 = i15;
                            break;
                        } else {
                            i15++;
                        }
                    }
                    if (i14 == i6 && i + 1 == list.get(i13).size()) {
                        buffer.writeInt(list2.get(i13).intValue());
                        i13 = i6;
                        buffer2 = buffer2;
                    } else {
                        buffer.writeInt((int) ((intCount(buffer2) + intCount) * (-1)));
                        i13 = i6;
                        buffer2 = buffer2;
                        buildTrieRecursive(intCount, buffer2, i + 1, list, i13, i6, list2);
                    }
                }
                buffer.write(buffer2, buffer2.size());
                return;
            }
            int min = Math.min(byteString.size(), byteString2.size());
            int i16 = 0;
            for (int i17 = i; i17 < min && byteString.getByte(i17) == byteString2.getByte(i17); i17++) {
                i16++;
            }
            long intCount2 = 1 + j + intCount(buffer) + 2 + i16;
            buffer.writeInt(-i16);
            buffer.writeInt(i8);
            int i18 = i;
            while (true) {
                i5 = i + i16;
                if (i18 >= i5) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i18) & 255);
                i18++;
            }
            if (i4 + 1 != i3) {
                Buffer buffer3 = new Buffer();
                buffer.writeInt((int) ((intCount(buffer3) + intCount2) * (-1)));
                buildTrieRecursive(intCount2, buffer3, i5, list, i4, i3, list2);
                buffer.write(buffer3, buffer3.size());
            } else if (i5 == list.get(i4).size()) {
                buffer.writeInt(list2.get(i4).intValue());
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }
}
