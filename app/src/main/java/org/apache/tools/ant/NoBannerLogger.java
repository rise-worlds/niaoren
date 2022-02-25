package org.apache.tools.ant;

import java.io.PrintStream;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class NoBannerLogger extends DefaultLogger {
    protected String targetName;

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public synchronized void targetStarted(BuildEvent buildEvent) {
        this.targetName = extractTargetName(buildEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String extractTargetName(BuildEvent buildEvent) {
        return buildEvent.getTarget().getName();
    }

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public synchronized void targetFinished(BuildEvent buildEvent) {
        this.targetName = null;
    }

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent buildEvent) {
        if (buildEvent.getPriority() <= this.msgOutputLevel && buildEvent.getMessage() != null && !"".equals(buildEvent.getMessage().trim())) {
            synchronized (this) {
                if (this.targetName != null) {
                    PrintStream printStream = this.out;
                    printStream.println(StringUtils.LINE_SEP + this.targetName + ":");
                    this.targetName = null;
                }
            }
            super.messageLogged(buildEvent);
        }
    }
}
