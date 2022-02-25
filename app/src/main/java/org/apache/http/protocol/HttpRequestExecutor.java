package org.apache.http.protocol;

import java.io.IOException;
import java.net.ProtocolException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.CoreProtocolPNames;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpRequestExecutor {
    protected boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
        int status;
        return ("HEAD".equalsIgnoreCase(request.getRequestLine().getMethod()) || (status = response.getStatusLine().getStatusCode()) < 200 || status == 204 || status == 304 || status == 205) ? false : true;
    }

    public HttpResponse execute(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (conn == null) {
            throw new IllegalArgumentException("Client connection may not be null");
        } else if (context != null) {
            try {
                HttpResponse response = doSendRequest(request, conn, context);
                if (response == null) {
                    return doReceiveResponse(request, conn, context);
                }
                return response;
            } catch (IOException ex) {
                conn.close();
                throw ex;
            } catch (RuntimeException ex2) {
                conn.close();
                throw ex2;
            } catch (HttpException ex3) {
                conn.close();
                throw ex3;
            }
        } else {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
    }

    public void preProcess(HttpRequest request, HttpProcessor processor, HttpContext context) throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (processor == null) {
            throw new IllegalArgumentException("HTTP processor may not be null");
        } else if (context != null) {
            processor.process(request, context);
        } else {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
    }

    protected HttpResponse doSendRequest(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (conn == null) {
            throw new IllegalArgumentException("HTTP connection may not be null");
        } else if (context != null) {
            HttpResponse response = null;
            context.setAttribute(ExecutionContext.HTTP_REQ_SENT, Boolean.FALSE);
            conn.sendRequestHeader(request);
            if (request instanceof HttpEntityEnclosingRequest) {
                boolean sendentity = true;
                ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
                if (((HttpEntityEnclosingRequest) request).expectContinue() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    conn.flush();
                    int tms = request.getParams().getIntParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 2000);
                    if (conn.isResponseAvailable(tms)) {
                        response = conn.receiveResponseHeader();
                        if (canResponseHaveBody(request, response)) {
                            conn.receiveResponseEntity(response);
                        }
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200) {
                            sendentity = false;
                        } else if (status == 100) {
                            response = null;
                        } else {
                            throw new ProtocolException("Unexpected response: " + response.getStatusLine());
                        }
                    }
                }
                if (sendentity) {
                    conn.sendRequestEntity((HttpEntityEnclosingRequest) request);
                }
            }
            conn.flush();
            context.setAttribute(ExecutionContext.HTTP_REQ_SENT, Boolean.TRUE);
            return response;
        } else {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
    }

    protected HttpResponse doReceiveResponse(HttpRequest request, HttpClientConnection conn, HttpContext context) throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (conn == null) {
            throw new IllegalArgumentException("HTTP connection may not be null");
        } else if (context != null) {
            HttpResponse response = null;
            int statuscode = 0;
            while (true) {
                if (response != null && statuscode >= 200) {
                    return response;
                }
                response = conn.receiveResponseHeader();
                if (canResponseHaveBody(request, response)) {
                    conn.receiveResponseEntity(response);
                }
                statuscode = response.getStatusLine().getStatusCode();
            }
        } else {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
    }

    public void postProcess(HttpResponse response, HttpProcessor processor, HttpContext context) throws HttpException, IOException {
        if (response == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        } else if (processor == null) {
            throw new IllegalArgumentException("HTTP processor may not be null");
        } else if (context != null) {
            processor.process(response, context);
        } else {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
    }
}
