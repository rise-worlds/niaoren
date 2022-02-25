package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.http.HttpHost;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.condition.IsSigned;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.ResourceUtils;

/* loaded from: classes2.dex */
public class SignJar extends AbstractJarSignerTask {
    public static final String ERROR_BAD_MAP = "Cannot map source file to anything sensible: ";
    public static final String ERROR_MAPPER_WITHOUT_DEST = "The destDir attribute is required if a mapper is set";
    public static final String ERROR_NO_ALIAS = "alias attribute must be set";
    public static final String ERROR_NO_STOREPASS = "storepass attribute must be set";
    public static final String ERROR_SIGNEDJAR_AND_PATHS = "You cannot specify the signed JAR when using paths or filesets";
    public static final String ERROR_TODIR_AND_SIGNEDJAR = "'destdir' and 'signedjar' cannot both be set";
    public static final String ERROR_TOO_MANY_MAPPERS = "Too many mappers";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    protected File destDir;
    private String digestAlg;
    private boolean force = false;
    protected boolean internalsf;
    protected boolean lazy;
    private FileNameMapper mapper;
    private boolean preserveLastModified;
    protected boolean sectionsonly;
    private String sigAlg;
    protected String sigfile;
    protected File signedjar;
    protected String tsacert;
    protected String tsaproxyhost;
    protected String tsaproxyport;
    protected String tsaurl;

    public void setSigfile(String str) {
        this.sigfile = str;
    }

    public void setSignedjar(File file) {
        this.signedjar = file;
    }

    public void setInternalsf(boolean z) {
        this.internalsf = z;
    }

    public void setSectionsonly(boolean z) {
        this.sectionsonly = z;
    }

    public void setLazy(boolean z) {
        this.lazy = z;
    }

    public void setDestDir(File file) {
        this.destDir = file;
    }

    public void add(FileNameMapper fileNameMapper) {
        if (this.mapper == null) {
            this.mapper = fileNameMapper;
            return;
        }
        throw new BuildException(ERROR_TOO_MANY_MAPPERS);
    }

    public FileNameMapper getMapper() {
        return this.mapper;
    }

    public String getTsaurl() {
        return this.tsaurl;
    }

    public void setTsaurl(String str) {
        this.tsaurl = str;
    }

    public String getTsaproxyhost() {
        return this.tsaproxyhost;
    }

    public void setTsaproxyhost(String str) {
        this.tsaproxyhost = str;
    }

    public String getTsaproxyport() {
        return this.tsaproxyport;
    }

    public void setTsaproxyport(String str) {
        this.tsaproxyport = str;
    }

    public String getTsacert() {
        return this.tsacert;
    }

    public void setTsacert(String str) {
        this.tsacert = str;
    }

    public void setForce(boolean z) {
        this.force = z;
    }

    public boolean isForce() {
        return this.force;
    }

    public void setSigAlg(String str) {
        this.sigAlg = str;
    }

    public String getSigAlg() {
        return this.sigAlg;
    }

    public void setDigestAlg(String str) {
        this.digestAlg = str;
    }

    public String getDigestAlg() {
        return this.digestAlg;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        FileNameMapper fileNameMapper;
        boolean z = this.jar != null;
        boolean z2 = this.signedjar != null;
        boolean z3 = this.destDir != null;
        boolean z4 = this.mapper != null;
        if (!z && !hasResources()) {
            throw new BuildException(AbstractJarSignerTask.ERROR_NO_SOURCE);
        } else if (this.alias == null) {
            throw new BuildException(ERROR_NO_ALIAS);
        } else if (this.storepass == null) {
            throw new BuildException(ERROR_NO_STOREPASS);
        } else if (z3 && z2) {
            throw new BuildException(ERROR_TODIR_AND_SIGNEDJAR);
        } else if (hasResources() && z2) {
            throw new BuildException(ERROR_SIGNEDJAR_AND_PATHS);
        } else if (z3 || !z4) {
            beginExecution();
            try {
                if (!z || !z2) {
                    Path createUnifiedSourcePath = createUnifiedSourcePath();
                    if (z4) {
                        fileNameMapper = this.mapper;
                    } else {
                        fileNameMapper = new IdentityMapper();
                    }
                    Iterator<Resource> it = createUnifiedSourcePath.iterator();
                    while (it.hasNext()) {
                        FileResource asFileResource = ResourceUtils.asFileResource((FileProvider) it.next().mo14823as(FileProvider.class));
                        File baseDir = z3 ? this.destDir : asFileResource.getBaseDir();
                        String[] mapFileName = fileNameMapper.mapFileName(asFileResource.getName());
                        if (mapFileName == null || mapFileName.length != 1) {
                            throw new BuildException(ERROR_BAD_MAP + asFileResource.getFile());
                        }
                        signOneJar(asFileResource.getFile(), new File(baseDir, mapFileName[0]));
                    }
                    return;
                }
                signOneJar(this.jar, this.signedjar);
            } finally {
                endExecution();
            }
        } else {
            throw new BuildException(ERROR_MAPPER_WITHOUT_DEST);
        }
    }

