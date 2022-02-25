package android.arch.paging;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
class PageResult<T> {
    static final int APPEND = 1;
    static final int INIT = 0;
    private static final PageResult INVALID_RESULT = new PageResult(Collections.EMPTY_LIST, 0);
    static final int PREPEND = 2;
    static final int TILE = 3;
    public final int leadingNulls;
    @NonNull
    public final List<T> page;
    public final int positionOffset;
    public final int trailingNulls;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class Receiver<T> {
        @MainThread
        public abstract void onPageResult(int i, @NonNull PageResult<T> pageResult);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface ResultType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> PageResult<T> getInvalidResult() {
        return INVALID_RESULT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageResult(@NonNull List<T> list, int i, int i2, int i3) {
        this.page = list;
        this.leadingNulls = i;
        this.trailingNulls = i2;
        this.positionOffset = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageResult(@NonNull List<T> list, int i) {
        this.page = list;
        this.leadingNulls = 0;
        this.trailingNulls = 0;
        this.positionOffset = i;
    }

    public String toString() {
        return "Result " + this.leadingNulls + ", " + this.page + ", " + this.trailingNulls + ", offset " + this.positionOffset;
    }

    public boolean isInvalid() {
        return this == INVALID_RESULT;
    }
}
