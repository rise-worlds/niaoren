package com.lidroid.xutils.http;

import android.os.SystemClock;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler;
import com.lidroid.xutils.http.callback.FileDownloadHandler;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.http.callback.StringDownloadHandler;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.util.OtherUtils;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public class HttpHandler<T> extends PriorityAsyncTask<Object, Object, Void> implements RequestCallBackHandler {
    private static final int UPDATE_FAILURE = 3;
    private static final int UPDATE_LOADING = 2;
    private static final int UPDATE_START = 1;
    private static final int UPDATE_SUCCESS = 4;
    private static final NotUseApacheRedirectHandler notUseApacheRedirectHandler = new NotUseApacheRedirectHandler(null);
    private RequestCallBack<T> callback;
    private String charset;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private HttpRedirectHandler httpRedirectHandler;
    private long lastUpdateTime;
    private HttpRequestBase request;
    private String requestMethod;
    private String requestUrl;
    private boolean isUploading = true;
    private int retriedCount = 0;
    private String fileSavePath = null;
    private boolean isDownloadingFile = false;
    private boolean autoResume = false;
    private boolean autoRename = false;
    private State state = State.WAITING;
    private long expiry = HttpCache.getDefaultExpiryTime();

    public void setHttpRedirectHandler(HttpRedirectHandler httpRedirectHandler) {
        if (httpRedirectHandler != null) {
            this.httpRedirectHandler = httpRedirectHandler;
        }
    }

    public HttpHandler(AbstractHttpClient abstractHttpClient, HttpContext httpContext, String str, RequestCallBack<T> requestCallBack) {
        this.client = abstractHttpClient;
        this.context = httpContext;
        this.callback = requestCallBack;
        this.charset = str;
        this.client.setRedirectHandler(notUseApacheRedirectHandler);
    }

    public State getState() {
        return this.state;
    }

    public void setExpiry(long j) {
        this.expiry = j;
    }

    public void setRequestCallBack(RequestCallBack<T> requestCallBack) {
        this.callback = requestCallBack;
    }

    public RequestCallBack<T> getRequestCallBack() {
        return this.callback;
    }

    private ResponseInfo<T> sendRequest(HttpRequestBase httpRequestBase) throws HttpException {
        IOException e;
        boolean z;
        String str;
        HttpRequestRetryHandler httpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
        do {
            if (this.autoResume && this.isDownloadingFile) {
                File file = new File(this.fileSavePath);
                long length = (!file.isFile() || !file.exists()) ? 0L : file.length();
                if (length > 0) {
                    httpRequestBase.setHeader("RANGE", "bytes=" + length + "-");
                }
            }
            try {
                this.requestMethod = httpRequestBase.getMethod();
                if (HttpUtils.sHttpCache.isEnabled(this.requestMethod) && (str = HttpUtils.sHttpCache.get(this.requestUrl)) != null) {
                    return new ResponseInfo<>(null, str, true);
                }
                if (!isCancelled()) {
                    return handleResponse(this.client.execute(httpRequestBase, this.context));
                }
                return null;
            } catch (HttpException e2) {
                throw e2;
            } catch (IOException e3) {
                e = e3;
                int i = this.retriedCount + 1;
                this.retriedCount = i;
                z = httpRequestRetryHandler.retryRequest(e, i, this.context);
                continue;
            } catch (NullPointerException e4) {
                e = new IOException(e4.getMessage());
                e.initCause(e4);
                int i2 = this.retriedCount + 1;
                this.retriedCount = i2;
                z = httpRequestRetryHandler.retryRequest(e, i2, this.context);
                continue;
            } catch (UnknownHostException e5) {
                e = e5;
                int i3 = this.retriedCount + 1;
                this.retriedCount = i3;
                z = httpRequestRetryHandler.retryRequest(e, i3, this.context);
                continue;
            } catch (Throwable th) {
                e = new IOException(th.getMessage());
                e.initCause(th);
                int i4 = this.retriedCount + 1;
                this.retriedCount = i4;
                z = httpRequestRetryHandler.retryRequest(e, i4, this.context);
                continue;
            }
        } while (z);
        throw new HttpException(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lidroid.xutils.task.PriorityAsyncTask
    public Void doInBackground(Object... objArr) {
        if (this.state == State.CANCELLED || objArr == null || objArr.length == 0) {
            return null;
        }
        if (objArr.length > 3) {
            this.fileSavePath = String.valueOf(objArr[1]);
            this.isDownloadingFile = this.fileSavePath != null;
            this.autoResume = ((Boolean) objArr[2]).booleanValue();
            this.autoRename = ((Boolean) objArr[3]).booleanValue();
        }
        try {
        } catch (HttpException e) {
            publishProgress(3, e, e.getMessage());
        }
        if (this.state == State.CANCELLED) {
            return null;
        }
        this.request = (HttpRequestBase) objArr[0];
        this.requestUrl = this.request.getURI().toString();
        if (this.callback != null) {
            this.callback.setRequestUrl(this.requestUrl);
        }
        publishProgress(1);
        this.lastUpdateTime = SystemClock.uptimeMillis();
        ResponseInfo<T> sendRequest = sendRequest(this.request);
        if (sendRequest != null) {
            publishProgress(4, sendRequest);
            return null;
        }
        return null;
    }

    @Override // com.lidroid.xutils.task.PriorityAsyncTask
    protected void onProgressUpdate(Object... objArr) {
        if (this.state != State.CANCELLED && objArr != null && objArr.length != 0 && this.callback != null) {
            switch (((Integer) objArr[0]).intValue()) {
                case 1:
                    this.state = State.STARTED;
                    this.callback.onStart();
                    return;
                case 2:
                    if (objArr.length == 3) {
                        this.state = State.LOADING;
                        this.callback.onLoading(Long.valueOf(String.valueOf(objArr[1])).longValue(), Long.valueOf(String.valueOf(objArr[2])).longValue(), this.isUploading);
                        return;
                    }
                    return;
                case 3:
                    if (objArr.length == 3) {
                        this.state = State.FAILURE;
                        this.callback.onFailure((HttpException) objArr[1], (String) objArr[2]);
                        return;
                    }
                    return;
                case 4:
                    if (objArr.length == 2) {
                        this.state = State.SUCCESS;
                        this.callback.onSuccess((ResponseInfo) objArr[1]);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private ResponseInfo<T> handleResponse(HttpResponse httpResponse) throws HttpException, IOException {
        if (httpResponse != null) {
            Object obj = null;
            String str = null;
            if (isCancelled()) {
                return null;
            }
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode < 300) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    this.isUploading = false;
                    if (this.isDownloadingFile) {
                        this.autoResume = this.autoResume && OtherUtils.isSupportRange(httpResponse);
                        if (this.autoRename) {
                            str = OtherUtils.getFileNameFromHttpResponse(httpResponse);
                        }
                        obj = new FileDownloadHandler().handleEntity(entity, this, this.fileSavePath, this.autoResume, str);
                    } else {
                        obj = new StringDownloadHandler().handleEntity(entity, this, this.charset);
                        if (HttpUtils.sHttpCache.isEnabled(this.requestMethod)) {
                            HttpUtils.sHttpCache.put(this.requestUrl, (String) obj, this.expiry);
                        }
                    }
                }
                return new ResponseInfo<>(httpResponse, obj, false);
            } else if (statusCode == 301 || statusCode == 302) {
                if (this.httpRedirectHandler == null) {
                    this.httpRedirectHandler = new DefaultHttpRedirectHandler();
                }
                HttpRequestBase directRequest = this.httpRedirectHandler.getDirectRequest(httpResponse);
                if (directRequest != null) {
                    return sendRequest(directRequest);
                }
                return null;
            } else if (statusCode == 416) {
                throw new HttpException(statusCode, "maybe the file has downloaded completely");
            } else {
                throw new HttpException(statusCode, statusLine.getReasonPhrase());
            }
        } else {
            throw new HttpException("response is null");
        }
    }

    @Override // com.lidroid.xutils.task.PriorityAsyncTask, com.lidroid.xutils.task.TaskHandler
    public void cancel() {
        this.state = State.CANCELLED;
        HttpRequestBase httpRequestBase = this.request;
        if (httpRequestBase != null && !httpRequestBase.isAborted()) {
            try {
                this.request.abort();
            } catch (Throwable unused) {
            }
        }
        if (!isCancelled()) {
            try {
                cancel(true);
            } catch (Throwable unused2) {
            }
        }
        RequestCallBack<T> requestCallBack = this.callback;
        if (requestCallBack != null) {
            requestCallBack.onCancelled();
        }
    }

    @Override // com.lidroid.xutils.http.callback.RequestCallBackHandler
    public boolean updateProgress(long j, long j2, boolean z) {
        if (!(this.callback == null || this.state == State.CANCELLED)) {
            if (z) {
                publishProgress(2, Long.valueOf(j), Long.valueOf(j2));
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (uptimeMillis - this.lastUpdateTime >= this.callback.getRate()) {
                    this.lastUpdateTime = uptimeMillis;
                    publishProgress(2, Long.valueOf(j), Long.valueOf(j2));
                }
            }
        }
        return this.state != State.CANCELLED;
    }

    /* loaded from: classes.dex */
    public enum State {
        WAITING(0),
        STARTED(1),
        LOADING(2),
        FAILURE(3),
        CANCELLED(4),
        SUCCESS(5);
        
        private int value;

        State(int i) {
            this.value = 0;
            this.value = i;
        }

        public static State valueOf(int i) {
            switch (i) {
                case 0:
                    return WAITING;
                case 1:
                    return STARTED;
                case 2:
                    return LOADING;
                case 3:
                    return FAILURE;
                case 4:
                    return CANCELLED;
                case 5:
                    return SUCCESS;
                default:
                    return FAILURE;
            }
        }

        public final int value() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    private static final class NotUseApacheRedirectHandler implements RedirectHandler {
        @Override // org.apache.http.client.RedirectHandler
        public final URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
            return null;
        }

        @Override // org.apache.http.client.RedirectHandler
        public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
            return false;
        }

        private NotUseApacheRedirectHandler() {
        }

        /* synthetic */ NotUseApacheRedirectHandler(NotUseApacheRedirectHandler notUseApacheRedirectHandler) {
            this();
        }
    }
}
