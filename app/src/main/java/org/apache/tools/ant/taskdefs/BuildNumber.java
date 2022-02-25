package org.apache.tools.ant.taskdefs;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class BuildNumber extends Task {
    private static final String DEFAULT_FILENAME = "build.number";
    private static final String DEFAULT_PROPERTY_NAME = "build.number";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File myFile;

    public void setFile(File file) {
        this.myFile = file;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Throwable th;
        IOException e;
        FileOutputStream fileOutputStream;
        File file = this.myFile;
        validate();
        Properties loadProperties = loadProperties();
        int buildNumber = getBuildNumber(loadProperties);
        loadProperties.put("build.number", String.valueOf(buildNumber + 1));
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(this.myFile);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            loadProperties.store(fileOutputStream, "Build Number for ANT. Do not edit!");
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
                log("error closing output stream " + e3, 0);
            }
            this.myFile = file;
            getProject().setNewProperty("build.number", String.valueOf(buildNumber));
        } catch (IOException e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            throw new BuildException("Error while writing " + this.myFile, e);
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    log("error closing output stream " + e5, 0);
                }
            }
            this.myFile = file;
            throw th;
        }
    }

    private int getBuildNumber(Properties properties) throws BuildException {
        String trim = properties.getProperty("build.number", ResultTypeConstant.f7213z).trim();
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e) {
            throw new BuildException(this.myFile + " contains a non integer build number: " + trim, e);
        }
    }

    private Properties loadProperties() throws BuildException {
        Throwable th;
        IOException e;
        Properties properties;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                properties = new Properties();
                fileInputStream = new FileInputStream(this.myFile);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            properties.load(fileInputStream);
            try {
                fileInputStream.close();
            } catch (IOException e3) {
                log("error closing input stream " + e3, 0);
            }
            return properties;
        } catch (IOException e4) {
            e = e4;
            throw new BuildException(e);
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                    log("error closing input stream " + e5, 0);
                }
            }
            throw th;
        }
    }

    private void validate() throws BuildException {
        if (this.myFile == null) {
            this.myFile = FILE_UTILS.resolveFile(getProject().getBaseDir(), "build.number");
        }
        if (!this.myFile.exists()) {
            try {
                FILE_UTILS.createNewFile(this.myFile);
            } catch (IOException e) {
                throw new BuildException(this.myFile + " doesn't exist and new file can't be created.", e);
            }
        }
        if (!this.myFile.canRead()) {
            throw new BuildException("Unable to read from " + this.myFile + Consts.f23430h);
        } else if (!this.myFile.canWrite()) {
            throw new BuildException("Unable to write to " + this.myFile + Consts.f23430h);
        }
    }
}
