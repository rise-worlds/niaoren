package p110z1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* renamed from: z1.avd */
/* loaded from: classes3.dex */
public final class ListCompositeDisposable implements Disposable, DisposableContainer {

    /* renamed from: a */
    List<Disposable> f17553a;

    /* renamed from: b */
    volatile boolean f17554b;

    public ListCompositeDisposable() {
    }

    public ListCompositeDisposable(Disposable... atrVarArr) {
        ObjectHelper.m9873a(atrVarArr, "resources is null");
        this.f17553a = new LinkedList();
        for (Disposable atrVar : atrVarArr) {
            ObjectHelper.m9873a(atrVar, "Disposable item is null");
            this.f17553a.add(atrVar);
        }
    }

    public ListCompositeDisposable(Iterable<? extends Disposable> iterable) {
        ObjectHelper.m9873a(iterable, "resources is null");
        this.f17553a = new LinkedList();
        for (Disposable atrVar : iterable) {
            ObjectHelper.m9873a(atrVar, "Disposable item is null");
            this.f17553a.add(atrVar);
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        if (!this.f17554b) {
            synchronized (this) {
                if (!this.f17554b) {
                    this.f17554b = true;
                    List<Disposable> list = this.f17553a;
                    this.f17553a = null;
                    m9940a(list);
                }
            }
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f17554b;
    }

    @Override // p110z1.DisposableContainer
    /* renamed from: a */
    public boolean mo9939a(Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "d is null");
        if (!this.f17554b) {
            synchronized (this) {
                if (!this.f17554b) {
                    List list = this.f17553a;
                    if (list == null) {
                        list = new LinkedList();
                        this.f17553a = list;
                    }
                    list.add(atrVar);
                    return true;
                }
            }
        }
        atrVar.dispose();
        return false;
    }

    /* renamed from: a */
    public boolean m9938a(Disposable... atrVarArr) {
        ObjectHelper.m9873a(atrVarArr, "ds is null");
        if (!this.f17554b) {
            synchronized (this) {
                if (!this.f17554b) {
                    List list = this.f17553a;
                    if (list == null) {
                        list = new LinkedList();
                        this.f17553a = list;
                    }
                    for (Disposable atrVar : atrVarArr) {
                        ObjectHelper.m9873a(atrVar, "d is null");
                        list.add(atrVar);
                    }
                    return true;
                }
            }
        }
        for (Disposable atrVar2 : atrVarArr) {
            atrVar2.dispose();
        }
        return false;
    }

    @Override // p110z1.DisposableContainer
    /* renamed from: b */
    public boolean mo9937b(Disposable atrVar) {
        if (!mo9936c(atrVar)) {
            return false;
        }
        atrVar.dispose();
        return true;
    }

    @Override // p110z1.DisposableContainer
    /* renamed from: c */
    public boolean mo9936c(Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "Disposable item is null");
        if (this.f17554b) {
            return false;
        }
        synchronized (this) {
            if (this.f17554b) {
                return false;
            }
            List<Disposable> list = this.f17553a;
            if (list != null && list.remove(atrVar)) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: a */
    public void m9941a() {
        if (!this.f17554b) {
            synchronized (this) {
                if (!this.f17554b) {
                    List<Disposable> list = this.f17553a;
                    this.f17553a = null;
                    m9940a(list);
                }
            }
        }
    }

    /* renamed from: a */
    void m9940a(List<Disposable> list) {
        if (list != null) {
            ArrayList arrayList = null;
            for (Disposable atrVar : list) {
                try {
                    atrVar.dispose();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
            if (arrayList == null) {
                return;
            }
            if (arrayList.size() == 1) {
                throw ExceptionHelper.m9432a((Throwable) arrayList.get(0));
            }
            throw new CompositeException(arrayList);
        }
    }
}
