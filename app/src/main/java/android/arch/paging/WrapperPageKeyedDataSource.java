package android.arch.paging;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

/* loaded from: classes.dex */
class WrapperPageKeyedDataSource<K, A, B> extends PageKeyedDataSource<K, B> {
    private final Function<List<A>, List<B>> mListFunction;
    private final PageKeyedDataSource<K, A> mSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WrapperPageKeyedDataSource(PageKeyedDataSource<K, A> pageKeyedDataSource, Function<List<A>, List<B>> function) {
        this.mSource = pageKeyedDataSource;
        this.mListFunction = function;
    }

    @Override // android.arch.paging.DataSource
    public void addInvalidatedCallback(@NonNull DataSource.InvalidatedCallback invalidatedCallback) {
        this.mSource.addInvalidatedCallback(invalidatedCallback);
    }

    @Override // android.arch.paging.DataSource
    public void removeInvalidatedCallback(@NonNull DataSource.InvalidatedCallback invalidatedCallback) {
        this.mSource.removeInvalidatedCallback(invalidatedCallback);
    }

    @Override // android.arch.paging.DataSource
    public void invalidate() {
        this.mSource.invalidate();
    }

    @Override // android.arch.paging.DataSource
    public boolean isInvalid() {
        return this.mSource.isInvalid();
    }

    @Override // android.arch.paging.PageKeyedDataSource
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<K> loadInitialParams, @NonNull final PageKeyedDataSource.LoadInitialCallback<K, B> loadInitialCallback) {
        this.mSource.loadInitial(loadInitialParams, new PageKeyedDataSource.LoadInitialCallback<K, A>() { // from class: android.arch.paging.WrapperPageKeyedDataSource.1
            @Override // android.arch.paging.PageKeyedDataSource.LoadInitialCallback
            public void onResult(@NonNull List<A> list, int i, int i2, @Nullable K k, @Nullable K k2) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), i, i2, k, k2);
            }

            @Override // android.arch.paging.PageKeyedDataSource.LoadInitialCallback
            public void onResult(@NonNull List<A> list, @Nullable K k, @Nullable K k2) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), k, k2);
            }
        });
    }

    @Override // android.arch.paging.PageKeyedDataSource
    public void loadBefore(@NonNull PageKeyedDataSource.LoadParams<K> loadParams, @NonNull final PageKeyedDataSource.LoadCallback<K, B> loadCallback) {
        this.mSource.loadBefore(loadParams, new PageKeyedDataSource.LoadCallback<K, A>() { // from class: android.arch.paging.WrapperPageKeyedDataSource.2
            @Override // android.arch.paging.PageKeyedDataSource.LoadCallback
            public void onResult(@NonNull List<A> list, @Nullable K k) {
                loadCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), k);
            }
        });
    }

    @Override // android.arch.paging.PageKeyedDataSource
    public void loadAfter(@NonNull PageKeyedDataSource.LoadParams<K> loadParams, @NonNull final PageKeyedDataSource.LoadCallback<K, B> loadCallback) {
        this.mSource.loadAfter(loadParams, new PageKeyedDataSource.LoadCallback<K, A>() { // from class: android.arch.paging.WrapperPageKeyedDataSource.3
            @Override // android.arch.paging.PageKeyedDataSource.LoadCallback
            public void onResult(@NonNull List<A> list, @Nullable K k) {
                loadCallback.onResult(DataSource.convert(WrapperPageKeyedDataSource.this.mListFunction, list), k);
            }
        });
    }
}
