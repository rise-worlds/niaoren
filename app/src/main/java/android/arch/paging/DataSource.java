package android.arch.paging;

import android.arch.core.util.Function;
import android.arch.paging.PageResult;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public abstract class DataSource<Key, Value> {
    private AtomicBoolean mInvalid = new AtomicBoolean(false);
    private CopyOnWriteArrayList<InvalidatedCallback> mOnInvalidatedCallbacks = new CopyOnWriteArrayList<>();

    /* loaded from: classes.dex */
    public interface InvalidatedCallback {
        @AnyThread
        void onInvalidated();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isContiguous();

    @NonNull
    public abstract <ToValue> DataSource<Key, ToValue> map(@NonNull Function<Value, ToValue> function);

    @NonNull
    public abstract <ToValue> DataSource<Key, ToValue> mapByPage(@NonNull Function<List<Value>, List<ToValue>> function);

    /* loaded from: classes.dex */
    public static abstract class Factory<Key, Value> {
        public abstract DataSource<Key, Value> create();

        @NonNull
        public <ToValue> Factory<Key, ToValue> map(@NonNull Function<Value, ToValue> function) {
            return mapByPage(DataSource.createListFunction(function));
        }

        @NonNull
        public <ToValue> Factory<Key, ToValue> mapByPage(@NonNull final Function<List<Value>, List<ToValue>> function) {
            return new Factory<Key, ToValue>() { // from class: android.arch.paging.DataSource.Factory.1
                @Override // android.arch.paging.DataSource.Factory
                public DataSource<Key, ToValue> create() {
                    return Factory.this.create().mapByPage(function);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static <X, Y> Function<List<X>, List<Y>> createListFunction(@NonNull final Function<X, Y> function) {
        return new Function<List<X>, List<Y>>() { // from class: android.arch.paging.DataSource.1
            public List<Y> apply(@NonNull List<X> list) {
                ArrayList arrayList = new ArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(Function.this.apply(list.get(i)));
                }
                return arrayList;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <A, B> List<B> convert(Function<List<A>, List<B>> function, List<A> list) {
        List<B> apply = function.apply(list);
        if (apply.size() == list.size()) {
            return apply;
        }
        throw new IllegalStateException("Invalid Function " + function + " changed return size. This is not supported.");
    }

    /* loaded from: classes.dex */
    static class LoadCallbackHelper<T> {
        private final DataSource mDataSource;
        private Executor mPostExecutor;
        private final PageResult.Receiver<T> mReceiver;
        final int mResultType;
        private final Object mSignalLock = new Object();
        private boolean mHasSignalled = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void validateInitialLoadParams(@NonNull List<?> list, int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Position must be non-negative");
            } else if (list.size() + i > i2) {
                throw new IllegalArgumentException("List size + position too large, last item in list beyond totalCount.");
            } else if (list.size() == 0 && i2 > 0) {
                throw new IllegalArgumentException("Initial result cannot be empty if items are present in data set.");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LoadCallbackHelper(@NonNull DataSource dataSource, int i, @Nullable Executor executor, @NonNull PageResult.Receiver<T> receiver) {
            this.mPostExecutor = null;
            this.mDataSource = dataSource;
            this.mResultType = i;
            this.mPostExecutor = executor;
            this.mReceiver = receiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setPostExecutor(Executor executor) {
            synchronized (this.mSignalLock) {
                this.mPostExecutor = executor;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean dispatchInvalidResultIfInvalid() {
            if (!this.mDataSource.isInvalid()) {
                return false;
            }
            dispatchResultToReceiver(PageResult.getInvalidResult());
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void dispatchResultToReceiver(@NonNull final PageResult<T> pageResult) {
            Executor executor;
            synchronized (this.mSignalLock) {
                if (!this.mHasSignalled) {
                    this.mHasSignalled = true;
                    executor = this.mPostExecutor;
                } else {
                    throw new IllegalStateException("callback.onResult already called, cannot call again.");
                }
            }
            if (executor != null) {
                executor.execute(new Runnable() { // from class: android.arch.paging.DataSource.LoadCallbackHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LoadCallbackHelper.this.mReceiver.onPageResult(LoadCallbackHelper.this.mResultType, pageResult);
                    }
                });
            } else {
                this.mReceiver.onPageResult(this.mResultType, pageResult);
            }
        }
    }

    @AnyThread
    public void addInvalidatedCallback(@NonNull InvalidatedCallback invalidatedCallback) {
        this.mOnInvalidatedCallbacks.add(invalidatedCallback);
    }

    @AnyThread
    public void removeInvalidatedCallback(@NonNull InvalidatedCallback invalidatedCallback) {
        this.mOnInvalidatedCallbacks.remove(invalidatedCallback);
    }

    @AnyThread
    public void invalidate() {
        if (this.mInvalid.compareAndSet(false, true)) {
            Iterator<InvalidatedCallback> it = this.mOnInvalidatedCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onInvalidated();
            }
        }
    }

    @WorkerThread
    public boolean isInvalid() {
        return this.mInvalid.get();
    }
}
