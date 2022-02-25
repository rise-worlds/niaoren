package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2783cu;
import java.nio.ByteBuffer;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cs */
/* loaded from: classes2.dex */
public class C2781cs extends C2785cv implements AbstractC2780cr {

    /* renamed from: p */
    static final ByteBuffer f13706p = ByteBuffer.allocate(0);

    /* renamed from: q */
    private int f13707q;

    /* renamed from: r */
    private String f13708r;

    public C2781cs() {
        super(AbstractC2783cu.EnumC2784a.CLOSING);
        setFin(true);
    }

    public C2781cs(int i) {
        super(AbstractC2783cu.EnumC2784a.CLOSING);
        setFin(true);
        m16142a(i, "");
    }

    public C2781cs(int i, String str) {
        super(AbstractC2783cu.EnumC2784a.CLOSING);
        setFin(true);
        m16142a(i, str);
    }

    /* renamed from: a */
    private void m16142a(int i, String str) {
        if (str == null) {
            str = "";
        }
        if (i == 1015) {
            str = "";
            i = AbstractC2780cr.f13695e;
        }
        if (i != 1005) {
            byte[] a = C2806dl.m16062a(str);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.putInt(i);
            allocate.position(2);
            ByteBuffer allocate2 = ByteBuffer.allocate(a.length + 2);
            allocate2.put(allocate);
            allocate2.put(a);
            allocate2.rewind();
            setPayload(allocate2);
        } else if (str.length() > 0) {
            throw new C2774cl(1002, "A close frame must have a closecode if it has a reason");
        }
    }

    /* renamed from: g */
    private void m16140g() {
        this.f13707q = AbstractC2780cr.f13695e;
        ByteBuffer c = super.mo16138c();
        c.mark();
        if (c.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(c.getShort());
            allocate.position(0);
            this.f13707q = allocate.getInt();
            int i = this.f13707q;
            if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
                throw new C2775cm("closecode must not be sent over the wire: " + this.f13707q);
            }
        }
        c.reset();
    }

    @Override // com.tendcloud.tenddata.AbstractC2780cr
    /* renamed from: a */
    public int mo16143a() {
        return this.f13707q;
    }

    /* renamed from: h */
    private void m16139h() {
        if (this.f13707q == 1005) {
            this.f13708r = C2806dl.m16061a(super.mo16138c());
            return;
        }
        ByteBuffer c = super.mo16138c();
        int position = c.position();
        try {
            try {
                c.position(c.position() + 2);
                this.f13708r = C2806dl.m16061a(c);
            } catch (IllegalArgumentException e) {
                throw new C2775cm(e);
            }
        } finally {
            c.position(position);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2780cr
    /* renamed from: b */
    public String mo16141b() {
        return this.f13708r;
    }

    @Override // com.tendcloud.tenddata.C2785cv
    public String toString() {
        return super.toString() + "code: " + this.f13707q;
    }

    @Override // com.tendcloud.tenddata.C2785cv, com.tendcloud.tenddata.AbstractC2782ct
    public void setPayload(ByteBuffer byteBuffer) {
        super.setPayload(byteBuffer);
        m16140g();
        m16139h();
    }

    @Override // com.tendcloud.tenddata.C2785cv, com.tendcloud.tenddata.AbstractC2783cu
    /* renamed from: c */
    public ByteBuffer mo16138c() {
        if (this.f13707q == 1005) {
            return f13706p;
        }
        return super.mo16138c();
    }
}
