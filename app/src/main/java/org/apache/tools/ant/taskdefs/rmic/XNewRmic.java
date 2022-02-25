package org.apache.tools.ant.taskdefs.rmic;

import org.apache.tools.ant.types.Commandline;

/* loaded from: classes2.dex */
public class XNewRmic extends ForkingSunRmic {
    public static final String COMPILER_NAME = "xnew";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    public Commandline setupRmicCommand() {
        return super.setupRmicCommand(new String[]{"-Xnew"});
    }
}
