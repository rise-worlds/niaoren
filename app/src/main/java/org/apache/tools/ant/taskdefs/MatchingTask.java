package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.selectors.AndSelector;
import org.apache.tools.ant.types.selectors.ContainsRegexpSelector;
import org.apache.tools.ant.types.selectors.ContainsSelector;
import org.apache.tools.ant.types.selectors.DateSelector;
import org.apache.tools.ant.types.selectors.DependSelector;
import org.apache.tools.ant.types.selectors.DepthSelector;
import org.apache.tools.ant.types.selectors.DifferentSelector;
import org.apache.tools.ant.types.selectors.ExtendSelector;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.MajoritySelector;
import org.apache.tools.ant.types.selectors.NoneSelector;
import org.apache.tools.ant.types.selectors.NotSelector;
import org.apache.tools.ant.types.selectors.OrSelector;
import org.apache.tools.ant.types.selectors.PresentSelector;
import org.apache.tools.ant.types.selectors.SelectSelector;
import org.apache.tools.ant.types.selectors.SelectorContainer;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.types.selectors.SizeSelector;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector;
import org.slf4j.Marker;
import p110z1.Consts;

/* loaded from: classes2.dex */
public abstract class MatchingTask extends Task implements SelectorContainer {
    protected FileSet fileset = new FileSet();

    @Override // org.apache.tools.ant.ProjectComponent
    public void setProject(Project project) {
        super.setProject(project);
        this.fileset.setProject(project);
    }

    public PatternSet.NameEntry createInclude() {
        return this.fileset.createInclude();
    }

    public PatternSet.NameEntry createIncludesFile() {
        return this.fileset.createIncludesFile();
    }

    public PatternSet.NameEntry createExclude() {
        return this.fileset.createExclude();
    }

    public PatternSet.NameEntry createExcludesFile() {
        return this.fileset.createExcludesFile();
    }

    public PatternSet createPatternSet() {
        return this.fileset.createPatternSet();
    }

    public void setIncludes(String str) {
        this.fileset.setIncludes(str);
    }

    public void XsetItems(String str) {
        log("The items attribute is deprecated. Please use the includes attribute.", 1);
        if (str == null || str.equals(Marker.ANY_MARKER) || str.equals(Consts.f23430h)) {
            createInclude().setName(SelectorUtils.DEEP_TREE_MATCH);
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, ", ");
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (trim.length() > 0) {
                PatternSet.NameEntry createInclude = createInclude();
                createInclude.setName(trim + "/**");
            }
        }
    }

    public void setExcludes(String str) {
        this.fileset.setExcludes(str);
    }

    public void XsetIgnore(String str) {
        log("The ignore attribute is deprecated.Please use the excludes attribute.", 1);
        if (str != null && str.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ", ", false);
            while (stringTokenizer.hasMoreTokens()) {
                PatternSet.NameEntry createExclude = createExclude();
                createExclude.setName("**/" + stringTokenizer.nextToken().trim() + "/**");
            }
        }
    }

    public void setDefaultexcludes(boolean z) {
        this.fileset.setDefaultexcludes(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DirectoryScanner getDirectoryScanner(File file) {
        this.fileset.setDir(file);
        return this.fileset.getDirectoryScanner(getProject());
    }

    public void setIncludesfile(File file) {
        this.fileset.setIncludesfile(file);
    }

    public void setExcludesfile(File file) {
        this.fileset.setExcludesfile(file);
    }

    public void setCaseSensitive(boolean z) {
        this.fileset.setCaseSensitive(z);
    }

    public void setFollowSymlinks(boolean z) {
        this.fileset.setFollowSymlinks(z);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public boolean hasSelectors() {
        return this.fileset.hasSelectors();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public int selectorCount() {
        return this.fileset.selectorCount();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public FileSelector[] getSelectors(Project project) {
        return this.fileset.getSelectors(project);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public Enumeration<FileSelector> selectorElements() {
        return this.fileset.selectorElements();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void appendSelector(FileSelector fileSelector) {
        this.fileset.appendSelector(fileSelector);
    }

    public void addSelector(SelectSelector selectSelector) {
        this.fileset.addSelector(selectSelector);
    }

    public void addAnd(AndSelector andSelector) {
        this.fileset.addAnd(andSelector);
    }

    public void addOr(OrSelector orSelector) {
        this.fileset.addOr(orSelector);
    }

    public void addNot(NotSelector notSelector) {
        this.fileset.addNot(notSelector);
    }

    public void addNone(NoneSelector noneSelector) {
        this.fileset.addNone(noneSelector);
    }

    public void addMajority(MajoritySelector majoritySelector) {
        this.fileset.addMajority(majoritySelector);
    }

    public void addDate(DateSelector dateSelector) {
        this.fileset.addDate(dateSelector);
    }

    public void addSize(SizeSelector sizeSelector) {
        this.fileset.addSize(sizeSelector);
    }

    public void addFilename(FilenameSelector filenameSelector) {
        this.fileset.addFilename(filenameSelector);
    }

    public void addCustom(ExtendSelector extendSelector) {
        this.fileset.addCustom(extendSelector);
    }

    public void addContains(ContainsSelector containsSelector) {
        this.fileset.addContains(containsSelector);
    }

    public void addPresent(PresentSelector presentSelector) {
        this.fileset.addPresent(presentSelector);
    }

    public void addDepth(DepthSelector depthSelector) {
        this.fileset.addDepth(depthSelector);
    }

    public void addDepend(DependSelector dependSelector) {
        this.fileset.addDepend(dependSelector);
    }

    public void addContainsRegexp(ContainsRegexpSelector containsRegexpSelector) {
        this.fileset.addContainsRegexp(containsRegexpSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDifferent(DifferentSelector differentSelector) {
        this.fileset.addDifferent(differentSelector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addType(TypeSelector typeSelector) {
        this.fileset.addType(typeSelector);
    }

    public void addModified(ModifiedSelector modifiedSelector) {
        this.fileset.addModified(modifiedSelector);
    }

    public void add(FileSelector fileSelector) {
        this.fileset.add(fileSelector);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final FileSet getImplicitFileSet() {
        return this.fileset;
    }
}
