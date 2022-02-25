package android.arch.paging;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class LivePagedListBuilder<Key, Value> {
    private PagedList.BoundaryCallback mBoundaryCallback;
    private PagedList.Config mConfig;
    private DataSource.Factory<Key, Value> mDataSourceFactory;
    private Executor mFetchExecutor;
    private Key mInitialLoadKey;

    public LivePagedListBuilder(@NonNull DataSource.Factory<Key, Value> factory, @NonNull PagedList.Config config) {
        this.mFetchExecutor = ArchTaskExecutor.getIOThreadExecutor();
        if (config == null) {
            throw new IllegalArgumentException("PagedList.Config must be provided");
        } else if (factory != null) {
            this.mDataSourceFactory = factory;
            this.mConfig = config;
        } else {
            throw new IllegalArgumentException("DataSource.Factory must be provided");
        }
    }

    public LivePagedListBuilder(@NonNull DataSource.Factory<Key, Value> factory, int i) {
        this(factory, new PagedList.Config.Builder().setPageSize(i).build());
    }

    @NonNull
    public LivePagedListBuilder<Key, Value> setInitialLoadKey(@Nullable Key key) {
        this.mInitialLoadKey = key;
        return this;
    }

    @NonNull
    public LivePagedListBuilder<Key, Value> setBoundaryCallback(@Nullable PagedList.BoundaryCallback<Value> boundaryCallback) {
        this.mBoundaryCallback = boundaryCallback;
        return this;
    }

    @NonNull
    public LivePagedListBuilder<Key, Value> setFetchExecutor(@NonNull Executor executor) {
        this.mFetchExecutor = executor;
        return this;
    }

    @NonNull
    public LiveData<PagedList<Value>> build() {
        return create(this.mInitialLoadKey, this.mConfig, this.mBoundaryCallback, this.mDataSourceFactory, ArchTaskExecutor.getMainThreadExecutor(), this.mFetchExecutor);
    }

    @NonNull
    @AnyThread
    private static <Key, Value> LiveData<PagedList<Value>> create(@Nullable final Key key, @NonNull final PagedList.Config config, @Nullable final PagedList.BoundaryCallback boundaryCallback, @NonNull final DataSource.Factory<Key, Value> factory, @NonNull final Executor executor, @NonNull final Executor executor2) {
        return new ComputableLiveData<PagedList<Value>>(executor2) { // from class: android.arch.paging.LivePagedListBuilder.1
            private final DataSource.InvalidatedCallback mCallback = new DataSource.InvalidatedCallback() { // from class: android.arch.paging.LivePagedListBuilder.1.1
                @Override // android.arch.paging.DataSource.InvalidatedCallback
                public void onInvalidated() {
                    invalidate();
                }
            };
            @Nullable
            private DataSource<Key, Value> mDataSource;
            @Nullable
            private PagedList<Value> mList;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.arch.lifecycle.ComputableLiveData
            public PagedList<Value> compute() {
                Object obj = key;
                PagedList<Value> pagedList = this.mList;
                if (pagedList != null) {
                    obj = pagedList.getLastKey();
                }
                do {
                    DataSource<Key, Value> dataSource = this.mDataSource;
                    if (dataSource != null) {
                        dataSource.removeInvalidatedCallback(this.mCallback);
                    }
                    this.mDataSource = factory.create();
                    this.mDataSource.addInvalidatedCallback(this.mCallback);
                    this.mList = new PagedList.Builder(this.mDataSource, config).setNotifyExecutor(executor).setFetchExecutor(executor2).setBoundaryCallback(boundaryCallback).setInitialKey(obj).build();
                } while (this.mList.isDetached());
                return this.mList;
            }
        }.getLiveData();
    }
}
