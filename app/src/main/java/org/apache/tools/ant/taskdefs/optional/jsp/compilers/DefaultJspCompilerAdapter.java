package org.apache.tools.ant.taskdefs.optional.jsp.compilers;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.jsp.JspC;
import org.apache.tools.ant.types.CommandlineJava;

/* loaded from: classes2.dex */
public abstract class DefaultJspCompilerAdapter implements JspCompilerAdapter {
    private static String lSep = System.getProperty("line.separator");
    protected JspC owner;

    @Override // org.apache.tools.ant.taskdefs.optional.jsp.compilers.JspCompilerAdapter
    public boolean implementsOwnDependencyChecking() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logAndAddFilesToCompile(JspC jspC, Vector vector, CommandlineJava commandlineJava) {
        jspC.log("Compilation " + commandlineJava.describeJavaCommand(), 3);
        StringBuffer stringBuffer = new StringBuffer("File");
        if (vector.size() != 1) {
            stringBuffer.append("s");
        }
        stringBuffer.append(" to be compiled:");
        stringBuffer.append(lSep);
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            String str = (String) elements.nextElement();
            commandlineJava.createArgument().setValue(str);
            stringBuffer.append("    ");
            stringBuffer.append(str);
            stringBuffer.append(lSep);
        }
        jspC.log(stringBuffer.toString(), 3);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.jsp.compilers.JspCompilerAdapter
    public void setJspc(JspC jspC) {
        this.owner = jspC;
    }

    public JspC getJspc() {
        return this.owner;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addArg(CommandlineJava commandlineJava, String str) {
        if (str != null && str.length() != 0) {
            commandlineJava.createArgument().setValue(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addArg(CommandlineJava commandlineJava, String str, String str2) {
        if (str2 != null) {
            commandlineJava.createArgument().setValue(str);
            commandlineJava.createArgument().setValue(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addArg(CommandlineJava commandlineJava, String str, File file) {
        if (file != null) {
            commandlineJava.createArgument().setValue(str);
            commandlineJava.createArgument().setFile(file);
        }
    }

    public Project getProject() {
        return getJspc().getProject();
    }
}
