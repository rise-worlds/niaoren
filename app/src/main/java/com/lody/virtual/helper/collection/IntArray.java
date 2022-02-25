package com.lody.virtual.helper.collection;

import java.util.Arrays;

/* loaded from: classes.dex */
public class IntArray {
    private static final int[] EMPTY_ARRAY = new int[0];
    private int[] mData;
    private int mSize;

    private IntArray() {
    }

    public IntArray(int i) {
        this.mData = new int[i];
    }

    /* renamed from: of */
    public static IntArray m19002of(int... iArr) {
        IntArray intArray = new IntArray();
        intArray.mData = Arrays.copyOf(iArr, iArr.length);
        intArray.mSize = iArr.length;
        return intArray;
    }

    public void clear() {
        this.mSize = 0;
    }

    public void optimize() {
        int i = this.mSize;
        int[] iArr = this.mData;
        if (i > iArr.length) {
            this.mData = Arrays.copyOf(iArr, i);
        }
    }

    public int[] getAll() {
        int i = this.mSize;
        return i > 0 ? Arrays.copyOf(this.mData, i) : EMPTY_ARRAY;
    }

    public int get(int i) {
        return this.mData[i];
    }

    public int[] getRange(int i, int i2) {
        return Arrays.copyOfRange(this.mData, i, i2);
    }

    public void set(int i, int i2) {
        if (i < this.mSize) {
            this.mData[i] = i2;
            return;
        }
        throw new IndexOutOfBoundsException("Index " + i + " is greater than the list size " + this.mSize);
    }

    private void ensureCapacity() {
        int i = this.mSize;
        int[] iArr = this.mData;
        if (i > iArr.length) {
            int length = iArr.length;
            while (this.mSize > length) {
                length = ((length * 3) / 2) + 1;
            }
            this.mData = Arrays.copyOf(this.mData, length);
        }
    }

    public int size() {
        return this.mSize;
    }

    public void addAll(int[] iArr) {
        int i = this.mSize;
        this.mSize = iArr.length + i;
        ensureCapacity();
        System.arraycopy(iArr, 0, this.mData, i, iArr.length);
    }

    public void add(int i) {
        this.mSize++;
        ensureCapacity();
        this.mData[this.mSize - 1] = i;
    }

    public void remove(int i) {
        remove(i, 1);
    }

    public void remove(int i, int i2) {
        int[] iArr = this.mData;
        System.arraycopy(iArr, i + i2, iArr, i, (this.mSize - i) - i2);
        this.mSize -= i2;
    }

    public boolean contains(int i) {
        for (int i2 = 0; i2 < this.mSize; i2++) {
            if (this.mData[i2] == i) {
                return true;
            }
        }
        return false;
    }
}
