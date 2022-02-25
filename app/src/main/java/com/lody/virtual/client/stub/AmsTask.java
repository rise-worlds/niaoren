package com.lody.virtual.client.stub;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.IAccountManagerResponse;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.VLog;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public abstract class AmsTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
    final Activity mActivity;
    final AccountManagerCallback<Bundle> mCallback;
    final Handler mHandler;
    protected final IAccountManagerResponse mResponse = new Response();

    public abstract void doWork() throws RemoteException;

    public AmsTask(Activity activity, Handler handler, AccountManagerCallback<Bundle> accountManagerCallback) {
        super(new Callable<Bundle>() { // from class: com.lody.virtual.client.stub.AmsTask.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Bundle call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        });
        this.mHandler = handler;
        this.mCallback = accountManagerCallback;
        this.mActivity = activity;
    }

    public final AccountManagerFuture<Bundle> start() {
        try {
            doWork();
        } catch (RemoteException e) {
            setException(e);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void set(Bundle bundle) {
        if (bundle == null) {
            VLog.m18991e("AccountManager", "the bundle must not be null\n%s", new Exception());
        }
        super.set((AmsTask) bundle);
    }

    private Bundle internalGetResult(Long l, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
        try {
            try {
                try {
                    if (l == null) {
                        return get();
                    }
                    return get(l.longValue(), timeUnit);
                } catch (InterruptedException | TimeoutException unused) {
                    cancel(true);
                    throw new OperationCanceledException();
                }
            } catch (CancellationException unused2) {
                throw new OperationCanceledException();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                } else if (cause instanceof UnsupportedOperationException) {
                    throw new AuthenticatorException(cause);
                } else if (cause instanceof AuthenticatorException) {
                    throw ((AuthenticatorException) cause);
                } else if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                } else if (cause instanceof Error) {
                    throw ((Error) cause);
                } else {
                    throw new IllegalStateException(cause);
                }
            }
        } finally {
            cancel(true);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.accounts.AccountManagerFuture
    public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
        return internalGetResult(null, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.accounts.AccountManagerFuture
    public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
        return internalGetResult(Long.valueOf(j), timeUnit);
    }

    @Override // java.util.concurrent.FutureTask
    protected void done() {
        AccountManagerCallback<Bundle> accountManagerCallback = this.mCallback;
        if (accountManagerCallback != null) {
            postToHandler(this.mHandler, accountManagerCallback, this);
        }
    }

    /* loaded from: classes.dex */
    private class Response extends IAccountManagerResponse.Stub {
        private Response() {
        }

        @Override // android.accounts.IAccountManagerResponse
        public void onResult(Bundle bundle) {
            Intent intent = (Intent) bundle.getParcelable("intent");
            if (intent != null && AmsTask.this.mActivity != null) {
                AmsTask.this.mActivity.startActivity(intent);
            } else if (bundle.getBoolean("retry")) {
                try {
                    AmsTask.this.doWork();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } else {
                AmsTask.this.set(bundle);
            }
        }

        @Override // android.accounts.IAccountManagerResponse
        public void onError(int i, String str) {
            if (i == 4 || i == 100 || i == 101) {
                AmsTask.this.cancel(true);
                return;
            }
            AmsTask amsTask = AmsTask.this;
            amsTask.setException(amsTask.convertErrorToException(i, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Exception convertErrorToException(int i, String str) {
        if (i == 3) {
            return new IOException(str);
        }
        if (i == 6) {
            return new UnsupportedOperationException(str);
        }
        if (i == 5) {
            return new AuthenticatorException(str);
        }
        if (i == 7) {
            return new IllegalArgumentException(str);
        }
        return new AuthenticatorException(str);
    }

    private void postToHandler(Handler handler, final AccountManagerCallback<Bundle> accountManagerCallback, final AccountManagerFuture<Bundle> accountManagerFuture) {
        if (handler == null) {
            handler = VirtualRuntime.getUIHandler();
        }
        handler.post(new Runnable() { // from class: com.lody.virtual.client.stub.AmsTask.2
            @Override // java.lang.Runnable
            public void run() {
                accountManagerCallback.run(accountManagerFuture);
            }
        });
    }
}
