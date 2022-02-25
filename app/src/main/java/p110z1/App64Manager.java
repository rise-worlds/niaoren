package p110z1;

import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.aix */
/* loaded from: classes3.dex */
public enum App64Manager {
    INSTANCE;
    
    private List<App64> app64s;

    public void init() {
        this.app64s = new ArrayList();
    }

    public void put(App64 aiwVar) {
        if (this.app64s == null) {
            init();
        }
        this.app64s.add(aiwVar);
    }

    public void remove(App64 aiwVar) {
        if (this.app64s == null) {
            init();
        }
        this.app64s.remove(aiwVar);
    }

    public List<App64> getApp64s() {
        return this.app64s;
    }
}
