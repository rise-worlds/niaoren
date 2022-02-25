package org.apache.tools.ant.taskdefs.optional.j2ee;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;

/* loaded from: classes2.dex */
public class JonasHotDeploymentTool extends GenericHotDeploymentTool implements HotDeploymentTool {
    protected static final String DEFAULT_ORB = "RMI";
    private static final String JONAS_DEPLOY_CLASS_NAME = "org.objectweb.jonas.adm.JonasAdmin";
    private static final String[] VALID_ACTIONS = {HotDeploymentTool.ACTION_DELETE, HotDeploymentTool.ACTION_DEPLOY, HotDeploymentTool.ACTION_LIST, HotDeploymentTool.ACTION_UNDEPLOY, "update"};
    private String davidHost;
    private int davidPort;
    private File jonasroot;
    private String orb = null;

    public void setDavidhost(String str) {
        this.davidHost = str;
    }

    public void setDavidport(int i) {
        this.davidPort = i;
    }

    public void setJonasroot(File file) {
        this.jonasroot = file;
    }

    public void setOrb(String str) {
        this.orb = str;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.j2ee.AbstractHotDeploymentTool
    public Path getClasspath() {
        Path classpath = super.getClasspath();
        if (classpath == null) {
            classpath = new Path(getTask().getProject());
        }
        if (this.orb != null) {
            File file = this.jonasroot;
            String file2 = new File(file, "lib/" + this.orb + "_jonas.jar").toString();
            String file3 = new File(this.jonasroot, "config/").toString();
            Project project = classpath.getProject();
            classpath.append(new Path(project, file2 + File.pathSeparator + file3));
        }
        return classpath;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.j2ee.GenericHotDeploymentTool, org.apache.tools.ant.taskdefs.optional.j2ee.AbstractHotDeploymentTool, org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool
    public void validateAttributes() throws BuildException {
        Java java = getJava();
        String action = getTask().getAction();
        if (action == null) {
            throw new BuildException("The \"action\" attribute must be set");
        } else if (isActionValid()) {
            if (getClassName() == null) {
                setClassName(JONAS_DEPLOY_CLASS_NAME);
            }
            File file = this.jonasroot;
            if (file == null || file.isDirectory()) {
                Commandline.Argument createJvmarg = java.createJvmarg();
                createJvmarg.setValue("-Dinstall.root=" + this.jonasroot);
                Commandline.Argument createJvmarg2 = java.createJvmarg();
                createJvmarg2.setValue("-Djava.security.policy=" + this.jonasroot + "/config/java.policy");
                if ("DAVID".equals(this.orb)) {
                    java.createJvmarg().setValue("-Dorg.omg.CORBA.ORBClass=org.objectweb.david.libs.binding.orbs.iiop.IIOPORB");
                    java.createJvmarg().setValue("-Dorg.omg.CORBA.ORBSingletonClass=org.objectweb.david.libs.binding.orbs.ORBSingletonClass");
                    java.createJvmarg().setValue("-Djavax.rmi.CORBA.StubClass=org.objectweb.david.libs.stub_factories.rmi.StubDelegate");
                    java.createJvmarg().setValue("-Djavax.rmi.CORBA.PortableRemoteObjectClass=org.objectweb.david.libs.binding.rmi.ORBPortableRemoteObjectDelegate");
                    java.createJvmarg().setValue("-Djavax.rmi.CORBA.UtilClass=org.objectweb.david.libs.helpers.RMIUtilDelegate");
                    java.createJvmarg().setValue("-Ddavid.CosNaming.default_method=0");
                    java.createJvmarg().setValue("-Ddavid.rmi.ValueHandlerClass=com.sun.corba.se.internal.io.ValueHandlerImpl");
                    if (this.davidHost != null) {
                        Commandline.Argument createJvmarg3 = java.createJvmarg();
                        createJvmarg3.setValue("-Ddavid.CosNaming.default_host=" + this.davidHost);
                    }
                    if (this.davidPort != 0) {
                        Commandline.Argument createJvmarg4 = java.createJvmarg();
                        createJvmarg4.setValue("-Ddavid.CosNaming.default_port=" + this.davidPort);
                    }
                }
            }
            if (getServer() != null) {
                Commandline.Argument createArg = java.createArg();
                createArg.setLine("-n " + getServer());
            }
            if (action.equals(HotDeploymentTool.ACTION_DEPLOY) || action.equals("update") || action.equals("redeploy")) {
                Commandline.Argument createArg2 = java.createArg();
                createArg2.setLine("-a " + getTask().getSource());
            } else if (action.equals(HotDeploymentTool.ACTION_DELETE) || action.equals(HotDeploymentTool.ACTION_UNDEPLOY)) {
                Commandline.Argument createArg3 = java.createArg();
                createArg3.setLine("-r " + getTask().getSource());
            } else if (action.equals(HotDeploymentTool.ACTION_LIST)) {
                java.createArg().setValue("-l");
            }
        } else {
            throw new BuildException("Invalid action \"" + action + "\" passed");
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.j2ee.GenericHotDeploymentTool, org.apache.tools.ant.taskdefs.optional.j2ee.AbstractHotDeploymentTool
    protected boolean isActionValid() {
        String action = getTask().getAction();
        int i = 0;
        while (true) {
            String[] strArr = VALID_ACTIONS;
            if (i >= strArr.length) {
                return false;
            }
            if (action.equals(strArr[i])) {
                return true;
            }
            i++;
        }
    }
}
