package com.lidroid.xutils.task;

/* compiled from: PriorityObjectBlockingQueue.java */
/* loaded from: classes.dex */
class Node<T> {
    Node<T> next;
    private PriorityObject<?> value;
    private boolean valueAsT = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node(T t) {
        setValue(t);
    }

    public Priority getPriority() {
        return this.value.priority;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.lidroid.xutils.task.PriorityObject<?>, T, com.lidroid.xutils.task.PriorityObject] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public T getValue() {
        /*
            r2 = this;
            com.lidroid.xutils.task.PriorityObject<?> r0 = r2.value
            if (r0 != 0) goto L_0x0006
            r0 = 0
            return r0
        L_0x0006:
            boolean r1 = r2.valueAsT
            if (r1 == 0) goto L_0x000b
            return r0
        L_0x000b:
            E r0 = r0.obj
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.task.Node.getValue():java.lang.Object");
    }

    public void setValue(T t) {
        if (t == null) {
            this.value = null;
        } else if (t instanceof PriorityObject) {
            this.value = (PriorityObject) t;
            this.valueAsT = true;
        } else {
            this.value = new PriorityObject<>(Priority.DEFAULT, t);
        }
    }
}
