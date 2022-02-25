package android.arch.paging;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class ListDataSource<T> extends PositionalDataSource<T> {
    private final List<T> mList;

    public ListDataSource(List<T> list) {
        this.mList = new ArrayList(list);
    }

    @Override // android.arch.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        int size = this.mList.size();
        int computeInitialLoadPosition = computeInitialLoadPosition(loadInitialParams, size);
        loadInitialCallback.onResult(this.mList.subList(computeInitialLoadPosition, computeInitialLoadSize(loadInitialParams, computeInitialLoadPosition, size) + computeInitialLoadPosition), computeInitialLoadPosition, size);
    }

    @Override // android.arch.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        loadRangeCallback.onResult(this.mList.subList(loadRangeParams.startPosition, loadRangeParams.startPosition + loadRangeParams.loadSize));
    }
}
