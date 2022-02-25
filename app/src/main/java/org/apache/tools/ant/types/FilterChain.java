package org.apache.tools.ant.types;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.filters.ClassConstants;
import org.apache.tools.ant.filters.EscapeUnicode;
import org.apache.tools.ant.filters.ExpandProperties;
import org.apache.tools.ant.filters.HeadFilter;
import org.apache.tools.ant.filters.LineContains;
import org.apache.tools.ant.filters.LineContainsRegExp;
import org.apache.tools.ant.filters.PrefixLines;
import org.apache.tools.ant.filters.ReplaceTokens;
import org.apache.tools.ant.filters.StripJavaComments;
import org.apache.tools.ant.filters.StripLineBreaks;
import org.apache.tools.ant.filters.StripLineComments;
import org.apache.tools.ant.filters.SuffixLines;
import org.apache.tools.ant.filters.TabsToSpaces;
import org.apache.tools.ant.filters.TailFilter;
import org.apache.tools.ant.filters.TokenFilter;

/* loaded from: classes2.dex */
public class FilterChain extends DataType implements Cloneable {
    private Vector<Object> filterReaders = new Vector<>();

    public void addFilterReader(AntFilterReader antFilterReader) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(antFilterReader);
            return;
        }
        throw noChildrenAllowed();
    }

    public Vector<Object> getFilterReaders() {
        if (isReference()) {
            return ((FilterChain) getCheckedRef()).getFilterReaders();
        }
        dieOnCircularReference();
        return this.filterReaders;
    }

    public void addClassConstants(ClassConstants classConstants) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(classConstants);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addExpandProperties(ExpandProperties expandProperties) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(expandProperties);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addHeadFilter(HeadFilter headFilter) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(headFilter);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addLineContains(LineContains lineContains) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(lineContains);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addLineContainsRegExp(LineContainsRegExp lineContainsRegExp) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(lineContainsRegExp);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addPrefixLines(PrefixLines prefixLines) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(prefixLines);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addSuffixLines(SuffixLines suffixLines) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(suffixLines);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addReplaceTokens(ReplaceTokens replaceTokens) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(replaceTokens);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addStripJavaComments(StripJavaComments stripJavaComments) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(stripJavaComments);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addStripLineBreaks(StripLineBreaks stripLineBreaks) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(stripLineBreaks);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addStripLineComments(StripLineComments stripLineComments) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(stripLineComments);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addTabsToSpaces(TabsToSpaces tabsToSpaces) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(tabsToSpaces);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addTailFilter(TailFilter tailFilter) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(tailFilter);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addEscapeUnicode(EscapeUnicode escapeUnicode) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(escapeUnicode);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addTokenFilter(TokenFilter tokenFilter) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(tokenFilter);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addDeleteCharacters(TokenFilter.DeleteCharacters deleteCharacters) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(deleteCharacters);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addContainsRegex(TokenFilter.ContainsRegex containsRegex) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(containsRegex);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addReplaceRegex(TokenFilter.ReplaceRegex replaceRegex) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(replaceRegex);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addTrim(TokenFilter.Trim trim) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(trim);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addReplaceString(TokenFilter.ReplaceString replaceString) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(replaceString);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addIgnoreBlank(TokenFilter.IgnoreBlank ignoreBlank) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(ignoreBlank);
            return;
        }
        throw noChildrenAllowed();
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.filterReaders.isEmpty()) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    public void add(ChainableReader chainableReader) {
        if (!isReference()) {
            setChecked(false);
            this.filterReaders.addElement(chainableReader);
            return;
        }
        throw noChildrenAllowed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                Iterator<Object> it = this.filterReaders.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof DataType) {
                        pushAndInvokeCircularReferenceCheck((DataType) next, stack, project);
                    }
                }
                setChecked(true);
            }
        }
    }
}
