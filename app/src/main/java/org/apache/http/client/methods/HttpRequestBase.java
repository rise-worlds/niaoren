package org.apache.http.client.methods;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public abstract class HttpRequestBase extends AbstractHttpMessage implements HttpUriRequest, AbortableHttpRequest, Cloneable {
    private Lock abortLock = new ReentrantLock();
    private boolean aborted;
    private ClientConnectionRequest connRequest;
    private ConnectionReleaseTrigger releaseTrigger;
    private URI uri;

    public abstract String getMethod();

    @Override // org.apache.http.HttpMessage
    public ProtocolVersion getProtocolVersion() {
        return HttpProtocolParams.getVersion(getParams());
    }

    public URI getURI() {
        return this.uri;
    }

    @Override // org.apache.http.HttpRequest
    public RequestLine getRequestLine() {
        String method = getMethod();
        ProtocolVersion ver = getProtocolVersion();
        URI uri = getURI();
        String uritext = null;
        if (uri != null) {
            uritext = uri.toASCIIString();
        }
        if (uritext == null || uritext.length() == 0) {
            uritext = "/";
        }
        return new BasicRequestLine(method, uritext, ver);
    }

    public void setURI(URI uri) {
        this.uri = uri;
    }

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    public void setConnectionRequest(ClientConnectionRequest connRequest) throws IOException {
        this.abortLock.lock();
        try {
            if (!this.aborted) {
                this.releaseTrigger = null;
                this.connRequest = connRequest;
                return;
            }
            throw new IOException("Request already aborted");
        } finally {
            this.abortLock.unlock();
        }
    }

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    public void setReleaseTrigger(ConnectionReleaseTrigger releaseTrigger) throws IOException {
        this.abortLock.lock();
        try {
            if (!this.aborted) {
                this.connRequest = null;
                this.releaseTrigger = releaseTrigger;
                return;
            }
            throw new IOException("Request already aborted");
        } finally {
            this.abortLock.unlock();
        }
    }

    @Override // org.apache.http.client.methods.HttpUriRequest, org.apache.http.client.methods.AbortableHttpRequest
    public void abort() {
        this.abortLock.lock();
        try {
            if (!this.aborted) {
                this.aborted = true;
                ClientConnectionRequest localRequest = this.connRequest;
                ConnectionReleaseTrigger localTrigger = this.releaseTrigger;
                if (localRequest != null) {
                    localRequest.abortRequest();
                }
                if (localTrigger != null) {
                    try {
                        localTrigger.abortConnection();
                    } catch (IOException e) {
                    }
                }
            }
        } finally {
            this.abortLock.unlock();
        }
    }

    @Override // org.apache.http.client.methods.HttpUriRequest
    public boolean isAborted() {
        return this.aborted;
    }

    public Object clone() throws CloneNotSupportedException {
        HttpRequestBase clone = (HttpRequestBase) super.clone();
        clone.abortLock = new ReentrantLock();
        clone.aborted = false;
        clone.releaseTrigger = null;
        clone.connRequest = null;
        clone.headergroup = (HeaderGroup) CloneUtils.clone(this.headergroup);
        clone.params = (HttpParams) CloneUtils.clone(this.params);
        return clone;
    }
}
