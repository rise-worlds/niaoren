package com.alipay.android.phone.mrpc.core;

import com.common.utils.VMProperUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* renamed from: com.alipay.android.phone.mrpc.core.j */
/* loaded from: classes.dex */
public final class C0603j extends AbstractC0582a {

    /* renamed from: g */
    private AbstractC0600g f153g;

    public C0603j(AbstractC0600g gVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.f153g = gVar;
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0616v
    /* renamed from: a */
    public final Object mo25446a() {
        C0609o oVar = new C0609o(this.f153g.mo25499a());
        oVar.m25478a(this.f122b);
        oVar.m25482a(this.f125e);
        oVar.m25479a(this.f126f);
        oVar.m25481a(ConnectionModel.f10389a, String.valueOf(this.f124d));
        oVar.m25481a("operationType", this.f123c);
        oVar.m25481a(HttpRequest.ENCODING_GZIP, String.valueOf(this.f153g.mo25496d()));
        oVar.m25480a(new BasicHeader(VMProperUtil.VMUUID, UUID.randomUUID().toString()));
        List<Header> b = this.f153g.mo25497c().m25515b();
        if (b != null && !b.isEmpty()) {
            for (Header header : b) {
                oVar.m25480a(header);
            }
        }
        StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(oVar.toString());
        try {
            C0615u uVar = this.f153g.mo25498b().mo25487a(oVar).get();
            if (uVar != null) {
                return uVar.m25448b();
            }
            throw new RpcException((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(13, "", e);
        } catch (CancellationException e2) {
            throw new RpcException(13, "", e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(9, "", e3);
            }
            HttpException httpException = (HttpException) cause;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        }
    }
}
