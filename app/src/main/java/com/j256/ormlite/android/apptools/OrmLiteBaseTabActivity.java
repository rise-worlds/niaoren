package com.j256.ormlite.android.apptools;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import p110z1.ConnectionSource;

/* loaded from: classes.dex */
public abstract class OrmLiteBaseTabActivity<H extends OrmLiteSqliteOpenHelper> extends TabActivity {

    /* renamed from: a */
    private volatile H f9392a;

    /* renamed from: b */
    private volatile boolean f9393b = false;

    /* renamed from: c */
    private volatile boolean f9394c = false;

    /* renamed from: a */
    public H m19864a() {
        if (this.f9392a != null) {
            return this.f9392a;
        }
        if (!this.f9393b) {
            throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
        } else if (this.f9394c) {
            throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
        } else {
            throw new IllegalStateException("Helper is null for some unknown reason");
        }
    }

    /* renamed from: b */
    public ConnectionSource m19861b() {
        return m19864a().getConnectionSource();
    }

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (this.f9392a == null) {
            this.f9392a = m19863a(this);
            this.f9393b = true;
        }
        super.onCreate(bundle);
    }

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        m19862a((OrmLiteBaseTabActivity<H>) this.f9392a);
        this.f9394c = true;
    }

    /* renamed from: a */
    protected H m19863a(Context context) {
        return (H) OpenHelperManager.m19845a(context);
    }

    /* renamed from: a */
    protected void m19862a(H h) {
        OpenHelperManager.m19841b();
        this.f9392a = null;
    }
}
