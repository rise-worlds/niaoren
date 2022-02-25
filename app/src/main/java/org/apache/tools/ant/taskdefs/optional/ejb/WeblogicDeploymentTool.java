package org.apache.tools.ant.taskdefs.optional.ejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;
import org.xml.sax.InputSource;

/* loaded from: classes2.dex */
public class WeblogicDeploymentTool extends GenericDeploymentTool {
    protected static final String COMPILER_EJB11 = "weblogic.ejbc";
    protected static final String COMPILER_EJB20 = "weblogic.ejbc20";
    protected static final String DEFAULT_COMPILER = "default";
    protected static final String DEFAULT_WL51_DTD_LOCATION = "/weblogic/ejb/deployment/xml/weblogic-ejb-jar.dtd";
    protected static final String DEFAULT_WL51_EJB11_DTD_LOCATION = "/weblogic/ejb/deployment/xml/ejb-jar.dtd";
    protected static final String DEFAULT_WL60_51_DTD_LOCATION = "/weblogic/ejb20/dd/xml/weblogic510-ejb-jar.dtd";
    protected static final String DEFAULT_WL60_DTD_LOCATION = "/weblogic/ejb20/dd/xml/weblogic600-ejb-jar.dtd";
    protected static final String DEFAULT_WL60_EJB11_DTD_LOCATION = "/weblogic/ejb20/dd/xml/ejb11-jar.dtd";
    protected static final String DEFAULT_WL60_EJB20_DTD_LOCATION = "/weblogic/ejb20/dd/xml/ejb20-jar.dtd";
    protected static final String DEFAULT_WL70_DTD_LOCATION = "/weblogic/ejb20/dd/xml/weblogic700-ejb-jar.dtd";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    public static final String PUBLICID_EJB11 = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN";
    public static final String PUBLICID_EJB20 = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN";
    public static final String PUBLICID_WEBLOGIC_EJB510 = "-//BEA Systems, Inc.//DTD WebLogic 5.1.0 EJB//EN";
    public static final String PUBLICID_WEBLOGIC_EJB600 = "-//BEA Systems, Inc.//DTD WebLogic 6.0.0 EJB//EN";
    public static final String PUBLICID_WEBLOGIC_EJB700 = "-//BEA Systems, Inc.//DTD WebLogic 7.0.0 EJB//EN";
    protected static final String WL_CMP_DD = "weblogic-cmp-rdbms-jar.xml";
    protected static final String WL_DD = "weblogic-ejb-jar.xml";
    private String ejb11DTD;
    private File outputDir;
    private String weblogicDTD;
    private String jarSuffix = ".jar";
    private boolean keepgenerated = false;
    private String ejbcClass = null;
    private String additionalArgs = "";
    private String additionalJvmArgs = "";
    private boolean keepGeneric = false;
    private String compiler = null;
    private boolean alwaysRebuild = true;
    private boolean noEJBC = false;
    private boolean newCMP = false;
    private Path wlClasspath = null;
    private Vector sysprops = new Vector();
    private Integer jvmDebugLevel = null;

    public void addSysproperty(Environment.Variable variable) {
        this.sysprops.add(variable);
    }

    public Path createWLClasspath() {
        if (this.wlClasspath == null) {
            this.wlClasspath = new Path(getTask().getProject());
        }
        return this.wlClasspath.createPath();
    }

    public void setOutputDir(File file) {
        this.outputDir = file;
    }

    public void setWLClasspath(Path path) {
        this.wlClasspath = path;
    }

    public void setCompiler(String str) {
        this.compiler = str;
    }

    public void setRebuild(boolean z) {
        this.alwaysRebuild = z;
    }

    public void setJvmDebugLevel(Integer num) {
        this.jvmDebugLevel = num;
    }

    public Integer getJvmDebugLevel() {
        return this.jvmDebugLevel;
    }

    public void setSuffix(String str) {
        this.jarSuffix = str;
    }

    public void setKeepgeneric(boolean z) {
        this.keepGeneric = z;
    }

    public void setKeepgenerated(String str) {
        this.keepgenerated = Boolean.valueOf(str).booleanValue();
    }

    public void setArgs(String str) {
        this.additionalArgs = str;
    }

    public void setJvmargs(String str) {
        this.additionalJvmArgs = str;
    }

    public void setEjbcClass(String str) {
        this.ejbcClass = str;
    }

    public String getEjbcClass() {
        return this.ejbcClass;
    }

    public void setWeblogicdtd(String str) {
        setEJBdtd(str);
    }

    public void setWLdtd(String str) {
        this.weblogicDTD = str;
    }

    public void setEJBdtd(String str) {
        this.ejb11DTD = str;
    }

    public void setOldCMP(boolean z) {
        this.newCMP = !z;
    }

    public void setNewCMP(boolean z) {
        this.newCMP = z;
    }

