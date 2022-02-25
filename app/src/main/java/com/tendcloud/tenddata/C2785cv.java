package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2783cu;
import java.nio.ByteBuffer;
import java.util.Arrays;
import p110z1.C4963cj;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cv */
/* loaded from: classes2.dex */
public class C2785cv implements AbstractC2782ct {

    /* renamed from: a_ */
    protected static byte[] f13709a_ = new byte[0];

    /* renamed from: b_ */
    protected boolean f13710b_;

    /* renamed from: c_ */
    protected AbstractC2783cu.EnumC2784a f13711c_;

    /* renamed from: d_ */
    protected boolean f13712d_;

    /* renamed from: e */
    private ByteBuffer f13713e;

    public C2785cv() {
    }

    public C2785cv(AbstractC2783cu.EnumC2784a aVar) {
        this.f13711c_ = aVar;
        this.f13713e = ByteBuffer.wrap(f13709a_);
    }

    public C2785cv(AbstractC2783cu cuVar) {
        this.f13710b_ = cuVar.mo16137d();
        this.f13711c_ = cuVar.mo16135f();
        this.f13713e = cuVar.mo16138c();
        this.f13712d_ = cuVar.mo16136e();
    }

    @Override // com.tendcloud.tenddata.AbstractC2783cu
    /* renamed from: d */
    public boolean mo16137d() {
        return this.f13710b_;
    }

    @Override // com.tendcloud.tenddata.AbstractC2783cu
    /* renamed from: f */
    public AbstractC2783cu.EnumC2784a mo16135f() {
        return this.f13711c_;
    }

    @Override // com.tendcloud.tenddata.AbstractC2783cu
    /* renamed from: e */
    public boolean mo16136e() {
        return this.f13712d_;
    }

    @Override // com.tendcloud.tenddata.AbstractC2783cu
    /* renamed from: c */
    public ByteBuffer mo16138c() {
        return this.f13713e;
    }

    @Override // com.tendcloud.tenddata.AbstractC2782ct
    public void setFin(boolean z) {
        this.f13710b_ = z;
    }

    @Override // com.tendcloud.tenddata.AbstractC2782ct
    public void setOptcode(AbstractC2783cu.EnumC2784a aVar) {
        this.f13711c_ = aVar;
    }

    @Override // com.tendcloud.tenddata.AbstractC2782ct
    public void setPayload(ByteBuffer byteBuffer) {
        this.f13713e = byteBuffer;
    }

    @Override // com.tendcloud.tenddata.AbstractC2782ct
    public void setTransferemasked(boolean z) {
        this.f13712d_ = z;
    }

    @Override // com.tendcloud.tenddata.AbstractC2783cu
    public void append(AbstractC2783cu cuVar) {
        ByteBuffer c = cuVar.mo16138c();
        if (this.f13713e == null) {
            this.f13713e = ByteBuffer.allocate(c.remaining());
            c.mark();
            this.f13713e.put(c);
            c.reset();
        } else {
            c.mark();
            ByteBuffer byteBuffer = this.f13713e;
            byteBuffer.position(byteBuffer.limit());
            ByteBuffer byteBuffer2 = this.f13713e;
            byteBuffer2.limit(byteBuffer2.capacity());
            if (c.remaining() > this.f13713e.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(c.remaining() + this.f13713e.capacity());
                this.f13713e.flip();
                allocate.put(this.f13713e);
                allocate.put(c);
                this.f13713e = allocate;
            } else {
                this.f13713e.put(c);
            }
            this.f13713e.rewind();
            c.reset();
        }
        this.f13710b_ = cuVar.mo16137d();
    }

    public String toString() {
        return "Framedata{ optcode:" + mo16135f() + ", fin:" + mo16137d() + ", payloadlength:[pos:" + this.f13713e.position() + ", len:" + this.f13713e.remaining() + "], payload:" + Arrays.toString(C2806dl.m16062a(new String(this.f13713e.array()))) + C4963cj.f20747d;
    }
}
