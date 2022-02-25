package org.apache.tools.ant.taskdefs.optional.ejb;

import java.io.File;
import java.util.Hashtable;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class WeblogicTOPLinkDeploymentTool extends WeblogicDeploymentTool {
    private static final String TL_DTD_LOC = "http://www.objectpeople.com/tlwl/dtd/toplink-cmp_2_5_1.dtd";
    private String toplinkDTD;
    private String toplinkDescriptor;

    public void setToplinkdescriptor(String str) {
        this.toplinkDescriptor = str;
    }

    public void setToplinkdtd(String str) {
        this.toplinkDTD = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public DescriptorHandler getDescriptorHandler(File file) {
        DescriptorHandler descriptorHandler = super.getDescriptorHandler(file);
        String str = this.toplinkDTD;
        if (str != null) {
            descriptorHandler.registerDTD("-//The Object People, Inc.//DTD TOPLink for WebLogic CMP 2.5.1//EN", str);
        } else {
            descriptorHandler.registerDTD("-//The Object People, Inc.//DTD TOPLink for WebLogic CMP 2.5.1//EN", TL_DTD_LOC);
        }
        return descriptorHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.WeblogicDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public void addVendorFiles(Hashtable hashtable, String str) {
        super.addVendorFiles(hashtable, str);
        File file = getConfig().descriptorDir;
        File file2 = new File(file, str + this.toplinkDescriptor);
        if (file2.exists()) {
            hashtable.put("META-INF/" + this.toplinkDescriptor, file2);
            return;
        }
        log("Unable to locate toplink deployment descriptor. It was expected to be in " + file2.getPath(), 1);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.WeblogicDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void validateConfigured() throws BuildException {
        super.validateConfigured();
        if (this.toplinkDescriptor == null) {
            throw new BuildException("The toplinkdescriptor attribute must be specified");
        }
    }
}
