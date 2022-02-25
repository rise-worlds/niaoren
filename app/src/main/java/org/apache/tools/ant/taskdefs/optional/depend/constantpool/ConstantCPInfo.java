package org.apache.tools.ant.taskdefs.optional.depend.constantpool;

/* loaded from: classes2.dex */
public abstract class ConstantCPInfo extends ConstantPoolEntry {
    private Object value;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConstantCPInfo(int i, int i2) {
        super(i, i2);
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }
}
