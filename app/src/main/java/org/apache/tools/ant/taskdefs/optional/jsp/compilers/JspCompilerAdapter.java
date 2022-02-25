package org.apache.tools.ant.taskdefs.optional.jsp.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.optional.jsp.JspC;
import org.apache.tools.ant.taskdefs.optional.jsp.JspMangler;

/* loaded from: classes2.dex */
public interface JspCompilerAdapter {
    JspMangler createMangler();

    boolean execute() throws BuildException;

    boolean implementsOwnDependencyChecking();

    void setJspc(JspC jspC);
}
