package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public class Jikes {
    private static final int MAX_FILES_ON_COMMAND_LINE = 250;
    protected String command;
    protected JikesOutputParser jop;
    protected Project project;

    protected Jikes(JikesOutputParser jikesOutputParser, String str, Project project) {
        System.err.println("As of Ant 1.2 released in October 2000, the Jikes class");
        System.err.println("is considered to be dead code by the Ant developers and is unmaintained.");
        System.err.println("Don't use it!");
        this.jop = jikesOutputParser;
        this.command = str;
        this.project = project;
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.io.File] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void compile(java.lang.String[] r12) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Jikes.compile(java.lang.String[]):void");
    }
}
