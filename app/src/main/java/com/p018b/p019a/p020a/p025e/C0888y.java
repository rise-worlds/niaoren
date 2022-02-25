package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.ByteString;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Reader.java */
/* renamed from: com.b.a.a.e.y */
/* loaded from: classes.dex */
public final class C0888y implements Closeable {

    /* renamed from: a */
    static final Logger f6007a = Logger.getLogger(Http2.class.getName());

    /* renamed from: b */
    final C0871e f6008b;

    /* renamed from: c */
    private final BufferedSource f6009c;

    /* renamed from: d */
    private final C0889z f6010d;

    /* renamed from: e */
    private final boolean f6011e;

    public C0888y(BufferedSource hVar, boolean z) {
        this.f6009c = hVar;
        this.f6011e = z;
        this.f6010d = new C0889z(this.f6009c);
        this.f6008b = new C0871e(this.f6010d);
    }

    /* renamed from: a */
    public final void m24601a(Http2Reader aaVar) {
        if (!this.f6011e) {
            ByteString c = this.f6009c.mo24283c(Http2.f5925a.mo24255g());
            if (f6007a.isLoggable(Level.FINE)) {
                f6007a.fine(Util.m24755a("<< CONNECTION %s", c.mo24257e()));
            }
            if (!Http2.f5925a.equals(c)) {
                throw Http2.m24642b("Expected a connection header but was %s", c.mo24267a());
            }
        } else if (!m24599a(true, aaVar)) {
            throw Http2.m24642b("Required SETTINGS preface not received", new Object[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public final boolean m24599a(boolean z, Http2Reader aaVar) {
        short s = 0;
        boolean z2 = false;
        short s2 = 0;
        short s3 = 0;
        try {
            this.f6009c.mo24288a(9L);
            int a = m24600a(this.f6009c);
            if (a < 0 || a > 16384) {
                throw Http2.m24642b("FRAME_SIZE_ERROR: %s", Integer.valueOf(a));
            }
            byte e = (byte) (this.f6009c.mo24281e() & 255);
            if (!z || e == 4) {
                byte e2 = (byte) (this.f6009c.mo24281e() & 255);
                int g = this.f6009c.mo24277g() & Integer.MAX_VALUE;
                if (f6007a.isLoggable(Level.FINE)) {
                    f6007a.fine(Http2.m24643a(true, g, a, e, e2));
                }
                switch (e) {
                    case 0:
                        boolean z3 = (e2 & 1) != 0;
                        if (!((e2 & 32) != 0)) {
                            if ((e2 & 8) != 0) {
                                s = (short) (this.f6009c.mo24281e() & 255);
                            }
                            aaVar.mo24606a(z3, g, this.f6009c, m24603a(a, e2, s));
                            this.f6009c.mo24278f(s);
                            break;
                        } else {
                            throw Http2.m24642b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
                        }
                    case 1:
                        if (g != 0) {
                            boolean z4 = (e2 & 1) != 0;
                            if ((e2 & 8) != 0) {
                                s3 = (short) (this.f6009c.mo24281e() & 255);
                            }
                            if ((e2 & 32) != 0) {
                                m24604a();
                                a -= 5;
                            }
                            aaVar.mo24605a(z4, g, m24602a(m24603a(a, e2, s3), s3, e2, g));
                            break;
                        } else {
                            throw Http2.m24642b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
                        }
                    case 2:
                        if (a != 5) {
                            throw Http2.m24642b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(a));
                        } else if (g != 0) {
                            m24604a();
                            break;
                        } else {
                            throw Http2.m24642b("TYPE_PRIORITY streamId == 0", new Object[0]);
                        }
                    case 3:
                        if (a != 4) {
                            throw Http2.m24642b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(a));
                        } else if (g != 0) {
                            int g2 = this.f6009c.mo24277g();
                            ErrorCode a2 = ErrorCode.m24665a(g2);
                            if (a2 != null) {
                                aaVar.mo24611a(g, a2);
                                break;
                            } else {
                                throw Http2.m24642b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(g2));
                            }
                        } else {
                            throw Http2.m24642b("TYPE_RST_STREAM streamId == 0", new Object[0]);
                        }
                    case 4:
                        if (g != 0) {
                            throw Http2.m24642b("TYPE_SETTINGS streamId != 0", new Object[0]);
                        } else if ((e2 & 1) != 0) {
                            if (a != 0) {
                                throw Http2.m24642b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                            }
                        } else if (a % 6 == 0) {
                            Settings alVar = new Settings();
                            for (int i = 0; i < a; i += 6) {
                                short f = this.f6009c.mo24279f();
                                int g3 = this.f6009c.mo24277g();
                                switch (f) {
                                    case 2:
                                        if (!(g3 == 0 || g3 == 1)) {
                                            throw Http2.m24642b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                                        }
                                        break;
                                    case 3:
                                        f = 4;
                                        break;
                                    case 4:
                                        f = 7;
                                        if (g3 >= 0) {
                                            break;
                                        } else {
                                            throw Http2.m24642b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                                        }
                                    case 5:
                                        if (g3 >= 16384 && g3 <= 16777215) {
                                            break;
                                        } else {
                                            throw Http2.m24642b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(g3));
                                        }
                                        break;
                                }
                                alVar.m24671a(f, g3);
                            }
                            aaVar.mo24608a(alVar);
                            break;
                        } else {
                            throw Http2.m24642b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(a));
                        }
                        break;
                    case 5:
                        if (g != 0) {
                            if ((e2 & 8) != 0) {
                                s2 = (short) (this.f6009c.mo24281e() & 255);
                            }
                            aaVar.mo24609a(this.f6009c.mo24277g() & Integer.MAX_VALUE, m24602a(m24603a(a - 4, e2, s2), s2, e2, g));
                            break;
                        } else {
                            throw Http2.m24642b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
                        }
                    case 6:
                        if (a != 8) {
                            throw Http2.m24642b("TYPE_PING length != 8: %s", Integer.valueOf(a));
                        } else if (g == 0) {
                            int g4 = this.f6009c.mo24277g();
                            int g5 = this.f6009c.mo24277g();
                            if ((e2 & 1) != 0) {
                                z2 = true;
                            }
                            aaVar.mo24607a(z2, g4, g5);
                            break;
                        } else {
                            throw Http2.m24642b("TYPE_PING streamId != 0", new Object[0]);
                        }
                    case 7:
                        if (a < 8) {
                            throw Http2.m24642b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(a));
                        } else if (g == 0) {
                            int g6 = this.f6009c.mo24277g();
                            int g7 = this.f6009c.mo24277g();
                            int i2 = a - 8;
                            if (ErrorCode.m24665a(g7) != null) {
                                ByteString iVar = ByteString.f6424b;
                                if (i2 > 0) {
                                    iVar = this.f6009c.mo24283c(i2);
                                }
                                aaVar.mo24610a(g6, iVar);
                                break;
                            } else {
                                throw Http2.m24642b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(g7));
                            }
                        } else {
                            throw Http2.m24642b("TYPE_GOAWAY streamId != 0", new Object[0]);
                        }
                    case 8:
                        if (a == 4) {
                            long g8 = this.f6009c.mo24277g() & 2147483647L;
                            if (g8 != 0) {
                                aaVar.mo24612a(g, g8);
                                break;
                            } else {
                                throw Http2.m24642b("windowSizeIncrement was 0", Long.valueOf(g8));
                            }
                        } else {
                            throw Http2.m24642b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(a));
                        }
                    default:
                        this.f6009c.mo24278f(a);
                        break;
                }
                return true;
            }
            throw Http2.m24642b("Expected a SETTINGS frame but was %s", Byte.valueOf(e));
        } catch (IOException unused) {
            return false;
        }
    }

    /* renamed from: a */
    private List<Header> m24602a(int i, short s, byte b, int i2) {
        C0889z zVar = this.f6010d;
        zVar.f6015d = i;
        zVar.f6012a = i;
        zVar.f6016e = s;
        zVar.f6013b = b;
        zVar.f6014c = i2;
        this.f6008b.m24663a();
        return this.f6008b.m24659b();
    }

    /* renamed from: a */
    private void m24604a() {
        this.f6009c.mo24277g();
        this.f6009c.mo24281e();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6009c.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m24600a(BufferedSource hVar) {
        return (hVar.mo24281e() & 255) | ((hVar.mo24281e() & 255) << 16) | ((hVar.mo24281e() & 255) << 8);
    }

    /* renamed from: a */
    private static int m24603a(int i, byte b, short s) {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        throw Http2.m24642b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }
}
