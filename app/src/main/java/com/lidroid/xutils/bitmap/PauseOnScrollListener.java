package com.lidroid.xutils.bitmap;

import android.widget.AbsListView;
import com.lidroid.xutils.task.TaskHandler;

/* loaded from: classes.dex */
public class PauseOnScrollListener implements AbsListView.OnScrollListener {
    private final AbsListView.OnScrollListener externalListener;
    private final boolean pauseOnFling;
    private final boolean pauseOnScroll;
    private TaskHandler taskHandler;

    public PauseOnScrollListener(TaskHandler taskHandler, boolean z, boolean z2) {
        this(taskHandler, z, z2, null);
    }

    public PauseOnScrollListener(TaskHandler taskHandler, boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.taskHandler = taskHandler;
        this.pauseOnScroll = z;
        this.pauseOnFling = z2;
        this.externalListener = onScrollListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.taskHandler.resume();
                break;
            case 1:
                if (this.pauseOnScroll) {
                    this.taskHandler.pause();
                    break;
                }
                break;
            case 2:
                if (this.pauseOnFling) {
                    this.taskHandler.pause();
                    break;
                }
                break;
        }
        AbsListView.OnScrollListener onScrollListener = this.externalListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AbsListView.OnScrollListener onScrollListener = this.externalListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(absListView, i, i2, i3);
        }
    }
}
