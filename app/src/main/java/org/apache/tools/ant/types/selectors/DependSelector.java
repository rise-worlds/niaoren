package org.apache.tools.ant.types.selectors;

import java.io.File;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class DependSelector extends MappingSelector {
    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder("{dependselector targetdir: ");
        if (this.targetdir == null) {
            sb.append("NOT YET SET");
        } else {
            sb.append(this.targetdir.getName());
        }
        sb.append(" granularity: ");
        sb.append(this.granularity);
        if (this.map != null) {
            sb.append(" mapper: ");
            sb.append(this.map.toString());
        } else if (this.mapperElement != null) {
            sb.append(" mapper: ");
            sb.append(this.mapperElement.toString());
        }
        sb.append(C4963cj.f20747d);
        return sb.toString();
    }

    @Override // org.apache.tools.ant.types.selectors.MappingSelector
    public boolean selectionTest(File file, File file2) {
        return SelectorUtils.isOutOfDate(file, file2, this.granularity);
    }
}
