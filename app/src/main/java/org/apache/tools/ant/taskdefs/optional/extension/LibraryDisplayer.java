package org.apache.tools.ant.taskdefs.optional.extension;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.text.ParseException;
import java.util.jar.Manifest;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
class LibraryDisplayer {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void displayLibrary(File file) throws BuildException {
        displayLibrary(file, ExtensionUtil.getManifest(file));
    }

    void displayLibrary(File file, Manifest manifest) throws BuildException {
        Extension[] available = Extension.getAvailable(manifest);
        Extension[] required = Extension.getRequired(manifest);
        Extension[] options = Extension.getOptions(manifest);
        Specification[] specifications = getSpecifications(manifest);
        if (!(available.length == 0 && required.length == 0 && options.length == 0 && specifications.length == 0)) {
            String str = "File: " + file;
            int length = str.length();
            printLine(length);
            System.out.println(str);
            printLine(length);
            if (available.length != 0) {
                System.out.println("Extensions Supported By Library:");
                for (Extension extension : available) {
                    System.out.println(extension.toString());
                }
            }
            if (required.length != 0) {
                System.out.println("Extensions Required By Library:");
                for (Extension extension2 : required) {
                    System.out.println(extension2.toString());
                }
            }
            if (options.length != 0) {
                System.out.println("Extensions that will be used by Library if present:");
                for (Extension extension3 : options) {
                    System.out.println(extension3.toString());
                }
            }
            if (specifications.length != 0) {
                System.out.println("Specifications Supported By Library:");
                for (Specification specification : specifications) {
                    displaySpecification(specification);
                }
            }
        }
    }

    private void printLine(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private Specification[] getSpecifications(Manifest manifest) throws BuildException {
        try {
            return Specification.getSpecifications(manifest);
        } catch (ParseException e) {
            throw new BuildException(e.getMessage(), e);
        }
    }

    private void displaySpecification(Specification specification) {
        String[] sections = specification.getSections();
        if (sections != null) {
            StringBuffer stringBuffer = new StringBuffer("Sections: ");
            for (String str : sections) {
                stringBuffer.append(ExpandableTextView.f6958c);
                stringBuffer.append(str);
            }
            System.out.println(stringBuffer);
        }
        System.out.println(specification.toString());
    }
}
