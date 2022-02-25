package org.apache.tools.ant.property;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.tools.ant.PropertyHelper;

/* loaded from: classes2.dex */
public class LocalPropertyStack {
    private final LinkedList<Map<String, Object>> stack = new LinkedList<>();
    private final Object LOCK = new Object();

    public void addLocal(String str) {
        synchronized (this.LOCK) {
            Map<String, Object> peek = this.stack.peek();
            if (peek != null) {
                peek.put(str, NullReturn.NULL);
            }
        }
    }

    public void enterScope() {
        synchronized (this.LOCK) {
            this.stack.addFirst(new ConcurrentHashMap());
        }
    }

    public void exitScope() {
        synchronized (this.LOCK) {
            this.stack.removeFirst().clear();
        }
    }

    public LocalPropertyStack copy() {
        LocalPropertyStack localPropertyStack;
        synchronized (this.LOCK) {
            localPropertyStack = new LocalPropertyStack();
            localPropertyStack.stack.addAll(this.stack);
        }
        return localPropertyStack;
    }

    public Object evaluate(String str, PropertyHelper propertyHelper) {
        synchronized (this.LOCK) {
            Iterator<Map<String, Object>> it = this.stack.iterator();
            while (it.hasNext()) {
                Object obj = it.next().get(str);
                if (obj != null) {
                    return obj;
                }
            }
            return null;
        }
    }

    public boolean setNew(String str, Object obj, PropertyHelper propertyHelper) {
        Map<String, Object> mapForProperty = getMapForProperty(str);
        if (mapForProperty == null) {
            return false;
        }
        if (mapForProperty.get(str) != NullReturn.NULL) {
            return true;
        }
        mapForProperty.put(str, obj);
        return true;
    }

    public boolean set(String str, Object obj, PropertyHelper propertyHelper) {
        Map<String, Object> mapForProperty = getMapForProperty(str);
        if (mapForProperty == null) {
            return false;
        }
        mapForProperty.put(str, obj);
        return true;
    }

    private Map<String, Object> getMapForProperty(String str) {
        synchronized (this.LOCK) {
            Iterator<Map<String, Object>> it = this.stack.iterator();
            while (it.hasNext()) {
                Map<String, Object> next = it.next();
                if (next.get(str) != null) {
                    return next;
                }
            }
            return null;
        }
    }
}
