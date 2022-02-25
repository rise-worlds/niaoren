package org.apache.tools.ant.taskdefs.compilers;

import java.io.File;
import java.util.Enumeration;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Apt;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class AptCompilerAdapter extends DefaultCompilerAdapter {
    private static final int APT_COMPILER_SUCCESS = 0;
    public static final String APT_ENTRY_POINT = "com.sun.tools.apt.Main";
    public static final String APT_METHOD_NAME = "process";

    protected Apt getApt() {
        return (Apt) getJavac();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAptCommandlineSwitches(Apt apt, Commandline commandline) {
        if (!apt.isCompile()) {
            commandline.createArgument().setValue("-nocompile");
        }
        String factory = apt.getFactory();
        if (factory != null) {
            commandline.createArgument().setValue("-factory");
            commandline.createArgument().setValue(factory);
        }
        Path factoryPath = apt.getFactoryPath();
        if (factoryPath != null) {
            commandline.createArgument().setValue("-factorypath");
            commandline.createArgument().setPath(factoryPath);
        }
        File preprocessDir = apt.getPreprocessDir();
        if (preprocessDir != null) {
            commandline.createArgument().setValue("-s");
            commandline.createArgument().setFile(preprocessDir);
        }
        Enumeration<Apt.Option> elements = apt.getOptions().elements();
        while (elements.hasMoreElements()) {
            Apt.Option nextElement = elements.nextElement();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-A");
            stringBuffer.append(nextElement.getName());
            if (nextElement.getValue() != null) {
                stringBuffer.append(SimpleComparison.f23609c);
                stringBuffer.append(nextElement.getValue());
            }
            commandline.createArgument().setValue(stringBuffer.toString());
        }
    }

    protected void setAptCommandlineSwitches(Commandline commandline) {
        setAptCommandlineSwitches(getApt(), commandline);
    }

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using apt compiler", 3);
        Commandline commandline = setupModernJavacCommand();
        setAptCommandlineSwitches(commandline);
        try {
            Class<?> cls = Class.forName(APT_ENTRY_POINT);
            return ((Integer) cls.getMethod(APT_METHOD_NAME, new String[0].getClass()).invoke(cls.newInstance(), commandline.getArguments())).intValue() == 0;
        } catch (BuildException e) {
            throw e;
        } catch (Exception e2) {
            throw new BuildException("Error starting apt compiler", e2, this.location);
        }
    }
}
