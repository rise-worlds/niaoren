package android.arch.paging;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import java.util.IdentityHashMap;
import java.util.List;

/* loaded from: classes.dex */
class WrapperItemKeyedDataSource<K, A, B> extends ItemKeyedDataSource<K, B> {
    private final IdentityHashMap<B, K> mKeyMap = new IdentityHashMap<>();
    private final Function<List<A>, List<B>> mListFunction;
    private final ItemKeyedDataSource<K, A> mSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WrapperItemKeyedDataSource(ItemKeyedDataSource<K, A> itemKeyedDataSource, Function<List<A>, List<B>> function) {
        this.mSource = itemKeyedDataSource;
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

    /* JADX INFO: Access modifiers changed from: private */
    public List<B> convertWithStashedKeys(List<A> list) {
        List<B> convert = convert(this.mListFunction, list);
        synchronized (this.mKeyMap) {
            for (int i = 0; i < convert.size(); i++) {
                this.mKeyMap.put(convert.get(i), this.mSource.getKey(list.get(i)));
            }
        }
        return convert;
    }

    @Override // android.arch.paging.ItemKeyedDataSource
    public void loadInitial(@NonNull ItemKeyedDataSource.LoadInitialParams<K> loadInitialParams, @NonNull final ItemKeyedDataSource.LoadInitialCallback<B> loadInitialCallback) {
        this.mSource.loadInitial(loadInitialParams, new ItemKeyedDataSource.LoadInitialCallback<A>() { // from class: android.arch.paging.WrapperItemKeyedDataSource.1
            @Override // android.arch.paging.ItemKeyedDataSource.LoadInitialCallback
            public void onResult(@NonNull List<A> list, int i, int i2) {
                loadInitialCallback.onResult(WrapperItemKeyedDataSource.this.convertWithStashedKeys(list), i, i2);
            }

            @Override // android.arch.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NonNull List<A> list) {
                loadInitialCallback.onResult(WrapperItemKeyedDataSource.this.convertWithStashedKeys(list));
            }
        });
    }

    @Override // android.arch.paging.ItemKeyedDataSource
    public void loadAfter(@NonNull ItemKeyedDataSource.LoadParams<K> loadParams, @NonNull final ItemKeyedDataSource.LoadCallback<B> loadCallback) {
        this.mSource.loadAfter(loadParams, new ItemKeyedDataSource.LoadCallback<A>() { // from class: android.arch.paging.WrapperItemKeyedDataSource.2
            @Override // android.arch.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NonNull List<A> list) {
                loadCallback.onResult(WrapperItemKeyedDataSource.this.convertWithStashedKeys(list));
            }
        });
    }

    @Override // android.arch.paging.ItemKeyedDataSource
    public void loadBefore(@NonNull ItemKeyedDataSource.LoadParams<K> loadParams, @NonNull final ItemKeyedDataSource.LoadCallback<B> loadCallback) {
        this.mSource.loadBefore(loadParams, new ItemKeyedDataSource.LoadCallback<A>() { // from class: android.arch.paging.WrapperItemKeyedDataSource.3
            @Override // android.arch.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NonNull List<A> list) {
                loadCallback.onResult(WrapperItemKeyedDataSource.this.convertWithStashedKeys(list));
            }
        });
    }

    @Override // android.arch.paging.ItemKeyedDataSource
    @NonNull
    public K getKey(@NonNull B b) {
        K k;
        synchronized (this.mKeyMap) {
            k = this.mKeyMap.get(b);
        }
        return k;
    }
}
