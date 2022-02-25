package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.UnsupportedEncodingException;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.launch.Locator;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class ManifestClassPath extends Task {
    private File dir;
    private int maxParentLevels = 2;
    private String name;
    private Path path;

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.name == null) {
            throw new BuildException("Missing 'property' attribute!");
        } else if (this.dir == null) {
            throw new BuildException("Missing 'jarfile' attribute!");
        } else if (getProject().getProperty(this.name) != null) {
            throw new BuildException("Property '" + this.name + "' already set!");
        } else if (this.path != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this.maxParentLevels + 1; i++) {
                stringBuffer.append("../");
            }
            String stringBuffer2 = stringBuffer.toString();
            FileUtils fileUtils = FileUtils.getFileUtils();
            this.dir = fileUtils.normalize(this.dir.getAbsolutePath());
            String[] list = this.path.list();
            StringBuffer stringBuffer3 = new StringBuffer();
            for (String str : list) {
                String absolutePath = new File(str).getAbsolutePath();
                File normalize = fileUtils.normalize(absolutePath);
                try {
                    String relativePath = this.dir.equals(normalize) ? Consts.f23430h : FileUtils.getRelativePath(this.dir, normalize);
                    String canonicalPath = normalize.getCanonicalPath();
                    if (File.separatorChar != '/') {
                        canonicalPath = canonicalPath.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX);
                    }
                    if (relativePath.equals(canonicalPath) || relativePath.startsWith(stringBuffer2)) {
                        throw new BuildException("No suitable relative path from " + this.dir + " to " + absolutePath);
                    }
                    if (normalize.isDirectory() && !relativePath.endsWith("/")) {
                        relativePath = relativePath + IOUtils.DIR_SEPARATOR_UNIX;
                    }
                    try {
                        stringBuffer3.append(Locator.encodeURI(relativePath));
                        stringBuffer3.append(' ');
                    } catch (UnsupportedEncodingException e) {
                        throw new BuildException(e);
                    }
                } catch (Exception e2) {
                    throw new BuildException("error trying to get the relative path from " + this.dir + " to " + absolutePath, e2);
                }
            }
            getProject().setNewProperty(this.name, stringBuffer3.toString().trim());
        } else {
            throw new BuildException("Missing nested <classpath>!");
        }
    }

    public void setProperty(String str) {
        this.name = str;
    }

    public void setJarFile(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.isDirectory()) {
            this.dir = parentFile;
            return;
        }
        throw new BuildException("Jar's directory not found: " + parentFile);
    }

    public void setMaxParentLevels(int i) {
        if (i >= 0) {
            this.maxParentLevels = i;
            return;
        }
        throw new BuildException("maxParentLevels must not be a negative number");
    }

    public void addClassPath(Path path) {
        this.path = path;
    }
}
