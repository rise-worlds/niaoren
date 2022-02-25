package org.apache.tools.ant.taskdefs.optional.ejb;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.xml.parsers.SAXParser;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.taskdefs.optional.sos.SOSCmd;
import org.apache.tools.ant.types.Path;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class JonasDeploymentTool extends GenericDeploymentTool {
    protected static final String DAVID_ORB = "DAVID";
    protected static final String EJB_JAR_1_1_DTD = "ejb-jar_1_1.dtd";
    protected static final String EJB_JAR_1_1_PUBLIC_ID = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN";
    protected static final String EJB_JAR_2_0_DTD = "ejb-jar_2_0.dtd";
    protected static final String EJB_JAR_2_0_PUBLIC_ID = "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN";
    protected static final String GENIC_CLASS = "org.objectweb.jonas_ejb.genic.GenIC";
    protected static final String JEREMIE_ORB = "JEREMIE";
    protected static final String JONAS_DD = "jonas-ejb-jar.xml";
    protected static final String JONAS_EJB_JAR_2_4_DTD = "jonas-ejb-jar_2_4.dtd";
    protected static final String JONAS_EJB_JAR_2_4_PUBLIC_ID = "-//ObjectWeb//DTD JOnAS 2.4//EN";
    protected static final String JONAS_EJB_JAR_2_5_DTD = "jonas-ejb-jar_2_5.dtd";
    protected static final String JONAS_EJB_JAR_2_5_PUBLIC_ID = "-//ObjectWeb//DTD JOnAS 2.5//EN";
    protected static final String OLD_GENIC_CLASS_1 = "org.objectweb.jonas_ejb.tools.GenWholeIC";
    protected static final String OLD_GENIC_CLASS_2 = "org.objectweb.jonas_ejb.tools.GenIC";
    protected static final String RMI_ORB = "RMI";
    private String additionalargs;
    private String descriptorName;
    private String javac;
    private String javacopts;
    private String jonasDescriptorName;
    private File jonasroot;
    private String orb;
    private File outputdir;
    private String rmicopts;
    private boolean keepgenerated = false;
    private boolean nocompil = false;
    private boolean novalidation = false;
    private boolean secpropag = false;
    private boolean verbose = false;
    private boolean keepgeneric = false;
    private String suffix = ".jar";
    private boolean nogenic = false;

    public void setKeepgenerated(boolean z) {
        this.keepgenerated = z;
    }

    public void setAdditionalargs(String str) {
        this.additionalargs = str;
    }

    public void setNocompil(boolean z) {
        this.nocompil = z;
    }

    public void setNovalidation(boolean z) {
        this.novalidation = z;
    }

    public void setJavac(String str) {
        this.javac = str;
    }

    public void setJavacopts(String str) {
        this.javacopts = str;
    }

    public void setRmicopts(String str) {
        this.rmicopts = str;
    }

    public void setSecpropag(boolean z) {
        this.secpropag = z;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setJonasroot(File file) {
        this.jonasroot = file;
    }

    public void setKeepgeneric(boolean z) {
        this.keepgeneric = z;
    }

    public void setJarsuffix(String str) {
        this.suffix = str;
    }

    public void setOrb(String str) {
        this.orb = str;
    }

    public void setNogenic(boolean z) {
        this.nogenic = z;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool, org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void processDescriptor(String str, SAXParser sAXParser) {
        this.descriptorName = str;
        log("JOnAS Deployment Tool processing: " + this.descriptorName, 3);
        super.processDescriptor(this.descriptorName, sAXParser);
        if (this.outputdir != null) {
            log("Deleting temp output directory '" + this.outputdir + "'.", 3);
            deleteAllFiles(this.outputdir);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public void writeJar(String str, File file, Hashtable hashtable, String str2) throws BuildException {
        File vendorOutputJarFile = super.getVendorOutputJarFile(str);
        super.writeJar(str, vendorOutputJarFile, hashtable, str2);
        addGenICGeneratedFiles(vendorOutputJarFile, hashtable);
        super.writeJar(str, getVendorOutputJarFile(str), hashtable, str2);
        if (!this.keepgeneric) {
            log("Deleting generic JAR " + vendorOutputJarFile.toString(), 3);
            vendorOutputJarFile.delete();
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void addVendorFiles(Hashtable hashtable, String str) {
        this.jonasDescriptorName = getJonasDescriptorName();
        File file = new File(getConfig().descriptorDir, this.jonasDescriptorName);
        if (file.exists()) {
            hashtable.put("META-INF/jonas-ejb-jar.xml", file);
            return;
        }
        log("Unable to locate the JOnAS deployment descriptor. It was expected to be in: " + file.getPath() + Consts.f23430h, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    public File getVendorOutputJarFile(String str) {
        File destDir = getDestDir();
        return new File(destDir, str + this.suffix);
    }

    private String getJonasDescriptorName() {
        String str;
        String str2;
        String str3;
        int lastIndexOf = this.descriptorName.lastIndexOf(File.separatorChar);
        boolean z = false;
        if (lastIndexOf != -1) {
            int i = lastIndexOf + 1;
            str2 = this.descriptorName.substring(0, i);
            str = this.descriptorName.substring(i);
        } else {
            str2 = "";
            str = this.descriptorName;
        }
        if (str.startsWith("ejb-jar.xml")) {
            return str2 + JONAS_DD;
        }
        int indexOf = this.descriptorName.indexOf(getConfig().baseNameTerminator, lastIndexOf);
        if (indexOf < 0) {
            int lastIndexOf2 = this.descriptorName.lastIndexOf(46) - 1;
            indexOf = lastIndexOf2 < 0 ? this.descriptorName.length() - 1 : lastIndexOf2;
            z = true;
        }
        int i2 = indexOf + 1;
        String substring = this.descriptorName.substring(lastIndexOf + 1, i2);
        String substring2 = this.descriptorName.substring(i2);
        if (z) {
            str3 = str2 + "jonas-" + substring + ".xml";
        } else {
            str3 = str2 + substring + "jonas-" + substring2;
        }
        log("Standard EJB descriptor name: " + this.descriptorName, 3);
        log("JOnAS-specific descriptor name: " + str3, 3);
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getJarBaseName(java.lang.String r4) {
        /*
            r3 = this;
            org.apache.tools.ant.taskdefs.optional.ejb.EjbJar$Config r0 = r3.getConfig()
            org.apache.tools.ant.taskdefs.optional.ejb.EjbJar$NamingScheme r0 = r0.namingScheme
            java.lang.String r0 = r0.getValue()
            java.lang.String r1 = "descriptor"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0042
            org.apache.tools.ant.taskdefs.optional.ejb.EjbJar$Config r0 = r3.getConfig()
            java.lang.String r0 = r0.baseNameTerminator
            int r0 = r4.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto L_0x0042
            r0 = 92
            r2 = 47
            java.lang.String r0 = r4.replace(r0, r2)
            int r0 = r0.lastIndexOf(r2)
            if (r0 == r1) goto L_0x0034
            java.lang.String r2 = ".xml"
            int r0 = r4.indexOf(r2, r0)
            goto L_0x003a
        L_0x0034:
            java.lang.String r0 = ".xml"
            int r0 = r4.indexOf(r0)
        L_0x003a:
            if (r0 == r1) goto L_0x0042
            r1 = 0
            java.lang.String r0 = r4.substring(r1, r0)
            goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = super.getJarBaseName(r4)
        L_0x0049:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = "JAR base name: "
            r4.append(r1)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r1 = 3
            r3.log(r4, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.optional.ejb.JonasDeploymentTool.getJarBaseName(java.lang.String):java.lang.String");
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void registerKnownDTDs(DescriptorHandler descriptorHandler) {
        descriptorHandler.registerDTD("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN", this.jonasroot + File.separator + "xml" + File.separator + EJB_JAR_1_1_DTD);
        descriptorHandler.registerDTD("-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN", this.jonasroot + File.separator + "xml" + File.separator + EJB_JAR_2_0_DTD);
        descriptorHandler.registerDTD(JONAS_EJB_JAR_2_4_PUBLIC_ID, this.jonasroot + File.separator + "xml" + File.separator + JONAS_EJB_JAR_2_4_DTD);
        descriptorHandler.registerDTD(JONAS_EJB_JAR_2_5_PUBLIC_ID, this.jonasroot + File.separator + "xml" + File.separator + JONAS_EJB_JAR_2_5_DTD);
    }

    private void addGenICGeneratedFiles(File file, Hashtable hashtable) {
        File file2;
        if (!this.nogenic) {
            Java java = new Java(getTask());
            java.setTaskName("genic");
            java.setFork(true);
            java.createJvmarg().setValue("-Dinstall.root=" + this.jonasroot);
            String str = this.jonasroot + File.separator + "config";
            if (new File(str, "java.policy").exists()) {
                java.createJvmarg().setValue("-Djava.security.policy=" + file2.toString());
            }
            try {
                this.outputdir = createTempDir();
                log("Using temporary output directory: " + this.outputdir, 3);
                java.createArg().setValue("-d");
                java.createArg().setFile(this.outputdir);
                Enumeration keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    new File(this.outputdir + File.separator + ((String) keys.nextElement())).getParentFile().mkdirs();
                }
                log("Worked around a bug of GenIC 2.5.", 3);
                Path combinedClasspath = getCombinedClasspath();
                if (combinedClasspath == null) {
                    combinedClasspath = new Path(getTask().getProject());
                }
                combinedClasspath.append(new Path(combinedClasspath.getProject(), str));
                combinedClasspath.append(new Path(combinedClasspath.getProject(), this.outputdir.toString()));
                if (this.orb != null) {
                    combinedClasspath.append(new Path(combinedClasspath.getProject(), this.jonasroot + File.separator + "lib" + File.separator + this.orb + "_jonas.jar"));
                }
                log("Using classpath: " + combinedClasspath.toString(), 3);
                java.setClasspath(combinedClasspath);
                String genicClassName = getGenicClassName(combinedClasspath);
                if (genicClassName != null) {
                    log("Using '" + genicClassName + "' GenIC class.", 3);
                    java.setClassname(genicClassName);
                    if (this.keepgenerated) {
                        java.createArg().setValue("-keepgenerated");
                    }
                    if (this.nocompil) {
                        java.createArg().setValue("-nocompil");
                    }
                    if (this.novalidation) {
                        java.createArg().setValue("-novalidation");
                    }
                    if (this.javac != null) {
                        java.createArg().setValue("-javac");
                        java.createArg().setLine(this.javac);
                    }
                    String str2 = this.javacopts;
                    if (str2 != null && !str2.equals("")) {
                        java.createArg().setValue("-javacopts");
                        java.createArg().setLine(this.javacopts);
                    }
                    String str3 = this.rmicopts;
                    if (str3 != null && !str3.equals("")) {
                        java.createArg().setValue("-rmicopts");
                        java.createArg().setLine(this.rmicopts);
                    }
                    if (this.secpropag) {
                        java.createArg().setValue("-secpropag");
                    }
                    if (this.verbose) {
                        java.createArg().setValue(SOSCmd.FLAG_VERBOSE);
                    }
                    if (this.additionalargs != null) {
                        java.createArg().setValue(this.additionalargs);
                    }
                    java.createArg().setValue("-noaddinjar");
                    java.createArg().setValue(file.getPath());
                    log("Calling " + genicClassName + " for " + getConfig().descriptorDir + File.separator + this.descriptorName + Consts.f23430h, 3);
                    if (java.executeJava() != 0) {
                        log("Deleting temp output directory '" + this.outputdir + "'.", 3);
                        deleteAllFiles(this.outputdir);
                        if (!this.keepgeneric) {
                            log("Deleting generic JAR " + file.toString(), 3);
                            file.delete();
                        }
                        throw new BuildException("GenIC reported an error.");
                    }
                    addAllFiles(this.outputdir, "", hashtable);
                    return;
                }
                log("Cannot find GenIC class in classpath.", 0);
                throw new BuildException("GenIC class not found, please check the classpath.");
            } catch (IOException e) {
                throw new BuildException("Cannot create temp dir: " + e.getMessage(), e);
            }
        }
    }

    String getGenicClassName(Path path) {
        AntClassLoader antClassLoader;
        Throwable th;
        log("Looking for GenIC class in classpath: " + path.toString(), 3);
        try {
            antClassLoader = path.getProject().createClassLoader(path);
            try {
                try {
                    antClassLoader.loadClass(GENIC_CLASS);
                    log("Found GenIC class 'org.objectweb.jonas_ejb.genic.GenIC' in classpath.", 3);
                    if (antClassLoader != null) {
                        antClassLoader.cleanup();
                    }
                    return GENIC_CLASS;
                } catch (ClassNotFoundException unused) {
                    log("GenIC class 'org.objectweb.jonas_ejb.genic.GenIC' not found in classpath.", 3);
                    try {
                        antClassLoader.loadClass(OLD_GENIC_CLASS_1);
                        log("Found GenIC class 'org.objectweb.jonas_ejb.tools.GenWholeIC' in classpath.", 3);
                        if (antClassLoader != null) {
                            antClassLoader.cleanup();
                        }
                        return OLD_GENIC_CLASS_1;
                    } catch (ClassNotFoundException unused2) {
                        log("GenIC class 'org.objectweb.jonas_ejb.tools.GenWholeIC' not found in classpath.", 3);
                        try {
                            antClassLoader.loadClass(OLD_GENIC_CLASS_2);
                            log("Found GenIC class 'org.objectweb.jonas_ejb.tools.GenIC' in classpath.", 3);
                            if (antClassLoader != null) {
                                antClassLoader.cleanup();
                            }
                            return OLD_GENIC_CLASS_2;
                        } catch (ClassNotFoundException unused3) {
                            log("GenIC class 'org.objectweb.jonas_ejb.tools.GenIC' not found in classpath.", 3);
                            if (antClassLoader != null) {
                                antClassLoader.cleanup();
                            }
                            return null;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (antClassLoader != null) {
                    antClassLoader.cleanup();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            antClassLoader = null;
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.GenericDeploymentTool
    protected void checkConfiguration(String str, SAXParser sAXParser) throws BuildException {
        File file = this.jonasroot;
        if (file == null) {
            throw new BuildException("The jonasroot attribut is not set.");
        } else if (file.isDirectory()) {
            String str2 = this.orb;
            if (str2 == null || str2.equals(RMI_ORB) || this.orb.equals(JEREMIE_ORB) || this.orb.equals(DAVID_ORB)) {
                String str3 = this.additionalargs;
                if (str3 == null || !str3.equals("")) {
                    String str4 = this.javac;
                    if (str4 != null && str4.equals("")) {
                        throw new BuildException("Empty javac attribut.");
                    }
                    return;
                }
                throw new BuildException("Empty additionalargs attribut.");
            }
            throw new BuildException("The orb attribut '" + this.orb + "' is not valid (must be either " + RMI_ORB + ", " + JEREMIE_ORB + " or " + DAVID_ORB + ").");
        } else {
            throw new BuildException("The jonasroot attribut '" + this.jonasroot + "' is not a valid directory.");
        }
    }

    private File createTempDir() throws IOException {
        File createTempFile = File.createTempFile("genic", null, null);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            return createTempFile;
        }
        throw new IOException("Cannot create the temporary directory '" + createTempFile + "'.");
    }

    private void deleteAllFiles(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteAllFiles(file2);
            }
        }
        file.delete();
    }

    private void addAllFiles(File file, String str, Hashtable hashtable) {
        if (!file.exists()) {
            throw new IllegalArgumentException();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                addAllFiles(listFiles[i], str.length() > 0 ? str + File.separator + listFiles[i].getName() : listFiles[i].getName(), hashtable);
            }
        } else {
            hashtable.put(str, file);
        }
    }
}
