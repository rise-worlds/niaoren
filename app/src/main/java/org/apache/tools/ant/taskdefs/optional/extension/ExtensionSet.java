package org.apache.tools.ant.taskdefs.optional.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Reference;

/* loaded from: classes2.dex */
public class ExtensionSet extends DataType {
    private final ArrayList extensions = new ArrayList();
    private final ArrayList extensionsFilesets = new ArrayList();

    public void addExtension(ExtensionAdapter extensionAdapter) {
        if (!isReference()) {
            setChecked(false);
            this.extensions.add(extensionAdapter);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addLibfileset(LibFileSet libFileSet) {
        if (!isReference()) {
            setChecked(false);
            this.extensionsFilesets.add(libFileSet);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addFileset(FileSet fileSet) {
        if (!isReference()) {
            setChecked(false);
            this.extensionsFilesets.add(fileSet);
            return;
        }
        throw noChildrenAllowed();
    }

    public Extension[] toExtensions(Project project) throws BuildException {
        if (isReference()) {
            return ((ExtensionSet) getCheckedRef()).toExtensions(project);
        }
        dieOnCircularReference();
        ArrayList extensions = ExtensionUtil.toExtensions(this.extensions);
        ExtensionUtil.extractExtensions(project, extensions, this.extensionsFilesets);
        return (Extension[]) extensions.toArray(new Extension[extensions.size()]);
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (!this.extensions.isEmpty() || !this.extensionsFilesets.isEmpty()) {
            throw tooManyAttributes();
        }
        super.setRefid(reference);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                Iterator it = this.extensions.iterator();
                while (it.hasNext()) {
                    pushAndInvokeCircularReferenceCheck((ExtensionAdapter) it.next(), stack, project);
                }
                Iterator it2 = this.extensionsFilesets.iterator();
                while (it2.hasNext()) {
                    pushAndInvokeCircularReferenceCheck((FileSet) it2.next(), stack, project);
                }
                setChecked(true);
            }
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        return "ExtensionSet" + Arrays.asList(toExtensions(getProject()));
    }
}
