package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.zip.ZipOutputStream;

/* loaded from: classes2.dex */
public class Ear extends Jar {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final String XML_DESCRIPTOR_PATH = "META-INF/application.xml";
    private File deploymentDescriptor;
    private boolean descriptorAdded;

    public Ear() {
        this.archiveType = "ear";
        this.emptyBehavior = "create";
    }

    public void setEarfile(File file) {
        setDestFile(file);
    }

    public void setAppxml(File file) {
        this.deploymentDescriptor = file;
        if (this.deploymentDescriptor.exists()) {
            ZipFileSet zipFileSet = new ZipFileSet();
            zipFileSet.setFile(this.deploymentDescriptor);
            zipFileSet.setFullpath(XML_DESCRIPTOR_PATH);
            super.addFileset(zipFileSet);
            return;
        }
        throw new BuildException("Deployment descriptor: " + this.deploymentDescriptor + DirectoryScanner.DOES_NOT_EXIST_POSTFIX);
    }

    public void addArchives(ZipFileSet zipFileSet) {
        zipFileSet.setPrefix("/");
        super.addFileset(zipFileSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Jar, org.apache.tools.ant.taskdefs.Zip
    public void initZipOutputStream(ZipOutputStream zipOutputStream) throws IOException, BuildException {
        if (this.deploymentDescriptor != null || isInUpdateMode()) {
            super.initZipOutputStream(zipOutputStream);
            return;
        }
        throw new BuildException("appxml attribute is required", getLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Zip
    public void zipFile(File file, ZipOutputStream zipOutputStream, String str, int i) throws IOException {
        if (XML_DESCRIPTOR_PATH.equalsIgnoreCase(str)) {
            File file2 = this.deploymentDescriptor;
            if (file2 == null || !FILE_UTILS.fileNameEquals(file2, file) || this.descriptorAdded) {
                logWhenWriting("Warning: selected " + this.archiveType + " files include a " + XML_DESCRIPTOR_PATH + " which will be ignored (please use appxml attribute to " + this.archiveType + " task)", 1);
                return;
            }
            super.zipFile(file, zipOutputStream, str, i);
            this.descriptorAdded = true;
            return;
        }
        super.zipFile(file, zipOutputStream, str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.Jar, org.apache.tools.ant.taskdefs.Zip
    public void cleanUp() {
        this.descriptorAdded = false;
        super.cleanUp();
    }
}
