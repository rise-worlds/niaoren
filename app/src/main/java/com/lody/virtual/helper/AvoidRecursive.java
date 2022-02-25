package com.lody.virtual.helper;

/* loaded from: classes.dex */
public class AvoidRecursive {
    private boolean mCalling = false;

    public boolean beginCall() {
        if (this.mCalling) {
            return false;
        }
        this.mCalling = true;
        return true;
    }

    public void finishCall() {
        this.mCalling = false;
    }
}
