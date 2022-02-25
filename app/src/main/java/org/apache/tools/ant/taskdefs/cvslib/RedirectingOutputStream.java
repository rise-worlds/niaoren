package org.apache.tools.ant.taskdefs.cvslib;

import org.apache.tools.ant.util.LineOrientedOutputStream;

/* loaded from: classes2.dex */
class RedirectingOutputStream extends LineOrientedOutputStream {
    private final ChangeLogParser parser;

    public RedirectingOutputStream(ChangeLogParser changeLogParser) {
        this.parser = changeLogParser;
    }

    @Override // org.apache.tools.ant.util.LineOrientedOutputStream
    protected void processLine(String str) {
        this.parser.stdout(str);
    }
}
