package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Enumeration;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class MajoritySelector extends BaseSelectorContainer {
    private boolean allowtie = true;

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (hasSelectors()) {
            sb.append("{majorityselect: ");
            sb.append(super.toString());
            sb.append(C4963cj.f20747d);
        }
        return sb.toString();
    }

    public void setAllowtie(boolean z) {
        this.allowtie = z;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        validate();
        Enumeration<FileSelector> selectorElements = selectorElements();
        int i = 0;
        int i2 = 0;
        while (selectorElements.hasMoreElements()) {
            if (selectorElements.nextElement().isSelected(file, str, file2)) {
                i++;
            } else {
                i2++;
            }
        }
        if (i > i2) {
            return true;
        }
        if (i2 > i) {
            return false;
        }
        return this.allowtie;
    }
}
