package org.apache.tools.ant.taskdefs;

import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* loaded from: classes2.dex */
public abstract class JDBCTask extends Task {
    private static final int HASH_TABLE_SIZE = 3;
    private static Hashtable<String, AntClassLoader> LOADER_MAP = new Hashtable<>(3);
    private Path classpath;
    private AntClassLoader loader;
    private boolean caching = true;
    private boolean autocommit = false;
    private String driver = null;
    private String url = null;
    private String userId = null;
    private String password = null;
    private String rdbms = null;
    private String version = null;
    private boolean failOnConnectionError = true;
    private List<Property> connectionProperties = new ArrayList();

    public void setClasspath(Path path) {
        this.classpath = path;
    }

    public void setCaching(boolean z) {
        this.caching = z;
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

    public void setDriver(String str) {
        this.driver = str.trim();
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setAutocommit(boolean z) {
        this.autocommit = z;
    }

    public void setRdbms(String str) {
        this.rdbms = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setFailOnConnectionError(boolean z) {
        this.failOnConnectionError = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0094, code lost:
        if (r7.indexOf(com.ctetin.expandabletextviewlibrary.ExpandableTextView.f6958c + r6.version) < 0) goto L_0x0096;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isValidRdbms(java.sql.Connection r7) {
        /*
            r6 = this;
            java.lang.String r0 = r6.rdbms
            r1 = 1
            if (r0 != 0) goto L_0x000a
            java.lang.String r0 = r6.version
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r0 = 0
            java.sql.DatabaseMetaData r7 = r7.getMetaData()     // Catch: SQLException -> 0x00b3
            java.lang.String r2 = r6.rdbms     // Catch: SQLException -> 0x00b3
            r3 = 3
            if (r2 == 0) goto L_0x0051
            java.lang.String r2 = r7.getDatabaseProductName()     // Catch: SQLException -> 0x00b3
            java.lang.String r2 = r2.toLowerCase()     // Catch: SQLException -> 0x00b3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: SQLException -> 0x00b3
            r4.<init>()     // Catch: SQLException -> 0x00b3
            java.lang.String r5 = "RDBMS = "
            r4.append(r5)     // Catch: SQLException -> 0x00b3
            r4.append(r2)     // Catch: SQLException -> 0x00b3
            java.lang.String r4 = r4.toString()     // Catch: SQLException -> 0x00b3
            r6.log(r4, r3)     // Catch: SQLException -> 0x00b3
            if (r2 == 0) goto L_0x003a
            java.lang.String r4 = r6.rdbms     // Catch: SQLException -> 0x00b3
            int r2 = r2.indexOf(r4)     // Catch: SQLException -> 0x00b3
            if (r2 >= 0) goto L_0x0051
        L_0x003a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: SQLException -> 0x00b3
            r7.<init>()     // Catch: SQLException -> 0x00b3
            java.lang.String r1 = "Not the required RDBMS: "
            r7.append(r1)     // Catch: SQLException -> 0x00b3
            java.lang.String r1 = r6.rdbms     // Catch: SQLException -> 0x00b3
            r7.append(r1)     // Catch: SQLException -> 0x00b3
            java.lang.String r7 = r7.toString()     // Catch: SQLException -> 0x00b3
            r6.log(r7, r3)     // Catch: SQLException -> 0x00b3
            return r0
        L_0x0051:
            java.lang.String r2 = r6.version     // Catch: SQLException -> 0x00b3
            if (r2 == 0) goto L_0x00b2
            java.lang.String r7 = r7.getDatabaseProductVersion()     // Catch: SQLException -> 0x00b3
            java.util.Locale r2 = java.util.Locale.ENGLISH     // Catch: SQLException -> 0x00b3
            java.lang.String r7 = r7.toLowerCase(r2)     // Catch: SQLException -> 0x00b3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: SQLException -> 0x00b3
            r2.<init>()     // Catch: SQLException -> 0x00b3
            java.lang.String r4 = "Version = "
            r2.append(r4)     // Catch: SQLException -> 0x00b3
            r2.append(r7)     // Catch: SQLException -> 0x00b3
            java.lang.String r2 = r2.toString()     // Catch: SQLException -> 0x00b3
            r6.log(r2, r3)     // Catch: SQLException -> 0x00b3
            if (r7 == 0) goto L_0x0096
            java.lang.String r2 = r6.version     // Catch: SQLException -> 0x00b3
            boolean r2 = r7.startsWith(r2)     // Catch: SQLException -> 0x00b3
            if (r2 != 0) goto L_0x00b2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: SQLException -> 0x00b3
            r2.<init>()     // Catch: SQLException -> 0x00b3
            java.lang.String r4 = " "
            r2.append(r4)     // Catch: SQLException -> 0x00b3
            java.lang.String r4 = r6.version     // Catch: SQLException -> 0x00b3
            r2.append(r4)     // Catch: SQLException -> 0x00b3
            java.lang.String r2 = r2.toString()     // Catch: SQLException -> 0x00b3
            int r7 = r7.indexOf(r2)     // Catch: SQLException -> 0x00b3
            if (r7 >= 0) goto L_0x00b2
        L_0x0096:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: SQLException -> 0x00b3
            r7.<init>()     // Catch: SQLException -> 0x00b3
            java.lang.String r1 = "Not the required version: \""
            r7.append(r1)     // Catch: SQLException -> 0x00b3
            java.lang.String r1 = r6.version     // Catch: SQLException -> 0x00b3
            r7.append(r1)     // Catch: SQLException -> 0x00b3
            java.lang.String r1 = "\""
            r7.append(r1)     // Catch: SQLException -> 0x00b3
            java.lang.String r7 = r7.toString()     // Catch: SQLException -> 0x00b3
            r6.log(r7, r3)     // Catch: SQLException -> 0x00b3
            return r0
        L_0x00b2:
            return r1
        L_0x00b3:
            java.lang.String r7 = "Failed to obtain required RDBMS information"
            r6.log(r7, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.JDBCTask.isValidRdbms(java.sql.Connection):boolean");
    }

    protected static Hashtable<String, AntClassLoader> getLoaderMap() {
        return LOADER_MAP;
    }

    protected AntClassLoader getLoader() {
        return this.loader;
    }

    public void addConnectionProperty(Property property) {
        this.connectionProperties.add(property);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Connection getConnection() throws BuildException {
        if (this.userId == null) {
            throw new BuildException("UserId attribute must be set!", getLocation());
        } else if (this.password == null) {
            throw new BuildException("Password attribute must be set!", getLocation());
        } else if (this.url != null) {
            try {
                log("connecting to " + getUrl(), 3);
                Properties properties = new Properties();
                properties.put(ServiceManagerNative.USER, getUserId());
                properties.put("password", getPassword());
                for (Property property : this.connectionProperties) {
                    String name = property.getName();
                    String value = property.getValue();
                    if (!(name == null || value == null)) {
                        log("Setting connection property " + name + " to " + value, 3);
                        properties.put(name, value);
                    }
                    log("Only name/value pairs are supported as connection properties.", 1);
                }
                Connection connect = getDriver().connect(getUrl(), properties);
                if (connect != null) {
                    connect.setAutoCommit(this.autocommit);
                    return connect;
                }
                throw new SQLException("No suitable Driver for " + this.url);
            } catch (SQLException e) {
                if (!this.failOnConnectionError) {
                    log("Failed to connect: " + e.getMessage(), 1);
                    return null;
                }
                throw new BuildException(e, getLocation());
            }
        } else {
            throw new BuildException("Url attribute must be set!", getLocation());
        }
    }

    private Driver getDriver() throws BuildException {
        Class<?> cls;
        if (this.driver != null) {
            try {
                if (this.classpath != null) {
                    synchronized (LOADER_MAP) {
                        if (this.caching) {
                            this.loader = LOADER_MAP.get(this.driver);
                        }
                        if (this.loader == null) {
                            log("Loading " + this.driver + " using AntClassLoader with classpath " + this.classpath, 3);
                            this.loader = getProject().createClassLoader(this.classpath);
                            if (this.caching) {
                                LOADER_MAP.put(this.driver, this.loader);
                            }
                        } else {
                            log("Loading " + this.driver + " using a cached AntClassLoader.", 3);
                        }
                    }
                    cls = this.loader.loadClass(this.driver);
                } else {
                    log("Loading " + this.driver + " using system loader.", 3);
                    cls = Class.forName(this.driver);
                }
                return (Driver) cls.newInstance();
            } catch (ClassNotFoundException e) {
                throw new BuildException("Class Not Found: JDBC driver " + this.driver + " could not be loaded", e, getLocation());
            } catch (IllegalAccessException e2) {
                throw new BuildException("Illegal Access: JDBC driver " + this.driver + " could not be loaded", e2, getLocation());
            } catch (InstantiationException e3) {
                throw new BuildException("Instantiation Exception: JDBC driver " + this.driver + " could not be loaded", e3, getLocation());
            }
        } else {
            throw new BuildException("Driver attribute must be set!", getLocation());
        }
    }

    public void isCaching(boolean z) {
        this.caching = z;
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public boolean isAutocommit() {
        return this.autocommit;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserid(String str) {
        this.userId = str;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRdbms() {
        return this.rdbms;
    }

    public String getVersion() {
        return this.version;
    }
}
