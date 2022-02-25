package com.j256.ormlite.android.apptools;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import p110z1.C5570ui;
import p110z1.ConnectionSource;
import p110z1.LoggerFactory;

/* loaded from: classes.dex */
public abstract class OrmLiteBaseActivity<H extends OrmLiteSqliteOpenHelper> extends Activity {

    /* renamed from: d */
    private static C5570ui f9379d = LoggerFactory.m545a(OrmLiteBaseActivity.class);

    /* renamed from: a */
    private volatile H f9380a;

    /* renamed from: b */
    private volatile boolean f9381b = false;

    /* renamed from: c */
    private volatile boolean f9382c = false;

    /* renamed from: a */
    public H m19880a() {
        if (this.f9380a != null) {
            return this.f9380a;
        }
        if (!this.f9381b) {
            throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
        } else if (this.f9382c) {
            throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
        } else {
            throw new IllegalStateException("Helper is null for some unknown reason");
        }
    }

    /* renamed from: b */
    public ConnectionSource m19877b() {
        return m19880a().getConnectionSource();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (this.f9380a == null) {
            this.f9380a = m19879a(this);
            this.f9381b = true;
        }
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        m19878a((OrmLiteBaseActivity<H>) this.f9380a);
        this.f9382c = true;
    }

    /* renamed from: a */
    protected H m19879a(Context context) {
        H h = (H) OpenHelperManager.m19845a(context);
        f9379d.m618a("{}: got new helper {} from OpenHelperManager", this, h);
        return h;
    }

    /* renamed from: a */
    protected void m19878a(H h) {
        OpenHelperManager.m19841b();
        f9379d.m618a("{}: helper {} was released, set to null", this, h);
        this.f9380a = null;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }
}