    private void signOneJar(File file, File file2) throws BuildException {
        if (file2 == null) {
            file2 = file;
        }
        if (!isUpToDate(file, file2)) {
            long lastModified = file.lastModified();
            ExecTask createJarSigner = createJarSigner();
            setCommonOptions(createJarSigner);
            bindToKeystore(createJarSigner);
            if (this.sigfile != null) {
                addValue(createJarSigner, "-sigfile");
                addValue(createJarSigner, this.sigfile);
            }
            try {
                if (!FILE_UTILS.areSame(file, file2)) {
                    addValue(createJarSigner, "-signedjar");
                    addValue(createJarSigner, file2.getPath());
                }
                if (this.internalsf) {
                    addValue(createJarSigner, "-internalsf");
                }
                if (this.sectionsonly) {
                    addValue(createJarSigner, "-sectionsonly");
                }
                if (this.sigAlg != null) {
                    addValue(createJarSigner, "-sigalg");
                    addValue(createJarSigner, this.sigAlg);
                }
                if (this.digestAlg != null) {
                    addValue(createJarSigner, "-digestalg");
                    addValue(createJarSigner, this.digestAlg);
                }
                addTimestampAuthorityCommands(createJarSigner);
                addValue(createJarSigner, file.getPath());
                addValue(createJarSigner, this.alias);
                log("Signing JAR: " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + " as " + this.alias);
                createJarSigner.execute();
                if (this.preserveLastModified) {
                    FILE_UTILS.setFileLastModified(file2, lastModified);
                }
            } catch (IOException e) {
                throw new BuildException(e);
            }
        }
    }

    private void addTimestampAuthorityCommands(ExecTask execTask) {
        if (this.tsaurl != null) {
            addValue(execTask, "-tsa");
            addValue(execTask, this.tsaurl);
        }
        if (this.tsacert != null) {
            addValue(execTask, "-tsacert");
            addValue(execTask, this.tsacert);
        }
        if (this.tsaproxyhost != null) {
            String str = this.tsaurl;
            if (str == null || str.startsWith("https")) {
                addProxyFor(execTask, "https");
            }
            String str2 = this.tsaurl;
            if (str2 == null || !str2.startsWith("https")) {
                addProxyFor(execTask, HttpHost.DEFAULT_SCHEME_NAME);
            }
        }
    }

    protected boolean isUpToDate(File file, File file2) {
        if (isForce() || file == null || !file.exists()) {
            return false;
        }
        if (file2 == null) {
            file2 = file;
        }
        if (!file.equals(file2)) {
            return FILE_UTILS.isUpToDate(file, file2);
        }
        if (this.lazy) {
            return isSigned(file);
        }
        return false;
    }

    protected boolean isSigned(File file) {
        try {
            return IsSigned.isSigned(file, this.sigfile == null ? this.alias : this.sigfile);
        } catch (IOException e) {
            log(e.toString(), 3);
            return false;
        }
    }

    public void setPreserveLastModified(boolean z) {
        this.preserveLastModified = z;
    }

    private void addProxyFor(ExecTask execTask, String str) {
        addValue(execTask, "-J-D" + str + ".proxyHost=" + this.tsaproxyhost);
        if (this.tsaproxyport != null) {
            addValue(execTask, "-J-D" + str + ".proxyPort=" + this.tsaproxyport);
        }
    }
}
