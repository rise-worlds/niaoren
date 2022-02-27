package com.stripe.android.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.stripe.android.C2364R;
import com.stripe.android.CustomerSession;
import com.stripe.android.PaymentSession;
import com.stripe.android.model.Customer;
import com.stripe.android.model.CustomerSource;
import java.util.List;

/* loaded from: classes2.dex */
public class PaymentMethodsActivity extends AppCompatActivity {

    /* renamed from: a */
    public static final String f12538a = "selected_payment";

    /* renamed from: b */
    static final String f12539b = "proxy_delay";

    /* renamed from: c */
    static final String f12540c = "PaymentMethodsActivity";

    /* renamed from: d */
    static final int f12541d = 700;

    /* renamed from: e */
    private boolean f12542e;

    /* renamed from: f */
    private Customer f12543f;

    /* renamed from: g */
    private AbstractC2466a f12544g;

    /* renamed from: h */
    private MaskedCardAdapter f12545h;

    /* renamed from: i */
    private ProgressBar f12546i;

    /* renamed from: j */
    private RecyclerView f12547j;

    /* renamed from: k */
    private boolean f12548k;

    /* renamed from: l */
    private boolean f12549l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.stripe.android.view.PaymentMethodsActivity$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2466a {
        @Nullable
        /* renamed from: a */
        Customer m17303a();

        /* renamed from: a */
        void m17302a(@NonNull CustomerSession.AbstractC2375a aVar);

        /* renamed from: a */
        void m17301a(String str);

        /* renamed from: a */
        void m17300a(@NonNull String str, @NonNull String str2, @Nullable CustomerSession.AbstractC2375a aVar);

        /* renamed from: b */
        void m17299b(@NonNull CustomerSession.AbstractC2375a aVar);
    }

