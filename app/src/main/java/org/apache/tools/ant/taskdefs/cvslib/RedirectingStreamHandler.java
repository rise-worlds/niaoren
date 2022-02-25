package org.apache.tools.ant.taskdefs.cvslib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.PumpStreamHandler;

/* loaded from: classes2.dex */
class RedirectingStreamHandler extends PumpStreamHandler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public RedirectingStreamHandler(ChangeLogParser changeLogParser) {
        super(new RedirectingOutputStream(changeLogParser), new ByteArrayOutputStream());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getErrors() {
        try {
            return ((ByteArrayOutputStream) getErr()).toString(HTTP.ASCII);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.apache.tools.ant.taskdefs.PumpStreamHandler, org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void stop() {
        super.stop();
        try {
            getErr().close();
            getOut().close();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }
}
