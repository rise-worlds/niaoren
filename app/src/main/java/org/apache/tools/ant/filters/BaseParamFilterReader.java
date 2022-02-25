package org.apache.tools.ant.filters;

import java.io.Reader;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Parameterizable;

/* loaded from: classes2.dex */
public abstract class BaseParamFilterReader extends BaseFilterReader implements Parameterizable {
    private Parameter[] parameters;

    public BaseParamFilterReader() {
    }

    public BaseParamFilterReader(Reader reader) {
        super(reader);
    }

    @Override // org.apache.tools.ant.types.Parameterizable
    public final void setParameters(Parameter[] parameterArr) {
        this.parameters = parameterArr;
        setInitialized(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parameter[] getParameters() {
        return this.parameters;
    }
}
