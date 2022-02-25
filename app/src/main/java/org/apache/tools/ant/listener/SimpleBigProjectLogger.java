package org.apache.tools.ant.listener;

import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.NoBannerLogger;

/* loaded from: classes2.dex */
public class SimpleBigProjectLogger extends NoBannerLogger {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.NoBannerLogger
    public String extractTargetName(BuildEvent buildEvent) {
        String extractTargetName = super.extractTargetName(buildEvent);
        String extractProjectName = extractProjectName(buildEvent);
        if (extractProjectName == null || extractTargetName == null) {
            return extractTargetName;
        }
        return extractProjectName + FilenameUtils.EXTENSION_SEPARATOR + extractTargetName;
    }
}
