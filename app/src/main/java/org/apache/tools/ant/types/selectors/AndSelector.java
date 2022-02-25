package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Enumeration;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class AndSelector extends BaseSelectorContainer {
    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (hasSelectors()) {
            sb.append("{andselect: ");
            sb.append(super.toString());
            sb.append(C4963cj.f20747d);
        }
        return sb.toString();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        validate();
        Enumeration<FileSelector> selectorElements = selectorElements();
        while (selectorElements.hasMoreElements()) {
            if (!selectorElements.nextElement().isSelected(file, str, file2)) {
                return false;
            }
        }
        return true;
    }
}
