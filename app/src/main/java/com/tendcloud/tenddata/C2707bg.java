package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2783cu;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bg */
/* loaded from: classes2.dex */
public class C2707bg {

    /* renamed from: d */
    private static final int f13571d = 30000;

    /* renamed from: e */
    private static final ByteBuffer f13572e = ByteBuffer.allocate(0);

    /* renamed from: a */
    private final AbstractC2708a f13573a;

    /* renamed from: b */
    private final C2709b f13574b;

    /* renamed from: c */
    private final URI f13575c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bg$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2708a {
        void bindEvents(JSONObject jSONObject);

        void cleanup();

        void clearEdits(JSONObject jSONObject);

        void performEdit(JSONObject jSONObject);

        void sendDeviceInfo();

        void sendSnapshot(JSONObject jSONObject);

        void setTweaks(JSONObject jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2707bg(URI uri, AbstractC2708a aVar, Socket socket) {
        this.f13573a = aVar;
        this.f13575c = uri;
        try {
            this.f13574b = new C2709b(uri, 30000, socket);
            this.f13574b.connectBlocking();
        } catch (InterruptedException e) {
            throw new Exception(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m16264a() {
        return !this.f13574b.isClosed() && !this.f13574b.isClosing() && !this.f13574b.isFlushAndClose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public BufferedOutputStream m16261b() {
        return new BufferedOutputStream(new C2710c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bg$b */
    /* loaded from: classes2.dex */
    public class C2709b extends AbstractRunnableC2762cd {
        C2709b(URI uri, int i, Socket socket) {
            super(uri, new C2770ch(), null, i);
            setSocket(socket);
        }

        @Override // com.tendcloud.tenddata.AbstractRunnableC2762cd
        public void onOpen(AbstractC2794dd ddVar) {
            try {
                C2811dq.iForDeveloper("Websocket connected");
                C2664ab.setDeveloperMode(true);
            } catch (Throwable unused) {
            }
        }

        @Override // com.tendcloud.tenddata.AbstractRunnableC2762cd
        public void onMessage(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("type");
                char c = 65535;
                switch (string.hashCode()) {
                    case -2061093049:
                        if (string.equals("device_info_request")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1643924835:
                        if (string.equals("clear_request")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1191248640:
                        if (string.equals("change_request")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1181127916:
                        if (string.equals("snapshot_request")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1149619396:
                        if (string.equals("tweak_request")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 138947050:
                        if (string.equals("dynamic_event_request")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        C2707bg.this.f13573a.sendDeviceInfo();
                        return;
                    case 1:
                        C2707bg.this.f13573a.sendSnapshot(jSONObject);
                        return;
                    case 2:
                        C2707bg.this.f13573a.performEdit(jSONObject);
                        return;
                    case 3:
                        C2707bg.this.f13573a.bindEvents(jSONObject);
                        return;
                    case 4:
                        C2707bg.this.f13573a.clearEdits(jSONObject);
                        return;
                    case 5:
                        C2707bg.this.f13573a.setTweaks(jSONObject);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // com.tendcloud.tenddata.AbstractRunnableC2762cd
        public void onClose(int i, String str, boolean z) {
            try {
                C2707bg.this.f13573a.cleanup();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // com.tendcloud.tenddata.AbstractRunnableC2762cd
        public void onError(Exception exc) {
            if (exc != null) {
                exc.getMessage();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bg$c */
    /* loaded from: classes2.dex */
    public class C2710c extends OutputStream {
        private C2710c() {
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            try {
                write(new byte[]{(byte) i}, 0, 1);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            try {
                write(bArr, 0, bArr.length);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            try {
                C2707bg.this.f13574b.sendFragmentedFrame(AbstractC2783cu.EnumC2784a.TEXT, ByteBuffer.wrap(bArr, i, i2), false);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            try {
                C2707bg.this.f13574b.sendFragmentedFrame(AbstractC2783cu.EnumC2784a.TEXT, C2707bg.f13572e, true);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private static String m16262a(ByteBuffer byteBuffer) {
        try {
            CharBuffer decode = Charset.forName("UTF-8").newDecoder().decode(byteBuffer);
            byteBuffer.flip();
            return decode.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
