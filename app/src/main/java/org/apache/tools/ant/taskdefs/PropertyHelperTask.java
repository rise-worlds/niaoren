package org.apache.tools.ant.taskdefs;

import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class PropertyHelperTask extends Task {
    private List delegates;
    private PropertyHelper propertyHelper;

    /* loaded from: classes2.dex */
    public final class DelegateElement {
        private String refid;

        private DelegateElement() {
        }

        public String getRefid() {
            return this.refid;
        }

        public void setRefid(String str) {
            this.refid = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public PropertyHelper.Delegate resolve() {
            if (this.refid != null) {
                return (PropertyHelper.Delegate) PropertyHelperTask.this.getProject().getReference(this.refid);
            }
            throw new BuildException("refid required for generic delegate");
        }
    }

    public synchronized void addConfigured(PropertyHelper propertyHelper) {
        if (this.propertyHelper == null) {
            this.propertyHelper = propertyHelper;
        } else {
            throw new BuildException("Only one PropertyHelper can be installed");
        }
    }

    public synchronized void addConfigured(PropertyHelper.Delegate delegate) {
        getAddDelegateList().add(delegate);
    }

    public DelegateElement createDelegate() {
        DelegateElement delegateElement = new DelegateElement();
        getAddDelegateList().add(delegateElement);
        return delegateElement;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (getProject() == null) {
            throw new BuildException("Project instance not set");
        } else if (this.propertyHelper == null && this.delegates == null) {
            throw new BuildException("Either a new PropertyHelper or one or more PropertyHelper delegates are required");
        } else {
            PropertyHelper propertyHelper = this.propertyHelper;
            if (propertyHelper == null) {
                propertyHelper = PropertyHelper.getPropertyHelper(getProject());
            }
            synchronized (propertyHelper) {
                if (this.delegates != null) {
                    for (Object obj : this.delegates) {
                        PropertyHelper.Delegate resolve = obj instanceof DelegateElement ? ((DelegateElement) obj).resolve() : (PropertyHelper.Delegate) obj;
                        log("Adding PropertyHelper delegate " + resolve, 4);
                        propertyHelper.add(resolve);
                    }
                }
            }
            if (this.propertyHelper != null) {
                log("Installing PropertyHelper " + this.propertyHelper, 4);
                getProject().addReference(MagicNames.REFID_PROPERTY_HELPER, this.propertyHelper);
            }
        }
    }

    private synchronized List getAddDelegateList() {
        if (this.delegates == null) {
            this.delegates = new ArrayList();
        }
        return this.delegates;
    }
}
