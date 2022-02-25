package com.cyjh.ddy.media.oksocket;

import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.bean.WebSocketResult;

/* loaded from: classes.dex */
public interface IControlSocketListener {

    /* loaded from: classes.dex */
    public interface IOnMessageListener {
        void onClosed(String str);

        void onConnected(ControlSocket controlSocket);

        void onFailure(ControlSocket controlSocket, String str);

        void onSended(ControlSocket controlSocket);
    }

    /* loaded from: classes.dex */
    public interface OnCommandResponseInfoMessageListener extends IOnMessageListener {
        void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket);
    }

    /* loaded from: classes.dex */
    public interface OnTextMessageListener extends IOnMessageListener {
        void onMessage(String str);
    }

    /* loaded from: classes.dex */
    public interface OnWebSocketResultMessageListener extends IOnMessageListener {
        void onMessage(WebSocketResult webSocketResult);
    }
}
