package com.xuhao.didi.p082a.p084b;

import com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions;
import com.xuhao.didi.p082a.p084b.p085a.IReader;
import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.xuhao.didi.a.b.a */
/* loaded from: classes2.dex */
public abstract class AbsReader implements IReader<IIOCoreOptions> {

    /* renamed from: a */
    protected volatile IIOCoreOptions f14360a;

    /* renamed from: b */
    protected IStateSender f14361b;

    /* renamed from: c */
    protected InputStream f14362c;

    @Override // com.xuhao.didi.p082a.p084b.p085a.IReader
    /* renamed from: a */
    public void mo15192a(InputStream inputStream, IStateSender fVar) {
        this.f14361b = fVar;
        this.f14362c = inputStream;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IReader
    /* renamed from: a */
    public void mo15193a(IIOCoreOptions aVar) {
        this.f14360a = aVar;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IReader
    /* renamed from: a */
    public void mo15194a() {
        InputStream inputStream = this.f14362c;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
