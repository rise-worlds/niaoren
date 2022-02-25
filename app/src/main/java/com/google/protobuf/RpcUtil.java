package com.google.protobuf;

/* loaded from: classes.dex */
public final class RpcUtil {
    /* JADX WARN: Multi-variable type inference failed */
    public static <Type extends Message> RpcCallback<Type> specializeCallback(RpcCallback<Message> rpcCallback) {
        return rpcCallback;
    }

    private RpcUtil() {
    }

    public static <Type extends Message> RpcCallback<Message> generalizeCallback(final RpcCallback<Type> rpcCallback, final Class<Type> cls, final Type type) {
        return new RpcCallback<Message>() { // from class: com.google.protobuf.RpcUtil.1
            public void run(Message message) {
                Message message2;
                try {
                    message2 = (Message) cls.cast(message);
                } catch (ClassCastException unused) {
                    message2 = RpcUtil.copyAsType(type, message);
                }
                rpcCallback.run(message2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <Type extends Message> Type copyAsType(Type type, Message message) {
        return (Type) type.newBuilderForType().mergeFrom(message).build();
    }

    public static <ParameterType> RpcCallback<ParameterType> newOneTimeCallback(final RpcCallback<ParameterType> rpcCallback) {
        return new RpcCallback<ParameterType>() { // from class: com.google.protobuf.RpcUtil.2
            private boolean alreadyCalled = false;

            @Override // com.google.protobuf.RpcCallback
            public void run(ParameterType parametertype) {
                synchronized (this) {
                    if (!this.alreadyCalled) {
                        this.alreadyCalled = true;
                    } else {
                        throw new AlreadyCalledException();
                    }
                }
                RpcCallback.this.run(parametertype);
            }
        };
    }

    /* loaded from: classes.dex */
    public static final class AlreadyCalledException extends RuntimeException {
        private static final long serialVersionUID = 5469741279507848266L;

        public AlreadyCalledException() {
            super("This RpcCallback was already called and cannot be called multiple times.");
        }
    }
}
