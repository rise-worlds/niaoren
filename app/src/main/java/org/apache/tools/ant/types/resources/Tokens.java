package org.apache.tools.ant.types.resources;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.ConcatResourceInputStream;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.LineTokenizer;
import org.apache.tools.ant.util.Tokenizer;

/* loaded from: classes2.dex */
public class Tokens extends BaseResourceCollectionWrapper {
    private String encoding;
    private Tokenizer tokenizer;

    @Override // org.apache.tools.ant.types.resources.BaseResourceCollectionWrapper
    protected synchronized Collection<Resource> getCollection() {
        InputStreamReader inputStreamReader;
        ResourceCollection resourceCollection = getResourceCollection();
        if (resourceCollection.size() == 0) {
            return Collections.emptySet();
        }
        if (this.tokenizer == null) {
            this.tokenizer = new LineTokenizer();
        }
        ConcatResourceInputStream concatResourceInputStream = new ConcatResourceInputStream(resourceCollection);
        concatResourceInputStream.setManagingComponent(this);
        try {
            if (this.encoding == null) {
                inputStreamReader = new InputStreamReader(concatResourceInputStream);
            } else {
                try {
                    inputStreamReader = new InputStreamReader(concatResourceInputStream, this.encoding);
                } catch (UnsupportedEncodingException e) {
                    throw new BuildException(e);
                }
            }
            ArrayList arrayList = new ArrayList();
            Tokenizer tokenizer = this.tokenizer;
            while (true) {
                String token = tokenizer.getToken(inputStreamReader);
                if (token != null) {
                    StringResource stringResource = new StringResource(token);
                    stringResource.setProject(getProject());
                    arrayList.add(stringResource);
                    tokenizer = this.tokenizer;
                } else {
                    FileUtils.close(concatResourceInputStream);
                    return arrayList;
                }
            }
        } catch (IOException e2) {
            throw new BuildException("Error reading tokens", e2);
        }
    }

    public synchronized void setEncoding(String str) {
        this.encoding = str;
    }

    public synchronized void add(Tokenizer tokenizer) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.tokenizer == null) {
            this.tokenizer = tokenizer;
            setChecked(false);
        } else {
            throw new BuildException("Only one nested tokenizer allowed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.AbstractResourceCollectionWrapper, org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            super.dieOnCircularReference(stack, project);
            if (!isReference()) {
                if (this.tokenizer instanceof DataType) {
                    pushAndInvokeCircularReferenceCheck((DataType) this.tokenizer, stack, project);
                }
                setChecked(true);
            }
        }
    }
}
