package org.apache.tools.ant.taskdefs.optional.ejb;

import java.io.File;
import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;

/* loaded from: classes2.dex */
public class JbossDeploymentTool extends GenericDeploymentTool {
    protected static final String JBOSS_CMP10D = "jaws.xml";
    protected static final String JBOSS_CMP20D = "jbosscmp-jdbc.xml";
    protected static final String JBOSS_DD = "jboss.xml";
    private String jarSuffix = ".jar";

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void validateConfigured() throws BuildException {
    }

    public void setSuffix(String str) {
        this.jarSuffix = str;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void addVendorFiles(Hashtable hashtable, String str) {
        File file = getConfig().descriptorDir;
        File file2 = new File(file, str + JBOSS_DD);
        if (file2.exists()) {
            hashtable.put("META-INF/jboss.xml", file2);
            String str2 = JBOSS_CMP10D;
            if (EjbJar.CMPVersion.CMP2_0.equals(getParent().getCmpversion())) {
                str2 = JBOSS_CMP20D;
            }
            File file3 = getConfig().descriptorDir;
            File file4 = new File(file3, str + str2);
            if (file4.exists()) {
                hashtable.put("META-INF/" + str2, file4);
                return;
            }
            log("Unable to locate jboss cmp descriptor. It was expected to be in " + file4.getPath(), 3);
            return;
        }
        log("Unable to locate jboss deployment descriptor. It was expected to be in " + file2.getPath(), 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public File getVendorOutputJarFile(String str) {
        if (getDestDir() == null && getParent().getDestdir() == null) {
            throw new BuildException("DestDir not specified");
        } else if (getDestDir() == null) {
            File destdir = getParent().getDestdir();
            return new File(destdir, str + this.jarSuffix);
        } else {
            File destDir = getDestDir();
            return new File(destDir, str + this.jarSuffix);
        }
    }

    private EjbJar getParent() {
        return (EjbJar) getTask();
    }
}
