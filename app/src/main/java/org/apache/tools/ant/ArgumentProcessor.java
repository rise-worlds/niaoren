package org.apache.tools.ant;

import java.io.PrintStream;
import java.util.List;

/* loaded from: classes2.dex */
public interface ArgumentProcessor {
    boolean handleArg(List<String> list);

    boolean handleArg(Project project, List<String> list);

    void prepareConfigure(Project project, List<String> list);

    void printUsage(PrintStream printStream);

    int readArguments(String[] strArr, int i);
}
