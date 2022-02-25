package p110z1;

import java.util.ArrayList;

/* renamed from: z1.atq */
/* loaded from: classes3.dex */
public final class CompositeDisposable implements Disposable, DisposableContainer {

    /* renamed from: a */
    OpenHashSet<Disposable> f17512a;

    /* renamed from: b */
    volatile boolean f17513b;

    public CompositeDisposable() {
    }

    public CompositeDisposable(@AbstractC3889atm Disposable... atrVarArr) {
        ObjectHelper.m9873a(atrVarArr, "disposables is null");
        this.f17512a = new OpenHashSet<>(atrVarArr.length + 1);
        for (Disposable atrVar : atrVarArr) {
            ObjectHelper.m9873a(atrVar, "A Disposable in the disposables array is null");
            this.f17512a.m9391a((OpenHashSet<Disposable>) atrVar);
        }
    }

    public CompositeDisposable(@AbstractC3889atm Iterable<? extends Disposable> iterable) {
        ObjectHelper.m9873a(iterable, "disposables is null");
        this.f17512a = new OpenHashSet<>();
        for (Disposable atrVar : iterable) {
            ObjectHelper.m9873a(atrVar, "A Disposable item in the disposables sequence is null");
            this.f17512a.m9391a((OpenHashSet<Disposable>) atrVar);
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        if (!this.f17513b) {
            synchronized (this) {
                if (!this.f17513b) {
                    this.f17513b = true;
                    OpenHashSet<Disposable> btdVar = this.f17512a;
                    this.f17512a = null;
                    m9998a(btdVar);
                }
            }
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f17513b;
    }

    @Override // p110z1.DisposableContainer
    /* renamed from: a */
    public boolean mo9939a(@AbstractC3889atm Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "disposable is null");
        if (!this.f17513b) {
            synchronized (this) {
                if (!this.f17513b) {
                    OpenHashSet<Disposable> btdVar = this.f17512a;
                    if (btdVar == null) {
                        btdVar = new OpenHashSet<>();
                        this.f17512a = btdVar;
                    }
                    btdVar.m9391a((OpenHashSet<Disposable>) atrVar);
                    return true;
                }
            }
        }
        atrVar.dispose();
        return false;
    }

    /* renamed from: a */
    public boolean m9997a(@AbstractC3889atm Disposable... atrVarArr) {
        ObjectHelper.m9873a(atrVarArr, "disposables is null");
        if (!this.f17513b) {
            synchronized (this) {
                if (!this.f17513b) {
                    OpenHashSet<Disposable> btdVar = this.f17512a;
                    if (btdVar == null) {
                        btdVar = new OpenHashSet<>(atrVarArr.length + 1);
                        this.f17512a = btdVar;
                    }
                    for (Disposable atrVar : atrVarArr) {
                        ObjectHelper.m9873a(atrVar, "A Disposable in the disposables array is null");
                        btdVar.m9391a((OpenHashSet<Disposable>) atrVar);
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
    public boolean mo9937b(@AbstractC3889atm Disposable atrVar) {
        if (!mo9936c(atrVar)) {
            return false;
        }
        atrVar.dispose();
        return true;
    }

    @Override // p110z1.DisposableContainer
    /* renamed from: c */
    public boolean mo9936c(@AbstractC3889atm Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "disposables is null");
        if (this.f17513b) {
            return false;
        }
        synchronized (this) {
            if (this.f17513b) {
                return false;
            }
            OpenHashSet<Disposable> btdVar = this.f17512a;
            if (btdVar != null && btdVar.m9389b(atrVar)) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: a */
    public void m9999a() {
        if (!this.f17513b) {
            synchronized (this) {
                if (!this.f17513b) {
                    OpenHashSet<Disposable> btdVar = this.f17512a;
                    this.f17512a = null;
                    m9998a(btdVar);
                }
            }
        }
    }

    /* renamed from: b */
    public int m9996b() {
        int i = 0;
        if (this.f17513b) {
            return 0;
        }
        synchronized (this) {
            if (this.f17513b) {
                return 0;
            }
            OpenHashSet<Disposable> btdVar = this.f17512a;
            if (btdVar != null) {
                i = btdVar.m9388c();
            }
            return i;
        }
    }

    /* renamed from: a */
    void m9998a(OpenHashSet<Disposable> btdVar) {
        Object[] b;
        if (btdVar != null) {
            ArrayList arrayList = null;
            for (Object obj : btdVar.m9390b()) {
                if (obj instanceof Disposable) {
                    try {
                        ((Disposable) obj).dispose();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(th);
                    }
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
