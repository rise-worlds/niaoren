package com.stripe.android.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.stripe.android.C2364R;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.PaymentSession;
import com.stripe.android.SourceCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.C2395g;
import com.stripe.android.model.Card;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.StripePaymentSource;

/* loaded from: classes2.dex */
public class AddSourceActivity extends StripeActivity {

    /* renamed from: a */
    public static final String f12363a = "new_source";

    /* renamed from: b */
    static final String f12364b = "AddSourceActivity";

    /* renamed from: c */
    static final String f12365c = "show_zip";

    /* renamed from: d */
    static final String f12366d = "proxy_delay";

    /* renamed from: e */
    static final String f12367e = "update_customer";

    /* renamed from: f */
    CardMultilineWidget f12368f;

    /* renamed from: g */
    AbstractC2419a f12369g;

    /* renamed from: h */
    FrameLayout f12370h;

    /* renamed from: i */
    AbstractC2420b f12371i;

    /* renamed from: p */
    private boolean f12372p;

    /* renamed from: q */
    private boolean f12373q;

    /* renamed from: r */
    private TextView.OnEditorActionListener f12374r = new TextView.OnEditorActionListener() { // from class: com.stripe.android.view.AddSourceActivity.1
        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            if (AddSourceActivity.this.f12368f.getCard() != null) {
                ((InputMethodManager) AddSourceActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AddSourceActivity.this.f12647o.getWindowToken(), 0);
            }
            AddSourceActivity.this.mo17218b();
            return true;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.stripe.android.view.AddSourceActivity$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2419a {
        /* renamed from: a */
        void m17446a(String str);

        /* renamed from: a */
        void m17445a(String str, CustomerSession.AbstractC2376b bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.stripe.android.view.AddSourceActivity$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2420b {
        /* renamed from: a */
        Stripe m17444a(@NonNull Context context);
    }

    @Override // com.stripe.android.view.StripeActivity, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.stripe.android.view.StripeActivity, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.stripe.android.view.StripeActivity, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /* renamed from: a */
    public static Intent m17462a(@NonNull Context context, boolean z, boolean z2) {
        Intent intent = new Intent(context, AddSourceActivity.class);
        intent.putExtra(f12365c, z);
        intent.putExtra(f12367e, z2);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stripe.android.view.StripeActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f12647o.setLayoutResource(C2364R.layout.activity_add_source);
        this.f12647o.inflate();
        this.f12368f = (CardMultilineWidget) findViewById(C2364R.C2366id.add_source_card_entry_widget);
        m17452c();
        this.f12370h = (FrameLayout) findViewById(C2364R.C2366id.add_source_error_container);
        boolean booleanExtra = getIntent().getBooleanExtra(f12365c, false);
        this.f12373q = getIntent().getBooleanExtra(f12367e, false);
        this.f12372p = getIntent().getBooleanExtra(PaymentSession.f11906b, true);
        this.f12368f.setShouldShowPostalCode(booleanExtra);
        if (this.f12373q && !getIntent().getBooleanExtra(f12366d, false)) {
            m17463a();
        }
        setTitle(C2364R.string.title_add_a_card);
    }

    /* renamed from: c */
    private void m17452c() {
        ((TextView) this.f12368f.findViewById(C2364R.C2366id.et_add_source_card_number_ml)).setOnEditorActionListener(this.f12374r);
        ((TextView) this.f12368f.findViewById(C2364R.C2366id.et_add_source_expiry_ml)).setOnEditorActionListener(this.f12374r);
        ((TextView) this.f12368f.findViewById(C2364R.C2366id.et_add_source_cvc_ml)).setOnEditorActionListener(this.f12374r);
        ((TextView) this.f12368f.findViewById(C2364R.C2366id.et_add_source_postal_ml)).setOnEditorActionListener(this.f12374r);
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17463a() {
        m17454a(f12364b, this.f12373q);
        m17454a(PaymentSession.f11905a, this.f12372p);
    }

    @Override // com.stripe.android.view.StripeActivity
    /* renamed from: b */
    protected void mo17218b() {
        Card card = this.f12368f.getCard();
        if (card != null) {
            card.m17893d(f12364b);
            Stripe d = m17451d();
            d.m17536b(PaymentConfiguration.m17996a().m17991b());
            SourceParams a = SourceParams.m17698a(card);
            mo17219a(true);
            d.m17553a(a, new SourceCallback() { // from class: com.stripe.android.view.AddSourceActivity.2
                @Override // com.stripe.android.SourceCallback
                /* renamed from: a */
                public void mo17449a(Exception exc) {
                    AddSourceActivity.this.mo17219a(false);
                    AddSourceActivity.this.m17220a(exc.getLocalizedMessage());
                }

                @Override // com.stripe.android.SourceCallback
                /* renamed from: a */
                public void mo17450a(C2395g gVar) {
                    if (AddSourceActivity.this.f12373q) {
                        AddSourceActivity.this.m17460a((StripePaymentSource) gVar);
                    } else {
                        AddSourceActivity.this.m17461a(gVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17460a(StripePaymentSource qVar) {
        String str;
        CustomerSession.AbstractC2376b bVar = new CustomerSession.AbstractC2376b() { // from class: com.stripe.android.view.AddSourceActivity.3
            @Override // com.stripe.android.CustomerSession.AbstractC2376b
            /* renamed from: a */
            public void mo17447a(@NonNull C2395g gVar) {
                AddSourceActivity.this.m17461a(gVar);
            }

            @Override // com.stripe.android.CustomerSession.AbstractC2376b
            /* renamed from: a */
            public void mo17448a(int i, @Nullable String str2) {
                AddSourceActivity.this.mo17219a(false);
            }
        };
        AbstractC2419a aVar = this.f12369g;
        if (aVar == null) {
            if (qVar instanceof C2395g) {
                str = ((C2395g) qVar).m17757q();
            } else {
                str = qVar instanceof Card ? "card" : "unknown";
            }
            CustomerSession.m18072a().m18068a(this, qVar.mo17592A(), str, bVar);
            return;
        }
        aVar.m17445a(qVar.mo17592A(), bVar);
    }

    /* renamed from: a */
    private void m17454a(@NonNull String str, boolean z) {
        if (this.f12369g != null) {
            m17453b(str, z);
        } else if (z) {
            CustomerSession.m18072a().m18055a(str);
        }
    }

    /* renamed from: b */
    private void m17453b(@NonNull String str, boolean z) {
        AbstractC2419a aVar = this.f12369g;
        if (aVar != null && z) {
            aVar.m17446a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17461a(@NonNull C2395g gVar) {
        mo17219a(false);
        Intent intent = new Intent();
        intent.putExtra(f12363a, gVar.toString());
        setResult(-1, intent);
        finish();
    }

    /* renamed from: d */
    private Stripe m17451d() {
        AbstractC2420b bVar = this.f12371i;
        if (bVar == null) {
            return new Stripe(this);
        }
        return bVar.m17444a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.stripe.android.view.StripeActivity
    /* renamed from: a */
    public void mo17219a(boolean z) {
        super.mo17219a(z);
        CardMultilineWidget cardMultilineWidget = this.f12368f;
        if (cardMultilineWidget != null) {
            cardMultilineWidget.setEnabled(!z);
        }
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17459a(AbstractC2419a aVar) {
        this.f12369g = aVar;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17458a(@NonNull AbstractC2420b bVar) {
        this.f12371i = bVar;
    }
}
