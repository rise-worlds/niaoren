package org.apache.tools.ant;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface BuildListener extends EventListener {
    void buildFinished(BuildEvent buildEvent);

    void buildStarted(BuildEvent buildEvent);

    void messageLogged(BuildEvent buildEvent);

    void targetFinished(BuildEvent buildEvent);

    void targetStarted(BuildEvent buildEvent);

    void taskFinished(BuildEvent buildEvent);

    void taskStarted(BuildEvent buildEvent);
}
