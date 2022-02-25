package android.arch.paging;

import android.arch.paging.PageResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class ContiguousDataSource<Key, Value> extends DataSource<Key, Value> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dispatchLoadAfter(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dispatchLoadBefore(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dispatchLoadInitial(@Nullable Key key, int i, int i2, boolean z, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Key getKey(int i, Value value);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.arch.paging.DataSource
    public boolean isContiguous() {
        return true;
    }
}
