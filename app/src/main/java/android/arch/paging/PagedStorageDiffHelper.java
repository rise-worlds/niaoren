package android.arch.paging;

import android.support.annotation.Nullable;
import android.support.p006v7.util.DiffUtil;
import android.support.p006v7.util.ListUpdateCallback;

/* loaded from: classes.dex */
class PagedStorageDiffHelper {
    private PagedStorageDiffHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> DiffUtil.DiffResult computeDiff(final PagedStorage<T> pagedStorage, final PagedStorage<T> pagedStorage2, final DiffUtil.ItemCallback<T> itemCallback) {
        final int computeLeadingNulls = pagedStorage.computeLeadingNulls();
        int computeLeadingNulls2 = pagedStorage2.computeLeadingNulls();
        final int size = (pagedStorage.size() - computeLeadingNulls) - pagedStorage.computeTrailingNulls();
        final int size2 = (pagedStorage2.size() - computeLeadingNulls2) - pagedStorage2.computeTrailingNulls();
        return DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: android.arch.paging.PagedStorageDiffHelper.1
            @Override // android.support.p006v7.util.DiffUtil.Callback
            @Nullable
            public Object getChangePayload(int i, int i2) {
                Object obj = PagedStorage.this.get(i + computeLeadingNulls);
                PagedStorage pagedStorage3 = pagedStorage2;
                Object obj2 = pagedStorage3.get(i2 + pagedStorage3.getLeadingNullCount());
                if (obj == null || obj2 == null) {
                    return null;
                }
                return itemCallback.getChangePayload(obj, obj2);
            }

            @Override // android.support.p006v7.util.DiffUtil.Callback
            public int getOldListSize() {
                return size;
            }

            @Override // android.support.p006v7.util.DiffUtil.Callback
            public int getNewListSize() {
                return size2;
            }

            @Override // android.support.p006v7.util.DiffUtil.Callback
            public boolean areItemsTheSame(int i, int i2) {
                Object obj = PagedStorage.this.get(i + computeLeadingNulls);
                PagedStorage pagedStorage3 = pagedStorage2;
                Object obj2 = pagedStorage3.get(i2 + pagedStorage3.getLeadingNullCount());
                if (obj == obj2) {
                    return true;
                }
                if (obj == null || obj2 == null) {
                    return false;
                }
                return itemCallback.areItemsTheSame(obj, obj2);
            }

            @Override // android.support.p006v7.util.DiffUtil.Callback
            public boolean areContentsTheSame(int i, int i2) {
                Object obj = PagedStorage.this.get(i + computeLeadingNulls);
                PagedStorage pagedStorage3 = pagedStorage2;
                Object obj2 = pagedStorage3.get(i2 + pagedStorage3.getLeadingNullCount());
                if (obj == obj2) {
                    return true;
                }
                if (obj == null || obj2 == null) {
                    return false;
                }
                return itemCallback.areContentsTheSame(obj, obj2);
            }
        }, true);
    }

    /* loaded from: classes.dex */
    private static class OffsettingListUpdateCallback implements ListUpdateCallback {
        private final ListUpdateCallback mCallback;
        private final int mOffset;

        private OffsettingListUpdateCallback(int i, ListUpdateCallback listUpdateCallback) {
            this.mOffset = i;
            this.mCallback = listUpdateCallback;
        }

        @Override // android.support.p006v7.util.ListUpdateCallback
        public void onInserted(int i, int i2) {
            this.mCallback.onInserted(i + this.mOffset, i2);
        }

        @Override // android.support.p006v7.util.ListUpdateCallback
        public void onRemoved(int i, int i2) {
            this.mCallback.onRemoved(i + this.mOffset, i2);
        }

        @Override // android.support.p006v7.util.ListUpdateCallback
        public void onMoved(int i, int i2) {
            ListUpdateCallback listUpdateCallback = this.mCallback;
            int i3 = this.mOffset;
            listUpdateCallback.onMoved(i + i3, i2 + i3);
        }

        @Override // android.support.p006v7.util.ListUpdateCallback
        public void onChanged(int i, int i2, Object obj) {
            this.mCallback.onChanged(i + this.mOffset, i2, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void dispatchDiff(ListUpdateCallback listUpdateCallback, PagedStorage<T> pagedStorage, PagedStorage<T> pagedStorage2, DiffUtil.DiffResult diffResult) {
        int computeTrailingNulls = pagedStorage.computeTrailingNulls();
        int computeTrailingNulls2 = pagedStorage2.computeTrailingNulls();
        int computeLeadingNulls = pagedStorage.computeLeadingNulls();
        int computeLeadingNulls2 = pagedStorage2.computeLeadingNulls();
        if (computeTrailingNulls == 0 && computeTrailingNulls2 == 0 && computeLeadingNulls == 0 && computeLeadingNulls2 == 0) {
            diffResult.dispatchUpdatesTo(listUpdateCallback);
            return;
        }
        if (computeTrailingNulls > computeTrailingNulls2) {
            int i = computeTrailingNulls - computeTrailingNulls2;
            listUpdateCallback.onRemoved(pagedStorage.size() - i, i);
        } else if (computeTrailingNulls < computeTrailingNulls2) {
            listUpdateCallback.onInserted(pagedStorage.size(), computeTrailingNulls2 - computeTrailingNulls);
        }
        if (computeLeadingNulls > computeLeadingNulls2) {
            listUpdateCallback.onRemoved(0, computeLeadingNulls - computeLeadingNulls2);
        } else if (computeLeadingNulls < computeLeadingNulls2) {
            listUpdateCallback.onInserted(0, computeLeadingNulls2 - computeLeadingNulls);
        }
        if (computeLeadingNulls2 != 0) {
            diffResult.dispatchUpdatesTo(new OffsettingListUpdateCallback(computeLeadingNulls2, listUpdateCallback));
        } else {
            diffResult.dispatchUpdatesTo(listUpdateCallback);
        }
    }
}
