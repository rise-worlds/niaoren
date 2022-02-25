package android.arch.paging;

import android.arch.paging.PageResult;
import android.arch.paging.PagedList;
import android.arch.paging.PagedStorage;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class TiledPagedList<T> extends PagedList<T> implements PagedStorage.Callback {
    private final PositionalDataSource<T> mDataSource;
    private PageResult.Receiver<T> mReceiver = new PageResult.Receiver<T>() { // from class: android.arch.paging.TiledPagedList.1
        @Override // android.arch.paging.PageResult.Receiver
        @AnyThread
        public void onPageResult(int i, @NonNull PageResult<T> pageResult) {
            if (pageResult.isInvalid()) {
                TiledPagedList.this.detach();
            } else if (!TiledPagedList.this.isDetached()) {
                if (i == 0 || i == 3) {
                    if (TiledPagedList.this.mStorage.getPageCount() == 0) {
                        TiledPagedList.this.mStorage.initAndSplit(pageResult.leadingNulls, pageResult.page, pageResult.trailingNulls, pageResult.positionOffset, TiledPagedList.this.mConfig.pageSize, TiledPagedList.this);
                    } else {
                        TiledPagedList.this.mStorage.insertPage(pageResult.positionOffset, pageResult.page, TiledPagedList.this);
                    }
                    if (TiledPagedList.this.mBoundaryCallback != null) {
                        boolean z = true;
                        boolean z2 = TiledPagedList.this.mStorage.size() == 0;
                        boolean z3 = !z2 && pageResult.leadingNulls == 0 && pageResult.positionOffset == 0;
                        int size = TiledPagedList.this.size();
                        if (z2 || (!(i == 0 && pageResult.trailingNulls == 0) && (i != 3 || pageResult.positionOffset + TiledPagedList.this.mConfig.pageSize < size))) {
                            z = false;
                        }
                        TiledPagedList.this.deferBoundaryCallbacks(z2, z3, z);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("unexpected resultType" + i);
            }
        }
    };

    @Override // android.arch.paging.PagedList
    boolean isContiguous() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public TiledPagedList(@NonNull PositionalDataSource<T> positionalDataSource, @NonNull Executor executor, @NonNull Executor executor2, @Nullable PagedList.BoundaryCallback<T> boundaryCallback, @NonNull PagedList.Config config, int i) {
        super(new PagedStorage(), executor, executor2, boundaryCallback, config);
        this.mDataSource = positionalDataSource;
        int i2 = this.mConfig.pageSize;
        this.mLastLoad = i;
        if (this.mDataSource.isInvalid()) {
            detach();
            return;
        }
        int max = Math.max(Math.round(this.mConfig.initialLoadSizeHint / i2), 2) * i2;
        this.mDataSource.dispatchLoadInitial(true, Math.max(0, Math.round((i - (max / 2)) / i2) * i2), max, i2, this.mMainThreadExecutor, this.mReceiver);
    }

    @Override // android.arch.paging.PagedList
    @NonNull
    public DataSource<?, T> getDataSource() {
        return this.mDataSource;
    }

    @Override // android.arch.paging.PagedList
    @Nullable
    public Object getLastKey() {
        return Integer.valueOf(this.mLastLoad);
    }

    @Override // android.arch.paging.PagedList
    protected void dispatchUpdatesSinceSnapshot(@NonNull PagedList<T> pagedList, @NonNull PagedList.Callback callback) {
        PagedStorage<T> pagedStorage = pagedList.mStorage;
        if (pagedStorage.isEmpty() || this.mStorage.size() != pagedStorage.size()) {
            throw new IllegalArgumentException("Invalid snapshot provided - doesn't appear to be a snapshot of this PagedList");
        }
        int i = this.mConfig.pageSize;
        int leadingNullCount = this.mStorage.getLeadingNullCount() / i;
        int pageCount = this.mStorage.getPageCount();
        int i2 = 0;
        while (i2 < pageCount) {
            int i3 = i2 + leadingNullCount;
            int i4 = 0;
            while (i4 < this.mStorage.getPageCount()) {
                int i5 = i3 + i4;
                if (!this.mStorage.hasPage(i, i5) || pagedStorage.hasPage(i, i5)) {
                    break;
                }
                i4++;
            }
            if (i4 > 0) {
                callback.onChanged(i3 * i, i * i4);
                i2 += i4 - 1;
            }
            i2++;
        }
    }

    @Override // android.arch.paging.PagedList
    protected void loadAroundInternal(int i) {
        this.mStorage.allocatePlaceholders(i, this.mConfig.prefetchDistance, this.mConfig.pageSize, this);
    }

    @Override // android.arch.paging.PagedStorage.Callback
    public void onInitialized(int i) {
        notifyInserted(0, i);
    }

    @Override // android.arch.paging.PagedStorage.Callback
    public void onPagePrepended(int i, int i2, int i3) {
        throw new IllegalStateException("Contiguous callback on TiledPagedList");
    }

    @Override // android.arch.paging.PagedStorage.Callback
    public void onPageAppended(int i, int i2, int i3) {
        throw new IllegalStateException("Contiguous callback on TiledPagedList");
    }

    @Override // android.arch.paging.PagedStorage.Callback
    public void onPagePlaceholderInserted(final int i) {
        this.mBackgroundThreadExecutor.execute(new Runnable() { // from class: android.arch.paging.TiledPagedList.2
            @Override // java.lang.Runnable
            public void run() {
                if (!TiledPagedList.this.isDetached()) {
                    int i2 = TiledPagedList.this.mConfig.pageSize;
                    if (TiledPagedList.this.mDataSource.isInvalid()) {
                        TiledPagedList.this.detach();
                        return;
                    }
                    int i3 = i * i2;
                    TiledPagedList.this.mDataSource.dispatchLoadRange(3, i3, Math.min(i2, TiledPagedList.this.mStorage.size() - i3), TiledPagedList.this.mMainThreadExecutor, TiledPagedList.this.mReceiver);
                }
            }
        });
    }

    @Override // android.arch.paging.PagedStorage.Callback
    public void onPageInserted(int i, int i2) {
        notifyChanged(i, i2);
    }
}
