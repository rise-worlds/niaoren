package com.common.utils.log;

/* loaded from: classes.dex */
public final class Settings {
    private boolean showThreadInfo = true;

    public Settings hideThreadInfo() {
        this.showThreadInfo = false;
        return this;
    }

    public boolean isHideThreadInfo() {
        return this.showThreadInfo;
    }
}
