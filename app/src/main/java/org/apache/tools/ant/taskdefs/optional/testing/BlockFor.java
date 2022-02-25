package org.apache.tools.ant.taskdefs.optional.testing;

import org.apache.tools.ant.taskdefs.WaitFor;

/* loaded from: classes2.dex */
public class BlockFor extends WaitFor {
    private String text;

    public BlockFor() {
        super("blockfor");
        this.text = getTaskName() + " timed out";
    }

    public BlockFor(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.WaitFor
    public void processTimeout() throws BuildTimeoutException {
        super.processTimeout();
        throw new BuildTimeoutException(this.text, getLocation());
    }

    public void addText(String str) {
        this.text = getProject().replaceProperties(str);
    }
}
