package org.apache.tools.ant.types;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.util.IdentityStack;

/* loaded from: classes2.dex */
public abstract class DataType extends ProjectComponent implements Cloneable {
    protected boolean checked = true;
    protected Reference ref;

    public boolean isReference() {
        return this.ref != null;
    }

    public void setRefid(Reference reference) {
        this.ref = reference;
        this.checked = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getDataTypeName() {
        return ComponentHelper.getElementName(getProject(), this, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dieOnCircularReference() {
        dieOnCircularReference(getProject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dieOnCircularReference(Project project) {
        if (!this.checked && isReference()) {
            dieOnCircularReference(new IdentityStack(this), project);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!this.checked && isReference()) {
            Object referencedObject = this.ref.getReferencedObject(project);
            if (referencedObject instanceof DataType) {
                IdentityStack instance = IdentityStack.getInstance(stack);
                if (!instance.contains(referencedObject)) {
                    instance.push(referencedObject);
                    ((DataType) referencedObject).dieOnCircularReference(instance, project);
                    instance.pop();
                } else {
                    throw circularReference();
                }
            }
            this.checked = true;
        }
    }

    public static void invokeCircularReferenceCheck(DataType dataType, Stack<Object> stack, Project project) {
        dataType.dieOnCircularReference(stack, project);
    }

    public static void pushAndInvokeCircularReferenceCheck(DataType dataType, Stack<Object> stack, Project project) {
        stack.push(dataType);
        dataType.dieOnCircularReference(stack, project);
        stack.pop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getCheckedRef() {
        return getCheckedRef(getProject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getCheckedRef(Project project) {
        return getCheckedRef(getClass(), getDataTypeName(), project);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T getCheckedRef(Class<T> cls, String str) {
        return (T) getCheckedRef(cls, str, getProject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T getCheckedRef(Class<T> cls, String str, Project project) {
        if (project != null) {
            dieOnCircularReference(project);
            T t = (T) this.ref.getReferencedObject(project);
            if (cls.isAssignableFrom(t.getClass())) {
                return t;
            }
            log("Class " + displayName(t.getClass()) + " is not a subclass of " + displayName(cls), 3);
            throw new BuildException(this.ref.getRefId() + " doesn't denote a " + str);
        }
        throw new BuildException("No Project specified");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuildException tooManyAttributes() {
        return new BuildException("You must not specify more than one attribute when using refid");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuildException noChildrenAllowed() {
        return new BuildException("You must not specify nested elements when using refid");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuildException circularReference() {
        return new BuildException("This data type contains a circular reference.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isChecked() {
        return this.checked;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setChecked(boolean z) {
        this.checked = z;
    }

    public Reference getRefid() {
        return this.ref;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkAttributesAllowed() {
        if (isReference()) {
            throw tooManyAttributes();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkChildrenAllowed() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
    }

    public String toString() {
        String description = getDescription();
        if (description == null) {
            return getDataTypeName();
        }
        return getDataTypeName() + ExpandableTextView.f6958c + description;
    }

    @Override // org.apache.tools.ant.ProjectComponent
    public Object clone() throws CloneNotSupportedException {
        DataType dataType = (DataType) super.clone();
        dataType.setDescription(getDescription());
        if (getRefid() != null) {
            dataType.setRefid(getRefid());
        }
        dataType.setChecked(isChecked());
        return dataType;
    }

    private String displayName(Class<?> cls) {
        return cls.getName() + " (loaded via " + cls.getClassLoader() + ")";
    }
}
