package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector;

/* loaded from: classes2.dex */
public abstract class BaseSelectorContainer extends BaseSelector implements SelectorContainer {
    private Vector<FileSelector> selectorsList = new Vector<>();

    @Override // org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public abstract boolean isSelected(File file, String str, File file2);

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public boolean hasSelectors() {
        dieOnCircularReference();
        return !this.selectorsList.isEmpty();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public int selectorCount() {
        dieOnCircularReference();
        return this.selectorsList.size();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public FileSelector[] getSelectors(Project project) {
        dieOnCircularReference();
        FileSelector[] fileSelectorArr = new FileSelector[this.selectorsList.size()];
        this.selectorsList.copyInto(fileSelectorArr);
        return fileSelectorArr;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public Enumeration<FileSelector> selectorElements() {
        dieOnCircularReference();
        return this.selectorsList.elements();
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        dieOnCircularReference();
        StringBuilder sb = new StringBuilder();
        Enumeration<FileSelector> selectorElements = selectorElements();
        while (selectorElements.hasMoreElements()) {
            sb.append(selectorElements.nextElement().toString());
            if (selectorElements.hasMoreElements()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void appendSelector(FileSelector fileSelector) {
        this.selectorsList.addElement(fileSelector);
        setChecked(false);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void validate() {
        verifySettings();
        dieOnCircularReference();
        String error = getError();
        if (error == null) {
            Enumeration<FileSelector> selectorElements = selectorElements();
            while (selectorElements.hasMoreElements()) {
                FileSelector nextElement = selectorElements.nextElement();
                if (nextElement instanceof BaseSelector) {
                    ((BaseSelector) nextElement).validate();
                }
            }
            return;
        }
        throw new BuildException(error);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addSelector(SelectSelector selectSelector) {
        appendSelector(selectSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addAnd(AndSelector andSelector) {
        appendSelector(andSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addOr(OrSelector orSelector) {
        appendSelector(orSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addNot(NotSelector notSelector) {
        appendSelector(notSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addNone(NoneSelector noneSelector) {
        appendSelector(noneSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addMajority(MajoritySelector majoritySelector) {
        appendSelector(majoritySelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDate(DateSelector dateSelector) {
        appendSelector(dateSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addSize(SizeSelector sizeSelector) {
        appendSelector(sizeSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addFilename(FilenameSelector filenameSelector) {
        appendSelector(filenameSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addCustom(ExtendSelector extendSelector) {
        appendSelector(extendSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addContains(ContainsSelector containsSelector) {
        appendSelector(containsSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addPresent(PresentSelector presentSelector) {
        appendSelector(presentSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDepth(DepthSelector depthSelector) {
        appendSelector(depthSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDepend(DependSelector dependSelector) {
        appendSelector(dependSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDifferent(DifferentSelector differentSelector) {
        appendSelector(differentSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addType(TypeSelector typeSelector) {
        appendSelector(typeSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addContainsRegexp(ContainsRegexpSelector containsRegexpSelector) {
        appendSelector(containsRegexpSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addModified(ModifiedSelector modifiedSelector) {
        appendSelector(modifiedSelector);
    }

    public void addReadable(ReadableSelector readableSelector) {
        appendSelector(readableSelector);
    }

    public void addWritable(WritableSelector writableSelector) {
        appendSelector(writableSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void add(FileSelector fileSelector) {
        appendSelector(fileSelector);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                Iterator<FileSelector> it = this.selectorsList.iterator();
                while (it.hasNext()) {
                    FileSelector next = it.next();
                    if (next instanceof DataType) {
                        pushAndInvokeCircularReferenceCheck((DataType) next, stack, project);
                    }
                }
                setChecked(true);
            }
        }
    }
}
