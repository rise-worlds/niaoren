package org.apache.tools.ant.types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.util.FileUtils;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class PatternSet extends DataType implements Cloneable {
    private List<NameEntry> includeList = new ArrayList();
    private List<NameEntry> excludeList = new ArrayList();
    private List<NameEntry> includesFileList = new ArrayList();
    private List<NameEntry> excludesFileList = new ArrayList();

    /* loaded from: classes2.dex */
    public class NameEntry {
        private Object ifCond;
        private String name;
        private Object unlessCond;

        public NameEntry() {
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setIf(Object obj) {
            this.ifCond = obj;
        }

        public void setIf(String str) {
            setIf((Object) str);
        }

        public void setUnless(Object obj) {
            this.unlessCond = obj;
        }

        public void setUnless(String str) {
            setUnless((Object) str);
        }

        public String getName() {
            return this.name;
        }

        public String evalName(Project project) {
            if (valid(project)) {
                return this.name;
            }
            return null;
        }

        private boolean valid(Project project) {
            PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(project);
            return propertyHelper.testIfCondition(this.ifCond) && propertyHelper.testUnlessCondition(this.unlessCond);
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            String str = this.name;
            if (str == null) {
                stringBuffer.append("noname");
            } else {
                stringBuffer.append(str);
            }
            if (!(this.ifCond == null && this.unlessCond == null)) {
                stringBuffer.append(":");
                String str2 = "";
                if (this.ifCond != null) {
                    stringBuffer.append("if->");
                    stringBuffer.append(this.ifCond);
                    str2 = C4963cj.f20745b;
                }
                if (this.unlessCond != null) {
                    stringBuffer.append(str2);
                    stringBuffer.append("unless->");
                    stringBuffer.append(this.unlessCond);
                }
            }
            return stringBuffer.toString();
        }
    }

    /* loaded from: classes2.dex */
    private static final class InvertedPatternSet extends PatternSet {
        private InvertedPatternSet(PatternSet patternSet) {
            setProject(patternSet.getProject());
            addConfiguredPatternset(patternSet);
        }

        @Override // org.apache.tools.ant.types.PatternSet
        public String[] getIncludePatterns(Project project) {
            return PatternSet.super.getExcludePatterns(project);
        }

        @Override // org.apache.tools.ant.types.PatternSet
        public String[] getExcludePatterns(Project project) {
            return PatternSet.super.getIncludePatterns(project);
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (!this.includeList.isEmpty() || !this.excludeList.isEmpty()) {
            throw tooManyAttributes();
        }
        super.setRefid(reference);
    }

    public void addConfiguredPatternset(PatternSet patternSet) {
        if (!isReference()) {
            String[] includePatterns = patternSet.getIncludePatterns(getProject());
            String[] excludePatterns = patternSet.getExcludePatterns(getProject());
            if (includePatterns != null) {
                for (String str : includePatterns) {
                    createInclude().setName(str);
                }
            }
            if (excludePatterns != null) {
                for (String str2 : excludePatterns) {
                    createExclude().setName(str2);
                }
                return;
            }
            return;
        }
        throw noChildrenAllowed();
    }

    public NameEntry createInclude() {
        if (!isReference()) {
            return addPatternToList(this.includeList);
        }
        throw noChildrenAllowed();
    }

    public NameEntry createIncludesFile() {
        if (!isReference()) {
            return addPatternToList(this.includesFileList);
        }
        throw noChildrenAllowed();
    }

    public NameEntry createExclude() {
        if (!isReference()) {
            return addPatternToList(this.excludeList);
        }
        throw noChildrenAllowed();
    }

    public NameEntry createExcludesFile() {
        if (!isReference()) {
            return addPatternToList(this.excludesFileList);
        }
        throw noChildrenAllowed();
    }

    public void setIncludes(String str) {
        if (isReference()) {
            throw tooManyAttributes();
        } else if (str != null && str.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ", ", false);
            while (stringTokenizer.hasMoreTokens()) {
                createInclude().setName(stringTokenizer.nextToken());
            }
        }
    }

    public void setExcludes(String str) {
        if (isReference()) {
            throw tooManyAttributes();
        } else if (str != null && str.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ", ", false);
            while (stringTokenizer.hasMoreTokens()) {
                createExclude().setName(stringTokenizer.nextToken());
            }
        }
    }

    private NameEntry addPatternToList(List<NameEntry> list) {
        NameEntry nameEntry = new NameEntry();
        list.add(nameEntry);
        return nameEntry;
    }

    public void setIncludesfile(File file) throws BuildException {
        if (!isReference()) {
            createIncludesFile().setName(file.getAbsolutePath());
            return;
        }
        throw tooManyAttributes();
    }

    public void setExcludesfile(File file) throws BuildException {
        if (!isReference()) {
            createExcludesFile().setName(file.getAbsolutePath());
            return;
        }
        throw tooManyAttributes();
    }

    private void readPatterns(File file, List<NameEntry> list, Project project) throws BuildException {
        Throwable th;
        IOException e;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (readLine.length() > 0) {
                    addPatternToList(list).setName(project.replaceProperties(readLine));
                }
            }
            FileUtils.close(bufferedReader);
        } catch (IOException e3) {
            e = e3;
            throw new BuildException("An error occurred while reading from pattern file: " + file, e);
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = bufferedReader;
            FileUtils.close(bufferedReader2);
            throw th;
        }
    }

    public void append(PatternSet patternSet, Project project) {
        if (!isReference()) {
            dieOnCircularReference(project);
            String[] includePatterns = patternSet.getIncludePatterns(project);
            if (includePatterns != null) {
                for (String str : includePatterns) {
                    createInclude().setName(str);
                }
            }
            String[] excludePatterns = patternSet.getExcludePatterns(project);
            if (excludePatterns != null) {
                for (String str2 : excludePatterns) {
                    createExclude().setName(str2);
                }
                return;
            }
            return;
        }
        throw new BuildException("Cannot append to a reference");
    }

    public String[] getIncludePatterns(Project project) {
        if (isReference()) {
            return getRef(project).getIncludePatterns(project);
        }
        dieOnCircularReference(project);
        readFiles(project);
        return makeArray(this.includeList, project);
    }

    public String[] getExcludePatterns(Project project) {
        if (isReference()) {
            return getRef(project).getExcludePatterns(project);
        }
        dieOnCircularReference(project);
        readFiles(project);
        return makeArray(this.excludeList, project);
    }

    public boolean hasPatterns(Project project) {
        if (isReference()) {
            return getRef(project).hasPatterns(project);
        }
        dieOnCircularReference(project);
        return this.includesFileList.size() > 0 || this.excludesFileList.size() > 0 || this.includeList.size() > 0 || this.excludeList.size() > 0;
    }

    private PatternSet getRef(Project project) {
        return (PatternSet) getCheckedRef(project);
    }

    private String[] makeArray(List<NameEntry> list, Project project) {
        if (list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NameEntry nameEntry : list) {
            String evalName = nameEntry.evalName(project);
            if (evalName != null && evalName.length() > 0) {
                arrayList.add(evalName);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void readFiles(Project project) {
        if (this.includesFileList.size() > 0) {
            for (NameEntry nameEntry : this.includesFileList) {
                String evalName = nameEntry.evalName(project);
                if (evalName != null) {
                    File resolveFile = project.resolveFile(evalName);
                    if (resolveFile.exists()) {
                        readPatterns(resolveFile, this.includeList, project);
                    } else {
                        throw new BuildException("Includesfile " + resolveFile.getAbsolutePath() + " not found.");
                    }
                }
            }
            this.includesFileList.clear();
        }
        if (this.excludesFileList.size() > 0) {
            for (NameEntry nameEntry2 : this.excludesFileList) {
                String evalName2 = nameEntry2.evalName(project);
                if (evalName2 != null) {
                    File resolveFile2 = project.resolveFile(evalName2);
                    if (resolveFile2.exists()) {
                        readPatterns(resolveFile2, this.excludeList, project);
                    } else {
                        throw new BuildException("Excludesfile " + resolveFile2.getAbsolutePath() + " not found.");
                    }
                }
            }
            this.excludesFileList.clear();
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        return "patternSet{ includes: " + this.includeList + " excludes: " + this.excludeList + " }";
    }

    @Override // org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        try {
            PatternSet patternSet = (PatternSet) super.clone();
            patternSet.includeList = new ArrayList(this.includeList);
            patternSet.excludeList = new ArrayList(this.excludeList);
            patternSet.includesFileList = new ArrayList(this.includesFileList);
            patternSet.excludesFileList = new ArrayList(this.excludesFileList);
            return patternSet;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    public void addConfiguredInvert(PatternSet patternSet) {
        addConfiguredPatternset(new InvertedPatternSet());
    }
}