    /* renamed from: a */
    public static Intent m17325a(Context context) {
        return new Intent(context, PaymentMethodsActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2364R.layout.activity_payment_methods);
        this.f12546i = (ProgressBar) findViewById(C2364R.C2366id.payment_methods_progress_bar);
        this.f12547j = (RecyclerView) findViewById(C2364R.C2366id.payment_methods_recycler);
        View findViewById = findViewById(C2364R.C2366id.payment_methods_add_payment_container);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.stripe.android.view.PaymentMethodsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent a = AddSourceActivity.m17462a(PaymentMethodsActivity.this, false, true);
                if (PaymentMethodsActivity.this.f12549l) {
                    a.putExtra(PaymentSession.f11906b, true);
                }
                PaymentMethodsActivity.this.startActivityForResult(a, 700);
            }
        });
        setSupportActionBar((Toolbar) findViewById(C2364R.C2366id.payment_methods_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!getIntent().getBooleanExtra(f12539b, false)) {
            m17326a();
        }
        findViewById.requestFocusFromTouch();
        this.f12549l = getIntent().hasExtra(PaymentSession.f11906b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 700 && i2 == -1) {
            m17317a(true);
            m17316b();
            CustomerSession.AbstractC2375a aVar = new CustomerSession.AbstractC2375a() { // from class: com.stripe.android.view.PaymentMethodsActivity.2
                @Override // com.stripe.android.CustomerSession.AbstractC2375a
                /* renamed from: a */
                public void mo17304a(@NonNull Customer dVar) {
                    PaymentMethodsActivity.this.m17324a(dVar);
                }

                @Override // com.stripe.android.CustomerSession.AbstractC2375a
                /* renamed from: a */
                public void mo17305a(int i3, @Nullable String str) {
                    if (str == null) {
                        str = "";
                    }
                    PaymentMethodsActivity.this.m17311b(str);
                    PaymentMethodsActivity.this.m17317a(false);
                }
            };
            AbstractC2466a aVar2 = this.f12544g;
            if (aVar2 == null) {
                CustomerSession.m18072a().m18045b(aVar);
            } else {
                aVar2.m17299b(aVar);
            }
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C2364R.C2366id.action_save).setIcon(C2486n.m17212a(this, getTheme(), C2364R.attr.titleTextColor, C2364R.C2365drawable.ic_checkmark));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C2364R.C2367menu.add_source_menu, menu);
        menu.findItem(C2364R.C2366id.action_save).setEnabled(!this.f12542e);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C2364R.C2366id.action_save) {
            m17306f();
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        if (!onOptionsItemSelected) {
            onBackPressed();
        }
        return onOptionsItemSelected;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17326a() {
        Customer dVar;
        AbstractC2466a aVar = this.f12544g;
        if (aVar == null) {
            dVar = CustomerSession.m18072a().m18039d();
        } else {
            dVar = aVar.m17303a();
        }
        if (dVar != null) {
            this.f12543f = dVar;
            m17310c();
            return;
        }
        m17307e();
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17323a(AbstractC2466a aVar) {
        this.f12544g = aVar;
    }

    /* renamed from: b */
    private void m17316b() {
        AbstractC2466a aVar = this.f12544g;
        if (aVar == null) {
            if (this.f12549l) {
                CustomerSession.m18072a().m18055a(PaymentSession.f11905a);
            }
            CustomerSession.m18072a().m18055a(f12540c);
            return;
        }
        if (this.f12549l) {
            aVar.m17301a(PaymentSession.f11905a);
        }
        this.f12544g.m17301a(f12540c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17324a(@NonNull Customer dVar) {
        if (!TextUtils.isEmpty(dVar.m17812d()) || dVar.m17810f().size() != 1) {
            m17315b(dVar);
            return;
        }
        CustomerSession.AbstractC2375a aVar = new CustomerSession.AbstractC2375a() { // from class: com.stripe.android.view.PaymentMethodsActivity.3
            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17304a(@NonNull Customer dVar2) {
                PaymentMethodsActivity.this.m17315b(dVar2);
            }

            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17305a(int i, @Nullable String str) {
                if (str == null) {
                    str = "";
                }
                PaymentMethodsActivity.this.m17311b(str);
                PaymentMethodsActivity.this.m17317a(false);
            }
        };
        CustomerSource eVar = dVar.m17810f().get(0);
        if (eVar == null || eVar.mo17592A() == null) {
            m17315b(dVar);
            return;
        }
        AbstractC2466a aVar2 = this.f12544g;
        if (aVar2 == null) {
            CustomerSession.m18072a().m18069a(this, eVar.mo17592A(), eVar.m17800g(), aVar);
        } else {
            aVar2.m17300a(eVar.mo17592A(), eVar.m17800g(), aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m17310c() {
        m17317a(false);
        Customer dVar = this.f12543f;
        if (dVar != null) {
            List<CustomerSource> f = dVar.m17810f();
            if (!this.f12548k) {
                this.f12545h = new MaskedCardAdapter(f);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                this.f12547j.setHasFixedSize(false);
                this.f12547j.setLayoutManager(linearLayoutManager);
                this.f12547j.setAdapter(this.f12545h);
                this.f12548k = true;
            } else {
                this.f12545h.m17244a(f);
            }
            String d = this.f12543f.m17812d();
            if (!TextUtils.isEmpty(d)) {
                this.f12545h.m17245a(d);
            }
            this.f12545h.notifyDataSetChanged();
        }
    }

    /* renamed from: d */
    private void m17308d() {
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17318a(String str) {
        CustomerSource a = this.f12543f.m17816a(str);
        if (a != null) {
            Intent intent = new Intent();
            intent.putExtra(f12538a, a.toString());
            setResult(-1, intent);
        } else {
            setResult(0);
        }
        finish();
    }

    /* renamed from: e */
    private void m17307e() {
        CustomerSession.AbstractC2375a aVar = new CustomerSession.AbstractC2375a() { // from class: com.stripe.android.view.PaymentMethodsActivity.4
            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17304a(@NonNull Customer dVar) {
                PaymentMethodsActivity.this.f12543f = dVar;
                PaymentMethodsActivity.this.m17310c();
            }

            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17305a(int i, @Nullable String str) {
                PaymentMethodsActivity.this.m17317a(false);
            }
        };
        m17317a(true);
        AbstractC2466a aVar2 = this.f12544g;
        if (aVar2 == null) {
            CustomerSession.m18072a().m18066a(aVar);
        } else {
            aVar2.m17302a(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17317a(boolean z) {
        this.f12542e = z;
        if (z) {
            this.f12546i.setVisibility(0);
        } else {
            this.f12546i.setVisibility(8);
        }
        supportInvalidateOptionsMenu();
    }

    /* renamed from: f */
    private void m17306f() {
        MaskedCardAdapter gVar = this.f12545h;
        if (gVar == null || gVar.m17251a() == null) {
            m17308d();
            return;
        }
        CustomerSource a = this.f12545h.m17251a();
        CustomerSession.AbstractC2375a aVar = new CustomerSession.AbstractC2375a() { // from class: com.stripe.android.view.PaymentMethodsActivity.5
            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17304a(@NonNull Customer dVar) {
                PaymentMethodsActivity.this.f12543f = dVar;
                PaymentMethodsActivity.this.m17318a(dVar.m17812d());
                PaymentMethodsActivity.this.m17317a(false);
            }

            @Override // com.stripe.android.CustomerSession.AbstractC2375a
            /* renamed from: a */
            public void mo17305a(int i, @Nullable String str) {
                if (str == null) {
                    str = "";
                }
                PaymentMethodsActivity.this.m17311b(str);
                PaymentMethodsActivity.this.m17317a(false);
            }
        };
        if (a != null && a.mo17592A() != null) {
            AbstractC2466a aVar2 = this.f12544g;
            if (aVar2 == null) {
                CustomerSession.m18072a().m18069a(this, a.mo17592A(), a.m17800g(), aVar);
            } else {
                aVar2.m17300a(a.mo17592A(), a.m17800g(), aVar);
            }
            m17317a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m17311b(@NonNull String str) {
        new AlertDialog.Builder(this).setMessage(str).setCancelable(true).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.stripe.android.view.PaymentMethodsActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m17315b(@NonNull Customer dVar) {
        if (this.f12545h == null) {
            m17310c();
            if (this.f12543f == null) {
                return;
            }
        }
        this.f12545h.m17248a(dVar);
        m17317a(false);
    }
}
