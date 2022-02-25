package org.apache.tools.ant.types;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Redirector;
import org.apache.tools.ant.util.MergingMapper;

/* loaded from: classes2.dex */
public class RedirectorElement extends DataType {
    private Boolean alwaysLog;
    private Boolean append;
    private Boolean createEmptyFiles;
    private String errorEncoding;
    private Mapper errorMapper;
    private String errorProperty;
    private String inputEncoding;
    private Mapper inputMapper;
    private String inputString;
    private Boolean logError;
    private Boolean logInputString;
    private String outputEncoding;
    private Mapper outputMapper;
    private String outputProperty;
    private boolean usingInput = false;
    private boolean usingOutput = false;
    private boolean usingError = false;
    private Vector<FilterChain> inputFilterChains = new Vector<>();
    private Vector<FilterChain> outputFilterChains = new Vector<>();
    private Vector<FilterChain> errorFilterChains = new Vector<>();
    private boolean outputIsBinary = false;

    public void addConfiguredInputMapper(Mapper mapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.inputMapper == null) {
            setChecked(false);
            this.inputMapper = mapper;
        } else if (this.usingInput) {
            throw new BuildException("attribute \"input\" cannot coexist with a nested <inputmapper>");
        } else {
            throw new BuildException("Cannot have > 1 <inputmapper>");
        }
    }

    public void addConfiguredOutputMapper(Mapper mapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.outputMapper == null) {
            setChecked(false);
            this.outputMapper = mapper;
        } else if (this.usingOutput) {
            throw new BuildException("attribute \"output\" cannot coexist with a nested <outputmapper>");
        } else {
            throw new BuildException("Cannot have > 1 <outputmapper>");
        }
    }

    public void addConfiguredErrorMapper(Mapper mapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.errorMapper == null) {
            setChecked(false);
            this.errorMapper = mapper;
        } else if (this.usingError) {
            throw new BuildException("attribute \"error\" cannot coexist with a nested <errormapper>");
        } else {
            throw new BuildException("Cannot have > 1 <errormapper>");
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (!this.usingInput && !this.usingOutput && !this.usingError && this.inputString == null && this.logError == null && this.append == null && this.createEmptyFiles == null && this.inputEncoding == null && this.outputEncoding == null && this.errorEncoding == null && this.outputProperty == null && this.errorProperty == null && this.logInputString == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    public void setInput(File file) {
        if (isReference()) {
            throw tooManyAttributes();
        } else if (this.inputString == null) {
            this.usingInput = true;
            this.inputMapper = createMergeMapper(file);
        } else {
            throw new BuildException("The \"input\" and \"inputstring\" attributes cannot both be specified");
        }
    }

    public void setInputString(String str) {
        if (isReference()) {
            throw tooManyAttributes();
        } else if (!this.usingInput) {
            this.inputString = str;
        } else {
            throw new BuildException("The \"input\" and \"inputstring\" attributes cannot both be specified");
        }
    }

    public void setLogInputString(boolean z) {
        if (!isReference()) {
            this.logInputString = z ? Boolean.TRUE : Boolean.FALSE;
            return;
        }
        throw tooManyAttributes();
    }

    public void setOutput(File file) {
        if (isReference()) {
            throw tooManyAttributes();
        } else if (file != null) {
            this.usingOutput = true;
            this.outputMapper = createMergeMapper(file);
        } else {
            throw new IllegalArgumentException("output file specified as null");
        }
    }

    public void setOutputEncoding(String str) {
        if (!isReference()) {
            this.outputEncoding = str;
            return;
        }
        throw tooManyAttributes();
    }

    public void setErrorEncoding(String str) {
        if (!isReference()) {
            this.errorEncoding = str;
            return;
        }
        throw tooManyAttributes();
    }

    public void setInputEncoding(String str) {
        if (!isReference()) {
            this.inputEncoding = str;
            return;
        }
        throw tooManyAttributes();
    }

    public void setLogError(boolean z) {
        if (!isReference()) {
            this.logError = z ? Boolean.TRUE : Boolean.FALSE;
            return;
        }
        throw tooManyAttributes();
    }

    public void setError(File file) {
        if (isReference()) {
            throw tooManyAttributes();
        } else if (file != null) {
            this.usingError = true;
            this.errorMapper = createMergeMapper(file);
        } else {
            throw new IllegalArgumentException("error file specified as null");
        }
    }

    public void setOutputProperty(String str) {
        if (!isReference()) {
            this.outputProperty = str;
            return;
        }
        throw tooManyAttributes();
    }

    public void setAppend(boolean z) {
        if (!isReference()) {
            this.append = z ? Boolean.TRUE : Boolean.FALSE;
            return;
        }
        throw tooManyAttributes();
    }

    public void setAlwaysLog(boolean z) {
        if (!isReference()) {
            this.alwaysLog = z ? Boolean.TRUE : Boolean.FALSE;
            return;
        }
        throw tooManyAttributes();
    }

    public void setCreateEmptyFiles(boolean z) {
        if (!isReference()) {
            this.createEmptyFiles = z ? Boolean.TRUE : Boolean.FALSE;
            return;
        }
        throw tooManyAttributes();
    }

    public void setErrorProperty(String str) {
        if (!isReference()) {
            this.errorProperty = str;
            return;
        }
        throw tooManyAttributes();
    }

    public FilterChain createInputFilterChain() {
        if (!isReference()) {
            FilterChain filterChain = new FilterChain();
            filterChain.setProject(getProject());
            this.inputFilterChains.add(filterChain);
            setChecked(false);
            return filterChain;
        }
        throw noChildrenAllowed();
    }

    public FilterChain createOutputFilterChain() {
        if (!isReference()) {
            FilterChain filterChain = new FilterChain();
            filterChain.setProject(getProject());
            this.outputFilterChains.add(filterChain);
            setChecked(false);
            return filterChain;
        }
        throw noChildrenAllowed();
    }

    public FilterChain createErrorFilterChain() {
        if (!isReference()) {
            FilterChain filterChain = new FilterChain();
            filterChain.setProject(getProject());
            this.errorFilterChains.add(filterChain);
            setChecked(false);
            return filterChain;
        }
        throw noChildrenAllowed();
    }

    public void setBinaryOutput(boolean z) {
        this.outputIsBinary = z;
    }

    public void configure(Redirector redirector) {
        configure(redirector, null);
    }

    public void configure(Redirector redirector, String str) {
        String[] strArr;
        String[] strArr2;
        if (isReference()) {
            getRef().configure(redirector, str);
            return;
        }
        dieOnCircularReference();
        Boolean bool = this.alwaysLog;
        if (bool != null) {
            redirector.setAlwaysLog(bool.booleanValue());
        }
        Boolean bool2 = this.logError;
        if (bool2 != null) {
            redirector.setLogError(bool2.booleanValue());
        }
        Boolean bool3 = this.append;
        if (bool3 != null) {
            redirector.setAppend(bool3.booleanValue());
        }
        Boolean bool4 = this.createEmptyFiles;
        if (bool4 != null) {
            redirector.setCreateEmptyFiles(bool4.booleanValue());
        }
        String str2 = this.outputProperty;
        if (str2 != null) {
            redirector.setOutputProperty(str2);
        }
        String str3 = this.errorProperty;
        if (str3 != null) {
            redirector.setErrorProperty(str3);
        }
        String str4 = this.inputString;
        if (str4 != null) {
            redirector.setInputString(str4);
        }
        Boolean bool5 = this.logInputString;
        if (bool5 != null) {
            redirector.setLogInputString(bool5.booleanValue());
        }
        Mapper mapper = this.inputMapper;
        String[] strArr3 = null;
        if (mapper != null) {
            try {
                strArr = mapper.getImplementation().mapFileName(str);
            } catch (NullPointerException e) {
                if (str == null) {
                    strArr = null;
                } else {
                    throw e;
                }
            }
            if (strArr != null && strArr.length > 0) {
                redirector.setInput(toFileArray(strArr));
            }
        }
        Mapper mapper2 = this.outputMapper;
        if (mapper2 != null) {
            try {
                strArr2 = mapper2.getImplementation().mapFileName(str);
            } catch (NullPointerException e2) {
                if (str == null) {
                    strArr2 = null;
                } else {
                    throw e2;
                }
            }
            if (strArr2 != null && strArr2.length > 0) {
                redirector.setOutput(toFileArray(strArr2));
            }
        }
        Mapper mapper3 = this.errorMapper;
        if (mapper3 != null) {
            try {
                strArr3 = mapper3.getImplementation().mapFileName(str);
            } catch (NullPointerException e3) {
                if (str != null) {
                    throw e3;
                }
            }
            if (strArr3 != null && strArr3.length > 0) {
                redirector.setError(toFileArray(strArr3));
            }
        }
        if (this.inputFilterChains.size() > 0) {
            redirector.setInputFilterChains(this.inputFilterChains);
        }
        if (this.outputFilterChains.size() > 0) {
            redirector.setOutputFilterChains(this.outputFilterChains);
        }
        if (this.errorFilterChains.size() > 0) {
            redirector.setErrorFilterChains(this.errorFilterChains);
        }
        String str5 = this.inputEncoding;
        if (str5 != null) {
            redirector.setInputEncoding(str5);
        }
        String str6 = this.outputEncoding;
        if (str6 != null) {
            redirector.setOutputEncoding(str6);
        }
        String str7 = this.errorEncoding;
        if (str7 != null) {
            redirector.setErrorEncoding(str7);
        }
        redirector.setBinaryOutput(this.outputIsBinary);
    }

    protected Mapper createMergeMapper(File file) {
        Mapper mapper = new Mapper(getProject());
        mapper.setClassname(MergingMapper.class.getName());
        mapper.setTo(file.getAbsolutePath());
        return mapper;
    }

    protected File[] toFileArray(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null) {
                arrayList.add(getProject().resolveFile(strArr[i]));
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
                return;
            }
            Object[] objArr = {this.inputMapper, this.outputMapper, this.errorMapper};
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] != null) {
                    stack.push(objArr[i]);
                    objArr[i].dieOnCircularReference(stack, project);
                    stack.pop();
                }
            }
            for (List<FilterChain> list : Arrays.asList(this.inputFilterChains, this.outputFilterChains, this.errorFilterChains)) {
                if (list != null) {
                    for (FilterChain filterChain : list) {
                        pushAndInvokeCircularReferenceCheck(filterChain, stack, project);
                    }
                }
            }
            setChecked(true);
        }
    }

    private RedirectorElement getRef() {
        return (RedirectorElement) getCheckedRef();
    }
}
