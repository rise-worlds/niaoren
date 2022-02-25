package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes2.dex */
public class TbsVideoCacheTask {
    public static final String KEY_VIDEO_CACHE_PARAM_FILENAME = "filename";
    public static final String KEY_VIDEO_CACHE_PARAM_FOLDERPATH = "folderPath";
    public static final String KEY_VIDEO_CACHE_PARAM_HEADER = "header";
    public static final String KEY_VIDEO_CACHE_PARAM_URL = "url";

    /* renamed from: a */
    Context f12984a;

    /* renamed from: b */
    TbsVideoCacheListener f12985b;

    /* renamed from: e */
    private String f12988e;

    /* renamed from: f */
    private String f12989f;

    /* renamed from: c */
    private boolean f12986c = false;

    /* renamed from: d */
    private TbsVideoCacheWizard f12987d = null;

    /* renamed from: g */
    private Object f12990g = null;

    public TbsVideoCacheTask(Context context, Bundle bundle, TbsVideoCacheListener tbsVideoCacheListener) {
        this.f12984a = null;
        this.f12985b = null;
        this.f12984a = context;
        this.f12985b = tbsVideoCacheListener;
        if (bundle != null) {
            this.f12988e = bundle.getString("taskId");
            this.f12989f = bundle.getString("url");
        }
        m16944a(bundle);
    }

    public String getTaskID() {
        return this.f12988e;
    }

    public String getTaskUrl() {
        return this.f12989f;
    }

    /* renamed from: a */
    private void m16944a(Bundle bundle) {
        DexLoader dexLoader;
        if (this.f12987d == null) {
            SDKEngine.m16828a(true).m16830a(this.f12984a, false, false);
            TbsWizard a = SDKEngine.m16828a(true).m16832a();
            if (a != null) {
                dexLoader = a.m16629b();
            } else {
                this.f12985b.onVideoDownloadError(this, -1, "init engine error!", null);
                dexLoader = null;
            }
            if (dexLoader != null) {
                this.f12987d = new TbsVideoCacheWizard(dexLoader);
            } else {
                this.f12985b.onVideoDownloadError(this, -1, "Java dexloader invalid!", null);
            }
        }
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar != null) {
            this.f12990g = nVar.m16654a(this.f12984a, this, bundle);
            if (this.f12990g == null) {
                this.f12985b.onVideoDownloadError(this, -1, "init task error!", null);
                return;
            }
            return;
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
        if (tbsVideoCacheListener != null) {
            tbsVideoCacheListener.onVideoDownloadError(this, -1, "init error!", null);
        }
    }

    public void pauseTask() {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar == null || this.f12990g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "pauseTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        nVar.m16655a();
    }

    public void stopTask() {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar == null || this.f12990g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "stopTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        nVar.m16651c();
    }

    public void resumeTask() {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar == null || this.f12990g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "resumeTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        nVar.m16652b();
    }

    public void removeTask(boolean z) {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar == null || this.f12990g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "removeTask failed, init uncompleted!", null);
                return;
            }
            return;
        }
        nVar.m16653a(z);
    }

    public long getContentLength() {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar != null && this.f12990g != null) {
            return nVar.m16650d();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
        if (tbsVideoCacheListener == null) {
            return 0L;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getContentLength failed, init uncompleted!", null);
        return 0L;
    }

    public int getDownloadedSize() {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar != null && this.f12990g != null) {
            return nVar.m16649e();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getDownloadedSize failed, init uncompleted!", null);
        return 0;
    }

    public int getProgress() {
        TbsVideoCacheWizard nVar = this.f12987d;
        if (nVar != null && this.f12990g != null) {
            return nVar.m16648f();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.f12985b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getProgress failed, init uncompleted!", null);
        return 0;
    }
}
