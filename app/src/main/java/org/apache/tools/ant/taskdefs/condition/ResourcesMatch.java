package org.apache.tools.ant.taskdefs.condition;

import java.io.IOException;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.ResourceUtils;

/* loaded from: classes2.dex */
public class ResourcesMatch implements Condition {
    private Union resources = null;
    private boolean asText = false;

    public void setAsText(boolean z) {
        this.asText = z;
    }

    public void add(ResourceCollection resourceCollection) {
        if (resourceCollection != null) {
            Union union = this.resources;
            if (union == null) {
                union = new Union();
            }
            this.resources = union;
            this.resources.add(resourceCollection);
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        Union union = this.resources;
        if (union != null) {
            if (union.size() > 1) {
                Iterator<Resource> it = this.resources.iterator();
                Resource next = it.next();
                while (it.hasNext()) {
                    Resource next2 = it.next();
                    try {
                        if (!ResourceUtils.contentEquals(next, next2, this.asText)) {
                            return false;
                        }
                        next = next2;
                    } catch (IOException e) {
                        throw new BuildException("when comparing resources " + next.toString() + " and " + next2.toString(), e);
                    }
                }
            }
            return true;
        }
        throw new BuildException("You must specify one or more nested resource collections");
    }
}
