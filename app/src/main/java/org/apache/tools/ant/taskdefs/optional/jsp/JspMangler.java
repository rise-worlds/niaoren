package org.apache.tools.ant.taskdefs.optional.jsp;

import java.io.File;

/* loaded from: classes2.dex */
public interface JspMangler {
    String mapJspToJavaName(File file);

    String mapPath(String str);
}
