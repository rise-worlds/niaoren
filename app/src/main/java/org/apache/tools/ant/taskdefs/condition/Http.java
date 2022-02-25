package org.apache.tools.ant.taskdefs.condition;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;

/* loaded from: classes2.dex */
public class Http extends ProjectComponent implements Condition {
    private static final String DEFAULT_REQUEST_METHOD = "GET";
    private static final int ERROR_BEGINS = 400;
    private String spec = null;
    private String requestMethod = "GET";
    private int errorsBeginAt = 400;

    public void setUrl(String str) {
        this.spec = str;
    }

    public void setErrorsBeginAt(int i) {
        this.errorsBeginAt = i;
    }

    public void setRequestMethod(String str) {
        this.requestMethod = str == null ? "GET" : str.toUpperCase(Locale.ENGLISH);
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (this.spec != null) {
            log("Checking for " + this.spec, 3);
            try {
                try {
                    URLConnection openConnection = new URL(this.spec).openConnection();
                    if (!(openConnection instanceof HttpURLConnection)) {
                        return true;
                    }
                    HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                    httpURLConnection.setRequestMethod(this.requestMethod);
                    int responseCode = httpURLConnection.getResponseCode();
                    log("Result code for " + this.spec + " was " + responseCode, 3);
                    if (responseCode > 0) {
                        if (responseCode < this.errorsBeginAt) {
                            return true;
                        }
                    }
                    return false;
                } catch (ProtocolException e) {
                    throw new BuildException("Invalid HTTP protocol: " + this.requestMethod, e);
                } catch (IOException unused) {
                    return false;
                }
            } catch (MalformedURLException e2) {
                throw new BuildException("Badly formed URL: " + this.spec, e2);
            }
        } else {
            throw new BuildException("No url specified in http condition");
        }
    }
}
