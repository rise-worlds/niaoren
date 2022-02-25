package org.jdeferred.android;

import android.os.AsyncTask;
import java.util.concurrent.CancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p110z1.DeferredManager;
import p110z1.DeferredObject;
import p110z1.Promise;

/* renamed from: org.jdeferred.android.i */
/* loaded from: classes2.dex */
public abstract class DeferredAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    /* renamed from: a */
    protected final Logger f14849a;

    /* renamed from: b */
    private final DeferredObject<Result, Throwable, Progress> f14850b;

    /* renamed from: c */
    private final DeferredManager.EnumC5238a f14851c;

    /* renamed from: d */
    private Throwable f14852d;

    /* renamed from: a */
    protected abstract Result m14763a(Params... paramsArr) throws Exception;

    public DeferredAsyncTask() {
        this.f14849a = LoggerFactory.getLogger(DeferredAsyncTask.class);
        this.f14850b = new DeferredObject<>();
        this.f14851c = DeferredManager.EnumC5238a.DEFAULT;
    }

    public DeferredAsyncTask(DeferredManager.EnumC5238a aVar) {
        this.f14849a = LoggerFactory.getLogger(DeferredAsyncTask.class);
        this.f14850b = new DeferredObject<>();
        this.f14851c = aVar;
    }

    @Override // android.os.AsyncTask
    protected final void onCancelled() {
        this.f14850b.mo3298b((DeferredObject<Result, Throwable, Progress>) new CancellationException());
    }

    @Override // android.os.AsyncTask
    protected final void onCancelled(Result result) {
        this.f14850b.mo3298b((DeferredObject<Result, Throwable, Progress>) new CancellationException());
    }

    @Override // android.os.AsyncTask
    protected final void onPostExecute(Result result) {
        Throwable th = this.f14852d;
        if (th != null) {
            this.f14850b.mo3298b((DeferredObject<Result, Throwable, Progress>) th);
        } else {
            this.f14850b.mo3299a((DeferredObject<Result, Throwable, Progress>) result);
        }
    }

    @Override // android.os.AsyncTask
    protected final void onProgressUpdate(Progress... progressArr) {
        if (progressArr == null || progressArr.length == 0) {
            this.f14850b.mo3297c(null);
        } else if (progressArr.length > 0) {
            this.f14849a.warn("There were multiple progress values.  Only the first one was used!");
            this.f14850b.mo3297c(progressArr[0]);
        }
    }

    @Override // android.os.AsyncTask
    protected final Result doInBackground(Params... paramsArr) {
        try {
            return m14763a((Object[]) paramsArr);
        } catch (Throwable th) {
            this.f14852d = th;
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    protected final void m14764a(Progress progress) {
        publishProgress(progress);
    }

    /* renamed from: a */
    public Promise<Result, Throwable, Progress> m14765a() {
        return this.f14850b.mo3300a();
    }

    /* renamed from: b */
    public DeferredManager.EnumC5238a m14762b() {
        return this.f14851c;
    }
}
