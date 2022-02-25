package android.arch.paging;

import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public abstract class PagedList<T> extends AbstractList<T> {
    @NonNull
    final Executor mBackgroundThreadExecutor;
    @Nullable
    final BoundaryCallback<T> mBoundaryCallback;
    @NonNull
    final Config mConfig;
    @NonNull
    final Executor mMainThreadExecutor;
    @NonNull
    final PagedStorage<T> mStorage;
    int mLastLoad = 0;
    T mLastItem = null;
    private boolean mBoundaryCallbackBeginDeferred = false;
    private boolean mBoundaryCallbackEndDeferred = false;
    private int mLowestIndexAccessed = Integer.MAX_VALUE;
    private int mHighestIndexAccessed = Integer.MIN_VALUE;
    private final AtomicBoolean mDetached = new AtomicBoolean(false);
    private final ArrayList<WeakReference<Callback>> mCallbacks = new ArrayList<>();

    @MainThread
    /* loaded from: classes.dex */
    public static abstract class BoundaryCallback<T> {
        public void onItemAtEndLoaded(@NonNull T t) {
        }

        public void onItemAtFrontLoaded(@NonNull T t) {
        }

        public void onZeroItemsLoaded() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract void onChanged(int i, int i2);

        public abstract void onInserted(int i, int i2);

        public abstract void onRemoved(int i, int i2);
    }

    abstract void dispatchUpdatesSinceSnapshot(@NonNull PagedList<T> pagedList, @NonNull Callback callback);

    @NonNull
    public abstract DataSource<?, T> getDataSource();

    @Nullable
    public abstract Object getLastKey();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isContiguous();

    abstract void loadAroundInternal(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public PagedList(@NonNull PagedStorage<T> pagedStorage, @NonNull Executor executor, @NonNull Executor executor2, @Nullable BoundaryCallback<T> boundaryCallback, @NonNull Config config) {
        this.mStorage = pagedStorage;
        this.mMainThreadExecutor = executor;
        this.mBackgroundThreadExecutor = executor2;
        this.mBoundaryCallback = boundaryCallback;
        this.mConfig = config;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public static <K, T> PagedList<T> create(@NonNull DataSource<K, T> dataSource, @NonNull Executor executor, @NonNull Executor executor2, @Nullable BoundaryCallback<T> boundaryCallback, @NonNull Config config, @Nullable K k) {
        int i;
        if (dataSource.isContiguous() || !config.enablePlaceholders) {
            if (!dataSource.isContiguous()) {
                dataSource = ((PositionalDataSource) dataSource).wrapAsContiguousWithoutPlaceholders();
                i = k != null ? ((Integer) k).intValue() : -1;
            } else {
                i = -1;
            }
            return new ContiguousPagedList((ContiguousDataSource) dataSource, executor, executor2, boundaryCallback, config, k, i);
        }
        return new TiledPagedList((PositionalDataSource) dataSource, executor, executor2, boundaryCallback, config, k != null ? ((Integer) k).intValue() : 0);
    }

    /* loaded from: classes.dex */
    public static final class Builder<Key, Value> {
        private BoundaryCallback mBoundaryCallback;
        private final Config mConfig;
        private final DataSource<Key, Value> mDataSource;
        private Executor mFetchExecutor;
        private Key mInitialKey;
        private Executor mNotifyExecutor;

        public Builder(@NonNull DataSource<Key, Value> dataSource, @NonNull Config config) {
            if (dataSource == null) {
                throw new IllegalArgumentException("DataSource may not be null");
            } else if (config != null) {
                this.mDataSource = dataSource;
                this.mConfig = config;
            } else {
                throw new IllegalArgumentException("Config may not be null");
            }
        }

        public Builder(@NonNull DataSource<Key, Value> dataSource, int i) {
            this(dataSource, new Config.Builder().setPageSize(i).build());
        }

        @NonNull
        public Builder<Key, Value> setNotifyExecutor(@NonNull Executor executor) {
            this.mNotifyExecutor = executor;
            return this;
        }

        @NonNull
        public Builder<Key, Value> setFetchExecutor(@NonNull Executor executor) {
            this.mFetchExecutor = executor;
            return this;
        }

        @NonNull
        public Builder<Key, Value> setBoundaryCallback(@Nullable BoundaryCallback boundaryCallback) {
            this.mBoundaryCallback = boundaryCallback;
            return this;
        }

        @NonNull
        public Builder<Key, Value> setInitialKey(@Nullable Key key) {
            this.mInitialKey = key;
            return this;
        }

        @WorkerThread
        @NonNull
        public PagedList<Value> build() {
            Executor executor = this.mNotifyExecutor;
            if (executor != null) {
                Executor executor2 = this.mFetchExecutor;
                if (executor2 != null) {
                    return PagedList.create(this.mDataSource, executor, executor2, this.mBoundaryCallback, this.mConfig, this.mInitialKey);
                }
                throw new IllegalArgumentException("BackgroundThreadExecutor required");
            }
            throw new IllegalArgumentException("MainThreadExecutor required");
        }
    }

    @Override // java.util.AbstractList, java.util.List
    @Nullable
    public T get(int i) {
        T t = this.mStorage.get(i);
        if (t != null) {
            this.mLastItem = t;
        }
        return t;
    }

    public void loadAround(int i) {
        this.mLastLoad = getPositionOffset() + i;
        loadAroundInternal(i);
        this.mLowestIndexAccessed = Math.min(this.mLowestIndexAccessed, i);
        this.mHighestIndexAccessed = Math.max(this.mHighestIndexAccessed, i);
        tryDispatchBoundaryCallbacks(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AnyThread
    public void deferBoundaryCallbacks(final boolean z, final boolean z2, final boolean z3) {
        if (this.mBoundaryCallback != null) {
            if (this.mLowestIndexAccessed == Integer.MAX_VALUE) {
                this.mLowestIndexAccessed = this.mStorage.size();
            }
            if (this.mHighestIndexAccessed == Integer.MIN_VALUE) {
                this.mHighestIndexAccessed = 0;
            }
            if (z || z2 || z3) {
                this.mMainThreadExecutor.execute(new Runnable() { // from class: android.arch.paging.PagedList.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z) {
                            PagedList.this.mBoundaryCallback.onZeroItemsLoaded();
                        }
                        if (z2) {
                            PagedList.this.mBoundaryCallbackBeginDeferred = true;
                        }
                        if (z3) {
                            PagedList.this.mBoundaryCallbackEndDeferred = true;
                        }
                        PagedList.this.tryDispatchBoundaryCallbacks(false);
                    }
                });
                return;
            }
            return;
        }
        throw new IllegalStateException("Can't defer BoundaryCallback, no instance");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryDispatchBoundaryCallbacks(boolean z) {
        final boolean z2 = true;
        final boolean z3 = this.mBoundaryCallbackBeginDeferred && this.mLowestIndexAccessed <= this.mConfig.prefetchDistance;
        if (!this.mBoundaryCallbackEndDeferred || this.mHighestIndexAccessed < (size() - 1) - this.mConfig.prefetchDistance) {
            z2 = false;
        }
        if (z3 || z2) {
            if (z3) {
                this.mBoundaryCallbackBeginDeferred = false;
            }
            if (z2) {
                this.mBoundaryCallbackEndDeferred = false;
            }
            if (z) {
                this.mMainThreadExecutor.execute(new Runnable() { // from class: android.arch.paging.PagedList.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PagedList.this.dispatchBoundaryCallbacks(z3, z2);
                    }
                });
            } else {
                dispatchBoundaryCallbacks(z3, z2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchBoundaryCallbacks(boolean z, boolean z2) {
        if (z) {
            this.mBoundaryCallback.onItemAtFrontLoaded(this.mStorage.getFirstLoadedItem());
        }
        if (z2) {
            this.mBoundaryCallback.onItemAtEndLoaded(this.mStorage.getLastLoadedItem());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void offsetBoundaryAccessIndices(int i) {
        this.mLowestIndexAccessed += i;
        this.mHighestIndexAccessed += i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mStorage.size();
    }

    public boolean isImmutable() {
        return isDetached();
    }

    @NonNull
    public List<T> snapshot() {
        return isImmutable() ? this : new SnapshotPagedList(this);
    }

    @NonNull
    public Config getConfig() {
        return this.mConfig;
    }

    public boolean isDetached() {
        return this.mDetached.get();
    }

    public void detach() {
        this.mDetached.set(true);
    }

    public int getPositionOffset() {
        return this.mStorage.getPositionOffset();
    }

    public void addWeakCallback(@Nullable List<T> list, @NonNull Callback callback) {
        if (!(list == null || list == this)) {
            if (!list.isEmpty()) {
                dispatchUpdatesSinceSnapshot((PagedList) list, callback);
            } else if (!this.mStorage.isEmpty()) {
                callback.onInserted(0, this.mStorage.size());
            }
        }
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            if (this.mCallbacks.get(size).get() == null) {
                this.mCallbacks.remove(size);
            }
        }
        this.mCallbacks.add(new WeakReference<>(callback));
    }

    public void removeWeakCallback(@NonNull Callback callback) {
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            Callback callback2 = this.mCallbacks.get(size).get();
            if (callback2 == null || callback2 == callback) {
                this.mCallbacks.remove(size);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyInserted(int i, int i2) {
        if (i2 != 0) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                Callback callback = this.mCallbacks.get(size).get();
                if (callback != null) {
                    callback.onInserted(i, i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyChanged(int i, int i2) {
        if (i2 != 0) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                Callback callback = this.mCallbacks.get(size).get();
                if (callback != null) {
                    callback.onChanged(i, i2);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Config {
        public final boolean enablePlaceholders;
        public final int initialLoadSizeHint;
        public final int pageSize;
        public final int prefetchDistance;

        private Config(int i, int i2, boolean z, int i3) {
            this.pageSize = i;
            this.prefetchDistance = i2;
            this.enablePlaceholders = z;
            this.initialLoadSizeHint = i3;
        }

        /* loaded from: classes.dex */
        public static final class Builder {
            private int mPageSize = -1;
            private int mPrefetchDistance = -1;
            private int mInitialLoadSizeHint = -1;
            private boolean mEnablePlaceholders = true;

            public Builder setPageSize(int i) {
                this.mPageSize = i;
                return this;
            }

            public Builder setPrefetchDistance(int i) {
                this.mPrefetchDistance = i;
                return this;
            }

            public Builder setEnablePlaceholders(boolean z) {
                this.mEnablePlaceholders = z;
                return this;
            }

            public Builder setInitialLoadSizeHint(int i) {
                this.mInitialLoadSizeHint = i;
                return this;
            }

            public Config build() {
                int i = this.mPageSize;
                if (i >= 1) {
                    if (this.mPrefetchDistance < 0) {
                        this.mPrefetchDistance = i;
                    }
                    if (this.mInitialLoadSizeHint < 0) {
                        this.mInitialLoadSizeHint = this.mPageSize * 3;
                    }
                    if (this.mEnablePlaceholders || this.mPrefetchDistance != 0) {
                        return new Config(this.mPageSize, this.mPrefetchDistance, this.mEnablePlaceholders, this.mInitialLoadSizeHint);
                    }
                    throw new IllegalArgumentException("Placeholders and prefetch are the only ways to trigger loading of more data in the PagedList, so either placeholders must be enabled, or prefetch distance must be > 0.");
                }
                throw new IllegalArgumentException("Page size must be a positive number");
            }
        }
    }
}
