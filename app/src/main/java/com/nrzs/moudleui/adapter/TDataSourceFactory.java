package com.nrzs.moudleui.adapter;

import android.arch.paging.DataSource;
import com.nrzs.moudleui.adapter.TPageCallback;

/* renamed from: com.nrzs.moudleui.adapter.b */
/* loaded from: classes2.dex */
public class TDataSourceFactory<T> extends DataSource.Factory<Integer, T> {

    /* renamed from: a */
    private TPageCallback.AbstractC2210a<T> f11282a;

    public TDataSourceFactory(TPageCallback.AbstractC2210a<T> aVar) {
        this.f11282a = aVar;
    }

    @Override // android.arch.paging.DataSource.Factory
    public DataSource<Integer, T> create() {
        return new TDataSource(this.f11282a);
    }
}
