package com.stripe.android;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.VisibleForTesting;
import com.stripe.android.RequestOptions;
import com.stripe.android.StripeApiHandler;
import com.stripe.android.model.AccountParams;
import com.stripe.android.model.BankAccount;
import com.stripe.android.model.C2395g;
import com.stripe.android.model.Card;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.StripePaymentSource;
import com.stripe.android.model.Token;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import p110z1.APIConnectionException;
import p110z1.APIException;
import p110z1.AuthenticationException;
import p110z1.CardException;
import p110z1.InvalidRequestException;
import p110z1.StripeException;

/* renamed from: com.stripe.android.q */
/* loaded from: classes2.dex */
public class Stripe {

    /* renamed from: a */
    AbstractC2410b f12315a = new AbstractC2410b() { // from class: com.stripe.android.q.1
        @Override // com.stripe.android.Stripe.AbstractC2410b
        /* renamed from: a */
        public void mo17528a(@NonNull final SourceParams kVar, @NonNull final String str, @Nullable final String str2, @Nullable Executor executor, @NonNull final SourceCallback pVar) {
            Stripe.this.m17538a(executor, new AsyncTask<Void, Void, C2409a>() { // from class: com.stripe.android.q.1.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* renamed from: a */
                public C2409a doInBackground(Void... voidArr) {
                    try {
                        return new C2409a(StripeApiHandler.m17519a(null, Stripe.this.f12317c, kVar, str, str2, null));
                    } catch (StripeException e) {
                        return new C2409a(e);
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(C2409a aVar) {
                    if (aVar.f12334a != null) {
                        pVar.mo17450a(aVar.f12334a);
                    } else if (aVar.f12336c != null) {
                        pVar.mo17449a(aVar.f12336c);
                    }
                }
            });
        }
    };
    @VisibleForTesting

    /* renamed from: b */
    AbstractC2411c f12316b = new AbstractC2411c() { // from class: com.stripe.android.q.2
        @Override // com.stripe.android.Stripe.AbstractC2411c
        /* renamed from: a */
        public void mo17527a(final Map<String, Object> map, final String str, final String str2, @NonNull final String str3, Executor executor, final TokenCallback yVar) {
            Stripe.this.m17538a(executor, new AsyncTask<Void, Void, C2409a>() { // from class: com.stripe.android.q.2.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* renamed from: a */
                public C2409a doInBackground(Void... voidArr) {
                    try {
                        return new C2409a(StripeApiHandler.m17521a(Stripe.this.f12317c, map, RequestOptions.m17578a(str, str2, RequestOptions.f12301a).m17572a(), str3, Stripe.this.f12318d));
                    } catch (StripeException e) {
                        return new C2409a(e);
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(C2409a aVar) {
                    Stripe.this.m17550a(aVar, yVar);
                }
            });
        }
    };

    /* renamed from: c */
    private Context f12317c;

    /* renamed from: d */
    private StripeApiHandler.AbstractC2412a f12318d;

    /* renamed from: e */
    private String f12319e;

    /* renamed from: f */
    private String f12320f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Stripe.java */
    /* renamed from: com.stripe.android.q$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2410b {
        /* renamed from: a */
        void mo17528a(@NonNull SourceParams kVar, @NonNull String str, @Nullable String str2, @Nullable Executor executor, @NonNull SourceCallback pVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Stripe.java */
    @VisibleForTesting
    /* renamed from: com.stripe.android.q$c */
    /* loaded from: classes2.dex */
    public interface AbstractC2411c {
        /* renamed from: a */
        void mo17527a(Map<String, Object> map, String str, String str2, @NonNull String str3, Executor executor, TokenCallback yVar);
    }

    public Stripe(@NonNull Context context) {
        this.f12317c = context;
    }

    public Stripe(@NonNull Context context, String str) {
        this.f12317c = context;
        m17536b(str);
    }

    /* renamed from: a */
    public void m17563a(@NonNull BankAccount bVar, @NonNull TokenCallback yVar) {
        m17561a(bVar, this.f12319e, (Executor) null, yVar);
    }

    /* renamed from: a */
    public void m17561a(@NonNull BankAccount bVar, @Size(min = 1) @NonNull String str, @Nullable Executor executor, @NonNull TokenCallback yVar) {
        if (bVar != null) {
            m17539a(StripeNetworkUtils.m17476a(this.f12317c, bVar), str, Token.f12284b, executor, yVar);
            return;
        }
        throw new RuntimeException("Required parameter: 'bankAccount' is requred to create a token");
    }

    /* renamed from: a */
    public void m17544a(@NonNull String str, @NonNull TokenCallback yVar) {
        m17541a(str, this.f12319e, (Executor) null, yVar);
    }

    /* renamed from: a */
    public void m17541a(@NonNull String str, @Size(min = 1) @NonNull String str2, @Nullable Executor executor, @NonNull TokenCallback yVar) {
        m17539a(StripeNetworkUtils.m17474a(this.f12317c, str), str2, Token.f12285c, executor, yVar);
    }

    /* renamed from: a */
    public Token m17564a(BankAccount bVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return m17562a(bVar, this.f12319e);
    }

    /* renamed from: a */
    public Token m17562a(BankAccount bVar, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        m17533d(str);
        RequestOptions a = RequestOptions.m17578a(str, this.f12320f, RequestOptions.f12301a).m17572a();
        Context context = this.f12317c;
        return StripeApiHandler.m17521a(context, StripeNetworkUtils.m17476a(context, bVar), a, Token.f12284b, this.f12318d);
    }

    /* renamed from: a */
    public void m17553a(@NonNull SourceParams kVar, @NonNull SourceCallback pVar) {
        m17552a(kVar, pVar, (String) null, (Executor) null);
    }

    /* renamed from: a */
    public void m17552a(@NonNull SourceParams kVar, @NonNull SourceCallback pVar, @Nullable String str, @Nullable Executor executor) {
        if (str == null) {
            str = this.f12319e;
        }
        if (str != null) {
            this.f12315a.mo17528a(kVar, str, this.f12320f, executor, pVar);
        }
    }

    /* renamed from: a */
    public void m17559a(@NonNull Card cVar, @NonNull TokenCallback yVar) {
        m17557a(cVar, this.f12319e, yVar);
    }

    /* renamed from: a */
    public void m17557a(@NonNull Card cVar, @NonNull String str, @NonNull TokenCallback yVar) {
        m17556a(cVar, str, (Executor) null, yVar);
    }

    /* renamed from: a */
    public void m17555a(@NonNull Card cVar, @NonNull Executor executor, @NonNull TokenCallback yVar) {
        m17556a(cVar, this.f12319e, executor, yVar);
    }

    /* renamed from: a */
    public void m17556a(@NonNull Card cVar, @Size(min = 1) @NonNull String str, @Nullable Executor executor, @NonNull TokenCallback yVar) {
        if (cVar != null) {
            m17539a(StripeNetworkUtils.m17475a(this.f12317c, cVar), str, "card", executor, yVar);
            return;
        }
        throw new RuntimeException("Required Parameter: 'card' is required to create a token");
    }

    @Nullable
    /* renamed from: a */
    public C2395g m17554a(@NonNull SourceParams kVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return m17551a(kVar, (String) null);
    }

    /* renamed from: a */
    public C2395g m17551a(@NonNull SourceParams kVar, @Nullable String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        if (str == null) {
            str = this.f12319e;
        }
        if (str == null) {
            return null;
        }
        return StripeApiHandler.m17519a(null, this.f12317c, kVar, str, this.f12320f, this.f12318d);
    }

    /* renamed from: a */
    public Token m17560a(Card cVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return m17558a(cVar, this.f12319e);
    }

    /* renamed from: a */
    public Token m17558a(Card cVar, String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        m17533d(str);
        RequestOptions a = RequestOptions.m17578a(str, this.f12320f, RequestOptions.f12301a).m17572a();
        Context context = this.f12317c;
        return StripeApiHandler.m17521a(context, StripeNetworkUtils.m17475a(context, cVar), a, "card", this.f12318d);
    }

    /* renamed from: a */
    public Token m17545a(@NonNull String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return m17543a(str, this.f12319e);
    }

    /* renamed from: a */
    public Token m17543a(@NonNull String str, String str2) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        m17533d(str2);
        RequestOptions a = RequestOptions.m17578a(str2, this.f12320f, RequestOptions.f12301a).m17572a();
        Context context = this.f12317c;
        return StripeApiHandler.m17521a(context, StripeNetworkUtils.m17474a(context, str), a, Token.f12285c, this.f12318d);
    }

    /* renamed from: a */
    public Token m17566a(@NonNull AccountParams aVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return m17565a(aVar, this.f12319e);
    }

    /* renamed from: a */
    public Token m17565a(@NonNull AccountParams aVar, @Nullable String str) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        if ((str == null ? this.f12319e : str) == null) {
            return null;
        }
        m17533d(str);
        try {
            return StripeApiHandler.m17521a(this.f12317c, aVar.m17927a(), RequestOptions.m17578a(str, this.f12320f, RequestOptions.f12301a).m17572a(), "account", this.f12318d);
        } catch (CardException unused) {
            return null;
        }
    }

    /* renamed from: a */
    public void m17540a(@NonNull List<String> list, @NonNull StripePaymentSource qVar) {
        Map<String, Object> map;
        RequestOptions.C2403a a = RequestOptions.m17580a(this.f12319e);
        String str = this.f12320f;
        if (str != null) {
            a.m17567e(str);
        }
        RequestOptions a2 = a.m17572a();
        if (qVar instanceof Token) {
            map = LoggingUtils.m18005a(this.f12317c, list, this.f12319e, ((Token) qVar).m17585d());
        } else {
            map = LoggingUtils.m18000b(this.f12317c, list, this.f12319e, ((C2395g) qVar).m17757q());
        }
        StripeApiHandler.m17501a(map, a2, this.f12318d);
    }

    /* renamed from: b */
    public C2395g m17535b(@Size(min = 1) @NonNull String str, @Size(min = 1) @NonNull String str2) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return m17542a(str, str2, (String) null);
    }

    /* renamed from: a */
    public C2395g m17542a(@Size(min = 1) @NonNull String str, @Size(min = 1) @NonNull String str2, @Nullable String str3) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        if (str3 == null) {
            str3 = this.f12319e;
        }
        if (str3 == null) {
            return null;
        }
        return StripeApiHandler.m17509a(str, str2, str3);
    }

    /* renamed from: b */
    public void m17536b(@Size(min = 1) @NonNull String str) {
        m17533d(str);
        this.f12319e = str;
    }

    /* renamed from: c */
    public void m17534c(@Size(min = 1) @NonNull String str) {
        this.f12320f = str;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17546a(StripeApiHandler.AbstractC2412a aVar) {
        this.f12318d = aVar;
    }

    /* renamed from: a */
    private void m17539a(@NonNull Map<String, Object> map, @Size(min = 1) @NonNull String str, @NonNull String str2, @Nullable Executor executor, @NonNull TokenCallback yVar) {
        if (yVar != null) {
            m17533d(str);
            this.f12316b.mo17527a(map, str, this.f12320f, str2, executor, yVar);
            return;
        }
        throw new RuntimeException("Required Parameter: 'callback' is required to use the created token and handle errors");
    }

    /* renamed from: d */
    private void m17533d(@Size(min = 1) @NonNull String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid Publishable Key: You must use a valid publishable key to create a token.  For more info, see https://stripe.com/docs/stripe.js.");
        } else if (str.startsWith("sk_")) {
            throw new IllegalArgumentException("Invalid Publishable Key: You are using a secret key to create a token, instead of the publishable one. For more info, see https://stripe.com/docs/stripe.js");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17550a(C2409a aVar, TokenCallback yVar) {
        if (aVar.f12335b != null) {
            yVar.m17191a(aVar.f12335b);
        } else if (aVar.f12336c != null) {
            yVar.m17190a(aVar.f12336c);
        } else {
            yVar.m17190a(new RuntimeException("Somehow got neither a token response or an error response"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17538a(Executor executor, AsyncTask<Void, Void, C2409a> asyncTask) {
        if (executor != null) {
            asyncTask.executeOnExecutor(executor, new Void[0]);
        } else {
            asyncTask.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Stripe.java */
    /* renamed from: com.stripe.android.q$a */
    /* loaded from: classes2.dex */
    public class C2409a {

        /* renamed from: a */
        final C2395g f12334a;

        /* renamed from: b */
        final Token f12335b;

        /* renamed from: c */
        final Exception f12336c;

        private C2409a(Token sVar) {
            this.f12335b = sVar;
            this.f12334a = null;
            this.f12336c = null;
        }

        private C2409a(C2395g gVar) {
            this.f12334a = gVar;
            this.f12336c = null;
            this.f12335b = null;
        }

        private C2409a(Exception exc) {
            this.f12336c = exc;
            this.f12334a = null;
            this.f12335b = null;
        }
    }
}
