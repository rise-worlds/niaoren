package org.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class BasicHttpProcessor implements HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable {
    protected List requestInterceptors = null;
    protected List responseInterceptors = null;

    @Override // org.apache.http.protocol.HttpRequestInterceptorList
    public void addRequestInterceptor(HttpRequestInterceptor itcp) {
        if (itcp != null) {
            if (this.requestInterceptors == null) {
                this.requestInterceptors = new ArrayList();
            }
            this.requestInterceptors.add(itcp);
        }
    }

    @Override // org.apache.http.protocol.HttpRequestInterceptorList
    public void addRequestInterceptor(HttpRequestInterceptor itcp, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        } else if (itcp != null) {
            if (this.requestInterceptors == null) {
                if (index <= 0) {
                    this.requestInterceptors = new ArrayList();
                } else {
                    throw new IndexOutOfBoundsException(String.valueOf(index));
                }
            }
            this.requestInterceptors.add(index, itcp);
        }
    }

    @Override // org.apache.http.protocol.HttpResponseInterceptorList
    public void addResponseInterceptor(HttpResponseInterceptor itcp, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        } else if (itcp != null) {
            if (this.responseInterceptors == null) {
                if (index <= 0) {
                    this.responseInterceptors = new ArrayList();
                } else {
                    throw new IndexOutOfBoundsException(String.valueOf(index));
                }
            }
            this.responseInterceptors.add(index, itcp);
        }
    }

    @Override // org.apache.http.protocol.HttpRequestInterceptorList
    public void removeRequestInterceptorByClass(Class clazz) {
        if (this.requestInterceptors != null) {
            Iterator it = this.requestInterceptors.iterator();
            while (it.hasNext()) {
                Object request = it.next();
                if (request.getClass().equals(clazz)) {
                    it.remove();
                }
            }
        }
    }

    @Override // org.apache.http.protocol.HttpResponseInterceptorList
    public void removeResponseInterceptorByClass(Class clazz) {
        if (this.responseInterceptors != null) {
            Iterator it = this.responseInterceptors.iterator();
            while (it.hasNext()) {
                Object request = it.next();
                if (request.getClass().equals(clazz)) {
                    it.remove();
                }
            }
        }
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor) {
        addRequestInterceptor(interceptor);
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor, int index) {
        addRequestInterceptor(interceptor, index);
    }

    @Override // org.apache.http.protocol.HttpRequestInterceptorList
    public int getRequestInterceptorCount() {
        if (this.requestInterceptors == null) {
            return 0;
        }
        return this.requestInterceptors.size();
    }

    @Override // org.apache.http.protocol.HttpRequestInterceptorList
    public HttpRequestInterceptor getRequestInterceptor(int index) {
        if (this.requestInterceptors == null || index < 0 || index >= this.requestInterceptors.size()) {
            return null;
        }
        return (HttpRequestInterceptor) this.requestInterceptors.get(index);
    }

    @Override // org.apache.http.protocol.HttpRequestInterceptorList
    public void clearRequestInterceptors() {
        this.requestInterceptors = null;
    }

    @Override // org.apache.http.protocol.HttpResponseInterceptorList
    public void addResponseInterceptor(HttpResponseInterceptor itcp) {
        if (itcp != null) {
            if (this.responseInterceptors == null) {
                this.responseInterceptors = new ArrayList();
            }
            this.responseInterceptors.add(itcp);
        }
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor) {
        addResponseInterceptor(interceptor);
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor, int index) {
        addResponseInterceptor(interceptor, index);
    }

    @Override // org.apache.http.protocol.HttpResponseInterceptorList
    public int getResponseInterceptorCount() {
        if (this.responseInterceptors == null) {
            return 0;
        }
        return this.responseInterceptors.size();
    }

    @Override // org.apache.http.protocol.HttpResponseInterceptorList
    public HttpResponseInterceptor getResponseInterceptor(int index) {
        if (this.responseInterceptors == null || index < 0 || index >= this.responseInterceptors.size()) {
            return null;
        }
        return (HttpResponseInterceptor) this.responseInterceptors.get(index);
    }

    @Override // org.apache.http.protocol.HttpResponseInterceptorList
    public void clearResponseInterceptors() {
        this.responseInterceptors = null;
    }

    @Override // org.apache.http.protocol.HttpRequestInterceptorList, org.apache.http.protocol.HttpResponseInterceptorList
    public void setInterceptors(List list) {
        if (list != null) {
            if (this.requestInterceptors != null) {
                this.requestInterceptors.clear();
            }
            if (this.responseInterceptors != null) {
                this.responseInterceptors.clear();
            }
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj instanceof HttpRequestInterceptor) {
                    addInterceptor((HttpRequestInterceptor) obj);
                }
                if (obj instanceof HttpResponseInterceptor) {
                    addInterceptor((HttpResponseInterceptor) obj);
                }
            }
            return;
        }
        throw new IllegalArgumentException("List must not be null.");
    }

    public void clearInterceptors() {
        clearRequestInterceptors();
        clearResponseInterceptors();
    }

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws IOException, HttpException {
        if (this.requestInterceptors != null) {
            for (int i = 0; i < this.requestInterceptors.size(); i++) {
                HttpRequestInterceptor interceptor = (HttpRequestInterceptor) this.requestInterceptors.get(i);
                interceptor.process(request, context);
            }
        }
    }

    @Override // org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse response, HttpContext context) throws IOException, HttpException {
        if (this.responseInterceptors != null) {
            for (int i = 0; i < this.responseInterceptors.size(); i++) {
                HttpResponseInterceptor interceptor = (HttpResponseInterceptor) this.responseInterceptors.get(i);
                interceptor.process(response, context);
            }
        }
    }

    protected void copyInterceptors(BasicHttpProcessor target) {
        if (this.requestInterceptors != null) {
            target.requestInterceptors = new ArrayList(this.requestInterceptors);
        }
        if (this.responseInterceptors != null) {
            target.responseInterceptors = new ArrayList(this.responseInterceptors);
        }
    }

    public BasicHttpProcessor copy() {
        BasicHttpProcessor clone = new BasicHttpProcessor();
        copyInterceptors(clone);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpProcessor clone = (BasicHttpProcessor) super.clone();
        copyInterceptors(clone);
        return clone;
    }
}
