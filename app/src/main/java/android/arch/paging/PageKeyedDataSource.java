package android.arch.paging;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.paging.PageResult;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class PageKeyedDataSource<Key, Value> extends ContiguousDataSource<Key, Value> {
    private final Object mKeyLock = new Object();
    @GuardedBy("mKeyLock")
    @Nullable
    private Key mNextKey = null;
    @GuardedBy("mKeyLock")
    @Nullable
    private Key mPreviousKey = null;

    /* loaded from: classes.dex */
    public static abstract class LoadCallback<Key, Value> {
        public abstract void onResult(@NonNull List<Value> list, @Nullable Key key);
    }

    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<Key, Value> {
        public abstract void onResult(@NonNull List<Value> list, int i, int i2, @Nullable Key key, @Nullable Key key2);

        public abstract void onResult(@NonNull List<Value> list, @Nullable Key key, @Nullable Key key2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.arch.paging.ContiguousDataSource
    @Nullable
    public final Key getKey(int i, Value value) {
        return null;
    }

    public abstract void loadAfter(@NonNull LoadParams<Key> loadParams, @NonNull LoadCallback<Key, Value> loadCallback);

    public abstract void loadBefore(@NonNull LoadParams<Key> loadParams, @NonNull LoadCallback<Key, Value> loadCallback);

    public abstract void loadInitial(@NonNull LoadInitialParams<Key> loadInitialParams, @NonNull LoadInitialCallback<Key, Value> loadInitialCallback);

    /* JADX INFO: Access modifiers changed from: private */
    public void initKeys(@Nullable Key key, @Nullable Key key2) {
        synchronized (this.mKeyLock) {
            this.mPreviousKey = key;
            this.mNextKey = key2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviousKey(@Nullable Key key) {
        synchronized (this.mKeyLock) {
            this.mPreviousKey = key;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextKey(@Nullable Key key) {
        synchronized (this.mKeyLock) {
            this.mNextKey = key;
        }
    }

    @Nullable
    private Key getPreviousKey() {
        Key key;
        synchronized (this.mKeyLock) {
            key = this.mPreviousKey;
        }
        return key;
    }

    @Nullable
    private Key getNextKey() {
        Key key;
        synchronized (this.mKeyLock) {
            key = this.mNextKey;
        }
        return key;
    }

    /* loaded from: classes.dex */
    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;

        public LoadInitialParams(int i, boolean z) {
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    /* loaded from: classes.dex */
    public static class LoadParams<Key> {
        public final Key key;
        public final int requestedLoadSize;

        public LoadParams(Key key, int i) {
            this.key = key;
            this.requestedLoadSize = i;
        }
    }

    /* loaded from: classes.dex */
    static class LoadInitialCallbackImpl<Key, Value> extends LoadInitialCallback<Key, Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final boolean mCountingEnabled;
        private final PageKeyedDataSource<Key, Value> mDataSource;

        LoadInitialCallbackImpl(@NonNull PageKeyedDataSource<Key, Value> pageKeyedDataSource, boolean z, @NonNull PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(pageKeyedDataSource, 0, null, receiver);
            this.mDataSource = pageKeyedDataSource;
            this.mCountingEnabled = z;
        }

        @Override // android.arch.paging.PageKeyedDataSource.LoadInitialCallback
        public void onResult(@NonNull List<Value> list, int i, int i2, @Nullable Key key, @Nullable Key key2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                this.mDataSource.initKeys(key, key2);
                int size = (i2 - i) - list.size();
                if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i, size, 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i));
                }
            }
        }

        @Override // android.arch.paging.PageKeyedDataSource.LoadInitialCallback
        public void onResult(@NonNull List<Value> list, @Nullable Key key, @Nullable Key key2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mDataSource.initKeys(key, key2);
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, 0));
            }
        }
    }

    /* loaded from: classes.dex */
    static class LoadCallbackImpl<Key, Value> extends LoadCallback<Key, Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final PageKeyedDataSource<Key, Value> mDataSource;

        LoadCallbackImpl(@NonNull PageKeyedDataSource<Key, Value> pageKeyedDataSource, int i, @Nullable Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(pageKeyedDataSource, i, executor, receiver);
            this.mDataSource = pageKeyedDataSource;
        }

        @Override // android.arch.paging.PageKeyedDataSource.LoadCallback
        public void onResult(@NonNull List<Value> list, @Nullable Key key) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                if (this.mCallbackHelper.mResultType == 1) {
                    this.mDataSource.setNextKey(key);
                } else {
                    this.mDataSource.setPreviousKey(key);
                }
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, 0));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.arch.paging.ContiguousDataSource
    public final void dispatchLoadInitial(@Nullable Key key, int i, int i2, boolean z, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, receiver);
        loadInitial(new LoadInitialParams<>(i, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.arch.paging.ContiguousDataSource
    public final void dispatchLoadAfter(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        Key nextKey = getNextKey();
        if (nextKey != null) {
            loadAfter(new LoadParams<>(nextKey, i2), new LoadCallbackImpl(this, 1, executor, receiver));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.arch.paging.ContiguousDataSource
    public final void dispatchLoadBefore(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        Key previousKey = getPreviousKey();
        if (previousKey != null) {
            loadBefore(new LoadParams<>(previousKey, i2), new LoadCallbackImpl(this, 2, executor, receiver));
        }
    }

    @Override // android.arch.paging.DataSource
    @NonNull
    public final <ToValue> PageKeyedDataSource<Key, ToValue> mapByPage(@NonNull Function<List<Value>, List<ToValue>> function) {
        return new WrapperPageKeyedDataSource(this, function);
    }

    @Override // android.arch.paging.DataSource
    @NonNull
    public final <ToValue> PageKeyedDataSource<Key, ToValue> map(@NonNull Function<Value, ToValue> function) {
        return mapByPage((Function) createListFunction(function));
    }
}
