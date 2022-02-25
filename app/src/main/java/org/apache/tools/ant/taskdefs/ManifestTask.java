package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Enumeration;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* loaded from: classes2.dex */
public class ManifestTask extends Task {
    public static final String VALID_ATTRIBUTE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
    private String encoding;
    private File manifestFile;
    private Manifest nestedManifest = new Manifest();
    private boolean mergeClassPaths = false;
    private boolean flattenClassPaths = false;
    private Mode mode = new Mode();

    /* loaded from: classes2.dex */
    public static class Mode extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"update", MSVSSConstants.WRITABLE_REPLACE};
        }
    }

    public ManifestTask() {
        this.mode.setValue(MSVSSConstants.WRITABLE_REPLACE);
    }

    public void addConfiguredSection(Manifest.Section section) throws ManifestException {
        Enumeration<String> attributeKeys = section.getAttributeKeys();
        while (attributeKeys.hasMoreElements()) {
            checkAttribute(section.getAttribute(attributeKeys.nextElement()));
        }
        this.nestedManifest.addConfiguredSection(section);
    }

    public void addConfiguredAttribute(Manifest.Attribute attribute) throws ManifestException {
        checkAttribute(attribute);
        this.nestedManifest.addConfiguredAttribute(attribute);
    }

    private void checkAttribute(Manifest.Attribute attribute) throws BuildException {
        String name = attribute.getName();
        char charAt = name.charAt(0);
        if (charAt == '-' || charAt == '_') {
            throw new BuildException("Manifest attribute names must not start with '" + charAt + "'.");
        }
        for (int i = 0; i < name.length(); i++) {
            char charAt2 = name.charAt(i);
            if (VALID_ATTRIBUTE_CHARS.indexOf(charAt2) < 0) {
                throw new BuildException("Manifest attribute names must not contain '" + charAt2 + "'");
            }
        }
    }

    public void setFile(File file) {
        this.manifestFile = file;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setMergeClassPathAttributes(boolean z) {
        this.mergeClassPaths = z;
    }

    public void setFlattenAttributes(boolean z) {
        this.flattenClassPaths = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0096 A[LOOP:0: B:28:0x0090->B:30:0x0096, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1 A[Catch: ManifestException -> 0x013d, TryCatch #0 {ManifestException -> 0x013d, blocks: (B:31:0x00b2, B:33:0x00c1, B:36:0x00cb, B:39:0x00d4, B:40:0x00d5), top: B:67:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cb A[Catch: ManifestException -> 0x013d, TryCatch #0 {ManifestException -> 0x013d, blocks: (B:31:0x00b2, B:33:0x00c1, B:36:0x00cb, B:39:0x00d4, B:40:0x00d5), top: B:67:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.ManifestTask.execute():void");
    }
}
