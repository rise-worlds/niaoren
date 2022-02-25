package com.j256.ormlite.android.apptools;

import android.app.ActivityGroup;
import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import p110z1.ConnectionSource;

/* loaded from: classes.dex */
public abstract class OrmLiteBaseActivityGroup<H extends OrmLiteSqliteOpenHelper> extends ActivityGroup {

    /* renamed from: a */
    private volatile H f9383a;

    /* renamed from: b */
    private volatile boolean f9384b = false;

    /* renamed from: c */
    private volatile boolean f9385c = false;

    /* renamed from: a */
    public H m19876a() {
        if (this.f9383a != null) {
            return this.f9383a;
        }
        if (!this.f9384b) {
            throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
        } else if (this.f9385c) {
            throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
        } else {
            throw new IllegalStateException("Helper is null for some unknown reason");
        }
    }

    /* renamed from: b */
    public ConnectionSource m19873b() {
        return m19876a().getConnectionSource();
    }

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (this.f9383a == null) {
            this.f9383a = m19875a(this);
            this.f9384b = true;
        }
        super.onCreate(bundle);
    }

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        m19874a((OrmLiteBaseActivityGroup<H>) this.f9383a);
        this.f9385c = true;
    }

    /* renamed from: a */
    protected H m19875a(Context context) {
        return (H) OpenHelperManager.m19845a(context);
    }

    /* renamed from: a */
    protected void m19874a(H h) {
        OpenHelperManager.m19841b();
        this.f9383a = null;
    }
}
