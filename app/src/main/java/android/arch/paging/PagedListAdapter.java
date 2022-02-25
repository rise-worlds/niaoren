package android.arch.paging;

import android.arch.paging.AsyncPagedListDiffer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p006v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.p006v7.util.AdapterListUpdateCallback;
import android.support.p006v7.util.DiffUtil;
import android.support.p006v7.widget.RecyclerView;
import android.support.p006v7.widget.RecyclerView.ViewHolder;

/* loaded from: classes.dex */
public abstract class PagedListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncPagedListDiffer<T> mDiffer;
    private final AsyncPagedListDiffer.PagedListListener<T> mListener = new AsyncPagedListDiffer.PagedListListener<T>() { // from class: android.arch.paging.PagedListAdapter.1
        @Override // android.arch.paging.AsyncPagedListDiffer.PagedListListener
        public void onCurrentListChanged(@Nullable PagedList<T> pagedList) {
            PagedListAdapter.this.onCurrentListChanged(pagedList);
        }
    };

    public void onCurrentListChanged(@Nullable PagedList<T> pagedList) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PagedListAdapter(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mDiffer = new AsyncPagedListDiffer<>(this, itemCallback);
        this.mDiffer.mListener = this.mListener;
    }

    protected PagedListAdapter(@NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mDiffer = new AsyncPagedListDiffer<>(new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.mDiffer.mListener = this.mListener;
    }

    public void submitList(PagedList<T> pagedList) {
        this.mDiffer.submitList(pagedList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public T getItem(int i) {
        return this.mDiffer.getItem(i);
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDiffer.getItemCount();
    }

    @Nullable
    public PagedList<T> getCurrentList() {
        return this.mDiffer.getCurrentList();
    }
}
