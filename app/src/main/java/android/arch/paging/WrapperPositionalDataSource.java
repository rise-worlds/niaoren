package android.arch.paging;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import java.util.List;

/* loaded from: classes.dex */
class WrapperPositionalDataSource<A, B> extends PositionalDataSource<B> {
    private final Function<List<A>, List<B>> mListFunction;
    private final PositionalDataSource<A> mSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WrapperPositionalDataSource(PositionalDataSource<A> positionalDataSource, Function<List<A>, List<B>> function) {
        this.mSource = positionalDataSource;
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

    @Override // android.arch.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull final PositionalDataSource.LoadInitialCallback<B> loadInitialCallback) {
        this.mSource.loadInitial(loadInitialParams, new PositionalDataSource.LoadInitialCallback<A>() { // from class: android.arch.paging.WrapperPositionalDataSource.1
            @Override // android.arch.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(@NonNull List<A> list, int i, int i2) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPositionalDataSource.this.mListFunction, list), i, i2);
            }

            @Override // android.arch.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(@NonNull List<A> list, int i) {
                loadInitialCallback.onResult(DataSource.convert(WrapperPositionalDataSource.this.mListFunction, list), i);
            }
        });
    }

    @Override // android.arch.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull final PositionalDataSource.LoadRangeCallback<B> loadRangeCallback) {
        this.mSource.loadRange(loadRangeParams, new PositionalDataSource.LoadRangeCallback<A>() { // from class: android.arch.paging.WrapperPositionalDataSource.2
            @Override // android.arch.paging.PositionalDataSource.LoadRangeCallback
            public void onResult(@NonNull List<A> list) {
                loadRangeCallback.onResult(DataSource.convert(WrapperPositionalDataSource.this.mListFunction, list));
            }
        });
    }
}
