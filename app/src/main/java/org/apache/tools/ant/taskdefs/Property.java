package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.property.ResolvePropertyMap;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class Property extends Task {
    private File basedir;
    protected Path classpath;
    protected String env;
    private Project fallback;
    protected File file;
    protected String name;
    protected String prefix;
    private boolean prefixValues;
    protected Reference ref;
    private boolean relative;
    protected String resource;
    private Object untypedValue;
    protected URL url;
    protected boolean userProperty;
    protected String value;
    private boolean valueAttributeUsed;

    public Property() {
        this(false);
    }

    protected Property(boolean z) {
        this(z, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Property(boolean z, Project project) {
        this.valueAttributeUsed = false;
        this.relative = false;
        this.prefixValues = false;
        this.userProperty = z;
        this.fallback = project;
    }

    public void setRelative(boolean z) {
        this.relative = z;
    }

    public void setBasedir(File file) {
        this.basedir = file;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(File file) {
        if (this.relative) {
            internalSetValue(file);
        } else {
            setValue(file.getAbsolutePath());
        }
    }

    public void setValue(Object obj) {
        this.valueAttributeUsed = true;
        internalSetValue(obj);
    }

    private void internalSetValue(Object obj) {
        this.untypedValue = obj;
        this.value = obj == null ? null : obj.toString();
    }

    public void setValue(String str) {
        setValue((Object) str);
    }

    public void addText(String str) {
        if (!this.valueAttributeUsed) {
            String replaceProperties = getProject().replaceProperties(str);
            String value = getValue();
            if (value != null) {
                replaceProperties = value + replaceProperties;
            }
            internalSetValue(replaceProperties);
        } else if (str.trim().length() > 0) {
            throw new BuildException("can't combine nested text with value attribute");
        }
    }

    public String getValue() {
        return this.value;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setPrefix(String str) {
        this.prefix = str;
        if (str != null && !str.endsWith(Consts.f23430h)) {
            this.prefix += Consts.f23430h;
        }
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefixValues(boolean z) {
        this.prefixValues = z;
    }

    public boolean getPrefixValues() {
        return this.prefixValues;
    }

    public void setRefid(Reference reference) {
        this.ref = reference;
    }

    public Reference getRefid() {
        return this.ref;
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public String getResource() {
        return this.resource;
    }

    public void setEnvironment(String str) {
        this.env = str;
    }

    public String getEnvironment() {
        return this.env;
    }

    public void setClasspath(Path path) {
        Path path2 = this.classpath;
        if (path2 == null) {
            this.classpath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference reference) {
        createClasspath().setRefid(reference);
    }

    public Path getClasspath() {
        return this.classpath;
    }

    @Deprecated
    public void setUserProperty(boolean z) {
        log("DEPRECATED: Ignoring request to set user property in Property task.", 1);
    }

    public String toString() {
        String str = this.value;
        return str == null ? "" : str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Reference reference;
        Object obj;
        if (getProject() != null) {
            if (this.name != null) {
                if (this.untypedValue == null && this.ref == null) {
                    throw new BuildException("You must specify value, location or refid with the name attribute", getLocation());
                }
            } else if (this.url == null && this.file == null && this.resource == null && this.env == null) {
                throw new BuildException("You must specify url, file, resource or environment when not using the name attribute", getLocation());
            }
            if (this.url == null && this.file == null && this.resource == null && this.prefix != null) {
                throw new BuildException("Prefix is only valid when loading from a url, file or resource", getLocation());
            }
            String str = this.name;
            if (!(str == null || (obj = this.untypedValue) == null)) {
                if (this.relative) {
                    try {
                        addProperty(this.name, FileUtils.getRelativePath(this.basedir != null ? this.basedir : getProject().getBaseDir(), obj instanceof File ? (File) obj : new File(obj.toString())).replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar));
                    } catch (Exception e) {
                        throw new BuildException(e, getLocation());
                    }
                } else {
                    addProperty(str, obj);
                }
            }
            File file = this.file;
            if (file != null) {
                loadFile(file);
            }
            URL url = this.url;
            if (url != null) {
                loadUrl(url);
            }
            String str2 = this.resource;
            if (str2 != null) {
                loadResource(str2);
            }
            String str3 = this.env;
            if (str3 != null) {
                loadEnvironment(str3);
            }
            String str4 = this.name;
            if (str4 != null && (reference = this.ref) != null) {
                try {
                    addProperty(str4, reference.getReferencedObject(getProject()).toString());
                } catch (BuildException e2) {
                    Project project = this.fallback;
                    if (project != null) {
                        addProperty(this.name, this.ref.getReferencedObject(project).toString());
                        return;
                    }
                    throw e2;
                }
            }
        } else {
            throw new IllegalStateException("project has not been set");
        }
    }

    protected void loadUrl(URL url) throws BuildException {
        Properties properties = new Properties();
        log("Loading " + url, 3);
        try {
            InputStream openStream = url.openStream();
            loadProperties(properties, openStream, url.getFile().endsWith(".xml"));
            if (openStream != null) {
                openStream.close();
            }
            addProperties(properties);
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    private void loadProperties(Properties properties, InputStream inputStream, boolean z) throws IOException {
        if (z) {
            properties.loadFromXML(inputStream);
        } else {
            properties.load(inputStream);
        }
    }

    protected void loadFile(File file) throws BuildException {
        Throwable th;
        Properties properties = new Properties();
        log("Loading " + file.getAbsolutePath(), 3);
        try {
            if (file.exists()) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    loadProperties(properties, fileInputStream, file.getName().endsWith(".xml"));
                    FileUtils.close(fileInputStream);
                    addProperties(properties);
                } catch (Throwable th3) {
                    th = th3;
                    FileUtils.close(fileInputStream);
                    throw th;
                }
            } else {
                log("Unable to find property file: " + file.getAbsolutePath(), 3);
            }
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x009f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void loadResource(java.lang.String r7) {
        /*
            r6 = this;
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Resource Loading "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r2 = 3
            r6.log(r1, r2)
            r1 = 1
            r2 = 0
            r3 = 0
            org.apache.tools.ant.types.Path r4 = r6.classpath     // Catch: all -> 0x0083, IOException -> 0x0087
            if (r4 == 0) goto L_0x0035
            org.apache.tools.ant.Project r3 = r6.getProject()     // Catch: all -> 0x002d, IOException -> 0x0032
            org.apache.tools.ant.types.Path r4 = r6.classpath     // Catch: all -> 0x002d, IOException -> 0x0032
            org.apache.tools.ant.AntClassLoader r3 = r3.createClassLoader(r4)     // Catch: all -> 0x002d, IOException -> 0x0032
            r4 = 1
            goto L_0x003f
        L_0x002d:
            r7 = move-exception
            r3 = r2
            r4 = 1
            goto L_0x0096
        L_0x0032:
            r7 = move-exception
            r3 = r2
            goto L_0x008a
        L_0x0035:
            java.lang.Class r4 = r6.getClass()     // Catch: all -> 0x0083, IOException -> 0x0087
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch: all -> 0x0083, IOException -> 0x0087
            r3 = r4
            r4 = 0
        L_0x003f:
            if (r3 != 0) goto L_0x0046
            java.io.InputStream r2 = java.lang.ClassLoader.getSystemResourceAsStream(r7)     // Catch: all -> 0x007e, IOException -> 0x0080
            goto L_0x004a
        L_0x0046:
            java.io.InputStream r2 = r3.getResourceAsStream(r7)     // Catch: all -> 0x007e, IOException -> 0x0080
        L_0x004a:
            if (r2 == 0) goto L_0x0059
            java.lang.String r1 = ".xml"
            boolean r7 = r7.endsWith(r1)     // Catch: all -> 0x007e, IOException -> 0x0080
            r6.loadProperties(r0, r2, r7)     // Catch: all -> 0x007e, IOException -> 0x0080
            r6.addProperties(r0)     // Catch: all -> 0x007e, IOException -> 0x0080
            goto L_0x006d
        L_0x0059:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: all -> 0x007e, IOException -> 0x0080
            r0.<init>()     // Catch: all -> 0x007e, IOException -> 0x0080
            java.lang.String r5 = "Unable to find resource "
            r0.append(r5)     // Catch: all -> 0x007e, IOException -> 0x0080
            r0.append(r7)     // Catch: all -> 0x007e, IOException -> 0x0080
            java.lang.String r7 = r0.toString()     // Catch: all -> 0x007e, IOException -> 0x0080
            r6.log(r7, r1)     // Catch: all -> 0x007e, IOException -> 0x0080
        L_0x006d:
            if (r2 == 0) goto L_0x0074
            r2.close()     // Catch: IOException -> 0x0073
            goto L_0x0074
        L_0x0073:
        L_0x0074:
            if (r4 == 0) goto L_0x007d
            if (r3 == 0) goto L_0x007d
            org.apache.tools.ant.AntClassLoader r3 = (org.apache.tools.ant.AntClassLoader) r3
            r3.cleanup()
        L_0x007d:
            return
        L_0x007e:
            r7 = move-exception
            goto L_0x0096
        L_0x0080:
            r7 = move-exception
            r1 = r4
            goto L_0x008a
        L_0x0083:
            r7 = move-exception
            r3 = r2
            r4 = 0
            goto L_0x0096
        L_0x0087:
            r7 = move-exception
            r3 = r2
            r1 = 0
        L_0x008a:
            org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException     // Catch: all -> 0x0094
            org.apache.tools.ant.Location r4 = r6.getLocation()     // Catch: all -> 0x0094
            r0.<init>(r7, r4)     // Catch: all -> 0x0094
            throw r0     // Catch: all -> 0x0094
        L_0x0094:
            r7 = move-exception
            r4 = r1
        L_0x0096:
            if (r2 == 0) goto L_0x009d
            r2.close()     // Catch: IOException -> 0x009c
            goto L_0x009d
        L_0x009c:
        L_0x009d:
            if (r4 == 0) goto L_0x00a6
            if (r3 == 0) goto L_0x00a6
            org.apache.tools.ant.AntClassLoader r3 = (org.apache.tools.ant.AntClassLoader) r3
            r3.cleanup()
        L_0x00a6:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Property.loadResource(java.lang.String):void");
    }

    protected void loadEnvironment(String str) {
        Properties properties = new Properties();
        if (!str.endsWith(Consts.f23430h)) {
            str = str + Consts.f23430h;
        }
        log("Loading Environment " + str, 3);
        for (Map.Entry<String, String> entry : Execute.getEnvironmentVariables().entrySet()) {
            properties.put(str + ((Object) entry.getKey()), entry.getValue());
        }
        addProperties(properties);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addProperties(Properties properties) {
        HashMap hashMap = new HashMap(properties);
        resolveAllProperties(hashMap);
        for (Object obj : hashMap.keySet()) {
            if (obj instanceof String) {
                String str = (String) obj;
                if (this.prefix != null) {
                    str = this.prefix + str;
                }
                addProperty(str, hashMap.get(obj));
            }
        }
    }

    protected void addProperty(String str, String str2) {
        addProperty(str, (Object) str2);
    }

    protected void addProperty(String str, Object obj) {
        PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(getProject());
        if (!this.userProperty) {
            propertyHelper.setNewProperty(str, obj);
        } else if (propertyHelper.getUserProperty(str) == null) {
            propertyHelper.setInheritedProperty(str, obj);
        } else {
            log("Override ignored for " + str, 3);
        }
    }

    private void resolveAllProperties(Map map) throws BuildException {
        PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(getProject());
        new ResolvePropertyMap(getProject(), propertyHelper, propertyHelper.getExpanders()).resolveAllProperties(map, getPrefix(), getPrefixValues());
    }
}
