package com.lidroid.xutils.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.lidroid.xutils.util.LogUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public abstract class PriorityAsyncTask<Params, Progress, Result> implements TaskHandler {
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    private Priority priority;
    private static final InternalHandler sHandler = new InternalHandler(null);
    public static final Executor sDefaultExecutor = new PriorityExecutor();
    private volatile boolean mExecuteInvoked = false;
    private final AtomicBoolean mCancelled = new AtomicBoolean();
    private final AtomicBoolean mTaskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> mWorker = new WorkerRunnable<Params, Result>() { // from class: com.lidroid.xutils.task.PriorityAsyncTask.1
        @Override // java.util.concurrent.Callable
        public Result call() throws Exception {
            PriorityAsyncTask.this.mTaskInvoked.set(true);
            Process.setThreadPriority(10);
            PriorityAsyncTask priorityAsyncTask = PriorityAsyncTask.this;
            return (Result) priorityAsyncTask.postResult(priorityAsyncTask.doInBackground(this.mParams));
        }
    };
    private final FutureTask<Result> mFuture = new FutureTask<Result>(this.mWorker) { // from class: com.lidroid.xutils.task.PriorityAsyncTask.2
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                PriorityAsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e) {
                LogUtils.m19222d(e.getMessage());
            } catch (CancellationException unused) {
                PriorityAsyncTask.this.postResultIfNotInvoked(null);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            }
        }
    };

    protected abstract Result doInBackground(Params... paramsArr);

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean isPaused() {
        return false;
    }

    protected void onCancelled() {
    }

    protected void onPostExecute(Result result) {
    }

    protected void onPreExecute() {
    }

    protected void onProgressUpdate(Progress... progressArr) {
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void pause() {
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void resume() {
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportCancel() {
        return true;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportPause() {
        return false;
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public boolean supportResume() {
        return false;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResultIfNotInvoked(Result result) {
        if (!this.mTaskInvoked.get()) {
            postResult(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result postResult(Result result) {
        sHandler.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    @Override // com.lidroid.xutils.task.TaskHandler
    public void cancel() {
        cancel(true);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Result get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(j, timeUnit);
    }

    public final PriorityAsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(sDefaultExecutor, paramsArr);
    }

    public final PriorityAsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (!this.mExecuteInvoked) {
            this.mExecuteInvoked = true;
            onPreExecute();
            this.mWorker.mParams = paramsArr;
            executor.execute(new PriorityRunnable(this.priority, this.mFuture));
            return this;
        }
        throw new IllegalStateException("Cannot execute task: the task is already executed.");
    }

    public static void execute(Runnable runnable) {
        execute(runnable, Priority.DEFAULT);
    }

    public static void execute(Runnable runnable, Priority priority) {
        sDefaultExecutor.execute(new PriorityRunnable(priority, runnable));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void publishProgress(Progress... progressArr) {
        if (!isCancelled()) {
            sHandler.obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InternalHandler extends Handler {
        /* synthetic */ InternalHandler(InternalHandler internalHandler) {
            this();
        }

        private InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            switch (message.what) {
                case 1:
                    asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
                    return;
                case 2:
                    asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;

        private WorkerRunnable() {
        }

        /* synthetic */ WorkerRunnable(WorkerRunnable workerRunnable) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AsyncTaskResult<Data> {
        final Data[] mData;
        final PriorityAsyncTask mTask;

        AsyncTaskResult(PriorityAsyncTask priorityAsyncTask, Data... dataArr) {
            this.mTask = priorityAsyncTask;
            this.mData = dataArr;
        }
    }
}
