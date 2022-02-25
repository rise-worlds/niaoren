package android.arch.paging;

import android.arch.paging.DataSource;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
@Deprecated
/* loaded from: classes.dex */
public abstract class LivePagedListProvider<Key, Value> extends DataSource.Factory<Key, Value> {
    @WorkerThread
    protected abstract DataSource<Key, Value> createDataSource();

    @Override // android.arch.paging.DataSource.Factory
    public DataSource<Key, Value> create() {
        return createDataSource();
    }
}
