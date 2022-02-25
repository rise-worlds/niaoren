package org.apache.tools.ant.types.selectors;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.util.Enumeration;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.PropertyHelper;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class SelectSelector extends BaseSelectorContainer {
    private Object ifCondition;
    private Object unlessCondition;

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (hasSelectors()) {
            stringBuffer.append("{select");
            if (this.ifCondition != null) {
                stringBuffer.append(" if: ");
                stringBuffer.append(this.ifCondition);
            }
            if (this.unlessCondition != null) {
                stringBuffer.append(" unless: ");
                stringBuffer.append(this.unlessCondition);
            }
            stringBuffer.append(ExpandableTextView.f6958c);
            stringBuffer.append(super.toString());
            stringBuffer.append(C4963cj.f20747d);
        }
        return stringBuffer.toString();
    }

    private SelectSelector getRef() {
        return (SelectSelector) getCheckedRef(getClass(), "SelectSelector");
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public boolean hasSelectors() {
        if (isReference()) {
            return getRef().hasSelectors();
        }
        return super.hasSelectors();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public int selectorCount() {
        if (isReference()) {
            return getRef().selectorCount();
        }
        return super.selectorCount();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public FileSelector[] getSelectors(Project project) {
        if (isReference()) {
            return getRef().getSelectors(project);
        }
        return super.getSelectors(project);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public Enumeration<FileSelector> selectorElements() {
        if (isReference()) {
            return getRef().selectorElements();
        }
        return super.selectorElements();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public void appendSelector(FileSelector fileSelector) {
        if (!isReference()) {
            super.appendSelector(fileSelector);
            return;
        }
        throw noChildrenAllowed();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        int selectorCount = selectorCount();
        if (selectorCount < 0 || selectorCount > 1) {
            setError("Only one selector is allowed within the <selector> tag");
        }
    }

    public boolean passesConditions() {
        PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(getProject());
        return propertyHelper.testIfCondition(this.ifCondition) && propertyHelper.testUnlessCondition(this.unlessCondition);
    }

    public void setIf(Object obj) {
        this.ifCondition = obj;
    }

    public void setIf(String str) {
        setIf((Object) str);
    }

    public void setUnless(Object obj) {
        this.unlessCondition = obj;
    }

    public void setUnless(String str) {
        setUnless((Object) str);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        validate();
        if (!passesConditions()) {
            return false;
        }
        Enumeration<FileSelector> selectorElements = selectorElements();
        if (!selectorElements.hasMoreElements()) {
            return true;
        }
        return selectorElements.nextElement().isSelected(file, str, file2);
    }
}
