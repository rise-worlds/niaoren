package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.LineOrientedOutputStream;

/* loaded from: classes2.dex */
public class LogOutputStream extends LineOrientedOutputStream {
    private int level;

    /* renamed from: pc */
    private ProjectComponent f14754pc;

    public LogOutputStream(ProjectComponent projectComponent) {
        this.level = 2;
        this.f14754pc = projectComponent;
    }

    public LogOutputStream(Task task, int i) {
        this((ProjectComponent) task, i);
    }

    public LogOutputStream(ProjectComponent projectComponent, int i) {
        this(projectComponent);
        this.level = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.util.LineOrientedOutputStream
    public void processBuffer() {
        try {
            super.processBuffer();
        } catch (IOException e) {
            throw new RuntimeException("Impossible IOException caught: " + e);
        }
    }

    @Override // org.apache.tools.ant.util.LineOrientedOutputStream
    protected void processLine(String str) {
        processLine(str, this.level);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processLine(String str, int i) {
        this.f14754pc.log(str, i);
    }

    public int getMessageLevel() {
        return this.level;
    }
}
