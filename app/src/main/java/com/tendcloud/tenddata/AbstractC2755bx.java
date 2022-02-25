package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2783cu;
import java.net.InetSocketAddress;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bx */
/* loaded from: classes2.dex */
public abstract class AbstractC2755bx implements AbstractC2759ca {
    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketHandshakeReceivedAsClient(AbstractC2752bw bwVar, AbstractC2786cw cwVar, AbstractC2794dd ddVar) {
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketHandshakeSentAsClient(AbstractC2752bw bwVar, AbstractC2786cw cwVar) {
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketMessageFragment(AbstractC2752bw bwVar, AbstractC2783cu cuVar) {
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketPong(AbstractC2752bw bwVar, AbstractC2783cu cuVar) {
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public AbstractC2795de onWebsocketHandshakeReceivedAsServer(AbstractC2752bw bwVar, AbstractC2765cf cfVar, AbstractC2786cw cwVar) {
        return new C2791da();
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketPing(AbstractC2752bw bwVar, AbstractC2783cu cuVar) {
        C2785cv cvVar = new C2785cv(cuVar);
        cvVar.setOptcode(AbstractC2783cu.EnumC2784a.PONG);
        bwVar.sendFrame(cvVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public String getFlashPolicy(AbstractC2752bw bwVar) {
        InetSocketAddress localSocketAddress = bwVar.getLocalSocketAddress();
        if (localSocketAddress != null) {
            StringBuffer stringBuffer = new StringBuffer(90);
            stringBuffer.append("<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"");
            stringBuffer.append(localSocketAddress.getPort());
            stringBuffer.append("\" /></cross-domain-policy>\u0000");
            return stringBuffer.toString();
        }
        throw new C2776cn("socket not bound");
    }
}
