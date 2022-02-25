package org.apache.tools.ant.types;

import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public final class AntFilterReader extends DataType implements Cloneable {
    private String className;
    private Path classpath;
    private final Vector<Parameter> parameters = new Vector<>();

    public void setClassName(String str) {
        if (!isReference()) {
            this.className = str;
            return;
        }
        throw tooManyAttributes();
    }

    public String getClassName() {
        if (isReference()) {
            return ((AntFilterReader) getCheckedRef()).getClassName();
        }
        dieOnCircularReference();
        return this.className;
    }

    public void addParam(Parameter parameter) {
        if (!isReference()) {
            this.parameters.addElement(parameter);
            return;
        }
        throw noChildrenAllowed();
    }

    public void setClasspath(Path path) {
        if (!isReference()) {
            Path path2 = this.classpath;
            if (path2 == null) {
                this.classpath = path;
            } else {
                path2.append(path);
            }
            setChecked(false);
            return;
        }
        throw tooManyAttributes();
    }

    public Path createClasspath() {
        if (!isReference()) {
            if (this.classpath == null) {
                this.classpath = new Path(getProject());
            }
            setChecked(false);
            return this.classpath.createPath();
        }
        throw noChildrenAllowed();
    }

    public Path getClasspath() {
        if (isReference()) {
            ((AntFilterReader) getCheckedRef()).getClasspath();
        }
        dieOnCircularReference();
        return this.classpath;
    }

    public void setClasspathRef(Reference reference) {
        if (!isReference()) {
            createClasspath().setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    public Parameter[] getParams() {
        if (isReference()) {
            ((AntFilterReader) getCheckedRef()).getParams();
        }
        dieOnCircularReference();
        Parameter[] parameterArr = new Parameter[this.parameters.size()];
        this.parameters.copyInto(parameterArr);
        return parameterArr;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.parameters.isEmpty() && this.className == null && this.classpath == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.classpath != null) {
                    pushAndInvokeCircularReferenceCheck(this.classpath, stack, project);
                }
                setChecked(true);
            }
        }
    }
}
