package org.apache.tools.ant.dispatch;

import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public abstract class DispatchTask extends Task implements Dispatchable {
    private String action;

    @Override // org.apache.tools.ant.dispatch.Dispatchable
    public String getActionParameterName() {
        return "action";
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }
}
