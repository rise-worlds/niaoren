package com.lody.virtual.helper.utils;

/* loaded from: classes.dex */
public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create();

    public final T get() {
        if (this.mInstance == null) {
            synchronized (this) {
                if (this.mInstance == null) {
                    this.mInstance = create();
                }
            }
        }
        return this.mInstance;
    }
}
