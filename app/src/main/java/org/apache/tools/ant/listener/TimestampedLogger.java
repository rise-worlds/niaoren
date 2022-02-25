package org.apache.tools.ant.listener;

import org.apache.tools.ant.DefaultLogger;

/* loaded from: classes2.dex */
public class TimestampedLogger extends DefaultLogger {
    public static final String SPACER = " - at ";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.DefaultLogger
    public String getBuildFailedMessage() {
        return super.getBuildFailedMessage() + SPACER + getTimestamp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.DefaultLogger
    public String getBuildSuccessfulMessage() {
        return super.getBuildSuccessfulMessage() + SPACER + getTimestamp();
    }
}
