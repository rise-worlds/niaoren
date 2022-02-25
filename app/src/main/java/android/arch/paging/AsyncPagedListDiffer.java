package android.arch.paging;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p006v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.p006v7.util.AdapterListUpdateCallback;
import android.support.p006v7.util.DiffUtil;
import android.support.p006v7.util.ListUpdateCallback;
import android.support.p006v7.widget.RecyclerView;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class AsyncPagedListDiffer<T> {
    private final AsyncDifferConfig<T> mConfig;
    private boolean mIsContiguous;
    @Nullable
    PagedListListener<T> mListener;
    private int mMaxScheduledGeneration;
    private PagedList<T> mPagedList;
    private PagedList<T> mSnapshot;
    private final ListUpdateCallback mUpdateCallback;
    Executor mMainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
    private PagedList.Callback mPagedListCallback = new PagedList.Callback() { // from class: android.arch.paging.AsyncPagedListDiffer.1
        @Override // android.arch.paging.PagedList.Callback
        public void onInserted(int i, int i2) {
            AsyncPagedListDiffer.this.mUpdateCallback.onInserted(i, i2);
        }

        @Override // android.arch.paging.PagedList.Callback
        public void onRemoved(int i, int i2) {
            AsyncPagedListDiffer.this.mUpdateCallback.onRemoved(i, i2);
        }

        @Override // android.arch.paging.PagedList.Callback
        public void onChanged(int i, int i2) {
            AsyncPagedListDiffer.this.mUpdateCallback.onChanged(i, i2, null);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface PagedListListener<T> {
        void onCurrentListChanged(@Nullable PagedList<T> pagedList);
    }

    public AsyncPagedListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mUpdateCallback = new AdapterListUpdateCallback(adapter);
        this.mConfig = new AsyncDifferConfig.Builder(itemCallback).build();
    }

    public AsyncPagedListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
    }

    @Nullable
    public T getItem(int i) {
        PagedList<T> pagedList = this.mPagedList;
        if (pagedList == null) {
            PagedList<T> pagedList2 = this.mSnapshot;
            if (pagedList2 != null) {
                return pagedList2.get(i);
            }
            throw new IndexOutOfBoundsException("Item count is zero, getItem() call is invalid");
        }
        pagedList.loadAround(i);
        return this.mPagedList.get(i);
    }

    public int getItemCount() {
        PagedList<T> pagedList = this.mPagedList;
        if (pagedList != null) {
            return pagedList.size();
        }
        PagedList<T> pagedList2 = this.mSnapshot;
        if (pagedList2 == null) {
            return 0;
        }
        return pagedList2.size();
    }

    public void submitList(final PagedList<T> pagedList) {
        if (pagedList != null) {
            if (this.mPagedList == null && this.mSnapshot == null) {
                this.mIsContiguous = pagedList.isContiguous();
            } else if (pagedList.isContiguous() != this.mIsContiguous) {
                throw new IllegalArgumentException("AsyncPagedListDiffer cannot handle both contiguous and non-contiguous lists.");
            }
        }
        PagedList<T> pagedList2 = this.mPagedList;
        if (pagedList != pagedList2) {
            final int i = this.mMaxScheduledGeneration + 1;
            this.mMaxScheduledGeneration = i;
            if (pagedList == null) {
                int itemCount = getItemCount();
                PagedList<T> pagedList3 = this.mPagedList;
                if (pagedList3 != null) {
                    pagedList3.removeWeakCallback(this.mPagedListCallback);
                    this.mPagedList = null;
                } else if (this.mSnapshot != null) {
                    this.mSnapshot = null;
                }
                this.mUpdateCallback.onRemoved(0, itemCount);
                PagedListListener<T> pagedListListener = this.mListener;
                if (pagedListListener != null) {
                    pagedListListener.onCurrentListChanged(null);
                }
            } else if (pagedList2 == null && this.mSnapshot == null) {
                this.mPagedList = pagedList;
                pagedList.addWeakCallback(null, this.mPagedListCallback);
                this.mUpdateCallback.onInserted(0, pagedList.size());
                PagedListListener<T> pagedListListener2 = this.mListener;
                if (pagedListListener2 != null) {
                    pagedListListener2.onCurrentListChanged(pagedList);
                }
            } else {
                PagedList<T> pagedList4 = this.mPagedList;
                if (pagedList4 != null) {
                    pagedList4.removeWeakCallback(this.mPagedListCallback);
                    this.mSnapshot = (PagedList) this.mPagedList.snapshot();
                    this.mPagedList = null;
                }
                final PagedList<T> pagedList5 = this.mSnapshot;
                if (pagedList5 == null || this.mPagedList != null) {
                    throw new IllegalStateException("must be in snapshot state to diff");
                }
                final PagedList pagedList6 = (PagedList) pagedList.snapshot();
                this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() { // from class: android.arch.paging.AsyncPagedListDiffer.2
                    @Override // java.lang.Runnable
                    public void run() {
                        final DiffUtil.DiffResult computeDiff = PagedStorageDiffHelper.computeDiff(pagedList5.mStorage, pagedList6.mStorage, AsyncPagedListDiffer.this.mConfig.getDiffCallback());
                        AsyncPagedListDiffer.this.mMainThreadExecutor.execute(new Runnable() { // from class: android.arch.paging.AsyncPagedListDiffer.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (AsyncPagedListDiffer.this.mMaxScheduledGeneration == i) {
                                    AsyncPagedListDiffer.this.latchPagedList(pagedList, pagedList6, computeDiff);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void latchPagedList(PagedList<T> pagedList, PagedList<T> pagedList2, DiffUtil.DiffResult diffResult) {
        PagedList<T> pagedList3 = this.mSnapshot;
        if (pagedList3 == null || this.mPagedList != null) {
            throw new IllegalStateException("must be in snapshot state to apply diff");
        }
        this.mPagedList = pagedList;
        this.mSnapshot = null;
        PagedStorageDiffHelper.dispatchDiff(this.mUpdateCallback, pagedList3.mStorage, pagedList.mStorage, diffResult);
        pagedList.addWeakCallback(pagedList2, this.mPagedListCallback);
        PagedListListener<T> pagedListListener = this.mListener;
        if (pagedListListener != null) {
            pagedListListener.onCurrentListChanged(this.mPagedList);
        }
    }

    @Nullable
    public PagedList<T> getCurrentList() {
        PagedList<T> pagedList = this.mSnapshot;
        return pagedList != null ? pagedList : this.mPagedList;
    }
}