    public void setNoEJBC(boolean z) {
        this.noEJBC = z;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void registerKnownDTDs(DescriptorHandler descriptorHandler) {
        descriptorHandler.registerDTD("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN", DEFAULT_WL51_EJB11_DTD_LOCATION);
        descriptorHandler.registerDTD("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN", DEFAULT_WL60_EJB11_DTD_LOCATION);
        descriptorHandler.registerDTD("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN", this.ejb11DTD);
        descriptorHandler.registerDTD("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN", DEFAULT_WL60_EJB20_DTD_LOCATION);
    }

    protected DescriptorHandler getWeblogicDescriptorHandler(final File file) {
        DescriptorHandler descriptorHandler = new DescriptorHandler(getTask(), file) { // from class: org.apache.tools.ant.taskdefs.optional.ejb.WeblogicDeploymentTool.1
            @Override // org.apache.tools.ant.taskdefs.optional.ejb.DescriptorHandler
            protected void processElement() {
                if (this.currentElement.equals("type-storage")) {
                    String str = this.currentText;
                    this.ejbFiles.put(str, new File(file, str.substring(9, str.length())));
                }
            }
        };
        descriptorHandler.registerDTD(PUBLICID_WEBLOGIC_EJB510, DEFAULT_WL51_DTD_LOCATION);
        descriptorHandler.registerDTD(PUBLICID_WEBLOGIC_EJB510, DEFAULT_WL60_51_DTD_LOCATION);
        descriptorHandler.registerDTD(PUBLICID_WEBLOGIC_EJB600, DEFAULT_WL60_DTD_LOCATION);
        descriptorHandler.registerDTD(PUBLICID_WEBLOGIC_EJB700, DEFAULT_WL70_DTD_LOCATION);
        descriptorHandler.registerDTD(PUBLICID_WEBLOGIC_EJB510, this.weblogicDTD);
        descriptorHandler.registerDTD(PUBLICID_WEBLOGIC_EJB600, this.weblogicDTD);
        Iterator it = getConfig().dtdLocations.iterator();
        while (it.hasNext()) {
            EjbJar.DTDLocation dTDLocation = (EjbJar.DTDLocation) it.next();
            descriptorHandler.registerDTD(dTDLocation.getPublicId(), dTDLocation.getLocation());
        }
        return descriptorHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public void addVendorFiles(Hashtable hashtable, String str) {
        File file = getConfig().descriptorDir;
        File file2 = new File(file, str + WL_DD);
        if (file2.exists()) {
            hashtable.put("META-INF/weblogic-ejb-jar.xml", file2);
            if (!this.newCMP) {
                log("The old method for locating CMP files has been DEPRECATED.", 3);
                log("Please adjust your weblogic descriptor and set newCMP=\"true\" to use the new CMP descriptor inclusion mechanism. ", 3);
                File file3 = getConfig().descriptorDir;
                File file4 = new File(file3, str + WL_CMP_DD);
                if (file4.exists()) {
                    hashtable.put("META-INF/weblogic-cmp-rdbms-jar.xml", file4);
                    return;
                }
                return;
            }
            try {
                SAXParserFactory newInstance = SAXParserFactory.newInstance();
                newInstance.setValidating(true);
                SAXParser newSAXParser = newInstance.newSAXParser();
                DescriptorHandler weblogicDescriptorHandler = getWeblogicDescriptorHandler(((File) hashtable.get("META-INF/ejb-jar.xml")).getParentFile());
                newSAXParser.parse(new InputSource(new FileInputStream(file2)), weblogicDescriptorHandler);
                Hashtable files = weblogicDescriptorHandler.getFiles();
                Enumeration keys = files.keys();
                while (keys.hasMoreElements()) {
                    String str2 = (String) keys.nextElement();
                    hashtable.put(str2, files.get(str2));
                }
            } catch (Exception e) {
                throw new BuildException("Exception while adding Vendor specific files: " + e.toString(), e);
            }
        } else {
            log("Unable to locate weblogic deployment descriptor. It was expected to be in " + file2.getPath(), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public File getVendorOutputJarFile(String str) {
        File destDir = getDestDir();
        return new File(destDir, str + this.jarSuffix);
    }

    private void buildWeblogicJar(File file, File file2, String str) {
        if (this.noEJBC) {
            try {
                FILE_UTILS.copyFile(file, file2);
                if (!this.keepgenerated) {
                    file.delete();
                }
            } catch (IOException e) {
                throw new BuildException("Unable to write EJB jar", e);
            }
        } else {
            String str2 = this.ejbcClass;
            try {
                Java java = new Java(getTask());
                java.setTaskName("ejbc");
                java.createJvmarg().setLine(this.additionalJvmArgs);
                if (!this.sysprops.isEmpty()) {
                    Enumeration elements = this.sysprops.elements();
                    while (elements.hasMoreElements()) {
                        java.addSysproperty((Environment.Variable) elements.nextElement());
                    }
                }
                if (getJvmDebugLevel() != null) {
                    Commandline.Argument createJvmarg = java.createJvmarg();
                    createJvmarg.setLine(" -Dweblogic.StdoutSeverityLevel=" + this.jvmDebugLevel);
                }
                if (str2 == null) {
                    if ("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN".equals(str)) {
                        str2 = COMPILER_EJB11;
                    } else if ("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN".equals(str)) {
                        str2 = COMPILER_EJB20;
                    } else {
                        log("Unrecognized publicId " + str + " - using EJB 1.1 compiler", 1);
                        str2 = COMPILER_EJB11;
                    }
                }
                java.setClassname(str2);
                java.createArg().setLine(this.additionalArgs);
                if (this.keepgenerated) {
                    java.createArg().setValue("-keepgenerated");
                }
                if (this.compiler == null) {
                    String property = getTask().getProject().getProperty("build.compiler");
                    if (property != null && property.equals("jikes")) {
                        java.createArg().setValue("-compiler");
                        java.createArg().setValue("jikes");
                    }
                } else if (!this.compiler.equals("default")) {
                    java.createArg().setValue("-compiler");
                    java.createArg().setLine(this.compiler);
                }
                Path combinedClasspath = getCombinedClasspath();
                if (!(this.wlClasspath == null || combinedClasspath == null || combinedClasspath.toString().trim().length() <= 0)) {
                    java.createArg().setValue("-classpath");
                    java.createArg().setPath(combinedClasspath);
                }
                java.createArg().setValue(file.getPath());
                if (this.outputDir == null) {
                    java.createArg().setValue(file2.getPath());
                } else {
                    java.createArg().setValue(this.outputDir.getPath());
                }
                Path path = this.wlClasspath;
                if (path == null) {
                    path = getCombinedClasspath();
                }
                java.setFork(true);
                if (path != null) {
                    java.setClasspath(path);
                }
                log("Calling " + str2 + " for " + file.toString(), 3);
                if (java.executeJava() != 0) {
                    throw new BuildException("Ejbc reported an error");
                }
            } catch (Exception e2) {
                throw new BuildException("Exception while calling " + str2 + ". Details: " + e2.toString(), e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public void writeJar(String str, File file, Hashtable hashtable, String str2) throws BuildException {
        File vendorOutputJarFile = super.getVendorOutputJarFile(str);
        super.writeJar(str, vendorOutputJarFile, hashtable, str2);
        if (this.alwaysRebuild || isRebuildRequired(vendorOutputJarFile, file)) {
            buildWeblogicJar(vendorOutputJarFile, file, str2);
        }
        if (!this.keepGeneric) {
            log("deleting generic jar " + vendorOutputJarFile.toString(), 3);
            vendorOutputJarFile.delete();
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void validateConfigured() throws BuildException {
        super.validateConfigured();
    }

    /* JADX WARN: Not initialized variable reg: 16, insn: 0x030e: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY]), block:B:141:0x030c */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x030c: MOVE  (r6 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:141:0x030c */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0310: MOVE  (r16 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:141:0x030c */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0314 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0322 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x031b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01dd A[Catch: all -> 0x0228, IOException -> 0x022c, ClassNotFoundException -> 0x0232, TryCatch #18 {IOException -> 0x022c, ClassNotFoundException -> 0x0232, all -> 0x0228, blocks: (B:49:0x019c, B:50:0x01a3, B:52:0x01a9, B:54:0x01bd, B:57:0x01ca, B:58:0x01d0, B:59:0x01d3, B:61:0x01dd, B:62:0x0205, B:63:0x0209, B:64:0x0215, B:66:0x021c, B:67:0x0220), top: B:182:0x019c }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0205 A[Catch: all -> 0x0228, IOException -> 0x022c, ClassNotFoundException -> 0x0232, TryCatch #18 {IOException -> 0x022c, ClassNotFoundException -> 0x0232, all -> 0x0228, blocks: (B:49:0x019c, B:50:0x01a3, B:52:0x01a9, B:54:0x01bd, B:57:0x01ca, B:58:0x01d0, B:59:0x01d3, B:61:0x01dd, B:62:0x0205, B:63:0x0209, B:64:0x0215, B:66:0x021c, B:67:0x0220), top: B:182:0x019c }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x021c A[Catch: all -> 0x0228, IOException -> 0x022c, ClassNotFoundException -> 0x0232, LOOP:4: B:64:0x0215->B:66:0x021c, LOOP_END, TryCatch #18 {IOException -> 0x022c, ClassNotFoundException -> 0x0232, all -> 0x0228, blocks: (B:49:0x019c, B:50:0x01a3, B:52:0x01a9, B:54:0x01bd, B:57:0x01ca, B:58:0x01d0, B:59:0x01d3, B:61:0x01dd, B:62:0x0205, B:63:0x0209, B:64:0x0215, B:66:0x021c, B:67:0x0220), top: B:182:0x019c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean isRebuildRequired(java.io.File r23, java.io.File r24) {
        /*
            Method dump skipped, instructions count: 832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.optional.ejb.WeblogicDeploymentTool.isRebuildRequired(java.io.File, java.io.File):boolean");
    }

    protected ClassLoader getClassLoaderFromJar(File file) throws IOException {
        Path path = new Path(getTask().getProject());
        path.setLocation(file);
        Path combinedClasspath = getCombinedClasspath();
        if (combinedClasspath != null) {
            path.append(combinedClasspath);
        }
        return getTask().getProject().createClassLoader(path);
    }
}
