package com.j256.ormlite.android.apptools;

import android.app.Service;
import android.content.Context;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import p110z1.ConnectionSource;

/* loaded from: classes.dex */
public abstract class OrmLiteBaseService<H extends OrmLiteSqliteOpenHelper> extends Service {

    /* renamed from: a */
    private volatile H f9389a;

    /* renamed from: b */
    private volatile boolean f9390b = false;

    /* renamed from: c */
    private volatile boolean f9391c = false;

    /* renamed from: a */
    public H m19868a() {
        if (this.f9389a != null) {
            return this.f9389a;
        }
        if (!this.f9390b) {
            throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
        } else if (this.f9391c) {
            throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
        } else {
            throw new IllegalStateException("Helper is null for some unknown reason");
        }
    }

    /* renamed from: b */
    public ConnectionSource m19865b() {
        return m19868a().getConnectionSource();
    }

    @Override // android.app.Service
    public void onCreate() {
        if (this.f9389a == null) {
            this.f9389a = m19867a(this);
            this.f9390b = true;
        }
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        m19866a((OrmLiteBaseService<H>) this.f9389a);
        this.f9391c = true;
    }

    /* renamed from: a */
    protected H m19867a(Context context) {
        return (H) OpenHelperManager.m19845a(context);
    }

    /* renamed from: a */
    protected void m19866a(H h) {
        OpenHelperManager.m19841b();
        this.f9389a = null;
    }
}
