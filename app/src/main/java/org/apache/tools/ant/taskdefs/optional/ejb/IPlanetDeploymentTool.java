package org.apache.tools.ant.taskdefs.optional.ejb;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import javax.xml.parsers.SAXParser;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;
import org.apache.tools.ant.taskdefs.optional.ejb.IPlanetEjbc;
import org.xml.sax.SAXException;

/* loaded from: classes2.dex */
public class IPlanetDeploymentTool extends GenericDeploymentTool {
    private static final String IAS_DD = "ias-ejb-jar.xml";
    private String descriptorName;
    private String displayName;
    private String iasDescriptorName;
    private File iashome;
    private String jarSuffix = ".jar";
    private boolean keepgenerated = false;
    private boolean debug = false;

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected String getPublicId() {
        return null;
    }

    public void setIashome(File file) {
        this.iashome = file;
    }

    public void setKeepgenerated(boolean z) {
        this.keepgenerated = z;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public void setSuffix(String str) {
        this.jarSuffix = str;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public void setGenericJarSuffix(String str) {
        log("Since a generic JAR file is not created during processing, the iPlanet Deployment Tool does not support the \"genericjarsuffix\" attribute.  It will be ignored.", 1);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void processDescriptor(String str, SAXParser sAXParser) {
        this.descriptorName = str;
        this.iasDescriptorName = null;
        log("iPlanet Deployment Tool processing: " + str + " (and " + getIasDescriptorName() + ")", 3);
        super.processDescriptor(str, sAXParser);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void checkConfiguration(String str, SAXParser sAXParser) throws BuildException {
        if (!str.substring(str.lastIndexOf(File.separatorChar) + 1).equals("ejb-jar.xml") || getConfig().baseJarName != null) {
            File file = new File(getConfig().descriptorDir, getIasDescriptorName());
            if (!file.exists() || !file.isFile()) {
                throw new BuildException("The iAS-specific EJB descriptor (" + file + ") was not found.", getLocation());
            }
            File file2 = this.iashome;
            if (file2 != null && !file2.isDirectory()) {
                throw new BuildException("If \"iashome\" is specified, it must be a valid directory (it was set to " + this.iashome + ").", getLocation());
            }
            return;
        }
        throw new BuildException("No name specified for the completed JAR file.  The EJB descriptor should be prepended with the JAR name or it should be specified using the attribute \"basejarname\" in the \"ejbjar\" task.", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected Hashtable parseEjbFiles(String str, SAXParser sAXParser) throws IOException, SAXException {
        IPlanetEjbc iPlanetEjbc = new IPlanetEjbc(new File(getConfig().descriptorDir, str), new File(getConfig().descriptorDir, getIasDescriptorName()), getConfig().srcDir, getCombinedClasspath().toString(), sAXParser);
        iPlanetEjbc.setRetainSource(this.keepgenerated);
        iPlanetEjbc.setDebugOutput(this.debug);
        File file = this.iashome;
        if (file != null) {
            iPlanetEjbc.setIasHomeDir(file);
        }
        if (getConfig().dtdLocations != null) {
            Iterator it = getConfig().dtdLocations.iterator();
            while (it.hasNext()) {
                EjbJar.DTDLocation dTDLocation = (EjbJar.DTDLocation) it.next();
                iPlanetEjbc.registerDTD(dTDLocation.getPublicId(), dTDLocation.getLocation());
            }
        }
        try {
            iPlanetEjbc.execute();
            this.displayName = iPlanetEjbc.getDisplayName();
            Hashtable ejbFiles = iPlanetEjbc.getEjbFiles();
            String[] cmpDescriptors = iPlanetEjbc.getCmpDescriptors();
            if (cmpDescriptors.length > 0) {
                File file2 = getConfig().descriptorDir;
                String substring = str.substring(0, str.lastIndexOf(File.separator) + 1);
                for (int i = 0; i < cmpDescriptors.length; i++) {
                    String substring2 = cmpDescriptors[i].substring(cmpDescriptors[i].lastIndexOf(47) + 1);
                    File file3 = new File(file2, substring + substring2);
                    if (file3.exists()) {
                        ejbFiles.put(cmpDescriptors[i], file3);
                    } else {
                        throw new BuildException("The CMP descriptor file (" + file3 + ") could not be found.", getLocation());
                    }
                }
            }
            return ejbFiles;
        } catch (IPlanetEjbc.EjbcException e) {
            throw new BuildException("An error has occurred while trying to execute the iAS ejbc utility", e, getLocation());
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void addVendorFiles(Hashtable hashtable, String str) {
        hashtable.put("META-INF/ias-ejb-jar.xml", new File(getConfig().descriptorDir, getIasDescriptorName()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public File getVendorOutputJarFile(String str) {
        File destDir = getDestDir();
        File file = new File(destDir, str + this.jarSuffix);
        log("JAR file name: " + file.toString(), 3);
        return file;
    }

    private String getIasDescriptorName() {
        String str;
        String str2;
        String str3 = this.iasDescriptorName;
        if (str3 != null) {
            return str3;
        }
        String str4 = "";
        int lastIndexOf = this.descriptorName.lastIndexOf(File.separatorChar);
        if (lastIndexOf != -1) {
            str4 = this.descriptorName.substring(0, lastIndexOf + 1);
        }
        int i = lastIndexOf + 1;
        if (this.descriptorName.substring(i).equals("ejb-jar.xml")) {
            str2 = "";
            str = "ejb-jar.xml";
        } else {
            int indexOf = this.descriptorName.indexOf(getConfig().baseNameTerminator, lastIndexOf);
            if (indexOf < 0 && this.descriptorName.lastIndexOf(46) - 1 < 0) {
                indexOf = this.descriptorName.length() - 1;
            }
            int i2 = indexOf + 1;
            str2 = this.descriptorName.substring(i, i2);
            str = this.descriptorName.substring(i2);
        }
        this.iasDescriptorName = str4 + str2 + "ias-" + str;
        return this.iasDescriptorName;
    }
}
