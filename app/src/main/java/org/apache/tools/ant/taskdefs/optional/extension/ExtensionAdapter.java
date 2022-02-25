package org.apache.tools.ant.taskdefs.optional.extension;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.DeweyDecimal;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class ExtensionAdapter extends DataType {
    private String extensionName;
    private String implementationURL;
    private String implementationVendor;
    private String implementationVendorID;
    private DeweyDecimal implementationVersion;
    private String specificationVendor;
    private DeweyDecimal specificationVersion;

    public void setExtensionName(String str) {
        verifyNotAReference();
        this.extensionName = str;
    }

    public void setSpecificationVersion(String str) {
        verifyNotAReference();
        this.specificationVersion = new DeweyDecimal(str);
    }

    public void setSpecificationVendor(String str) {
        verifyNotAReference();
        this.specificationVendor = str;
    }

    public void setImplementationVendorId(String str) {
        verifyNotAReference();
        this.implementationVendorID = str;
    }

    public void setImplementationVendor(String str) {
        verifyNotAReference();
        this.implementationVendor = str;
    }

    public void setImplementationVersion(String str) {
        verifyNotAReference();
        this.implementationVersion = new DeweyDecimal(str);
    }

    public void setImplementationUrl(String str) {
        verifyNotAReference();
        this.implementationURL = str;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.extensionName == null && this.specificationVersion == null && this.specificationVendor == null && this.implementationVersion == null && this.implementationVendorID == null && this.implementationVendor == null && this.implementationURL == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    private void verifyNotAReference() throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Extension toExtension() throws BuildException {
        if (isReference()) {
            return ((ExtensionAdapter) getCheckedRef()).toExtension();
        }
        dieOnCircularReference();
        if (this.extensionName != null) {
            DeweyDecimal deweyDecimal = this.specificationVersion;
            String deweyDecimal2 = deweyDecimal != null ? deweyDecimal.toString() : null;
            DeweyDecimal deweyDecimal3 = this.implementationVersion;
            return new Extension(this.extensionName, deweyDecimal2, this.specificationVendor, deweyDecimal3 != null ? deweyDecimal3.toString() : null, this.implementationVendor, this.implementationVendorID, this.implementationURL);
        }
        throw new BuildException("Extension is missing name.");
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        return "{" + toExtension().toString() + C4963cj.f20747d;
    }
}
