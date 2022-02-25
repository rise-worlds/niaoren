package android.arch.paging;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
class SnapshotPagedList<T> extends PagedList<T> {
    private final boolean mContiguous;
    private final DataSource<?, T> mDataSource;
    private final Object mLastKey;

    @Override // android.arch.paging.PagedList
    void dispatchUpdatesSinceSnapshot(@NonNull PagedList<T> pagedList, @NonNull PagedList.Callback callback) {
    }

    @Override // android.arch.paging.PagedList
    public boolean isDetached() {
        return true;
    }

    @Override // android.arch.paging.PagedList
    public boolean isImmutable() {
        return true;
    }

    @Override // android.arch.paging.PagedList
    void loadAroundInternal(int i) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnapshotPagedList(@NonNull PagedList<T> pagedList) {
        super(pagedList.mStorage.snapshot(), pagedList.mMainThreadExecutor, pagedList.mBackgroundThreadExecutor, null, pagedList.mConfig);
        this.mDataSource = pagedList.getDataSource();
        this.mContiguous = pagedList.isContiguous();
        this.mLastKey = pagedList.getLastKey();
    }

    @Override // android.arch.paging.PagedList
    boolean isContiguous() {
        return this.mContiguous;
    }

    @Override // android.arch.paging.PagedList
    @Nullable
    public Object getLastKey() {
        return this.mLastKey;
    }

    @Override // android.arch.paging.PagedList
    @NonNull
    public DataSource<?, T> getDataSource() {
        return this.mDataSource;
    }
}
