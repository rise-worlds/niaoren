package org.apache.http.impl.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams applicationParams;
    protected final HttpParams clientParams;
    private final Log log;
    protected final HttpParams overrideParams;
    protected final HttpParams requestParams;

    public ClientParamsStack(HttpParams aparams, HttpParams cparams, HttpParams rparams, HttpParams oparams) {
        this.log = LogFactory.getLog(getClass());
        this.applicationParams = aparams;
        this.clientParams = cparams;
        this.requestParams = rparams;
        this.overrideParams = oparams;
    }

    public ClientParamsStack(ClientParamsStack stack) {
        this(stack.getApplicationParams(), stack.getClientParams(), stack.getRequestParams(), stack.getOverrideParams());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ClientParamsStack(org.apache.http.impl.client.ClientParamsStack r5, org.apache.http.params.HttpParams r6, org.apache.http.params.HttpParams r7, org.apache.http.params.HttpParams r8, org.apache.http.params.HttpParams r9) {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x0004
            r0 = r6
            goto L_0x0008
        L_0x0004:
            org.apache.http.params.HttpParams r0 = r5.getApplicationParams()
        L_0x0008:
            if (r7 == 0) goto L_0x000c
            r1 = r7
            goto L_0x0010
        L_0x000c:
            org.apache.http.params.HttpParams r1 = r5.getClientParams()
        L_0x0010:
            if (r8 == 0) goto L_0x0014
            r2 = r8
            goto L_0x0018
        L_0x0014:
            org.apache.http.params.HttpParams r2 = r5.getRequestParams()
        L_0x0018:
            if (r9 == 0) goto L_0x001c
            r3 = r9
            goto L_0x0020
        L_0x001c:
            org.apache.http.params.HttpParams r3 = r5.getOverrideParams()
        L_0x0020:
            r4.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.client.ClientParamsStack.<init>(org.apache.http.impl.client.ClientParamsStack, org.apache.http.params.HttpParams, org.apache.http.params.HttpParams, org.apache.http.params.HttpParams, org.apache.http.params.HttpParams):void");
    }

    public final HttpParams getApplicationParams() {
        return this.applicationParams;
    }

    public final HttpParams getClientParams() {
        return this.clientParams;
    }

    public final HttpParams getRequestParams() {
        return this.requestParams;
    }

    public final HttpParams getOverrideParams() {
        return this.overrideParams;
    }

    @Override // org.apache.http.params.HttpParams
    public Object getParameter(String name) {
        if (name != null) {
            Object result = null;
            if (this.overrideParams != null) {
                result = this.overrideParams.getParameter(name);
            }
            if (result == null && this.requestParams != null) {
                result = this.requestParams.getParameter(name);
            }
            if (result == null && this.clientParams != null) {
                result = this.clientParams.getParameter(name);
            }
            if (result == null && this.applicationParams != null) {
                result = this.applicationParams.getParameter(name);
            }
            if (this.log.isDebugEnabled()) {
                Log log = this.log;
                log.debug("'" + name + "': " + result);
            }
            return result;
        }
        throw new IllegalArgumentException("Parameter name must not be null.");
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setParameter(String name, Object value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }

    @Override // org.apache.http.params.HttpParams
    public boolean removeParameter(String name) {
        throw new UnsupportedOperationException("Removing parameters in a stack is not supported.");
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams copy() {
        return this;
    }
}
