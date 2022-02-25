package org.apache.tools.ant.types.resources.selectors;

import java.util.Iterator;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Comparison;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Quantifier;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.resources.comparators.DelegatedResourceComparator;
import org.apache.tools.ant.types.resources.comparators.ResourceComparator;

/* loaded from: classes2.dex */
public class Compare extends DataType implements ResourceSelector {
    private static final String ONE_CONTROL_MESSAGE = " the <control> element should be specified exactly once.";
    private Union control;
    private DelegatedResourceComparator comp = new DelegatedResourceComparator();
    private Quantifier against = Quantifier.ALL;
    private Comparison when = Comparison.EQUAL;

    public synchronized void add(ResourceComparator resourceComparator) {
        if (!isReference()) {
            this.comp.add(resourceComparator);
            setChecked(false);
        } else {
            throw noChildrenAllowed();
        }
    }

    public synchronized void setAgainst(Quantifier quantifier) {
        if (!isReference()) {
            this.against = quantifier;
        } else {
            throw tooManyAttributes();
        }
    }

    public synchronized void setWhen(Comparison comparison) {
        if (!isReference()) {
            this.when = comparison;
        } else {
            throw tooManyAttributes();
        }
    }

    public synchronized ResourceCollection createControl() {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.control == null) {
            this.control = new Union();
            setChecked(false);
        } else {
            throw oneControl();
        }
        return this.control;
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public synchronized boolean isSelected(Resource resource) {
        if (isReference()) {
            return ((ResourceSelector) getCheckedRef()).isSelected(resource);
        } else if (this.control != null) {
            dieOnCircularReference();
            Iterator<Resource> it = this.control.iterator();
            int i = 0;
            int i2 = 0;
            while (it.hasNext()) {
                if (this.when.evaluate(this.comp.compare(resource, it.next()))) {
                    i++;
                } else {
                    i2++;
                }
            }
            return this.against.evaluate(i, i2);
        } else {
            throw oneControl();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.control != null) {
                    DataType.pushAndInvokeCircularReferenceCheck(this.control, stack, project);
                }
                DataType.pushAndInvokeCircularReferenceCheck(this.comp, stack, project);
                setChecked(true);
            }
        }
    }

    private BuildException oneControl() {
        return new BuildException(super.toString() + ONE_CONTROL_MESSAGE);
    }
}
