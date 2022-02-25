package org.apache.tools.ant.taskdefs;

import com.liulishuo.filedownloader.model.ConnectionModel;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TypeAdapter;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class AugmentReference extends Task implements TypeAdapter {

    /* renamed from: id */
    private String f14743id;

    @Override // org.apache.tools.ant.TypeAdapter
    public void checkProxyClass(Class<?> cls) {
    }

    @Override // org.apache.tools.ant.TypeAdapter
    public synchronized Object getProxy() {
        Object reference;
        if (getProject() != null) {
            hijackId();
            if (getProject().hasReference(this.f14743id)) {
                reference = getProject().getReference(this.f14743id);
                log("project reference " + this.f14743id + SimpleComparison.f23609c + String.valueOf(reference), 4);
            } else {
                throw new IllegalStateException("Unknown reference \"" + this.f14743id + "\"");
            }
        } else {
            throw new IllegalStateException(getTaskName() + "Project owner unset");
        }
        return reference;
    }

    @Override // org.apache.tools.ant.TypeAdapter
    public void setProxy(Object obj) {
        throw new UnsupportedOperationException();
    }

    private synchronized void hijackId() {
        if (this.f14743id == null) {
            RuntimeConfigurable wrapper = getWrapper();
            this.f14743id = wrapper.getId();
            if (this.f14743id != null) {
                wrapper.setAttribute(ConnectionModel.f10389a, (String) null);
                wrapper.removeAttribute(ConnectionModel.f10389a);
                wrapper.setElementTag("augmented reference \"" + this.f14743id + "\"");
            } else {
                throw new IllegalStateException(getTaskName() + " attribute 'id' unset");
            }
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        restoreWrapperId();
    }

    private synchronized void restoreWrapperId() {
        if (this.f14743id != null) {
            log("restoring augment wrapper " + this.f14743id, 4);
            RuntimeConfigurable wrapper = getWrapper();
            wrapper.setAttribute(ConnectionModel.f10389a, this.f14743id);
            wrapper.setElementTag(getTaskName());
            this.f14743id = null;
        }
    }
}
