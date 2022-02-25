package org.apache.tools.ant;

/* loaded from: classes2.dex */
public interface SubBuildListener extends BuildListener {
    void subBuildFinished(BuildEvent buildEvent);

    void subBuildStarted(BuildEvent buildEvent);
}
