package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;
import com.tencent.tbs.video.interfaces.IUserStateHolder;

/* renamed from: com.tencent.smtt.sdk.o */
/* loaded from: classes2.dex */
class TbsVideoPlayer {

    /* renamed from: e */
    private static TbsVideoPlayer f13229e;

    /* renamed from: a */
    TbsVideoView f13230a;

    /* renamed from: b */
    Context f13231b;

    /* renamed from: c */
    IUserStateHolder f13232c;

    /* renamed from: d */
    IUserStateChangedListener f13233d;

    /* renamed from: a */
    public static synchronized TbsVideoPlayer m16644a(Context context) {
        TbsVideoPlayer oVar;
        synchronized (TbsVideoPlayer.class) {
            if (f13229e == null) {
                f13229e = new TbsVideoPlayer(context);
            }
            oVar = f13229e;
        }
        return oVar;
    }

    private TbsVideoPlayer(Context context) {
        this.f13230a = null;
        this.f13231b = context.getApplicationContext();
        this.f13230a = new TbsVideoView(this.f13231b);
    }

    /* renamed from: a */
    public boolean m16643a(String str, Bundle bundle, IUserStateHolder aVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("videoUrl", str);
        }
        if (aVar != null) {
            this.f13230a.m16640a();
            if (!this.f13230a.m16636b()) {
                return false;
            }
            this.f13232c = aVar;
            this.f13233d = new IUserStateChangedListener() { // from class: com.tencent.smtt.sdk.o.1
                @Override // com.tencent.tbs.video.interfaces.IUserStateChangedListener
                public void onUserStateChanged() {
                    TbsVideoPlayer.this.f13230a.m16634c();
                }
            };
            this.f13232c.m16362a(this.f13233d);
            bundle.putInt("callMode", 3);
        } else {
            bundle.putInt("callMode", 1);
        }
        this.f13230a.m16637a(bundle, aVar == null ? null : this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16645a(Activity activity, int i) {
        this.f13230a.m16638a(activity, i);
    }

    /* renamed from: a */
    public boolean m16647a() {
        this.f13230a.m16640a();
        return this.f13230a.m16636b();
    }

    /* renamed from: a */
    public void m16646a(int i, int i2, Intent intent) {
        IUserStateHolder aVar = this.f13232c;
        if (aVar != null) {
            aVar.m16363a(i, i2, intent);
        }
    }
}
