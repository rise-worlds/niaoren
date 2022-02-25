package org.apache.tools.ant.listener;

import java.io.File;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.SubBuildListener;
import org.apache.tools.ant.util.StringUtils;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class BigProjectLogger extends SimpleBigProjectLogger implements SubBuildListener {
    public static final String FOOTER = "======================================================================";
    public static final String HEADER = "======================================================================";
    private volatile boolean subBuildStartedRaised = false;
    private final Object subBuildLock = new Object();

    protected String getFooter() {
        return "======================================================================";
    }

    protected String getHeader() {
        return "======================================================================";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.DefaultLogger
    public String getBuildFailedMessage() {
        return super.getBuildFailedMessage() + TimestampedLogger.SPACER + getTimestamp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.DefaultLogger
    public String getBuildSuccessfulMessage() {
        return super.getBuildSuccessfulMessage() + TimestampedLogger.SPACER + getTimestamp();
    }

    @Override // org.apache.tools.ant.NoBannerLogger, org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent buildEvent) {
        maybeRaiseSubBuildStarted(buildEvent);
        super.targetStarted(buildEvent);
    }

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent buildEvent) {
        maybeRaiseSubBuildStarted(buildEvent);
        super.taskStarted(buildEvent);
    }

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent buildEvent) {
        maybeRaiseSubBuildStarted(buildEvent);
        subBuildFinished(buildEvent);
        super.buildFinished(buildEvent);
    }

    @Override // org.apache.tools.ant.NoBannerLogger, org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent buildEvent) {
        maybeRaiseSubBuildStarted(buildEvent);
        super.messageLogged(buildEvent);
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildStarted(BuildEvent buildEvent) {
        File baseDir;
        String str;
        String extractNameOrDefault = extractNameOrDefault(buildEvent);
        Project project = buildEvent.getProject();
        if ((project == null ? null : project.getBaseDir()) == null) {
            str = "With no base directory";
        } else {
            str = "In " + baseDir.getAbsolutePath();
        }
        printMessage(StringUtils.LINE_SEP + getHeader() + StringUtils.LINE_SEP + "Entering project " + extractNameOrDefault + StringUtils.LINE_SEP + str + StringUtils.LINE_SEP + getFooter(), this.out, buildEvent.getPriority());
    }

    protected String extractNameOrDefault(BuildEvent buildEvent) {
        String extractProjectName = extractProjectName(buildEvent);
        if (extractProjectName == null) {
            return "";
        }
        return Typography.f21049a + extractProjectName + Typography.f21049a;
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildFinished(BuildEvent buildEvent) {
        String extractNameOrDefault = extractNameOrDefault(buildEvent);
        String str = buildEvent.getException() != null ? "failing " : "";
        printMessage(StringUtils.LINE_SEP + getHeader() + StringUtils.LINE_SEP + "Exiting " + str + "project " + extractNameOrDefault + StringUtils.LINE_SEP + getFooter(), this.out, buildEvent.getPriority());
    }

    private void maybeRaiseSubBuildStarted(BuildEvent buildEvent) {
        if (!this.subBuildStartedRaised) {
            synchronized (this.subBuildLock) {
                if (!this.subBuildStartedRaised) {
                    this.subBuildStartedRaised = true;
                    subBuildStarted(buildEvent);
                }
            }
        }
    }
}
