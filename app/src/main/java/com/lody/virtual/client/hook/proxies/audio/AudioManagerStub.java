package com.lody.virtual.client.hook.proxies.audio;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import java.lang.reflect.Method;
import p110z1.IAudioService;

/* loaded from: classes.dex */
public class AudioManagerStub extends BinderInvocationProxy {
    public AudioManagerStub() {
        super(IAudioService.C5163a.asInterface, "audio");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastPkgMethodProxy("adjustVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("adjustLocalOrRemoteStreamVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("adjustSuggestedStreamVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("adjustStreamVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("adjustMasterVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setStreamVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setMasterVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setMicrophoneMute") { // from class: com.lody.virtual.client.hook.proxies.audio.AudioManagerStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                replaceLastUserId(objArr);
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new ReplaceLastPkgMethodProxy("setRingerModeExternal"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setRingerModeInternal"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setMode"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("avrcpSupportsAbsoluteVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("abandonAudioFocus"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("requestAudioFocus"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setWiredDeviceConnectionState"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setSpeakerphoneOn"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("setBluetoothScoOn"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("stopBluetoothSco"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("startBluetoothSco"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("disableSafeMediaVolume"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("registerRemoteControlClient"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("unregisterAudioFocusClient"));
    }
}
