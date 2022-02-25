package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.Comparison;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public class ResourceCount extends Task implements Condition {
    private static final String COUNT_REQUIRED = "Use of the ResourceCount condition requires that the count attribute be set.";
    private static final String ONE_NESTED_MESSAGE = "ResourceCount can count resources from exactly one nested ResourceCollection.";
    private Integer count;
    private String property;

    /* renamed from: rc */
    private ResourceCollection f14758rc;
    private Comparison when = Comparison.EQUAL;

    public void add(ResourceCollection resourceCollection) {
        if (this.f14758rc == null) {
            this.f14758rc = resourceCollection;
            return;
        }
        throw new BuildException(ONE_NESTED_MESSAGE);
    }

    public void setRefid(Reference reference) {
        Object referencedObject = reference.getReferencedObject();
        if (referencedObject instanceof ResourceCollection) {
            add((ResourceCollection) referencedObject);
            return;
        }
        throw new BuildException(reference.getRefId() + " doesn't denote a ResourceCollection");
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.f14758rc == null) {
            throw new BuildException(ONE_NESTED_MESSAGE);
        } else if (this.property == null) {
            log("resource count = " + this.f14758rc.size());
        } else {
            getProject().setNewProperty(this.property, Integer.toString(this.f14758rc.size()));
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() {
        ResourceCollection resourceCollection = this.f14758rc;
        if (resourceCollection == null) {
            throw new BuildException(ONE_NESTED_MESSAGE);
        } else if (this.count != null) {
            return this.when.evaluate(new Integer(resourceCollection.size()).compareTo(this.count));
        } else {
            throw new BuildException(COUNT_REQUIRED);
        }
    }

    public void setCount(int i) {
        this.count = new Integer(i);
    }

    public void setWhen(Comparison comparison) {
        this.when = comparison;
    }

    public void setProperty(String str) {
        this.property = str;
    }
}
