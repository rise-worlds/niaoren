package com.j256.ormlite.android.apptools;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import p110z1.ConnectionSource;

/* loaded from: classes.dex */
public abstract class OrmLiteBaseListActivity<H extends OrmLiteSqliteOpenHelper> extends ListActivity {

    /* renamed from: a */
    private volatile H f9386a;

    /* renamed from: b */
    private volatile boolean f9387b = false;

    /* renamed from: c */
    private volatile boolean f9388c = false;

    /* renamed from: a */
    public H m19872a() {
        if (this.f9386a != null) {
            return this.f9386a;
        }
        if (!this.f9387b) {
            throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
        } else if (this.f9388c) {
            throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
        } else {
            throw new IllegalStateException("Helper is null for some unknown reason");
        }
    }

    /* renamed from: b */
    public ConnectionSource m19869b() {
        return m19872a().getConnectionSource();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (this.f9386a == null) {
            this.f9386a = m19871a(this);
            this.f9387b = true;
        }
        super.onCreate(bundle);
    }

    @Override // android.app.ListActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        m19870a((OrmLiteBaseListActivity<H>) this.f9386a);
        this.f9388c = true;
    }

    /* renamed from: a */
    protected H m19871a(Context context) {
        return (H) OpenHelperManager.m19845a(context);
    }

    /* renamed from: a */
    protected void m19870a(H h) {
        OpenHelperManager.m19841b();
        this.f9386a = null;
    }
}
