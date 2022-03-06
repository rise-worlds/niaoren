package com.lbd.xj.manager;

import p110z1.PreferencesUtil;

/* renamed from: com.lbd.xj.manager.b */
/* loaded from: classes.dex */
public enum XJDataManager {
    INSTANCE;
    
    public int nstatic = 1;
    private boolean fullActivityIsStart = false;

    XJDataManager() {
    }

    public boolean isFullActivityIsStart() {
        return this.fullActivityIsStart;
    }

    public void setFullActivityIsStart(boolean z) {
        this.fullActivityIsStart = z;
    }

    public void saveHasdecompression(boolean z) {
        PreferencesUtil.m13937a().m13931a("decompression", Boolean.valueOf(z));
    }
}
