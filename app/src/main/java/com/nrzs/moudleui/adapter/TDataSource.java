package com.nrzs.moudleui.adapter;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import com.nrzs.moudleui.adapter.TPageCallback;

/* renamed from: com.nrzs.moudleui.adapter.a */
/* loaded from: classes2.dex */
public class TDataSource<T> extends PositionalDataSource<T> {

    /* renamed from: a */
    private TPageCallback<T> f11281a;

    public TDataSource(TPageCallback.AbstractC2210a<T> aVar) {
        this.f11281a = aVar.m18439a();
    }

    @Override // android.arch.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        loadInitialCallback.onResult(this.f11281a.m18440a(loadInitialParams.requestedStartPosition, loadInitialParams.requestedLoadSize, loadInitialParams.pageSize), 0);
    }

    @Override // android.arch.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        loadRangeCallback.onResult(this.f11281a.m18441a(loadRangeParams.startPosition, loadRangeParams.loadSize));
    }

    @Override // android.arch.paging.DataSource
    public void invalidate() {
        super.invalidate();
    }
}
